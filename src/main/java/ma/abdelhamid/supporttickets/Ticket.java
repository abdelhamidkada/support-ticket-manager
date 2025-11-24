package ma.abdelhamid.supporttickets;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Ticket {

    private int id;
    private String clientName;
    private String phoneNumber;
    private String description;
    private TicketType type;
    private TicketPriority priority;
    private TicketStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdated;

    public Ticket(int id,
                  String clientName,
                  String phoneNumber,
                  TicketType type,
                  TicketPriority priority,
                  String description) {
        this.id = id;
        this.clientName = clientName;
        this.phoneNumber = phoneNumber;
        this.type = type;
        this.priority = priority;
        this.description = description;
        this.status = TicketStatus.OPEN;
        this.createdAt = LocalDateTime.now();
        this.lastUpdated = this.createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getDescription() {
        return description;
    }

    public TicketType getType() {
        return type;
    }

    public TicketPriority getPriority() {
        return priority;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
        this.lastUpdated = LocalDateTime.now();
    }

    public void setPriority(TicketPriority priority) {
        this.priority = priority;
        this.lastUpdated = LocalDateTime.now();
    }

    public void setDescription(String description) {
        this.description = description;
        this.lastUpdated = LocalDateTime.now();
    }

    public String formatForList() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return String.format(
                "#%d | %s | %s | %s | %s | %s | créé le %s",
                id,
                clientName,
                type,
                priority,
                status,
                phoneNumber,
                createdAt.format(formatter)
        );
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return "Ticket #" + id + System.lineSeparator() +
                "Client      : " + clientName + System.lineSeparator() +
                "Téléphone   : " + phoneNumber + System.lineSeparator() +
                "Type        : " + type + System.lineSeparator() +
                "Priorité    : " + priority + System.lineSeparator() +
                "Statut      : " + status + System.lineSeparator() +
                "Créé le     : " + createdAt.format(formatter) + System.lineSeparator() +
                "Mis à jour  : " + lastUpdated.format(formatter) + System.lineSeparator() +
                "Description : " + description;
    }
}
