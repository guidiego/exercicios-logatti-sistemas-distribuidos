package crud;

import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.rmi.RemoteException;

import crud.Crud;
import crud.Veterinary;

public class CrudImpl
extends UnicastRemoteObject
implements Crud {
    private static final long serialVersionUID = 1L;
    private List<Veterinary> veterinaries =
        new ArrayList<Veterinary>();

    public CrudImpl() throws RemoteException {
        super();
    }

    public void save(Veterinary v) {
        this.veterinaries.add(v);
    }

    public ArrayList<Veterinary> list(String query) {
        if (query == "") {
            return new ArrayList<Veterinary>(this.veterinaries);
        }

        List<Veterinary> filtered =
            this.veterinaries.stream()
                .filter(v -> v.contains(query))
                .collect(Collectors.toList());

        return new ArrayList<Veterinary>(filtered);
    }
}