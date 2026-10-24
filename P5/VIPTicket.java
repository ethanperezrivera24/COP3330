
// Arup Guha
// 10/5/2026
// VIPTicket Class for COP 3330 Program 5 Framework

public class VIPTicket extends Ticket {
    protected int curFoodEaten;
    protected int maxFoodAllowed;
    protected boolean metPlayer;          // was metArtist
    protected String playerMet;           // who they met (nobody until they meet one)

    // Typical constructor.
    public VIPTicket(Money m, Date d, char myRow, int mySeat, int maxFood) {
        super(m, d, myRow, mySeat);
        maxFoodAllowed = maxFood;
        curFoodEaten = 0;
        metPlayer = false;
        playerMet = "nobody";
    }

    // To String defines out a single ticket prints out.
    public String toString() {
        return super.toString()+" *VIP: "+curFoodEaten+"/"+maxFoodAllowed+" food units, met: "+playerMet+"*";
    }

    // I get to eat units amount of food (or whatever my max allowable is, whichever
    // is less).
    public void eatFood(int units) {
        int actualEat = Math.min(maxFoodAllowed-curFoodEaten, units);
        System.out.println("With your VIP perks, you ate "+actualEat+" units of food.");
        curFoodEaten += actualEat;
    }

    // Returns true if this is the first meeting, false if a player was already met.
    public boolean meetPlayer(String playerName) {
        // Not allowed to go twice.
        if (metPlayer) return false;
        // Yeah!
        metPlayer = true;
        playerMet = playerName;
        return true;
    }

    // Says a message containing what perks the ticket contains
    public String getGameDayPerk() {
        return super.getGameDayPerk() + " You have access to the club lounge and in-seat food service.";
    }
}
