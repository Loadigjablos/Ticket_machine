package ch.csbe.l10n;
/**
 * this interface describes every Component like Buttons or Lables that have one thing in common.
 * They have Text that can be changed.
 * @author domin
 */
public interface LocaleListener {

	public void localeChanged(); // will be used if the Locale language got changed
	
}
