package Hibernate.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "USERNAME")
    private String name;
    //можно не указывать Column name, если оно совпадает с названием столбца в таблице
    private String password;

//    @OneToMany(fetch = FetchType.EAGER)
//    @JoinColumn(name = "user_id")
//    private List<Notebook.Hibernate.models.Note> notes;



    public void addNote(Note note){
        note.setUser(this);
    }
    public User() {

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public User(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
//                ", notes=" + notes +
                '}';
    }
}