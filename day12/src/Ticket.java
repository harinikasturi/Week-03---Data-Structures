class Ticket {
    String ticketId;
    String customerName;
    String movieName;
    String seatNumber;
    String bookingTime;
    Ticket next;

    public Ticket(String ticketId, String customerName, String movieName,
                  String seatNumber, String bookingTime) {
        this.ticketId = ticketId;
        this.customerName = customerName;
        this.movieName = movieName;
        this.seatNumber = seatNumber;
        this.bookingTime = bookingTime;
        this.next = null;
    }
}

class TicketReservationSystem {
    private Ticket head;
    private Ticket tail;
    private int count;

    public void addTicket(String ticketId, String customerName, String movieName,
                          String seatNumber, String bookingTime) {
        Ticket newTicket = new Ticket(ticketId, customerName, movieName, seatNumber, bookingTime);
        if (head == null) {
            head = newTicket;
            newTicket.next = head;
            tail = head;
        } else {
            tail.next = newTicket;
            newTicket.next = head;
            tail = newTicket;
        }
        count++;
    }

    public void removeTicket(String ticketId) {
        if (head == null) return;
        if (head.ticketId.equals(ticketId)) {
            if (head == tail) {
                head = tail = null;
            } else {
                head = head.next;
                tail.next = head;
            }
            count--;
            return;
        }
        Ticket prev = head;
        Ticket curr = head.next;
        while (curr != head && !curr.ticketId.equals(ticketId)) {
            prev = curr;
            curr = curr.next;
        }
        if (curr != head) {
            prev.next = curr.next;
            if (curr == tail) {
                tail = prev;
            }
            count--;
        }
    }

    public void displayTickets() {
        if (head == null) {
            System.out.println("No tickets booked");
            return;
        }
        System.out.println("Current Tickets:");
        Ticket temp = head;
        do {
            System.out.println("Ticket ID: " + temp.ticketId +
                    ", Customer: " + temp.customerName +
                    ", Movie: " + temp.movieName +
                    ", Seat: " + temp.seatNumber +
                    ", Time: " + temp.bookingTime);
            temp = temp.next;
        } while (temp != head);
    }

    public Ticket searchByCustomer(String customerName) {
        if (head == null) return null;
        Ticket temp = head;
        do {
            if (temp.customerName.equals(customerName)) {
                return temp;
            }
            temp = temp.next;
        } while (temp != head);
        return null;
    }

    public Ticket searchByMovie(String movieName) {
        if (head == null) return null;
        Ticket temp = head;
        do {
            if (temp.movieName.equals(movieName)) {
                return temp;
            }
            temp = temp.next;
        } while (temp != head);
        return null;
    }

    public int countTickets() {
        return count;
    }
}