package ba.unsa.etf.rs.zadaca4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class VehicleController {
    @FXML
    private TextField modelField;
    @FXML
    private TextField chasisNumberField;
    @FXML
    private TextField plateNumberField;
    @FXML
    private ComboBox manufacturerCombo;
    @FXML
    private ComboBox ownerCombo;

    public VehicleController(VehicleDAO dao, Vehicle v) {
    }

    @FXML
    private void validateInput(ActionEvent actionEvent) {
    }

    @FXML
    private void zatvoriProzorPropuhJe(ActionEvent actionEvent) {
    }
}
