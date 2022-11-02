package ch.csbe.defaults;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ch.csbe.l10n.Localization;
/**
 * This Class is the default for Most GUIS. It has buttons to change the Locale Language.
 * @author domin
 */
public class Frame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 3440194156845341345L;

	private Button ch_DEButton = new Button("DEUTSCH", "DEUTSCH", "DEUTSCH", 2); // button to change the Language to German
	
	private Button ch_FRButton = new Button("FRANÁAIS".toUpperCase(), "FRANÁAIS".toUpperCase(), "FRANÁAIS".toUpperCase(), 2); // button to change the Language to French
	
	private Button us_ENButton = new Button("ENGLISCH", "ENGLISCH", "ENGLISCH", 2); // button to change the Language to English
	
	protected Button back = new Button("<- Back ", "<- Zur¸ck ", "<- Retour ", 2); // gets you back to other windows
	
	protected JPanel bottom = new JPanel(); // Container at the bottom
	
	protected JPanel top = new JPanel(); // Container at the top
	
	private BufferedImage img; // The Logo
	/**
	 * adds the buttons and Panels to the frame, colors them and adds Action listener
	 */
	public Frame() {
		this.setBackground(new Color(0xFFF0ED));
		
		this.bottom.add(back);
		this.back.setVisible(false);
		
		this.bottom.add(ch_DEButton);
		this.ch_DEButton.addActionListener(this);
		
		this.bottom.add(ch_FRButton);
		this.ch_FRButton.addActionListener(this);
		
		this.bottom.add(us_ENButton);
		this.us_ENButton.addActionListener(this);
		
		this.bottom.setBackground(new Color(0x7F7F7F));
		this.top.setBackground(new Color(0x7F7F7F));
		
		this.add(bottom, BorderLayout.PAGE_END);
		
		JLabel imageLabel = new JLabel("", JLabel.CENTER);
		try {
			img = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Logo-small.png"));
			imageLabel.setIcon(new ImageIcon(img));
			this.top.add(imageLabel, BorderLayout.LINE_END);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.add(top, BorderLayout.PAGE_START);
	}
	/**
	 * when you finished with adding stuff to the Frame, this method needs to be
	 * Activated. So that the Window can fully function.
	 * this fills the screen with the window.
	 */
	protected void finischRender() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    this.setBounds(0, 0, screenSize.width, screenSize.height);
	    this.setResizable(false);
	    this.setUndecorated(true);
		this.revalidate();
		this.setVisible(true);
	}
	/**
	 * each button changes the Locale Language
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(ch_DEButton)) {
			Localization.setLocaleLanguage("de_CH");
		} else if (e.getSource().equals(ch_FRButton)) {
			Localization.setLocaleLanguage("fr_CH");
		} else if (e.getSource().equals(us_ENButton)) {
			Localization.setLocaleLanguage("us_EN");
		}
	}
}
