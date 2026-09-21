// Ethan Perez-Rivera
// 9/16/2026
// Seating Chart Manager

import java.util.*;

public class ticketing_v1 {
    // Venue dimensions and region configurations
    final public static int TOTAL_ROWS = 26;
    final public static int TOTAL_COLS = 60;
    final public static int REGION_ROWS = 13;
    final public static int REGION_COLS = 20;
    
    // Ticket prices for regions 1 through 6
    final public static int[] COST = {200, 300, 200, 100, 150, 100};

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
                System.out.println("How many tickets do you want?");
                int numTickets = stdin.nextInt();

                int cost = buySeats(seats, region, numTickets);

                // Update running totals if transaction succeeded
                if (cost > 0) {
                    ticketsSold += cost / COST[region - 1];
                    totalRevenue += cost;
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
    public static int getMenuChoice(Scanner stdin) {
        int choice;
        while (true) {
            System.out.println("Please select a choice on the menu below:");
            System.out.println("1. Buy Concert Tickets");
            System.out.println("2. See Stadium Ticket Map");
            System.out.println("3. View Total Revenue, Tickets Sold");
            System.out.println("4. Exit Ticketing System V1");
            choice = stdin.nextInt();

            if (choice >= 1 && choice <= 4) break;

            System.out.println();
            System.out.println("Sorry that choice is not valid. Please try again.\n");
        }
        return choice;
    }

    // Prompts user for target region and validates input (1-6)
    public static int getRegion(Scanner stdin) {
        int region;
        while (true) {
            System.out.println("Which region(1-6) do you want your seats in?");
            region = stdin.nextInt();

            if (region >= 1 && region <= 6) break;

            System.out.println();
            System.out.println("Sorry that region is not valid. Please try again.");
        }
        return region;
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
    public static int buySeats(char[][] seats, int region, int numTickets) {
        System.out.println();
        // Reject negative ticket amounts
        if (numTickets < 0) {
            System.out.println("Sorry you can't buy a negative number of seats.");
            System.out.println();
            return 0;
        }
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
                    System.out.print((char)('A' + i) + "" + j + " ");
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