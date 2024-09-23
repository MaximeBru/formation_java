import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Bibliotheque bibliotheque = new Bibliotheque();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        chargerDonnees();
        afficherMenu();
    }

    private static void afficherMenu() {
        int choix;
        do {
            System.out.println("=== Menu Bibliothèque ===");
            System.out.println("1. Ajouter un livre");
            System.out.println("2. Ajouter un utilisateur");
            System.out.println("3. Emprunter un livre");
            System.out.println("4. Supprimer un utilisateur");
            System.out.println("5. Afficher les livres disponibles");
            System.out.println("6. Afficher les utilisateurs");
            System.out.println("7. Afficher les livres empruntés par un utilisateur");
            System.out.println("8. Quitter");
            System.out.print("Choix : ");
            choix = lireEntier();
            traiterChoix(choix);
        } while (choix != 8);
        sauvegarderDonnees();
    }

    private static void traiterChoix(int choix) {
        switch (choix) {
            case 1:
                ajouterLivre();
                break;
            case 2:
                ajouterUtilisateur();
                break;
            case 3:
                emprunterLivre();
                break;
            case 4:
                supprimerUtilisateur();
                break;
            case 5:
                afficherLivresDisponibles();
                break;
            case 6:
                afficherUtilisateurs();
                break;
            case 7:
                afficherLivresEmpruntes();
                break;
            case 8:
                System.out.println("Au revoir !");
                break;
            default:
                System.out.println("Choix invalide.");
        }
    }

    private static void ajouterLivre() {
        System.out.print("Titre : ");
        String titre = scanner.nextLine();
        System.out.print("Auteur : ");
        String auteur = scanner.nextLine();
        System.out.print("ISBN : ");
        String isbn = scanner.nextLine();
        System.out.print("Année publication : ");
        int anneePublication = lireEntier();
        Livre livre = new Livre(titre, auteur, isbn, anneePublication);
        bibliotheque.ajouterLivre(livre);
        System.out.println("Livre ajouté avec succès.");
    }

    private static void ajouterUtilisateur() {
        System.out.print("Nom : ");
        String nom = scanner.nextLine();
        System.out.print("Email : ");
        String email = scanner.nextLine();
        Utilisateur utilisateur = new Utilisateur(nom, email);
        Utilisateur.getUtilisateurs().add(utilisateur); // Ajout à la liste des utilisateurs
        System.out.println("Utilisateur ajouté avec succès.");
    }

    private static void supprimerUtilisateur() {
        System.out.print("Nom de l'utilisateur à supprimer : ");
        String nom = scanner.nextLine();
        System.out.print("Email de l'utilisateur à supprimer : ");
        String email = scanner.nextLine();
        Utilisateur utilisateur = new Utilisateur(nom, email);

        try {
            Utilisateur.supprimerUtilisateur(utilisateur);
            System.out.println("Utilisateur supprimé avec succès.");
        } catch (UtilisateurInexistantException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    private static void emprunterLivre() {
        System.out.print("Nom de l'utilisateur : ");
        String nomUtilisateur = scanner.nextLine();
        System.out.print("Email de l'utilisateur : ");
        String emailUtilisateur = scanner.nextLine();

        // Utilisation de la méthode rechercherUtilisateur
        Utilisateur utilisateurRecherche = Utilisateur.rechercherUtilisateur(nomUtilisateur, emailUtilisateur);

        if (utilisateurRecherche == null) {
            System.out.println("Utilisateur non trouvé.");
            return;
        }

        System.out.print("ISBN du livre : ");
        String isbnLivre = scanner.nextLine();

        Livre livre = bibliotheque.rechercherLivreParIsbn(isbnLivre);

        if (livre == null) {
            System.out.println("Livre non trouvé ou non disponible.");
            return;
        }

        try {
            bibliotheque.emprunterLivre(utilisateurRecherche, livre);
            System.out.println("Livre emprunté avec succès.");
        } catch (LivreNonDisponibleException | UtilisateurInexistantException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }


    private static void afficherLivresDisponibles() {
        List<Livre> livres = bibliotheque.getLivres();
        if (livres.isEmpty()) {
            System.out.println("Aucun livre disponible.");
        } else {
            for (Livre livre : livres) {
                System.out.println("Titre : " + livre.getTitre() + ", Auteur : " + livre.getAuteur() + ", ISBN : " + livre.getIsbn());
            }
        }
    }

    private static void afficherUtilisateurs() {
        List<Utilisateur> utilisateurs = Utilisateur.getUtilisateurs();
        if (utilisateurs.isEmpty()) {
            System.out.println("Aucun utilisateur.");
        } else {
            for (Utilisateur utilisateur : utilisateurs) {
                System.out.println("Nom : " + utilisateur.getNom() + ", Email : " + utilisateur.getEmail());
            }
        }
    }

    private static void afficherLivresEmpruntes() {
        System.out.print("Nom de l'utilisateur : ");
        String nomUtilisateur = scanner.nextLine();
        System.out.print("Email de l'utilisateur : ");
        String emailUtilisateur = scanner.nextLine();
        Utilisateur utilisateur = new Utilisateur(nomUtilisateur, emailUtilisateur);
        List<Livre> livresEmpruntes = bibliotheque.getLivresEmpruntes(utilisateur);
        if (livresEmpruntes.isEmpty()) {
            System.out.println("Aucun livre emprunté.");
        } else {
            for (Livre livre : livresEmpruntes) {
                System.out.println("Titre : " + livre.getTitre() + ", Auteur : " + livre.getAuteur() + ", ISBN : " + livre.getIsbn());
            }
        }
    }

    private static int lireEntier() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Entrée invalide. Veuillez entrer un nombre : ");
            }
        }
    }

    private static void chargerDonnees() {
        try {
            List<Livre> livres = Chargement.chargerLivres("livres.txt");
            for (Livre livre : livres) {
                bibliotheque.ajouterLivre(livre);
            }

            List<Utilisateur> utilisateurs = Chargement.chargerUtilisateurs("utilisateurs.txt");
            for (Utilisateur utilisateur : utilisateurs) {
                Utilisateur.ajouterUtilisateur(utilisateur); // Ajout utilisateur
            }

            System.out.println("Données chargées avec succès.");
        } catch (IOException e) {
            System.out.println("Erreur lors du chargement des données : " + e.getMessage());
        }
    }

    private static void sauvegarderDonnees() {
        try {
            Sauvegarde.sauvegarderLivres(bibliotheque.getLivres(), "livres.txt");
            Sauvegarde.sauvegarderUtilisateurs(Utilisateur.getUtilisateurs(), "utilisateurs.txt");
            System.out.println("Données sauvegardées avec succès.");
        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde des données : " + e.getMessage());
        }
    }
}
