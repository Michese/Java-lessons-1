package sample;

import java.util.ArrayList;

public class Doctor {
    private String name;
    private int id;
    public ArrayList<Patient> patients = new ArrayList<>();

    Doctor(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    public int getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
}
