Ethan Perez-Rivera
10/10/26
COP 3330 - Program #5: Inheritance and Tickets
Theme: Miami Marlins "Gate Check"
 
HOW TO RUN
----------
1. Open a terminal inside the P5 folder.
2. Compile:  javac *.java
3. Run:      java TicketApplication
4. Use the menu by typing a number (1-5) and pressing Enter.
 
HOW TO TEST THE REQUIRED FEATURES
---------------------------------
The program builds three tickets (a Ticket, a VIPTicket, and a ClevelanderTicket)
and stores them in one BaseTicket[] array. Each menu option demonstrates one
required feature:
 
  Option 1 - (a) Inheritance
    Calls eatFood() and meetPlayer() on the ClevelanderTicket. ClevelanderTicket
    does not define either method; it inherits them from VIPTicket.
 
  Option 2 - (b) Polymorphism via toString
    Prints every ticket with one loop over the BaseTicket array. Each ticket
    prints differently (plain / VIP food units + player met/ VIP food units + player met + drink units).
 
  Option 3 - (c) Polymorphism via a new method
    Calls getGameDayPerk(), a method I added. It is defined in BaseTicket and
    overridden in Ticket, VIPTicket, and ClevelanderTicket, and the message
    changes depending on the real type of each ticket.
 
  Option 4 - (d) Selective functionality using instanceof
    "Scans" each fan at the gate. instanceof routes Clevelander fans to the party
    deck, VIP fans to the club gate, and everyone else to the main gate. The
    ClevelanderTicket check comes BEFORE the VIPTicket check because a
    ClevelanderTicket is also a VIPTicket; if VIPTicket were checked first, the
    Clevelander branch would never run.
 
  Option 5 - Quit
 
Note: a ticket can only meet the player once, so if option 1 is run first, the
Clevelander ticket will report "already met a player" in option 4.
This is expected behavior because the tickets retain state.
The menu options share the same tickets, so earlier options change what later ones show.

 
NEW CLASS
---------
ClevelanderTicket extends VIPTicket. It tracks drink units the same way VIPTicket
tracks food units (curDrinksHad / maxDrinksAllowed), has a haveDrinks(int) method,
and overrides toString() and getGameDayPerk().
 
CHANGES TO THE PROVIDED CLASSES
-------------------------------
BaseTicket, Ticket, VIPTicket:
  - Added getGameDayPerk() (each class overrides it and builds on its parent's
    version with super).
VIPTicket:
  - Renamed metArtist -> metPlayer and meetArtist() -> meetPlayer(String
    playerName) to fit the baseball theme. Added a playerMet field. The player
    fans can meet is Sandy Alcantara.
  - toString() printed the seat twice (Ticket.toString() already includes it), so
    it now shows the VIP food units instead.
  - eatFood() used "curFoodEaten -= actualEat", which let the cap be ignored and
    made the counter go negative. Changed to "+=" so the maximum is enforced.