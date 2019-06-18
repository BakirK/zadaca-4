package ba.unsa.etf.rs.zadaca4;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDate;

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
    private Owner owner;
    private VehicleDAO dao;

    public OwnerController(VehicleDAO dao, Owner owner) {
        this.owner = owner;
        this.dao = dao;
    }


    @FXML
    public void initialize() {
        ObservableList<Place > places = dao.getPlaces();
        placeOfBirth.setItems(places);
        addressPlace.setItems(places);

        if (owner == null) {
            nameField.setText("");
            nameField.getStyleClass().add("fieldIncorrect");

            surnameField.setText("");
            surnameField.getStyleClass().add("fieldIncorrect");

            parentNameField.setText("");
            parentNameField.getStyleClass().add("fieldIncorrect");

            addressField.setText("");
            addressField.getStyleClass().add("fieldIncorrect");

            jmbgField.setText("");
            jmbgField.getStyleClass().add("fieldCorrect");

            dateField.setValue(LocalDate.now());
            dateField.getEditor().getStyleClass().add("fieldIncorrect");

            placeOfBirth.setValue("");
            placeOfBirth.getEditor().getStyleClass().add("fieldIncorrect");

            addressPlace.setValue("");
            addressPlace.getEditor().getStyleClass().add("fieldIncorrect");

            postalNumberField.setText("");
            postalNumberField.getStyleClass().add("fieldIncorrect");
        } else {
            nameField.setText(owner.getName());
            nameField.getStyleClass().add("fieldCorrect");

            surnameField.setText(owner.getSurname());
            surnameField.getStyleClass().add("fieldCorrect");

            parentNameField.setText(owner.getParentName());
            parentNameField.getStyleClass().add("fieldCorrect");

            addressField.setText(owner.getLivingAddress());
            addressField.getStyleClass().add("fieldCorrect");

            jmbgField.setText(owner.getJmbg());
            jmbgField.getStyleClass().add("fieldCorrect");

            dateField.setValue(owner.getDateOfBirth());
            dateField.getStyleClass().add("fieldCorrect");

            placeOfBirth.setValue(owner.getPlaceOfBirth().getName());
            placeOfBirth.getStyleClass().add("fieldCorrect");

            addressPlace.setValue(owner.getLivingPlace().getName());
            addressPlace.getStyleClass().add("fieldCorrect");

            postalNumberField.setText(owner.getLivingPlace().getPostalNumber());
            postalNumberField.getStyleClass().add("fieldCorrect");

        }
    }

    @FXML
    private void validateInput(ActionEvent actionEvent) {
    }

    @FXML
    private void zatvoriProzorPropuhJe(ActionEvent actionEvent) {
    }
}
