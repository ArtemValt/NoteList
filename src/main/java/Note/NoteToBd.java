package Note;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class NoteToBd extends Table {
    public NoteToBd() throws SQLException {
    }


    public static List<Sentence> node = new LinkedList<>();

    public static List<Sentence> select(int userID) {
        node.clear();
        try {
            ResultSet rs = st.executeQuery("SELECT * FROM Node WHERE USERS_ID = " + userID +
                    " ORDER BY importance DESC, CreatedWhen DESC;");

            while (rs.next()) {
                int id = rs.getInt(1);
                String sentence = rs.getString(2);
                String createdWhen = rs.getString(3);
                String complDate = rs.getString(4);
                int important = (rs.getInt(5));
                int user_id = rs.getInt(6);
                Sentence sentence1 = new Sentence(id, sentence, createdWhen,
                        complDate, important, user_id);
                node.add(sentence1);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return node;

    }

    public static Sentence selectOne(long id, long userID) {
        Sentence sentence = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Node WHERE ID = ? AND USERS_ID = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.setLong(2, userID);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                int i = rs.getInt(1);
                String sentenc = rs.getString(2);
                String createdWhen = rs.getString(3);
                String complDate = rs.getString(4);
                int important = (rs.getInt(5));
                int user_id = rs.getInt(6);
                sentence = new Sentence(i, sentenc, createdWhen,
                        complDate, important, user_id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sentence;
    }

    public static void insert(Sentence sentence) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO Node " +
                            "(Sentence, CreatedWhen, ComplDate, importance, USERS_ID)" +
                            "VALUES ( ?, ?, ?, ?, ?);");

            preparedStatement.setString(1, sentence.getSentence());
            preparedStatement.setString(2, formatter.format(calendar.getTime()).toString());
            preparedStatement.setString(3, sentence.getDateСompletion());
            preparedStatement.setString(4, String.valueOf(sentence.getImportance()));
            preparedStatement.setLong(5, sentence.getUser_id());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void update(Sentence sentence) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        try {
            String sql = "UPDATE NODE SET Sentence = ?, CreatedWhen = ? ,ComplDate=?, Importance = ? WHERE id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, sentence.getSentence());
            preparedStatement.setString(2, formatter.format(calendar.getTime()).toString());
            preparedStatement.setInt(4, sentence.getImportance());
            preparedStatement.setString(3, sentence.getDateСompletion());
            preparedStatement.setInt(5, sentence.getId());


            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void delete(long id, long userID) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM Node WHERE ID = ? AND USERS_ID = ?;");
            preparedStatement.setLong(1, id);
            preparedStatement.setLong(2, userID);
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Sentence> searchid(int i, int id) throws SQLException {

        List<Sentence> l = select(id).stream().
                filter(x -> x.getId() == i).
                collect(Collectors.toList());
        return l;
    }

    public static List<Sentence> search(String name, int imp, String date, int id) {
        List<Sentence> l = select(id).stream().
                filter(x -> x.getSentence().contains(name) && x.getImportance() == imp && x.getDateСompletion().contains(date)).
                collect(Collectors.toList());
        return l;

    }

}
