package bupt.sse.shop.order.dao;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import bupt.sse.shop.order.vo.Order;

public class OrderDao extends HibernateDaoSupport{
	public void save(Order order){
		this.getHibernateTemplate().save(order);
	}

}
