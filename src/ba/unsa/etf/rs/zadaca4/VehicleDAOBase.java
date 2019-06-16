package ba.unsa.etf.rs.zadaca4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

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

    private Place getPlace(int id) {
        Place place = null;
        try {
            getPlaceStatement.setInt(1, id);
            ResultSet resultSet = getPlaceStatement.executeQuery();
            while (resultSet.next()) {
                place = new Place(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return place;
    }

    @Override
    public ObservableList<Owner> getOwners() {
        ObservableList<Owner> owners = FXCollections.observableArrayList();
        try {
            ResultSet res = getOwnersStatement.executeQuery();
            while(res.next()) {
                Owner owner = new Owner(res.getInt(1), res.getString(2),
                        res.getString(3), res.getString(4),
                        res.getDate(5).toLocalDate(), getPlace(res.getInt(6)),
                        res.getString(7), getPlace(res.getInt(8)),
                        res.getString(9));
                owners.add(owner);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return owners;
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
