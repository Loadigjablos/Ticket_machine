package ch.csbe.defaults;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

import ch.csbe.l10n.LocaleListener;
import ch.csbe.l10n.Localization;
/**
 * is a Label that can alter its content
 * @author domin
 */
public class Label extends JLabel implements LocaleListener {

	private static final long serialVersionUID = -333221113632957318L;
	
	private String thisTextUs_EN; // the Label text in English
	private String thisTextDe_CH; // the Label text in German
	private String thisTextFr_CH;// the Label text in French
	/**
	 * alters the values thisTextUs_EN, thisTextDe_CH and thisTextFr_CH then sets the text
	 * @param thisTextUs_EN text in English
	 * @param thisTextDe_CH text in German
	 * @param thisTextFr_CH text in French
	 */
	public Label(String thisTextUs_EN, String thisTextDe_CH, String thisTextFr_CH) {
		
		this.thisTextUs_EN = thisTextUs_EN;
		this.thisTextDe_CH = thisTextDe_CH;
		this.thisTextFr_CH = thisTextFr_CH;
		
		localeChanged();
		
		this.setFont(new Font("Arial", Font.PLAIN, this.getFont().getSize() * 3));
		
		this.setForeground(Color.BLACK);
		
		this.setToolTipText(thisTextUs_EN + " | " + thisTextDe_CH + " | " + thisTextFr_CH);
		
		/*
		 * if the locale Language changes, a Array with buttons and labels needs to 
		 * will be added just like here
		 */
		Localization.addInstance((LocaleListener) this);
	}
	/**
	 * for simple text fields with no Translation
	 * @param text
	 */
	public Label(String text) {
		this.setFont(new Font("Arial", Font.PLAIN, this.getFont().getSize() * 10));
		this.setText(text);
	}
	/**
	 * alters the values thisTextUs_EN, thisTextDe_CH and thisTextFr_CH then sets the text
	 * @param thisTextUs_EN text in English
	 * @param thisTextDe_CH text in German
	 * @param thisTextFr_CH text in French
	 */
	public void updateText(String thisTextUs_EN, String thisTextDe_CH, String thisTextFr_CH) {
		this.thisTextUs_EN = thisTextUs_EN;
		this.thisTextDe_CH = thisTextDe_CH;
		this.thisTextFr_CH = thisTextFr_CH;
		localeChanged();
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
