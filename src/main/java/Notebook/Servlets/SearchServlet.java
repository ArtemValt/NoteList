package Notebook.Servlets;

import Notebook.Note.NoteToBd;
import Notebook.Note.Sentence;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    private final static Logger logger = Logger.getLogger(SearchServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String id = request.getParameter("id");
        String userdate = request.getParameter("date");
        String importance = request.getParameter("importance");
        String sentence = request.getParameter("sentence");
        HttpSession session = request.getSession();

        List<Sentence> list = null;

        if ("".equals(id))
            list = NoteToBd.search(sentence, Integer.parseInt(importance), userdate, Integer.parseInt(String.valueOf(session.getAttribute("userId"))));
        else {
            try {
                list = NoteToBd.searchid(Integer.parseInt(id), Integer.parseInt(String.valueOf(session.getAttribute("userId"))));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (list.isEmpty()) {
            logger.info("not search");
            response.sendRedirect(request.getContextPath() + "/notsearch.jsp");
        }
        logger.info("search completed");

        request.setAttribute("note", list);
        getServletContext().getRequestDispatcher("/searchall.jsp").forward(request, response);
    }
}







