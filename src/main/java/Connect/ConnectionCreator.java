package Connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionCreator {
    private static final String PASSWORD_KEY = "db.password";
    private static final String USERNAME_KEY = "db.username";
    private static final String URL_KEY = "db.url";

    static {
        try {
            String driverName = PropertiesUtil.get("db.driver");
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // fatal exception
        }
    }

    public ConnectionCreator() {
    }

    public static Connection createConnection() throws SQLException {
        return DriverManager.getConnection(PropertiesUtil.get(URL_KEY),
                PropertiesUtil.get(USERNAME_KEY), PropertiesUtil.get(PASSWORD_KEY));
    }
}