package bupt.sse.shop.user.dao;



import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


import bupt.sse.shop.user.vo.User;

public class UserDao extends HibernateDaoSupport {
	public User findByUsername(String username) {
		String hql="from User Where username=?";
		List<User> users=this.getHibernateTemplate().find(hql,username);
		if(users!=null&&users.size()>0){
			return users.get(0);
		}
		return null;
	}

	public void save(User user) {
		this.getHibernateTemplate().save(user);
	}

	public User findByCode(String code) {
		String hql="from User Where code=?";
		List<User> users=this.getHibernateTemplate().find(hql,code);
		if(users!=null&&users.size()>0){
			return users.get(0);
		}
		return null;
	}
	
	public void update(User existUser) {
		this.getHibernateTemplate().update(existUser);		
	}
    //用户登录
	public User login(User user) {
		String hql="from User where username=? and password=? and (state=1 or state=2 or state=3)";
		List<User> users=this.getHibernateTemplate().find(hql,user.getUsername(),user.getPassword());
		if(users!=null&&users.size()>0){
			return users.get(0);
		}
		return null;
	}

	public User findById(int uid) {
		String hql="from User where uid=?";
		List<User> users=this.getHibernateTemplate().find(hql,uid);
		if(users!=null&&users.size()>0){
			return users.get(0);
		}
		return null;
	}

	public void saveIdentifyingCode(User user) {
		String hql="update User set code=? where username=?";
		Query query=this.getSession().createQuery(hql);
		query.setParameter(0, user.getCode());
		query.setParameter(1, user.getUsername());
		query.executeUpdate();
	}

	public User findByUC(String username, String code) {
		String hql="from User where username=? and code=?";
		List<User> users=this.getHibernateTemplate().find(hql,username,code);
		if(users!=null&&users.size()>0){
			return users.get(0);
		}
		return null;
	}
}
