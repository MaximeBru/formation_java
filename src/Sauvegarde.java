import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Sauvegarde {
    public static void sauvegarderLivres(List<Livre> livres, String fichier) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fichier))) {
            for (Livre livre : livres) {
                writer.write(livre.getTitre() + "," + livre.getAuteur() + "," + livre.getIsbn() + "," + livre.getAnneePublication());
                writer.newLine();
            }
        }
    }

    public static void sauvegarderUtilisateurs(List<Utilisateur> utilisateurs, String fichier) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fichier))) {
            for (Utilisateur utilisateur : utilisateurs) {
                writer.write(utilisateur.getNom() + "," + utilisateur.getEmail() + "," + utilisateur.getUtilisateur());
                writer.newLine();
            }
        }
    }
}
