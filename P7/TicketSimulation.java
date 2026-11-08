// Ethan Perez-Rivera
// 10/30/26
// Ticket Simulation program for part C of P7

import java.util.*;
import java.io.*;

public class TicketSimulation {

    public static void main(String[] args) throws Exception {

        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(stdin.readLine().trim());

        // poll() always returns the "smallest" Buyer according to compareTo.
        PriorityQueue<Buyer> queue = new PriorityQueue<Buyer>();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            StringTokenizer tok = new StringTokenizer(stdin.readLine());
            int action = Integer.parseInt(tok.nextToken());

            // Action 1: a new buyer joins the queue.
            if (action == 1) {
                String name = tok.nextToken();
                int priority = Integer.parseInt(tok.nextToken());
                int tickets = Integer.parseInt(tok.nextToken());
                queue.add(new Buyer(name, priority, tickets));
            }

            // Action 2: hand out released tickets until none are left.
            else {
                int available = Integer.parseInt(tok.nextToken());

                while (available > 0) {
                    Buyer next = queue.poll();

                    // Buyer's whole request can be filled; they leave the queue.
                    if (next.getNumTickets() <= available) {
                        sb.append(next.getName()).append(" receives ")
                          .append(next.getNumTickets()).append(" tickets.\n");
                        available -= next.getNumTickets();
                    }

                    // Partial fill: give them everything left and re-add them.
                    else {
                        sb.append(next.getName()).append(" receives ")
                          .append(available).append(" tickets.\n");
                        next.reduceTickets(available);
                        available = 0;
                        queue.add(next);
                    }
                }
            }
        }

        System.out.print(sb);
    }
}