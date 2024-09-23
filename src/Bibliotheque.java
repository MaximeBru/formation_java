import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Bibliotheque {
    private final List<Livre> livres = new ArrayList<>();
    private final List<Utilisateur> utilisateurs = new ArrayList<>();
    private final Map<Utilisateur, List<Emprunt>> emprunts = new HashMap<>();

    public List<Livre> getLivres() {
        return livres;
    }

    public void ajouterLivre(Livre livre) {
        livres.add(livre);
    }
    public void supprimerLivre(Livre livre) {
        livres.remove(livre);
    }
    public void ajouterUtilisateur(Utilisateur utilisateur) {
        utilisateurs.add(utilisateur);
    }
    public List<Livre> getLivresEmpruntes(Utilisateur utilisateur) {
        return emprunts.getOrDefault(utilisateur, new ArrayList<>())
                .stream()
                .map(Emprunt::getLivre)
                .collect(Collectors.toList());
    }
    public void emprunterLivre(Utilisateur utilisateur, Livre livre) throws LivreNonDisponibleException, UtilisateurInexistantException {
        if (!utilisateurs.contains(utilisateur)) {
            throw new UtilisateurInexistantException("Utilisateur non trouvé !");
        }
        if (!livres.contains(livre) || !livre.isDisponible()) {
            throw new LivreNonDisponibleException("Le livre n'est pas disponible !");
        }

        Emprunt nouvelEmprunt = new Emprunt(utilisateur, livre); // Utilise le constructeur correct
        emprunts.computeIfAbsent(utilisateur, k -> new ArrayList<>()).add(nouvelEmprunt);
        livre.setDisponible(false); // Marque le livre comme non disponible
    }
    public void verifierRetards() {
        LocalDate dateActuelle = LocalDate.now();
        for (Map.Entry<Utilisateur, List<Emprunt>> entry : emprunts.entrySet()) {
            Utilisateur utilisateur = entry.getKey();
            List<Emprunt> empruntsUtilisateur = entry.getValue();
            for (Emprunt emprunt : empruntsUtilisateur) {
                int retard = emprunt.calculerRetard(dateActuelle);
                if (retard > 0) {
                    System.out.println("L'utilisateur " + utilisateur.getNom() + " a un retard de " + retard + " jours sur le livre: " + emprunt.getLivre().getTitre());
                }
            }
        }
    }

    public void retournerLivre(Utilisateur utilisateur, Livre livre, LocalDate dateRetour) throws UtilisateurInexistantException {
        if (!utilisateurs.contains(utilisateur)) {
            throw new UtilisateurInexistantException("Utilisateur non trouvé !");
        }
        List<Emprunt> livresEmpruntes = emprunts.get(utilisateur);
        if (livresEmpruntes != null) {
            for (Emprunt emprunt : livresEmpruntes) {
                if (emprunt.getLivre().equals(livre)) {
                    emprunt.retournerLivre(dateRetour);
                    livres.add(livre);
                    break;
                }
            }
        }
    }

    public void afficherLivres() {
        Utils.afficherElements(livres);
    }

    public void afficherUtilisateurs() {
        Utils.afficherElements(utilisateurs);
    }
}
