package ch.csbe.Menus;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;

import ch.csbe.defaults.Button;
import ch.csbe.defaults.Frame;
import ch.csbe.defaults.Label;
import ch.csbe.defaults.RadioButton;
import ch.csbe.statics.TicketValues;
/**
 * the user cann set the amount of tickets and if its reduced frame 
 * @author domin
 */
public class NumberAndHalftax extends Frame implements ActionListener {

	private static final long serialVersionUID = -4146309942683698456L;
	
	private static NumberAndHalftax instance; // this instance
	
	/*
	 * tells the user how his ticket locks like
	 */
	private Label topText = new Label("you have a " + TicketValues.type + " Ticket from " + TicketValues.from + " to " + TicketValues.to + " in the " + TicketValues.grade + ". class",
									"Sie haben ein " + TicketValues.type + " Billet von " + TicketValues.from + " zu " + TicketValues.to + " in der " + TicketValues.grade + ". klasse",
									"tu as un " + TicketValues.type + " Billet à partir de " + TicketValues.from + " à " + TicketValues.to + " dans le " + TicketValues.grade + ". classer");

	private Button conntinue = new Button("Conntinue", "Weiter", "Continuer", 1);  // goes to the next seting
	
	private JPanel containerAmount = new JPanel(); // holds the button + and - and descTextAmount, amount.
	
	/*
	 * informs the user how many tickets he wants
	 */
	private Label descriptionTextAmount = new Label("How many tickets do you want",
								   					"Wie Viele Ticket wollen sie",
								   					"Combien de billets voulez-vous");
	/*
	 * buttons that indicate how many tickets you have
	 */
	private Button more = new Button(" + ", " + ", " + ", 1);
	private Button less = new Button(" - ", " - ", " - ", 1);
	private Label amount = new Label("" + TicketValues.count);
	
	/*
	 * tells the user what that is
	 */
	private Label descriptionTextHalftax = new Label("Would you like it reduced?",
			 										 "Möchten sie es Ermässigt?",
													 "Souhaitez-vous qu'il soit réduit ?");
	
	private RadioButton halfFareTrue = new RadioButton("I want half-fare", "ich will halbtax", "Je veux le demi-tarif"); //option to want halfFare
	private RadioButton halfFareFalse = new RadioButton("I dont want half-fare", "Ich will nicht halbtax", "Je ne veux pas d'abonnement demi-tarif"); //option to not want halfFare
	
	private JPanel containerHalf = new JPanel(); // middle for the radio buttons
	
	private JPanel list = new JPanel(); // has a BorderLayout for the options
	
	private final ButtonGroup group = new ButtonGroup(); // radio button group
	
	/**
	 * The Singleton method
	 * @return this instance
	 */
	public static NumberAndHalftax getInstance() {
		if (instance == null) {
			instance = new NumberAndHalftax();
		}
		
		return instance;
	}
	/**
	 * adds buttons labels to a frame
	 */
	private NumberAndHalftax() {
		
		super.top.add(topText, BorderLayout.LINE_START);
		
		this.list.setLayout(new BorderLayout());
		
		this.containerAmount.add(descriptionTextAmount, BorderLayout.PAGE_START);
		this.containerAmount.add(less, BorderLayout.LINE_START);
		this.less.addActionListener(this);
		this.containerAmount.add(amount, BorderLayout.CENTER);
		this.containerAmount.add(more, BorderLayout.LINE_END);
		this.more.addActionListener(this);
		this.list.add(containerAmount, BorderLayout.PAGE_START);
		
		TicketValues.reduced = false;
		this.containerHalf.add(descriptionTextHalftax, BorderLayout.PAGE_START);
		this.containerHalf.add(halfFareTrue);
		this.containerHalf.add(halfFareFalse);
		group.add(halfFareFalse);
		group.add(halfFareTrue);
		this.halfFareFalse.setSelected(true);
		this.list.add(containerHalf, BorderLayout.CENTER);
		
		this.list.add(conntinue, BorderLayout.PAGE_END);
		this.conntinue.addActionListener(this);
		
		this.add(list);
		
		super.back.setVisible(true);
		super.back.addActionListener(this);
		
		super.finischRender();
	}
	/**
	 * The text will be altered
	 */
	public void updateText() {
		this.topText.updateText("you have a " + TicketValues.type + " Ticket from " + TicketValues.from + " to " + TicketValues.to + " in the " + TicketValues.grade + ". class",
				"Sie haben ein " + TicketValues.type + " Billet von " + TicketValues.from + " zu " + TicketValues.to + " in der " + TicketValues.grade + ". klasse",
				"tu as un " + TicketValues.type + " Billet à partir de " + TicketValues.from + " à " + TicketValues.to + " dans le " + TicketValues.grade + ". classer");
	}
	/**
	 * the more and less button will increase or decrease the ticket amount
	 * the continue button checks the radio buttons then makes a new instance of Buying
	 * the back button will get you back
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		if (e.getSource().equals(back)) {
			this.setVisible(false);
			From.getInstance().setVisible(true);
		} else if (e.getSource().equals(more)) {
			TicketValues.count += 1;
			if (TicketValues.count > 100) {
				TicketValues.count = 100;
			}
		} else if (e.getSource().equals(less)) {
			TicketValues.count -= 1;
			if (TicketValues.count < 1) {
				TicketValues.count = 1;
			}
		} else if (e.getSource().equals(conntinue)) {
			TicketValues.reduced = this.halfFareTrue.isSelected();
			this.setVisible(false);
			Buying.getInstance().update();
			Buying.getInstance().setVisible(true);
		}
		amount.setText("" + TicketValues.count);
	}
}
