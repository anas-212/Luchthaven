package people.Personeel;

public class Personeel {
    private String naam;
    private int leeftijd;
    private String adres;
    private String rol; // Bijvoorbeeld: Piloot, Cabin Crew, etc.

    // Constructor
    public Personeel(String naam, int leeftijd, String adres, String rol) {
        this.naam = naam;
        this.leeftijd = leeftijd;
        this.adres = adres;
        this.rol = rol;
    }

    // Getters
    public String getNaam() {
        return naam;
    }

    public int getLeeftijd() {
        return leeftijd;
    }

    public String getAdres() {
        return adres;
    }

    public String getRol() {
        return rol;
    }

    // Setters
    public void setNaam(String naam) {
        this.naam = naam;
    }

    public void setLeeftijd(int leeftijd) {
        this.leeftijd = leeftijd;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Personeel{" +
                "naam='" + naam + '\'' +
                ", leeftijd=" + leeftijd +
                ", adres='" + adres + '\'' +
                ", rol='" + rol + '\'' +
                '}';
    }
}
