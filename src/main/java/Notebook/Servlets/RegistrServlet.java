package Notebook.Servlets;

import Notebook.Connect.ConnectionPool;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet(urlPatterns = "/registr")
public class RegistrServlet extends HttpServlet {
//    private static final Logger log = Logger.getLogger(RegistrServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        try {
            boolean compare = true;
            String password = req.getParameter("password");
            String login = req.getParameter("username");
            if (password != null && login != null) {
                Connection conn = ConnectionPool.getInstance().getConnection();
                Statement statement = conn.createStatement();
                ResultSet rs = statement.executeQuery("Select USERNAME from users ");
                while (rs.next()) {
                    String sentence = rs.getString(1);
                    if (sentence.equals(login)) {
                        compare = false;
                        break;
                    }
                }
                if (compare != true) {
                    resp.sendRedirect(req.getContextPath() + "/regis.jsp");
                } else {
                    statement.execute("INSERT INTO USERS (USERNAME, PASSWORD )  " +
                            "values('" + login +
                            "', '" + password + "');");
                    resp.sendRedirect(req.getContextPath() + "/login.jsp");
                }


            } else {
                req.setAttribute("textError", "Посмотрите корректность данных!");
                resp.sendRedirect(req.getContextPath() + "/regis.jsp");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
