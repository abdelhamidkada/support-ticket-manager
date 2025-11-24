package ma.abdelhamid.supporttickets;

import java.util.*;

public class TicketRepository {

    private final Map<Integer, Ticket> tickets = new LinkedHashMap<>();
    private int nextId = 1;

    public Ticket save(Ticket ticket) {
        if (ticket.getId() == 0) {
            ticket.setId(nextId++);
        } else if (ticket.getId() >= nextId) {
            nextId = ticket.getId() + 1;
        }
        tickets.put(ticket.getId(), ticket);
        return ticket;
    }

    public Optional<Ticket> findById(int id) {
        return Optional.ofNullable(tickets.get(id));
    }

    public List<Ticket> findAll() {
        return new ArrayList<>(tickets.values());
    }

    public void deleteById(int id) {
        tickets.remove(id);
    }

    public boolean existsById(int id) {
        return tickets.containsKey(id);
    }

    public List<Ticket> findByStatus(TicketStatus status) {
        List<Ticket> result = new ArrayList<>();
        for (Ticket ticket : tickets.values()) {
            if (ticket.getStatus() == status) {
                result.add(ticket);
            }
        }
        return result;
    }

    public List<Ticket> findByType(TicketType type) {
        List<Ticket> result = new ArrayList<>();
        for (Ticket ticket : tickets.values()) {
            if (ticket.getType() == type) {
                result.add(ticket);
            }
        }
        return result;
    }

    public void replaceAll(List<Ticket> newTickets) {
        tickets.clear();
        int maxId = 0;
        for (Ticket t : newTickets) {
            tickets.put(t.getId(), t);
            if (t.getId() > maxId) {
                maxId = t.getId();
            }
        }
        nextId = maxId + 1;
    }
}
