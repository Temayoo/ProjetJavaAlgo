package src.View;
import java.util.InputMismatchException;
import java.util.Scanner;

public class cli {

    public static void menu() {
        System.out.println("╔════════════════════════╗");
        System.out.println("║         Menu           ║");
        System.out.println("╠════════════════════════╣");
        System.out.println("║ 1. Commencer           ║");
        System.out.println("║ 2. Score               ║");
        System.out.println("║ 3. Règles              ║");
        System.out.println("║ 4. Nettoyer les scores ║"); // Ajout de l'option Nettoyer les scores
        System.out.println("║ 5. Quitter             ║");
        System.out.println("╚════════════════════════╝");

        Scanner scanner = new Scanner(System.in);
        try {
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("\033[H\033[2J");
                    System.out.flush();
                    System.out.println("Commencez " + "\r\n" + " ");
                    src.Controller.gamebase.demarrerJeu();
                    break;
                case 2:
                    System.out.println("\033[H\033[2J");
                    System.out.flush();
                    System.out.println("\r\n" + "Comment voulez vous trier les scores" + "\r\n" + " ");
                    cliTriScore.scoreCli();
                    break;
                case 3:
                    System.out.println("Règles");
                    System.out.println("À chaque tour un joueur devra déplacer son pion d’une case (verticalement ou horizontalement)," + "\r\n" + "puis détruire une case du plateau afin de bloquer l’(es) autre(s) joueur(s)." + "\r\n" + "Le dernier joueur pouvant encore se déplacer gagne." + "\r\n" + "- Un joueur ne peut pas détruire une case occupée." + "\r\n" + "- Un joueur ne peut pas occuper une case détruite ou une case déjà occupée.\n" +
                            "- Un joueur bloqué pendant un tour est déclaré perdant. " + "\r\n" + " ");
                    menu();
                    break;
                case 4:
                    System.out.println("Nettoyer les scores");
                    src.Controller.trieScore.nettoyerScores(); // Appel à la méthode nettoyerScores
                    menu();
                    break;
                case 5:
                    System.out.println(" Quitter ");
                    break;
                default:
                    System.out.println("\033[H\033[2J");
                    System.out.flush();
                    System.out.println("Veuillez entrer un chiffre entre 1 et 5");
                    menu();
            }
        } catch (InputMismatchException e) {
            System.out.println("\033[H\033[2J");
            System.out.flush();
            System.out.println("Veuillez entrer un chiffre entre 1 et 5");
            menu();
        }
    }
}
