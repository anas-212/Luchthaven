package models;

import people.Passagier;

public class Ticket {
    private Passagier passagier;
    private Vlucht vlucht;
    private String klasse;

    public Ticket(Passagier passagier, Vlucht vlucht, String klasse) {
        klasse = klasse.toLowerCase();
        if (!klasse.equals("economy") && !klasse.equals("business")) {
            throw new IllegalArgumentException("Klasse moet 'economy' of 'business' zijn.");
        }
        this.passagier = passagier;
        this.vlucht = vlucht;
        this.klasse = klasse;
    }

    public Passagier getPassagier() {
        return passagier;
    }

    public Vlucht getVlucht() {
        return vlucht;
    }

    public String getKlasse() {
        return klasse;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "passagier=" + passagier +
                ", vlucht=" + vlucht +
                ", klasse='" + klasse + '\'' +
                '}';
    }
}
