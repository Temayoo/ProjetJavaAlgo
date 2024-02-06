package src.Controller;

import src.Model.Carte;

import java.io.IOException;

public class gamebase {

    public static boolean estTermine = false;

    public static void main(String[] args) throws IOException {
        src.View.cli.menu();
    }

    public static void demarrerJeu() {
        System.out.println("Le jeu commence !");

        // Instancier la classe Carte avec la taille souhaitée
        Carte carteJeu = new Carte(12, 11);

        // Générer la carte
        carteJeu.genererCarte();

        // Afficher la carte initiale avec les joueurs
        carteJeu.spawn(1, 1, "1"); // Position initiale du joueur 1
        carteJeu.spawn(9, 8, "2"); // Position initiale du joueur 2
        carteJeu.afficher();


    }
}
