
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet(urlPatterns = "/registr")
public class RegistrServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        try {
            String password = req.getParameter("password");
            String login = req.getParameter("username");
            if (password != null && login != null) {
                Class.forName("org.h2.Driver").getDeclaredConstructor().newInstance();
                Connection conn = null;
                try {
                    conn = DriverManager.getConnection("jdbc:h2:C:/Users/1/IdeaProjects/education/BAZA/baz;AUTO_SERVER=TRUE", "qwerty", "qwerty");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                Statement statement = conn.createStatement();
                statement.execute("INSERT INTO USERS (USERNAME, PASSWORD )  " +
                        "values('" + login +
                        "', '" + password + "');");
                req.setAttribute("textError", "Поздравляю, вы зарегистрировали аккаунт!");
                req.getRequestDispatcher("/index.jsp").forward(req, resp);
            } else {
                req.setAttribute("textError", "Посмотрите корректность данных!");
                resp.sendRedirect(req.getContextPath() + "/regis.jsp");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
