package server;

import java.rmi.Remote;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import calculator.*;

public class CalculatorServer implements Remote {
    public CalculatorServer() {
        try {
            Calculator c = new CalculatorImpl();
            LocateRegistry.createRegistry(CalcRmiConstants.REDUCED.getPort());
            Naming.rebind(CalcRmiConstants.REDUCED.getUri(), c);
        } catch (Exception e) {
            System.out.println("Trouble: " + e);
        }
    }

    public static void main(String[] args) {
        new CalculatorServer();
    }
}