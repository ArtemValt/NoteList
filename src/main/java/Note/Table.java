package Note;

import Connect.ConnectionCreator;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Table  implements AutoCloseable  {

    static ConnectionCreator conn = new ConnectionCreator();
    static Connection connection;

    static {
        try {
            connection = conn.createConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Statement st;

    static {
        try {
            st = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Table() throws SQLException {
    }


    @Override
    public void close() throws IOException {
        try {
            if(connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Ошибка закрытия SQL соединения!");
        }
    }
}
