package bupt.sse.shop.aspect;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicdataSource extends AbstractRoutingDataSource{

	@Override
	protected Object determineCurrentLookupKey() {
		// TODO Auto-generated method stub
		return DataSourceSwitcher.getDataSource();
	}

}
