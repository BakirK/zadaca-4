package ba.unsa.etf.rs.zadaca4;

import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static sun.management.jmxremote.ConnectorBootstrap.initialize;

public class VehicleDAOBase implements VehicleDAO {
    private static VehicleDAOBase instance = null;
    private Connection connection;
    private PreparedStatement getOwnersStatement, getPlaceStatement;


    public VehicleDAOBase() {
        prepareStatements();
    }

    private void prepareStatements() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:vehicles.db");
            getOwnersStatement = connection.prepareStatement("SELECT id, name, surname, " +
                    "parent_name, date_od_birth, place_of_birth, living_address, living_place, jmbg " +
                    "FROM owner ORDER BY id");
            getPlaceStatement = connection.prepareStatement("SELECT * FROM place WHERE id=?");
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
