package com.bitcademy.mysite.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class ContextLoadListener implements ServletContextListener {
	

    // 톰캣에서 Application이 로딩될때
    public void contextInitialized(ServletContextEvent servletContextEvent)  { 
    	ServletContext context = servletContextEvent.getServletContext();
    	String contextConfigLocation = context.getInitParameter("contextConfigLocation");
    	
    	System.out.println("Application start..." + contextConfigLocation);
    }
	
    // 톰캣에서 Application이 내려갈때
    public void contextDestroyed(ServletContextEvent sce)  { 

    }

	
}
