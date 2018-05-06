package com.pad.bgvi.server;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pad.bgvi.server.rmi.RMIServer;
import com.pad.bgvi.server.utils.HibernateUtil;

public class ServletLifecycleListener implements ServletContextListener{

	final static Logger log = LogManager.getLogger(ServletLifecycleListener.class.getName());
	
	public ServletLifecycleListener() {
		
	}

	public void contextInitialized(ServletContextEvent sce) {
		
		System.out.println("STARTING SERVER...");
		ServletContext context = sce.getServletContext();
		initHibernate(context);
		initRMIServer();
		System.out.println("SERVER ONLINE...");
		
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
	private void initRMIServer() {
		try {
			RMIServer.init();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			e.printStackTrace();
		}
	}
	
	private void initHibernate(ServletContext context) {
		HibernateUtil.init(context);
	}

	public void contextDestroyed(ServletContextEvent sce) {
		HibernateUtil.shutdown();
	}
}
