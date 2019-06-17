package ba.unsa.etf.rs.zadaca4;

public class Place implements Comparable<Place> {
    private int id;
    private String name;
    private String postalNumber;

    public Place(int id, String name, String postalNumber) {
        this.id = id;
        this.name = name;
        this.postalNumber = postalNumber;
    }

    public Place() {
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

    public String getPostalNumber() {
        return postalNumber;
    }

    public void setPostalNumber(String postalNumber) {
        this.postalNumber = postalNumber;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(Place o) {
        return this.name.compareTo(o.name);
    }
}
