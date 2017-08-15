package com.spittr.config;


import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Configuration
@EnableTransactionManagement
@ComponentScan(
		basePackages={
				"com.spittr"
				},
		excludeFilters={
				@ComponentScan.Filter(type=FilterType.ANNOTATION, value=EnableWebMvc.class)}
)
public class RootConfig {

	@Bean
	public DataSource dataSource(){
		DriverManagerDataSource dSource = new DriverManagerDataSource();
		dSource.setDriverClassName("com.mysql.jdbc.Driver");
		dSource.setUrl("jdbc:mysql://localhost:3306/srect?useUnicode=true&characterEncoding=UTF-8");
		dSource.setUsername("root");
		dSource.setPassword("root");
		return dSource;
	}
	
	@Bean(name="sessionFactory")
	public LocalSessionFactoryBean sessionFactory(){
		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		sessionFactoryBean.setDataSource(dataSource());
		sessionFactoryBean.setPackagesToScan(
				"com.spittr.model",
				"com.spittr.user.model", 
				"com.spittr.authorization.model",
				"com.spittr.message.model",
				"com.spittr.image.model",
				"com.spittr.location.model"
				);

		Properties hibernateProperties = new Properties();
		hibernateProperties.put("hibernate.dialect", "com.spittr.config.MySQLDialectUTF8");
        	hibernateProperties.put("hibernate.show_sql", "true");

        	hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");  
        	sessionFactoryBean.setHibernateProperties(hibernateProperties);
        return sessionFactoryBean;
	}
	
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory){
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory);
		return transactionManager;
	}
	
}
