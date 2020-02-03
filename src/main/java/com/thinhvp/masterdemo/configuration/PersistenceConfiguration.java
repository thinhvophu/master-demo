package com.thinhvp.masterdemo.configuration;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.thinhvp.masterdemo.persistence.repository")
@Configuration
@PropertySource("classpath:application.properties")
public class PersistenceConfiguration {

	@Autowired
	Environment environment;

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);
		return transactionManager;
	}

	@Bean
	LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setDataSource(mysqlDataSource());
		entityManagerFactory.setJpaProperties(hibernateProperties());
		entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		entityManagerFactory.setPackagesToScan("com.thinhvp.masterdemo.persistence.entity");
		return entityManagerFactory;
	}

	@Bean
	Properties hibernateProperties() {
		Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
		hibernateProperties.setProperty("hibernate.format_sql", environment.getProperty("hibernate.format_sql"));
		hibernateProperties.setProperty("hibernate.dialect", environment.getProperty("hibernate.dialect"));
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto"));
		hibernateProperties.setProperty("hibernate.dialect.storage_engine", environment.getProperty("hibernate.dialect.storage_engine"));
		return hibernateProperties;
	}

	@Bean
	DataSource mysqlDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getRequiredProperty("db.driver"));
		dataSource.setUsername(environment.getRequiredProperty("db.user"));
		dataSource.setPassword(environment.getRequiredProperty("db.password"));
		dataSource.setUrl(environment.getRequiredProperty("db.url"));
		return dataSource;
	}
}
