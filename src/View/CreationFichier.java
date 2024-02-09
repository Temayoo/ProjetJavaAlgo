package src.View;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;

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

    // Méthode pour lire les scores depuis un fichier et les retourner sous forme de TreeMap
    public static TreeMap<String, Integer> lireScores(String cheminFichier) {
        // Crée un TreeMap pour stocker les scores (clé: pseudo, valeur: score)
        TreeMap<String, Integer> scores = new TreeMap<>();

        try (BufferedReader lecteur = new BufferedReader(new FileReader(cheminFichier))) {
            // Crée un BufferedReader pour lire le fichier

            String ligne;

            // Lit chaque ligne du fichier
            while ((ligne = lecteur.readLine()) != null) {
                // Divise la ligne en deux parties séparées par ": "
                String[] parts = ligne.split(": ");

                // Vérifie si la ligne est bien au format attendu (pseudo: score)
                if (parts.length == 2) {
                    // Extrayez le pseudo et le score de chaque partie
                    String pseudo = parts[0];
                    int score = Integer.parseInt(parts[1]);

                    // Ajoute le pseudo et le score au TreeMap
                    scores.put(pseudo, score);
                }
            }
        } catch (IOException e) {
            // Gère les exceptions liées à la lecture du fichier
            e.printStackTrace();
        }

        // Renvoie le TreeMap contenant les scores lus depuis le fichier
        return scores;
    }

}

