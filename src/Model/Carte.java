package src.Model;

public class Carte {

    int tailleX, tailleY;
    public String[][] grille;

    public Carte(int tailleX, int tailleY) {
        this.tailleX = tailleX;
        this.tailleY = tailleY;
        grille = new String[tailleY][tailleX];
    }

    public void genererCarte() {
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
                System.out.print(grille[y][x] + " ");
            }
            System.out.println();
        }
    }

    public void spawn(int x, int y, String visuel) {
        grille[y][x] = visuel;
    }
}
