package bupt.sse.shop.store.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import bupt.sse.shop.store.service.StoreService;
import bupt.sse.shop.store.vo.Store;

public class StoreAction extends ActionSupport implements ModelDriven<Store> {
	private Store store=new Store();
	private StoreService storeService;
	
	
	public void setStoreService(StoreService storeService) {
		this.storeService = storeService;
	}

	@Override
	public Store getModel() {
		
		return store;
	}

}
