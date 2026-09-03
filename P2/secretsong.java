/*  Ethan Perez-Rivera
    9/1/26
    Randomly selects a secret song from a list of choices provided by the user.
*/

import java.util.*;

public class secretsong {
    public static void main(String[] args) {
        // Initialize scanner, random, & local variables
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();
        int secretNum, n;
        String secretSong = "";

        // Get # of song choices & convert string input into int
        System.out.println("How many secret song choices are there?");
        n = Integer.parseInt(scanner.nextLine());
        
        // Randomize selection
        secretNum = rand.nextInt(n) + 1;

        // Loop to get song choices
        for(int i = 1; i <= n; i++) {
            String song;
            System.out.println("What is the title of the choice #" + i + "?");
            song = scanner.nextLine();

            // If on secretNum iteration, set secretSong to song
            if(i == secretNum)
                secretSong = song;
        }

        // Print result & close scanner
        System.out.println("The secret song for the concert is: " + secretSong + ".");
        scanner.close();
    }
}