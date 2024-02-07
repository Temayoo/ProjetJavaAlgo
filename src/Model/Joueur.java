package src.Model;

public class Joueur {
    private String pseudo;
    private int positionX;
    private int positionY;
    private String symbole;

    public Joueur(String pseudo, int positionX, int positionY, String symbole) {
        this.pseudo = pseudo;
        this.positionX = positionX;
        this.positionY = positionY;
        this.symbole = symbole;
    }

    public String obtenirPseudo() {
        return pseudo;
    }

    public String obtenirSymbole() {
        return symbole;
    }

    public int obtenirPositionX() {
        return positionX;
    }

    public int obtenirPositionY() {
        return positionY;
    }

    public void deplacerVersLeHaut(Carte carte) {
        if (carte.obtenirContenuCase(positionX, positionY - 1).equals(".")) {
            positionY--;
        }
    }

    public void deplacerVersLeBas(Carte carte) {
        if (carte.obtenirContenuCase(positionX, positionY + 1).equals(".")) {
            positionY++;
        }
    }

    public void deplacerVersLaGauche(Carte carte) {
        if (carte.obtenirContenuCase(positionX - 1, positionY).equals(".")) {
            positionX--;
        }
    }

    public void deplacerVersLaDroite(Carte carte) {
        if (carte.obtenirContenuCase(positionX + 1, positionY).equals(".")) {
            positionX++;
        }
    }

    public boolean peutBougerVersLeHaut(Carte carte) {
        return carte.obtenirContenuCase(positionX, positionY - 1).equals(".");
    }

    public boolean peutBougerVersLeBas(Carte carte) {
        return carte.obtenirContenuCase(positionX, positionY + 1).equals(".");
    }

    public boolean peutBougerVersLaGauche(Carte carte) {
        return carte.obtenirContenuCase(positionX - 1, positionY).equals(".");
    }

    public boolean peutBougerVersLaDroite(Carte carte) {
        return carte.obtenirContenuCase(positionX + 1, positionY).equals(".");
    }

}