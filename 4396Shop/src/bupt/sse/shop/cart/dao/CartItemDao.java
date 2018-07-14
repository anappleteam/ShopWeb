package bupt.sse.shop.cart.dao;

import java.util.List;

import org.hibernate.sql.Update;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import bupt.sse.shop.cart.vo.CartItem;
import bupt.sse.shop.order.vo.Order;
import bupt.sse.shop.product.vo.Product;
import bupt.sse.shop.utils.PageHibernateCallback;

public class CartItemDao extends HibernateDaoSupport {

	public void save(CartItem cartItem) {
		this.getHibernateTemplate().save(cartItem);
	}

	public void update(CartItem cartItem) {
		this.getHibernateTemplate().update(cartItem);
	}

	public void delete(CartItem cartItem) {
		this.getHibernateTemplate().delete(cartItem);
	}
	
	
	public List<CartItem> findByUid(Integer uid){
		String hql="from CartItem c where c.user.uid=? order by addtime desc";
		List<CartItem> list=this.getHibernateTemplate().find(hql,uid);
		if (list!=null&&list.size()>0) {
			return list;		
		}
		return null;
	}

	public Integer findByCountUid(int uid) {
		String hql = "select count(*) from CartItem c where c.user.uid = ?";
		@SuppressWarnings("unchecked")
		List<Long> list = this.getHibernateTemplate().find(hql, uid);
		if (list != null & list.size() > 0) {
			return list.get(0).intValue();
		}
		return null;
	}

	public CartItem findByUid_Pid(Integer uid, Integer pid) {
		String hql ="from CartItem c where 1=1 and c.user.uid=? and c.product.pid=?";
		List<CartItem> list=this.getHibernateTemplate().find(hql,uid,pid);
		if (list!=null&&list.size()>0) {
			return list.get(0);		
		}
		return null;
	}

	public CartItem findByCitemid(Integer citemid) {
		String hql = "from CartItem c where c.citemid=?";
		List<CartItem> list=this.getHibernateTemplate().find(hql,citemid);
		if (list!=null&&list.size()>0) {
			return list.get(0);		
		}
		return null;
	}

}
