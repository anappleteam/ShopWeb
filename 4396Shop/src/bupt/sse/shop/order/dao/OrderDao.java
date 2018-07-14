package bupt.sse.shop.order.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import bupt.sse.shop.order.vo.Order;
import bupt.sse.shop.order.vo.OrderItem;
import bupt.sse.shop.product.vo.Product;
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
	
	public OrderItem findByTid(Integer itemid) {
		return this.getHibernateTemplate().get(OrderItem.class, itemid);
	}



	public void update(Order curOrder) {
		this.getHibernateTemplate().update(curOrder);
	}
	
	public void updateItem(OrderItem curItem) {
		this.getHibernateTemplate().update(curItem);
	}
	
	//Dao层统计订单个数
	public int findByCount() {
		String hql="select count(*) from Order";
		List<Long> list=this.getHibernateTemplate().find(hql);
		if(list != null & list.size()>0){
			return list.get(0).intValue();
		}
		return 0;
	}
	
	//Dao层带分页查询的方法
	public List<Order> findByPage(int begin, int limit) {
		String hql="from Order order by ordertime desc";
		List<Order> list=this.getHibernateTemplate().execute(new PageHibernateCallback<Order>(hql, null, begin, limit));
		if(list!=null&&list.size()>0){
			return list;
		}
		return null;
	}
	
	//根据订单id查询订单项
	public List<OrderItem> findOrderItem(Integer oid) {
		String hql="from OrderItem oi where oi.order.oid=?";
		List<OrderItem> list=this.getHibernateTemplate().find(hql,oid);
		if(list !=null&&list.size()>0){
			return list;
		}
		return null;
	}

	//根据店铺ID查询其所属的所有商品订单项
	public int findByCountSid(Integer sid) {
		String hql="select count(*) from OrderItem o where o.store.sid=? and o.state!=null";
		List<Long> list =this.getHibernateTemplate().find(hql,sid);
		if(list !=null&&list.size()>0){
			return list.get(0).intValue();
		}
		return 0;
	}

	public List<OrderItem> findByPageSid(Integer sid, int begin, int limit) {
		String hql = "select OI from OrderItem OI where OI.store.sid= ? and OI.state!=null";
		//分页的一种写法；
		List<OrderItem> list= this.getHibernateTemplate().find(hql,sid);
		if (list !=null && list.size()>0) {
			return list;
		}
		return null;
	}

}
