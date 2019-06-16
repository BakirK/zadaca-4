package ba.unsa.etf.rs.zadaca4;

import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static sun.management.jmxremote.ConnectorBootstrap.initialize;

public class VehicleDAOBase implements VehicleDAO {
    private static VehicleDAOBase instance = null;
    private Connection connection;
    private PreparedStatement getOwnersStatement;


    public VehicleDAOBase() {
        prepareStatements();
    }

    private void prepareStatements() {

    }


    public static VehicleDAOBase getInstance() {
        if (instance == null) {
            initialize();
        }
        return instance;
    }

    private static void initialize() {
        instance = new VehicleDAOBase();
    }


    public static void deleteInstance() {
        if (instance != null) {
            try {
                instance.connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        instance = null;
    }










    @Override
    public ObservableList<Owner> getOwners() {
        return null;
    }

    @Override
    public ObservableList<Vehicle> getVehicles() {
        return null;
    }

    @Override
    public ObservableList<Place> getPlaces() {
        return null;
    }

    @Override
    public ObservableList<Manufacturer> getManufacturers() {
        return null;
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
