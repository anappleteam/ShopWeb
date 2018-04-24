package bupt.sse.shop.order.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.swing.text.html.HTML;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.debugging.PrettyPrintWriter;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayAccount;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.domain.AlipayTradePayModel;
import com.alipay.api.domain.AlipayTradePrecreateModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.alipay.api.response.AlipayTradePayResponse;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import bupt.sse.shop.order.service.OrderService;
import bupt.sse.shop.order.vo.Order;
import bupt.sse.shop.order.vo.OrderItem;
import bupt.sse.shop.product.vo.Product;
import bupt.sse.shop.user.vo.User;
import bupt.sse.shop.utils.PageBean;

public class OrderAction extends ActionSupport implements ModelDriven<Order>{
	
	private Order order = new Order();
	private OrderService orderService;
	private Integer page;
	
	
	
	public void setPage(Integer page) {
		this.page = page;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public Order getModel(){
		return order;
	}
	
	//从购物车到订单
	
	public String save(){
		order.setOrdertime(new Date());
		order.setState(1);
		order.setTotal(1.00);
		
		//vertual data
		Product product = new Product();
		product.setImage("'product/vd.jpg'");
		product.setIs_hot(1);
		product.setMarket_price(1.00);
		product.setPdesc("virtual data");
		product.setPid(70);
		product.setPname("Virtual");
		product.setShop_price(1.00);
		
		OrderItem orderItem = new OrderItem();
		orderItem.setCount(1);
		orderItem.setSubtotal(1.00);
		orderItem.setProduct(product);
		orderItem.setOrder(order);
		
		order.getOrderItems().add(orderItem);
		User existUser = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("existUser");
		if(existUser == null){
			this.addActionError("亲！您还没有登录！请先登录！");
			return "login";
		}
		order.setUser(existUser);
		orderService.save(order);
		
		
		return "saveSuccess";
	}
	
	
	//查询我的订单
	public String findByUid(){
		User user = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("existUser");
		PageBean<Order> pageBean = orderService.findByPageUid(user.getUid(),page);
		ActionContext.getContext().getValueStack().set("pageBean",pageBean);
		return "findByUidSuccess";
	}
	
	public String findByOid(){
		order = orderService.findByOid(order.getOid());
		return "findByOidSuccess";
	}
	
	public void payOrder() throws IOException, AlipayApiException{
		Order curOrder = orderService.findByOid(order.getOid());
		curOrder.setAddr(order.getAddr());
		curOrder.setPhone(order.getPhone());
		curOrder.setName(order.getName());
		orderService.update(curOrder);
		
		
		//alipay
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key,"json",AlipayConfig.charset,AlipayConfig.alipay_public_key,AlipayConfig.sign_type);
		AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
		request.setReturnUrl(AlipayConfig.return_url);
		request.setNotifyUrl(AlipayConfig.notify_url);
		String realNo = "43967777"+curOrder.getOid().toString();
		String realNt = curOrder.getTotal().toString();
		String realNa = curOrder.getName().toString()+"在4396购物网消费的商品";
		String realNb = curOrder.getName().toString()+"于"+curOrder.getOrdertime()+"在4396购物网消费的商品";
		request.setBizContent("{\"out_trade_no\":\""+ realNo +"\"," 
				+ "\"total_amount\":\""+ realNt +"\"," 
				+ "\"subject\":\""+ realNa +"\"," 
				+ "\"body\":\""+ realNa +"\"," 
				+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

		String form="";
		form = alipayClient.pageExecute(request).getBody();
		System.out.println(form);
		HttpServletResponse re = ServletActionContext.getResponse();
		re.setContentType("text/html;charset=utf-8");
		PrintWriter out = re.getWriter();
		out.println(form);
		out.flush();
		out.close();
	
	}
	
	public String returnorder() throws UnsupportedEncodingException, AlipayApiException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Map<String,String> params = new HashMap<String,String>();
		Map<String,String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用
			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}
		
		boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名

		//——请在这里编写您的程序（以下代码仅作参考）——
		if(signVerified) {
			//商户订单号
			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
		
			//支付宝交易号
			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
		
			//付款金额
			String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");
			

			Order currOrder = orderService.findByOid(Integer.parseInt(out_trade_no.replace("43967777", "")));
			currOrder.setState(2);
			orderService.update(currOrder);
			
			System.out.println("trade_no:"+trade_no+"<br/>out_trade_no:"+out_trade_no+"<br/>total_amount:"+total_amount);
		}else {
			System.out.println("验签失败");
		}
		return "paySuccess";
    }
	
}
