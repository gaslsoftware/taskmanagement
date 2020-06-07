package com.gasl.taskmanagement.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class HibernateConfiguration {
	Logger logger = LoggerFactory.getLogger(HibernateConfiguration.class);
	
	@Value("${configured.database}")
	private String configuredDatabase;
    @Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		
		if (configuredDatabase.equals("MYSQL")) {
			logger.info("\n \n  DATABASE SWITCH VALUE : MYSQL  \n");
			sessionFactory.setDataSource(mySqlDataSource());
		} 
		else if (configuredDatabase.equals("h2")) {
			logger.info("\n \n DATABASE SWITCH VALUE : h2  \n");
			sessionFactory.setDataSource(h2DataSource());
		}

		sessionFactory.setPackagesToScan("com.gasl.taskmanagement.dto");
		sessionFactory.setHibernateProperties(hibernateProperties());

		return sessionFactory;
	}
    @ConditionalOnProperty(name = "configured.database", havingValue ="MYSQL")
    @Bean
    public DataSource mySqlDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3308/admin");
        dataSource.setUsername("root");
        dataSource.setPassword("");
        return dataSource;
    }
    
    @ConditionalOnProperty(name = "configured.database", havingValue ="h2")
    @Bean
    public DataSource h2DataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:testdb");
        dataSource.setUsername("sa");
        dataSource.setPassword("password");
 
        return dataSource;
    }
 
    @Bean
    public PlatformTransactionManager hibernateTransactionManager() {
        HibernateTransactionManager transactionManager
          = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }
 
	private final Properties hibernateProperties() {
		Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
		
		if (configuredDatabase.equals("MYSQL")) {
			hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		} else if (configuredDatabase.equals("h2")) {
			hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");

		}

		return hibernateProperties;
	}}
