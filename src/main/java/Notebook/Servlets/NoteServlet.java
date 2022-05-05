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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


@WebServlet("/index")
public class NoteServlet extends HttpServlet {
    private  final static Logger logger = Logger.getLogger(NoteServlet.class);
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String userId = String.valueOf(session.getAttribute("userId"));
        if ("".equals(userId))
            response.sendRedirect(request.getContextPath() + "/login.jsp");

        NoteToBd noteToBd = null;
        try {
            noteToBd = new NoteToBd();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        List<Sentence> note = noteToBd.select(Integer.parseInt(userId));
        List<Sentence> remember = new LinkedList<>();

        for (Sentence a : note) {
            Date date1 = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            try {
                Date dateCompl = formatter.parse(a.getDateСompletion());
                long minutes = (dateCompl.getTime() - date1.getTime()) / 60000;
                if (minutes <= 60) {
                    logger.info("added entry to reminders\n");
                    remember.add(a);
                }
            } catch (ParseException e) {
            }
        }
        logger.info("showing inscriptions\n");
            request.setAttribute("note", note);
            request.setAttribute("remember", remember);

            request.getRequestDispatcher("/index.jsp").forward(request, response);
            System.out.println("Get");



    }

    @Override
    public void destroy() {
        logger.info("work stopped\n");
    }
}