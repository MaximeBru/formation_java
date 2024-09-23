import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Bibliotheque {
    private final List<Livre> livres = new ArrayList<>();
    private final Map<Utilisateur, List<Emprunt>> emprunts = new HashMap<>();
    private List<Livre> catalogue;

    public Bibliotheque() {
        // Initialisation du catalogue avec une liste vide
        this.catalogue = new ArrayList<>();
    }

    public List<Livre> getLivres() {
        return livres;
    }

    public void ajouterLivre(Livre livre) {
        livres.add(livre);
        catalogue.add(livre); // Ajout du livre dans le catalogue aussi
    }

    public void supprimerLivre(Livre livre) {
        livres.remove(livre);
        catalogue.remove(livre); // Supprimer le livre du catalogue
    }

    public List<Livre> getLivresEmpruntes(Utilisateur utilisateur) {
        return emprunts.getOrDefault(utilisateur, new ArrayList<>())
                .stream()
                .map(Emprunt::getLivre)
                .collect(Collectors.toList());
    }

    public void emprunterLivre(Utilisateur utilisateur, Livre livre) throws LivreNonDisponibleException, UtilisateurInexistantException {
        List<Utilisateur> utilisateurs = Utilisateur.getUtilisateurs();
        if (!utilisateurs.contains(utilisateur)) {
            throw new UtilisateurInexistantException("Utilisateur non trouvé !");
        }

        if (!livres.contains(livre) || !livre.isDisponible()) {
            throw new LivreNonDisponibleException("Le livre n'est pas disponible !");
        }

        Emprunt nouvelEmprunt = new Emprunt(utilisateur, livre);
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

    public List<Livre> rechercherLivresParTitre(String motCle) {
        return livres.stream()
                .filter(livre -> livre.getTitre().toLowerCase().contains(motCle.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Livre> trierLivresParAnnee() {
        return livres.stream()
                .sorted(Comparator.comparingInt(Livre::getAnneePublication))
                .collect(Collectors.toList());
    }

    public Livre rechercherLivreParIsbn(String isbn) {
        // Vérifie d'abord si le catalogue est bien initialisé
        if (catalogue == null) {
            System.out.println("Le catalogue est vide.");
            return null;
        }

        for (Livre livre : catalogue) {
            if (livre.getIsbn().equals(isbn)) {
                return livre; // Retourne le livre trouvé
            }
        }
        return null; // Retourne null si aucun livre n'est trouvé avec cet ISBN
    }

    public void retournerLivre(Utilisateur utilisateur, Livre livre, LocalDate dateRetour) throws UtilisateurInexistantException {
        List<Utilisateur> utilisateurs = Utilisateur.getUtilisateurs();
        if (!utilisateurs.contains(utilisateur)) {
            throw new UtilisateurInexistantException("Utilisateur non trouvé !");
        }
        List<Emprunt> livresEmpruntes = emprunts.get(utilisateur);
        if (livresEmpruntes != null) {
            for (Emprunt emprunt : livresEmpruntes) {
                if (emprunt.getLivre().equals(livre)) {
                    emprunt.retournerLivre(dateRetour);
                    livre.setDisponible(true); // Marque le livre comme disponible
                    break;
                }
            }
        }
    }

    public void afficherLivres() {
        Utils.afficherElements(livres);
    }

    public void afficherUtilisateurs() {
        Utils.afficherElements(Utilisateur.getUtilisateurs());
    }
}
