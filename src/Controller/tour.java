package src.Controller;

import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import src.Model.Joueur;
import src.Model.Carte;

public class tour {

     static void tourJoueur(Joueur joueur, Carte carteJeu) {
        System.out.println("Tour de " + joueur.obtenirPseudo());

        // Sauvegarde des coordonnées actuelles du joueur
        int ancienX = joueur.obtenirPositionX();
        int ancienY = joueur.obtenirPositionY();

        mouvement.deplacement(joueur, carteJeu);

        // Vérifier si le joueur s'est réellement déplacé
        if (ancienX == joueur.obtenirPositionX() && ancienY == joueur.obtenirPositionY()) {
            System.out.println("Le déplacement n'est pas valide. Veuillez choisir une autre direction.");
            tourJoueur(joueur, carteJeu); // Répéter le tour du joueur
        } else {
            placerX(joueur, carteJeu);
        }
        // Vérifier si tous les joueurs sont bloqués après le placement de "X"
        if (joueursBloques(carteJeu.getJoueurs(), carteJeu)) {
            carteJeu.afficher();
            System.out.println("Le jeu est terminé !");
            gamebase.estTermine = true;  // Mettre fin au jeu

            // Trouver le joueur gagnant et perdant
        }
    }
    
    private static void placerX(Joueur joueur, Carte carteJeu) {
        Scanner scanner = new Scanner(System.in);
        boolean coordonneesValides = false;

        while (!coordonneesValides) {
            try {
                System.out.println("Choisissez les coordonnées où placer 'X' (format : x y) : ");
                int x = scanner.nextInt();
                int y = scanner.nextInt();

                
                if (x < 0 || x >= carteJeu.obtenirTailleX() || y < 0 || y >= carteJeu.obtenirTailleY()) {
                    System.out.println("Erreur : Veuillez entrer deux entiers valides.");
                    scanner.nextLine();
                    placerX(joueur, carteJeu);
                    continue;
                }

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
