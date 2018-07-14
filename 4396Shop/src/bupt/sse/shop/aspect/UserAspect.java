package bupt.sse.shop.aspect;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import com.opensymphony.xwork2.ActionContext;

import bupt.sse.shop.user.vo.User;



/**
 * 自定义切面bean
 * @author cxp
 *
 */
@Aspect
public class UserAspect {
	// 日志记录器
	private Logger logger = Logger.getLogger(this.getClass());
	/**
	 * 前置通知，拦截orderAction所有方法，检查用户是否登陆
	 */
	@Before("orderActionPointCut()")
	public void checkLoginBefore(JoinPoint joinPoint) throws UserNotLoginException {
		try {
			User existUser = (User) ServletActionContext.getRequest().getSession()
					.getAttribute("existUser");
			if(existUser == null){
				throw new UserNotLoginException();
			}
		} catch (Exception e) {
			ServletActionContext.getRequest().getSession().setAttribute("loginMessage","您尚未登陆！");
			throw new UserNotLoginException();
		}
			
		
	}
	
	/**
	 *  环绕通知，在目标方法前后执行，拦截目标方法执行，返回目标方法返回值，抛出目标方法异常
	 *  性能监视
	 * @param proceedingJoinPoint
	 * @return
	 * @throws Throwable
	 */
	@Around("allMethodPointCut()")
	public Object performanceAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		long start = System.currentTimeMillis();
		Object result = proceedingJoinPoint.proceed();
		long end = System.currentTimeMillis();
		logger.info("记录目标方法运行时间:"+proceedingJoinPoint.getTarget().getClass().getName()+":"+proceedingJoinPoint.getSignature().getName() + (double)(end - start)/1000+"秒");
		return result;
	}
	@Pointcut("execution(* bupt.sse.shop..*(..))")
	private void allMethodPointCut(){
	}
	
	@Pointcut("execution(* bupt.sse.shop.order.action.OrderAction.*(..))")
	private void orderActionPointCut(){
	}
}
