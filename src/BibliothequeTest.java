import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class BibliothequeTest {

    @Test
    public void testAjouterLivre() {
        Bibliotheque bibliotheque = new Bibliotheque();
        Livre livre = new Livre("Le Petit Prince", "Antoine de Saint-Exupéry", "978-0156012195", 1943);

        bibliotheque.ajouterLivre(livre);

        assertTrue(bibliotheque.getLivres().contains(livre));
    }

    @Test
    public void testSupprimerLivre() {
        Bibliotheque bibliotheque = new Bibliotheque();
        Livre livre = new Livre("Le Petit Prince", "Antoine de Saint-Exupéry", "978-0156012195", 1943);

        bibliotheque.ajouterLivre(livre);
        bibliotheque.supprimerLivre(livre);

        assertFalse(bibliotheque.getLivres().contains(livre));
    }

    @Test
    public void testEmprunterLivre() throws UtilisateurInexistantException, LivreNonDisponibleException {
        Bibliotheque bibliotheque = new Bibliotheque();
        Utilisateur utilisateur = new Utilisateur("Jean Dupont", "jean@example.com");
        Livre livre = new Livre("Le Petit Prince", "Antoine de Saint-Exupéry", "978-0156012195", 1943);

        bibliotheque.ajouterLivre(livre);
        bibliotheque.emprunterLivre(utilisateur, livre);

        assertFalse(livre.isDisponible());
    }

    @Test
    public void testRetournerLivre() throws UtilisateurInexistantException, LivreNonDisponibleException {
        Bibliotheque bibliotheque = new Bibliotheque();
        Utilisateur utilisateur = new Utilisateur("Jean Dupont", "jean@example.com");
        Livre livre = new Livre("Le Petit Prince", "Antoine de Saint-Exupéry", "978-0156012195", 1943);

        bibliotheque.ajouterLivre(livre);
        bibliotheque.emprunterLivre(utilisateur, livre);

        // Appelle retournerLivre avec les bons paramètres
        bibliotheque.retournerLivre(utilisateur, livre, LocalDate.now());

        assertTrue(livre.isDisponible());
    }
}
