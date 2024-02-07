package src.View;

import java.util.Scanner;

public class scannerPseudo {
    public static String[] demanderPseudos() {
        Scanner scanner = new Scanner(System.in);
        String[] pseudos = new String[2];


        //verifie que le pseudo fais plus de deux caractère

        for (int i = 0; i < 2; i++) {
            System.out.println("Veuillez entrer le pseudo du Joueur " + (i + 1) + " (entre 2 et 10 caractères): ");
            String pseudo = scanner.next();

            //verifie à la fois la taille du pseuso et si le pseudo 1 et le pseudo 2 sont identiques
            while (pseudo.length() < 2 || pseudo.length() > 10 || (i == 1 && pseudo.equals(pseudos[0]))) {
                if (i == 1 && pseudo.equals(pseudos[0])) {
                    System.out.println("Le pseudo doit être différent de celui du Joueur 1. Veuillez réessayer : ");
                } else {
                    System.out.println("Le pseudo doit avoir entre 2 et 10 caractères. Veuillez réessayer : ");
                }
                pseudo = scanner.next();
            }

            pseudos[i] = pseudo;
        }

        return pseudos;
    }
}
