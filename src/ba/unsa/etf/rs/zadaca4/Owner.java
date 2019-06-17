package ba.unsa.etf.rs.zadaca4;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class Owner implements Comparable<Owner> {
    private final ReadOnlyStringWrapper theName = new ReadOnlyStringWrapper();

    private int id;
    private String name, surname, parentName;
    private LocalDate dateOfBirth;
    private Place placeOfBirth;
    private String livingAddress;
    private Place livingPlace;
    private String jmbg;

    public Owner(int id, String name, String surname, String parentName, LocalDate dateOfBirth, Place placeOfBirth, String livingAddress, Place livingPlace, String jmbg) {
        theName.bind(Bindings.concat(name, " ", surname));
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

    public ReadOnlyStringProperty theNameProperty() {
        return theName.getReadOnlyProperty();
    }

    public final String getTheName() {
        return theName.get();
    }

    public Owner() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Place getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(Place placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getLivingAddress() {
        return livingAddress;
    }

    public void setLivingAddress(String livingAddress) {
        this.livingAddress = livingAddress;
    }

    public Place getLivingPlace() {
        return livingPlace;
    }

    public void setLivingPlace(Place livingPlace) {
        this.livingPlace = livingPlace;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    @Override
    public String toString() { return surname + " " + name; }

    @Override
    public int compareTo(Owner o) {
        return this.id - o.id;
    }
}
