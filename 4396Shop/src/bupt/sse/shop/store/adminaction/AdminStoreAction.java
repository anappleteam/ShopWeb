package bupt.sse.shop.store.adminaction;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import bupt.sse.shop.store.service.StoreService;
import bupt.sse.shop.store.vo.Store;
import bupt.sse.shop.user.service.UserService;
import bupt.sse.shop.utils.PageBean;

public class AdminStoreAction extends ActionSupport{
	//注入sotreService
	private StoreService storeService;

	public void setStoreService(StoreService storeService) {
		this.storeService = storeService;
	}
	//注入userService
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	//接收page参数
	private Integer page;

	public void setPage(Integer page) {
		this.page = page;
	}
	
	//带分页查询的执行方法
	public String findAllAudit() {
		//分页查询
		PageBean<Store> pageBean=storeService.findByPageAudit(page);
		//通过值栈保存数据
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		//页面跳转
		return "findAllAudit";
	}
		
	//带分页查询的执行方法
	public String findAll() {
		//分页查询
		PageBean<Store> pageBean=storeService.findByPage(page);
		//通过值栈保存数据
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		//页面跳转
		return "findAll";
	}
	//接受店铺id
	private Integer sid;
	
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public String accept(){
		//先查询店铺
		Store store=storeService.findBySid(sid);
		store.setState(1);
		storeService.updateStore(store);
		store.getOwner().setState(3);
		userService.update(store.getOwner());
		return "acceptSuccess";
	}
	
	public String reject(){
		//先查询店铺
		Store store=storeService.findBySid(sid);
		storeService.deleteStore(store);
		store.getOwner().setState(1);
		userService.update(store.getOwner());
		return "rejectSuccess";
	}
	
	public String information(){
		Store store=storeService.findBySid(sid);
		//通过值栈保存数据
		ActionContext.getContext().getValueStack().set("store", store);
		//页面跳转
		return "infoPage";
	}
}
