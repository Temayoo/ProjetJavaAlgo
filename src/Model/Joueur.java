package src.Model;

public class Joueur {
    private String pseudo;
    private int positionX;
    private int positionY;

    public Joueur(String pseudo, int positionX, int positionY) { // creation d'un joueur
        this.pseudo = pseudo;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public String obtenirPseudo() {
        return pseudo;
    }
    public void creationPseudo(String pseudo) {
        if (pseudo.length() < 2 || pseudo.length() > 10) {
            throw new IllegalArgumentException("Le pseudo doit avoir entre 2 et 10 caractères.");
        }
        this.pseudo = pseudo;
    }

    public int obtenirPositionX() {
        return positionX;
    }

    public int obtenirPositionY() {
        return positionY;
    }

    public void deplacerVersLeHaut() {
        positionY--;
    }

    public void deplacerVersLeBas() {
        positionY++;
    }

    public void deplacerVersLaGauche() {
        positionX--;
    }

    public void deplacerVersLaDroite() {
        positionX++;
    }
}
