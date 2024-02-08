package src.Controller;

public class gestionScore {

    public static void triCroissant (int[] tableau){
        int n = tableau.length;

        for (int i = 1; i < n; ++i) {
            int cle = tableau[i];
            int j = i - 1;

            while (j >= 0 && tableau[j] > cle) {
                tableau[j + 1] = tableau[j];
                j = j - 1;
            }

            tableau[j + 1] = cle;
        }
    }
    public static void triDecroissant (int[] tableau) {
        int n = tableau.length;

        for (int i = 1; i < n; ++i) {
            int cle = tableau[i];
            int j = i - 1;
            while (j >= 0 && tableau[j] < cle) {  // ajusté pour trier en ordre inverse
                tableau[j + 1] = tableau[j];
                j = j - 1;
            }

            tableau[j + 1] = cle;
        }
    }
}
