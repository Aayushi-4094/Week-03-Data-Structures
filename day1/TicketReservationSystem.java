class Ticket {
    int ticketId;
    String customerName;
    String movieName;
    String seatNumber;
    String bookingTime;
    Ticket next;

    Ticket(int ticketId, String customerName, String movieName, String seatNumber, String bookingTime) {
        this.ticketId = ticketId;
        this.customerName = customerName;
        this.movieName = movieName;
        this.seatNumber = seatNumber;
        this.bookingTime = bookingTime;
        this.next = null;
    }
}

class CircularLinkedList {
    Ticket head = null;
    Ticket tail = null;

    void addTicket(int ticketId, String customerName, String movieName, String seatNumber, String bookingTime) {
        Ticket newTicket = new Ticket(ticketId, customerName, movieName, seatNumber, bookingTime);
        if (head == null) {
            head = newTicket;
            tail = newTicket;
            newTicket.next = head;
        } else {
            tail.next = newTicket;
            tail = newTicket;
            tail.next = head;
        }
    }

    void removeTicket(int ticketId) {
        if (head == null) return;
        Ticket current = head, prev = null;
        do {
            if (current.ticketId == ticketId) {
                if (prev == null) {
                    if (head == head.next) {
                        head = null;
                        tail = null;
                    } else {
                        head = current.next;
                        tail.next = head;
                    }
                } else {
                    prev.next = current.next;
                    if (current == tail) {
                        tail = prev;
                    }
                }
                return;
            }
            prev = current;
            current = current.next;
        } while (current != head);
    }

    void displayTickets() {
        if (head == null) {
            System.out.println("No tickets booked.");
            return;
        }
        Ticket current = head;
        do {
            System.out.println("Ticket ID: " + current.ticketId + ", Customer: " + current.customerName +
                    ", Movie: " + current.movieName + ", Seat: " + current.seatNumber + ", Booking Time: " + current.bookingTime);
            current = current.next;
        } while (current != head);
    }

    void searchTicket(String keyword) {
        if (head == null) {
            System.out.println("No tickets booked.");
            return;
        }
        Ticket current = head;
        do {
            if (current.customerName.contains(keyword) || current.movieName.contains(keyword)) {
                System.out.println("Ticket ID: " + current.ticketId + ", Customer: " + current.customerName +
                        ", Movie: " + current.movieName + ", Seat: " + current.seatNumber + ", Booking Time: " + current.bookingTime);
            }
            current = current.next;
        } while (current != head);
    }

    int totalTickets() {
        if (head == null) return 0;
        int count = 0;
        Ticket current = head;
        do {
            count++;
            current = current.next;
        } while (current != head);
        return count;
    }
}

public class TicketReservationSystem {
    public static void main(String[] args) {
        CircularLinkedList tickets = new CircularLinkedList();
        
        tickets.addTicket(1, "Alice", "Avatar 2", "A1", "2025-04-16 10:00");
        tickets.addTicket(2, "Bob", "The Batman", "B2", "2025-04-16 12:00");
        tickets.addTicket(3, "Charlie", "Avatar 2", "C3", "2025-04-16 14:00");

        tickets.displayTickets();

        System.out.println("Search result for 'Avatar':");
        tickets.searchTicket("Avatar");

        System.out.println("Total booked tickets: " + tickets.totalTickets());

        System.out.println("Removing ticket with ID 2.");
        tickets.removeTicket(2);

        tickets.displayTickets();

        System.out.println("Total booked tickets after removal: " + tickets.totalTickets());
    }
}
