// Ethan Perez-Rivera
// 10/30/26
// Sponsors program for part B of P7

import java.util.*;
import java.io.*;

public class Sponsors {

    public static void main(String[] args) throws Exception {

        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(stdin.readLine().trim());

        // Maps each band to its one current sponsor.
        HashMap<String, String> sponsorOf = new HashMap<String, String>();

        // Build output in one buffer.
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            int action = Integer.parseInt(stdin.readLine().trim());
            String band = stdin.readLine();

            // Action 1: band switches to a new sponsor (put overwrites the old one).
            if (action == 1) {
                String sponsor = stdin.readLine();
                sponsorOf.put(band, sponsor);
            }

            // Action 2: band loses its sponsor.
            else if (action == 2) {
                sponsorOf.remove(band);
            }

            // Action 3: report the band's sponsor, if there is one.
            else {
                String sponsor = sponsorOf.get(band);
                if (sponsor == null)
                    sb.append("No sponsor\n");
                else
                    sb.append(sponsor).append("\n");
            }
        }

        System.out.print(sb);
    }
}