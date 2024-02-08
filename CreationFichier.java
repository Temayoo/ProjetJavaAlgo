import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CreationFichier {
    public static void creationFichierScore()
    {
        try {
            // Recevoir le fichier
            File f = new File("DossierScore/score.txt");

            // Créer un nouveau fichier et Vérifier s'il n'existe pas
            if (f.createNewFile())
                System.out.println("File created");
            else
                System.out.println("File already exists");
        }
        catch (Exception e) {
            System.err.println(e);
        }
    }

    public static void ecrireScore(String text) {
        Path path = Paths.get("DossierScore/score.txt");
        try {
            String str = text;
            byte[] bs = str.getBytes();
            Path writtenFilePath = Files.write(path, bs);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void lectureFichier(){
            String cheminFichier = "DossierScore/score.txt";

            try {
                BufferedReader lecteur = new BufferedReader(new FileReader(cheminFichier));

                // Lisez chaque ligne du fichier et affichez-la dans le terminal
                String ligne;
                while ((ligne = lecteur.readLine()) != null) {
                    System.out.println(ligne);
                }
                lecteur.close();
            } catch (IOException e) {
                // Gérez les exceptions liées à la lecture du fichier
                e.printStackTrace();
            }
        }
    }
