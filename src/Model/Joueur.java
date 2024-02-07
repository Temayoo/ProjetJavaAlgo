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


    public boolean peutBougerVersLeHaut(Carte carte) {
        int newX = obtenirPositionX();
        int newY = obtenirPositionY() - 1;

        return !collisionAvecAutreJoueur(newX, newY, carte.getJoueurs()) && carte.obtenirContenuCase(newX, newY).equals(".");
    }

    public boolean peutBougerVersLeBas(Carte carte) {
        int newX = obtenirPositionX();
        int newY = obtenirPositionY() + 1;

        return !collisionAvecAutreJoueur(newX, newY, carte.getJoueurs()) && carte.obtenirContenuCase(newX, newY).equals(".");
    }

    public boolean peutBougerVersLaGauche(Carte carte) {
        int newX = obtenirPositionX() - 1;
        int newY = obtenirPositionY();

        return !collisionAvecAutreJoueur(newX, newY, carte.getJoueurs()) && carte.obtenirContenuCase(newX, newY).equals(".");
    }

    public boolean peutBougerVersLaDroite(Carte carte) {
        int newX = obtenirPositionX() + 1;
        int newY = obtenirPositionY();

        return !collisionAvecAutreJoueur(newX, newY, carte.getJoueurs()) && carte.obtenirContenuCase(newX, newY).equals(".");
    }

    public static boolean joueursBloques(List<Joueur> joueurs, Carte carte) {
        for (Joueur joueur : joueurs) {
            if (joueurBloque(joueur, joueurs, carte)) {
                return true; // Le joueur est bloqué dans toutes les directions
            }
        }
        return false; // Aucun joueur n'est bloqué dans toutes les directions
    }

    private static boolean joueurBloque(Joueur joueur, List<Joueur> joueurs, Carte carte) {
        boolean bloqueHaut = !joueur.peutBougerVersLeHaut(carte);
        boolean bloqueBas = !joueur.peutBougerVersLeBas(carte);
        boolean bloqueGauche = !joueur.peutBougerVersLaGauche(carte);
        boolean bloqueDroite = !joueur.peutBougerVersLaDroite(carte);

        // Si le joueur est bloqué dans toutes les directions, il est considéré comme bloqué
        return bloqueHaut && bloqueBas && bloqueGauche && bloqueDroite;
    }
    public boolean estBloque(Carte carte) {
        return !peutBougerVersLeHaut(carte) && !peutBougerVersLeBas(carte)
                && !peutBougerVersLaGauche(carte) && !peutBougerVersLaDroite(carte);
    }


}