package bupt.sse.shop.user.action;


import java.io.IOException;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import bupt.sse.shop.store.service.StoreService;
import bupt.sse.shop.store.vo.Store;
import bupt.sse.shop.user.service.UserService;
import bupt.sse.shop.user.vo.User;

public class UserAction extends ActionSupport implements ModelDriven<User>{
	private User user=new User();
	//注入UserService
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	//注入StoreService
	private StoreService storeService;
	
	public void setStoreService(StoreService storeService) {
		this.storeService = storeService;
	}

	//接收验证码
	private String checkcode;
	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
	/**
	 * AJAX校验用户名
	 * @return
	 * @throws IOException 
	 */
	public String findByName() throws IOException{
		User existUser=userService.findByUsername(user.getUsername());
		//获得response对象，向页面输出
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		if(existUser!=null){
			response.getWriter().print(true);
		}else {
			response.getWriter().print(false);
		}
		return NONE;
	}
	@Override
	public User getModel() {
		return user;
	}
	public String registPage() {
		return "registPage";
	}
	/**
	 * 用户注册action
	 * @return
	 * @throws UnknownHostException 
	 */
	public String regist() throws UnknownHostException{
		//判断验证码
		//从session中获得验证码
		String checkcodeString=(String) ServletActionContext.getRequest().getSession().getAttribute("checkcode");
		if(!checkcode.equalsIgnoreCase(checkcodeString)){
			this.addActionError("验证码输入错误");
			return "registcheckcodeFail";
		}
		userService.save(user);
		this.addActionMessage("注册成功！请去邮箱激活！");
		return "msg";
	}
	/**
	 * 用户激活
	 */
	public String active() {
		//根据激活码查询用户
		User existUser=userService.findByCode(user.getCode());
		if(existUser==null){
			//激活码错误
			this.addActionMessage("激活失败，激活码错误！");
		}else {
			//激活成功
			//修改用户状态
			existUser.setState(1);
			existUser.setCode(null);
			userService.update(existUser);
			this.addActionMessage("激活成功,请去登录！");
		}
		return "msg";
	}
	/**
	 * 跳转到登录界面
	 */
	public String loginPage() {
		return "loginPage";
	}
	/**
	 * 登录
	 */
	public String login() {
		//判断验证码
		//从session中获得验证码
		String checkcodeString=(String) ServletActionContext.getRequest().getSession().getAttribute("checkcode");
		if(!checkcode.equalsIgnoreCase(checkcodeString)){
			this.addActionError("验证码输入错误");
			return "logincheckcodeFail";
		}
		User existUser=userService.login(user);
		if(existUser==null){
			this.addActionError("登录失败：用户名或密码有误或用户未激活！");
			return LOGIN;
		}
		else {
			HttpServletRequest request =ServletActionContext.getRequest();
			HttpServletResponse response =ServletActionContext.getResponse();
			LoginUtils.createCookie(request, response);
			ServletActionContext.getRequest().getSession().setAttribute("existUser", existUser);
			return "loginSuccess";
		}
	}
	/**
	 * 用户退出
	 */
	public String quit() {
		ServletActionContext.getRequest().getSession().invalidate();
		return "quit";
	}
	/**
	 * 入驻商户跳转
	 */
	public String merchantsettle(){
		User existUser=(User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		if(existUser==null){
			//Ajax 向前台返回信息
			return "requestFail";
		}
		else {
			User user=userService.findById(existUser.getUid());
			if(user.getState()==2){
				this.addActionMessage("您已提交入驻申请，请等待审核！");
				return "msg";
			}
			else if (user.getState()==3) {
				return "mystore";
			}
		}
		return "settle";
	}
	
	/**
	 * 提交入驻申请action
	 */
	private String storename=null;
	private String storeinfo=null;
	
	public void setStorename(String storename) {
		this.storename = storename;
	}
	public void setStoreinfo(String storeinfo) {
		this.storeinfo = storeinfo;
	}
	public String merchantRequest() {
		User existUser=(User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		if(existUser==null){
			//Ajax 向前台返回信息
			return "requestFail";
		}
		else {
			Store newStore=new Store();
			newStore.setSname(storename);
			newStore.setSdesc(storeinfo);
			newStore.setState(0);//未审核状态
			newStore.setOwner(existUser);
			storeService.addStore(newStore);
			existUser.setState(2);//状态2为商家审核
			userService.update(existUser);
			this.addActionMessage("您已提交入驻申请，请等待审核！");
		}
		return "msg";
		
	}
	/**
	 * AJAX刷新页面 existUser信息
	 * @return
	 * @throws IOException 
	 */
	public String fresh(){
		User existUser=(User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		if(existUser!=null){
			User newExistUser=userService.findById(existUser.getUid());
			ServletActionContext.getRequest().getSession().setAttribute("existUser", newExistUser);
		}
		return NONE;
	}
	
	/**
	 * 找回密码跳转action
	 */
	public String changePwdPage(){
		return "changePwdPage";
	}
	/**
	 * AJAX异步根据用户名查询邮箱并返回邮箱
	 * @throws IOException 
	 */
	public String findEmailByUname() throws IOException{
		User needChangeUser=userService.findByUsername(user.getUsername());
		if(needChangeUser!=null&&needChangeUser.getState()>=1){
			String registEmail=needChangeUser.getEmail();
			//获得response对象，向页面输出
			HttpServletResponse response=ServletActionContext.getResponse();
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print(registEmail);
		}
		else{
			this.addActionError("用户名不存在或尚未激活!");
		}
		return null;
	}
	/**
	 * 修改密码，向邮箱发送认证消息
	 * @throws UnknownHostException 
	 */
	public String changePwd() throws UnknownHostException{
		//生成验证码
		String str="";
		for (int i = 0;i<5;i++){
            str = str+ (char)(Math.random()*26+'A');
        }
		user.setCode(str);
		userService.saveIdentifyingCode(user);
		this.addActionMessage("我们已向您的邮箱"+user.getEmail()+"发送了一封激活邮件请查收!");
		return "msg";
	}
	/**
	 * 用户点击邮件链接的action
	 */
	public String userChangePasswordPage(){
		ActionContext.getContext().getValueStack().set("username", user.getUsername());
		return "userChangePasswordPage";
	}
	/**
	 * 验证用户修改密码信息
	 */
	public String userChangePwd(){
		//根据验证码查询用户
		User existUser=userService.findByUC(user.getUsername(),user.getCode());
		if(existUser==null){
			//验证码错误
			this.clearActionErrors();
			this.addActionError("验证失败，验证码错误！");
			return "changeFail";
		}else {
			//成功
			//修改用户状态
			existUser.setPassword(user.getPassword());;
			existUser.setCode(null);
			userService.update(existUser);
			this.clearMessages();
			this.addActionMessage("激活成功,请去登录！");
		}
		return "msg";
	}
}
