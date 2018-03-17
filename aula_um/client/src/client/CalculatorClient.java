package client;

import java.rmi.Naming;
import calculator.*;

public class CalculatorClient {
    public static void main(String[] args) {
        try {
            Calculator c = (Calculator) Naming
                .lookup(CalcRmiConstants.REDUCED.getUri());

            System.out.println(c.add(1,2));
            System.out.println(c.sub(1,2));
            System.out.println(c.mult(1,2));
            System.out.println(c.div(4,2));
        } catch (Exception e) {
            System.out.println("Trouble: " + e);
        }
    }
}