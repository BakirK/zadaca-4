package ba.unsa.etf.rs.zadaca4;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainController {
    @FXML
    private Tab ownersTab, vehiclesTab;
    @FXML
    private TableView tableOwners, tableVehicles;
    @FXML
    private TableColumn tableOwnersId, tableOwnersJmbg;
    private TableColumn<Owner, String> tableOwnersName;
    private TableColumn tableVehiclesId, tableVehiclesManufacturer,
            tableVehiclesModel, tableVehiclesChasisNumber, tableVehiclesPlateNumber;
    @FXML
    private Label searchVehicle, searchOwner;
    private VehicleDAO dao = null;

    @FXML
    public void initialize() {
        dao = new VehicleDAOBase();
        tableOwnersId.setCellValueFactory(new PropertyValueFactory<Owner, Integer>("id"));
        //tableOwnersName.setCellValueFactory(cellData -> cellData.getValue().theNameProperty());
        //tableOwnersName.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getTheName()));
        //tableOwnersName.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName().concat(" ").concat(data.getValue().getSurname())));

        tableOwnersJmbg.setCellValueFactory(new PropertyValueFactory("jmbg"));
        tableOwners.setItems(dao.getOwners());
    }


    @FXML
    private void useSQL(ActionEvent actionEvent) {
        if (dao != null) {
            dao.close();
        }
        dao = new VehicleDAOBase();
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
