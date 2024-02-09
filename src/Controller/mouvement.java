package src.Controller;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import src.Model.Carte;
import src.Model.Joueur;

import static src.View.cli.menu;


public class mouvement {
    
 // Méthode pour déplacer le joueur vers le haut s'il y a une case libre
    public static void deplacerVersLeHaut(List<Joueur> joueurs, Carte carte,int  positionX, int positionY, Joueur joueur) {
        int newX = joueur.positionX;
        int newY = joueur.positionY - 1;

        if (!collisionAvecAutreJoueur(newX, newY, joueurs)) {
            if (carte.obtenirContenuCase(newX, newY).equals(".")) {
                joueur.positionY = newY;
            }
        } else {
            System.out.println("Collision avec un autre joueur ! Choisissez une autre direction.");
        }
    }

    // Méthode pour déplacer le joueur vers le bas s'il y a une case libre
    public static void deplacerVersLeBas(List<Joueur> joueurs, Carte carte, int positionX,int positionY, Joueur joueur) {
        int newX = joueur.positionX;
        int newY = joueur.positionY + 1;

        if (!collisionAvecAutreJoueur(newX, newY, joueurs)) {
            if (carte.obtenirContenuCase(newX, newY).equals(".")) {
                joueur.positionY = newY;
            }
        } else {
            System.out.println("Collision avec un autre joueur ! Choisissez une autre direction.");
        }
    }

    // Méthode pour déplacer le joueur vers la gauche s'il y a une case libre
    public static void deplacerVersLaGauche(List<Joueur> joueurs, Carte carte,int positionX,int positionY, Joueur joueur) {
        int newX = joueur.positionX - 1;
        int newY = joueur.positionY;

        if (!collisionAvecAutreJoueur(newX, newY, joueurs)) {
            if (carte.obtenirContenuCase(newX, newY).equals(".")) {
                joueur.positionX = newX;
            }
        } else {
            System.out.println("Collision avec un autre joueur ! Choisissez une autre direction.");
        }
    }

    // Méthode pour déplacer le joueur vers la droite s'il y a une case libre
    public static void deplacerVersLaDroite(List<Joueur> joueurs, Carte carte,int  positionX,int positionY, Joueur joueur) {
        int newX = joueur.positionX + 1;
        int newY = joueur.positionY;

        if (!collisionAvecAutreJoueur(newX, newY, joueurs)) {
            if (carte.obtenirContenuCase(newX, newY).equals(".")) {
                joueur.positionX = newX;
            }
        } else {

            System.out.println("Collision avec un autre joueur ! Choisissez une autre direction.");

        }
    }

    // Vérifie si le joueur peut se déplacer vers le haut sur une case vide
    public static boolean peutBougerVersLeHaut(Carte carte, Joueur joueur) {
        int newX = joueur.obtenirPositionX();
        int newY = joueur.obtenirPositionY() - 1;

        // Vérifie s'il n'y a pas de collision avec un autre joueur et si la case est vide
        return !collisionAvecAutreJoueur(newX, newY, carte.getJoueurs()) && carte.obtenirContenuCase(newX, newY).equals(".");
    }

    // Vérifie si le joueur peut se déplacer vers le bas sur une case vide
    public static boolean peutBougerVersLeBas(Carte carte, Joueur joueur) {
        int newX = joueur.obtenirPositionX();
        int newY = joueur.obtenirPositionY() + 1;

        // Vérifie s'il n'y a pas de collision avec un autre joueur et si la case est vide
        return !collisionAvecAutreJoueur(newX, newY, carte.getJoueurs()) && carte.obtenirContenuCase(newX, newY).equals(".");
    }

    // Vérifie si le joueur peut se déplacer vers la gauche sur une case vide
    public static boolean peutBougerVersLaGauche(Carte carte, Joueur joueur ) {
        int newX = joueur.obtenirPositionX() - 1;
        int newY = joueur.obtenirPositionY();
        
        // Vérifie s'il n'y a pas de collision avec un autre joueur et si la case est vide
        return !collisionAvecAutreJoueur(newX, newY, carte.getJoueurs()) && carte.obtenirContenuCase(newX, newY).equals(".");
    }

    // Vérifie si le joueur peut se déplacer vers la droite sur une case vide
    public static boolean peutBougerVersLaDroite(Carte carte, Joueur joueur) {
        int newX = joueur.obtenirPositionX() + 1;
        int newY = joueur.obtenirPositionY();

        // Vérifie s'il n'y a pas de collision avec un autre joueur et si la case est vide
        return !collisionAvecAutreJoueur(newX, newY, carte.getJoueurs()) && carte.obtenirContenuCase(newX, newY).equals(".");
    }

    
    static void deplacement(Joueur joueur,Carte carteJeu) {
        Scanner scanner = new Scanner(System.in);
        boolean mouvementValide = false;

        while (!mouvementValide) {
            try {
                System.out.println("Choisissez une direction (z - Haut / s - Bas / q - Gauche / d - Droite / b - Quitter) : ");
                String direction = scanner.nextLine().toLowerCase();

                int ancienX = joueur.obtenirPositionX();
                int ancienY = joueur.obtenirPositionY();

                switch (direction) {
                    case "z":
                        deplacerVersLeHaut(carteJeu.getJoueurs(), carteJeu, ancienX, ancienY, joueur);
                        break;
                    case "s":
                        deplacerVersLeBas(carteJeu.getJoueurs(), carteJeu, ancienX, ancienY, joueur);
                        break;
                    case "q":
                        deplacerVersLaGauche(carteJeu.getJoueurs(), carteJeu, ancienX, ancienY, joueur);
                        break;
                    case "d":
                        deplacerVersLaDroite(carteJeu.getJoueurs(), carteJeu, ancienX, ancienY, joueur);
                        break;
                    case "b":
                        System.out.println("\033[H\033[2J");
                        System.out.flush();
                        menu(); // Renvoie au menu
                        break;
                    default:
                        System.out.println("Direction invalide ! Veuillez choisir z, s, q, d ou b.");
                }

                // Vérifier si le joueur s'est réellement déplacé
              if (ancienX != joueur.obtenirPositionX() || ancienY != joueur.obtenirPositionY()) {
                    mouvementValide = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Erreur de saisie. Veuillez entrer une direction valide.");
                scanner.nextLine();
            }
        }
        carteJeu.afficher();
    }

      // Méthode pour vérifier s'il y a une collision avec un autre joueur en fonction des coordonnées
    public static boolean collisionAvecAutreJoueur(int newX, int newY, List<Joueur> joueurs) {
        // on parcourt une boucle de tout les joueurs qu'on récupére
        for (Joueur autreJoueur : joueurs) {
            if (autreJoueur != joueurs && autreJoueur.obtenirPositionX() == newX && autreJoueur.obtenirPositionY() == newY) {
                return true; // Il y a une collision avec un autre joueur
            }
        }
        return false; // Aucune collision avec un autre joueur
    }

    public static boolean estBloque(Carte carte, Joueur joueur) {
        return !peutBougerVersLeHaut(carte, joueur) && !peutBougerVersLeBas(carte, joueur)
                && !peutBougerVersLaGauche(carte, joueur) && !peutBougerVersLaDroite(carte, joueur);
    }
}
