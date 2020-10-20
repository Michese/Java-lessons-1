package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class Controller {
    @FXML
    public TextField textLogin;
    @FXML
    public TextField textPassword;
    @FXML
    public TableView<Tuple> tableView;
    @FXML
    public TableColumn<Tuple, String> nameDoctorColumn;
    @FXML
    public TableColumn<Tuple, Integer> idDoctorColumn;
    @FXML
    public TableColumn<Tuple, String> namePatientColumn;
    @FXML
    public TableColumn<Tuple, Integer> analyzesPatientColumn;
    @FXML
    public Label textWelcome;

    private Model model;
    private Doctor currentDoctor;

    @FXML
    public void initialize() throws SQLException, ClassNotFoundException {
        model = new Model();
        currentDoctor = null;
        renderTuples(model.getAllTuple());
    }

    @FXML
    public void clickLogIn() throws SQLException {
        int idDoctor = model.authorization(textLogin.getText(), textPassword.getText());
        if (idDoctor != 0) {
            currentDoctor = model.getDoctorById(idDoctor);
            textWelcome.setText("Добро пожаловать,\n" + currentDoctor.getName() + "!");
            renderTuples(model.getTuplesByIdDoctor(idDoctor));
        }
    }

    public void clickLogOut() {
        currentDoctor = null;
        textWelcome.setText(null);
        renderTuples(model.getAllTuple());
    }

    private void renderTuples(ObservableList<Tuple> tuples) {
        nameDoctorColumn.setCellValueFactory(new PropertyValueFactory<Tuple, String>("nameDoctor"));
        namePatientColumn.setCellValueFactory(new PropertyValueFactory<Tuple, String>("namePatient"));
        analyzesPatientColumn.setCellValueFactory(new PropertyValueFactory<Tuple, Integer>("analyzes"));
        idDoctorColumn.setCellValueFactory(new PropertyValueFactory<Tuple, Integer>("id"));
        tableView.setItems(tuples);
    }

}
