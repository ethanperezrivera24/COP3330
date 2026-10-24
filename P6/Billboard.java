// Ethan Perez-Rivera
// 10/17/26
// Reads song data from stdin, sorts by global rankings, & outputs yearly charts

import java.util.*;

public class Billboard {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read total number of songs
        int n = Integer.parseInt(scanner.nextLine().trim());

        Song[] songs = new Song[n];
        // Track which years appear
        boolean[] yearsPresent = new boolean[2027];

        // Read each song's information (3 lines per song)
        for (int i = 0; i < n; i++) {
            String title = scanner.nextLine();
            String artist = scanner.nextLine();
            String statsLine = scanner.nextLine();

            StringTokenizer tokenizer = new StringTokenizer(statsLine);
            int year = Integer.parseInt(tokenizer.nextToken());
            long sales = Long.parseLong(tokenizer.nextToken());
            long streams = Long.parseLong(tokenizer.nextToken());

            songs[i] = new Song(title, artist, year, sales, streams);
            
            if (year >= 1800 && year <= 2026) {
                yearsPresent[year] = true;
            }
        }

        scanner.close();

        // 1. Sort the global array of songs
        Arrays.sort(songs);

        // Output the global sorted list with rankings
        for (int i = 0; i < songs.length; i++) {
            System.out.println((i + 1) + ". " + songs[i]);
        }

        // 2. Output sorted lists grouped by year
        for (int year = 1800; year <= 2026; year++) {
            if (yearsPresent[year]) {
                // Count songs for this specific year
                int count = 0;
                for (int i = 0; i < songs.length; i++) {
                    if (songs[i].getYear() == year) {
                        count++;
                    }
                }

                // Make a temporary array for this year's songs
                Song[] yearSongs = new Song[count];
                int index = 0;
                for (int i = 0; i < songs.length; i++) {
                    if (songs[i].getYear() == year) {
                        yearSongs[index++] = songs[i];
                    }
                }

                // Sort the songs for this year
                Arrays.sort(yearSongs);

                // Output year on its own
                System.out.println(year);

                // Output each song in the year's list
                for (int i = 0; i < yearSongs.length; i++) {
                    System.out.println((i + 1) + ". " + yearSongs[i]);
                }
            }
        }
    }
}