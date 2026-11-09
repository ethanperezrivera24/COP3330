// Ethan Perez-Rivera
// 11/08/2026
// Custom exception thrown for an negative number of seats requested for P8

public class NegativeSeatException extends Exception {

    // Pass the message up to Exception so getMessage() and the stack trace can show it.
    public NegativeSeatException(String s) {
        super(s);
    }
}
