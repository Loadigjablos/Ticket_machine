package ch.csbe.statics;
/**
 * here is everything important to a Ticket
 * @author domin
 */
public class TicketValues {

	public static int count = 1; // how many tickets you want
	
	public static String type = "Singel"; // Multi, return, singel is the type of ticket
	
	public static String from = ""; // where you start
	
	public static String to = ""; // where you go
	
	public static int stations = 0; // how many stations to the destination
	
	public static int grade = 2; // in which class you go
	
	public static boolean reduced = false; // if the user wants the ticket reduced
	/**
	 * sets all values to it's normal state
	 */
	public static void setDefault() {
		count = 1;
		type = "Singel";
		from = Main.getStartPosition();
		grade = 2;
		reduced = false;
		stations = 0;
	}
}
