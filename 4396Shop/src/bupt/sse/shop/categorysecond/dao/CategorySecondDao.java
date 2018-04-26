package bupt.sse.shop.categorysecond.dao;

import java.util.List;

import org.springframework.dao.support.DaoSupport;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import bupt.sse.shop.categorysecond.vo.CategorySecond;
import bupt.sse.shop.utils.PageHibernateCallback;


public class CategorySecondDao extends HibernateDaoSupport{
	
	// DAO中的统计二级分类个数的方法
		public int findCount() {
			String hql = "select count(*) from CategorySecond";
			List<Long> list = this.getHibernateTemplate().find(hql);
			if (list != null && list.size() > 0) {
				return list.get(0).intValue();
			}
			return 0;
		}

		// DAO中分页查询的方法
		public List<CategorySecond> findByPage(int begin, int limit) {
			String hql = "from CategorySecond order by csid desc";
			List<CategorySecond> list = this.getHibernateTemplate().execute(
					new PageHibernateCallback<CategorySecond>(hql, null, begin,
							limit));
			return list;
		}		
		public CategorySecond findByCsid(Integer csid){
			return this.getHibernateTemplate().get(CategorySecond.class, csid);
		}
		public void delete(CategorySecond categorySecond){
			this.getHibernateTemplate().delete(categorySecond);
		}
		public void save(CategorySecond categorySecond){
			this.getHibernateTemplate().save(categorySecond);
		}
		public void update(CategorySecond categorySecond){
			this.getHibernateTemplate().update(categorySecond);
		}
		//持久层查询所有二级分类方法
		public List<CategorySecond> findAll(){
			String hql="from CategorySecond";
			return this.getHibernateTemplate().find(hql);
		}
		
}
