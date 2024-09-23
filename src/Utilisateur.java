@Info(auteur = "Jean Dupont", version = "1.0", description = "Classe représentant un utilisateur de la bibliothèque")
public class Utilisateur implements Comparable<Utilisateur> {
    private String nom;
    private String email;
    private String idUtilisateur; // Un identifiant unique pour chaque utilisateur

    // Constructeur
    public Utilisateur(String nom, String email) {
        this.nom = nom;
        this.email = email;
        this.idUtilisateur = genererIdUtilisateur(); // Génère un ID unique pour l'utilisateur
    }

    // Getters
    public String getNom() {
        return nom;
    }

    public String getEmail() {
        return email;
    }

    public String getIdUtilisateur() {
        return idUtilisateur;
    }

    // Méthode pour comparer deux utilisateurs (par ID d'utilisateur)
    @Override
    public int compareTo(Utilisateur autreUtilisateur) {
        return this.idUtilisateur.compareTo(autreUtilisateur.getIdUtilisateur());
    }

    // Générer un ID unique (exemple basique)
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
        return idUtilisateur.equals(utilisateur.idUtilisateur);
    }

    @Override
    public int hashCode() {
        return idUtilisateur.hashCode();
    }
}
