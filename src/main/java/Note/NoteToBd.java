package Note;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class NoteToBd extends Table{
    public NoteToBd() throws SQLException {
    }public void createTable() throws SQLException {
        st.execute("CREATE TABLE IF NOT EXISTS Node(" +
                "id BIGINT PRIMARY KEY AUTO_INCREMENT," +
                "Sentence VARCHAR(255) NOT NULL ," +
                "Date VARCHAR(255) NOT NULL," +
                "importance INT NOT NULL )");
    }

    public static List<Sentence> node = new LinkedList<>();

    public static List<Sentence> select() {

        try{
            String sql ="SELECT * FROM Node";
            ResultSet resultSet = st.executeQuery(sql);
            while(resultSet.next()){
                int id = resultSet.getInt(1);
                String sentense = resultSet.getString(2);
                String date = resultSet.getString(3);
                int importance = resultSet.getInt(4);
                node.add(new Sentence(id,sentense, date, importance));
            }

        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return node;
    }

    public static void insert(Sentence sentence) throws SQLException {
        String sql = "INSERT INTO NODE(Sentence,date,importance) VALUES(" + "'" + sentence.getSentence() + "'," + "'" + String.valueOf(new Date()) + "',"
                + "'" + sentence.getImportance() + "'" + ")";
        st.executeUpdate(sql);

    }

    public static List<Sentence> selectOne(int id) {

        node = null;
        try {

            String sql = "SELECT id, Sentence, Date , importance  FROM Node where =" + id;
            ResultSet resultSet = st.executeQuery(sql);
            if (resultSet.next()) {
                int i = resultSet.getInt(1);
                String sentense = resultSet.getString(2);
                String date = resultSet.getString(3);
                int importance = resultSet.getInt(4);
                node.add(new Sentence(i,sentense, date, importance));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return node;
    }


    public void removecell(int x) throws SQLException {
        st.executeUpdate("DELETE FROM Node WHERE id = " + x);

    }
}
