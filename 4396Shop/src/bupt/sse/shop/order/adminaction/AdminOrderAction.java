package bupt.sse.shop.order.adminaction;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import bupt.sse.shop.order.service.OrderService;
import bupt.sse.shop.order.vo.Order;
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
}
