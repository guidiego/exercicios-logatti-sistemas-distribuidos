package chat;

import java.lang.Thread;
import java.rmi.RemoteException;
import java.util.ArrayList;

import chat.Chat;
import chat.MTCallbackInterface;

public class MessageThread extends Thread {
    private Chat chat;
    private int messageIndex;
    private MTCallbackInterface callback;

    public MessageThread(Chat c, MTCallbackInterface callback) {
        this.chat = c;
        this.messageIndex = 0;
        this.callback = callback;
    }

    public void run() {
        try {
            while(true) {
                ArrayList<String> msgs =
                    this.chat.getMessagesFrom(this.messageIndex);

                this.messageIndex += msgs.size();

                for (String m : msgs) {
                    this.callback.run(m);
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}