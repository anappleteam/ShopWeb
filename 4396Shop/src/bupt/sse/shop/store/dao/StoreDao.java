package bupt.sse.shop.store.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

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

}
