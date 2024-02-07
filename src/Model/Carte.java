package src.Model;

public class Carte {

    private int tailleX, tailleY;
    private String[][] grille;
    private Joueur joueur1;
    private Joueur joueur2;

    public Carte(int tailleX, int tailleY, Joueur joueur1, Joueur joueur2) {
        this.tailleX = tailleX;
        this.tailleY = tailleY;
        this.joueur1 = joueur1;
        this.joueur2 = joueur2;
        grille = new String[tailleY][tailleX];
    }

    public void genererCarte() { // genere la carte + rebord avec double boucle for
        for (int y = 0; y < tailleY; y++) {
            for (int x = 0; x < tailleX; x++) {
                if (y == 0 || y == tailleY - 1 || x == 0 || x == tailleX - 1) {
                    grille[y][x] = "#";
                } else {
                    grille[y][x] = ".";
                }
            }
        }
    }

    public void afficher() {
        for (int y = 0; y < tailleY; y++) {
            for (int x = 0; x < tailleX; x++) {
                if (joueur1.obtenirPositionX() == x && joueur1.obtenirPositionY() == y) {
                    System.out.print(joueur1.obtenirSymbole() + " ");
                } else if (joueur2.obtenirPositionX() == x && joueur2.obtenirPositionY() == y) {
                    System.out.print(joueur2.obtenirSymbole() + " ");
                } else {
                    System.out.print(grille[y][x] + " ");
                }
            }
            System.out.println(); // Passer à la ligne après chaque ligne de la grille
        }
    }

    public String obtenirContenuCase(int x, int y) {
        return grille[y][x];
    }

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