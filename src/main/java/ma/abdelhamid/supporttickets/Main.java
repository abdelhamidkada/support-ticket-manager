package ma.abdelhamid.supporttickets;

public class Main {

    public static void main(String[] args) {
        TicketRepository repository = new TicketRepository();
        TicketFileStorage storage = new TicketFileStorage("tickets.csv");
        TicketService service = new TicketService(repository, storage);
        ConsoleUI ui = new ConsoleUI(service);
        ui.run();
    }
}
