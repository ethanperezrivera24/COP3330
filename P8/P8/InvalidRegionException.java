// Ethan Perez-Rivera
// 11/08/2026
// Custom exception thrown for an region number outside the valid range for P8

public class InvalidRegionException extends Exception {

    // Pass the message up to Exception so getMessage() and the stack trace can show it.
    public InvalidRegionException(String s) {
        super(s);
    }
}
