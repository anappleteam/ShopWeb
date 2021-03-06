package bupt.sse.shop.store.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import bupt.sse.shop.product.vo.Product;
import bupt.sse.shop.store.vo.Store;
import bupt.sse.shop.utils.PageHibernateCallback;

public class StoreDao extends HibernateDaoSupport {

	public void addStore(Store store) {
		this.getHibernateTemplate().save(store);
	}

	//统计待审核店铺个数
	public int findByCount() {
		String hql="select count(*) from Store where state=1";
		List<Long> list=this.getHibernateTemplate().find(hql);
		if(list != null & list.size()>0){
			return list.get(0).intValue();
		}
		return 0;
	}

	//Dao层带分页查询的方法
	public List<Store> findByPage(int begin, int limit) {
		String hql="from Store where state=1 order by sid desc";
		List<Store> list=this.getHibernateTemplate().execute(new PageHibernateCallback<Store>(hql, null, begin, limit));
		if(list!=null&&list.size()>0){
			return list;
		}
		return null;
	}

	public Store findById(Integer sid) {
		String hql="from Store where sid=?";
		List<Store> result=this.getHibernateTemplate().find(hql,sid);
		if(result!=null&&result.size()>0){
			return result.get(0);
		}
		return null;
	}

	public void update(Store store) {
		this.getHibernateTemplate().update(store);
	}

	public int findByCountAudit() {
		String hql="select count(*) from Store where state=0";
		List<Long> list=this.getHibernateTemplate().find(hql);
		if(list != null & list.size()>0){
			return list.get(0).intValue();
		}
		return 0;
	}

	public List<Store> findByPageAudit(int begin, int limit) {
		String hql="from Store where state=0 order by sid desc";
		List<Store> list=this.getHibernateTemplate().execute(new PageHibernateCallback<Store>(hql, null, begin, limit));
		if(list!=null&&list.size()>0){
			return list;
		}
		return null;
	}

	public void delete(Store store) {
		String hqlString="delete from Store where sid=?";
		Query query=this.getSession().createQuery(hqlString);
		query.setParameter(0, store.getSid());
		query.executeUpdate();
	}

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

	public List<Store> findByUid(int uid) {
		String hql="from Store where uid=?";
		return getHibernateTemplate().find(hql,uid);
	}
}
