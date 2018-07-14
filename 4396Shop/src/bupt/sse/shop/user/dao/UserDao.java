package bupt.sse.shop.user.dao;



import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import bupt.sse.shop.user.vo.User;
import bupt.sse.shop.utils.PageHibernateCallback;

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
	public User findByUid(Integer uid) {
		return this.getHibernateTemplate().get(User.class,uid);
	}
	
	public void update(User existUser) {
		this.getHibernateTemplate().update(existUser);		
	}
    //用户登录
	public User login(User user) {
		String hql="from User where username=? and password=? and (state=1 or state=2 or state=3)";
		List<User> users=this.getHibernateTemplate().find(hql,user.getUsername(),user.getPassword());
		if(users!=null&&users.size()>0){
			//return getHibernateTemplate().get(User.class, users.get(0).getUid());
			return users.get(0);
		}
		return null;
	}
	//获取用户总数
	public int findCount() {
		String hql="select count(*) from User";
		List<Long> list=this.getHibernateTemplate().find(hql);
		if (list!=null && list.size()>0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	public List<User> findByPage(int begin, int limit) {
		String hql="from User order by uid";
		List<User> list =this.getHibernateTemplate().execute(
				new PageHibernateCallback<User>(hql,null, begin, limit));
		if (list !=null && list.size()>0) {
			return list;
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
	public User findByUid(int uid) {
		return getHibernateTemplate().get(User.class, uid);
	}

	public void delete(User user) {
		getHibernateTemplate().delete(user);
	}
}
