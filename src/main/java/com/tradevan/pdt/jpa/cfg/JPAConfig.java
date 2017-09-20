package com.tradevan.pdt.jpa.cfg;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.tradevan.pdt.jpa.handler.AuthHandler;

@Configuration
@EnableTransactionManagement
//@PropertySource(value = { "classpath:conf/persistence.properties" })
public class JPAConfig {

	/*@Autowired
	private  Environment env;*/
	
	@Autowired
	private Properties persistenceProps;

	/**
	 * Initialize dataSource
	 * @return DataSource
	 */
	/*@Bean("dataSource")
	public  DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(persistenceProps.getProperty("datasource.driver"));
		dataSource.setUrl(persistenceProps.getProperty("datasource.url"));
		dataSource.setUsername(persistenceProps.getProperty("datasource.username"));
		//TODO orapass 要解密，改用xml控管
		dataSource.setPassword(persistenceProps.getProperty("datasource.password"));
		return dataSource;
	}*/

	@Bean
	public  DataSource dataSource() throws Exception{
	    BasicDataSource dataSource = new BasicDataSource();
	    dataSource.setDriverClassName(persistenceProps.getProperty("datasource.driver"));
        dataSource.setUrl(persistenceProps.getProperty("datasource.url"));
        dataSource.setUsername(persistenceProps.getProperty("datasource.username"));
        String authHandler = persistenceProps.getProperty("datasource.auth.handler");
        
        if(authHandler!=null){
            // orapass 要解密，改用xml控管
            AuthHandler handler = (AuthHandler) Class.forName(authHandler).newInstance();
            Properties prop = new Properties();
            prop.setProperty("datasource.encrypted", persistenceProps.getProperty("datasource.encrypted"));
            prop.setProperty("datasource.auth.file", persistenceProps.getProperty("datasource.auth.file"));
            prop.setProperty("datasource.application.id",  persistenceProps.getProperty("datasource.application.id"));
            prop.setProperty("datasource.username",  persistenceProps.getProperty("datasource.username"));
            handler.init(prop);
            dataSource.setPassword(handler.getPassword());
            handler = null;
        }else{
            dataSource.setPassword(persistenceProps.getProperty("datasource.password"));
        }
        
        int minIdle = 5;
        try{
            minIdle = Integer.parseInt(persistenceProps.getProperty("datasource.minIdle"));
        }catch(Exception e){
        }

        int maxIdle = 20;
        try{
            minIdle = Integer.parseInt(persistenceProps.getProperty("datasource.maxIdle"));
        }catch(Exception e){
        }
        
        dataSource.setMinIdle(minIdle);
        dataSource.setMaxIdle(maxIdle);
        dataSource.setMaxOpenPreparedStatements(180);
        
        return dataSource;
    }
	
}
