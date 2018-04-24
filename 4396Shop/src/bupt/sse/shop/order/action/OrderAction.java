package bupt.sse.shop.order.action;

import java.util.Date;

import javax.servlet.Servlet;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import bupt.sse.shop.order.service.OrderService;
import bupt.sse.shop.order.vo.Order;
import bupt.sse.shop.order.vo.OrderItem;
import bupt.sse.shop.product.vo.Product;
import bupt.sse.shop.user.vo.User;

public class OrderAction extends ActionSupport implements ModelDriven<Order>{
	
	private Order order = new Order();
	private OrderService orderService;
	
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public Order getModel(){
		return order;
	}
	
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
	
}
