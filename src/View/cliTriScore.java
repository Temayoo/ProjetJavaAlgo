package src.View;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import static src.Controller.trieScore.triScoreCroissant;
import static src.Controller.trieScore.triScoreDecroissant;
import static src.View.cli.menu;

public class cliTriScore {

    public static void scoreCli() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("╔════════════════════════════════╗");
            System.out.println("║         Menu                   ║");
            System.out.println("╠════════════════════════════════╣");
            System.out.println("║ 1. Sans tri                    ║");
            System.out.println("║ 2. Ordre croissant             ║");
            System.out.println("║ 3. Ordre décroissant           ║");
            System.out.println("║ 4. Retourner au menu principal ║");
            System.out.println("║ Choisissez une option :        ║");
            System.out.println("╚════════════════════════════════╝");

            int choix = scanner.nextInt();

            switch (choix) {
                case 1:
                    System.out.println("\033[H\033[2J");
                    System.out.flush();
                    System.out.println("\r\n" + "Voici les scores des différents joueurs non triés" + "\r\n" + " ");
                    CreationFichier.lectureFichier("DossierScore/score.txt");
                    scoreCli();
                    break;
                case 2:
                    System.out.println("\033[H\033[2J");
                    System.out.flush();
                    // Tri croissant
                    System.out.println("\r\n" + "Voici les scores des différents joueurs triés par ordre croissant" + "\r\n" + " ");
                    triScoreCroissant();
                    scoreCli();
                    break;
                case 3:
                    System.out.println("\033[H\033[2J");
                    System.out.flush();
                    // Tri décroissant
                    System.out.println("\r\n" + "Voici les scores des différents joueurs triés par ordre décroissant" + "\r\n" + " ");
                    triScoreDecroissant();
                    scoreCli();
                    break;
                case 4:
                    System.out.println("\033[H\033[2J");
                    System.out.flush();
                    menu(); // Revenir au menu
                    break;
                default:
                    System.out.println("\033[H\033[2J");
                    System.out.flush();
                    System.out.println("Veuillez entrer un chiffre entre 1 et 4");
                    scoreCli();
            }
        } catch (InputMismatchException e) {
            System.out.println("\033[H\033[2J");
            System.out.flush();
            System.out.println("Veuillez entrer un chiffre entre 1 et 4");
            scoreCli();
        }
    }
}
