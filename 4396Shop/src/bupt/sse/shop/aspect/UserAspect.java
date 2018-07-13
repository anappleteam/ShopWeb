package bupt.sse.shop.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;


/**
 * 自定义切面bean
 * @author cxp
 *
 */
@Aspect
public class UserAspect {
	// 日志记录器
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Around("allMethodPointCut()")
	// 环绕通知，在目标方法前后执行，拦截目标方法执行，返回目标方法返回值，抛出目标方法异常
	public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		long start = System.currentTimeMillis();
		Object result = proceedingJoinPoint.proceed();
		long end = System.currentTimeMillis();
		logger.info("记录目标方法运行时间:"+proceedingJoinPoint.getTarget().getClass().getName()+":"+proceedingJoinPoint.getSignature().getName() + (double)(end - start)/1000+"秒");
		return result;
	}
	@Pointcut("execution(* bupt.sse.shop.user.service.UserService.*(..))")
	private void allMethodPointCut(){
	}
	
	
}
