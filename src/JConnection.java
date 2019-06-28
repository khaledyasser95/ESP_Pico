import java.sql.Connection;
import java.sql.DriverManager;

public class JConnection {
    static boolean connected = false;

    public static Connection ConnectDB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/sensors", "root", "");
            connected = true;
            return conn;
        } catch (Exception e) {
            connected = false;
            return null;
        }
    }


    public boolean isConnected() {
        return connected;
    }
}
