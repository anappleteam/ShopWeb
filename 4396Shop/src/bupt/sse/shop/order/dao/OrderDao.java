package bupt.sse.shop.order.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import bupt.sse.shop.order.vo.Order;
import bupt.sse.shop.utils.PageHibernateCallback;

public class OrderDao extends HibernateDaoSupport{
	public void save(Order order){
		this.getHibernateTemplate().save(order);
	}

	public Integer findByCountUid(int uid) {
		String hql = "select count(*) from Order o where o.user.uid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql,uid);
		if(list != null & list.size()>0){
			return list.get(0).intValue();
		}
		return null;
	}

	public List<Order> findByPageUid(int uid, Integer begin, int i) {
		String hql = "from Order o where o.user.uid = ? order by ordertime desc";
		List<Order> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Order>(hql,new Object[]{uid}, begin, i));
		return list;
	}

	public Order findByOid(Integer oid) {
		return this.getHibernateTemplate().get(Order.class, oid);
	}

	public void update(Order curOrder) {
		this.getHibernateTemplate().update(curOrder);
	}

}
