import Note.NoteToBd;
import Note.Sentence;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/create")
public class AddNewNote extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/create.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            response.setContentType("text/html;charset=UTF-8");
            HttpSession session = request.getSession();

            String name = request.getParameter("sentence");
            String date = (request.getParameter("date"));
            int importance = Integer.parseInt(request.getParameter("importance"));
            String userId = String.valueOf(session.getAttribute("userId"));
            Sentence sentence = new Sentence(1, name, new String("") ,date, importance,Integer.parseInt(userId));
            NoteToBd.insert(sentence);

            response.sendRedirect(request.getContextPath() + "/index");
        } catch (Exception ex) {

            getServletContext().getRequestDispatcher("/create.jsp").forward(request, response);
        }
    }
}

