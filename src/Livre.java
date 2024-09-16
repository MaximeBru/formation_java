public class Livre {
    private String titre;
    private String auteur;
    private String isbn;

    public Livre(String titre, String auteur, String isbn) {
        this.titre = titre;
        this.auteur = auteur;
        this.isbn = isbn;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }


    public boolean equals(Livre autreLivre) {
        return this.isbn.equals(autreLivre.getIsbn());
    }
}