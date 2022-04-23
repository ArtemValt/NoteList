package Note;

public class Sentence {
    private int id;
    private String sentence;
    private String createdDate;

    private String dateСompletion;

    private int importance;

    public Sentence(int id, String sentence, String createdDate, String dateСompletion, int importance) {
        this.id = id;
        this.sentence = sentence;
        this.createdDate = createdDate;
        this.dateСompletion = dateСompletion;
        this.importance = importance;
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
}