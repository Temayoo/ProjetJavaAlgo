package src.Controller;

import src.Model.Carte;
import src.Model.Joueur;
import src.View.CreationFichier;
import src.View.scannerPseudo;
import src.View.cliFinDePartie;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static src.Controller.trieScore.chargerScores;
import static src.Controller.trieScore.sauvegarderScores;

public class gamebase {
    // Chemin pour acceder au document txt ou sont ranger les scores
    public static final String FICHIER_SCORES = "DossierScore/score.txt";
    // List/Map qui enregistre les scores pseudo/scores
    public static final Map<String, Integer> scores = new HashMap<>();

    public static boolean estTermine = false;

    // Méthode getter pour estTermine
    public static boolean isEstTermine() {
        return estTermine;
    }

    // Méthode setter pour estTermine
    public static void setEstTermine(boolean estTermine) {
        gamebase.estTermine = estTermine;
    }


    // Méthode principale pour démarrer le jeu
    public static void demarrerJeu() {
        chargerScores();
        // Demande les pseudos des joueurs
        String[] pseudos = scannerPseudo.demanderPseudos();

        // Initialisation des joueurs et de la carte
        Joueur joueur1 = new src.Model.Joueur(pseudos[0], 6, 6, "1  ");
        Joueur joueur2 = new src.Model.Joueur(pseudos[1], 6, 5, "2  ");
        Carte carteJeu = new Carte(13, 12, joueur1, joueur2);

        // Génération de la carte
        carteJeu.genererCarte();

        // Sélection aléatoire du premier joueur
        Joueur joueurActuel = (Math.random() < 0.5) ? joueur1 : joueur2;
        estTermine = false;

        Joueur gagnant = null;
        Joueur perdant = null;

        // Boucle principale du jeu
        while (!estTermine) {
            carteJeu.afficher();
            tour.tourJoueur(joueurActuel, carteJeu);
            joueurActuel = (joueurActuel == joueur1) ? joueur2 : joueur1;
        }

        // Vérification des joueurs bloqués
        for (Joueur joueur : carteJeu.getJoueurs()) {
            if (mouvement.estBloque(carteJeu, joueur)) {
                perdant = joueur;
                gagnant = (joueur == joueur1) ? joueur2 : joueur1;
                break;  // Sortir de la boucle dès qu'un joueur est bloqué
            }
        }

        // Enregistrement des scores dans le fichier avant d'afficher le message de fin
        mettreAJourScores(gagnant, perdant);
        CreationFichier.ecrireScore(FICHIER_SCORES, scores);

        // Affichage du message de fin si la partie a un gagnant
        if (gagnant != null && perdant != null) {
            System.out.println("\r\n" + "Le joueur " + gagnant.obtenirPseudo() + " a gagné la partie!" + "\r\n");
            cliFinDePartie.finDePartie();
        }
        sauvegarderScores();
    }

    // Méthode pour mettre à jour les scores des joueurs
    private static void mettreAJourScores(Joueur gagnant, Joueur perdant) {
        String pseudoGagnant = gagnant.obtenirPseudo();
        String pseudoPerdant = perdant.obtenirPseudo();

        scores.put(pseudoGagnant, scores.getOrDefault(pseudoGagnant, 0) + 5);
        scores.put(pseudoPerdant, scores.getOrDefault(pseudoPerdant, 0) - 2);
    }

}