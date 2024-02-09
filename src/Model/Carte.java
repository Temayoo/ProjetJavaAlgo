package src.Model;

import java.util.ArrayList;
import java.util.List;

public class Carte {

    // Variables pour la taille de la carte, la grille et les joueurs
    private int tailleX, tailleY;
    private String[][] grille;

    List<Joueur> joueurs;
    private Joueur joueur1;
    private Joueur joueur2;

    public Joueur getJoueur1() {
        return joueur1;
    }

    public void setJoueur1(Joueur joueur1) {
        this.joueur1 = joueur1;
    }

    // Getter et Setter pour joueur2
    public Joueur getJoueur2() {
        return joueur2;
    }

    public void setJoueur2(Joueur joueur2) {
        this.joueur2 = joueur2;
    }

    // Getter et Setter pour la liste de joueurs
    public List<Joueur> getJoueurs() {
        return joueurs;
    }

    public void setJoueurs(List<Joueur> joueurs) {
        this.joueurs = joueurs;
    }

    // Constructeur de la classe Carte avec initialisation des joueurs et de la grille
// Ajoutez cela à votre constructeur dans la classe Carte
    public Carte(int tailleX, int tailleY, Joueur joueur1, Joueur joueur2) {
        this.tailleX = tailleX;
        this.tailleY = tailleY;
        this.joueur1 = joueur1;
        this.joueur2 = joueur2;
        grille = new String[tailleY][tailleX];

        // Initialisez la liste de joueurs
        joueurs = new ArrayList<>();
        joueurs.add(joueur1);
        joueurs.add(joueur2);
    }

    // Méthode pour générer la carte avec des rebords
    public void genererCarte() {
        for (int y = 0; y < tailleY; y++) {
            for (int x = 0; x < tailleX; x++) {
                // Ajouter des rebords à la carte
                if (y == 0 || y == tailleY - 1 || x == 0 || x == tailleX - 1) {
                    grille[y][x] = "#";  // Utiliser "#" pour les rebords
                } else {
                    grille[y][x] = ".";  // Utiliser "." pour les cases intérieures
                }
            }
        }
    }



// Méthode pour afficher la carte avec les positions des joueurs et les coordonnées
    public void afficher() {
        // Trouver la longueur maximale des chiffres des colonnes
        int maxColWidth = String.valueOf(tailleX - 1).length() + 2;  // 2 pour les espaces avant et après

        // Afficher la ligne horizontale avec les coordonnées des colonnes
        System.out.print("    ");  // Décalage ajusté pour aligner les chiffres avec la grille
        for (int x = 0; x < tailleX; x++) {
            System.out.printf("%-" + maxColWidth + "s", x);
        }
        System.out.println();  // Passer à la ligne après la ligne des coordonnées des colonnes

        for (int y = 0; y < tailleY; y++) {
            // Afficher le numéro de ligne à gauche
            System.out.printf("%-" + maxColWidth + "s", y);

            for (int x = 0; x < tailleX; x++) {
                // Afficher le symbole du joueur1 si ses coordonnées correspondent à la case actuelle
                if (joueur1.obtenirPositionX() == x && joueur1.obtenirPositionY() == y) {
                    System.out.print(joueur1.obtenirSymbole() + " ");
                }
                // Afficher le symbole du joueur2 si ses coordonnées correspondent à la case actuelle
                else if (joueur2.obtenirPositionX() == x && joueur2.obtenirPositionY() == y) {
                    System.out.print(joueur2.obtenirSymbole() + " ");
                } else {
                    System.out.printf("%-" + maxColWidth + "s", grille[y][x] + " ");  // Afficher le contenu de la grille
                }
            }
            System.out.println();  // Passer à la ligne après chaque ligne de la grille
        }
    }





    // Méthode pour obtenir le contenu d'une case à des coordonnées spécifiques
    public String obtenirContenuCase(int x, int y) {
        return grille[y][x];
    }

    // Méthode pour placer un "X" à des coordonnées spécifiques sur la grille
    public void placerX(int x, int y) {
        grille[y][x] = "X";
    }

    public int obtenirTailleX() {
        return tailleX;
    }

    public int obtenirTailleY() {
        return tailleY;
    }

}

