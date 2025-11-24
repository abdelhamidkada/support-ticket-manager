package ma.abdelhamid.supporttickets;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class TicketService {

    private final TicketRepository repository;
    private final TicketFileStorage fileStorage;

    public TicketService(TicketRepository repository, TicketFileStorage fileStorage) {
        this.repository = repository;
        this.fileStorage = fileStorage;
    }

    public Ticket createTicket(String clientName,
                               String phoneNumber,
                               TicketType type,
                               TicketPriority priority,
                               String description) {
        Ticket ticket = new Ticket(0, clientName, phoneNumber, type, priority, description);
        return repository.save(ticket);
    }

    public List<Ticket> findAll() {
        return repository.findAll();
    }

    public Optional<Ticket> findById(int id) {
        return repository.findById(id);
    }

    public Optional<Ticket> changeStatus(int id, TicketStatus newStatus) {
        Optional<Ticket> optional = repository.findById(id);
        optional.ifPresent(ticket -> {
            ticket.setStatus(newStatus);
            repository.save(ticket);
        });
        return optional;
    }

    public Optional<Ticket> changePriority(int id, TicketPriority newPriority) {
        Optional<Ticket> optional = repository.findById(id);
        optional.ifPresent(ticket -> {
            ticket.setPriority(newPriority);
            repository.save(ticket);
        });
        return optional;
    }

    public boolean deleteTicket(int id) {
        if (!repository.existsById(id)) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }

    public List<Ticket> findByStatus(TicketStatus status) {
        return repository.findByStatus(status);
    }

    public List<Ticket> findByType(TicketType type) {
        return repository.findByType(type);
    }

    public boolean saveToFile() {
        try {
            fileStorage.saveAll(repository.findAll());
            return true;
        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde des tickets : " + e.getMessage());
            return false;
        }
    }

    public boolean loadFromFile() {
        try {
            List<Ticket> tickets = fileStorage.loadAll();
            repository.replaceAll(tickets);
            return true;
        } catch (IOException e) {
            System.out.println("Erreur lors du chargement des tickets : " + e.getMessage());
            return false;
        }
    }
}
