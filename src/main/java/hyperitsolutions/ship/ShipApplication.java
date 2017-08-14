package hyperitsolutions.ship;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.jpa.AvailableSettings;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import hyperitsolutions.ship.model.OrderRepository;
import hyperitsolutions.ship.model.entity.Order;

@PropertySource(value = { "classpath:jdbc.properties" })
@SpringBootApplication
@EnableTransactionManagement
public class ShipApplication {

	private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
	private static final String PROPERTY_NAME_HIBERNATE_MAX_FETCH_DEPTH = "hibernate.max_fetch_depth";
	private static final String PROPERTY_NAME_HIBERNATE_JDBC_FETCH_SIZE = "hibernate.jdbc.fetch_size";
	private static final String PROPERTY_NAME_HIBERNATE_JDBC_BATCH_SIZE = "hibernate.jdbc.batch_size";
	private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
	private static final String ENTITYMANAGER_PACKAGES_TO_SCAN = "hyperitsolutions.ship.model.entity";
	private static final String HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";

	public static void main(String[] args) {

		SpringApplication.run(ShipApplication.class, args);
		
	}

	@Autowired
	private Environment env;

	// CORS
		@Bean
		FilterRegistrationBean corsFilter(
				@Value("${tagit.origin:http://localhost:8080}") String origin) {
			FilterRegistrationBean bean =  new FilterRegistrationBean();
			bean.setFilter(
			new Filter() {
				
				@Override
				public void doFilter(ServletRequest req, ServletResponse res,
						FilterChain chain) throws IOException, ServletException {
					HttpServletRequest request = (HttpServletRequest) req;
					HttpServletResponse response = (HttpServletResponse) res;
					String method = request.getMethod();
					// this origin value could just as easily have come from a database
					response.setHeader("Access-Control-Allow-Origin", origin);
					response.setHeader("Access-Control-Allow-Methods",
							"POST,GET,OPTIONS,DELETE");
					response.setHeader("Access-Control-Max-Age", Long.toString(60 * 60));
					response.setHeader("Access-Control-Allow-Credentials", "true");
					response.setHeader(
							"Access-Control-Allow-Headers",
							"Origin,Accept,X-Requested-With,Content-Type,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization");
					if ("OPTIONS".equals(method)) {
						response.setStatus(HttpStatus.OK.value());
					}
					else {
						chain.doFilter(req, res);
					}
				}

				public void init(FilterConfig filterConfig) {
				}

				public void destroy() {
				}
			});
			return bean;
		}

	@Bean(name = "lab1")
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
		dataSource.setUrl(env.getProperty("jdbc.url"));
		dataSource.setUsername(env.getProperty("jdbc.username"));
		dataSource.setPassword(env.getProperty("jdbc.password"));
		return dataSource;
	}

	@Bean(name="transactionManager")
	public JpaTransactionManager jpaTransactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
		return transactionManager;
	}

	private HibernateJpaVendorAdapter vendorAdaptor() {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setShowSql(true);
		return vendorAdapter;
	}

	@Bean(name="entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {

		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setPersistenceUnitName("lab1");
		entityManagerFactoryBean.setJpaVendorAdapter(vendorAdaptor());
		entityManagerFactoryBean.setDataSource(dataSource());
		entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		entityManagerFactoryBean.setPackagesToScan(ENTITYMANAGER_PACKAGES_TO_SCAN);
		entityManagerFactoryBean.setJpaProperties(jpaHibernateProperties());

		return entityManagerFactoryBean;
	}

	private Properties jpaHibernateProperties() {

		Properties properties = new Properties();

		properties.put(PROPERTY_NAME_HIBERNATE_MAX_FETCH_DEPTH,
				env.getProperty(PROPERTY_NAME_HIBERNATE_MAX_FETCH_DEPTH));
		properties.put(PROPERTY_NAME_HIBERNATE_JDBC_FETCH_SIZE,
				env.getProperty(PROPERTY_NAME_HIBERNATE_JDBC_FETCH_SIZE));
		properties.put(PROPERTY_NAME_HIBERNATE_JDBC_BATCH_SIZE,
				env.getProperty(PROPERTY_NAME_HIBERNATE_JDBC_BATCH_SIZE));
		properties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL, env.getProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));
		properties.put(PROPERTY_NAME_HIBERNATE_DIALECT, env.getProperty(PROPERTY_NAME_HIBERNATE_DIALECT));
		properties.put(HIBERNATE_HBM2DDL_AUTO, env.getProperty(HIBERNATE_HBM2DDL_AUTO));

		properties.put(AvailableSettings.SCHEMA_GEN_DATABASE_ACTION, "none");
		return properties;
	}
	
	
	
	
	@Bean
    public CommandLineRunner run(OrderRepository orderRepository) {
        return args -> {

        	if (null != orderRepository) {
        		orderRepository.deleteAll();
				System.out.println("repository NOT null!");
				Order order2 = new Order();
				order2.setName("ordertwo");
				order2.setId(1L);
				order2 = orderRepository.save(order2);
			}else {
				System.out.println("repository is null");
			}
            

        };
    }
	
	static class InnerShipApp{
	
		
		
		static void run(OrderRepository orderRepository) {
			
		}
	}

}
