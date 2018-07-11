package bupt.sse.shop.user.service;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.transaction.annotation.Transactional;

import bupt.sse.shop.user.dao.UserDao;
import bupt.sse.shop.user.vo.User;
import bupt.sse.shop.utils.MailUitls;
import bupt.sse.shop.utils.UUIDUtils;

@Transactional
public class UserService {
	//注入UserDao
	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}
	//实现用户注册
	public void save(User user) throws UnknownHostException {
		//数据存入数据库
		user.setState(0); //0代表未激活，1代表已激活
		String code =UUIDUtils.getUUID()+UUIDUtils.getUUID();
		user.setCode(code);
		userDao.save(user);
		String title="激活4396账号";
		InetAddress address = InetAddress.getLocalHost();
        String hostAddress = address.getHostAddress();  
		String content="<h1>"+user.getName()+"您好！4396购物商城官方激活邮件！点下面链接完成激活操作！</h1><h3>激活码:"+code+"</h3><h3><a href='http://"+hostAddress+":8080/4396Shop/user_active.action?code="+code+"'>点此链接完成验证</a></h3>";
		//发邮件
		try {
			MailUitls.sendMail(user.getEmail(), user.getName(),code,title,content);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public User findByCode(String code) {
		return userDao.findByCode(code);
	}
	//修改用户状态
	public void update(User existUser) {
		userDao.update(existUser);
	}
	public User login(User user) {
		return userDao.login(user);
	}
	public User findById(int uid) {
		return userDao.findById(uid);
	}
	public void saveIdentifyingCode(User user) throws UnknownHostException {
		userDao.saveIdentifyingCode(user);
		String title="修改4396账号密码";
		InetAddress address = InetAddress.getLocalHost();
        String hostAddress = address.getHostAddress();  
		String content="<h1>敬爱的"+user.getUsername()+"您好！4396购物商城官方修改密码邮件！点下面链接完成修改密码操作！</h1><h3>验证码:"+user.getCode()+"</h3><h3><a href='http://"+hostAddress+":8080/4396Shop/user_userChangePasswordPage.action?username="+user.getUsername()+"'>点此链接前往修改</a></h3>";
		//发邮件
		try {
			MailUitls.sendMail(user.getEmail(), user.getName(),user.getCode(),title,content);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//查询username和code匹配的用户
	public User findByUC(String username, String code) {
		return userDao.findByUC(username,code);
	}
}
