package src.Model;

import java.util.List;

public class Joueur {

    // Variables représentant un joueur
    private String pseudo;
    private int positionX;
    private int positionY;
    private String symbole;
    private int score;

    // Constructeur pour initialiser le joueur avec un pseudo, des positions et un symbole
    public Joueur(String pseudo, int positionX, int positionY, String symbole, int score) {
        this.pseudo = pseudo;
        this.positionX = positionX;
        this.positionY = positionY;
        this.symbole = symbole;
        this.score = score;
    }

    // Méthode pour obtenir le pseudo du joueur
    public String obtenirPseudo() {
        return pseudo;
    }

    // Méthode pour obtenir le symbole du joueur
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

    // Méthode pour obtenir le score du joueur
    public int obtenirScore() {
        return score;
    }

    // Méthode pour mettre à jour le score du joueur
    public void mettreAJourScore(int points) {
        score += points;
    }



    // Méthode pour déplacer le joueur vers le haut s'il y a une case libre pas de joueur en fonction de coordonées et un "."
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

    // Méthode pour déplacer le joueur vers le bas s'il y a une case libre pas de joueur en fonction de coordonées et un "."
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

    // Méthode pour déplacer le joueur vers le gauche s'il y a une case libre pas de joueur en fonction de coordonées et un "."
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

    // Méthode pour déplacer le joueur vers le droite s'il y a une case libre pas de joueur en fonction de coordonées et un "."
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


    // Méthode pour vérifier s'il y a une collision avec un autre joueur en fonction des coordonnées
    public boolean collisionAvecAutreJoueur(int newX, int newY, List<Joueur> joueurs) {
        // on parcourt une boucle de tout les joueurs qu'on récupére
        for (Joueur autreJoueur : joueurs) {
            if (autreJoueur != this && autreJoueur.obtenirPositionX() == newX && autreJoueur.obtenirPositionY() == newY) {
                return true; // Il y a une collision avec un autre joueur
            }
        }
        return false; // Aucune collision avec un autre joueur
    }


    // Vérifie si le joueur peut se déplacer vers le haut sur une case vide
    public boolean peutBougerVersLeHaut(Carte carte) {
        int newX = obtenirPositionX();
        int newY = obtenirPositionY() - 1;

        // Vérifie s'il n'y a pas de collision avec un autre joueur et si la case est vide
        return !collisionAvecAutreJoueur(newX, newY, carte.getJoueurs()) && carte.obtenirContenuCase(newX, newY).equals(".");
    }

    // Vérifie si le joueur peut se déplacer vers le bas sur une case vide
    public boolean peutBougerVersLeBas(Carte carte) {
        int newX = obtenirPositionX();
        int newY = obtenirPositionY() + 1;

        // Vérifie s'il n'y a pas de collision avec un autre joueur et si la case est vide
        return !collisionAvecAutreJoueur(newX, newY, carte.getJoueurs()) && carte.obtenirContenuCase(newX, newY).equals(".");
    }

    // Vérifie si le joueur peut se déplacer vers la gauche sur une case vide
    public boolean peutBougerVersLaGauche(Carte carte) {
        int newX = obtenirPositionX() - 1;
        int newY = obtenirPositionY();

        // Vérifie s'il n'y a pas de collision avec un autre joueur et si la case est vide
        return !collisionAvecAutreJoueur(newX, newY, carte.getJoueurs()) && carte.obtenirContenuCase(newX, newY).equals(".");
    }

    // Vérifie si le joueur peut se déplacer vers la droite sur une case vide
    public boolean peutBougerVersLaDroite(Carte carte) {
        int newX = obtenirPositionX() + 1;
        int newY = obtenirPositionY();

        // Vérifie s'il n'y a pas de collision avec un autre joueur et si la case est vide
        return !collisionAvecAutreJoueur(newX, newY, carte.getJoueurs()) && carte.obtenirContenuCase(newX, newY).equals(".");
    }

    // Vérifie si tous les joueurs sont bloqués dans toutes les directions sur la carte
    public static boolean joueursBloques(List<Joueur> joueurs, Carte carte) {
        //boucle qui parcourt tout les joueurs dans une boucle
        for (Joueur joueur : joueurs) {
            if (joueurBloque(joueur, joueurs, carte)) {
                return true; // Au moins un joueur est bloqué dans toutes les directions
            }
        }
        return false; // Aucun joueur n'est bloqué dans toutes les directions
    }

    // Vérifie si un joueur est bloqué dans toutes les directions sur la carte
    private static boolean joueurBloque(Joueur joueur, List<Joueur> joueurs, Carte carte) {
        //on appelle les fonctions si on peut bouger dans tous les sens + ! pour verifier si on peut pas bouger
        boolean bloqueHaut = !joueur.peutBougerVersLeHaut(carte);
        boolean bloqueBas = !joueur.peutBougerVersLeBas(carte);
        boolean bloqueGauche = !joueur.peutBougerVersLaGauche(carte);
        boolean bloqueDroite = !joueur.peutBougerVersLaDroite(carte);

        // Si le joueur est bloqué dans toutes les directions, il est considéré comme bloqué
        return bloqueHaut && bloqueBas && bloqueGauche && bloqueDroite;
    }

    // Vérifie si le joueur est bloqué dans toutes les directions sur la carte
    public boolean estBloque(Carte carte) {
        return !peutBougerVersLeHaut(carte) && !peutBougerVersLeBas(carte)
                && !peutBougerVersLaGauche(carte) && !peutBougerVersLaDroite(carte);
    }

}