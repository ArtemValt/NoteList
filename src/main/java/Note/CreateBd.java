package Note;

import java.sql.SQLException;

public class CreateBd extends Table{

    public CreateBd() throws SQLException {
    }

    public void createTable() throws SQLException {
        st.execute("CREATE TABLE IF NOT EXISTS Users(" +
                    "ID BIGINT PRIMARY KEY AUTO_INCREMENT," +
                    "USERNAME VARCHAR(255) NOT NULL," +
                    "PASSWORD VARCHAR(255) NOT NULL);" +
                    "");
        st.execute("CREATE TABLE IF NOT EXISTS Node(" +
                "id BIGINT PRIMARY KEY AUTO_INCREMENT," +
                "Sentence VARCHAR(255) NOT NULL ," +
                "CreatedWhen VARCHAR(255) NOT NULL," +
                "ComplDate VARCHAR(255) NOT NULL," +
                "importance INT NOT NULL," +
                "USERS_ID BIGINT," +
                "FOREIGN KEY (USERS_ID) REFERENCES Users(ID))");
    }
}
