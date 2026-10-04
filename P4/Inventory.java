// Ethan Perez-Rovera
// 10/3/26
// Framework File for COP 3330 Program #4
// Note: This must be filled in by the student and most of the code is here.

import java.util.*;

class Inventory {

	final public static int TOTAL_ROWS = 26;
	final public static int REGION_ROWS = 13;
	final public static int REGION_COLS = 20;

	private Ticket[][] seats;
	private boolean[][] sold;
	private int numColSections;
	private int numRegions;
	private int numSold;
	private int totalRevenue;
	
	// Pre-condition: The dimension of prices is 2 by n, corresponding to an arena with
	//                26 rows and 20n seats, with sections numbered 1 to 2n. The first
	//                n sections are rows A-M, and the last n sections are rows N-Z.
	//                prices[i][j] is the initial cost of every ticket in section n*i+j+1.
	// Post-condition: Initializes the inventory to be 26 rows and 20n columns of tickets,
	//                 set to the price indicated in the prices array, and setting no
	//                 tickets as sold.
	public Inventory(int[][] prices) {
	
		/*** Fill in code. ***/
		numColSections = prices[0].length; 	// number of sections for top half
		numRegions = 2 * numColSections;	// top half & bottom half

		int totalCols = REGION_COLS * numColSections;

		// allocate seats & sold grids. seats starts null. sold starts false
		seats = new Ticket[TOTAL_ROWS][totalCols];
		sold = new boolean[TOTAL_ROWS][totalCols];

		// create a Ticket for every seat
		for (int r = 0; r < TOTAL_ROWS; r++) {
			for (int c = 0; c < totalCols; c++) {

				int half = r / REGION_ROWS;      // 0 = rows A-M, 1 = rows N-Z
				int secCol = c / REGION_COLS;    // which block of 20 columns
				char rowLetter = (char)('A' + r);

				// set each Ticket's price
				seats[r][c] = new Ticket(rowLetter, c, prices[half][secCol]);
			}
		}
	}
	
	// Returns the current number of total tickets sold in the inventory.
	public int getNumSold() {
		/*** Fill in code. ***/
		return numSold;
	}
	
	// Returns the total revenue collected from the original ticket sales.
	public int getTotalRevenue() {
		/*** Fill in code. ***/
		return totalRevenue;
	}
	
	// Returns the number of available seats in the section with number secNum.
	// Note: if an invalid section number is given, 0 should be returned automatically.
	public int numAvailable(int secNum) {
		
		/*** Fill in code. ***/
		if(secNum < 1 || secNum > numRegions) return 0;

		int half = (secNum - 1) / numColSections; // top half = 0, bottom half = 1
		int sec = (secNum - 1) % numColSections; // which block of 20 columns within the half

		int available = 0;
		// iterate through section & increment if seat hasn't been sold
		for(int r = 0; r < REGION_ROWS; r++) {
			for(int c = 0; c < REGION_COLS; c++) {
				if(!sold[(REGION_ROWS * half) + r][(REGION_COLS * sec) + c])
					available++;
			}
		}

		return available;
	}
	
	// Returns true if we can buy numTickets tickets from region number region.
	// Note: Should return false if numTickets < 0.
	public boolean canBuy(int region, int numTickets) {

		/*** Fill in code. ***/
		return numTickets >= 0 && numTickets <= numAvailable(region);
	}
	
	// Pre-condition: Should only be called if numTickets number of tickets can be bought from
	//                the region with the region number region.
	// Post-condition: Executes the purchase, marking numTickets tickets (in the order described
	//                 in program 3) as sold in section with the number secNum. For each ticket
	//                 sold, a single line should be printed with the String representation of
	//                 a Ticket object. Use the sample input/output to determine this representation.
	public void buySeats(int secNum, int numTickets) {
	
		/*** Fill in code. ***/
		int half = (secNum - 1) / numColSections; // top half = 0, bottom half = 1
		int sec = (secNum - 1) % numColSections; // which block of 20 columns within the half

		int bought = 0;
		for(int r = 0; r < REGION_ROWS; r++) {
			for(int c = 0; c < REGION_COLS; c++) {
				if(bought >= numTickets)
					return;

				int row = (REGION_ROWS * half) + r;
				int col = (REGION_COLS * sec) + c;

				if(!sold[row][col]) {
					sold[row][col] = true;
					System.out.println(seats[row][col]);
					numSold++;
					totalRevenue += seats[row][col].getFaceValue();
					bought++;
				}
			}
		}
		
	}
	
	// Returns a String representation of the current object.
	// Note: This is the full string that gets printed for option #2, including the header
	//       "Here is the current seating chart:" and all of the newlines.
	public String toString() {
		
		/*** Fill in code. ***/
		// stringbuilder to make string mutable
		StringBuilder result = new StringBuilder("Here is the current seating chart:\n\n");

		int totalCols = REGION_COLS * numColSections;

		// numbers for seats in each section, with space inbetween sections
		result.append(" ");
		for (int b = 0; b < numColSections; b++)
			result.append(" 01234567890123456789");
		result.append(" \n");

		for (int r = 0; r < TOTAL_ROWS; r++) {
			// new line after rows in region
			if(r == REGION_ROWS) result.append("\n");

			// row letter + space
			result.append((char)('A' + r)).append(" ");

			for (int c = 0; c < totalCols; c++) {
				result.append(sold[r][c] ? 'X' : 'O'); // X if seat is taken, O if open

				// space after region ends
				if ((c + 1) % REGION_COLS == 0)
					result.append(" ");
			}
			result.append("\n");
		}

		return result.toString();
	}
}