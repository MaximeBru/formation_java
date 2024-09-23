import java.time.LocalDate;
import java.time.Period;

public class Emprunt {
    private Livre livre;
    private Utilisateur utilisateur;
    private LocalDate dateEmprunt;
    private LocalDate dateRetour;

    public Emprunt(Utilisateur utilisateur, Livre livre) {
        this.utilisateur = utilisateur;
        this.livre = livre;
        this.dateEmprunt = LocalDate.now();
        this.dateRetour = null;
    }


    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public Livre getLivre() {
        return livre;
    }

    public LocalDate getDateEmprunt() {
        return dateEmprunt;
    }

    public LocalDate getDateRetour() {
        return dateRetour;
    }

    public void retournerLivre(LocalDate dateRetour) {
        this.dateRetour = dateRetour;
    }

    public int calculerRetard(LocalDate dateActuelle) {
        if (dateRetour == null) {
            Period retard = Period.between(dateEmprunt, dateActuelle);
            return retard.getDays();
        }
        return 0;
    }
}
