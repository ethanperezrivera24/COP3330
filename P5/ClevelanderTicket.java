// Ethan Perez-Rivera
// 10/9/26
// Clevelander Ticket Class for P5

public class ClevelanderTicket extends VIPTicket {
    protected int curDrinksHad;
    protected int maxDrinksAllowed;

    public ClevelanderTicket (Money m, Date d, char myRow, int mySeat, int maxFood, int maxDrinks) {
        super(m, d, myRow, mySeat, maxFood);
        maxDrinksAllowed = maxDrinks;
        curDrinksHad = 0;
    }

    // I get to drink units amount of drinks (or whatever my max allowable is, whichever
    // is less).
    public void haveDrinks(int units) {
        int actualDrink = Math.min(maxDrinksAllowed-curDrinksHad, units);
        System.out.println("With your Clevelander perks, you drank "+actualDrink+" units of drinks.");
        curDrinksHad += actualDrink;
    }

    // To String defines out a single ticket prints out.
    public String toString() {
        return super.toString()+" *Clevelander: "+curDrinksHad+"/"+maxDrinksAllowed+" drink units*";
    }   

    // Says a message containing what perks the ticket contains
    public String getGameDayPerk() {
        return super.getGameDayPerk() + " Plus the party deck and a drink allowance!";
    }
}