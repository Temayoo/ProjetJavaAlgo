package src.View;

import java.util.InputMismatchException;
import java.util.Scanner;
import static src.View.cli.menu;

public class cliTriScore {

    public static void scoreCli(){
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("╔════════════════════════════════╗");
            System.out.println("║         Menu                   ║");
            System.out.println("╠════════════════════════════════╣");
            System.out.println("║ 1. Sans tri                    ║");
            System.out.println("║ 2. Ordre croissant             ║");
            System.out.println("║ 3. Ordre décroissant           ║");
            System.out.println("║ 4. Revenir menu                ║");
            System.out.println("║ Choisissez une option          ║");
            System.out.println("╚════════════════════════════════╝");

            int choix = scanner.nextInt();

            switch (choix) {
                case 1:
                    System.out.println("\r\n" + "Voici les scores des differents joueurs pas triés" + "\r\n" + " ");
                    src.View.CreationFichier.lectureFichier("DossierScore/score.txt");  // Lis le fichier txt
                    scoreCli();
                    break;
                case 2:
                    // tri de la map
                    //ecrire dans le ficier la nouvelle liste
                    src.View.CreationFichier.lectureFichier("DossierScore/score.txt");  // Lis le fichier txt
                    break;
                case 3:
                    menu(); // renvoie au menu
                    break;
                case 4:
                    menu(); // renvoie au menu
                    break;
                default:
                    System.out.println("\033[H\033[2J");
                    System.out.flush();
                    System.out.println("Veuillez entrer un chiffre entre 1 et 4");
                    scoreCli();
            }
        } catch (InputMismatchException e) { // gestion d'erreur si l'utilisateur ne met pas un chiffre
            System.out.println("\033[H\033[2J");
            System.out.flush();
            System.out.println("Veuillez entrer un chiffre entre 1 et 4");
            scoreCli();
        }
    }
}

