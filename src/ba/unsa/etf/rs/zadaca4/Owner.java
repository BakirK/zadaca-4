package ba.unsa.etf.rs.zadaca4;

import java.time.LocalDate;

public class Owner {
    private int id;
    private String name, surname, parentName;
    private LocalDate dateOfBirth;
    private Place placeOfBirth;
    private String livingAddress;
    private Place livingPlace;
    private String jmbg;

    private Owner(int id, String name, String surname, String parentName, LocalDate dateOfBirth, Place placeOfBirth, String livingAddress, Place livingPlace, String jmbg) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.parentName = parentName;
        this.dateOfBirth = dateOfBirth;
        this.placeOfBirth = placeOfBirth;
        this.livingAddress = livingAddress;
        this.livingPlace = livingPlace;
        this.jmbg = jmbg;
    }

    private Owner() {
    }

    private int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    private String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    private String getSurname() {
        return surname;
    }

    private void setSurname(String surname) {
        this.surname = surname;
    }

    private String getParentName() {
        return parentName;
    }

    private void setParentName(String parentName) {
        this.parentName = parentName;
    }

    private LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    private void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    private Place getPlaceOfBirth() {
        return placeOfBirth;
    }

    private void setPlaceOfBirth(Place placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    private String getLivingAddress() {
        return livingAddress;
    }

    private void setLivingAddress(String livingAddress) {
        this.livingAddress = livingAddress;
    }

    private Place getLivingPlace() {
        return livingPlace;
    }

    private void setLivingPlace(Place livingPlace) {
        this.livingPlace = livingPlace;
    }

    private String getJmbg() {
        return jmbg;
    }

    private void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    @Override
    public String toString() { return surname + " " + name; }
}
