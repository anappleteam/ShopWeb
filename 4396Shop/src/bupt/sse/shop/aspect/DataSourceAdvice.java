package bupt.sse.shop.aspect;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.springframework.aop.ThrowsAdvice;

public class DataSourceAdvice implements ThrowsAdvice{

	private Logger logger = Logger.getLogger(this.getClass());

	public void afterThrowing(Method method, Object[] args, Object target, Exception ex) throws Throwable {
		DataSourceSwitcher.setSlave();
		logger.info("出现异常,切换到: slave");
	}

}
