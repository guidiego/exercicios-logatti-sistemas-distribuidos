package client;

import java.util.Scanner;
import java.rmi.Naming;
import chat.*;

public class ChatClient {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            Chat c = (Chat) Naming
                .lookup(ChatRmiConstants.REDUCED.getUri());
            System.out.println("Enter Your name and press Enter:");
            String user = scanner.nextLine().trim();

            System.out.println("Bem Vindo: " + user);
            Boolean inChat = true;
            while (inChat) {
                String msg = scanner.nextLine().trim();
                if (msg == "exit") {
                    inChat = false;
                } else {
                    c.send(user, msg);
                }
            }
        } catch (Exception e) {
            System.out.println("Trouble: " + e);
        }
    }
}