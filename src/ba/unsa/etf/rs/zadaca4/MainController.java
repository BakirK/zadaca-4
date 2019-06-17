package ba.unsa.etf.rs.zadaca4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class MainController {
    @FXML
    private Tab ownersTab, vehiclesTab;
    @FXML
    private TableView tableOwners, tableVehicles;
    @FXML
    private TableColumn tableOwnersId, tableOwnersName, tableOwnersJmbg, tableVehiclesId, tableVehiclesManufacturer,
            tableVehiclesModel, tableVehiclesChasisNumber, tableVehiclesPlateNumber;
    @FXML
    private Label searchVehicle, searchOwner;


    @FXML
    private void useSQL(ActionEvent actionEvent) {
    }

    @FXML
    private void useXML(ActionEvent actionEvent) {
    }

    @FXML
    private void addOwner(ActionEvent actionEvent) {
    }

    @FXML
    private void removeOwner(ActionEvent actionEvent) {
    }

    @FXML
    private void editOwner(ActionEvent actionEvent) {
    }

    @FXML
    private void addVehicle(ActionEvent actionEvent) {
    }

    @FXML
    private void removeVehicle(ActionEvent actionEvent) {
    }

    @FXML
    private void editVehicle(ActionEvent actionEvent) {
    }
}
