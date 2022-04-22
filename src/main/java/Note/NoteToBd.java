package Note;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class NoteToBd extends Table {
    public NoteToBd() throws SQLException {
    }

    public void createTable() throws SQLException {
        st.execute("CREATE TABLE IF NOT EXISTS Node(" +
                "id BIGINT PRIMARY KEY AUTO_INCREMENT," +
                "Sentence VARCHAR(255) NOT NULL ," +
                "Date VARCHAR(255) NOT NULL," +
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
                int importance = resultSet.getInt(4);
                node.add(new Sentence(id, sentense, date, importance));
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return node;
    }

    public static void insert(Sentence sentence) throws SQLException {
        String sql = "INSERT INTO NODE(Sentence,date,importance) VALUES(" + "'" + sentence.getSentence() + "'," + "'" + String.valueOf(new Date()) + "',"
                + "'" + sentence.getImportance() + "'" + ")";
        st.executeUpdate(sql);

    }

    public static Sentence selectOne(int id) {

        Sentence sentence = null;
        try {

            String sql = "SELECT * FROM NODE where id = " + id;
            ResultSet resultSet = st.executeQuery(sql);
            if (resultSet.next()) {
                int i = resultSet.getInt(1);
                String sentense = resultSet.getString(2);
                String date = resultSet.getString(3);
                int importance = resultSet.getInt(4);
                sentence = new Sentence(i, sentense, date, importance);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return sentence;
    }

    public static int update(Sentence sentence) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        try {
            Class.forName("org.h2.Driver").getDeclaredConstructor().newInstance();

            Connection conn = DriverManager.getConnection("jdbc:h2:C:/Users/1/IdeaProjects/education/BAZA/baz;AUTO_SERVER=TRUE", "qwerty", "qwerty");
            String sql = "UPDATE NODE SET Sentence = ?, Date = ? , Importance = ? WHERE id = ?";
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setString(1, sentence.getSentence());
                preparedStatement.setString(2, sentence.getDate());
                preparedStatement.setInt(3, sentence.getImportance());
                preparedStatement.setInt(4, sentence.getId());

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

}
