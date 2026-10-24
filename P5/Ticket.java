// Arup Guha
// 10/5/2026
// Ticket Class for COP 3330 Program 5 Framework

public class Ticket extends BaseTicket {
    protected char row;
    protected int seatNum;

    // Typical constructor.
    public Ticket(Money m, Date d, char myRow, int mySeat) {
        super(m, d);
        row = myRow;
        seatNum = mySeat;
    }

    // To String defines out a single ticket prints out.
    public String toString() {
        return super.toString()+" Seat: "+row+" "+seatNum;
    }

    // Says a message containing what perks the ticket contains
    public String getGameDayPerk() {
        return "Welcome to the ballpark! Don't forget to pick up your free Marlins schedule magnet!";
    }
}