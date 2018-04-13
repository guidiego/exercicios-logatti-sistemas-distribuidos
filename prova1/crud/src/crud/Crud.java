package crud;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Crud extends Remote {
	public void save(Veterinary v) throws RemoteException;
	public ArrayList<Veterinary> list(String query) throws RemoteException;
}