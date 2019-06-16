package ba.unsa.etf.rs.zadaca4;

public class Place {
    private int id;
    private String name;
    private String postalNumber;

    private Place(int id, String name, String postalNumber) {
        this.id = id;
        this.name = name;
        this.postalNumber = postalNumber;
    }

    private Place() {
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

    private String getPostalNumber() {
        return postalNumber;
    }

    private void setPostalNumber(String postalNumber) {
        this.postalNumber = postalNumber;
    }

    @Override
    public String toString() {
        return name;
    }
}
