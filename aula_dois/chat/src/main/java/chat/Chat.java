package chat;

import java.rmi.Remote;

public interface Chat extends Remote {
	public void send(String u, String m) throws java.rmi.RemoteException;
}
