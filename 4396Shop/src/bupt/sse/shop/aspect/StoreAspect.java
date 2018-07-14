package bupt.sse.shop.aspect;

import org.apache.struts2.ServletActionContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import bupt.sse.shop.user.vo.User;

@Aspect
public class StoreAspect {
	@Before("MngMethodPointCut()||MngMethodPointCut()||updatecut()")
	public void before(JoinPoint joinPoint)throws UserNotLoginException
	{
		try{
		User user=(User)ServletActionContext.getRequest().getSession().getAttribute("existUser");
		if(user==null){
			throw new UserNotLoginException();
		};
		}
		catch(Exception e){
			ServletActionContext.getRequest().getSession().setAttribute("loginMessage","您尚未登陆！");
			throw new UserNotLoginException();
		}
	}
	
	@Pointcut("execution(* bupt.sse.shop.store.action.StoreAction.*Mng(..))")
	private void MngMethodPointCut(){	
	}
	
	@Pointcut("execution(* bupt.sse.shop.store.action.StoreAction.findOrderItem(..))")
	private void findOrderItemPointCut(){
	}
	
	@Pointcut("execution(* bupt.sse.shop.store.action.StoreAction.updateState(..))")
	private void updatecut(){
	}

}
