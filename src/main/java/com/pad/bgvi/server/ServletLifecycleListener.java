package com.pad.bgvi.server;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.pad.bgvi.server.model.Product;
import com.pad.bgvi.server.utils.HibernateUtil;

public class ServletLifecycleListener implements ServletContextListener{

	final static Logger log = LogManager.getLogger(ServletLifecycleListener.class.getName());
	
	public ServletLifecycleListener() {
		
	}

	public void contextInitialized(ServletContextEvent sce) {
		
		log.info("Starting server...");
		ServletContext context = sce.getServletContext();
		initHibernate(context);
		log.info("SERVER ONLINE...");
		
		
		/*Session session = HibernateUtil.getSessionFactory().openSession();
		//creating transaction object
		Transaction t=session.beginTransaction();
		Product p = new Product();
		p.setName("whatever");
		p.setDescription("fuck off :*");
		p.setCategoryType(1);
		p.setImage("assets/dans_dama.jpg");
		p.setPrice(1000000);
		p.setQuantity(234);
		session.persist(p);
		t.commit();
		session.close();*/
	}
	
	private void initHibernate(ServletContext context) {
		HibernateUtil.init(context);
	}

	public void contextDestroyed(ServletContextEvent sce) {
		HibernateUtil.shutdown();
	}
}
