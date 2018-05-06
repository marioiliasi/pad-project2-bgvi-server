package com.pad.bgvi.server.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.pad.bgvi.common.IRMIServer;
import com.pad.bgvi.common.RMIServerUtil;

public class RMIServer extends UnicastRemoteObject implements IRMIServer {

	private static final long serialVersionUID = 8891765370603977567L;
	
	private static RMIServer server;
	
	public RMIServer() throws RemoteException {
		super();
	}

	public static void init() throws RemoteException, MalformedURLException {
		if (server == null) {
			server = new RMIServer();
			Naming.rebind(RMIServerUtil.URI, server);
		} else {
			destroy();
			init();
		}
	}
	
	public static void destroy() throws RemoteException {
		if(server != null) {
			server.stopServer();
			server = null;
		}
	}

	public static RMIServer getServer() throws RemoteException, MalformedURLException {
		return server;
	}

	public void startServer() throws RemoteException {
		
	}

	public void restartServer() throws RemoteException {
		
	}

	public void stopServer() throws RemoteException {

	}
}
