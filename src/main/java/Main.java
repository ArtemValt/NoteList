import Note.NoteToBd;
import Note.Sentence;

import java.sql.Date;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        NoteToBd noteToBd = new NoteToBd();
        noteToBd.removecell(1);
        noteToBd.insert(new Sentence(1,"22","21/04",1));
    }
}
