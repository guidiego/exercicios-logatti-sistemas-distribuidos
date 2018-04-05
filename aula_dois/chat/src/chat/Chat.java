package chat;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Chat extends Remote {
	public void send(String u, String m) throws RemoteException;
	public ArrayList<String> getMessagesFrom(int i) throws RemoteException;
}