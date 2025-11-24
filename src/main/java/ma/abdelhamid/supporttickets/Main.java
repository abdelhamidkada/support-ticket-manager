package ma.abdelhamid.supporttickets;

/**
 * Main entry point for the Support Ticket Manager application.
 * Initializes the core components and starts the console interface.
 */


public class Main {

    public static void main(String[] args) {
        TicketRepository repository = new TicketRepository();
        TicketFileStorage storage = new TicketFileStorage("tickets.csv");
        TicketService service = new TicketService(repository, storage);
        ConsoleUI ui = new ConsoleUI(service);
        ui.run();
    }
}
