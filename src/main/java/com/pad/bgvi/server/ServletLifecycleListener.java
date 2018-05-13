package com.pad.bgvi.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.pad.bgvi.common.RMIServerUtil;
import com.pad.bgvi.server.model.Product;
import com.pad.bgvi.server.rmi.ShopImpl;
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
		p.setName("Pantaloni dans damă");
		p.setDescription("Pantalon cu talie joasă și croială reglabilă. Modern și confortabil.");
		p.setCategoryType(1);
		p.setImage("assets/dans_dama.jpg");
		p.setPrice(27);
		p.setQuantity(0);
		session.persist(p);
		
		Product p2 = new Product();
		p2.setName("Tricou drumeție damă");
		p2.setDescription("Tricoul evacuează eficient transpirația și evită mirosurile neplăcute. Ideal pentru primele drumeții la munte.");
		p2.setCategoryType(1);
		p2.setImage("assets/drumetie.jpg");
		p2.setPrice(25);
		p2.setQuantity(2);
		session.persist(p2);
		
		Product p3 = new Product();
		p3.setName("Încălțăminte drumeție bărbați");
		p3.setDescription("Talpă din cauciuc ce asigură rezistenţă şi aderenţă optimă. Faţa mid oferă susţinere, pentru o drumeţie sigură la munte.");
		p3.setCategoryType(2);
		p3.setImage("assets/incaltaminte_drumetie_barbati.jpg");
		p3.setPrice(80);
		p3.setQuantity(2);
		session.persist(p3);
		
		Product p4 = new Product();
		p4.setName("Poante de piele");
		p4.setDescription("Material din piele pentru mișcare mai bună a boltei plantare. Bandă din nailon ce asigură flexibilitate și eleganță.");
		p4.setCategoryType(2);
		p4.setImage("assets/poante_piele.jpg");
		p4.setPrice(45);
		p4.setQuantity(3);
		session.persist(p4);
		
		Product p5 = new Product();
		p5.setName("Racheta tenis BABOLAT");
		p5.setDescription("Noua rachetă BABOLAT Pure Drive oferă un echilibru perfect între lovituri puternice și control.");
		p5.setCategoryType(3);
		p5.setImage("assets/babolat.jpg");
		p5.setPrice(110);
		p5.setQuantity(7);
		session.persist(p5);
		
		Product p6 = new Product();
		p6.setName("Bicicleta dama");
		p6.setDescription("Bicicleta de oras pentru dame.");
		p6.setCategoryType(3);
		p6.setImage("assets/bicicleta.jpg");
		p6.setPrice(250);
		p6.setQuantity(2);
		session.persist(p6);
		
		t.commit();
		
		session.close();*/
	}
	private void initRMIServer() {
		try {
			ShopImpl server = new ShopImpl();
			Registry reg = LocateRegistry.createRegistry(1099);
			// System.out.println("Server is ready");
			// reg.rebind(RMIServerUtil.URI, server);
			//Remote r = new ShopImpl();
			reg.rebind("//192.168.43.94/server-management", server);
		} catch (RemoteException e) {
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
