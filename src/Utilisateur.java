import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Info(auteur = "max", version = "1.0", description = " utilisateur de la bibliothèque")
public class Utilisateur implements Comparable<Utilisateur> {
    private String nom;
    private String email;
    private String idUtilisateur;
    private static final HashMap<Utilisateur, List<Livre>> emprunts = new HashMap<>();
    private static final List<Utilisateur> utilisateurs = new ArrayList<>();

    // Constructeur
    public Utilisateur(String nom, String email) {
        this.nom = nom;
        this.email = email;
        this.idUtilisateur = genererIdUtilisateur();
    }

    public Utilisateur() {}

    // Getters
    public String getNom() {
        return nom;
    }

    public String getEmail() {
        return email;
    }

    public static void ajouterUtilisateur(Utilisateur utilisateur) {
        if (!utilisateurs.contains(utilisateur)) {
            utilisateurs.add(utilisateur);
        }
    }

    public String getUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(String idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public static List<Utilisateur> getUtilisateurs() {
        return utilisateurs;
    }

    @Override
    public int compareTo(Utilisateur autreUtilisateur) {
        return this.idUtilisateur.compareTo(autreUtilisateur.getUtilisateur());
    }

    // Générer un ID
    private String genererIdUtilisateur() {
        return "USER-" + nom.substring(0, 3).toUpperCase() + "-" + System.currentTimeMillis();
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "Nom='" + nom + '\'' +
                ", Email='" + email + '\'' +
                ", ID='" + idUtilisateur + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Utilisateur utilisateur = (Utilisateur) obj;
        return nom.equals(utilisateur.nom) && email.equals(utilisateur.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, email);
    }

    public static void supprimerUtilisateur(Utilisateur utilisateur) throws UtilisateurInexistantException {
        if (!utilisateurs.contains(utilisateur)) {
            throw new UtilisateurInexistantException("Impossible de supprimer, utilisateur inexistant.");
        }
        utilisateurs.remove(utilisateur);
        emprunts.remove(utilisateur);
    }

    public static Utilisateur rechercherUtilisateur(String nom, String email) {
        for (Utilisateur utilisateur : utilisateurs) {
            if (utilisateur.getNom().equals(nom) && utilisateur.getEmail().equals(email)) {
                return utilisateur;
            }
        }
        return null;
    }
}
