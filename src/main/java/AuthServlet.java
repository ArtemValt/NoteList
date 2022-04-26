
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;

@WebServlet(urlPatterns = "/auth")
public class AuthServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("userId").equals(null))
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        System.out.println("Post AUTH");
        HttpSession session = req.getSession();
        String userId = (String) session.getAttribute("userId");

        try {
            String password = req.getParameter("password");
            String login = req.getParameter("username");
            if (!password.equals("") && !login.equals("")) {
                Class.forName("org.h2.Driver").getDeclaredConstructor().newInstance();
                Connection conn = null;
                try {
                    conn = DriverManager.getConnection("jdbc:h2:C:/Users/1/IdeaProjects/education/BAZA/baz;AUTO_SERVER=TRUE", "qwerty", "qwerty");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                try {

                    Statement statement = conn.createStatement();
                    String sql = "SELECT * FROM USERS where USERNAME = " + "'" + login + "'AND PASSWORD = " + "'" + password + "'";

                    ResultSet resultSet = statement.executeQuery(sql);
                    while (resultSet.next()) {
                        int id = resultSet.getInt(1);
                        session.setAttribute("userId", String.valueOf(id));
                    }
                    if (!session.getAttribute("userId").equals(null))
                        resp.sendRedirect(req.getContextPath() + "/index");
                    else {
                        req.setAttribute("textError", "Посмотрите корректность данных!");
                        resp.sendRedirect(req.getContextPath() + "/regis.jsp");
                    }
                } catch (SQLException e) {
                    req.setAttribute("textError", "Посмотрите корректность данных!");
                    resp.sendRedirect(req.getContextPath() + "/regis.jsp");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            else {
                req.setAttribute("textError", "Посмотрите корректность данных!");
                resp.sendRedirect(req.getContextPath() + "/regis.jsp");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
