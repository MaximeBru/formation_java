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
                if (donnees.length == 4) {
                    Livre livre = new Livre(donnees[0], donnees[1], donnees[2], Integer.parseInt(donnees[3]));
                    livres.add(livre);
                }
            }
        }
        return livres;
    }

    public static List<Utilisateur> chargerUtilisateurs(String fichier) throws IOException {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fichier))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                String[] donnees = ligne.split(",");
                if (donnees.length == 3) {
                    Utilisateur utilisateur = new Utilisateur(donnees[0], donnees[1]);
                    utilisateur.setIdUtilisateur(donnees[2]);
                    utilisateurs.add(utilisateur);
                }
            }
        }
        return utilisateurs;
    }
}
