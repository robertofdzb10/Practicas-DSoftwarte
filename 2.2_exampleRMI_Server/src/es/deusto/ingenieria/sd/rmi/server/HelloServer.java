package es.deusto.ingenieria.sd.rmi.server;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class HelloServer extends UnicastRemoteObject implements IHelloAPI {

	private static final long serialVersionUID = 1L;
	private int cont = 0;

	protected HelloServer() throws RemoteException {
		super();
	}

	public String sayHello() {
		cont++;
		System.out.println(" * Client number: " + cont);
		return "Hello World!";
	}

	public static void main(String[] args) {
		if (args.length != 3) {
			System.out.println("usage: java [policy] [codebase] es.deusto.ingenieria.sd.rmi..HelloServer [ip] [port] [name]");
			System.exit(0);
		}

		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		String name = "//" + args[0] + ":" + args[1] + "/" + args[2];

		try {		
			IHelloAPI serviceObject = new HelloServer();
			Naming.rebind(name, serviceObject);
			System.out.println("* Server '" + name + "' active and waiting...");
		} catch (Exception e) {
			System.err.println("- Exception running the server: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	
}