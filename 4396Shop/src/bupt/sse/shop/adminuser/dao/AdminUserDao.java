package bupt.sse.shop.adminuser.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import bupt.sse.shop.adminuser.vo.AdminUser;

public class AdminUserDao extends HibernateDaoSupport{

	
	public AdminUser login(AdminUser adminUser) {
		String hql="from AdminUser where username=? and password=?";
		List<AdminUser> adminUsers=this.getHibernateTemplate().find(hql,adminUser.getUsername(),adminUser.getPassword());
		if(adminUsers!=null&&adminUsers.size()>0){
			return adminUsers.get(0);
		}
		return null;
	}

}
