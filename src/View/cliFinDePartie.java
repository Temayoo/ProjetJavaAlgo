package src.View;

import java.util.InputMismatchException;
import java.util.Scanner;

import static src.Controller.gamebase.demarrerJeu;
import static src.View.cli.menu;

public class cliFinDePartie {

    // Méthode pour gérer la fin de la partie et proposer des options au joueur
    public static void finDePartie() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("╔════════════════════════════════╗");
            System.out.println("║         Menu                   ║");
            System.out.println("╠════════════════════════════════╣");
            System.out.println("║ 1. Rejouer                     ║");
            System.out.println("║ 2. Retourner au menu principal ║");
            System.out.println("║ Choisissez une option          ║");
            System.out.println("╚════════════════════════════════╝");

            int choix = scanner.nextInt();

            switch (choix) {
                case 1:
                    System.out.println("\033[H\033[2J");
                    System.out.flush();
                    demarrerJeu(); // lance la fonction demarrerjeu pour recommencer une partie
                    break;
                case 2:
                    System.out.println("\033[H\033[2J");
                    System.out.flush();
                    menu(); // renvoie au menu
                    break;
                default:
                    System.out.println("\033[H\033[2J");
                    System.out.flush();
                    System.out.println("Veuillez entrer un chiffre entre 1 et 2");
                    finDePartie();
            }
        } catch (InputMismatchException e) { // gestion d'erreur si l'utilisateur ne met pas un chiffre
            System.out.println("\033[H\033[2J");
            System.out.flush();
            System.out.println("Veuillez entrer un chiffre entre 1 et 2");
            finDePartie();
        }
    }
}
