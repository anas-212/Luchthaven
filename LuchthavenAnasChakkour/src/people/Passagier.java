package people;

public class Passagier {
    private String naam;
    private int leeftijd;
    private String adres;
    private double bagagegewicht;

    // Constructor
    public Passagier(String naam, int leeftijd, String adres, double bagagegewicht) {
        this.naam = naam;
        this.leeftijd = leeftijd;
        this.adres = adres;
        setBagagegewicht(bagagegewicht);
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

    public double getBagagegewicht() {
        return bagagegewicht;
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

    public void setBagagegewicht(double bagagegewicht) {
        if (bagagegewicht < 0 || bagagegewicht > 25) {
            throw new IllegalArgumentException("Bagagegewicht moet tussen 0 en 25 kg zijn.");
        }
        this.bagagegewicht = bagagegewicht;
    }

    @Override
    public String toString() {
        return "Passagier{" +
                "naam='" + naam + '\'' +
                ", leeftijd=" + leeftijd +
                ", adres='" + adres + '\'' +
                ", bagagegewicht=" + bagagegewicht +
                '}';
    }
}
