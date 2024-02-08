package src.View;
import java.util.InputMismatchException;
import java.util.Scanner;


public class cli {

    public static void menu(){
        System.out.println("╔════════════════════════╗");
        System.out.println("║         Menu           ║");
        System.out.println("╠════════════════════════╣");
        System.out.println("║ 1. Commencer           ║");
        System.out.println("║ 2. Score               ║");
        System.out.println("║ 2. Règles              ║");
        System.out.println("║ 3. Quitter             ║");
        System.out.println("╚════════════════════════╝");
        // different choix cli

        Scanner scanner = new Scanner(System.in); // lancement d'un scanner
        try {
            int choice = scanner.nextInt(); // récupere l'input du scanner
            switch(choice){
                case 1:
                    System.out.println("Commencez " + "\r\n" + " ");
                    src.Controller.gamebase.demarrerJeu(); // lance la fonction de jeu dans gamebase
                    break;
                case 2:
                    System.out.println("\r\n" + "Voici les scores des differents joueurs" + "\r\n" + " ");
                    src.View.CreationFichier.lectureFichier("DossierScore/score.txt");  // Lis le fichier txt
                    menu();
                    break;
                case 3:
                    System.out.println("Règles");
                    System.out.println("À chaque tour un joueur devra déplacer son pion d’une case (verticalement ou horizontalement),"+"\r\n"+"puis detruire une case du plateau afin de bloquer l’(es) autre(s) joueur(s)."+"\r\n"+"Le dernier joueur pouvant encore se déplacer gagne."+"\r\n"+"- Un joueur ne peut pas détruire une case occupée."+"\r\n"+"- Un joueur ne peut pas occuper une case détruite ou une case déjà occupée.\n" +
                            "- Un joueur bloqué pendant un tour est déclaré perdant. "+"\r\n"+" "); // ecrit les regles
                    menu();
                    break;
                case 4:
                    System.out.println(" Quitter ");
                    break;
                default: //gestion par defaut
                    System.out.println("\033[H\033[2J");
                    System.out.flush();
                    System.out.println("Veuillez entrer un chiffre entre 1 et 4");
                    menu();
            }
        }catch( InputMismatchException e){ // gestion d'erreur si l'utilisateur ne mets pas une bonne donnée
            System.out.println("\033[H\033[2J");
            System.out.flush();
            System.out.println("Veuillez entrer un chiffre entre 1 et 4");
            menu();
        }

    }
}
