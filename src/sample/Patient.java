package sample;

public class Patient {
    private int id;
    private String name;
    private int analyzes;
    public Patient(int id, String name, int analyzes) {
        this.id = id;
        this.name = name;
        this.analyzes = analyzes;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getAnalyzes() {
        return this.analyzes;
    }
}
