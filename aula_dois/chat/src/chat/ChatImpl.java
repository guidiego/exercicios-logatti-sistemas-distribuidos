package chat;

import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.rmi.RemoteException;

import chat.Chat;

public class ChatImpl
extends UnicastRemoteObject
implements Chat {
    private static final long serialVersionUID = 1L;
    private ArrayList<String> messages = new ArrayList<String>();
    private SimpleDateFormat df = new SimpleDateFormat("dd/MM/YYYY hh:mm");
    // private ChatRepository repo = new ChatRepository();

    public ChatImpl() throws RemoteException {
        super();
    }

    public void send(String u, String m) {
        String date = this.df.format(new Date());
        String msg = "[" + u + "] " + date + " : " + m;

        // repo.saveMessage(u, m, date);
        this.messages.add(msg);
    }

    public ArrayList<String> getMessagesFrom(int i) {
        return new ArrayList<String>(
            this.messages.subList(i, this.messages.size())
        );
    }
}