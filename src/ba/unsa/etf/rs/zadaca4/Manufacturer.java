package ba.unsa.etf.rs.zadaca4;

public class Manufacturer implements Comparable<Manufacturer> {
    private int id;
    private String name;

    public Manufacturer() {
    }

    public Manufacturer(int id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() { return name; }

    @Override
    public int compareTo(Manufacturer o) {
        return this.name.compareTo(o.name);
    }
}