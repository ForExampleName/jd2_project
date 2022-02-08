package by.academy.config;

import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.*;
import java.util.Set;

public class WebAppInit implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(WebAppConfig.class);

        DispatcherServlet dispatcherServlet = new DispatcherServlet(context);
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", dispatcherServlet);
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("*.html", "*.do");

        int MAX_SIZE = 5 * 1024 * 1024;
        MultipartConfigElement multipartConfigElement = new MultipartConfigElement("", MAX_SIZE, MAX_SIZE, MAX_SIZE);
        dispatcher.setMultipartConfig(multipartConfigElement);
    }
}
