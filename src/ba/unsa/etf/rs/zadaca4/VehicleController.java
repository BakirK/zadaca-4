package ba.unsa.etf.rs.zadaca4;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Comparator;

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

    private boolean modelCorrectInput = false, chasisCorrectInput = false, plateNameCorrectInput = false,
            manufacturerCorrectInput = false, ownerCorrectInput = false;
    VehicleDAO dao;
    Vehicle vehicle;

    public VehicleController(VehicleDAO dao, Vehicle vehicle) {
        this.dao = dao;
        this.vehicle = vehicle;
    }


    @FXML
    public void initialize() {
        ObservableList<Owner> owners = dao.getOwners();
        owners.sort(new Comparator<Owner>() {
            @Override
            public int compare(Owner o1, Owner o2) {
                return o1.toString().compareTo(o2.toString());
            }
        });
        ownerCombo.setItems(owners);
        ObservableList<Manufacturer> manufacturers = dao.getManufacturers();
        manufacturerCombo.setItems(manufacturers);

        if(vehicle == null) {
            modelField.setText("");
            modelField.getStyleClass().add("fieldIncorrect");

            chasisNumberField.setText("");
            chasisNumberField.getStyleClass().add("fieldIncorrect");

            plateNumberField.setText("");
            plateNumberField.getStyleClass().add("fieldIncorrect");

            manufacturerCombo.setValue("");
            manufacturerCombo.getStyleClass().add("fieldIncorrect");

            ownerCombo.setValue("");
            ownerCombo.getStyleClass().add("fieldIncorrect");
        } else {
            modelField.setText(vehicle.getModel());
            modelField.getStyleClass().add("fieldCorrect");
            modelCorrectInput = true;

            chasisNumberField.setText(vehicle.getChasisNumber());
            chasisNumberField.getStyleClass().add("fieldCorrect");
            chasisCorrectInput = true;

            plateNumberField.setText(vehicle.getPlateNumber());
            plateNumberField.getStyleClass().add("fieldCorrect");
            plateNameCorrectInput = true;

            manufacturerCombo.setValue(vehicle.getManufacturer());
            manufacturerCombo.getStyleClass().add("fieldCorrect");
            manufacturerCorrectInput = true;

            ownerCombo.setValue(vehicle.getOwner());
            ownerCombo.getStyleClass().add("fieldCorrect");
            ownerCorrectInput = true;
        }
    }

    @FXML
    private void validateInput(ActionEvent actionEvent) {
        Manufacturer m = checkManufacturer();
        boolean adding = false;
        if(vehicle == null) {
            vehicle = new Vehicle();
            adding = true;
        }
        if(m != null) {
            vehicle.setModel(modelField.getText());
            vehicle.setPlateNumber(plateNumberField.getText());
            vehicle.setChasisNumber(chasisNumberField.getText());
            vehicle.setManufacturer(m);
            vehicle.setOwner((Owner) ownerCombo.getValue());
            if(adding) {
                dao.addVehicle(vehicle);
            } else {
                dao.changeVehicle(vehicle);
            }
            zatvoriProzorPropuhJe(actionEvent);
        }
    }

    private Manufacturer checkManufacturer() {
        Manufacturer man = null;
        if(manufacturerCombo.getValue() == null) {
            return null;
        }
        String name = manufacturerCombo.getValue().toString().trim();
        for(Manufacturer m: dao.getManufacturers()) {
            if(m.getName().equals(name)) {
                man = m;
            }
        }
        if(man == null) {
            if(!name.trim().isEmpty()) {
                man = new Manufacturer(-1, name);
            }
        }
        return man;
    }

    @FXML
    private void zatvoriProzorPropuhJe(ActionEvent actionEvent) {
        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }
}
