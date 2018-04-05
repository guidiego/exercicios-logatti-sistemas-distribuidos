package server;

import java.rmi.Remote;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import chat.*;
import server.ServerLog;

class ChatServer implements Remote {
    public ChatServer() {
        ServerLog log = new ServerLog();

        try {
            Chat c = new ChatImpl();
            LocateRegistry.createRegistry(ChatRmiConstants.REDUCED.getPort());
            Naming.rebind(ChatRmiConstants.REDUCED.getUri(), c);

            MessageThread mt = new MessageThread(c, new MTCallbackInterface(){
                @Override
                public void run(String m) {
                    log.append(m);
                }
            });
            mt.start();
        } catch (Exception e) {
            log.finish();
            System.out.println("Trouble: " + e);
        }
    }

    public static void main(String[] args) {
        new ChatServer();
    }
}