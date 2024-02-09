package src.Controller;

import src.View.CreationFichier;

import java.util.Map;
import java.util.TreeMap;

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
}
