import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Note.NoteToBd;
import Note.Sentence;



@WebServlet("/index")
public class NoteServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        NoteToBd noteToBd = null;
        try {
            noteToBd = new NoteToBd();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        List<Sentence> note = noteToBd.select();
        request.setAttribute("note", note);

        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }
}