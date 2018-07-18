package bupt.sse.shop.conment.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import bupt.sse.shop.conment.vo.Conment;

public class ConmentDao extends HibernateDaoSupport{

	public List<Conment> findByPid(Integer pid) {
		String hql="from Conment c where c.product.pid=?";
		List<Conment> conments= this.getHibernateTemplate().find(hql,pid);
		if (conments !=null && conments.size()>0) {
			return conments;
		}
		return null;
	}
	
	public void save(Conment conment){
		this.getHibernateTemplate().save(conment);
	}
 
}
