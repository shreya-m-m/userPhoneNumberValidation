package connector;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

// This class initializes the web application by configuring the DispatcherServlet.
public class WebInitializer implements WebApplicationInitializer {

    // Method called when the application starts up.
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
    	
        // Create an application context with the configuration defined in PhoneNumDatabaseConnector class.
        AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
        webContext.register(PhoneNumDatabaseConnector.class);

        // Register the DispatcherServlet with the ServletContext.
        ServletRegistration.Dynamic server = servletContext.addServlet("Apache Tomcat v9.0 at localhost", new DispatcherServlet(webContext));
        
        // Map the DispatcherServlet to the root URL ("/").
        server.addMapping("/");
        
        // Ensure that the DispatcherServlet is loaded when the application starts.
        server.setLoadOnStartup(1);
    }
}
