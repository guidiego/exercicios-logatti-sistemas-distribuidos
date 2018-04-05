package chat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

class ChatRepository {
    private Connection conn;
    private final String connTemplate = "jdbc:mysql://%s/%s?user=%s&password=%s";
    private final String connUri = System.getenv("MYSQL_URI");
    private final String connDB = System.getenv("MYSQL_DATABASE");
    private final String connPass = System.getenv("MYSQL_PASSWORD");
    private final String connUser = System.getenv("MYSQL_USER");

    public ChatRepository () {
        try {
            this.conn =
               DriverManager.getConnection(this.createConnString());

            this.createChatTable();
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    public void saveMessage(String user, String message, String date) {
        try {
            Statement stmt = this.conn.createStatement();
            stmt.execute(this.createMessageInsert(user, message, date));
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

    private void createChatTable() {
        try {
            Statement stmt = this.conn.createStatement();
            stmt.executeUpdate(
                "CREATE TABLE messages (" +
                "   id INT NOT NULL AUTO_INCREMENT" +
                "   user VARCHAR(64) NOT NULL" +
                "   message text NOT NULL" +
                "   date date NOT NULL" +
                ")"
            );
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

    private String createMessageInsert(String user, String message, String date) {
        String insertTemplate = "INSERT INTO messages (%s, %s, %s)";

        return String.format(
            insertTemplate, user, message, date
        );
    }

    private String createConnString() {
        return String.format(
            this.connTemplate,
            this.connUri,
            this.connDB,
            this.connUser,
            this.connPass
        );
    }

}