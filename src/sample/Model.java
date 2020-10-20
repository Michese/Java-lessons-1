package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Model {
    private DB db;
    private ArrayList<Doctor> doctors = new ArrayList<>();

    Model() throws SQLException, ClassNotFoundException {
        this.db = DB.getInstance();
        this.setAllDoctors();
        this.addAllPatients();
        System.out.println("great new model");
    }

    public void setAllDoctors() throws SQLException {
        String sql = "select doctor.id as id_doctor, doctor.name as name_doctor from doctor";
        try {
            ResultSet rs = this.db.executeQuery(sql);
            while (rs.next()) {
                doctors.add(new Doctor(rs.getInt("id_doctor"), rs.getString("name_doctor")));
            }
            System.out.println("great setAllDoctors");
        } catch (SQLException exp) {
            System.err.println(exp.getMessage());
        } finally {
            this.db.close();
        }
    }

    public ObservableList<Tuple> getAllTuple() {
        ObservableList<Tuple> result = FXCollections.observableArrayList();
        int count = 1;
        for (Doctor doctor : doctors) {
            if (doctor.patients.isEmpty()) {
                result.add(new Tuple(count++, doctor.getName(), "", 0));
            } else {
                for (Patient patient : doctor.patients) {
                    result.add(new Tuple(count++, doctor.getName(), patient.getName(), patient.getAnalyzes()));
                }
            }
        }
        System.out.println("great getAllTuple");
        return result;
    }

    public Doctor getDoctorById(int id) {
        return doctors.stream().filter(doctor -> (doctor.getId() == id)).findFirst().orElse(null);
    }

    public void addAllPatients() throws SQLException {
        String sql = "select `registration`.`id_doctor` as id_doctor, `registration`.`id_patient` as id_patient, " +
                "`patient`.`name` as name_patient, `patient`.analyzes as analyzes " +
                "from `registration` " +
                "join `patient` on `registration`.`id_patient`=`patient`.`id` " +
                "order by `registration`.`id_doctor`";
        try {
            ResultSet rs = this.db.executeQuery(sql);
            while (rs.next()) {
                Patient patient = new Patient(rs.getInt("id_patient"), rs.getString("name_patient"), rs.getInt("analyzes"));
                int idDoctor = rs.getInt("id_doctor");
                for (Doctor doctor : doctors) {
                    if (idDoctor == doctor.getId()) {
                        doctor.addPatient(patient);
                        break;
                    }
                }
            }
            System.out.println("great addAllPatients");
        } catch (SQLException exp) {
            System.err.println(exp.getMessage());
        } finally {
            this.db.close();
        }
    }

    public String get_SHA_512_SecurePassword(String passwordToHash, String salt) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    public int authorization(String login, String password) throws SQLException {
        int idDoctor = 0;
        String sql = "select doctor.id as id_doctor, doctor.password as password_doctor " +
                "from doctor where doctor.login = \'" + login + "\'";
        try {
            ResultSet rs = this.db.executeQuery(sql);
            if(rs.next()) {
                if (rs.getString("password_doctor").equals(get_SHA_512_SecurePassword(password, login))) {
                    idDoctor = rs.getInt("id_doctor");
                } else {
                    System.out.println("Неверный пароль!");
                }
            }
            else {
                System.out.println("Неверный логин!");
            }
        } catch (SQLException exp) {
            System.err.println(exp.getMessage());
        } finally {
            this.db.close();
        }
        return idDoctor;
    }

    public ObservableList<Tuple> getTuplesByIdDoctor(int idDoctor) {
        ObservableList<Tuple> result = FXCollections.observableArrayList();
        int count = 1;
        Doctor doctor = getDoctorById(idDoctor);
        for (Patient patient : doctor.patients) {
            result.add(new Tuple(count++, doctor.getName(), patient.getName(), patient.getAnalyzes()));
        }
        return result;
    }
}
