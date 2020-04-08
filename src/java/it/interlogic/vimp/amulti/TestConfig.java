package it.interlogic.vimp.amulti;

/**
 * @author ago
 *
 */


//@Configuration
//@ComponentScan("it.interlogic.vimp.service")
//@EnableJpaRepositories(basePackages = "it.interlogic.vimp.data")
public class TestConfig
{
	
//	@Bean
//	public EntityManagerFactory entityManagerFactory(DataSource dataSource)
//	{
//		log.info("Register Localized Entity Manager");
//		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//		vendorAdapter.setDatabasePlatform("org.hibernate.dialect.SQLServerDialect");
//
//		vendorAdapter.setShowSql(true);
//		vendorAdapter.setGenerateDdl(false);
//
//		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
//		factory.setDataSource(dataSource);
//		factory.setPackagesToScan("it.interlogic.vimp.data.jpa.model");
//		factory.setJpaVendorAdapter(vendorAdapter);
//		
//		Map<String,String> jpaProperties = new Hashtable<String,String>();
//		jpaProperties.put("hibernate.ejb.interceptor","it.interlogic.vimp.amulti.MultilanguageInterceptor");
//		factory.setJpaPropertyMap(jpaProperties);
//
//		factory.afterPropertiesSet();
//		return factory.getObject();
//	}

//	@Bean
//	public DataSource dataSource()
//	{
//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		try
//		{
//			Properties prop = new Properties();
//			prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("dbconnection.properties"));
//
//			dataSource.setDriverClassName(prop.getProperty("jdbc.diverName"));
//			dataSource.setUrl(prop.getProperty("jdbc.url"));
//			dataSource.setUsername(prop.getProperty("jdbc.username"));
//			dataSource.setPassword(prop.getProperty("jdbc.password"));
//		}
//		catch (Exception err)
//		{
//			err.printStackTrace();
//		}
//
//		return dataSource;
//	}

//	@Bean
//	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory)
//	{
//		JpaTransactionManager transactionManager = new JpaTransactionManager();
//		transactionManager.setEntityManagerFactory(entityManagerFactory);
//		return transactionManager;
//	}

}