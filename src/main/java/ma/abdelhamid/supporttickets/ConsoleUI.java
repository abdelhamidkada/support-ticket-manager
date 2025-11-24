package ma.abdelhamid.supporttickets;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ConsoleUI {

    private final TicketService service;
    private final Scanner scanner = new Scanner(System.in);

    public ConsoleUI(TicketService service) {
        this.service = service;
    }

    public void run() {
        System.out.println("=== Gestionnaire de tickets de support ===");

        // Charger automatiquement depuis le fichier si possible
        service.loadFromFile();

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Votre choix : ");
            switch (choice) {
                case 1 -> createTicket();
                case 2 -> listTickets();
                case 3 -> showTicketById();
                case 4 -> changeTicketStatus();
                case 5 -> changeTicketPriority();
                case 6 -> filterByStatus();
                case 7 -> filterByType();
                case 8 -> saveTickets();
                case 9 -> loadTickets();
                case 0 -> {
                    running = false;
                    System.out.println("Au revoir.");
                }
                default -> System.out.println("Choix invalide.");
            }
            System.out.println();
        }

        // Sauvegarde à la fin
        service.saveToFile();
    }

    private void printMenu() {
        System.out.println("---------------");
        System.out.println("1. Créer un ticket");
        System.out.println("2. Lister tous les tickets");
        System.out.println("3. Rechercher un ticket par ID");
        System.out.println("4. Modifier le statut d'un ticket");
        System.out.println("5. Modifier la priorité d'un ticket");
        System.out.println("6. Filtrer les tickets par statut");
        System.out.println("7. Filtrer les tickets par type");
        System.out.println("8. Sauvegarder les tickets dans un fichier");
        System.out.println("9. Charger les tickets depuis un fichier");
        System.out.println("0. Quitter");
    }

    private void createTicket() {
        System.out.println("--- Nouveau ticket ---");
        String clientName = readLine("Nom du client : ");
        String phone = readLine("Téléphone     : ");
        TicketType type = askType();
        TicketPriority priority = askPriority();
        String description = readLine("Description   : ");

        Ticket ticket = service.createTicket(clientName, phone, type, priority, description);
        System.out.println("Ticket créé avec l'ID #" + ticket.getId());
    }

    private void listTickets() {
        System.out.println("--- Liste des tickets ---");
        List<Ticket> tickets = service.findAll();
        if (tickets.isEmpty()) {
            System.out.println("Aucun ticket pour le moment.");
            return;
        }
        for (Ticket t : tickets) {
            System.out.println(t.formatForList());
        }
    }

    private void showTicketById() {
        int id = readInt("ID du ticket : ");
        Optional<Ticket> ticket = service.findById(id);
        if (ticket.isPresent()) {
            System.out.println(ticket.get());
        } else {
            System.out.println("Ticket introuvable.");
        }
    }

    private void changeTicketStatus() {
        int id = readInt("ID du ticket : ");
        TicketStatus status = askStatus();
        Optional<Ticket> ticket = service.changeStatus(id, status);
        if (ticket.isPresent()) {
            System.out.println("Statut mis à jour : " + ticket.get().getStatus());
        } else {
            System.out.println("Ticket introuvable.");
        }
    }

    private void changeTicketPriority() {
        int id = readInt("ID du ticket : ");
        TicketPriority priority = askPriority();
        Optional<Ticket> ticket = service.changePriority(id, priority);
        if (ticket.isPresent()) {
            System.out.println("Priorité mise à jour : " + ticket.get().getPriority());
        } else {
            System.out.println("Ticket introuvable.");
        }
    }

    private void filterByStatus() {
        TicketStatus status = askStatus();
        List<Ticket> tickets = service.findByStatus(status);
        System.out.println("--- Tickets avec le statut " + status + " ---");
        if (tickets.isEmpty()) {
            System.out.println("Aucun ticket.");
            return;
        }
        for (Ticket t : tickets) {
            System.out.println(t.formatForList());
        }
    }

    private void filterByType() {
        TicketType type = askType();
        List<Ticket> tickets = service.findByType(type);
        System.out.println("--- Tickets de type " + type + " ---");
        if (tickets.isEmpty()) {
            System.out.println("Aucun ticket.");
            return;
        }
        for (Ticket t : tickets) {
            System.out.println(t.formatForList());
        }
    }

    private void saveTickets() {
        if (service.saveToFile()) {
            System.out.println("Tickets sauvegardés.");
        } else {
            System.out.println("Erreur lors de la sauvegarde.");
        }
    }

    private void loadTickets() {
        if (service.loadFromFile()) {
            System.out.println("Tickets chargés.");
        } else {
            System.out.println("Erreur lors du chargement.");
        }
    }

    private int readInt(String label) {
        while (true) {
            System.out.print(label);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException ex) {
                System.out.println("Merci d'entrer un nombre.");
            }
        }
    }

    private String readLine(String label) {
        System.out.print(label);
        return scanner.nextLine().trim();
    }

    private TicketStatus askStatus() {
        System.out.println("Choisir un statut :");
        TicketStatus[] values = TicketStatus.values();
        for (int i = 0; i < values.length; i++) {
            System.out.println((i + 1) + ". " + values[i]);
        }
        while (true) {
            int choice = readInt("Votre choix : ");
            if (choice >= 1 && choice <= values.length) {
                return values[choice - 1];
            }
            System.out.println("Choix invalide.");
        }
    }

    private TicketPriority askPriority() {
        System.out.println("Choisir une priorité :");
        TicketPriority[] values = TicketPriority.values();
        for (int i = 0; i < values.length; i++) {
            System.out.println((i + 1) + ". " + values[i]);
        }
        while (true) {
            int choice = readInt("Votre choix : ");
            if (choice >= 1 && choice <= values.length) {
                return values[choice - 1];
            }
            System.out.println("Choix invalide.");
        }
    }

    private TicketType askType() {
        System.out.println("Choisir un type de ticket :");
        TicketType[] values = TicketType.values();
        for (int i = 0; i < values.length; i++) {
            System.out.println((i + 1) + ". " + values[i]);
        }
        while (true) {
            int choice = readInt("Votre choix : ");
            if (choice >= 1 && choice <= values.length) {
                return values[choice - 1];
            }
            System.out.println("Choix invalide.");
        }
    }
}
