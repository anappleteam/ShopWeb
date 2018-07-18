package bupt.sse.shop.order.action;


import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;

import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.mchange.v2.beans.BeansUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import bupt.sse.shop.cart.service.CartItemService;
import bupt.sse.shop.cart.vo.Cart;
import bupt.sse.shop.cart.vo.CartItem;
import bupt.sse.shop.conment.service.ConmentService;
import bupt.sse.shop.conment.vo.Conment;
import bupt.sse.shop.order.service.OrderItemService;
import bupt.sse.shop.order.service.OrderService;
import bupt.sse.shop.order.vo.Order;
import bupt.sse.shop.order.vo.OrderItem;
import bupt.sse.shop.product.service.ProductService;
import bupt.sse.shop.product.vo.Product;
import bupt.sse.shop.user.vo.User;
import bupt.sse.shop.utils.PageBean;

public class OrderAction extends ActionSupport implements ModelDriven<Order>{
	
	private Order order = new Order();
	private OrderService orderService;
	private OrderItemService orderItemService;
	private ConmentService conmentService;
	

	//revieve uid 
	private Integer uid;

	private CartItemService cartItemService;
	private Integer page;
	private Double total=0.0;
	private Integer itemid;
	
	private ProductService productService;
	
	public void setPage(Integer page) {
		this.page = page;
	}
	
	public void setItemid(Integer itemid) {
		this.itemid = itemid;
	}
	
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	public void setOrderItemService(OrderItemService orderItemService) {
		this.orderItemService = orderItemService;
	}
	
	public void setConmentService(ConmentService conmentService) {
		this.conmentService = conmentService;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}
	
	public void setCartItemService(CartItemService cartItemService) {
		this.cartItemService = cartItemService;
	}
	
	public Order getModel(){
		return order;
	}
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	//从购物车到订单
	private String cidsstring;
	public void setCidsstring(String cidsstring) {
		this.cidsstring = cidsstring;
	}
	@Transactional(isolation=Isolation.REPEATABLE_READ)
	public String save() throws ParseException, IOException{
		// 加前置通知
		User existUser = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("existUser");
		if(existUser == null){
			this.addActionError("亲！您还没有登录！请先登录！");
			return "login";
		}
		order.setUser(existUser);
		order.setName(existUser.getName());
		order.setAddr(existUser.getAddr());
		order.setPhone(existUser.getPhone());
		String[] cids=cidsstring.split(",");
		Cart cart = (Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		if (cart == null) {
			this.addActionMessage("亲!您还没有购物!");
			return "msg";
		}
		List<CartItem> cartItems=cart.getCartItems();
		Map<Integer,CartItem> cartItemMap=cartItems.stream().collect(Collectors.toMap(CartItem::getCitemid, a -> a,(k1,k2)->k1));
		for (int i = 0; i < cids.length; i++) {
			String string = cids[i];
			Integer cid=Integer.valueOf(string);
			if(cartItemMap.containsKey(cid))
			{
				//购物项中的信息填入订单项
				CartItem cartItem=cartItemMap.get(cid);
				OrderItem orderItem = new OrderItem();
				orderItem.setCount(cartItem.getCount());
				orderItem.setSubtotal(cartItem.getSubtotal());
				orderItem.setProduct(cartItem.getProduct());
				orderItem.setOrder(order);
				orderItem.setStore(cartItem.getProduct().getStore());
				//修改订单项的总金额
				total+=cartItem.getSubtotal();
				order.getOrderItems().add(orderItem);
				//将对应购物项从购物车移除
				cartItemService.removeCartItem(cartItem.getCitemid());
				//更新商品库存
				Product product=cartItem.getProduct();
				Integer new_pAvailable=product.getPavailable()-orderItem.getCount();
				if (new_pAvailable>=0) {
					product.setPavailable(new_pAvailable);				
				}
				productService.update(product);
			}	
		}
		order.setTotal(total);
		order.setState(1);
		Date date=new Date();                             
        SimpleDateFormat temp=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        String date2=temp.format(date);  
        Date date3=temp.parse(date2);  
		order.setOrdertime(date3);
		orderService.save(order);
		
		return "saveSuccess";
	}
	
	
	//查询我的订单
	@Transactional(readOnly=true)
	public String findByUid(){
		User user = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("existUser");
		PageBean<Order> pageBean = orderService.findByPageUid(user.getUid(),page);
		ActionContext.getContext().getValueStack().set("pageBean",pageBean);
		return "findByUidSuccess";
	}
	
	@Transactional(readOnly=true)
	public String findByOid(){
		order = orderService.findByOid(order.getOid());
		return "findByOidSuccess";
	}
	
	@Transactional(isolation=Isolation.REPEATABLE_READ)
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
	
	@Transactional(isolation=Isolation.REPEATABLE_READ)
	public String notifyorder() throws AlipayApiException, UnsupportedEncodingException{
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
		
		/* 实际验证过程建议商户务必添加以下校验：
		1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
		2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
		3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
		4、验证app_id是否为该商户本身。
		*/
		if(signVerified) {//验证成功
			//商户订单号
			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
		
			//支付宝交易号
			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
		
			//交易状态
			String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
			
			if(trade_status.equals("TRADE_FINISHED")){
				//判断该笔订单是否在商户网站中已经做过处理
				//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				//如果有做过处理，不执行商户的业务程序
					
				//注意：
				//退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
			}else if (trade_status.equals("TRADE_SUCCESS")){
				//判断该笔订单是否在商户网站中已经做过处理
				//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				//如果有做过处理，不执行商户的业务程序
				
				//注意：
				//付款完成后，支付宝系统发送该交易状态通知
			}
			Order currOrder = orderService.findByOid(Integer.parseInt(out_trade_no.replace("43967777", "")));
			currOrder.setState(2);
			orderService.update(currOrder);
			for (OrderItem orderitem : currOrder.getOrderItems()) {
				orderitem.setState(0);
				orderItemService.update(orderitem);
			}
			
			System.out.println("success");
			
		}else {//验证失败
			System.out.println("fail");
		
			//调试用，写文本函数记录程序运行情况是否正常
			//String sWord = AlipaySignature.getSignCheckContentV1(params);
			//AlipayConfig.logResult(sWord);
		}
		return "paySuccess";
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

			for (OrderItem orderitem : currOrder.getOrderItems()) {
				orderitem.setState(0);
				orderItemService.update(orderitem);
			}
			
			System.out.println("trade_no:"+trade_no+"<br/>out_trade_no:"+out_trade_no+"<br/>total_amount:"+total_amount);
		}else {
			System.out.println("验签失败");
		}
		return "paySuccess";
    }
	
	//确认收货Action
	public String updateState() throws Exception{
		//根据订单id查询订单
		
		int receiveall=0;
		OrderItem curItem = orderItemService.findByTid(itemid);
		curItem.setState(1);
		orderItemService.update(curItem);
		Order curOrder=orderService.findByOid(curItem.getOrder().getOid());
		for(OrderItem orderItem : curOrder.getOrderItems()){
			if(orderItem.getState()!=1)
				receiveall=1;
		}
		if(receiveall==0){
			curOrder.setState(4);
			orderService.update(curOrder);
		}
		return "updateStateSuccess";
	}
	
	public void submitscore(){
		User user = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("existUser");
		HttpServletRequest request = ServletActionContext.getRequest();  
		int item = Integer.parseInt(request.getParameter("itemid"));
		int eva = Integer.parseInt(request.getParameter("evaluate"));
		String com = request.getParameter("comment");
		OrderItem curItem = orderItemService.findByTid(item);
		curItem.setComment(com);
		curItem.setEvaluate(eva);
		orderItemService.update(curItem);
		Conment conment = new Conment();
		conment.setUsername(user.getUsername());
		conment.setEvaluate(eva);
		conment.setContent(com);
		conment.setProduct(curItem.getProduct());
		conmentService.save(conment);
	}
}
