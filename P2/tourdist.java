/*  Ethan Perez-Rivera
    9/1/26
    Computes total distance for band on tour
*/

import java.util.*;

public class tourdist {
    public static void main(String[] args) {
        // Initialize scanner & local variables
        Scanner scanner = new Scanner(System.in);
        int total = 0;
        int n;

        // Get # of locations
        System.out.println("How many locations are on the tour?");
        n = scanner.nextInt();

        // Loop to get distance between each location & add to total
        for(int i = 1; i < n; i++) {
            System.out.println("What is the distance, in miles, between location " + i + " and location " + (i + 1) + "?");
            int dist = scanner.nextInt();
            total += dist;
        }

        // Print result & close scanner
        System.out.println("The band will travel a total of " + total + " miles for their tour.");
        scanner.close();
    }
}