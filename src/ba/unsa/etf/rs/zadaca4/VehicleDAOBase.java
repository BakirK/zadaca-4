package ba.unsa.etf.rs.zadaca4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

import static sun.management.jmxremote.ConnectorBootstrap.initialize;

public class VehicleDAOBase implements VehicleDAO {
    private static VehicleDAOBase instance = null;
    private Connection connection;
    private PreparedStatement getOwnersStatement, getPlaceStatement, getVehiclesStatement,
        getManufacturerStatement, getOwnerStatement, getPlacesStatement;


    public VehicleDAOBase() {
        prepareStatements();
    }

    private void prepareStatements() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:vehicles.db");

            getOwnersStatement = connection.prepareStatement("SELECT id, name, surname, " +
                    "parent_name, date_od_birth, place_of_birth, living_address, living_place, jmbg " +
                    "FROM owner ORDER BY id");

            getVehiclesStatement = connection.prepareStatement("SELECT id, manufacturer, model," +
                    "chasis_number, plate_number, owner FROM vehicle ORDER BY ID");

            getPlaceStatement = connection.prepareStatement("SELECT * FROM place WHERE id=?");

            getPlacesStatement = connection.prepareStatement("SELECT id, name, postal_number " +
                            "FROM place ORDER BY id");

            getManufacturerStatement = connection.prepareStatement("SELECT * FROM manufacturer " +
                    "WHERE id=?");

            getOwnerStatement = connection.prepareStatement("SELECT * FROM owner WHERE id=?");

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

    private Manufacturer getManufacturer(int id) {
        Manufacturer manufacturer = null;
        try {
            getManufacturerStatement.setInt(1, id);
            ResultSet resultSet = getManufacturerStatement.executeQuery();
            while (resultSet.next()) {
                manufacturer = new Manufacturer(resultSet.getInt(1), resultSet.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return manufacturer;
    }

    private Owner getOwner(int id) {
        Owner owner = null;
        try {
            getOwnerStatement.setInt(1, id);
            ResultSet res = getOwnerStatement.executeQuery();
            while (res.next()) {
                owner = new Owner(res.getInt(1), res.getString(2),
                        res.getString(3), res.getString(4),
                        res.getDate(5).toLocalDate(), getPlace(res.getInt(6)),
                        res.getString(7), getPlace(res.getInt(8)),
                        res.getString(9));}
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return owner;
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
        ObservableList<Vehicle> vehicles = FXCollections.observableArrayList();
        try {
            ResultSet res = getVehiclesStatement.executeQuery();
            while(res.next()) {
                Manufacturer manufacturer = getManufacturer(res.getInt(2));
                Owner owner = getOwner(res.getInt(6));
                Vehicle vehicle = new Vehicle(res.getInt(1), manufacturer, res.getString(3),
                        res.getString(4), res.getString(5), owner);
                vehicles.add(vehicle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    @Override
    public ObservableList<Place> getPlaces() {
        ObservableList<Place> places = FXCollections.observableArrayList();

        return places;
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
