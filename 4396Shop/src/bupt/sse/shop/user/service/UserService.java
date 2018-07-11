package bupt.sse.shop.user.service;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import bupt.sse.shop.user.dao.UserDao;
import bupt.sse.shop.user.vo.User;
import bupt.sse.shop.utils.MailUitls;
import bupt.sse.shop.utils.PageBean;
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
	public void save(User user) {
		//数据存入数据库
		user.setState(0); //0代表未激活，1代表已激活
		String code =UUIDUtils.getUUID()+UUIDUtils.getUUID();
		user.setCode(code);
		userDao.save(user);
		//发邮件
		try {
			MailUitls.sendMail(user.getEmail(), user.getName(),code);
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
	//业务层按页查询
	public PageBean<User> findByPage(Integer page) {
		PageBean<User> pageBean=new PageBean<User>();
		//设置当前页数
		pageBean.setPage(page);
		//设置每页显示的记录数
		int limit=6;
		pageBean.setLimit(limit);
		//设置总记录数
		int totalCount=userDao.findCount();
		pageBean.setTotalCount(totalCount);
		//设置总页数
		int totalPage=0;
		if (totalCount%limit==0) {
			totalPage=totalCount/limit;
		}else {
			totalPage=totalCount/limit+1;
		}
		pageBean.setTotalPage(totalPage);
		int begin=(page-1)*limit;
		List<User> pList=userDao.findByPage(begin,limit);
		pageBean.setList(pList);
		return pageBean;
	}
	public User findByUid(int uid) {
		return userDao.findByUid(uid);
	}
	public void delete(User user) {
		userDao.delete(user);
	}
}
