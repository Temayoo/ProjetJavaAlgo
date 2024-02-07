package src.Model;

import java.util.List;


public class Joueur {

    // Variables représentant du joueur
    private String pseudo;
    private int positionX;
    private int positionY;
    private String symbole;



    // Constructeur pour initialiser le joueur avec un pseudo, des positions et un symbole
    public Joueur(String pseudo, int positionX, int positionY, String symbole) {
        this.pseudo = pseudo;
        this.positionX = positionX;
        this.positionY = positionY;
        this.symbole = symbole;
    }

    public String obtenirPseudo() {
        return pseudo;
    }

    public String obtenirSymbole() {
        return symbole;
    }

    // Méthode pour obtenir la position en X du joueur
    public int obtenirPositionX() {
        return positionX;
    }

    // Méthode pour obtenir la position en Y du joueur
    public int obtenirPositionY() {
        return positionY;
    }


    // Méthode pour déplacer le joueur vers le haut s'il y a une case libre
    public void deplacerVersLeHaut(List<Joueur> joueurs, Carte carte) {
        int newX = positionX;
        int newY = positionY - 1;

        if (!collisionAvecAutreJoueur(newX, newY, joueurs)) {
            if (carte.obtenirContenuCase(newX, newY).equals(".")) {
                positionY = newY;
            }
        } else {
            System.out.println("Collision avec un autre joueur ! Choisissez une autre direction.");
        }
    }

    // Méthode pour déplacer le joueur vers le bas s'il y a une case libre
    public void deplacerVersLeBas(List<Joueur> joueurs, Carte carte) {
        int newX = positionX;
        int newY = positionY + 1;

        if (!collisionAvecAutreJoueur(newX, newY, joueurs)) {
            if (carte.obtenirContenuCase(newX, newY).equals(".")) {
                positionY = newY;
            }
        } else {
            System.out.println("Collision avec un autre joueur ! Choisissez une autre direction.");
        }
    }

    // Méthode pour déplacer le joueur vers la gauche s'il y a une case libre
    public void deplacerVersLaGauche(List<Joueur> joueurs, Carte carte) {
        int newX = positionX - 1;
        int newY = positionY;

        if (!collisionAvecAutreJoueur(newX, newY, joueurs)) {
            if (carte.obtenirContenuCase(newX, newY).equals(".")) {
                positionX = newX;
            }
        } else {
            System.out.println("Collision avec un autre joueur ! Choisissez une autre direction.");
        }
    }

    // Méthode pour déplacer le joueur vers la droite s'il y a une case libre
    public void deplacerVersLaDroite(List<Joueur> joueurs, Carte carte) {
        int newX = positionX + 1;
        int newY = positionY;

        if (!collisionAvecAutreJoueur(newX, newY, joueurs)) {
            if (carte.obtenirContenuCase(newX, newY).equals(".")) {
                positionX = newX;
            }
        } else {

            System.out.println("Collision avec un autre joueur ! Choisissez une autre direction.");

        }
    }



    // Méthode pour vérifier s'il y a une collision avec un autre joueur
    public boolean collisionAvecAutreJoueur(int newX, int newY, List<Joueur> joueurs) {
        for (Joueur autreJoueur : joueurs) {
            if (autreJoueur != this && autreJoueur.obtenirPositionX() == newX && autreJoueur.obtenirPositionY() == newY) {
                return true; // Il y a une collision avec un autre joueur
            }
        }
        return false; // Aucune collision avec un autre joueur
    }




    // Méthode pour vérifier si le joueur peut bouger vers le haut
    public boolean peutBougerVersLeHaut(Carte carte) {
        return carte.obtenirContenuCase(positionX, positionY - 1).equals(".");
    }

    // Méthode pour vérifier si le joueur peut bouger vers le bas
    public boolean peutBougerVersLeBas(Carte carte) {
        return carte.obtenirContenuCase(positionX, positionY + 1).equals(".");
    }

    // Méthode pour vérifier si le joueur peut bouger vers la gauche
    public boolean peutBougerVersLaGauche(Carte carte) {
        return carte.obtenirContenuCase(positionX - 1, positionY).equals(".");
    }

    // Méthode pour vérifier si le joueur peut bouger vers la droite
    public boolean peutBougerVersLaDroite(Carte carte) {
        return carte.obtenirContenuCase(positionX + 1, positionY).equals(".");
    }
}
