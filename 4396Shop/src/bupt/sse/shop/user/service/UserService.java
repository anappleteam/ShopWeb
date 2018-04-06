package bupt.sse.shop.user.service;
import org.springframework.transaction.annotation.Transactional;

import bupt.sse.shop.user.dao.UserDao;
import bupt.sse.shop.user.vo.User;
import bupt.sse.shop.utils.MailUitls;
import bupt.sse.shop.utils.UUIDUtils;

@Transactional
public class UserService {
	//ע��UserDao
	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}
	//ʵ���û�ע��
	public void save(User user) {
		//���ݴ������ݿ�
		user.setState(0); //0����δ���1�����Ѽ���
		String code =UUIDUtils.getUUID()+UUIDUtils.getUUID();
		user.setCode(code);
		userDao.save(user);
		//���ʼ�
		try {
			MailUitls.sendMail(user.getEmail(), user.getName(),code);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public User findByCode(String code) {
		return userDao.findByCode(code);
	}
	//�޸��û�״̬
	public void update(User existUser) {
		userDao.update(existUser);
	}
}
