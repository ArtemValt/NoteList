package Notebook.Servlets;

import Notebook.Note.NoteToBd;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
    private final static Logger logger = Logger.getLogger(DeleteServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            HttpSession session = request.getSession();
            String userId = String.valueOf(session.getAttribute("userId"));
            NoteToBd.delete(id, Long.parseLong(userId));
            response.sendRedirect(request.getContextPath() + "/index");
            logger.info("Запись удалена");
        } catch (Exception ex) {
            getServletContext().getRequestDispatcher("/notfound.jsp").forward(request, response);
        }
    }
}