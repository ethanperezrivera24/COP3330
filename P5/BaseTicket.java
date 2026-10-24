
// Arup Guha
// 10/5/2026
// BaseTicket Class for COP 3330 Program 5 Framework

public class BaseTicket {
    protected Money cost;
    protected Date date;

    // Typical constructor.
    public BaseTicket(Money m, Date d) {
        cost = m;
        date = d;
    }

    // To String defines out a single ticket prints out.
    public String toString() {
        return "Date: "+date+" Cost: "+cost;
    }

    // Says a message containing what perks the ticket contains
    public String getGameDayPerk() {
        return "Welcome to the ballpark!";
    }
}
