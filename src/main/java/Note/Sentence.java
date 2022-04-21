package Note;

public class Sentence {
    private int id;
    private String sentence;
    private String date;
    private int importance;

    public Sentence(int id, String sentence, String date, int importance) {
        this.id = id;
        this.sentence = sentence;
        this.date = date;
        this.importance = importance;
    }

    public int getId() {
        return id;
    }

    public String getSentence() {
        return sentence;
    }

    public String getDate() {
        return date;
    }

    public int getImportance() {
        return importance;
    }
}

