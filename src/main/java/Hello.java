import Note.NoteToBd;
import Note.Sentence;

import java.io.PrintWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hello")
public class Hello extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

//        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();

        String username = request.getParameter("username");
        String userdate = request.getParameter("userdate");
        String importance = request.getParameter("importance");
        try {
            NoteToBd noteToBd = new NoteToBd();
            noteToBd.insert(new Sentence(1,username,userdate,Integer.parseInt(importance)));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            writer.println("<p>Note: " + username + "</p>");
            writer.println("<p>Date: " + userdate + "</p>");
            writer.println("<p>importance: " + importance + "</p>");
        } finally {
            writer.close();
        }
    }
}