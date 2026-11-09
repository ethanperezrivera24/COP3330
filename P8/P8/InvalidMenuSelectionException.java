// Ethan Perez-Rivera
// 11/08/2026
// Custom exception thrown for an invalid main menu choice (not 1-4) for P8

public class InvalidMenuSelectionException extends Exception {

    // Pass the message up to Exception so getMessage() and the stack trace can show it.
    public InvalidMenuSelectionException(String s) {
        super(s);
    }
}
