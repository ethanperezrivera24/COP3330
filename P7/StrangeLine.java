// Ethan Perez-Rivera
// 10/30/26
// StrangeLine program for part A of P7

import java.util.*;
import java.io.*;

public class StrangeLine {

    public static void main(String[] args) throws Exception {

        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(stdin.readLine().trim());

        // Create empty line
        ArrayList<Person> line = new ArrayList<Person>();

        // Collect output here and print once at the end.
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            StringTokenizer tok = new StringTokenizer(stdin.readLine());
            int op = Integer.parseInt(tok.nextToken());

            // Operation 1: add person to the back of the line.
            if (op == 1) {
                String first = tok.nextToken();
                String last = tok.nextToken();
                int tickets = Integer.parseInt(tok.nextToken());
                line.add(new Person(first, last, tickets));
            }

            // Operation 2: insert person at index idx (they cut in line).
            else if (op == 2) {
                String first = tok.nextToken();
                String last = tok.nextToken();
                int tickets = Integer.parseInt(tok.nextToken());
                int idx = Integer.parseInt(tok.nextToken());
                line.add(idx, new Person(first, last, tickets));
            }

            // Operation 3: the person at the front buys tickets.
            else if (op == 3) {
                Person p = line.remove(0);
                sb.append(p).append("\n");
            }

            // Operation 4: the person at index idx buys tickets.
            else {
                int idx = Integer.parseInt(tok.nextToken());
                Person p = line.remove(idx);
                sb.append(p).append("\n");
            }
        }

        System.out.print(sb);
    }
}