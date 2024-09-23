import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.List;

public class Main {
    private static Bibliotheque bibliotheque = new Bibliotheque();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws UtilisateurInexistantException {
        // Ajout de livres pour la démonstration
        bibliotheque.ajouterLivre(new Livre("Livre A", "Auteur A", "ISBN001", 1995));
        bibliotheque.ajouterLivre(new Livre("Livre B", "Auteur B", "ISBN002", 2005));
        bibliotheque.ajouterLivre(new Livre("Livre C", "Auteur C", "ISBN003", 2010));

        afficherMenu();
    }

    private static void afficherMenu() throws UtilisateurInexistantException {
        int choix;
        do {
            System.out.println("=== Menu Bibliothèque ===");
            System.out.println("1. Emprunter un livre");
            System.out.println("2. Retourner un livre");
            System.out.println("3. Vérifier les retards");
            System.out.println("4. Quitter");
            System.out.print("Choix : ");
            choix = Integer.parseInt(scanner.nextLine());
            traiterChoix(choix);
        } while (choix != 4);
    }

    private static void traiterChoix(int choix) throws UtilisateurInexistantException {
        switch (choix) {
            case 1:
                emprunterLivre();
                break;
            case 2:
                retournerLivre();
                break;
            case 3:
                bibliotheque.verifierRetards();
                break;
            case 4:
                System.out.println("Au revoir !");
                break;
            default:
                System.out.println("Choix invalide.");
        }
    }



    private static void emprunterLivre() {
        System.out.print("Entrez votre nom : ");
        String nom = scanner.nextLine();
        Utilisateur utilisateur = new Utilisateur(nom, nom + "@mail.com");  // Simple utilisateur par défaut

        System.out.print("Entrez le titre du livre à emprunter : ");
        String titre = scanner.nextLine();
        Livre livre = bibliotheque.getLivres().stream().filter(l -> l.getTitre().equalsIgnoreCase(titre)).findFirst().orElse(null);

        if (livre != null) {
            try {
                bibliotheque.emprunterLivre(utilisateur, livre);
                System.out.println("Livre emprunté avec succès.");
            } catch (LivreNonDisponibleException | UtilisateurInexistantException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Livre non trouvé.");
        }
    }

    private static void retournerLivre() throws UtilisateurInexistantException {
        System.out.print("Entrez votre nom : ");
        String nom = scanner.nextLine();
        Utilisateur utilisateur = new Utilisateur(nom, nom + "@mail.com");

        System.out.print("Entrez le titre du livre à retourner : ");
        String titre = scanner.nextLine();
        Livre livre = bibliotheque.getLivresEmpruntes(utilisateur).stream()
                .filter(l -> l.getTitre().equalsIgnoreCase(titre))
                .findFirst()
                .orElse(null);

        if (livre != null) {
            bibliotheque.retournerLivre(utilisateur, livre, LocalDate.now());
            System.out.println("Livre retourné avec succès.");
        } else {
            System.out.println("Livre non trouvé.");
        }
    }


}
