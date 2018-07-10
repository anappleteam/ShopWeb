package bupt.sse.shop.store.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import bupt.sse.shop.order.vo.Order;
import bupt.sse.shop.store.dao.StoreDao;
import bupt.sse.shop.store.vo.Store;
import bupt.sse.shop.utils.PageBean;

@Transactional
public class StoreService {
	//注入StoreDao
	private StoreDao storeDao;
	
	public void setStoreDao(StoreDao storeDao) {
		this.storeDao = storeDao;
	}

	public void addStore(Store store) {
		storeDao.addStore(store);
	}

	public PageBean<Store> findByPage(Integer page) {
		PageBean<Store> pageBean=new PageBean<Store>();
		//设置当前页数
		pageBean.setPage(page);
		//设置每页显示的记录数
		int limit=10;
		pageBean.setLimit(limit);
		//设置记录数
		int totalCount=storeDao.findByCount();
		pageBean.setTotalCount(totalCount);
		//设置总页数
		int totalPage=0;
		if(totalCount%limit==0){
			totalPage=totalCount/limit;
		}else {
			totalPage=totalCount/limit+1;
		}
		pageBean.setTotalPage(totalPage);
		//设置每页显示的数据集合
		int begin=(page-1)*limit;
		List<Store> list=storeDao.findByPage(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
	public PageBean<Store> findByPageAudit(Integer page) {
		PageBean<Store> pageBean=new PageBean<Store>();
		//设置当前页数
		pageBean.setPage(page);
		//设置每页显示的记录数
		int limit=10;
		pageBean.setLimit(limit);
		//设置记录数
		int totalCount=storeDao.findByCountAudit();
		pageBean.setTotalCount(totalCount);
		//设置总页数
		int totalPage=0;
		if(totalCount%limit==0){
			totalPage=totalCount/limit;
		}else {
			totalPage=totalCount/limit+1;
		}
		pageBean.setTotalPage(totalPage);
		//设置每页显示的数据集合
		int begin=(page-1)*limit;
		List<Store> list=storeDao.findByPageAudit(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
	public Store findBySid(Integer sid) {
		return storeDao.findById(sid);
	}

	public void updateStore(Store store) {
		storeDao.update(store);
	}


}
