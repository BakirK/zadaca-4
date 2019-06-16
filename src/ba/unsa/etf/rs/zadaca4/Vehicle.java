package ba.unsa.etf.rs.zadaca4;

public class Vehicle {
    private int id;
    private Manufacturer manufacturer;
    private String model, chasisNumber, plateNumber;
    private Owner owner;

    private Vehicle(int id, Manufacturer manufacturer, String model, String chasisNumber, String plateNumber, Owner owner) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.model = model;
        this.chasisNumber = chasisNumber;
        this.plateNumber = plateNumber;
        this.owner = owner;
    }

    private Vehicle() {
    }

    private int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    private Manufacturer getManufacturer() {
        return manufacturer;
    }

    private void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    private String getModel() {
        return model;
    }

    private void setModel(String model) {
        this.model = model;
    }

    private String getChasisNumber() {
        return chasisNumber;
    }

    private void setChasisNumber(String chasisNumber) {
        this.chasisNumber = chasisNumber;
    }

    private String getPlateNumber() {
        return plateNumber;
    }

    private void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    private Owner getOwner() {
        return owner;
    }

    private void setOwner(Owner owner) {
        this.owner = owner;
    }
}
