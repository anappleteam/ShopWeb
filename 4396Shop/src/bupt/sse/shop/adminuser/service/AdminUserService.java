package bupt.sse.shop.adminuser.service;

import org.springframework.transaction.annotation.Transactional;

import bupt.sse.shop.adminuser.dao.AdminUserDao;
import bupt.sse.shop.adminuser.vo.AdminUser;

/**
 * 后台登录的service
 * @author 曹锡鹏
 *
 */
@Transactional
public class AdminUserService{
//注入Dao
	private AdminUserDao adminUserDao;
	public void setAdminUserDao(AdminUserDao adminUserDao) {
		this.adminUserDao = adminUserDao;
	}
	
	public AdminUser login(AdminUser adminUser) {
		return adminUserDao.login(adminUser);
	} 
}
