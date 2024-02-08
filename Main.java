import java.io.IOException;

import static src.View.CreationFichier.creationFichier;

public class Main {
    public static void main(String[] args) throws IOException{
        creationFichier();
        src.View.cli.menu(); // appelle la fonction menu qui se trouve dans le dossier cli

    }
}


