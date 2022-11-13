package com.example.client;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.Policy;

public class Application {

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        Policy.setPolicy(new MinimalPolicy());
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        Registry registry = LocateRegistry.getRegistry();
        MessengerService stub = (MessengerService)registry.lookup("MessengerServiceImpl");
        //MessengerService stub = (MessengerService)Naming.lookup("MessengerServiceImpl");

        String responseMessage = stub.sendMessage("Client Message");
        String expectedMessage = "Server Message";

        System.out.printf("Expected: %s", expectedMessage);
        System.out.printf("Received: %s", responseMessage);
    }
}
