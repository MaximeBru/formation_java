import org.junit.Test;
import static org.junit.Assert.*;

public class UtilisateurTest {

    @Test
    public void testCreationUtilisateur() {
        Utilisateur utilisateur = new Utilisateur("Jean Dujardin", "jean@acteur.com");
        assertEquals("Jean Dujardin", utilisateur.getNom());
        assertEquals("jean@acteur.com", utilisateur.getEmail());
    }
}
