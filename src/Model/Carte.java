package src.Model;

public class Carte {

    // Variables pour la taille de la carte, la grille et les joueurs
    private int tailleX, tailleY;
    private String[][] grille;
    private Joueur joueur1;
    private Joueur joueur2;

    // Constructeur de la classe Carte avec initialisation des joueurs et de la grille
    public Carte(int tailleX, int tailleY, Joueur joueur1, Joueur joueur2) {
        this.tailleX = tailleX;
        this.tailleY = tailleY;
        this.joueur1 = joueur1;
        this.joueur2 = joueur2;
        grille = new String[tailleY][tailleX];
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

    // Méthode pour afficher la carte avec les positions des joueurs
    public void afficher() {
        for (int y = 0; y < tailleY; y++) {
            for (int x = 0; x < tailleX; x++) {
                // Afficher le symbole du joueur1 si ses coordonnées correspondent à la case actuelle
                if (joueur1.obtenirPositionX() == x && joueur1.obtenirPositionY() == y) {
                    System.out.print(joueur1.obtenirSymbole() + " ");
                }
                // Afficher le symbole du joueur2 si ses coordonnées correspondent à la case actuelle
                else if (joueur2.obtenirPositionX() == x && joueur2.obtenirPositionY() == y) {
                    System.out.print(joueur2.obtenirSymbole() + " ");
                } else {
                    System.out.print(grille[y][x] + " ");  // Afficher le contenu de la grille
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
