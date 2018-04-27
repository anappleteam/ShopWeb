package bupt.sse.shop.interceptor;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

import bupt.sse.shop.adminuser.vo.AdminUser;

/**
 * 后台权限拦截器
 * 
 * @author mly
 *
 */

public class PrivilegeInterceptor extends MethodFilterInterceptor{

	@Override
	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		
		AdminUser existAdminUser=(AdminUser) ServletActionContext.getRequest().getSession().getAttribute("existAdminUser");
		
		if(existAdminUser==null){
			//没有登录
			ActionSupport actionSupport=(ActionSupport) actionInvocation.getAction();
			
			actionSupport.addActionError("尚未登录管理员");
			
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().flush();
			response.getWriter().write("<script>window.open('/4396Shop/admin/index.jsp','_parent');</script>");
			return "loginFail";
		}else {
			//已经登录
			return actionInvocation.invoke();
		}
	}

}
