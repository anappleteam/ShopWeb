package bupt.sse.shop.aspect;

import org.springframework.util.Assert;

@SuppressWarnings({"rawtypes","unchecked"})
public class DataSourceSwitcher {
	private static final ThreadLocal contextHolder = new ThreadLocal();
	
	public static void setDataSource(String dataSource){
		Assert.notNull(dataSource,"datasource can not be null");
		contextHolder.set(dataSource);
	}
	public static void setMaster(){
		setDataSource("master");
	}
	public static void setSlave(){
		setDataSource("slave");
	}
	public static String getDataSource(){
		return (String) contextHolder.get();
	}
}
