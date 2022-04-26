import Note.NoteToBd;
import Note.Sentence;

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
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


@WebServlet("/index")
public class NoteServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        if (String.valueOf(session.getAttribute("userId")).equals("null"))
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        String userId = String.valueOf(session.getAttribute("userId"));

        NoteToBd noteToBd = null;
        try {
            noteToBd = new NoteToBd();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        List<Sentence> note = noteToBd.select(Integer.parseInt(userId));
        List<Sentence> remember = new LinkedList<>();

        for (Sentence a : note) {
            Calendar c1 = Calendar.getInstance();
            Calendar c2 = Calendar.getInstance();
            Date date1 = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            try {
                Date date2 = formatter.parse(a.getDateСompletion());

                c1.setTime(date1);
                c2.setTime(date2);
                int yearDiff = c1.get(Calendar.YEAR) - c2.get(Calendar.YEAR);
                int monthDiff = c1.get(Calendar.MONTH) - c2.get(Calendar.MONTH);
                int dayDiff = c2.get(Calendar.DAY_OF_MONTH) - c1.get(Calendar.DAY_OF_MONTH);
                int minDif = c2.get(Calendar.MINUTE) - c1.get(Calendar.MINUTE);
                int hoursdif = c2.get(Calendar.HOUR) - c1.get(Calendar.HOUR);
                if (yearDiff == 0 && monthDiff == 0 && dayDiff == 0) {
                    if (Math.abs(hoursdif) == 1 || hoursdif == 0)
                        remember.add(a);
                }


            } catch (ParseException e) {
                throw new RuntimeException(e);
            }


        }
        request.setAttribute("note", note);
        request.setAttribute("remember", remember);

        request.getRequestDispatcher("/index.jsp").forward(request, response);
        System.out.println("Get");


    }


    @Override
    public void destroy() {
        System.out.println("destr");
    }


}