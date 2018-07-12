package bupt.sse.shop.store.action;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;

import javax.enterprise.inject.New;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import bupt.sse.shop.product.vo.Product;
import bupt.sse.shop.store.service.StoreService;
import bupt.sse.shop.store.vo.Store;
import bupt.sse.shop.user.vo.User;
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
	
	public String productMng(){
		return null;
		
	}
	
	public String storeMng() {
		User user=(User)ServletActionContext.getRequest().getSession().getAttribute("existUser");
		if(user==null)return "storeError";
		List<Store> stores=storeService.findByUid(user.getUid());
		if(sid==null){	
			/*if(!stores.isEmpty()){
				ServletActionContext.getRequest().getSession().setAttribute("managedStore", new ArrayList<Store>(stores).get(0));
				ArrayList<Product> list=new ArrayList<Product>(new ArrayList<Store>(stores).get(0).getProducts());
				return "storeMng";
			}*/
			ServletActionContext.getRequest().getSession().setAttribute("managedStore", stores.get(0));
			return "storeMng";
		}else {
			for(Store store:stores){
				if(store.getSid().intValue()==sid.intValue()){
					ServletActionContext.getRequest().getSession().setAttribute("managedStore", store);
					return "storeMng";
				}
			}
		}

		return "storeError";
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
