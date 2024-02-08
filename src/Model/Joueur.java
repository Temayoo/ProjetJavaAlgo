package src.Model;

import java.util.List;
import src.Controller.gamebase;


public class Joueur {

    // Variables représentant du joueur
    private String pseudo;
    public int positionX;
    public int positionY;
    private String symbole;


    public static Joueur joueur1;
    public static Joueur joueur2;


    public static Joueur joueurActuel ;

    public static Carte carteJeu;


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

    public static void setJoueurActuel(Joueur Joueur) {
        joueurActuel = Joueur;
    }
    public static Joueur getJoueurActuel() {
        return joueurActuel;
    }
}
