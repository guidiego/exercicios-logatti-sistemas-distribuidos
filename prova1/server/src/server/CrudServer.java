package server;

import java.rmi.Remote;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import crud.*;

class CrudServer implements Remote {
    public CrudServer() {

        try {
            Crud c = new CrudImpl();
            LocateRegistry.createRegistry(CrudRmiConstants.REDUCED.getPort());
            Naming.rebind(CrudRmiConstants.REDUCED.getUri(), c);

        } catch (Exception e) {
            System.out.println("Trouble: " + e);
        }
    }

    public static void main(String[] args) {
        new CrudServer();
    }
}