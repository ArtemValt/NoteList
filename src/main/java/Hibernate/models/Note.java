package Hibernate.models;

import javax.persistence.*;

@Entity
@Table(name = "node")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "sentence")
    private String sentence;

    //можно не указывать Column name, если оно совпадает с названием столбца в таблице
    private String CREATEDWHEN;
    private String COMPLDATE;
    private String IMPORTANCE;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USERS_ID")
    User user;

    public Note(String sentence, String CREATEDWHEN, String COMPLDATE, String IMPORTANCE, User users) {
        this.sentence = sentence;
        this.CREATEDWHEN = CREATEDWHEN;
        this.COMPLDATE = COMPLDATE;
        this.IMPORTANCE = IMPORTANCE;
        this.user = users;
    }

    public Note() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String model) {
        this.sentence = model;
    }

    public String getCREATEDWHEN() {
        return CREATEDWHEN;
    }

    public void setCREATEDWHEN(String CREATEDWHEN) {
        this.CREATEDWHEN = CREATEDWHEN;
    }

    public String getCOMPLDATE() {
        return COMPLDATE;
    }

    public void setCOMPLDATE(String COMPLDATE) {
        this.COMPLDATE = COMPLDATE;
    }

    public String getIMPORTANCE() {
        return IMPORTANCE;
    }

    public void setIMPORTANCE(String IMPORTANCE) {
        this.IMPORTANCE = IMPORTANCE;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User users) {
        this.user = users;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", sentence='" + sentence + '\'' +
                ", CREATEDWHEN='" + CREATEDWHEN + '\'' +
                ", COMPLDATE='" + COMPLDATE + '\'' +
                ", IMPORTANCE='" + IMPORTANCE + '\'' +
                '}';
    }

}