package chat;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ChatImpl extends UnicastRemoteObject implements Chat  {
    private static final long serialVersionUID = 1L;

    public ChatImpl() throws RemoteException {
        super();
    }

    public void send(String u, String m) {
        System.out.println("[" + u + "] " + m);
    }
}