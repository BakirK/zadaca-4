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
    @FXML
    private TableColumn<Owner, String> tableOwnersName;
    @FXML
    private TableColumn tableVehiclesId, tableVehiclesManufacturer,
            tableVehiclesModel, tableVehiclesChasisNumber, tableVehiclesPlateNumber;
    @FXML
    private Label searchVehicle, searchOwner;
    private VehicleDAO dao = null;

    @FXML
    public void initialize() {
        dao = new VehicleDAOBase();
        tableOwnersId.setCellValueFactory(new PropertyValueFactory<Owner, Integer>("id"));
        tableOwnersName.setCellValueFactory(cellData -> cellData.getValue().theNameProperty());
        //tableOwnersName.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getTheName()));
        //tableOwnersName.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName().concat(" ").concat(data.getValue().getSurname())));
        tableOwnersJmbg.setCellValueFactory(new PropertyValueFactory("jmbg"));
        tableOwners.setItems(dao.getOwners());

        tableVehiclesId.setCellValueFactory(new PropertyValueFactory<Vehicle, Integer>("id"));
        tableVehiclesModel.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("model"));
        tableVehiclesManufacturer.setCellValueFactory(new PropertyValueFactory<Vehicle, Integer>("manufacturer"));
        tableVehiclesChasisNumber.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("chasisNumber"));
        tableVehiclesPlateNumber.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("plateNumber"));
        tableVehicles.setItems(dao.getVehicles());
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
        if (dao != null) {
            dao.close();
        }
        dao = new VehicleDAOXML();
    }

    @FXML
    private void addOwner(ActionEvent actionEvent) {

    }

    @FXML
    private void removeOwner(ActionEvent actionEvent) {
        if (tableOwners.getSelectionModel().getSelectedItem() != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete selected owner?", ButtonType.OK, ButtonType.NO, ButtonType.CANCEL);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.OK) {
                dao.deleteOwner((Owner) tableOwners.getSelectionModel().getSelectedItem());
                updateTableView();
                //tableOwners.getSelectionModel().selectFirst();
            }
        }
    }

        @FXML
        private void editOwner (ActionEvent actionEvent){
        }

        @FXML
        private void addVehicle (ActionEvent actionEvent){
        }

        @FXML
        private void removeVehicle (ActionEvent actionEvent){
            if (tableVehicles.getSelectionModel().getSelectedItem() != null) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete selected vehicle?", ButtonType.OK, ButtonType.NO, ButtonType.CANCEL);
                alert.showAndWait();

                if (alert.getResult() == ButtonType.OK) {
                    dao.deleteOwner((Owner) tableVehicles.getSelectionModel().getSelectedItem());
                    updateTableView();
                    //tableVehicles.getSelectionModel().selectFirst();
                }
            }

        }

        @FXML
        private void editVehicle (ActionEvent actionEvent){
        }

    private void updateTableView() {
        int index = tableOwners.getSelectionModel().getSelectedIndex();
        tableOwners.getItems().clear();
        tableOwners.setItems(dao.getOwners());
        tableOwners.requestFocus();
        tableOwners.getSelectionModel().select(index);


        index = tableVehicles.getSelectionModel().getSelectedIndex();
        tableVehicles.getItems().clear();
        tableVehicles.setItems(dao.getVehicles());
        tableVehicles.requestFocus();
        tableOwners.getSelectionModel().select(index);
    }

}

