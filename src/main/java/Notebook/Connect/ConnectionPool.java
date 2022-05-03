package Notebook.Connect;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {
    private ConnectionPool(){};
    private static ConnectionPool instance=null;

    public static ConnectionPool getInstance() {
        if(instance==null)
            instance=new ConnectionPool();
        return instance;
    }
    public Connection getConnection(){
        Context ctx = null;
        DataSource dataSource = null;
        Connection c = null;
        try {
            ctx = new InitialContext();
            dataSource=(DataSource) ctx.lookup("java:comp/env/jdbc/notebookDB");
            c= dataSource.getConnection();
        } catch (NamingException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return c;

    }
}
