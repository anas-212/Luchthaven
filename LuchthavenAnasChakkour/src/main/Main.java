package main;

import models.Ticket;
import people.Passagier;
import models.Vlucht;
import people.Personeel.CabinCrew;
import people.Personeel.Personeel;
import people.Personeel.Piloot;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static List<Passagier> passagiers = new ArrayList<>();
    private static List<Vlucht> vluchten = new ArrayList<>();
    private static List<Ticket> tickets = new ArrayList<>();
    private static List<Personeel> personeel = new ArrayList<>();
    private static String klasse;

    public static void main(String[] args) {
        char keuze;


        do {
            System.out.println("Charleroi Airport Keuzemenu: ");
            System.out.println("1. Aanmaken passagier");
            System.out.println("2. Aanmaken vlucht");
            System.out.println("3. Aanmaken ticket");
            System.out.println("4. Boarding");
            System.out.println("5. Vluchtinfo printen");
            System.out.println("0. Stoppen");
            System.out.print("Keuze: ");
            keuze = sc.next().charAt(0);

            switch (keuze) {
                case '1': passagierAanmaken();
                    break;
                case '2': vluchtAanmaken();
                    break;
                case '3': ticketAanmaken();
                    break;
                case '4': boarding();
                    break;
                case '5': vluchtInfoPrinten();
                    break;
                case '0':
                    System.out.println("Programma gestopt!");
                    break;
                default:
                    System.out.println("Ongeldige keuze, probeer opnieuw.");
            }
        } while (keuze != '0');
    }

    static void passagierAanmaken() {
        System.out.print("Naam Passagier: ");
        String naam = sc.next();

        System.out.print("Leeftijd Passagier: ");
        int leeftijd = sc.nextInt();

        System.out.print("Adres Passagier: ");
        String adres = sc.next();

        System.out.print("Bagagegewicht Passagier (in kg): ");
        double bagagegewicht = sc.nextDouble();

        Passagier passagier = new Passagier(naam, leeftijd, adres, bagagegewicht);
        passagiers.add(passagier);
        System.out.println("Passagier succesvol aangemaakt: " + passagier);

    }

    static void vluchtAanmaken() {
        System.out.print("Bestemming (volledige naam): ");
        String bestemming = sc.next();

        Vlucht vlucht = new Vlucht(bestemming);
        vluchten.add(vlucht);

        System.out.println("Vlucht succesvol aangemaakt: " + vlucht);
    }

    static void ticketAanmaken() {
        if (passagiers.isEmpty()) {
            System.out.println("Geen passagiers beschikbaar. Maak eerst een passagier aan.");
            passagierAanmaken();
        }

        if (vluchten.isEmpty()) {
            System.out.println("Geen vluchten beschikbaar. Maak eerst een vlucht aan.");
            vluchtAanmaken();
        }

        // Passagier selecteren
        System.out.println("Beschikbare passagiers:");
        for (int i = 0; i < passagiers.size(); i++) {
            System.out.println((i + 1) + ". " + passagiers.get(i));
        }
        System.out.print("Kies een passagier (nummer): ");
        int passagierIndex = sc.nextInt() - 1;

        if (passagierIndex < 0 || passagierIndex >= passagiers.size()) {
            System.out.println("Ongeldige keuze. Probeer opnieuw.");
            return;
        }
        Passagier geselecteerdePassagier = passagiers.get(passagierIndex);

        // Vlucht selecteren
        System.out.println("Beschikbare vluchten:");
        for (int i = 0; i < vluchten.size(); i++) {
            System.out.println((i + 1) + ". " + vluchten.get(i));
        }
        System.out.print("Kies een vlucht (nummer): ");
        int vluchtIndex = sc.nextInt() - 1;

        if (vluchtIndex < 0 || vluchtIndex >= vluchten.size()) {
            System.out.println("Ongeldige keuze. Probeer opnieuw.");
            return; //return sans rien pour faire stopper le programme quand il y a une erreur 
        }
        Vlucht geselecteerdeVlucht = vluchten.get(vluchtIndex);

        // Klasse kiezen
        do {
            System.out.print("Kies een klasse (economy/business): ");
            klasse = sc.next().toLowerCase();
            if (!klasse.equals("economy") && !klasse.equals("business")) {
                System.out.println("Ongeldige klasse. Probeer opnieuw.");
            }
        } while (!klasse.equals("economy") && !klasse.equals("business")); //Continue a demander le klasse tant que tu a spas mis un geldige klasse


        // Ticket aanmaken
        Ticket ticket = new Ticket(geselecteerdePassagier, geselecteerdeVlucht, klasse);
        tickets.add(ticket);

        System.out.println("Ticket succesvol aangemaakt: " + ticket);
    }

    static void boarding() {
        initPersoneel();

        if (tickets.isEmpty()) {
            System.out.println("Geen tickets beschikbaar. Maak eerst een ticket aan.");
            return;
        }


        System.out.println("Beschikbare tickets:");
        for (int i = 0; i < tickets.size(); i++) {
            System.out.println((i + 1) + ". " + tickets.get(i));
        }


        System.out.print("Kies een ticket (nummer): ");
        int ticketIndex = sc.nextInt() - 1;

        if (ticketIndex < 0 || ticketIndex >= tickets.size()) {
            System.out.println("Ongeldige keuze. Probeer opnieuw.");
            return;
        }

        Ticket geselecteerdTicket = tickets.get(ticketIndex);
        Vlucht geselecteerdeVlucht = geselecteerdTicket.getVlucht();


        for (int i = 0; i < personeel.size(); i++) {
            Personeel p = personeel.get(i);
            geselecteerdeVlucht.voegPersoneelslidToe(p);
        }


        geselecteerdeVlucht.boardPassagier(geselecteerdTicket);
    }

    private static List<Personeel> initPersoneel() {

        personeel.add(new Piloot("John Smith", 45, "Aviation Road 10", "Piloot"));
        personeel.add(new Piloot("Jane Doe", 38, "Skyline Avenue 15", "Piloot"));
        personeel.add(new CabinCrew("Alice Brown", 30, "Crew Street 5", "Cabin Crew"));
        personeel.add(new CabinCrew("Bob White", 28, "Crew Street 6", "Cabin Crew"));
        personeel.add(new CabinCrew("Clara Green", 32, "Crew Street 7", "Cabin Crew"));
        personeel.add(new CabinCrew("Camille West", 42, "Crew Street 9", "Onboarder"));

        System.out.println("Personeel aan boord en de onboarder gaat de boarding doen ");
        return personeel;
    }

    static void vluchtInfoPrinten() {
        if (vluchten.isEmpty()) {
            System.out.println("Geen vluchten beschikbaar. Maak eerst een vlucht aan.");
            return;
        }

        // Toon beschikbare vluchten
        System.out.println("Beschikbare vluchten:");
        for (int i = 0; i < vluchten.size(); i++) {
            System.out.println((i + 1) + ". " + vluchten.get(i));
        }

        // Vraag de gebruiker om een vlucht te kiezen
        System.out.print("Kies een vlucht (nummer): ");
        int vluchtIndex = sc.nextInt() - 1;

        if (vluchtIndex < 0 || vluchtIndex >= vluchten.size()) {
            System.out.println("Ongeldige keuze. Probeer opnieuw.");
            return;
        }

        Vlucht geselecteerdeVlucht = vluchten.get(vluchtIndex);
        geselecteerdeVlucht.printVluchtInfoNaarBestand();
    }
}
