package ch.csbe.Menus;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

import ch.csbe.defaults.Label;
/**
 * just a screen with some text and a mouse listener
 * @author domin
 */
public class StartScreen extends JFrame implements MouseListener {

	private static final long serialVersionUID = -2057745681919622704L;

	private Label label = new Label("<html><div>Bildschirm Berühren<div/><div>Touch the Screen<div/><div>Toucher l'ècrans<div/><html/>"); // tells the user what to do
	
	private static StartScreen instance; // this instance
	
	/**
	 * The Singleton method
	 * @return this instance
	 */
	public static StartScreen getInstance() {
		if (instance == null) {
			instance = new StartScreen();
		}
		
		return instance;
	}
	/**
	 * creates a full window with some text on it
	 */
	private StartScreen() {
		this.add(label);
		
		this.setBackground(new Color(0xFFF0ED));

		this.addMouseListener(this);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    this.setBounds(0,0,screenSize.width, screenSize.height);
	    
	    this.setResizable(false);
	    
	    this.setUndecorated(true);
	    
		this.revalidate();
		
		this.setVisible(true);
	}
	/**
	 * if the user presses on the screen it will open the next window
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		this.setVisible(false);
		ChoseType.getInstance().setVisible(true);
	}

	/*
	 * Unused Methods
	 */
	
	@Override
	public void mousePressed(MouseEvent e) { }

	@Override
	public void mouseReleased(MouseEvent e) { }

	@Override
	public void mouseEntered(MouseEvent e) { }

	@Override
	public void mouseExited(MouseEvent e) { }
}
