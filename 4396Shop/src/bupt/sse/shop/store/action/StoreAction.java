package bupt.sse.shop.store.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import bupt.sse.shop.order.service.OrderService;
import bupt.sse.shop.order.vo.Order;
import bupt.sse.shop.order.vo.OrderItem;
import bupt.sse.shop.product.vo.Product;
import bupt.sse.shop.store.service.StoreService;
import bupt.sse.shop.store.vo.Store;
import bupt.sse.shop.user.vo.User;
import bupt.sse.shop.utils.PageBean;

public class StoreAction extends ActionSupport implements ModelDriven<OrderItem>{
	private StoreService storeService;
	private OrderService orderService;
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	//模型驱动对象
	private OrderItem orderItem=new OrderItem();
	
	@Override
	public OrderItem getModel() {
		return orderItem;
	}
	private Integer sid;
	private Integer page;
	private Store store;

	public Store getStore() {
		return store;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public void setStoreService(StoreService storeService) {
		this.storeService = storeService;
	} 
	
	//我的商店
	public String myStores() {
		return "myStores";
	}
	
	public String productMng(){
		return null;
		
	}
	
	public String storeMng() {
		
		return "storeMng";
	}
	
	//商店的订单管理
	public String orderMng(){
		//分页查询
		PageBean<OrderItem> pageBean;
		//修改状态刷新后使用此查询方法
		if(sid==null&&orderItem!=null){
			pageBean=orderService.findBySidPage(orderItem.getStore().getSid(), page);
		}
		else 
		{
			pageBean=orderService.findBySidPage(sid, page);
		}
		//通过值栈保存数据
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		//页面跳转
		return "orderMng";
	}
		
		//根据订单id查询订单项
		public String findOrderItem() throws IOException {
			//根据订单id查询订单项
			OrderItem orderitem=orderService.findByTid(orderItem.getItemid());
			//通过值栈显示到页面上
			ActionContext.getContext().getValueStack().set("orderItem", orderitem);
			return "findOrderItem";
		}
		
		//后台修改订单状态
		public String updateState() {
			//根据订单查询订单
			OrderItem curOrderItem=orderService.findByTid(orderItem.getItemid());
			//修改订单状态
			curOrderItem.setState(2);
			orderService.updateItem(curOrderItem);
			//页面跳转
			return "updateStateSuccess";
			
		}
	//跟据商店ID查询商店的所有商品并分页展示
	public String findBySid()
	{
		PageBean<Product> pageBean=storeService.findBySid(sid,page);
		store =storeService.findStoreBySid(sid);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findBySid";
	}
	public Integer getSid() {
		return sid;
	}
}
