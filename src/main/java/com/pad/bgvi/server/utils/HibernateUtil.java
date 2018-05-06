package com.pad.bgvi.server.utils;

import javax.servlet.ServletContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	final static Logger log = LogManager.getLogger(HibernateUtil.class.getName());
    private static SessionFactory sessionFactory;
    private static Configuration configuration;
    
    private static SessionFactory buildSessionFactory(ServletContext context) {
        try {
            log.info("Initializing hibernate from hibernate.xml - building session factory...");
            configuration = new Configuration().configure();
            return configuration.buildSessionFactory();
        }
        catch (Throwable ex) {
        	log.error("Failed building session factory", ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static void init(ServletContext context) {
    	if(sessionFactory != null) {
    		shutdown();
    	}
    	sessionFactory = buildSessionFactory(context);
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    public static Configuration getConfiguration() {
		return configuration;
	}

	public static void shutdown() {
    	log.info("Closing hibernate caches and connection pools...");
    	getSessionFactory().close();
    }

}
