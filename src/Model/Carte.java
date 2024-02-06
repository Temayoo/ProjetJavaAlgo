package src.Model;

public class Carte {

    private int tailleX, tailleY;
    private String[][] grille;
    private Joueur joueur1, joueur2;

    public Carte(int tailleX, int tailleY) { // Recuperation de la taille de la map + position joueur
        this.tailleX = tailleX;
        this.tailleY = tailleY;
        grille = new String[tailleY][tailleX];
        joueur1 = new Joueur("J1", tailleX / 2 - 1, tailleY / 2);
        joueur2 = new Joueur("J2", tailleX / 2, tailleY / 2);
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
                    System.out.print("1 ");
                } else if (joueur2.obtenirPositionX() == x && joueur2.obtenirPositionY() == y) {
                    System.out.print("2 ");
                } else {
                    System.out.print(grille[y][x] + " ");
                }
            }
            System.out.println();
        }
    }
}
