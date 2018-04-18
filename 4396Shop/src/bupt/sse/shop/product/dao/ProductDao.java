package bupt.sse.shop.product.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import bupt.sse.shop.product.vo.Product;
import bupt.sse.shop.utils.PageHibernateCallback;

public class ProductDao extends HibernateDaoSupport {
	//查询热门商品
	public List<Product> findHot() {
		//使用离线条件查询。
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		criteria.add(Restrictions.eq("is_hot",1));
		criteria.addOrder(Order.desc("pdate"));
		List<Product> list=this.getHibernateTemplate().findByCriteria(criteria,0,10);
		return list;
	}
	//查询最新商品
	public List<Product> findNew() {
		//使用离线条件查询
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		criteria.addOrder(Order.desc("pdate"));
		List<Product> list = this.getHibernateTemplate().findByCriteria(criteria,0,10);
		return list;
 	}
	//根据商品ID查询商品
	public Product findByPid(Integer pid) {
		return this.getHibernateTemplate().get(Product.class,pid);
		
	}
	//根据商分类ID查询商品的个数
	public int findCountCid(Integer cid) {
		String hql= "select count(*) from Product p where p.categorySecond.category.cid=?";
		List<Long> list = this.getHibernateTemplate().find(hql,cid);
		if (list != null&& list.size()>0) {
			return list.get(0).intValue();
		}
		return 0;
	}
	
	//根据分类ID查询商品的集合
	public List<Product> findByPageCid(Integer cid, int begin, int limit) {
		String hql = "select p from Product p join p.categorySecond cs join cs.category c where c.cid= ?";
		//分页的一种写法；
		List<Product> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, new Object[]{cid}, begin, limit));
		if (list !=null && list.size()>0) {
			return list;
		}
		return null;
	}

}
