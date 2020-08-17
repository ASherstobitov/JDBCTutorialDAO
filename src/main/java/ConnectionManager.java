import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;



public class ConnectionManager {
    private static ConnectionManager instance = new ConnectionManager();

    public static ConnectionManager getInstance() {
        return instance;
    }
    public ConnectionManager() {
    }

    public Connection getConnection() {
            Connection connection = null;

        try {
            Properties properties = new Properties();
            InputStream inputStream = getClass().getResourceAsStream("db.properties");
            properties.load(inputStream);
            inputStream.close();
            String url = properties.getProperty("url");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
