package ba.unsa.etf.rs.zadaca4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.beans.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;

public class VehicleDAOXML implements VehicleDAO, Serializable {
    private static final long serialVersionUID = 1L;
    private ArrayList<Manufacturer> manufacturers = new ArrayList<>();
    private ArrayList<Owner> owners = new ArrayList<>();
    private ArrayList<Place> places = new ArrayList<>();
    private ArrayList<Vehicle> vehicles = new ArrayList<>();

    public VehicleDAOXML() {
        load();
        sort();
    }

    private void sort() {
        manufacturers.sort(new Comparator<Manufacturer>() {
            @Override
            public int compare(Manufacturer o1, Manufacturer o2) {
                return o1.compareTo(o2);
            }
        });
        owners.sort(new Comparator<Owner>() {
            @Override
            public int compare(Owner o1, Owner o2) {
                return o1.compareTo(o2);
            }
        });
        places.sort(new Comparator<Place>() {
            @Override
            public int compare(Place o1, Place o2) {
                return o1.compareTo(o2);
            }
        });
        vehicles.sort(new Comparator<Vehicle>() {
            @Override
            public int compare(Vehicle o1, Vehicle o2) {
                return o1.compareTo(o2);
            }
        });

    }

    private void load() {
        try {
            XMLDecoder XMLdecoder = new XMLDecoder(new FileInputStream("manufacturers.xml"));
            manufacturers = (ArrayList<Manufacturer>)XMLdecoder.readObject();
            XMLdecoder.close();

            XMLdecoder = new XMLDecoder(new FileInputStream("owners.xml"));
            owners = (ArrayList<Owner>)XMLdecoder.readObject();
            XMLdecoder.close();

            XMLdecoder = new XMLDecoder(new FileInputStream("places.xml"));
            places = (ArrayList<Place>)XMLdecoder.readObject();
            XMLdecoder.close();

            XMLdecoder = new XMLDecoder(new FileInputStream("vehicles.xml"));
            vehicles = (ArrayList<Vehicle>)XMLdecoder.readObject();
            XMLdecoder.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void save() {
        try {
            XMLEncoder XMLdecoder = new XMLEncoder(new FileOutputStream("manufacturers.xml"));
            XMLdecoder.setPersistenceDelegate(LocalDate.class,
                    new PersistenceDelegate() {
                        @Override
                        protected Expression instantiate(Object localDate, Encoder encdr) {
                            return new Expression(localDate,
                                    LocalDate.class,
                                    "parse",
                                    new Object[]{localDate.toString()});
                        }
                    });
            XMLdecoder.writeObject(manufacturers);
            XMLdecoder.close();

            XMLdecoder = new XMLEncoder(new FileOutputStream("owners.xml"));
            XMLdecoder.writeObject(owners);
            XMLdecoder.close();

            XMLdecoder = new XMLEncoder(new FileOutputStream("places.xml"));
            XMLdecoder.writeObject(places);
            XMLdecoder.close();

            XMLdecoder = new XMLEncoder(new FileOutputStream("vehicles.xml"));
            XMLdecoder.writeObject(vehicles);
            XMLdecoder.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

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

    private int addPlaceIfNotExists(Place p) {
        boolean found = false;
        for (int i = 0; i < places.size(); i++) {
            if(places.get(i).getId() == p.getId() && places.get(i).getName().equals(p.getName())
                        && places.get(i).getPostalNumber().equals(p.getPostalNumber()) ) {
                places.set(i, p);
                return p.getId();
            }
        }
        int max = -1;
        if(!found) {

            for(Place place: places) {
                if(place.getId() > max) {
                    max = place.getId();
                }
            }
            max++;
            p.setId(max );
            places.add(p);
        }
        System.out.println("max: " + max);
        return max;
    }
    @Override
    public void addOwner(Owner owner) {
        owner.getPlaceOfBirth().setId(addPlaceIfNotExists(owner.getPlaceOfBirth()));
        owner.getLivingPlace().setId(addPlaceIfNotExists(owner.getLivingPlace()));
        int max = 0;
        for(Owner o: owners) {
            if(o.getId() > max) {
                max = o.getId();
            }
        }
        owner.setId(max + 1);
        owners.add(owner);
        save();
        sort();
    }
    @Override
    public void changeOwner(Owner owner) {
        owner.getPlaceOfBirth().setId(addPlaceIfNotExists(owner.getPlaceOfBirth()));
        owner.getLivingPlace().setId(addPlaceIfNotExists(owner.getLivingPlace()));
        int index = -1;
        for (int i = 0; i < owners.size(); i++) {
            if(owners.get(i).getId() == owner.getId()) {
                index = i;
                System.out.println("index: " + index);
                break;
            }
        }
        if(index != -1) {
            owners.set(index, owner);
        }
        save();
        sort();
    }

    @Override
    public void deleteOwner(Owner owner) {
        for (int i = 0; i < vehicles.size(); i++) {
            if(vehicles.get(i).getOwner().getId() == owner.getId()) {
                throw new IllegalArgumentException("Owner has cars assinged to him");
            }
        }

        for (int i = 0; i < owners.size(); i++) {
            if(owners.get(i).getId() == owner.getId()) {
                owners.remove(i);
                break;
            }
        }
        save();
        sort();
    }
    private int addManufacturerIfNotExists(Manufacturer m) {
        boolean found = false;
        for(Manufacturer manufacturer: manufacturers) {
            if(manufacturer.getId() == m.getId()) {
                found = true;
                break;
            }
        }
        int id = m.getId();
        if(!found) {
            for(Manufacturer manufacturer: manufacturers) {
                if(manufacturer.getId() > id) {
                    id = manufacturer.getId();
                }
            }
            id++;
            m.setId(id);
            manufacturers.add(m);
        }
        return id;
    }
    private void checkOwnerAndManufacturer(Vehicle vehicle) throws IllegalArgumentException {
        boolean found = false;
        for(Owner o: owners) {
            if(o.getId() == vehicle.getOwner().getId()) {
                found = true;
                break;
            }
        }
        if(!found) {
            throw new IllegalArgumentException("Owner does not exists");
        }
        vehicle.getManufacturer().setId(addManufacturerIfNotExists(vehicle.getManufacturer()));
    }

    @Override
    public void addVehicle(Vehicle vehicle) throws IllegalArgumentException {
        checkOwnerAndManufacturer(vehicle);
        int max = 0;
        for(Vehicle v: vehicles) {
            if(v.getId() > max) {
                max = v.getId();
            }
        }
        vehicle.setId(max + 1);
        vehicles.add(vehicle);
        save();
        sort();
    }

    @Override
    public void changeVehicle(Vehicle vehicle) throws IllegalArgumentException  {
        checkOwnerAndManufacturer(vehicle);
        for (int i = 0; i < vehicles.size(); i++) {
            if(vehicles.get(i).getId() == vehicle.getId()) {
                vehicles.set(i, vehicle);
                save();
                sort();
                break;
            }
        }
    }

    @Override
    public void deleteVehicle(Vehicle vehicle) {
        for (int i = 0; i < vehicles.size(); i++) {
            if(vehicles.get(i).getId() == vehicle.getId()) {
                vehicles.remove(i);
                save();
                sort();
                break;
            }
        }
    }

    @Override
    public void close() {

    }
}
