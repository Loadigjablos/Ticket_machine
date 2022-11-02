package ch.csbe.defaults;

import ch.csbe.l10n.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JButton;
/**
 * This describes the style and interaction with the button
 * @author domin
 */
public class Button extends JButton implements LocaleListener {

	private static final long serialVersionUID = 916446702852540128L;
	
	private String thisTextUs_EN; // the button text in English
	private String thisTextDe_CH; // the button text in German
	private String thisTextFr_CH; // the button text in French
	/** 
	 * @param thisTextUs_EN text in English
	 * @param thisTextDe_CH text in German
	 * @param thisTextFr_CH text in French
	 * @param type How the Button Will Look (1, 2)
	 */
	public Button(String thisTextUs_EN, String thisTextDe_CH, String thisTextFr_CH, int type) {
		
		this.thisTextUs_EN = thisTextUs_EN;
		this.thisTextDe_CH = thisTextDe_CH;
		this.thisTextFr_CH = thisTextFr_CH;
		
		localeChanged();
		
		this.setContentAreaFilled(false);
		this.setBorder(null);
		
		if (type == 1) {
			this.setBackground(new Color(0xE50010));
			this.setForeground(new Color(0x000000));
			this.setFont(new Font("Arial", Font.PLAIN, this.getFont().getSize() * 6));
		} else if (type == 2) {
			this.setBackground(new Color(0xFFED00));
			this.setForeground(new Color(0x000000));
			this.setFont(new Font("Arial", Font.PLAIN, this.getFont().getSize() * 7));
		}
		
		this.setToolTipText(thisTextUs_EN + " | " + thisTextDe_CH + " | " + thisTextFr_CH);
		
		/*
		 * if the locale Language changes, a Array with buttons and labels needs to 
		 * will be added just like here
		 */
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
	/**
	 * this paints a rectangle shape for the button
	 */
	@Override
	public void paint(Graphics g) {
		g.setColor(this.getBackground());
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		super.paint(g);
	}
}
