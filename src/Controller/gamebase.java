package src.Controller;

import src.Model.Carte;
import src.Model.Joueur;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import src.Controller.tour;

public class gamebase {

    private static final Joueur Joueur = null;
    private static final Carte Carte = null;
    private static boolean estTermine = false;
    
    public static boolean isEstTermine() {
        return estTermine;
    }

    public static void setEstTermine(boolean estTermine) {
        gamebase.estTermine = estTermine;
    }

    public static void demarrerJeu() {
        String[] pseudos = src.View.scannerPseudo.demanderPseudos();

        Joueur joueur1 = new src.Model.Joueur(pseudos[0], 5, 5, "1",0);
        Joueur joueur2 = new src.Model.Joueur(pseudos[1], 6, 5, "2",0);
        Carte carteJeu = new Carte(12, 11, joueur1, joueur2);

        carteJeu.genererCarte();
        Joueur joueurActuel = (Math.random() < 0.5) ? joueur1 : joueur2;

        while (!estTermine) {
            carteJeu.afficher();
            tour.tourJoueur( joueurActuel, carteJeu);
            joueurActuel = (joueurActuel == joueur1) ? joueur2 : joueur1;
        }
    
    }

}
