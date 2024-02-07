package src.Controller;

import src.Model.Carte;
import src.Model.Joueur;

import java.util.Scanner;

public class gamebase {

    // Variable pour déterminer si le jeu est terminé
    private static boolean estTermine = false;


    private static Joueur joueur1;
    private static Joueur joueur2;


    private static Carte carteJeu;


    private static Joueur joueurActuel;

    // Méthode pour démarrer le jeu
    public static void demarrerJeu() {
        // Appeler la méthode demanderPseudos() de la nouvelle classe scannerPseudo
        String[] pseudos = src.View.scannerPseudo.demanderPseudos();

        // Initialisation des joueurs et de la carte
        joueur1 = new src.Model.Joueur(pseudos[0], 6, 5, "1");
        joueur2 = new src.Model.Joueur(pseudos[1], 6, 6, "2");
        carteJeu = new Carte(13, 12, joueur1, joueur2);  // Passer les joueurs à la carte

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

    // Méthode pour gérer le tour d'un joueur
    private static void tourJoueur(Joueur joueur) {
        // Un Sout pour savoir qui est entrain de jouer
        System.out.println("Tour de " + joueur.obtenirPseudo());

        // Déplacement
        deplacement(joueur);

        // Pose de la croix
        placerX(joueur);
    }

    // Méthode pour gérer le déplacement d'un joueur
    private static void deplacement(Joueur joueur) {
        Scanner scanner = new Scanner(System.in);
        boolean mouvementValide = false;
        while (!mouvementValide) {
            try {
                System.out.println("Choisissez une direction (z pour haut, s pour bas, q pour gauche, d pour droite) : ");
                String direction = scanner.nextLine().toLowerCase();

                switch (direction) {
                    case "z":
                        joueur.deplacerVersLeHaut(carteJeu);
                        mouvementValide = true;
                        break;
                    case "s":
                        joueur.deplacerVersLeBas(carteJeu);
                        mouvementValide = true;
                        break;
                    case "q":
                        joueur.deplacerVersLaGauche(carteJeu);
                        mouvementValide = true;
                        break;
                    case "d":
                        joueur.deplacerVersLaDroite(carteJeu);
                        mouvementValide = true;
                        break;
                    default:
                        System.out.println("Direction invalide !");
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Erreur de saisie. Veuillez entrer une direction valide.");
                scanner.nextLine();  // enlève la ligne incorrecte
            }
        }
    }

    // Méthode pour gérer la pose de la croix par un joueur
    private static void placerX(Joueur joueur) {
        Scanner scanner = new Scanner(System.in);
        boolean coordonneesValides = false;
        while (!coordonneesValides) {
            System.out.println("Choisissez les coordonnées où placer 'X' (format : x y) : ");
            int x = scanner.nextInt();
            int y = scanner.nextInt();

            if (carteJeu.obtenirContenuCase(x, y).equals(".")) {
                carteJeu.placerX(x, y);
                System.out.println("'X' placé avec succès !");
                coordonneesValides = true;
            } else {
                System.out.println("Vous ne pouvez placer 'X' que sur une case libre ('.').");
            }
        }
    }
}
