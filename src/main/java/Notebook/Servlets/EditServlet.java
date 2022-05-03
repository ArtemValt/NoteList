package Notebook.Servlets;

import Notebook.Note.NoteToBd;
import Notebook.Note.Sentence;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/edit")
public class EditServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        try {
            int id = Integer.parseInt(request.getParameter("id"));
            HttpSession session = request.getSession();
            String userId = String.valueOf(session.getAttribute("userId"));
            Sentence sentence = NoteToBd.selectOne(id, Long.parseLong(userId));
            if (sentence != null) {
                request.setAttribute("note", sentence);
                getServletContext().getRequestDispatcher("/edit.jsp").forward(request, response);
            } else {
                getServletContext().getRequestDispatcher("/notfound.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            getServletContext().getRequestDispatcher("/notfound.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String date = (request.getParameter("date"));
            int importance = Integer.parseInt(request.getParameter("importance"));
            HttpSession session = request.getSession();
            String userId = String.valueOf(session.getAttribute("userId"));
            Sentence sentence = new Sentence(id, name, new String(""), date, importance, Integer.parseInt(userId));
            NoteToBd.update(sentence);
            response.sendRedirect(request.getContextPath() + "/index");
        } catch (Exception ex) {

            getServletContext().getRequestDispatcher("/notfound.jsp").forward(request, response);
        }
    }
}
