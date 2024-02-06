package src.View;

import java.util.InputMismatchException;
import java.util.Scanner;

public class cli {

    public static void menu(){
        System.out.println( "Menu");
        System.out.println(" 1. Jouer");
        System.out.println(" 2. Score");
        System.out.println(" 3. Règles");
        System.out.println(" 4. Quitter");

        Scanner scanner = new Scanner(System.in);
        try {
            int choice = scanner.nextInt();
            switch(choice){
                case 1:
                    System.out.println(" Commencez "+"\r\n"+" ");
                    menu();
                case 2:
                    System.out.println(" Score "+"\r\n"+" ");
                    menu();
                case 3:
                    System.out.println("Règles");
                    System.out.println("À chaque tour un joueur devra déplacer son pion d’une case (verticalement ou horizontalement),"+"\r\n"+"puis detruire une case du plateau afin de bloquer l’(es) autre(s) joueur(s)."+"\r\n"+"Le dernier joueur pouvant encore se déplacer gagne."+"\r\n"+"- Un joueur ne peut pas détruire une case occupée."+"\r\n"+"- Un joueur ne peut pas occuper une case détruite ou une case déjà occupée.\n" +
                            "- Un joueur bloqué pendant un tour est déclaré perdant. "+"\r\n"+" ");
                    menu();
                case 4:
                    System.out.println(" Quitter ");
                    break;
                default:
                    System.out.println("\033[H\033[2J");
                    System.out.flush();
                    System.out.println("Veuillez entrer un chiffre entre 1 et 4");
                    menu();
            }
        }catch( InputMismatchException e){
            System.out.println("\033[H\033[2J");
            System.out.flush();
            System.out.println("Veuillez entrer un chiffre entre 1 et 4");
            menu();
        }

    }



}
