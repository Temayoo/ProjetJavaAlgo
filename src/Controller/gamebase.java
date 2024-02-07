package src.Controller;

import src.Model.Carte;
import src.Model.Joueur;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class gamebase {

    private static boolean estTermine = false;
    private static Joueur joueur1;
    private static Joueur joueur2;

    public static void setJoueurActuel(Joueur joueurActuel) {
        gamebase.joueurActuel = joueurActuel;
    }
    public static Joueur getJoueurActuel() {
        return joueurActuel;
    }

    private static Carte carteJeu;



    private static Joueur joueurActuel;

    public static void demarrerJeu() {
        String[] pseudos = src.View.scannerPseudo.demanderPseudos();

        joueur1 = new src.Model.Joueur(pseudos[0], 5, 5, "1");
        joueur2 = new src.Model.Joueur(pseudos[1], 6, 5, "2");
        carteJeu = new Carte(12, 11, joueur1, joueur2);

        carteJeu.genererCarte();
        joueurActuel = (Math.random() < 0.5) ? joueur1 : joueur2;

        while (!estTermine) {
            carteJeu.afficher();
            tourJoueur(joueurActuel);
            joueurActuel = (joueurActuel == joueur1) ? joueur2 : joueur1;
        }
    }

    private static void tourJoueur(Joueur joueur) {
        System.out.println("Tour de " + joueur.obtenirPseudo());

        // Sauvegarde des coordonnées actuelles du joueur
        int ancienX = joueur.obtenirPositionX();
        int ancienY = joueur.obtenirPositionY();

        deplacement(joueur);

        // Vérifier si le joueur s'est réellement déplacé
        if (ancienX == joueur.obtenirPositionX() && ancienY == joueur.obtenirPositionY()) {
            System.out.println("Le déplacement n'est pas valide. Veuillez choisir une autre direction.");
            tourJoueur(joueur); // Répéter le tour du joueur
        } else {
            placerX(joueur);
        }
    }

    private static void deplacement(Joueur joueur) {
        Scanner scanner = new Scanner(System.in);
        boolean mouvementValide = false;

        while (!mouvementValide) {
            try {
                System.out.println("Choisissez une direction (z pour haut, s pour bas, q pour gauche, d pour droite) : ");
                String direction = scanner.nextLine().toLowerCase();

                int ancienX = joueur.obtenirPositionX();
                int ancienY = joueur.obtenirPositionY();

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
        carteJeu.afficher();
    }

    private static void placerX(Joueur joueur) {
        Scanner scanner = new Scanner(System.in);
        boolean coordonneesValides = false;

        while (!coordonneesValides) {
            try {
                System.out.println("Choisissez les coordonnées où placer 'X' (format : x y) : ");
                int x = scanner.nextInt();
                int y = scanner.nextInt();

                if (x < 0 || x > carteJeu.obtenirTailleX() || y < 0 || y > carteJeu.obtenirTailleY()) {
                    System.out.println("Erreur : Veuillez entrer deux entiers valides.");
                    scanner.nextLine();
                    placerX(joueur);
                }

                if (carteJeu.obtenirContenuCase(x, y).equals(".")) {
                    carteJeu.placerX(x, y);
                    System.out.println("'X' placé avec succès !");
                    coordonneesValides = true;
                } else {
                    System.out.println("Vous ne pouvez placer 'X' que sur une case libre ('.').");
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
