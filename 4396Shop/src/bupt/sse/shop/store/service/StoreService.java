package bupt.sse.shop.store.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import bupt.sse.shop.order.vo.Order;
import bupt.sse.shop.product.vo.Product;
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
	//更新店铺信息
	public void updateStore(Store store) {
		storeDao.update(store);
	}
	//删除店铺
	public void deleteStore(Store store) {
		storeDao.delete(store);
	}
	//根据sid查找商品集合，并且分页显示;
	public PageBean<Product> findBySid(Integer sid, Integer page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		//设置当前页数
		pageBean.setPage(page);
		//设置每页显示记录
		int limit= 8;
		pageBean.setLimit(limit);
		//设置总记录数
		int totalCount =0;
		totalCount=storeDao.findCountSid(sid);
		pageBean.setTotalCount(totalCount);
		//设置总页数
		int totalPage=0;
		//totalPage=(int) Math.ceil(totalCount/limit);
		if(totalCount % limit ==0){
			totalPage = totalCount/limit;
		}else {
			totalPage= totalCount/limit +1;
		}
		pageBean.setTotalPage(totalPage);
		//每页显示的数据集合
		//从那开始
		int begin = (page-1)*limit;
		List<Product> list = storeDao.findByPageSid(sid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	//查询店铺信息
	public Store findStoreBySid(Integer sid) {
		return storeDao.findStoreBySid(sid);
	}
}
