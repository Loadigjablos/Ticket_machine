package ch.csbe.statics;

import ch.csbe.Menus.*;
import ch.csbe.l10n.Localization;
/**
 * The start of the application
 * @author domin
 */
public class Main {
	
	private static String startPosition; // the location of the Ticket Machine.
	
	private static String startLocaleLanguage; // the Locale Language, that is spoken.
	/**
	 * Fills Variables and makes a GUI
	 * @param args the parameters that can be used to declare where the ticket machine is and which language that is spocken
	 */
	public static void main(String[] args) {
		try {
			startLocaleLanguage = args[0];
			Localization.setLocaleLanguage(startLocaleLanguage);
		} catch(Exception e) {
			System.out.println("no argument");
		}
		try {
			startPosition = args[1];
		} catch(Exception e) {
			System.out.println("no argument");
		}
		
		StartScreen.getInstance().setVisible(true);
	}
	/**
	 * @return in Which City or Village they are
	 */
	public static String getStartPosition() {
		return startPosition;
	}
	/**
	 * @return the Locale Language
	 */
	public static String getStartLocaleLanguage() {
		return startLocaleLanguage;
	}
}
