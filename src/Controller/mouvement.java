package src.Controller;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import src.Model.Carte;
import src.Model.Joueur;


public class mouvement {
    
 // Méthode pour déplacer le joueur vers le haut s'il y a une case libre
    public static void deplacerVersLeHaut(List<Joueur> joueurs, Carte carte,int  positionX, int positionY, Joueur joueur) {
        int newX = joueur.positionX;
        int newY = joueur.positionY - 1;

        if (!collisionAvecAutreJoueur(newX, newY, joueurs, joueurs)) {
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

        if (!collisionAvecAutreJoueur(newX, newY, joueurs, joueurs)) {
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

        if (!collisionAvecAutreJoueur(newX, newY, joueurs, joueurs)) {
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

        if (!collisionAvecAutreJoueur(newX, newY, joueurs, joueurs)) {
            if (carte.obtenirContenuCase(newX, newY).equals(".")) {
                joueur.positionX = newX;
            }
        } else {

            System.out.println("Collision avec un autre joueur ! Choisissez une autre direction.");

        }
    }

     // Méthode pour vérifier si le joueur peut bouger vers le haut
     public boolean peutBougerVersLeHaut(Carte carte,int positionX,int positionY) {
        return carte.obtenirContenuCase(positionX, positionY - 1).equals(".");
    }

    // Méthode pour vérifier si le joueur peut bouger vers le bas
    public boolean peutBougerVersLeBas(Carte carte,int positionX,int positionY) {
        return carte.obtenirContenuCase(positionX, positionY + 1).equals(".");
    }

    // Méthode pour vérifier si le joueur peut bouger vers la gauche
    public boolean peutBougerVersLaGauche(Carte carte,int positionX,int positionY) {
        return carte.obtenirContenuCase(positionX - 1, positionY).equals(".");
    }

    // Méthode pour vérifier si le joueur peut bouger vers la droite
    public boolean peutBougerVersLaDroite(Carte carte,int positionX,int positionY) {
        return carte.obtenirContenuCase(positionX + 1, positionY).equals(".");
    }

    
    static void deplacement(Joueur joueur,Carte carteJeu) {
        Scanner scanner = new Scanner(System.in);
        boolean mouvementValide = false;

        while (!mouvementValide) {
            try {
                System.out.println("Choisissez une direction (z pour haut, s pour bas, q pour gauche, d pour droite) : ");
                String direction = scanner.nextLine().toLowerCase();

                int ancienX = joueur.obtenirPositionX();
                int ancienY = joueur.obtenirPositionY();

                switch (direction) {
                    case "z":
                        deplacerVersLeHaut(carteJeu.getJoueurs(), carteJeu,ancienX,ancienY, joueur);
                        break;
                    case "s":
                        deplacerVersLeBas(carteJeu.getJoueurs(), carteJeu,ancienX,ancienY, joueur);
                        break;
                    case "q":
                        deplacerVersLaGauche(carteJeu.getJoueurs(), carteJeu,ancienX,ancienY, joueur);
                        break;
                    case "d":
                        deplacerVersLaDroite(carteJeu.getJoueurs(), carteJeu,ancienX,ancienY, joueur);
                        break;
                    default:
                        System.out.println("Direction invalide !");
                }

                // Vérifier si le joueur s'est réellement déplacé
                // TODO VERIFIER POURQUOI ON NE RENTRE PAS DANS LA CONDITION
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

      // Méthode pour vérifier s'il y a une collision avec un autre joueur
      public static boolean collisionAvecAutreJoueur(int newX, int newY, List<Joueur> joueurs,List<Joueur> joueurs2) {
        for (Joueur autreJoueur : joueurs) {
            if (joueurs2 != joueurs && ((Joueur) joueurs2).obtenirPositionX() == newX && ((Joueur) joueurs2).obtenirPositionY() == newY) {
                return true; // Il y a une collision avec un autre joueur
            }
        }
        return false; // Aucune collision avec un autre joueur
    }

}
