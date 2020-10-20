package sample;

public class Tuple {
    private String nameDoctor;
    private String namePatient;
    private int analyzes;
    private int id;

    Tuple(int id, String nameDoctor, String namePatient, int analyzes) {
        this.id = id;
        this.nameDoctor = nameDoctor;
        this.namePatient = namePatient;
        this.analyzes = analyzes;
    }

    public String getNameDoctor() {
        return this.nameDoctor;
    }

    public String getNamePatient() {
        return this.namePatient;
    }

    public int getAnalyzes() {
        return this.analyzes;
    }

    public int getId() {
        return this.id;
    }
}
