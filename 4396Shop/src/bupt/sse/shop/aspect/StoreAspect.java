package bupt.sse.shop.aspect;

import org.apache.struts2.ServletActionContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import bupt.sse.shop.user.vo.User;

@Aspect
public class StoreAspect {
	@Before("MngMethodPointCut()")
	public void before(JoinPoint joinPoint)
	{
		User user=(User)ServletActionContext.getRequest().getSession().getAttribute("existUser");
		if(user==null);
	}
	
	@Pointcut("execution(*bupt.sse.shop.store.action.StoreAction.*Mng(..))")
	private void MngMethodPointCut(){	
	}

}
