package ru.epam.spring.cinema.web.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

import ru.epam.spring.cinema.config.SpringRootConfig;
import ru.epam.spring.cinema.web.listerner.SessionListener;

public class AppInitializer extends AbstractDispatcherServletInitializer {

	@Override
	protected WebApplicationContext createRootApplicationContext() {
 		AnnotationConfigApplicationContext parentContext
			= new AnnotationConfigApplicationContext(SpringRootConfig.class);
        XmlWebApplicationContext context = new XmlWebApplicationContext();
        context.setParent(parentContext);
        context.setConfigLocation("/WEB-INF/spring/security-context.xml");
        return context;
	}

	@Override
	protected WebApplicationContext createServletApplicationContext() {
        XmlWebApplicationContext context = new XmlWebApplicationContext();
        context.setConfigLocation("/WEB-INF/spring/servlet-context.xml");
        return context;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
        servletContext.addListener(new SessionListener());
    }

}
