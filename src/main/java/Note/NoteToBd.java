package Note;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class NoteToBd extends Table {
    public NoteToBd() throws SQLException {
    }

    public void createTable() throws SQLException {
        st.execute("CREATE TABLE IF NOT EXISTS Node(" +
                "id BIGINT PRIMARY KEY AUTO_INCREMENT," +
                "Sentence VARCHAR(255) NOT NULL ," +
                "Date VARCHAR(255) NOT NULL," +
                "ComplDate VARCHAR(255) NOT NULL," +
                "importance INT NOT NULL )");
    }

    public static List<Sentence> node = new LinkedList<>();

    public static List<Sentence> select() {

        try {
            String sql = "SELECT * FROM NODE";
            ResultSet resultSet = st.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String sentense = resultSet.getString(2);
                String date = resultSet.getString(3);
                String complDate = resultSet.getString(4);
                int importance = resultSet.getInt(5);
                node.add(new Sentence(id, sentense, date, complDate, importance));
            }
            resultSet.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return node;
    }

    public static void insert(Sentence sentence) throws SQLException {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String text = date.format(formatter);

        String sql = "INSERT INTO NODE(Sentence,date,ComplDate,importance) VALUES(" + "'" + sentence.getSentence()
                + "'," + "'" + text + "'," + "'" + sentence.getDateСompletion()
                + "','" + sentence.getImportance() + "'" + ")";
        st.executeUpdate(sql);

    }

    public static Sentence selectOne(int id) {

        Sentence sentence = null;
        try {

            String sql = "SELECT * FROM NODE where id = " + id;
            ResultSet resultSet = st.executeQuery(sql);
            if (resultSet.next()) {
                String sentense = resultSet.getString(2);
                String date = resultSet.getString(3);
                String complDate = resultSet.getString(4);
                int importance = resultSet.getInt(5);
                sentence = new Sentence(id, sentense, date, complDate, importance);
            }
            resultSet.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return sentence;
    }

    public static int update(Sentence sentence) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        try {
            Class.forName("org.h2.Driver").getDeclaredConstructor().newInstance();

            Connection conn = DriverManager.getConnection("jdbc:h2:C:/Users/1/IdeaProjects/education/BAZA/baz;AUTO_SERVER=TRUE", "qwerty", "qwerty");
            String sql = "UPDATE NODE SET Sentence = ?, Date = ? ,ComplDate=?, Importance = ? WHERE id = ?";
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setString(1, sentence.getSentence());
                preparedStatement.setString(2, sentence.getCreatedDate());
                preparedStatement.setInt(4, sentence.getImportance());
                preparedStatement.setString(3,sentence.getDateСompletion());
                preparedStatement.setInt(5, sentence.getId());

                return preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int delete(int id) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        try {
            Class.forName("org.h2.Driver").getDeclaredConstructor().newInstance();
            Connection conn = null;
            try {
                conn = DriverManager.getConnection("jdbc:h2:C:/Users/1/IdeaProjects/education/BAZA/baz;AUTO_SERVER=TRUE", "qwerty", "qwerty");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            String sql = "DELETE FROM NODE WHERE id = ?";
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setInt(1, id);

                return preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return 0;
    }

    public static List<Sentence> searchid(int i) throws SQLException {

        List<Sentence> l = select().stream().
                filter(x -> x.getId() == i).
                collect(Collectors.toList());
        return l;
    }

    public static List<Sentence> search(String name, int imp, String date) {
        List<Sentence> l = select().stream().
                filter(x -> x.getSentence().contains(name) && x.getImportance() == imp && x.getCreatedDate().contains(date)).
                collect(Collectors.toList());
        return l;

    }

}
