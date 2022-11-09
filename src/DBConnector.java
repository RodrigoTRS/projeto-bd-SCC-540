import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnector implements GlobalVars {
    Connection connection;
    public DBConnector () {
    }

    public void connect() {
        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to Oracle Database Server!");
        } catch (SQLException e) {
            System.out.println("Oops, error:");
            e.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            connection.close();
            System.out.println("Disconnected to Oracle Database Server!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
