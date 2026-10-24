// Ethan Perez-Rivera
// 10/10/26
// Marlins Gate Check: demo application for P5

import java.util.*;

public class TicketApplication {

    public static final String PLAYER = "Sandy Alcantara";

    public static void main(String[] args) {

        Scanner stdin = new Scanner(System.in);

        // Fans hold one of three ticket types: Ticket, VIPTicket, or ClevelanderTicket.
        // BaseTicket is shared parent
        // Constructors: Ticket(Money, Date, row, seat)
        //               VIPTicket(Money, Date, row, seat, maxFood)
        //               ClevelanderTicket(Money, Date, row, seat, maxFood, maxDrinks)
        BaseTicket[] tickets = new BaseTicket[3];
        tickets[0] = new Ticket(new Money(35, 0), new Date(5, 4, 2026), 'M', 14);
        tickets[1] = new VIPTicket(new Money(120, 50), new Date(5, 4, 2026), 'C', 8, 5);
        tickets[2] = new ClevelanderTicket(new Money(200, 0), new Date(5, 4, 2026), 'A', 1, 5, 4);

        // Menu loop
        int choice = 0;
        while (choice != 5) {
            printMenu();
            choice = stdin.nextInt();

            if (choice == 1)
                showInheritance(tickets);
            else if (choice == 2)
                showToString(tickets);
            else if (choice == 3)
                showPerks(tickets);
            else if (choice == 4)
                gateScan(tickets);
            else if (choice != 5)
                System.out.println("Pick a number from 1 to 5.");
        }

        System.out.println("Thanks for visiting the park. Go Fish!");
        stdin.close();
    }

    public static void printMenu() {
        System.out.println();
        System.out.println("=== MARLINS GATE CHECK ===");
        System.out.println("1. Inheritance demo");
        System.out.println("2. Print every ticket (toString polymorphism)");
        System.out.println("3. Show every ticket's game day perk (new polymorphic method)");
        System.out.println("4. Scan fans at the gate (instanceof)");
        System.out.println("5. Quit");
        System.out.print("Choice: ");
    }

    // (a) INHERITANCE
    // Clevelander ticket gets eatFood & meetPlayer from VIP ticket
    public static void showInheritance(BaseTicket[] tickets) {
        System.out.println();
        System.out.println("--- (a) Inheritance ---");
 
        // Find the Clevelander ticket in the array.
        ClevelanderTicket c = null;
        for (BaseTicket t : tickets)
            if (t instanceof ClevelanderTicket)
                c = (ClevelanderTicket) t;
 
        System.out.println("Before: " + c);
 
        // eatFood is defined in VIPTicket, but we call it on a ClevelanderTicket.
        c.eatFood(2);

        c.haveDrinks(1);
 
        // meetPlayer is also inherited from VIPTicket.
        if (c.meetPlayer(PLAYER))
            System.out.println("You met " + PLAYER + "!");
        else
            System.out.println("You already met a player with this ticket.");
 
        System.out.println("After:  " + c);
    }

    // (b) POLYMORPHISM VIA toString
    //  One variable type (BaseTicket), a different toString runs for each object.
    public static void showToString(BaseTicket[] tickets) {
        System.out.println();
        System.out.println("--- (b) toString polymorphism ---");
 
        for (BaseTicket t : tickets)
            System.out.println(t);
    }

    // (c) POLYMORPHISM VIA getGameDayPerk()
    // Java picks right version of getGameDayPerk @ runtime
    public static void showPerks(BaseTicket[] tickets) {
        System.out.println();
        System.out.println("--- (c) getGameDayPerk polymorphism ---");
 
        for (int i = 0; i < tickets.length; i++)
            System.out.println("Ticket " + (i+1) + ": " + tickets[i].getGameDayPerk());
    }

    // (d) SELECTIVE FUNCTIONALITY WITH instanceof
    //         if (t instanceof ClevelanderTicket)  -> cast, call haveDrinks, send to party deck gate
    //         else if (t instanceof VIPTicket)     -> cast, call meetPlayer("..."), send to club gate
    //         else                                 -> general gate
    public static void gateScan(BaseTicket[] tickets) {
        System.out.println();
        System.out.println("--- (d) Gate scan (instanceof) ---");
 
        for (BaseTicket t : tickets) {
 
            if (t instanceof ClevelanderTicket) {
                ClevelanderTicket c = (ClevelanderTicket) t;
                System.out.println("Clevelander fan -> party deck gate.");
                c.haveDrinks(2);
                if (c.meetPlayer(PLAYER))
                    System.out.println("You met " + PLAYER + "!");
                else
                    System.out.println("You already met a player with this ticket.");
            }
            else if (t instanceof VIPTicket) {
                VIPTicket v = (VIPTicket) t;
                System.out.println("VIP fan -> club gate.");
                v.eatFood(1);
                if (v.meetPlayer(PLAYER))
                    System.out.println("You met " + PLAYER + "!");
                else
                    System.out.println("You already met a player with this ticket.");
            }
            else {
                System.out.println("General admission fan -> main gate.");
            }
            System.out.println();
        }
    }
}