
// Arup Guha
// 10/5/2026
// Date Class for COP 3330 Program 5 Framework

public class Date {
    // For out toString method.
    final public static String[] MONTH_NAMES = {"",
    "January","February","March","April","May","June","July",
    "August","September","October","November","December"};
    private int day;
    private int month;
    private int year;

    // Creates a date object on the day myday, month mymonth and year myyear.
    // User must pass in a valid date.
    public Date(int myday, int mymonth, int myyear) {
        day = myday;
        month = mymonth;
        year = myyear;
    }

    // Returns a String representation of this Date object.
    public String toString() {
        return MONTH_NAMES[month]+" "+day+", "+year;
    }
}
