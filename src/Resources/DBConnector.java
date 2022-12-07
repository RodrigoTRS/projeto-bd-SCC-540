package Resources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnector implements GlobalVars{
    public Connection connection;
    public DBConnector () {
    }

    public void connect() {
        try {
            connection = DriverManager.getConnection(GlobalVars.url, GlobalVars.username, GlobalVars.password);
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
