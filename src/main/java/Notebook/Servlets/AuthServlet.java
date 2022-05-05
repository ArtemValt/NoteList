package Notebook.Servlets;

import Notebook.Connect.ConnectionPool;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet(urlPatterns = "/auth")
public class AuthServlet extends HttpServlet {
    private final static Logger logger = Logger.getLogger(AuthServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        System.out.println("Post AUTH");
        HttpSession session = req.getSession();
        String userId = String.valueOf(session.getAttribute("userId"));

        try {
            String password = req.getParameter("password");
            String login = req.getParameter("username");
            if (!password.equals("") && !login.equals("")) {
                Connection conn = ConnectionPool.getInstance().getConnection();

                try {

                    Statement statement = conn.createStatement();
                    String sql = "SELECT * FROM USERS where USERNAME = " + "'" + login + "'AND PASSWORD = " + "'" + password + "'";

                    ResultSet resultSet = statement.executeQuery(sql);
                    while (resultSet.next()) {
                        int id = resultSet.getInt(1);
                        session.setAttribute("userId", String.valueOf(id));
                        userId = String.valueOf(id);
                    }
                    if (!userId.equals("null")) {
                        logger.info("user logged in\n");
                        resp.sendRedirect(req.getContextPath() + "/index");
                    }
                    else {
                        logger.info("user not logged in\n");
                        resp.sendRedirect(req.getContextPath() + "/regis.jsp");
                    }
                } catch (SQLException e) {
                    throw new SQLException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
