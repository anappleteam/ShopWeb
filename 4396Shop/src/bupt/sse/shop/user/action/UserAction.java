package bupt.sse.shop.user.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import bupt.sse.shop.user.service.UserService;
import bupt.sse.shop.user.vo.User;

public class UserAction extends ActionSupport implements ModelDriven<User>{
	private User user=new User();
	//ע��UserService
	private UserService userService;
	//������֤��
	private String checkcode;
	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
	/**
	 * AJAXУ���û���
	 * @return
	 * @throws IOException 
	 */
	public String findByName() throws IOException{
		User existUser=userService.findByUsername(user.getUsername());
		//���response������ҳ�����
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
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public String regist(){
		//�ж���֤��
		//��session�л����֤��
		String checkcodeString=(String) ServletActionContext.getRequest().getSession().getAttribute("checkcode");
		if(!checkcode.equalsIgnoreCase(checkcodeString)){
			this.addActionError("��֤���������");
			return "registcheckcodeFail";
		}
		userService.save(user);
		this.addActionMessage("ע��ɹ�����ȥ���伤�");
		return "msg";
	}
	/**
	 * �û�����
	 */
	public String active() {
		//���ݼ������ѯ�û�
		User existUser=userService.findByCode(user.getCode());
		if(existUser==null){
			//���������
			this.addActionMessage("����ʧ�ܣ����������");
		}else {
			//����ɹ�
			//�޸��û�״̬
			existUser.setState(1);
			existUser.setCode(null);
			userService.update(existUser);
			this.addActionMessage("����ɹ�,��ȥ��¼��");
		}
		return "msg";
	}
	/**
	 * ��ת����¼����
	 */
	public String loginPage() {
		return "loginPage";
	}
	/**
	 * ��¼
	 */
	public String login() {
		//�ж���֤��
		//��session�л����֤��
		String checkcodeString=(String) ServletActionContext.getRequest().getSession().getAttribute("checkcode");
		if(!checkcode.equalsIgnoreCase(checkcodeString)){
			this.addActionError("��֤���������");
			return "logincheckcodeFail";
		}
		User existUser=userService.login(user);
		if(existUser==null){
			this.addActionError("��¼ʧ�ܣ��û���������������û�δ���");
			return LOGIN;
		}
		else {
			ServletActionContext.getRequest().getSession().setAttribute("existUser", existUser);
			return "loginSuccess";
		}
	}
	/**
	 * �û��˳�
	 */
	public String quit() {
		ServletActionContext.getRequest().getSession().invalidate();
		return "quit";
	}
}
