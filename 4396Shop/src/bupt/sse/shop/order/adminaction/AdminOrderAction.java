package bupt.sse.shop.order.adminaction;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import bupt.sse.shop.order.service.OrderService;
import bupt.sse.shop.order.vo.Order;
import bupt.sse.shop.order.vo.OrderItem;
import bupt.sse.shop.utils.PageBean;

/**
 * 后台订单管理
 * 
 * @author mly
 *
 */

public class AdminOrderAction extends ActionSupport implements ModelDriven<Order>{
	
	//模型驱动对象
	private Order order=new Order();

	@Override
	public Order getModel() {
		return order;
	}
	
	//注入订单管理Service
	private OrderService orderService=new OrderService();

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	//接收page参数
	private Integer page;

	public void setPage(Integer page) {
		this.page = page;
	}
	
	//带分页查询的执行方法
	public String findAll() {
		//分页查询
		PageBean<Order> pageBean=orderService.findByPageUid(page);
		//通过值栈保存数据
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		//页面跳转
		return "findAll";
	}
	
	//根据订单id查询订单项
	public String findOrderItem() {
		//根据订单id查询订单项
		List<OrderItem> list=orderService.findOrderItem(order.getOid());
		//通过值栈显示到页面上
		ActionContext.getContext().getValueStack().set("list", list);
		//页面跳转
		return "findOrderItem";
	}
	
	//后台修改订单状态
	public String updateState() {
		//根据订单查询订单
		Order curOrder=orderService.findByOid(order.getOid());
		//修改订单状态
		curOrder.setState(3);
		orderService.update(curOrder);
		//页面跳转
		return "updateStateSuccess";
	}
}
