package src.Controller;

import src.Model.Carte;
import src.Model.Joueur;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static src.Model.Joueur.joueursBloques;

public class gamebase {

    // Variable indiquant si le jeu est terminé
    private static boolean estTermine = false;

    // Joueurs du jeu
    private static Joueur joueur1;
    private static Joueur joueur2;

    // Carte du jeu
    private static Carte carteJeu;

    // Joueur actuel (celui du tour)
    private static Joueur joueurActuel;

    // Méthode pour démarrer le jeu
    public static void demarrerJeu() {
        // Demander les pseudos aux joueurs
        String[] pseudos = src.View.scannerPseudo.demanderPseudos();

        // Initialiser les joueurs et la carte
        joueur1 = new src.Model.Joueur(pseudos[0], 5, 5, "1");
        joueur2 = new src.Model.Joueur(pseudos[1], 6, 5, "2");
        carteJeu = new Carte(12, 11, joueur1, joueur2);

        // Générer la carte et déterminer aléatoirement le joueur actuel
        carteJeu.genererCarte();
        joueurActuel = (Math.random() < 0.5) ? joueur1 : joueur2;

        // Boucle principale du jeu
        while (!estTermine) {
            carteJeu.afficher();
            tourJoueur(joueurActuel);
            joueurActuel = (joueurActuel == joueur1) ? joueur2 : joueur1;
        }

        // Afficher le joueur bloqué lorsque le jeu est terminé
        for (Joueur joueur : carteJeu.getJoueurs()) {
            if (joueur.estBloque(carteJeu)) {
                System.out.println("Le jeu est terminé car le joueur " + joueur.obtenirPseudo() + " est bloqué !");
                return;
            }
        }
    }

    // Méthode représentant le tour d'un joueur
    private static void tourJoueur(Joueur joueur) {
        System.out.println("Tour de " + joueur.obtenirPseudo());

        // Sauvegarde des coordonnées actuelles du joueur pour verifier si il a bougé
        int ancienX = joueur.obtenirPositionX();
        int ancienY = joueur.obtenirPositionY();

        // Appel de la méthode de déplacement du joueur
        deplacement(joueur);

        // Vérifier si le joueur s'est réellement déplacé en regardant c'est coordonnées
        if (ancienX == joueur.obtenirPositionX() && ancienY == joueur.obtenirPositionY()) {
            System.out.println("Le déplacement n'est pas valide. Veuillez choisir une autre direction.");
            tourJoueur(joueur); // Répéter le tour du joueur
        } else {
            // Appel de la méthode de placement de "X"
            placerX(joueur);

            // Vérifier si tous les joueurs sont bloqués après le placement de "X"
            if (joueursBloques(carteJeu.getJoueurs(), carteJeu)) {
                carteJeu.afficher();
                System.out.println("Le jeu est terminé !");
                estTermine = true;  // Mettre fin au jeu
                return;  // Sortir de la méthode et du tour du joueur
            }
        }
    }

    // Méthode de déplacement du joueur
    private static void deplacement(Joueur joueur) {
        Scanner scanner = new Scanner(System.in);
        boolean mouvementValide = false;
        // Variable pour valider le changement de position du joueur
        while (!mouvementValide) {
            try {
                System.out.println("Choisissez une direction (z pour haut, s pour bas, q pour gauche, d pour droite) : ");
                String direction = scanner.nextLine().toLowerCase();

                int ancienX = joueur.obtenirPositionX();
                int ancienY = joueur.obtenirPositionY();

                // Déplacement du joueur en fonction de la direction choisie
                switch (direction) {
                    case "z":
                        joueur.deplacerVersLeHaut(carteJeu.getJoueurs(), carteJeu);
                        break;
                    case "s":
                        joueur.deplacerVersLeBas(carteJeu.getJoueurs(), carteJeu);
                        break;
                    case "q":
                        joueur.deplacerVersLaGauche(carteJeu.getJoueurs(), carteJeu);
                        break;
                    case "d":
                        joueur.deplacerVersLaDroite(carteJeu.getJoueurs(), carteJeu);
                        break;
                    default:
                        System.out.println("Direction invalide !");
                }

                // Vérifier si le joueur s'est réellement déplacé
                if (ancienX != joueur.obtenirPositionX() || ancienY != joueur.obtenirPositionY()) {
                    mouvementValide = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Erreur de saisie. Veuillez entrer une direction valide.");
                scanner.nextLine();
            }
        }
        // affiche la carte pour mettre a jour
        carteJeu.afficher();
    }

    // Méthode de placement de "X" sur la carte
    private static void placerX(Joueur joueur) {
        Scanner scanner = new Scanner(System.in);
        boolean coordonneesValides = false;
        // variable pour savoir si les coordonnées sont bon et qu'ils y a aucun joueur sur la carte
        while (!coordonneesValides) {
            try {
                System.out.println("Choisissez les coordonnées où placer 'X' (format : x y) : ");
                int x = scanner.nextInt();
                int y = scanner.nextInt();

                // Vérifier la validité des coordonnées
                if (x < 0 || x >= carteJeu.obtenirTailleX() || y < 0 || y >= carteJeu.obtenirTailleY()) {
                    System.out.println("Erreur : Veuillez entrer des coordonnées valides.");
                    scanner.nextLine();
                    continue;
                }

                // Vérifier s'il y a un joueur à la position choisie
                boolean joueurPresent = false;
                for (Joueur autreJoueur : carteJeu.getJoueurs()) {
                    if (autreJoueur.obtenirPositionX() == x && autreJoueur.obtenirPositionY() == y) {
                        joueurPresent = true;
                        break;
                    }
                }

                // Gérer le placement de "X" en fonction des conditions
                if (joueurPresent) {
                    System.out.println("Vous ne pouvez pas placer 'X' sur la position d'un autre joueur.");
                } else if (carteJeu.obtenirContenuCase(x, y).equals(".")) {
                    carteJeu.placerX(x, y);
                    System.out.println("'X' placé avec succès !");
                    coordonneesValides = true;
                } else {
                    System.out.println("Vous ne pouvez placer 'X' que sur une case libre ('.')");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erreur : Veuillez entrer deux entiers valides.");
                scanner.nextLine();
            } catch (NoSuchElementException e) {
                System.out.println("Erreur : Entrée non valide. Assurez-vous d'entrer deux entiers.");
                scanner.nextLine();
            }
        }
    }
}
