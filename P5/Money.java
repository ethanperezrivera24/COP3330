// Arup Guha
// 10/5/2026
// Money Class for COP 3330 Program 5 Framework

public class Money {
    private long dollars;
    private long cents;

    // Creates a Money object with d dollars and c cents. Assumes c, d >= 0.
    public Money(long d, long c) {
        dollars = d + c/100;
        cents = c%100;
    }

    // Returns the total number of cents in this money object.
    private long totalCents() {
        return 100*dollars + cents;
    }

    // Returns a String representation of this money object.
    public String toString() {
        if (cents >= 10)
            return "$"+dollars+"."+cents;
        return "$"+dollars+".0"+cents;
    }

    // Adds this to other and returns a new Money object storing the sum.
    public Money add(Money other) {
        long total = this.totalCents() + other.totalCents();
        return new Money(total/100, total%100);
    }
}