package com.pad.bgvi.server;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

public class ServletLifecycleListener implements ServletContextListener{

	private Logger log = Logger.getLogger(ServletLifecycleListener.class.getName());
	
	public ServletLifecycleListener() {
		
	}

	public void contextInitialized(ServletContextEvent sce) {
		log.info("SERVER ONLINE...");
		
	}

	public void contextDestroyed(ServletContextEvent sce) {
		
	}
}
