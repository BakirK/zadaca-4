package ba.unsa.etf.rs.zadaca4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.util.ArrayList;

public class VehicleDAOXML implements VehicleDAO, Serializable {
    private static final long serialVersionUID = 1L;
    private ArrayList<Manufacturer> manufacturers = new ArrayList<>();
    private ArrayList<Owner> owners = new ArrayList<>();
    private ArrayList<Place> places = new ArrayList<>();
    private ArrayList<Vehicle> vehicles = new ArrayList<>();


    @Override
    public ObservableList<Owner> getOwners() {
        return FXCollections.observableArrayList(owners);
    }

    @Override
    public ObservableList<Vehicle> getVehicles() {
        return FXCollections.observableArrayList(vehicles);
    }

    @Override
    public ObservableList<Place> getPlaces() {
        return FXCollections.observableArrayList(places);
    }

    @Override
    public ObservableList<Manufacturer> getManufacturers() {
        return FXCollections.observableArrayList(manufacturers);
    }

    @Override
    public void addOwner(Owner owner) {

    }

    @Override
    public void changeOwner(Owner owner) {

    }

    @Override
    public void deleteOwner(Owner owner) {

    }

    @Override
    public void addVehicle(Vehicle vehicle) {

    }

    @Override
    public void changeVehicle(Vehicle vehicle) {

    }

    @Override
    public void deleteVehicle(Vehicle vehicle) {

    }

    @Override
    public void close() {

    }
}
