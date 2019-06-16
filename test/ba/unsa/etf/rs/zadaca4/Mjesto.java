package ba.unsa.etf.rs.zadaca4;

public class Mjesto {
    private int id;
    private String naziv;
    private String postanskiBroj;

    private Mjesto(int id, String naziv, String postanskiBroj) {
        this.id = id;
        this.naziv = naziv;
        this.postanskiBroj = postanskiBroj;
    }

    private Mjesto() {
    }


    private int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    private String getNaziv() {
        return naziv;
    }

    private void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    private String getPostanskiBroj() {
        return postanskiBroj;
    }

    private void setPostanskiBroj(String postanskiBroj) {
        this.postanskiBroj = postanskiBroj;
    }

    @Override
    public String toString() {
        return naziv;
    }
}
