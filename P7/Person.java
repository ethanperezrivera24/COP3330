// Ethan Perez-Rivera
// 10/30/26
// Person class for part A of P7

public class Person {

    private String firstName;
    private String lastName;
    private int numTickets;

    // Builds a Person from their names and ticket count.
    public Person(String first, String last, int tickets) {
        firstName = first;
        lastName = last;
        numTickets = tickets;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getNumTickets() {
        return numTickets;
    }

    // Returns the required output line for when this person buys tickets.
    public String toString() {
        return firstName + " " + lastName + " bought " + numTickets + " tickets.";
    }
}