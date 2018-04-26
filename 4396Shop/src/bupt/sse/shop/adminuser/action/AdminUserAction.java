package bupt.sse.shop.adminuser.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import bupt.sse.shop.adminuser.service.AdminUserService;
import bupt.sse.shop.adminuser.vo.AdminUser;

/**
 * 后台用户的Action
 * 
 * @author 曹锡鹏
 *
 */
public class AdminUserAction extends ActionSupport implements ModelDriven<AdminUser> {
	private AdminUser adminUser = new AdminUser();

	@Override
	public AdminUser getModel() {

		return adminUser;
	}
	//注入service
	private AdminUserService adminUserService;
	
	public void setAdminUserService(AdminUserService adminUserService) {
		this.adminUserService = adminUserService;
	}
	//用户登录的action
	public String login() {
		AdminUser existAdminUser=adminUserService.login(adminUser);
		if(existAdminUser==null){
			//登录失败
			this.addActionError("用户名或密码错误");
			return "loginFail";
		}else {
			//登录成功
			ServletActionContext.getRequest().getSession().setAttribute("existAdminUser", existAdminUser);
			return "loginSuccess";
		}
	}
}
