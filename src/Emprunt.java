import java.time.LocalDate;
import java.time.Period;

public class Emprunt {
    private Livre livre;
    private Utilisateur utilisateur;
    private LocalDate dateEmprunt;
    private LocalDate dateRetour;

    // Constructeur
    public Emprunt(Utilisateur utilisateur, Livre livre) {
        this.utilisateur = utilisateur;
        this.livre = livre;
        this.dateEmprunt = LocalDate.now();
        this.dateRetour = null;
    }

    // Getter pour l'utilisateur
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    // Getter pour le livre
    public Livre getLivre() {
        return livre;
    }

    public LocalDate getDateEmprunt() {
        return dateEmprunt;
    }

    // Getter pour la date de retour
    public LocalDate getDateRetour() {
        return dateRetour;
    }

    // Méthode pour retourner le livre
    public void retournerLivre(LocalDate dateRetour) {
        this.dateRetour = dateRetour;
    }

    // Méthode pour calculer le retard
    public int calculerRetard(LocalDate dateActuelle) {
        if (dateRetour == null) {
            Period retard = Period.between(dateEmprunt, dateActuelle);
            return retard.getDays();
        }
        return 0;
    }
}
