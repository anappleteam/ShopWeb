package bupt.sse.shop.aspect;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;

public class DataSourceAdvice implements MethodBeforeAdvice,ThrowsAdvice,AfterReturningAdvice{

	private Logger logger = Logger.getLogger(this.getClass());

	public static Set<String> masterMethod = new HashSet<String>();
	public static Set<String> slaveMethod = new HashSet<String>();
	static{
		masterMethod.add("save");
		masterMethod.add("update");
		masterMethod.add("updateItem");
		masterMethod.add("delete");
		masterMethod.add("saveIdentifyingCode");
		slaveMethod.add("login");
	}
	
	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable{

		String methodname = method.getName();
		logger.info("切入点："+target.getClass().getName()+"的"+ methodname + "方法");
		if(masterMethod.contains(methodname)){
			logger.info("切换到主数据库");
			DataSourceSwitcher.setMaster();
//		}else if(slaveMethod.contains(methodname)){
//			logger.info("切换到从数据库");
//			DataSourceSwitcher.setSlave();
		}else{
			logger.info("切换到从数据库");
			DataSourceSwitcher.setSlave();
		}
		
	}
	
	public void afterThrowing(Method method, Object[] args, Object target, Exception ex) throws Throwable {
//		DataSourceSwitcher.setSlave();
//		logger.info("出现异常,切换到: slave");
	}

	@Override
	public void afterReturning(Object arg0, Method arg1, Object[] arg2, Object arg3) throws Throwable {
	}

}
