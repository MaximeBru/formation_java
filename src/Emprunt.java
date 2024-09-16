public class Emprunt {
    private Livre livre;
    private Utilisateur utilisateur;
    private String dateEmprunt;

    public Emprunt(Livre livre, Utilisateur utilisateur, String dateEmprunt) {
        this.livre = livre;
        this.utilisateur = utilisateur;
        this.dateEmprunt = dateEmprunt;
    }

    public Livre getLivre() {
        return livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String getDateEmprunt() {
        return dateEmprunt;
    }

    public void setDateEmprunt(String dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }
}
