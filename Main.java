import src.View.cli;

import javax.swing.text.View;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException{
        CreationFichier.creationFichierScore();
        CreationFichier.ecrireScore("coucou c'est moi tchoupi");
        CreationFichier.lectureFichier();
        src.View.cli.menu(); // appelle la fonction menu qui se trouve dans le dossier cli
    }
}


