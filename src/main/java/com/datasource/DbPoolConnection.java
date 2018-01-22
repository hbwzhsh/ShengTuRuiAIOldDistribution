package com.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.utility.PropertiesUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.Properties;

public class DbPoolConnection {
	
	private static final Logger logger = LogManager.getLogger(DbPoolConnection.class);
	
	private static DbPoolConnection databasePool = null;
	private static DruidDataSource dds = null;
	static {
		Properties properties = PropertiesUtil.loadPropertyFile("db_server.properties");
		try {
			dds = (DruidDataSource) DruidDataSourceFactory.createDataSource( properties );
			logger.debug("connect to database....");
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(e.getMessage());
		}
	}
	
	private DbPoolConnection() {
		
	}
	
	public static synchronized DbPoolConnection getInstance() {
		if (null == databasePool) {
			databasePool = new DbPoolConnection();
		}
		return databasePool;
	}
	
	public DruidPooledConnection getConnection() throws SQLException {
		
		return dds.getConnection();
	}
}