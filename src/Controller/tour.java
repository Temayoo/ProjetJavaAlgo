package src.Controller;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import src.Model.Joueur;
import src.Model.Carte;

public class tour {

     static void tourJoueur(Joueur joueur, Carte carteJeu) {
        System.out.println("Tour de " + joueur.obtenirPseudo());

        // Sauvegarde des coordonnées actuelles du joueur
        int ancienX = joueur.obtenirPositionX();
        int ancienY = joueur.obtenirPositionY();

        mouvement.deplacement(joueur, carteJeu);

        // Vérifier si le joueur s'est réellement déplacé
        if (ancienX == joueur.obtenirPositionX() && ancienY == joueur.obtenirPositionY()) {
            System.out.println("Le déplacement n'est pas valide. Veuillez choisir une autre direction.");
            tourJoueur(joueur, carteJeu); // Répéter le tour du joueur
        } else {
            placerX(joueur, carteJeu);
        }
    }
    



    private static void placerX(Joueur joueur, Carte carteJeu) {
        Scanner scanner = new Scanner(System.in);
        boolean coordonneesValides = false;

        while (!coordonneesValides) {
            try {
                System.out.println("Choisissez les coordonnées où placer 'X' (format : x y) : ");
                int x = scanner.nextInt();
                int y = scanner.nextInt();

                
                if (x < 0 || x >= carteJeu.obtenirTailleX() || y < 0 || y >= carteJeu.obtenirTailleY()) {
                    System.out.println("Erreur : Veuillez entrer deux entiers valides.");
                    scanner.nextLine();
                    placerX(joueur, carteJeu);
                    continue;
                }

                if (carteJeu.obtenirContenuCase(x, y).equals(".")) {
                    carteJeu.placerX(x, y);
                    System.out.println("'X' placé avec succès !");
                    coordonneesValides = true;

                } else {
                    System.out.println("Vous ne pouvez placer 'X' que sur une case libre ('.').");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erreur : Veuillez entrer deux entiers valides.");
                scanner.nextLine();
            } catch (NoSuchElementException e) {
                System.out.println("Erreur : Entrée non valide. Assurez-vous d'entrer deux entiers.");
                scanner.nextLine();
            }
        }
    }

}
