package Notebook.Note;

public class Sentence {
    private int id;
    private String sentence;
    private String createdDate;

    private String dateСompletion;

    private int importance;

    private int user_id;

    public Sentence(int id, String sentence, String createdDate, String dateСompletion, int importance, int user_id) {
        this.id = id;
        this.sentence = sentence;
        this.createdDate = createdDate;
        this.dateСompletion = dateСompletion;
        this.importance = importance;
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public String getSentence() {
        return sentence;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public String getDateСompletion() {
        return dateСompletion;
    }

    public int getImportance() {
        return importance;
    }

    public int getUser_id() {
        return user_id;
    }
}