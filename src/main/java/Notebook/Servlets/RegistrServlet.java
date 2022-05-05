package Notebook.Servlets;

import Notebook.Connect.ConnectionPool;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

import org.apache.log4j.Logger;


@WebServlet(urlPatterns = "/registr")
public class RegistrServlet extends HttpServlet {
    private final static Logger logger = Logger.getLogger(RegistrServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        try {
            logger.info("Log4j2ExampleApp started.");
            logger.info("Log4j2ExampleApp started.");
            logger.warn("Something to warn");
            logger.error("Something failed.");
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
                    logger.info("Пользователь не найден");
                    resp.sendRedirect(req.getContextPath() + "/regis.jsp");
                } else {
                    statement.execute("INSERT INTO USERS (USERNAME, PASSWORD )  " +
                            "values('" + login +
                            "', '" + password + "');");
                    logger.info("Пользователь зарегестрировался");

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
