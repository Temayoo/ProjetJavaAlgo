package src.View;

import java.util.Scanner;

public class scannerPseudo {
    public static String[] demanderPseudos() {
        Scanner scanner = new Scanner(System.in);
        String[] pseudos = new String[2];
        for (int i = 0; i < 2; i++) {
            System.out.println("Veuillez entrer le pseudo du Joueur " + (i + 1) + " (entre 2 et 10 caractères): ");
            String pseudo = scanner.next();

            while (pseudo.length() < 2 || pseudo.length() > 10) {
                System.out.println("Le pseudo doit avoir entre 2 et 10 caractères. Veuillez réessayer : ");
                pseudo = scanner.next();
            }

            pseudos[i] = pseudo;
        }

        return pseudos;
    }
}
