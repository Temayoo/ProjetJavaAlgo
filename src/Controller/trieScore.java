package src.Controller;

import src.View.CreationFichier;

import java.io.*;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import static src.Controller.gamebase.FICHIER_SCORES;
import static src.Controller.gamebase.scores;

public class trieScore {


    // Méthode pour trier les scores par ordre croissant
    public static void triScoreCroissant() {
        // Lire les scores depuis le fichier et stocker dans un TreeMap (clé: nom, valeur: score)
        TreeMap<String, Integer> scores = CreationFichier.lireScores("DossierScore/score.txt");
        //permet de prend les elements de la map et de les triées par ordres croissant
        scores.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));
    }

    // Méthode pour trier les scores par ordre décroissant
    public static void triScoreDecroissant() {
        // Lire les scores depuis le fichier et stocker dans un TreeMap (clé: nom, valeur: score)
        TreeMap<String, Integer> scores = CreationFichier.lireScores("DossierScore/score.txt");
        //permet de prend les elements de la map et de les triées par ordres decroissant
        scores.entrySet().stream()
                .sorted(Map.Entry.comparingByValue((v1, v2) -> v2.compareTo(v1)))
                .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));
    }

    // Méthode pour charger les scores depuis un fichier texte
    public static void chargerScores() {
        try (Scanner scanner = new Scanner(new File(FICHIER_SCORES))) {
            while (scanner.hasNextLine()) {
                String[] scoreInfo = scanner.nextLine().split(":");
                String pseudo = scoreInfo[0].trim();
                int score = Integer.parseInt(scoreInfo[1].trim());
                scores.put(pseudo, score);
            }
            System.out.println("Scores chargés avec succès !");
        } catch (FileNotFoundException e) {
            // Le fichier de scores n'existe pas encore
            System.out.println("Aucun fichier de scores trouvé. Les scores seront initialisés à zéro.");
        }
    }

    // Méthode pour sauvegarder les scores dans un fichier texte
    static void sauvegarderScores() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FICHIER_SCORES))) {
            for (Map.Entry<String, Integer> entry : scores.entrySet()) {
                writer.println(entry.getKey() + ": " + entry.getValue());
            }
            System.out.println("Scores sauvegardés avec succès !");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Méthode pour nettoyer les scores
    public static void nettoyerScores() {
        scores.clear(); // Effacer tous les scores
        sauvegarderScores(); // Sauvegarder les scores mis à jour
        System.out.println("Scores nettoyés avec succès !");
    }
}

