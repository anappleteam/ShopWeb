package bupt.sse.shop.store.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import bupt.sse.shop.product.vo.Product;
import bupt.sse.shop.store.vo.Store;
import bupt.sse.shop.utils.PageHibernateCallback;

public class StoreDao extends HibernateDaoSupport{

	public int findCountSid(Integer sid) {
		String hql= "select count(*) from Product p where p.store.sid=?";
		List<Long> list = this.getHibernateTemplate().find(hql,sid);
		if (list != null&& list.size()>0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	public List<Product> findByPageSid(Integer sid, int begin, int limit) {
		String hql = "select p from Product p where p.store.sid= ?";
		//分页的一种写法；
		List<Product> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, new Object[]{sid}, begin, limit));
		if (list !=null && list.size()>0) {
			return list;
		}
		return null;
	}

	public Store findStoreBySid(Integer sid) {
		Store store=this.getHibernateTemplate().get(Store.class,sid);
		return store;
	}
}
