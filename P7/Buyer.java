// Ethan Perez-Rivera
// 10/30/26
// Buyer class for part C of P7

public class Buyer implements Comparable<Buyer> {

    private String name;
    private int priority;
    private int numTickets;

    // Constructor
    public Buyer(String name, int priority, int numTickets) {
        this.name = name;
        this.priority = priority;
        this.numTickets = numTickets;
    }

    public String getName() {
        return name;
    }

    public int getNumTickets() {
        return numTickets;
    }

    // Lowers this buyer's remaining request after a partial sale.
    public void reduceTickets(int amount) {
        numTickets -= amount;
    }

    // Negative return value means "this" buyer gets tickets first.
    public int compareTo(Buyer other) {
        // lower priority number goes first.
        if (this.priority != other.priority)
            return Integer.compare(this.priority, other.priority);

        // fewer tickets requested goes first.
        if (this.numTickets != other.numTickets)
            return Integer.compare(this.numTickets, other.numTickets);

        // name that comes first lexicographically.
        return this.name.compareTo(other.name);
    }
}