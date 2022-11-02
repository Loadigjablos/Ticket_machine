package ch.csbe.Menus;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import ch.csbe.defaults.Button;
import ch.csbe.defaults.Frame;
import ch.csbe.defaults.Label;
import ch.csbe.statics.TicketValues;
/**
 * here you cann chose which class your ticket has
 * @author domin
 */
public class ChoseClass extends Frame implements ActionListener {

	private static final long serialVersionUID = 6912616604598438687L;
	
	private static ChoseClass instance; // this instance
	
	private Button firstClass = new Button("1. Class", "1. Klasse", "1. Classe", 1); // the user can set his ticket to first class
	//describes the offer of first class
	private Label firstClassDescription = new Label("<html><p>You get a comfortable place<p/><br><p>for double the money<p/><html/>", 
			 "<html><p>Sie bekommen einen Gemütlichen platz<p/><br><p>für das doppelte vom geld<p/><html/>",
			 "<html><p>Vous obtenez un endroit confortable<p/><br><p>pour le double de l'argent<p/><html/>");
	private JPanel firstClassRow = new JPanel();// a flow layout for button and label
	
	private Button secondClass = new Button("2. Class", "2. Klasse", "2. Classe", 1); // the user can set his ticket to second class
	//describes the offer of first class
	private Label secondClassDescription = new Label("<html><p>You get a simple seat<p/><br><p>For little money<p/><html/>", 
			"<html><p>Sie beckommen einen Simplen platz<p/><br><p>Für wenig geld<p/><html/>",
			"<html><p>Vous obtenez un siège simple<p/><br><p>Pour peu d'argent<p/><html/>");
	private JPanel secondClassRow = new JPanel();// a flow layout for button and label
	
	private JPanel container = new JPanel(); // a box layout for the classRows
	
	/**
	 * The Singleton method
	 * @return this instance
	 */
	public static ChoseClass getInstance() {
		if (instance == null) {
			instance = new ChoseClass();
		}
		
		return instance;
	}
	/**
	 * adds buttons to the Frame
	 */
	private ChoseClass() {
		
		this.container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		
		this.firstClassRow.add(firstClass);
		this.firstClassRow.add(firstClassDescription);
		this.firstClass.addActionListener(this);
		
		this.secondClassRow.add(secondClass);
		this.secondClassRow.add(secondClassDescription);
		this.secondClass.addActionListener(this);
		
		this.container.add(firstClassRow);
		this.container.add(secondClassRow);
		
		this.add(container, BorderLayout.CENTER);
		
		super.back.setVisible(true);
		super.back.addActionListener(this);
		
		super.finischRender();
	}
	/**
	 * when a button is clicked this method will be called. and a new From instance will be created
	 * the buttons for the classes will determine which class the ticket has.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		if (e.getSource().equals(firstClass)) {
			
			TicketValues.grade = 1;
			this.setVisible(false);
			From.getInstance().setVisible(true);
		} else if (e.getSource().equals(secondClass)) {
			
			TicketValues.grade = 2;
			this.setVisible(false);
			From.getInstance().setVisible(true);
		} else if (e.getSource().equals(back)) {
			
			this.setVisible(false);
			ChoseType.getInstance().setVisible(true);
		}
	}
}
