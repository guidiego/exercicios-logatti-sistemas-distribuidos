package client;

import java.rmi.Naming;
import chat.*;

public class ChatClient {
    public static void main(String[] args) {
        try {
            Chat c = (Chat) Naming
                .lookup(ChatRmiConstants.REDUCED.getUri());
            System.out.println("Enter Your name and press Enter:");
            String user = s.nextLine().trim();

            System.out.println("Bem Vindo: " + user);
            Boolean inChat = true;
            while (inChat) {
                String msg = s.nextLine().trim();
                if (msg == "exist") {
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