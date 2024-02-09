package src.Controller;

import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import src.Model.Joueur;
import src.Model.Carte;

import static src.Model.Joueur.joueur1;
import static src.Model.Joueur.joueur2;

public class tour {

     static void tourJoueur(Joueur joueur, Carte carteJeu) {
        System.out.println("Tour de " + joueur.obtenirPseudo());

        // Sauvegarde des coordonnées actuelles du joueur
        int ancienX = joueur.obtenirPositionX();
        int ancienY = joueur.obtenirPositionY();

        mouvement.deplacement(joueur, carteJeu);
         System.out.println("\033[H\033[2J");
         System.out.flush();

        // Vérifier si le joueur s'est réellement déplacé
        if (ancienX == joueur.obtenirPositionX() && ancienY == joueur.obtenirPositionY()) {
            System.out.println("\r\n" +"Le déplacement n'est pas valide. Veuillez choisir une autre direction.");
            tourJoueur(joueur, carteJeu); // Répéter le tour du joueur
        } else {
            placerX(joueur, carteJeu);
            System.out.println("\033[H\033[2J");
            System.out.flush();
        }
        // Vérifier si tous les joueurs sont bloqués après le placement de "X"
        if (joueursBloques(carteJeu.getJoueurs(), carteJeu)) {
            carteJeu.afficher();
            System.out.println( "\r\n" + "Le jeu est terminé !");
            gamebase.estTermine = true;  // Mettre fin au jeu
        }

    }
        // methode placerX permet de mettre les bloques sur la carte pour gagner le jeu

    private static void placerX(Joueur joueur, Carte carteJeu) {
        Scanner scanner = new Scanner(System.in);
        boolean coordonneesValides = false;

        while (!coordonneesValides) {
            try {
                System.out.println("\r\n" + "Choisissez les coordonnées où placer 'X' (format : Largeur , Longueur) : ");

                // Lire la ligne complète
                String input = scanner.nextLine();

                // Utiliser un scanner séparé pour analyser la ligne
                Scanner inputScanner = new Scanner(input);

                // Vérifier si deux entiers peuvent être lus
                if (inputScanner.hasNextInt()) {
                    int x = inputScanner.nextInt();

                    if (inputScanner.hasNextInt()) {
                        int y = inputScanner.nextInt();

                        // Vérifier si les coordonnées sont valides
                        if (x >= 0 && x < carteJeu.obtenirTailleX() && y >= 0 && y < carteJeu.obtenirTailleY()) {
                            // Vérifier si la case est occupée par un joueur
                            boolean joueurPresent = carteJeu.getJoueurs().stream()
                                    .anyMatch(autreJoueur -> autreJoueur.obtenirPositionX() == x && autreJoueur.obtenirPositionY() == y);

                            // Gérer le placement de "X" en fonction des conditions
                            if (joueurPresent) {
                                System.out.println("\r\n" + "Vous ne pouvez pas placer 'X' sur la position d'un autre joueur.");
                            } else if (carteJeu.obtenirContenuCase(x, y).equals(".")) {
                                carteJeu.placerX(x, y);
                                System.out.println("\r\n" + "'X' placé avec succès !");
                                coordonneesValides = true;
                            } else {
                                System.out.println("\r\n" + "Vous ne pouvez placer 'X' que sur une case libre ('.')");
                            }
                        } else {
                            System.out.println("\r\n" + "Erreur : Veuillez entrer deux entiers valides dans les limites de la carte.");
                        }
                    } else {
                        System.out.println("\r\n" + "Erreur : Veuillez entrer deux entiers valides.");
                    }
                } else {
                    System.out.println("\r\n" + "Erreur : Veuillez entrer deux entiers valides.");
                }

                // Fermer le scanner temporaire
                inputScanner.close();
            } catch (InputMismatchException e) {
                System.out.println("\r\n" + "Erreur : Veuillez entrer deux entiers valides.");
            }
        }
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
        boolean bloqueHaut = !mouvement.peutBougerVersLeHaut(carte, joueur);
        boolean bloqueBas = !mouvement.peutBougerVersLeBas(carte, joueur);
        boolean bloqueGauche = !mouvement.peutBougerVersLaGauche(carte, joueur);
        boolean bloqueDroite = !mouvement.peutBougerVersLaDroite(carte, joueur);

        // Si le joueur est bloqué dans toutes les directions, il est considéré comme bloqué
        return bloqueHaut && bloqueBas && bloqueGauche && bloqueDroite;
    }

   

}
