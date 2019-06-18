package ba.unsa.etf.rs.zadaca4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class VehicleDAOBase implements VehicleDAO {
    private static VehicleDAOBase instance = null;
    private Connection connection;
    private PreparedStatement getOwnersStatement, getPlaceStatement, getVehiclesStatement,
        getManufacturerStatement, getManufacturersStatement, getOwnerStatement, getPlacesStatement,
            updateOwnerStatement, addPlaceStatement, getMaxPlaceIdStatement, addOwnerStatement,
            getMaxOwnerIdStatement, deleteOwnerStatement, getOwnerVehiclesStatement,
            getMaxManufacturerIdStatement, addManufacturerStatement, addVehicleStatement,
            getMaxVehicleIdStatement, updateVehicleStatement, deleteVehicleStatement;


    public VehicleDAOBase() {
        prepareStatements();
    }

    private void prepareStatements() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:vehicles.db");
            Class.forName("org.sqlite.JDBC");
            getOwnersStatement = connection.prepareStatement("SELECT id, name, surname, " +
                    "parent_name, date_of_birth, place_of_birth, living_address, living_place, jmbg " +
                    "FROM owner ORDER BY id;");

            getVehiclesStatement = connection.prepareStatement("SELECT id, manufacturer, model," +
                    "chasis_number, plate_number, owner FROM vehicle ORDER BY ID;");

            getPlaceStatement = connection.prepareStatement("SELECT * FROM place WHERE id=?;");

            getPlacesStatement = connection.prepareStatement("SELECT id, name, postal_number " +
                            "FROM place ORDER BY name;");

            getManufacturerStatement = connection.prepareStatement("SELECT * FROM manufacturer " +
                    "WHERE id=?;");

            getManufacturersStatement = connection.prepareStatement("SELECT * FROM manufacturer " +
                    "ORDER BY name;");

            getOwnerStatement = connection.prepareStatement("SELECT * FROM owner WHERE id=?;");
            updateOwnerStatement = connection.prepareStatement("UPDATE owner SET name=?, surname=?," +
                    " parent_name=?, date_of_birth=?, place_of_birth=?, living_address=?," +
                    " living_place=?, jmbg=? WHERE id=?; COMMIT;");
            addPlaceStatement = connection.prepareStatement("INSERT INTO place VALUES (?,?,?); COMMIT;");
            getMaxPlaceIdStatement = connection.prepareStatement("SELECT max(id) + 1 FROM place;");
            getMaxOwnerIdStatement = connection.prepareStatement("SELECT max(id) + 1 FROM owner;");
            getMaxManufacturerIdStatement = connection.prepareStatement("SELECT max(id) + 1 FROM manufacturer;");
            addOwnerStatement = connection.prepareStatement("INSERT INTO owner VALUES (?,?,?,?,?,?,?,?,?); COMMIT;");
            getOwnerVehiclesStatement = connection.prepareStatement("SELECT * FROM vehicle WHERE owner=?;");
            deleteOwnerStatement = connection.prepareStatement("DELETE FROM owner WHERE id=?; COMMIT;");
            addManufacturerStatement = connection.prepareStatement("INSERT INTO manufacturer VALUES (?,?); COMMIT;");
            addVehicleStatement = connection.prepareStatement("INSERT INTO vehicle VALUES(?,?,?,?,?,?); COMMIT;");
            getMaxVehicleIdStatement = connection.prepareStatement("SELECT max(id) + 1 FROM vehicle;");
            updateVehicleStatement = connection.prepareStatement("UPDATE vehicle SET manufacturer=?," +
                    " model=?, chasis_number=?, plate_number=?, owner=? " +
                    "WHERE id=?; COMMIT;");
            deleteVehicleStatement = connection.prepareStatement("DELETE FROM vehicle WHERE id=?; COMMIT;");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
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
        try {
            ResultSet res = getPlacesStatement.executeQuery();
            while(res.next()) {
                Place place = new Place(res.getInt(1),
                        res.getString(2),res.getString(3));
                places.add(place);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return places;
    }

    @Override
    public ObservableList<Manufacturer> getManufacturers() {
        ObservableList<Manufacturer> manufacturers = FXCollections.observableArrayList();
        try {
            ResultSet res = getManufacturersStatement.executeQuery();
            while(res.next()) {
                Manufacturer manufacturer = new Manufacturer(res.getInt(1),
                        res.getString(2));
                manufacturers.add(manufacturer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return manufacturers;
    }

    @Override
    public void addOwner(Owner owner) {
        int id = addPlaceIfNotExists(owner.getLivingPlace());
        if(id != owner.getLivingPlace().getId()) {
            owner.getLivingPlace().setId(id);
        }
        id = addPlaceIfNotExists(owner.getPlaceOfBirth());
        if(id != owner.getPlaceOfBirth().getId()) {
            owner.getPlaceOfBirth().setId(id);
        }
        try {
            ResultSet r = getMaxOwnerIdStatement.executeQuery();
            id = r.getInt(1);
            addOwnerStatement.setInt(1, id);
            addOwnerStatement.setString(2, owner.getName());
            addOwnerStatement.setString(3, owner.getSurname());
            addOwnerStatement.setString(4, owner.getParentName());
            addOwnerStatement.setDate( 5, Date.valueOf(owner.getDateOfBirth()));
            addOwnerStatement.setInt( 6, owner.getPlaceOfBirth().getId());
            addOwnerStatement.setString( 7, owner.getLivingAddress());
            addOwnerStatement.setInt( 8, owner.getLivingPlace().getId());
            addOwnerStatement.setString( 9, owner.getJmbg());
            addOwnerStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int addPlaceIfNotExists(Place p) {
        int id = p.getId();
        try {
            getPlaceStatement.setInt(1, p.getId());
            ResultSet res = getPlaceStatement.executeQuery();
            if(!res.next()) {
                ResultSet temp = getMaxPlaceIdStatement.executeQuery();
                id = temp.getInt(1);
                addPlaceStatement.setInt(1, id);
                addPlaceStatement.setString(2, p.getName());
                addPlaceStatement.setString(3, p.getPostalNumber());
                addPlaceStatement.executeUpdate();
                p.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    private int addManufacturerIfNotExists(Manufacturer m) {
        int id = m.getId();
        try {
            getManufacturerStatement.setInt(1, id);
            ResultSet res = getManufacturerStatement.executeQuery();
            if(!res.next()) {
                res = getMaxManufacturerIdStatement.executeQuery();
                id = res.getInt(1);
                m.setId(id);
                addManufacturerStatement.setInt(1, m.getId());
                addManufacturerStatement.setString(2, m.getName());
                addManufacturerStatement.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public void changeOwner(Owner owner) {
        try {
            int id = addPlaceIfNotExists(owner.getPlaceOfBirth());
            owner.getPlaceOfBirth().setId(id);
            id = addPlaceIfNotExists(owner.getLivingPlace());
            owner.getLivingPlace().setId(id);
            updateOwnerStatement.setString(1, owner.getName());
            updateOwnerStatement.setString(2, owner.getSurname());
            updateOwnerStatement.setString(3, owner.getParentName());
            updateOwnerStatement.setDate(4, Date.valueOf(owner.getDateOfBirth()));
            updateOwnerStatement.setInt(5, owner.getPlaceOfBirth().getId());
            updateOwnerStatement.setString(6, owner.getLivingAddress());
            updateOwnerStatement.setInt(7, owner.getLivingPlace().getId());
            updateOwnerStatement.setString(8, owner.getJmbg());
            updateOwnerStatement.setInt(9, owner.getId());
            updateOwnerStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOwner(Owner owner) throws IllegalArgumentException {
        try {
            getOwnerVehiclesStatement.setInt(1, owner.getId());
            ResultSet res = getOwnerVehiclesStatement.executeQuery();
            if(res.next()) {
                throw new IllegalArgumentException();
            }
            deleteOwnerStatement.setInt(1, owner.getId());
            deleteOwnerStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void addVehicle(Vehicle vehicle)throws IllegalArgumentException  {
        try {
            //provjera da li postoji owner
            checkOwnerAndManufacturer(vehicle);
            ResultSet res;
            res = getMaxVehicleIdStatement.executeQuery();
            vehicle.setId(res.getInt(1));
            addVehicleStatement.setInt(1, vehicle.getId());
            addVehicleStatement.setInt(2, vehicle.getManufacturer().getId());
            addVehicleStatement.setString(3, vehicle.getModel());
            addVehicleStatement.setString(4, vehicle.getChasisNumber());
            addVehicleStatement.setString(5, vehicle.getPlateNumber());
            addVehicleStatement.setInt(6, vehicle.getOwner().getId());
            addVehicleStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void changeVehicle(Vehicle vehicle)throws IllegalArgumentException  {
        try {
            checkOwnerAndManufacturer(vehicle);
            updateVehicleStatement.setInt(1, vehicle.getManufacturer().getId());
            updateVehicleStatement.setString(2, vehicle.getModel());
            updateVehicleStatement.setString(3, vehicle.getChasisNumber());
            updateVehicleStatement.setString(4, vehicle.getPlateNumber());
            updateVehicleStatement.setInt(5, vehicle.getOwner().getId());
            updateVehicleStatement.setInt(6, vehicle.getId());
            updateVehicleStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void checkOwnerAndManufacturer(Vehicle vehicle) throws SQLException, IllegalArgumentException {
        getOwnerStatement.setInt(1, vehicle.getOwner().getId());
        ResultSet res = getOwnerStatement.executeQuery();
        if(!res.next()) {
            throw new IllegalArgumentException("Owner does not exists");
        }
        int id = addManufacturerIfNotExists(vehicle.getManufacturer());
        if(id != vehicle.getManufacturer().getId()) {
            vehicle.getManufacturer().setId(id);
        }
    }

    @Override
    public void deleteVehicle(Vehicle vehicle) {
        try {
            deleteVehicleStatement.setInt(1, vehicle.getId());
            deleteVehicleStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        deleteInstance();
    }
}
