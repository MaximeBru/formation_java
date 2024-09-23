import java.util.Collection;

public class Utils {
    public static <T> void afficherElements(Collection<T> collection) {
        for (T element : collection) {
            System.out.println(element);
        }
    }
}