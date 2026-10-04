// Arup Guha
// 9/28/2026
// Driver code for COP 3330 Program 4 (Fall 2026)

/*** Note to student: Do NOT submit this! We will run your code with this file.
                      It's just posted so you can properly test the classes you
					  write.
***/

import java.util.*;

public class Ticketing_V2 {

	public static void main(String[] args) {
		
		Scanner stdin = new Scanner(System.in);
		
		// Number of regions with different columns.
		int numColRegions = stdin.nextInt();
		int[][] ticketPrices = new int[2][numColRegions];
		for (int i=0; i<2; i++)
			for (int j=0; j<numColRegions; j++)
				ticketPrices[i][j] = stdin.nextInt();
			
		// Create the inventory object.
		Inventory myConcert = new Inventory(ticketPrices);
		
		// Get number of commands to process.
		int numCmd = stdin.nextInt();
		
		// Process commands.
		for (int loop=0; loop<numCmd; loop++) {
			
			// Get the command type.
			int cmdType = stdin.nextInt();
			
			// Attempt a buy.
			if (cmdType == 1) {
				
				// Get region, number of seats.
				int region = stdin.nextInt();
				int numSeats = stdin.nextInt();
				
				// See if we can do it.
				boolean canDo = myConcert.canBuy(region, numSeats);

				// Error message for trying to buy too many tickets.
				if (!canDo) {
					System.out.println("Sorry the transaction couldn't be made.");
					System.out.println("Not enough available seats.");
				}
				
				// Do the transaction.
				else {
					System.out.println("Here are the tickets you are receiving:");
					myConcert.buySeats(region, numSeats);
				}
			}
			
			// See the Stadium map.
			else if (cmdType == 2) {
				System.out.print(myConcert);
			}
			
			// View total revenue, tickets sold.
			else {
				System.out.println("Total tickets sold = "+myConcert.getNumSold());
				System.out.println("Total revenue = $"+myConcert.getTotalRevenue());
			}
			
			// To make things easier to read.
			System.out.println();
		}
		
		// Ending summary.
		System.out.println("Thank you for using Ticketing System V2");
		System.out.println("Total tickets sold = "+myConcert.getNumSold());
		System.out.println("Total revenue = $"+myConcert.getTotalRevenue());
		System.out.print(myConcert);		
	}
}