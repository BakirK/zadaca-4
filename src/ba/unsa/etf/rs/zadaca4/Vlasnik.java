package ba.unsa.etf.rs.zadaca4;

import java.time.LocalDate;

public class Vlasnik {
    private int id;
    private String ime, prezime, imeRoditelja;
    private LocalDate datumRodjenja;
    private Mjesto mjestoRodjenja;
    private String adresaPrebivalista;
    private Mjesto mjestoPrebivalista;
    private String jmbg;

    private Vlasnik(int id, String ime, String prezime, String imeRoditelja, LocalDate datumRodjenja, Mjesto mjestoRodjenja, String adresaPrebivalista, Mjesto mjestoPrebivalista, String jmbg) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.imeRoditelja = imeRoditelja;
        this.datumRodjenja = datumRodjenja;
        this.mjestoRodjenja = mjestoRodjenja;
        this.adresaPrebivalista = adresaPrebivalista;
        this.mjestoPrebivalista = mjestoPrebivalista;
        this.jmbg = jmbg;
    }

    private Vlasnik() {
    }

    private int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    private String getIme() {
        return ime;
    }

    private void setIme(String ime) {
        this.ime = ime;
    }

    private String getPrezime() {
        return prezime;
    }

    private void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    private String getImeRoditelja() {
        return imeRoditelja;
    }

    private void setImeRoditelja(String imeRoditelja) {
        this.imeRoditelja = imeRoditelja;
    }

    private LocalDate getDatumRodjenja() {
        return datumRodjenja;
    }

    private void setDatumRodjenja(LocalDate datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    private Mjesto getMjestoRodjenja() {
        return mjestoRodjenja;
    }

    private void setMjestoRodjenja(Mjesto mjestoRodjenja) {
        this.mjestoRodjenja = mjestoRodjenja;
    }

    private String getAdresaPrebivalista() {
        return adresaPrebivalista;
    }

    private void setAdresaPrebivalista(String adresaPrebivalista) {
        this.adresaPrebivalista = adresaPrebivalista;
    }

    private Mjesto getMjestoPrebivalista() {
        return mjestoPrebivalista;
    }

    private void setMjestoPrebivalista(Mjesto mjestoPrebivalista) {
        this.mjestoPrebivalista = mjestoPrebivalista;
    }

    private String getJmbg() {
        return jmbg;
    }

    private void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    @Override
    public String toString() { return prezime + " " + ime; }
}
