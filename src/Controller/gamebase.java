package src.Controller;

import src.Model.Carte;
import src.Model.Joueur;


public class gamebase {

    private static final Joueur Joueur = null;
    private static final Carte Carte = null;
    public static boolean estTermine = false;
    
    public static boolean isEstTermine() {
        return estTermine;
    }

    public static void setEstTermine(boolean estTermine) {
        gamebase.estTermine = estTermine;
    }

    public static void demarrerJeu() {
        String[] pseudos = src.View.scannerPseudo.demanderPseudos();

        Joueur joueur1 = new src.Model.Joueur(pseudos[0], 5, 5, "1");
        Joueur joueur2 = new src.Model.Joueur(pseudos[1], 6, 5, "2");
        Carte carteJeu = new Carte(12, 11, joueur1, joueur2);

        carteJeu.genererCarte();
        Joueur joueurActuel = (Math.random() < 0.5) ? joueur1 : joueur2;
        estTermine = false;

        while (!estTermine) {
            carteJeu.afficher();
            tour.tourJoueur( joueurActuel, carteJeu);
            joueurActuel = (joueurActuel == joueur1) ? joueur2 : joueur1;
        }

        // Afficher le joueur bloqué lorsque le jeu est terminé
        for (Joueur joueur : carteJeu.getJoueurs()) {
            if (mouvement.estBloque(carteJeu, joueur)) {
                // Trouver le joueur gagnant et perdant
                Joueur perdant = joueur;
                Joueur gagnant = (joueur == joueur1) ? joueur2 : joueur1;

                // Afficher le joueur gagnant
                System.out.println( "\r\n" + "Le joueur " + gagnant.obtenirPseudo() + " a gagné la partie!" + "\r\n" );
                src.View.cliFinDePartie.finDePartie();
            }
        }
    }

}
