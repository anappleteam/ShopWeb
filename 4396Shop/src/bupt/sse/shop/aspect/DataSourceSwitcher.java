package bupt.sse.shop.aspect;

import org.springframework.util.Assert;

@SuppressWarnings({"rawtypes","unchecked"})
public class DataSourceSwitcher {
	private static final ThreadLocal contextHolder = new ThreadLocal();
	public static void setMaster(){
		contextHolder.remove();
	}
	public static void setSlave(){
		Assert.notNull("slave","datasource can not be null");
		contextHolder.set("slave");
	}
	public static String getDataSource(){
		Object source = contextHolder.get();
		return (String) source;
	}
	
}
