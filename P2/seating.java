/*  Ethan Perez-Rivera
    9/2/26
    Simplifies the seating chart for a concert venue as a rectangle with rows and columns.
*/

import java.util.*;

public class seating {
    public static void main(String[] args) {
        // Initialize scanner, random, & local variables
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();
        int numRows, numCols, percent, totalSeats;

        // Get numRows, numCols, and percent from user & calculate totalSeats
        System.out.println("How many rows in the concert venue?");
        numRows = scanner.nextInt();
        System.out.println("How many columns in the concert venue?");
        numCols = scanner.nextInt();
        System.out.println("What percentage of the tickets are already sold?");
        percent = scanner.nextInt();
        totalSeats = numRows * numCols;

        // For loop to print seating arrangement
        for(int i = 0; i < totalSeats; i++) {
            // Determine row and column
            int row = i / numCols;
            int col = i % numCols;

            // Roll between 0-99 and if less than percent, seat is taken. Print seat taken or open.
            int roll = rand.nextInt(100);
            char seat = (roll < percent) ? 'X' : 'O';
            System.out.print(seat);

            // If on 20th seat & not the end of a row, add a space inbetween
            if (col % 20 == 19 && col != numCols - 1)
                System.out.print(' ');

            // If end of row, go to next line
            if(col == numCols - 1) {
                System.out.println();

                // If on 5th row & not final row, add a blank line inbetween
                if (row % 5 == 4 && row != numRows - 1)
                    System.out.println();
            }
        }

        // Close scanner
        scanner.close();
    }
}

