// Ethan Perez-Rovera
// 10/3/26
// Framework File for COP 3330 Program #4

public class Ticket {

	private char row;
	private int seatNum;
	private int faceValueCost;

	// Creates a Ticket object for row thisRow, seat number thisSeat with an face value cost of faceValueCost.
	public Ticket(char thisRow, int thisSeat, int initCost) {
		/*** Fill in ***/
		// store each parameter in the matching field
		row = thisRow;
		seatNum = thisSeat;
		faceValueCost = initCost;
	}
	
	// Returns the face value cost of this ticket.
	public int getFaceValue() {
		/*** Fill in ***/
		// getter: hand back stored price
		return faceValueCost;
	}
	
	// Returns a string representation of this ticket.
	public String toString() {
		/*** Fill in ***/
		// build string matching output format
		return String.format("%c %d $%d", row, seatNum, faceValueCost);
	}

}