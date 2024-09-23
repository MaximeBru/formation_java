import org.junit.Test;
import static org.junit.Assert.*;
import java.time.LocalDate;

public class EmpruntTest {

    @Test
    public void testCreationEmprunt() {
        Livre livre = new Livre("1984", "George Orwell", "978-0451524935", 1949);
        Utilisateur utilisateur = new Utilisateur("John Doe", "john@example.com"); // Ajout de l'utilisateur
        Emprunt emprunt = new Emprunt(utilisateur, livre); // Utilisation du bon constructeur

        assertEquals(livre, emprunt.getLivre());
        assertEquals(utilisateur, emprunt.getUtilisateur()); // Vérifie l'utilisateur
        assertNotNull(emprunt.getDateEmprunt()); // Vérifie que la date d'emprunt n'est pas null
        assertNull(emprunt.getDateRetour()); // Vérifie que la date de retour est initialement null
    }

    @Test
    public void testRetournerLivre() {
        Livre livre = new Livre("1984", "George Orwell", "978-0451524935", 1949);
        Utilisateur utilisateur = new Utilisateur("John Doe", "john@example.com"); // Ajout de l'utilisateur
        Emprunt emprunt = new Emprunt(utilisateur, livre); // Utilisation du bon constructeur

        LocalDate dateRetour = LocalDate.now().plusDays(3); // Simule un retour 3 jours après l'emprunt
        emprunt.retournerLivre(dateRetour);

        assertEquals(dateRetour, emprunt.getDateRetour());
    }

    @Test
    public void testCalculerRetardSansRetour() {
        Livre livre = new Livre("1984", "George Orwell", "978-0451524935", 1949);
        Utilisateur utilisateur = new Utilisateur("John Doe", "john@example.com"); // Ajout de l'utilisateur
        Emprunt emprunt = new Emprunt(utilisateur, livre); // Utilisation du bon constructeur
        emprunt.retournerLivre(null); // Assurer que le livre n'est pas retourné

        LocalDate dateActuelle = LocalDate.now().minusDays(5); // Emprunt effectué il y a 5 jours
        int retard = emprunt.calculerRetard(dateActuelle);

        assertEquals(5, retard); // Vérifie que le retard est de 5 jours
    }

    @Test
    public void testCalculerRetardAvecRetour() {
        Livre livre = new Livre("1984", "George Orwell", "978-0451524935", 1949);
        Utilisateur utilisateur = new Utilisateur("John Doe", "john@example.com"); // Ajout de l'utilisateur
        Emprunt emprunt = new Emprunt(utilisateur, livre); // Utilisation du bon constructeur

        LocalDate dateEmprunt = LocalDate.now().minusDays(5); // Emprunt effectué il y a 5 jours
        emprunt.retournerLivre(LocalDate.now()); // Le livre est retourné aujourd'hui

        int retard = emprunt.calculerRetard(LocalDate.now());

        assertEquals(0, retard); // Vérifie qu'il n'y a pas de retard
    }
}
