import org.junit.Test;
import static org.junit.Assert.*;
import java.time.LocalDate;

public class EmpruntTest {

    @Test
    public void testCreationEmprunt() {
        Livre livre = new Livre("1984", "George Orwell", "978-0451524935", 1949);
        LocalDate dateEmprunt = LocalDate.now();
        Emprunt emprunt = new Emprunt(livre, dateEmprunt);

        assertEquals(livre, emprunt.getLivre());
        assertEquals(dateEmprunt, emprunt.getDateEmprunt());
        assertNull(emprunt.getDateRetour()); // Vérifie que la date de retour est initialement null
    }

    @Test
    public void testRetournerLivre() {
        Livre livre = new Livre("1984", "George Orwell", "978-0451524935", 1949);
        LocalDate dateEmprunt = LocalDate.now();
        Emprunt emprunt = new Emprunt(livre, dateEmprunt);

        LocalDate dateRetour = LocalDate.now().plusDays(3); // Simule un retour 3 jours après l'emprunt
        emprunt.retournerLivre(dateRetour);

        assertEquals(dateRetour, emprunt.getDateRetour());
    }

    @Test
    public void testCalculerRetardSansRetour() {
        Livre livre = new Livre("1984", "George Orwell", "978-0451524935", 1949);
        LocalDate dateEmprunt = LocalDate.now().minusDays(5); // Emprunt effectué il y a 5 jours
        Emprunt emprunt = new Emprunt(livre, dateEmprunt);

        LocalDate dateActuelle = LocalDate.now(); // Date actuelle
        int retard = emprunt.calculerRetard(dateActuelle);

        assertEquals(5, retard); // Vérifie que le retard est de 5 jours
    }

    @Test
    public void testCalculerRetardAvecRetour() {
        Livre livre = new Livre("1984", "George Orwell", "978-0451524935", 1949);
        LocalDate dateEmprunt = LocalDate.now().minusDays(5); // Emprunt effectué il y a 5 jours
        Emprunt emprunt = new Emprunt(livre, dateEmprunt);

        LocalDate dateRetour = LocalDate.now(); // Le livre est retourné aujourd'hui
        emprunt.retournerLivre(dateRetour);

        int retard = emprunt.calculerRetard(dateRetour);

        assertEquals(0, retard); // Vérifie qu'il n'y a pas de retard
    }
}
