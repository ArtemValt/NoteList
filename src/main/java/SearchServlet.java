import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String id = request.getParameter("id");
        String userdate = request.getParameter("userdate");
        String importance = request.getParameter("importance");
        String sentence = request.getParameter("sentence");
        List<Sentence> list = null;

        if (id.equals(""))
            list = NoteToBd.search(sentence, Integer.parseInt(importance), userdate);
        else {
            try {
                list = NoteToBd.searchid(Integer.parseInt(id));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        request.setAttribute("note", list);
        getServletContext().getRequestDispatcher("/searchall.jsp").forward(request, response);
    }
}







