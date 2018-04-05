package server;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.Date;

class ServerLog {
    private static final String FILENAME =
        System.getProperty("user.home") + File.separator + "chat-log.txt";

        private SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
        private BufferedWriter bw;
        private FileWriter fw;

    public ServerLog() {
        try {
			this.fw = new FileWriter(FILENAME);
		} catch (IOException e) {
			e.printStackTrace();
        }

        this.bw = new BufferedWriter(this.fw);
    }

    public void append(String m) {
        try {
			this.bw.write(
            this.df.format(new Date()) + "'" + m + "'\n");
            this.bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public void finish() {
        this.append("Server close");

        try {
            this.bw.close();
			this.fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}