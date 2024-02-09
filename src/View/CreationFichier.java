package src.View;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class CreationFichier {

    public static void creationFichier()
    {
        try {

            // Recevoir le fichier
            File f = new File("DossierScore/score.txt");

            // Créer un nouveau fichier
            // Vérifier s'il n'existe pas
            if (f.createNewFile())
                System.out.println("File created");
            else
                System.out.println("File already exists");
        }
        catch (Exception e) {
            System.err.println(e);
        }
    }


    // Méthode pour écrire les scores dans un fichier
    public static void ecrireScore(String nomFichier, Map<String, Integer> scores) {
        Path path = Paths.get(nomFichier);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (Map.Entry<String, Integer> entry : scores.entrySet()) {
                writer.write(entry.getKey() + ": " + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour lire les scores depuis un fichier et les afficher
    public static void lectureFichier(String cheminFichier) {
        try (BufferedReader lecteur = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            int count = 0;

            while ((ligne = lecteur.readLine()) != null && count < 10) {
                System.out.println(ligne);
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

