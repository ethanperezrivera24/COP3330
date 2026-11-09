// Ethan Perez-Rivera
// 11/8/2026
// Ticketing System with Exceptions for P8
// Seating Chart Manager, modified from P3 (ticketing_v1.java).
//
// P8 changes (marked with "P8" comments below):
//   Task #1 - Invalid menu choices, invalid regions, and negative seat counts are now
//             signaled with InvalidMenuSelectionException, InvalidRegionException, and
//             NegativeSeatException, and caught so the program behaves like Program #3.
//   Task #2 - Non-integer input is caught (InputMismatchException) for every number the
//             user types; the bad input is discarded and the user is reprompted.
//   Fix     - Ticket labels now print the seat number (j + 1) instead of the array index j.

import java.util.*;

public class ticketing_v3 {
    // Venue dimensions and region configurations
    final public static int TOTAL_ROWS = 26;
    final public static int TOTAL_COLS = 60;
    final public static int REGION_ROWS = 13;
    final public static int REGION_COLS = 20;

    // Ticket prices for regions 1 through 6
    final public static int[] COST = {200, 300, 200, 100, 150, 100};

    // P8: number of regions
    final public static int NUM_REGIONS = (TOTAL_ROWS / REGION_ROWS) * (TOTAL_COLS / REGION_COLS);

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        char[][] seats = makeEmptyChart();
        int ticketsSold = 0;
        int totalRevenue = 0;

        // Main event loop
        while (true) {
            int choice = getMenuChoice(stdin);

            // Option 1: Process seat purchase
            if (choice == 1) {
                int region = getRegion(stdin);

                // P8: a negative seat count is now thrown by getNumTickets and caught here,
                // which cancels the purchase just like Program #3 did.
                try {
                    int numTickets = getNumTickets(stdin);
                    int cost = buySeats(seats, region, numTickets);

                    // Update running totals if transaction succeeded
                    if (cost > 0) {
                        ticketsSold += cost / COST[region - 1];
                        totalRevenue += cost;
                    }
                }
                catch (NegativeSeatException e) {
                    System.out.println();
                    System.out.println("Sorry you can't buy a negative number of seats.");
                    System.out.println();
                }
            // Option 2: Display current chart layout
            } else if (choice == 2) {
                printChart(seats);
            // Option 3: Display current sales summary
            } else if (choice == 3) {
                System.out.println();
                System.out.println("Current tickets sold = " + ticketsSold);
                System.out.println("Current revenue = $" + totalRevenue);
                System.out.println();
            // Option 4: Display final totals and exit
            } else {
                System.out.println();
                System.out.println("Thank you for using Ticketing System V1");
                System.out.println("Total tickets sold = " + ticketsSold);
                System.out.println("Total revenue = $" + totalRevenue);
                System.out.println("Here is the final seating chart:");
                printChartBody(seats);
                break;
            }
        }
    }

    // Creates and populates the seating array with 'O' for open seats
    public static char[][] makeEmptyChart() {
        char[][] seats = new char[TOTAL_ROWS][TOTAL_COLS];
        for (int i = 0; i < TOTAL_ROWS; i++) {
            for (int j = 0; j < TOTAL_COLS; j++)
                seats[i][j] = 'O';
        }
        return seats;
    }

    // Prompts user for menu choice and validates input (1-4)
    // P8: validation now uses InvalidMenuSelectionException, and non-integers are caught.
    public static int getMenuChoice(Scanner stdin) {
        while (true) {
            System.out.println("Please select a choice on the menu below:");
            System.out.println("1. Buy Concert Tickets");
            System.out.println("2. See Stadium Ticket Map");
            System.out.println("3. View Total Revenue, Tickets Sold");
            System.out.println("4. Exit Ticketing System V1");

            try {
                int choice = stdin.nextInt();
                checkMenuChoice(choice);
                return choice;
            }
            catch (InvalidMenuSelectionException e) {
                System.out.println();
                System.out.println("Sorry that choice is not valid. Please try again.\n");
            }
            catch (InputMismatchException e) {
                stdin.nextLine();   // discard the bad input so nextInt() doesn't fail forever
                System.out.println();
                System.out.println("Sorry, please enter a whole number for your choice.\n");
            }
        }
    }

    // P8: throws InvalidMenuSelectionException if choice is not 1-4
    public static void checkMenuChoice(int choice) throws InvalidMenuSelectionException {
        if (choice < 1 || choice > 4)
            throw new InvalidMenuSelectionException("Invalid menu choice: " + choice);
    }

    // Prompts user for target region and validates input (1-6)
    // P8: validation now uses InvalidRegionException, and non-integers are caught.
    public static int getRegion(Scanner stdin) {
        while (true) {
            System.out.println("Which region(1-" + NUM_REGIONS + ") do you want your seats in?");

            try {
                int region = stdin.nextInt();
                checkRegion(region);
                return region;
            }
            catch (InvalidRegionException e) {
                System.out.println();
                System.out.println("Sorry that region is not valid. Please try again.");
            }
            catch (InputMismatchException e) {
                stdin.nextLine();
                System.out.println();
                System.out.println("Sorry, please enter a whole number for the region.");
            }
        }
    }

    // P8: throws InvalidRegionException if region is not 1-NUM_REGIONS
    public static void checkRegion(int region) throws InvalidRegionException {
        if (region < 1 || region > NUM_REGIONS)
            throw new InvalidRegionException("Invalid region: " + region);
    }

    // P8: reads the number of tickets. Non-integers are caught and the user is reprompted.
    // A negative count is thrown to main as a NegativeSeatException.
    public static int getNumTickets(Scanner stdin) throws NegativeSeatException {
        while (true) {
            System.out.println("How many tickets do you want?");

            try {
                int numTickets = stdin.nextInt();
                if (numTickets < 0)
                    throw new NegativeSeatException("Negative seat count: " + numTickets);
                return numTickets;
            }
            catch (InputMismatchException e) {
                stdin.nextLine();
                System.out.println();
                System.out.println("Sorry, please enter a whole number of tickets.");
            }
        }
    }

    // Prints header banner and the seating chart
    public static void printChart(char[][] seats) {
        System.out.println();
        System.out.println("Here is the current seating chart:");
        printChartBody(seats);
        System.out.println();
    }

    // Displays the visual grid with column headers, row labels (A-Z), and region spacing
    public static void printChartBody(char[][] seats) {
        System.out.println();
        System.out.println("  12345678901234567890 12345678901234567890 12345678901234567890 ");
        for (int i = 0; i < TOTAL_ROWS; i++) {
            // Add row spacing between region bands
            if (i % REGION_ROWS == 0)
                System.out.println();

            // Convert row index to letter (A, B, C...)
            System.out.print((char)('A' + i) + " ");

            for (int j = 0; j < TOTAL_COLS; j++) {
                System.out.print(seats[i][j]);
                // Add column spacing between regions
                if ((j + 1) % REGION_COLS == 0)
                    System.out.print(" ");
            }
            System.out.println();
        }
    }

    // Reserves available seats in a region, marks them as 'X', and returns total cost
    // P8: the negative-count check moved out of here (now NegativeSeatException in getNumTickets).
    public static int buySeats(char[][] seats, int region, int numTickets) {
        System.out.println();
        // Check if enough seats are available
        if (numOpenSeats(seats, region) < numTickets) {
            System.out.println("Sorry the transaction couldn't be made.");
            System.out.println("Not enough available seats.");
            System.out.println();
            return 0;
        }

        int startRow = regionStartRow(region);
        int startCol = regionStartCol(region);
        int cost = COST[region - 1];
        int totalCost = 0;
        int sold = 0;

        // Reserve open seats row by row within the specified region
        System.out.println("Here are the tickets you are receiving:");
        for (int i = startRow; i < startRow + REGION_ROWS && sold < numTickets; i++) {
            for (int j = startCol; j < startCol + REGION_COLS && sold < numTickets; j++) {
                if (seats[i][j] == 'O') {
                    seats[i][j] = 'X';  // Mark seat as sold
                    // P8 fix: seat numbers start at 1, so print j + 1 (index 20 is seat 21)
                    System.out.print((char)('A' + i) + "" + (j + 1) + " ");
                    totalCost += cost;
                    sold++;
                    // Print 20 seat locations per line
                    if (sold % 20 == 0) System.out.println();
                }
            }
        }
        if (sold % 20 != 0) System.out.println();
        System.out.println();

        return totalCost;
    }

    // Counts remaining available seats ('O') in a specific region
    public static int numOpenSeats(char[][] seats, int region) {
        int count = 0;
        int startRow = regionStartRow(region);
        int startCol = regionStartCol(region);
        for (int i = startRow; i < startRow + REGION_ROWS; i++) {
            for (int j = startCol; j < startCol + REGION_COLS; j++) {
                if (seats[i][j] == 'O')
                    count++;
            }
        }
        return count;
    }

    // Calculates starting row index for a given region (1-6)
    public static int regionStartRow(int region) {
        int r = region - 1;
        int numColBands = TOTAL_COLS / REGION_COLS;
        int rowBand = r / numColBands;
        return rowBand * REGION_ROWS;
    }

    // Calculates starting column index for a given region (1-6)
    public static int regionStartCol(int region) {
        int r = region - 1;
        int numColBands = TOTAL_COLS / REGION_COLS;
        int colBand = r % numColBands;
        return colBand * REGION_COLS;
    }
}