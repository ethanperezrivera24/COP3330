/*  Ethan Perez-Rivera
    8/26/26
    Program calculating whether you can afford to purchase a concert ticket depending on your
    hours worked & pay.
*/

import java.util.*; // Import java.util package

public class attend {
    public static void main(String[] args){
        // Init scanner
        Scanner scanner = new Scanner(System.in);

        // Scan user input for hours
        System.out.println("How many hours did you babysit?");
        int hours = scanner.nextInt();

        // Scan user input for pay
        System.out.println("How much did you get paid per hour of babysitting?");
        float pay = scanner.nextFloat();

        // Scan user input for ticket cost
        System.out.println("What is the cost of a single concert ticket?");
        float cost = scanner.nextFloat();

        // Check if user has enough money to afford ticket and print corresponding message
        if (cost <= hours * pay)
            System.out.println("Great, you earned enough money to attend the concert!");
        else
            System.out.println("Sorry, you haven't made enough money to attend the concert :(");

        // Free scanner
        scanner.close();
    }
}