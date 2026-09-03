/*  Ethan Perez-Rivera
    8/26/26
    Program asking for the cost of tickets & how many tickets purchased to calculate a total.
*/

import java.util.*; // Import java.util package

public class tickets {
    public static void main(String[] args) {
        // Init scanner & total
        Scanner scanner = new Scanner(System.in);
        float total;
        
        // Scan user input for lower ticket cost
        System.out.println("How much does a single lower bowl ticket cost in dollars?");
        float lowerTix = scanner.nextFloat();
        
        // Scan user input for upper ticket cost
        System.out.println("How much does a single upper bowl ticket cost in dollars?");
        float upperTix = scanner.nextFloat();
        
        // Scan # of lower tickets user is purchasing
        System.out.println("How many lower bowl tickets are you purchasing?");
        int numLow = scanner.nextInt();
        
        // Scan # of upper tickets user is purchasing
        System.out.println("How many upper bowl tickets are you purchasing?");
        int numUp = scanner.nextInt();

        // Calculate total & print to two decimals
        total = (lowerTix * numLow) + (upperTix * numUp);
        System.out.printf("Your total cost is $%.2f.\n", total);

        // Free scanner
        scanner.close();
    }
}