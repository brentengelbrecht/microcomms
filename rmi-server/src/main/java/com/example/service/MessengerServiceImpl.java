package com.example.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MessengerServiceImpl extends UnicastRemoteObject implements MessengerService {

    private static final long serialVersionUID = 1L;

    protected MessengerServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public String sendMessage(String clientMessage) {
        System.out.println("Invoked: sendMessage - Received from Client: " + clientMessage);
        return "Client Message".equals(clientMessage) ? "Server Message" : null;
    }

    public String unexposedMethod() { return "Not implemented"; }
}
