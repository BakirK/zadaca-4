package ba.unsa.etf.rs.zadaca4;

import javafx.beans.property.SimpleStringProperty;

public class Vehicle {
    private int id;
    private Manufacturer manufacturer;
    private String chasisNumber = " ", plateNumber = " ";

    public String getModel() {
        return model.get();
    }

    public void setModel(String model) {
        this.model.set(model);
    }

    public SimpleStringProperty model = new SimpleStringProperty();
    private Owner owner;

    public Vehicle(int id, Manufacturer manufacturer, String model, String chasisNumber, String plateNumber, Owner owner) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.model.set(model);
        this.chasisNumber = chasisNumber;
        this.plateNumber = plateNumber;
        this.owner = owner;
    }

    public Vehicle() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    /*public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }*/

    public String getChasisNumber() {
        return chasisNumber;
    }

    public void setChasisNumber(String chasisNumber) {
        this.chasisNumber = chasisNumber;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public int compareTo(Vehicle o) {
        return this.id - o.id;
    }
}
