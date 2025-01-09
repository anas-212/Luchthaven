package models;

import people.Passagier;
import people.Personeel.Personeel;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Vlucht {
    private static int teller = 1; // Teller voor unieke nummers
    private static final String MAATSCHAPPIJ_CODE = "RAM";
    private String vluchtCode;
    private String bestemming;
    private int stoelenEconomy = 0;
    private int stoelenBusiness = 0;

    private List<Passagier> passagiers = new ArrayList<>();
    private List<Personeel> personeel = new ArrayList<>();
    private List<Passagier> geboarddePassagiers = new ArrayList<>();

    public Vlucht(String bestemming) {
        vluchtCode = genereerVluchtcode(bestemming);
        this.bestemming = bestemming;
    }

    private static String genereerVluchtcode(String bestemming) {
        // Bestemmingscode halen (eerste 3 letters van bestemming)
        String bestemmingsCode = bestemming.substring(0, 3).toUpperCase();

        // Uniek vluchtnummer genereren
        int vluchtnummer = new Random().nextInt(90000) + 100; // Random getal tussen 100-99900

        // Vluchtcode opbouwen
        String vluchtcode = MAATSCHAPPIJ_CODE + vluchtnummer + "-" + bestemmingsCode;

        return vluchtcode;
    }

    public void voegPassagierToe(Passagier passagier, String klasse) {
        klasse = klasse.toLowerCase();
        if (klasse.equals("economy")) {
            stoelenEconomy++;
        } else if (klasse.equals("business")) {
            stoelenBusiness++;
        }
        passagiers.add(passagier);
        System.out.println("Passagier toegevoegd aan " + klasse + "-klasse.");
    }

    public void boardPassagier(Ticket ticket) {
        // Validatie: Controleer of het ticket bij deze vlucht hoort
        if (!ticket.getVlucht().getVluchtCode().equals(this.vluchtCode)) {
            System.out.println("Fout: Ticket behoort niet tot deze vlucht.");
            return;
        }

        // Validatie: Controleer of de passagier al aan boord is
        if (geboarddePassagiers.contains(ticket.getPassagier())) {
            System.out.println("Fout: Passagier is al aan boord.");
            return;
        }

        // Wijs een stoel toe en voeg de passagier toe aan de lijst van geboardde passagiers
        voegPassagierToe(ticket.getPassagier(), ticket.getKlasse());
        geboarddePassagiers.add(ticket.getPassagier());
        System.out.println("Passagier " + ticket.getPassagier().getNaam() + " succesvol geboard.");
    }


    public void voegPersoneelslidToe(Personeel personeelslid) {
        personeel.add(personeelslid);
    }

    public void printVluchtInfoNaarBestand() {
        String bestandNaam = vluchtCode + "_vluchtinfo.txt"; // Bestandsnaam gebaseerd op de vluchtcode

        try (FileWriter writer = new FileWriter(bestandNaam)) {
            writer.write("Vluchtinformatie\n");
            writer.write("=================\n");
            writer.write("Vluchtcode: " + vluchtCode + "\n");
            writer.write("Bestemming: " + bestemming + "\n");
            writer.write("Aantal stoelen (Economy): " + stoelenEconomy + "\n");
            writer.write("Aantal stoelen (Business): " + stoelenBusiness + "\n");

            writer.write("\nToegewezen Personeel:\n");
            if (personeel.isEmpty()) {
                writer.write("Geen personeel toegewezen.\n");
            } else {
                for (int i = 0; i < personeel.size(); i++) {
                    Personeel p = personeel.get(i);
                    writer.write("- " + p + "\n");
                }
            }

            writer.write("\nPassagiers:\n");
            if (passagiers.isEmpty()) {
                writer.write("Geen passagiers aan boord.\n");
            } else {
                for (int i = 0; i < passagiers.size(); i++) {
                    Passagier p = passagiers.get(i);
                    writer.write("- " + p + "\n");
                }
            }

            System.out.println("Vluchtinformatie succesvol weggeschreven naar: " + bestandNaam);
        } catch (IOException e) {
            System.err.println("Fout bij het schrijven naar bestand: " + e.getMessage());
        }
    }


    // Getters
    public String getVluchtCode() {
        return vluchtCode;
    }

    public String getBestemming() {
        return bestemming;
    }

    public int getStoelenEconomy() {
        return stoelenEconomy;
    }

    public int getStoelenBusiness() {
        return stoelenBusiness;
    }

    // Setters
    public void setVluchtCode(String vluchtCode) {
        this.vluchtCode = vluchtCode;
    }

    public void setBestemming(String bestemming) {
        this.bestemming = bestemming;
    }

    public void setStoelenEconomy(int stoelenEconomy) {
        this.stoelenEconomy = stoelenEconomy;
    }

    public void setStoelenBusiness(int stoelenBusiness) {
        this.stoelenBusiness = stoelenBusiness;
    }

    @Override
    public String toString() {
        return "Vlucht{" +
                "vluchtCode='" + vluchtCode + '\'' +
                ", bestemming='" + bestemming + '\'' +
                ", stoelenEconomy=" + stoelenEconomy +
                ", stoelenBusiness=" + stoelenBusiness +
                '}';
    }
}

