package crud;
import java.io.Serializable;
// import java.rmi.RemoteException;

public class Veterinary implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private String cpf;

    public Veterinary (int id, String name, String cpf) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
    }

    public boolean contains(String query) {
        if (
            Integer.toString(this.id).contains(query) ||
            this.name.contains(query) ||
            this.cpf.contains(query)
        ) {
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return "Id: " + this.id + " | Nome: " + this.name + " | CPF: " + this.cpf;
    }
}