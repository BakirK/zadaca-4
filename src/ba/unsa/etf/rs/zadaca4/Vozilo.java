package ba.unsa.etf.rs.zadaca4;

public class Vozilo {
    private int id;
    private Proizvodjac proizvodjac;
    private String model, brojSasije, brojTablica;
    private Vlasnik vlasnik;

    private Vozilo(int id, Proizvodjac proizvodjac, String model, String brojSasije, String brojTablica, Vlasnik vlasnik) {
        this.id = id;
        this.proizvodjac = proizvodjac;
        this.model = model;
        this.brojSasije = brojSasije;
        this.brojTablica = brojTablica;
        this.vlasnik = vlasnik;
    }

    private Vozilo() {
    }

    private int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    private Proizvodjac getProizvodjac() {
        return proizvodjac;
    }

    private void setProizvodjac(Proizvodjac proizvodjac) {
        this.proizvodjac = proizvodjac;
    }

    private String getModel() {
        return model;
    }

    private void setModel(String model) {
        this.model = model;
    }

    private String getBrojSasije() {
        return brojSasije;
    }

    private void setBrojSasije(String brojSasije) {
        this.brojSasije = brojSasije;
    }

    private String getBrojTablica() {
        return brojTablica;
    }

    private void setBrojTablica(String brojTablica) {
        this.brojTablica = brojTablica;
    }

    private Vlasnik getVlasnik() {
        return vlasnik;
    }

    private void setVlasnik(Vlasnik vlasnik) {
        this.vlasnik = vlasnik;
    }
}
