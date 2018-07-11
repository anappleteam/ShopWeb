package bupt.sse.shop.store.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import bupt.sse.shop.product.vo.Product;
import bupt.sse.shop.store.service.StoreService;
import bupt.sse.shop.store.vo.Store;
import bupt.sse.shop.utils.PageBean;

public class StoreAction extends ActionSupport{
	private StoreService storeService;
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
