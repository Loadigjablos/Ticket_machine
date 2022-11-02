package ch.csbe.defaults;

import java.awt.Font;

import javax.swing.JRadioButton;

import ch.csbe.l10n.LocaleListener;
import ch.csbe.l10n.Localization;
/**
 * this is class for radio button only exists to have bigger font.
 * It also exists to be translated.
 * @author dominic
 */
public class RadioButton extends JRadioButton implements LocaleListener {

	private static final long serialVersionUID = 7906806653565444305L;
	
	private String thisTextUs_EN; // the button text in English
	private String thisTextDe_CH; // the button text in German
	private String thisTextFr_CH; // the button text in French
	/**
	 * sets the font triple than the usual
	 * @param thisTextUs_EN text in English
	 * @param thisTextDe_CH text in German
	 * @param thisTextFr_CH text in French
	 */
	public RadioButton(String thisTextUs_EN, String thisTextDe_CH, String thisTextFr_CH) {
		this.thisTextUs_EN = thisTextUs_EN;
		this.thisTextDe_CH = thisTextDe_CH;
		this.thisTextFr_CH = thisTextFr_CH;
		
		localeChanged();
		
		this.setToolTipText(thisTextUs_EN + " | " + thisTextDe_CH + " | " + thisTextFr_CH);
		
		this.setFont(new Font("Arial", Font.PLAIN, this.getFont().getSize() * 3));
		
		Localization.addInstance((LocaleListener) this);
	}
	
	/**
	 * when the locale changed the text will alter to the specific language
	 */
	@Override
	public void localeChanged() {
		switch (Localization.getLocaleLanguage().toLowerCase()) {
			case "us_en":
				this.setText(this.thisTextUs_EN);
				break;
			case "fr_ch":
				this.setText(this.thisTextFr_CH);
				break;
			case "de_ch":
				this.setText(this.thisTextDe_CH);
				break;
			default:
				this.setText("No Value");
				break;
		}
	}
}
