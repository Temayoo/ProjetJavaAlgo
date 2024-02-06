package src.Controller;

import src.Model.Carte;
import src.Model.Joueur;

import java.util.Scanner;

public class gamebase {

    private static boolean estTermine = false;
    private static Joueur joueur1;
    private static Joueur joueur2;
    private static Carte carteJeu;
    private static Joueur joueurActuel; // Ajout d'une variable pour suivre le joueur actuel

    public static void demarrerJeu() {
        // Appeler la méthode demanderPseudos() de la nouvelle classe scannerPseudo
        String[] pseudos = src.View.scannerPseudo.demanderPseudos();

        // Initialisation des joueurs et de la carte
        joueur1 = new src.Model.Joueur(pseudos[0], 5, 5);
        joueur2 = new src.Model.Joueur(pseudos[1], 6, 5);
        carteJeu = new Carte(12, 11);

        // Générer la carte
        carteJeu.genererCarte();

        // Initialiser le joueur actuel de manière aléatoire
        joueurActuel = (Math.random() < 0.5) ? joueur1 : joueur2;

        // Boucle principale du jeu
        while (!estTermine) {
            carteJeu.afficher();
            tourJoueur(joueurActuel);
            joueurActuel = (joueurActuel == joueur1) ? joueur2 : joueur1; // Changer de joueur
        }
    }

    private static void tourJoueur(Joueur joueur) {
        System.out.println("Tour de " + joueur.obtenirPseudo());

        Scanner scanner = new Scanner(System.in);
        System.out.println("Choisissez une direction (z pour haut, s pour bas, q pour gauche, d pour droite) : ");
        String direction = scanner.nextLine();

        switch (direction.toLowerCase()) {
            case "z":
                joueur.deplacerVersLeHaut();
                break;
            case "s":
                joueur.deplacerVersLeBas();
                break;
            case "q":
                joueur.deplacerVersLaGauche();
                break;
            case "d":
                joueur.deplacerVersLaDroite();
                break;
            default:
                System.out.println("Direction invalide !");
        }

        // Mettez à jour l'affichage après le déplacement
        carteJeu.afficher();
    }
}
