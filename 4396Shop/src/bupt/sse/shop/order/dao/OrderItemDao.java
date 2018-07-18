package bupt.sse.shop.order.dao;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import bupt.sse.shop.order.vo.OrderItem;

public class OrderItemDao extends HibernateDaoSupport {
	
	
	public OrderItem findByTid(Integer itemid) {
		return this.getHibernateTemplate().get(OrderItem.class, itemid);
	}


	public void update(OrderItem curItem) {
		this.getHibernateTemplate().update(curItem);
	}

}
