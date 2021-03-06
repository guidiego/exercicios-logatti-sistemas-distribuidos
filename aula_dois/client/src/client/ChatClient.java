package client;

import java.rmi.Naming;
import java.rmi.RemoteException;

import chat.Chat;
import chat.ChatRmiConstants;
import chat.MessageThread;
import chat.MTCallbackInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class ChatClient {

    String appName = "Chat";
    ChatClient chatUi;
    JFrame newFrame = new JFrame(appName);
    JButton sendMessage;
    JTextField messageBox;
    JTextArea chatBox;
    JTextField usernameChooser;
    JFrame preFrame;
    String  username;
    Chat chat;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager
                            .getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ChatClient chatUi = new ChatClient();
                chatUi.preDisplay();
            }
        });
    }

    public void preDisplay() {
        newFrame.setVisible(false);
        preFrame = new JFrame(appName);
        usernameChooser = new JTextField(15);
        JLabel chooseUsernameLabel = new JLabel("Digite seu usuario:");
        JButton enterServer = new JButton("Entrar");
        enterServer.addActionListener(new enterServerButtonListener());
        JPanel prePanel = new JPanel(new GridBagLayout());

        GridBagConstraints preRight = new GridBagConstraints();
        preRight.insets = new Insets(0, 0, 0, 10);
        preRight.anchor = GridBagConstraints.EAST;
        GridBagConstraints preLeft = new GridBagConstraints();
        preLeft.anchor = GridBagConstraints.WEST;
        preLeft.insets = new Insets(0, 10, 0, 10);
        preRight.fill = GridBagConstraints.HORIZONTAL;
        preRight.gridwidth = GridBagConstraints.REMAINDER;

        prePanel.add(chooseUsernameLabel, preLeft);
        prePanel.add(usernameChooser, preRight);
        preFrame.add(BorderLayout.CENTER, prePanel);
        preFrame.add(BorderLayout.SOUTH, enterServer);
        preFrame.setSize(300, 300);
        preFrame.setVisible(true);

    }

    public void display() {

        try {
            chat = (Chat) Naming.lookup(ChatRmiConstants.REDUCED.getUri());
            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new BorderLayout());

            JPanel southPanel = new JPanel();
            southPanel.setBackground(Color.GRAY);
            southPanel.setLayout(new GridBagLayout());

            messageBox = new JTextField(30);
            messageBox.requestFocusInWindow();

            sendMessage = new JButton("Enviar");
            sendMessage.addActionListener(new sendMessageButtonListener());

            chatBox = new JTextArea();
            chatBox.setEditable(false);
            chatBox.setFont(new Font("Serif", Font.PLAIN, 15));
            chatBox.setLineWrap(true);

            mainPanel.add(new JScrollPane(chatBox), BorderLayout.CENTER);

            GridBagConstraints left = new GridBagConstraints();
            left.anchor = GridBagConstraints.LINE_START;
            left.fill = GridBagConstraints.HORIZONTAL;
            left.weightx = 512.0D;
            left.weighty = 1.0D;

            GridBagConstraints right = new GridBagConstraints();
            right.insets = new Insets(0, 10, 0, 0);
            right.anchor = GridBagConstraints.LINE_END;
            right.fill = GridBagConstraints.NONE;
            right.weightx = 1.0D;
            right.weighty = 1.0D;

            southPanel.add(messageBox, left);
            southPanel.add(sendMessage, right);

            mainPanel.add(BorderLayout.SOUTH, southPanel);

            newFrame.add(mainPanel);
            newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            newFrame.setSize(470, 300);
            newFrame.setVisible(true);

            MessageThread mt = new MessageThread(chat, new MTCallbackInterface(){
                @Override
                public void run(String m) {
                    chatBox.append(m + "\n");
                }
            });
            mt.start();
        } catch (Exception e) {
            System.out.println("Trouble: " + e);
        }
    }

    class sendMessageButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (messageBox.getText().length() < 1) {
                // do nothing
            } else if (messageBox.getText().equals(".clear")) {
                chatBox.setText("Limpar Mensagens\n");
                messageBox.setText("");
            } else {
                try {
                    chat.send(username, messageBox.getText());
                    messageBox.setText("");
				} catch (RemoteException e) {
					e.printStackTrace();
				}
            }
            messageBox.requestFocusInWindow();
        }
    }

    class enterServerButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            username = usernameChooser.getText();
            if (username.length() < 1) {
                System.out.println("No!");
            } else {
                preFrame.setVisible(false);
                display();
            }
        }

    }
}