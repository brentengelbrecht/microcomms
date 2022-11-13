package com.example.service;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.Policy;
import java.util.Properties;

public class Application {

    public static void main(String[] args) throws RemoteException {
        Policy.setPolicy(new MinimalPolicy());
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        Registry registry = null;
        try {
            registry = LocateRegistry.createRegistry(1099);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        try {
            //Naming.rebind("//localhost/MessengerService", new MessengerServiceImpl());
            registry.bind("MessengerServiceImpl", new MessengerServiceImpl());
            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
