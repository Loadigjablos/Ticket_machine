package ch.csbe.l10n;

import java.util.ArrayList;
/**
 * tis is to alter text if the locale Language is changed
 * @author domin
 */
public class Localization {
	
	private static String localeLanguage = "de_CH";
	
	private static ArrayList<LocaleListener> Instances = new ArrayList<LocaleListener>(); // every instance that has text that can be translated is here
	/**
	 * @return the current Language
	 */
	public static String getLocaleLanguage() {
		return localeLanguage;
	}
	/**
	 * this changes the language and asks every instance in the array to translate its content
	 * @param newLocaleLanguage the new set Language
	 */
	public static void setLocaleLanguage(String newLocaleLanguage) {
		localeLanguage = newLocaleLanguage;
		for (int i = 0; i < Instances.size(); i++) {
			Instances.get(i).localeChanged();
		}
	}
	/**
	 * is for buttons or labels that have text in them.
	 * @param listener the Instance that has text that can be altered
	 */
	public static void addInstance(LocaleListener listener) {
		Instances.add(listener);
	}
}
