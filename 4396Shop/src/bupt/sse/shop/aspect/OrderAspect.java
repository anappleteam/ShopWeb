package bupt.sse.shop.aspect;

import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;


@Aspect
public class OrderAspect {
	
	private Logger logger = Logger.getLogger(this.getClass());

	@After("allMethodPointCut()")
	public void around(JoinPoint JoinPoint) throws Throwable{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		logger.info("记录用户订单操作:"+JoinPoint.getTarget().getClass().getName()+":"+JoinPoint.getSignature().getName() + df.format(System.currentTimeMillis()));
	}
	
	@Before("MoneyPointCut()")
	public void before(JoinPoint JoinPoint) throws Throwable{ 
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		logger.info("记录用户金钱相关操作:"+JoinPoint.getTarget().getClass().getName()+":"+JoinPoint.getSignature().getName() + df.format(System.currentTimeMillis()));
	}
	
	@Pointcut("execution(* bupt.sse.shop.order.service.OrderService.*(..))")
	private void allMethodPointCut(){
	}
	
	@Pointcut("execution(* bupt.sse.shop.order.service.OrderService.Order*(..))")
	private void MoneyPointCut(){
	}
}
