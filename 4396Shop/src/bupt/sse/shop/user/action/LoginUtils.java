package bupt.sse.shop.user.action;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginUtils {
	public static void createCookie(HttpServletRequest request,
			HttpServletResponse response) {
		String username=null;
		String password=request.getParameter("password");//获取用户的密码
		try {
			username = URLEncoder.encode(request.getParameter("username"), "UTF-8");//获取用户的登录名
//			因为用可能用户的登录名是使用的中文，所以这里将用户的中文名进行编码，如果你的用户名不是中文
			
		} catch (UnsupportedEncodingException e) {
		
			e.printStackTrace();
		}
		
		String remember = request.getParameter("remember");//获取用户是否有勾选记住密码的值
		Cookie nameCookie = new Cookie("username",username);//新建用户名cookie对象
		Cookie passwordCookie = new Cookie("password",password);//新建密码cookie对象
		if(remember!=null&&remember.equals("remember")){//如果用户勾选了记住密码的功能
			//则执行if内部的条件语句
		
			nameCookie.setMaxAge(7*24*60*60);//设置cookie的有效时长 单位是秒
			passwordCookie.setMaxAge(7*24*60*60);//
			nameCookie.setPath(request.getContextPath()+"/");//设置c有效范围cookie的，这里是整个上下文环境
			passwordCookie.setPath(request.getContextPath()+"/");
			response.addCookie(nameCookie);//写入cookie
			response.addCookie(passwordCookie);
		}else{
			
			nameCookie.setMaxAge(0);
			/**
			 * 如果用户没有勾选记住密码的功能，那么在页面跳转的时候
			 * 执行改方法就会把cookie的有效时长清零。这样记住密码功能就不能使用了
			 */
			passwordCookie.setMaxAge(0);
			
		}
		
	}
}
