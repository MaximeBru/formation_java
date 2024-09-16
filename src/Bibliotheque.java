import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Bibliotheque {
    private final List<Livre> livres;
    private final List<Utilisateur> utilisateurs;
    private final HashMap<Utilisateur, List<Livre>> emprunts;

    public Bibliotheque() {
        livres = new ArrayList<>();
        utilisateurs = new ArrayList<>();
        emprunts = new HashMap<>();
    }

    public void ajouterLivre(Livre livre) {
        livres.add(livre);
    }

    public void ajouterLivres(List<Livre> livres) {
        this.livres.addAll(livres);
    }

    public void ajouterUtilisateur(Utilisateur utilisateur) {
        utilisateurs.add(utilisateur);
    }

    public void ajouterUtilisateurs(List<Utilisateur> utilisateurs) {
        this.utilisateurs.addAll(utilisateurs);
    }

    public List<Livre> getLivres() {
        return livres;
    }

    public List<Utilisateur> getUtilisateurs() {
        return utilisateurs;
    }

    public void emprunterLivre(Utilisateur utilisateur, Livre livre) throws LivreNonDisponibleException, UtilisateurInexistantException {
        if (!utilisateurs.contains(utilisateur)) {
            throw new UtilisateurInexistantException("Utilisateur non trouvé !");
        }

        if (!livres.contains(livre)) {
            throw new LivreNonDisponibleException("Le livre n'est pas disponible !");
        }

        List<Livre> livresEmpruntes = emprunts.getOrDefault(utilisateur, new ArrayList<>());
        livresEmpruntes.add(livre);
        emprunts.put(utilisateur, livresEmpruntes);
        livres.remove(livre);
    }

    public void supprimerUtilisateur(Utilisateur utilisateur) throws UtilisateurInexistantException {
        if (!utilisateurs.contains(utilisateur)) {
            throw new UtilisateurInexistantException("Impossible de supprimer, utilisateur inexistant.");
        }
        utilisateurs.remove(utilisateur);
        emprunts.remove(utilisateur);
    }

    public List<Livre> getLivresEmpruntes(Utilisateur utilisateur) {
        return emprunts.getOrDefault(utilisateur, new ArrayList<>());
    }
}
