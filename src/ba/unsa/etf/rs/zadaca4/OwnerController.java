package ba.unsa.etf.rs.zadaca4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class OwnerController {
    @FXML
    private TextField nameField;
    @FXML
    private TextField surnameField;
    @FXML
    private TextField parentNameField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField jmbgField;
    @FXML
    private DatePicker dateField;
    @FXML
    private ComboBox placeOfBirth;
    @FXML
    private ComboBox addressPlace;
    @FXML
    private TextField postalNumberField;

    @FXML
    private void validateInput(ActionEvent actionEvent) {
    }

    @FXML
    private void zatvoriProzorPropuhJe(ActionEvent actionEvent) {
    }
}
