package server;

import java.rmi.Remote;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import chat.*;

class ChatServer implements Remote {
    public ChatServer() {
        try {
            Chat c = new ChatImpl();
            LocateRegistry.createRegistry(ChatRmiConstants.REDUCED.getPort());
            Naming.rebind(ChatRmiConstants.REDUCED.getUri(), c);
        } catch (Exception e) {
            System.out.println("Trouble: " + e);
        }
    }

    public static void main(String[] args) {
        new ChatServer();
    }
}