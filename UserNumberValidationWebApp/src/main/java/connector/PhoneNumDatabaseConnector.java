package connector;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import entities.MyAdmin;
import entities.MyUser;

// This class is responsible for configuring database connectivity and Spring MVC settings.
@Component
@EnableWebMvc
@Configuration
@ComponentScan(basePackages="controller,entities,service")
public class PhoneNumDatabaseConnector {

	// Bean to resolve view names to JSP resources
	@Bean
	public InternalResourceViewResolver resolver() {
		InternalResourceViewResolver resolve = new InternalResourceViewResolver();
		resolve.setPrefix("WEB-INF/views/");
		resolve.setSuffix(".jsp");
		return resolve;
	}
	
	// Properties for Hibernate configuration
	private Properties myprops() {
		Properties props =new Properties();
		props.put("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
		props.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/phonenum");
		props.put("hibernate.connection.username", "root");
		props.put("hibernate.connection.password", "root");
		props.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
		props.put("hibernate.hbm2ddl.auto", "update");
		props.put("hibernate.show_sql", "true");
		return props;
		
	}
	
	// Bean to configure and provide Hibernate session factory
	@Bean
	public SessionFactory factory() {
		SessionFactory factory= null;
		org.hibernate.cfg.Configuration config = new org.hibernate.cfg.Configuration();
		config.setProperties(myprops()); // Set Hibernate properties
		config.addAnnotatedClass(MyUser.class); // Add annotated entity classes
		config.addAnnotatedClass(MyAdmin.class);
		StandardServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build(); // Build Hibernate service registry
		factory = config.buildSessionFactory(registry); // Build session factory
		return factory;
	}
}
