package bupt.sse.shop.category.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import bupt.sse.shop.category.vo.Category;


/*
 * category 持久层对象
 */
public class CategoryDao extends HibernateDaoSupport {

	public List<Category> findAll() {
		String hql = "from Category";
		List<Category> list= this.getHibernateTemplate().find(hql);
		System.out.println(list.toString());
		return list;
	}

}
