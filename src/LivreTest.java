import org.junit.Test;
import static org.junit.Assert.*;

public class LivreTest {

    @Test
    public void testLivreCreation() {
        Livre livre = new Livre("manga", "auteurJap", "123-0156012195", 2020);
        assertEquals("manga", livre.getTitre());
        assertEquals("auteurJap", livre.getAuteur());
        assertEquals("123-0156012195", livre.getIsbn());
        assertEquals(2020, livre.getAnneePublication());
        assertTrue(livre.isDisponible());
    }

    @Test
    public void testSetDisponible() {
        Livre livre = new Livre("manga", "auteurJap", "123-0156012195", 2020);
        livre.setDisponible(false);
        assertFalse(livre.isDisponible());
    }
}
