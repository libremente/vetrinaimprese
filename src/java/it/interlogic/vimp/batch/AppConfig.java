package it.interlogic.vimp.batch;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * @author ago
 *
 */

@Configuration
@ComponentScan({ "it.interlogic.vimp.service", "it.interlogic.vimp.data.dao" })
@EnableJpaRepositories(basePackages = "it.interlogic.vimp.data")
@PropertySource(value = "/dbconnection.properties", ignoreResourceNotFound=true)
public class AppConfig
{
	@Bean
	public EntityManagerFactory entityManagerFactory()
	{
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setDatabasePlatform("org.hibernate.dialect.SQLServerDialect");
		vendorAdapter.setShowSql(true);
		vendorAdapter.setGenerateDdl(false);

		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setDataSource(dataSource());
		factory.setPackagesToScan("it.interlogic.vimp.data.jpa.model");
		factory.setJpaVendorAdapter(vendorAdapter);

		Properties jpaProperties = new Properties();
		jpaProperties.put("hibernate.ejb.interceptor", "it.interlogic.vimp.amulti.MultilanguageInterceptor");
		factory.setJpaProperties(jpaProperties);

		factory.afterPropertiesSet();
		return factory.getObject();
	}

	@Bean
	public DataSource dataSource()
	{
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		try
		{
			Properties prop = new Properties();
			prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("dbconnection.properties"));

			dataSource.setDriverClassName(prop.getProperty("jdbc.diverName"));
			dataSource.setUrl(prop.getProperty("jdbc.url"));
			dataSource.setUsername(prop.getProperty("jdbc.username"));
			dataSource.setPassword(prop.getProperty("jdbc.password"));
		}
		catch (Exception err)
		{
			err.printStackTrace();
		}

		return dataSource;
	}

	@Bean
	public PlatformTransactionManager transactionManager()
	{
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory());
		return transactionManager;
	}

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource)
	{
		return new JdbcTemplate(dataSource);
	}

}