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
	//Dao层保存一级分类的方法
	public void save(Category category) {
		this.getHibernateTemplate().save(category);
	}
	public Category findByCid(Integer cid) {
		
		return this.getHibernateTemplate().get(Category.class,cid);
	}
	//Dao删除一级分类的方法
	public void delete(Category category) {
		this.getHibernateTemplate().delete(category);
	}
	public void update(Category category) {
		this.getHibernateTemplate().update(category);
	}

}
