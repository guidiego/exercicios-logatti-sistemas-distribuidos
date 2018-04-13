package client;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.ArrayList;

import crud.*;

public class CrudClient {
    private static Scanner sc = new Scanner(System.in);
    private static int localId = 0;
    private static Crud crud;

    public static void main(String[] args) {
        int opt = -1;
        try {
            crud = (Crud) Naming.lookup(CrudRmiConstants.REDUCED.getUri());
        } catch (Exception e) {
            System.out.println("Trouble in main: " + e);
        }
        while (opt != 0) {
            if (opt > 2) {
                System.out.println("Opção Errada");
                break;
            }

            opt = printMenu();
            switch(opt) {
                case 1:
                    save();
                    break;
                case 2:
                    list();
                    break;
            }
        }
    }

    public static int printMenu() {
        System.out.println("O que deseja fazer no sistema?");
        System.out.println("0 - Sair");
        System.out.println("1 - Cadastrar");
        System.out.println("2 - Pesquisar");
        System.out.print("> ");
        return sc.nextInt();
    }

    public static void save() {
        System.out.println("----- Cadastro de Veterinario -----");
        System.out.println("Informe o nome");
        sc.nextLine();

        System.out.print("> ");
        String name = sc.nextLine();


        System.out.println("Informe o Cpf");
        System.out.print("> ");
        String cpf = sc.nextLine();

        try {
            localId++;
            Veterinary v = new Veterinary(localId, name, cpf);
            crud.save(v);
            System.out.println("Salvo veterinario " + name + " com id " + localId);
        } catch (Exception e) {
            localId--;
            System.out.println("Trouble in add: " + e);
        }
    }

    public static void list() {
        System.out.println("----- Listagem Veterinario -----");
        System.out.println("(Dica: Você pode digitar algo se quiser procurar, para listar todos digite enter)");
        sc.nextLine();
        try {
            System.out.print("> ");
            for (Veterinary v : crud.list(sc.nextLine())) {
                System.out.println(v);
            }
        } catch (Exception e) {
            System.out.println("Trouble in list: " + e);
        }
    }
}