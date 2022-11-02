package ch.csbe.Menus;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import ch.csbe.defaults.Button;
import ch.csbe.defaults.Frame;
import ch.csbe.defaults.Label;
import ch.csbe.l10n.Localization;
import ch.csbe.payment.PaymentListener;
import ch.csbe.payment.PaymentManager;
import ch.csbe.statics.Main;
import ch.csbe.statics.TicketValues;
/**
 * if every ticket setting is done then you can buy it here
 * @author dominic
 */
public class Buying extends Frame implements ActionListener, PaymentListener {

	private static final long serialVersionUID = -6854315972215844014L;
	
	private static Buying instance; // this instance
	
	/*
	 * tells the user what he has set
	 */
	private Label topText = new Label("you have " + TicketValues.count + " Ticket/s " + TicketValues.type + " from " + TicketValues.from + " to " 
										+ TicketValues.to + " in the " + TicketValues.grade + ". class. ",
									  "Sie haben " + TicketValues.count + " Billet/s " + TicketValues.type + " von " + TicketValues.from + " zu " 
										+ TicketValues.to + " in der " + TicketValues.grade + ". klasse",
									  "tu as "  + TicketValues.count + " Billet/s " + TicketValues.type + " à partir de " + TicketValues.from + " à "
										+ TicketValues.to + " dans le " + TicketValues.grade + ". classe");
	 // in the Frame will be shown how much to pay
	private Label payment = new Label("That would be " + String.format("%.02f", (float)this.cost) + " CHF",
			 						  "Das Wäre dann " + String.format("%.02f", (float)this.cost) + " CHF",
			 						  "Ce serait alors" + String.format("%.02f", (float)this.cost) + " CHF");
	
	private Label moneyGivenText; // this shows how much money the user put inside
	
	/*
	 * to cancel a Transaction
	 */
	private Button cancel = new Button("<html><div>Return Money<div/><div>Cancel<div/><html/>",
									   "<html><div>Geld Zurück<div/><div>Abbrechen<div/><html/>",
									   "<html><div>remboursement<div/><div>annuler<div/><html/>",
									   2);
	
	private JPanel middle = new JPanel(); // in the center of the frame
	
	private double cost = 0.00; // the exact price of the Tick or all of the tickets
	
	private double moneyGiven = 0.00; // the money the user puts in the machine
	
	/**
	 * The Singleton method
	 * @return this instance
	 */
	public static Buying getInstance() {
		if (instance == null) {
			instance = new Buying();
		}
		
		return instance;
	}
	/**
	 * Creates a Frame that shows how much to pay. You can also cancel the Transaction.
	 */
	private Buying() {	
		this.middle.add(cancel);
		this.cancel.addActionListener(this);
		super.top.add(topText, BorderLayout.LINE_START);
		
		this.moneyGivenText = new Label(" " + (float)this.moneyGiven + "  ");
		
		this.middle.add(payment);
		this.middle.add(moneyGivenText);
		
		this.middle.setBackground(Color.WHITE);
		this.add(middle, BorderLayout.CENTER);
		
		PaymentManager.getInstance().addPaymentListener(this);
		
		super.back.setVisible(true);
		super.back.addActionListener(this);
		
		super.finischRender();
	}
	/**
	 * Prints every ticket to the console
	 */
	private void printTickets() {
		for (int i = 0; i < TicketValues.count; i++) {
			System.out.printf("ticketType=%s\nfrom=%s\nto=%s\nclass=%s\nreduced=%s\n\n", TicketValues.type, TicketValues.from, TicketValues.to, TicketValues.grade, TicketValues.reduced);
		}
	}
	/**
	 * this changes texts calculates the cost
	 */
	public void update() {
		cost = 0;
		/*
		 * Calculating the Ticket cost
		 */
		cost += 2.90;
		if (TicketValues.type.equals("return")) {
			cost *= 2;
		} else if (TicketValues.type.equals("multi")) {
			cost *= 5;
		}
		for (int i = 3; i < TicketValues.stations; i++) {
			cost += 0.9;
		}
		if (TicketValues.grade == 1) {
			cost *= 2;
		}
		if (TicketValues.reduced) {
			cost /= 2;
		}
		double oneTicket = cost;
		for (int i = 1; i < TicketValues.count; i++) {
			cost += oneTicket;
		}
		
		this.payment.updateText("That would be " + String.format("%.02f", (float)this.cost) + " CHF",
				 "Das Wäre dann " + String.format("%.02f", (float)this.cost) + " CHF",
				 "Ce serait alors" + String.format("%.02f", (float)this.cost) + " CHF");
		
		this.topText.updateText("you have " + TicketValues.count + " Ticket/s " + TicketValues.type + " from " + TicketValues.from + " to " 
				+ TicketValues.to + " in the " + TicketValues.grade + ". class. ",
			  "Sie haben " + TicketValues.count + " Billet/s " + TicketValues.type + " von " + TicketValues.from + " zu " 
				+ TicketValues.to + " in der " + TicketValues.grade + ". klasse",
			  "tu as "  + TicketValues.count + " Billet/s " + TicketValues.type + " à partir de " + TicketValues.from + " à "
				+ TicketValues.to + " dans le " + TicketValues.grade + ". classe");
		
		PaymentManager.getInstance().requestPayment((float)this.cost);
	}
	/**
	 * if a button is clicked the this will be called
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		if (e.getSource().equals(back)) {
			this.setVisible(false);
			PaymentManager.getInstance().returnMoney((float) this.moneyGiven);
			moneyGiven = 0;
			PaymentManager.getInstance().closePayment();
			NumberAndHalftax.getInstance().updateText();
			NumberAndHalftax.getInstance().setVisible(true);
		} else if (e.getSource().equals(cancel)) {
			this.setVisible(false);
			PaymentManager.getInstance().returnMoney((float) this.moneyGiven);
			moneyGiven = 0;
			PaymentManager.getInstance().closePayment();
			TicketValues.setDefault();
			StartScreen.getInstance().setVisible(true);
		}
	}
	/**
	 * when money gets Received this method will be called
	 */
	@Override
	public void paymentReceived(float amount) {
		this.moneyGiven += amount;
		if (this.moneyGiven >= this.cost) {
			this.moneyGiven -= this.cost;
			PaymentManager.getInstance().returnMoney((float) this.moneyGiven);
			moneyGiven = 0;
			PaymentManager.getInstance().closePayment();
			this.setVisible(false);
			printTickets();
			TicketValues.setDefault();
			Localization.setLocaleLanguage(Main.getStartLocaleLanguage());
			StartScreen.getInstance().setVisible(true);
		}
		this.moneyGivenText.setText("   " + String.format("%.02f", (float)this.moneyGiven) + "   ");
	}
}
