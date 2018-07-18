package bupt.sse.shop.user.adminaction;

import java.io.File;
import java.util.List;

import javax.enterprise.inject.New;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import bupt.sse.shop.categorysecond.vo.CategorySecond;
import bupt.sse.shop.user.service.UserService;
import bupt.sse.shop.user.vo.User;
import bupt.sse.shop.utils.PageBean;

public class UserAdminAction extends ActionSupport implements ModelDriven<User> {
	private UserService userService;
	private Integer page;
	private User user=new User();


	@Override
	public User getModel() {
		return user;
	}
	
	public void setPage(Integer page) {
		this.page = page;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	// 分页查询用户方法
	public String findAll() {
		PageBean<User> pageBean = userService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}

	// 移除用户
	/*public String delete() {
		// 先查询再删除
		user = userService.findByUid(user.getUid());

		// 删除数据库表项
		userService.delete(user);

		// 页面跳转
		return "deleteSuccess";
	}*/
	
	//禁用用户
	public String disable() {
		if(user.getUid()<1){
			this.addActionError("illegal id");
			return findAll();
		}
		else {
			user=userService.findByUid(user.getUid());
			user.setState(user.getState()+100);
			userService.update(user);
			return findAll();
		}
	}
	
	//启用用户
	public String enable() {
		if(user.getUid()<1){
			this.addActionError("illegal id");
			return findAll();
		}else{
			user=userService.findByUid(user.getUid());
			user.setState(user.getState()%100);
			userService.update(user);
			return findAll();
		}
	}
}
