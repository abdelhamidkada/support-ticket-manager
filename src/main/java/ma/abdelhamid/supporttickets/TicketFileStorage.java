package ma.abdelhamid.supporttickets;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TicketFileStorage {

    private final String filePath;

    public TicketFileStorage(String filePath) {
        this.filePath = filePath;
    }

    public void saveAll(List<Ticket> tickets) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Ticket t : tickets) {
                String line = String.join(";",
                        String.valueOf(t.getId()),
                        escape(t.getClientName()),
                        escape(t.getPhoneNumber()),
                        t.getType().name(),
                        t.getPriority().name(),
                        t.getStatus().name(),
                        escape(t.getDescription())
                );
                writer.write(line);
                writer.newLine();
            }
        }
    }

    public List<Ticket> loadAll() throws IOException {
        List<Ticket> tickets = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            return tickets;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";", 7);
                if (parts.length < 7) {
                    continue;
                }
                int id = Integer.parseInt(parts[0]);
                String clientName = unescape(parts[1]);
                String phone = unescape(parts[2]);
                TicketType type = TicketType.valueOf(parts[3]);
                TicketPriority priority = TicketPriority.valueOf(parts[4]);
                TicketStatus status = TicketStatus.valueOf(parts[5]);
                String description = unescape(parts[6]);

                Ticket ticket = new Ticket(
                        id,
                        clientName,
                        phone,
                        type,
                        priority,
                        description
                );
                ticket.setStatus(status);
                tickets.add(ticket);
            }
        }
        return tickets;
    }

    private String escape(String value) {
        if (value == null) {
            return "";
        }
        return value.replace(";", ",").replace("\n", " ");
    }

    private String unescape(String value) {
        return value;
    }
}
