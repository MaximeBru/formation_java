import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Chargement {
    public static List<Livre> chargerLivres(String fichier) throws IOException {
        List<Livre> livres = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fichier))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                String[] donnees = ligne.split(",");
                if (donnees.length == 4) {  // Correction de donnees.length pour 4 éléments
                    Livre livre = new Livre(donnees[0], donnees[1], donnees[2], Integer.parseInt(donnees[3]));
                    livres.add(livre);
                }
            }
        }
        return livres;  // Renvoie la liste complète de livres
    }

    public static List<Utilisateur> chargerUtilisateurs(String fichier) throws IOException {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fichier))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                String[] donnees = ligne.split(",");
                if (donnees.length == 2) {
                    Utilisateur utilisateur = new Utilisateur(donnees[0], donnees[1]);
                    utilisateurs.add(utilisateur);
                }
            }
        }
        return utilisateurs; // Retourne une liste d'utilisateurs
    }

}
