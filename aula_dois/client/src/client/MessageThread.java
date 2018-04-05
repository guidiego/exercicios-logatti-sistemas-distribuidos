package client;

import java.lang.Thread;
import java.rmi.RemoteException;
import java.util.ArrayList;
import javax.swing.JTextArea;

import chat.Chat;

class MessageThread extends Thread {
    private Chat chat;
    private int messageIndex;
    private JTextArea chatBox;

    public MessageThread(Chat c, JTextArea chatBox) {
        this.chat = c;
        this.messageIndex = 0;
        this.chatBox = chatBox;
    }

	public void run() {
        try {
            while(true) {
                ArrayList<String> msgs =
                    this.chat.getMessagesFrom(this.messageIndex);

                this.messageIndex += msgs.size();

                for (String m : msgs) {
                    System.out.println(m);
                    this.chatBox.append(m);
                }
            }
		} catch (RemoteException e) {
			e.printStackTrace();
		}
    }
}