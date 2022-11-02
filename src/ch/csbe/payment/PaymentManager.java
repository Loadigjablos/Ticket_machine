package ch.csbe.payment;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The payment manager is responsible for communicating between the ticket software and the physical payment machine.
 */
public class PaymentManager {

	/**
	 * The singleton instance of this class.
	 */
	private static PaymentManager instance;
	
	/**
	 * The payment window instance.
	 */
	private PaymentWindow paymentWindow;
	
	/**
	 * The amount that the software requested.
	 */
	private float requestedAmount;
	
	/**
	 * @return The payment manager instance.
	 */
	public static PaymentManager getInstance() {
		if (instance == null) {
			instance = new PaymentManager();
		}
		
		return instance;
	}
	
	/**
	 * Initializes the payment manager instance.
	 */
	private PaymentManager() {
		this.paymentWindow = new PaymentWindow();
	}
	
	/**
	 * Adds a new payment listener.
	 * @param listener The listener to add.
	 */
	public void addPaymentListener(PaymentListener listener) {
		this.paymentWindow.paymentListeners.add(listener);
	}
	
	/**
	 * Removes the given payment listener.
	 * @param listener The listener to remove.
	 */
	public void removePaymentListener(PaymentListener listener) {
		this.paymentWindow.paymentListeners.remove(listener);
	}
	
	/**
	 * Requests the user to pay the given amount using cash or card. 
	 * @param amount The amount to request.
	 */
	public void requestPayment(float amount) {
		this.requestedAmount = amount;
		
		this.paymentWindow.paymentRequestLabel.setText("The software has requested CHF " + String.format("%.02f", amount) + ".");
		
		//Enable the payment buttons.
		for (Entry<Float, JButton> pair : this.paymentWindow.amountButtons.entrySet()) {
			pair.getValue().setEnabled(true);
		}
		this.paymentWindow.cardPaymentButton.setEnabled(true);
	}
	
	/**
	 * Marks the payment as complete. Must be called after the user has paid the full amount or if the order was canceled.
	 * You must return the already paid amount to the user if the order was canceled!
	 * This method will do nothing if there is no open payment.
	 * @see returnMoney
	 */
	public void closePayment() {
		//Enable the payment buttons.
		for (Entry<Float, JButton> pair : this.paymentWindow.amountButtons.entrySet()) {
			pair.getValue().setEnabled(false);
		}
		this.paymentWindow.cardPaymentButton.setEnabled(false);
		
		this.paymentWindow.paymentRequestLabel.setText("Waiting for the software to make a payment request ...");
		//this.paymentWindow.statusLabel.setText("");
		
		this.requestedAmount = 0;
	}
	
	/**
	 * Returns the given amount of money to the user.
	 * @param change The change to give.
	 */
	public void returnMoney(float change) {
		this.paymentWindow.statusLabel.setText("The machine returned CHF " + String.format("%.02f", change));
	}
	
	/**
	 * Forwards the paid amount to the software.
	 * If 0 is passed as parameter, the requested amount is used (i.e. full card payment).
	 * @param paidAmount The amount that was entered into the physical payment machine.
	 */
	void makePayment(float paidAmount) {
		//If the amount is 0, it is a full card payment.
		if (paidAmount == 0) {
			paidAmount = this.requestedAmount;
		}
		
		//Notify the listeners.
		for (PaymentListener listener : this.paymentWindow.paymentListeners) {
			listener.paymentReceived(paidAmount);
		}
	}
	
}

/**
 * The payment window provides a virtual test window to simulate money input into the payment machine. 
 */
class PaymentWindow extends JFrame implements ActionListener {

	/**
	 * Serial ID.
	 */
	private static final long serialVersionUID = 4286661855335239773L;
	
	/**
	 * The list of payment listeners.
	 */
	ArrayList<PaymentListener> paymentListeners = new ArrayList<PaymentListener>();
	
	/**
	 * The label that displays the requested amount.
	 */
	JLabel paymentRequestLabel;
	
	/**
	 * The button to pay the exact amount by card.
	 */
	JButton cardPaymentButton;
	
	/**
	 * A label that displays the most recent action.
	 */
	JLabel statusLabel;
	
	HashMap<Float, JButton> amountButtons = new HashMap<Float, JButton>();
	
	PaymentWindow() {
		this.setTitle("Wallet Simulator 2022");
		this.setSize(550, 600);
		
		JLabel titleLabel = new JLabel("Wallet Simulator 2022");
		titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		titleLabel.setFont(titleLabel.getFont().deriveFont(24f));
		this.add(titleLabel, BorderLayout.NORTH);
		
		JPanel contentContainer = new JPanel();
		contentContainer.setLayout(new BoxLayout(contentContainer, BoxLayout.PAGE_AXIS));
		contentContainer.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		this.add(contentContainer, BorderLayout.CENTER);
		
		this.paymentRequestLabel = new JLabel("Waiting for the software to make a payment request ...");
		paymentRequestLabel.setFont(this.paymentRequestLabel.getFont().deriveFont(18f));
		contentContainer.add(this.paymentRequestLabel);
		
		//Create the amount buttons.
		Float[] amounts = new Float[] { 0.05f, 0.1f, 0.2f, 0.5f, 1f, 2f, 5f, 10f, 20f, 50f, 100f, 200f, 1000f };
		for (Float amount : amounts) {
			JButton amountButton = new JButton("Pay CHF " + String.format("%.02f", amount));
			amountButton.addActionListener(this);
			amountButton.setEnabled(false);
			contentContainer.add(amountButton);
			this.amountButtons.put(amount, amountButton);
		}
		
		//Card payment button.
		this.cardPaymentButton = new JButton("Pay the exact amount by card");
		this.cardPaymentButton.addActionListener(this);
		this.cardPaymentButton.setEnabled(false);
		contentContainer.add(this.cardPaymentButton);
		
		//Status label.
		this.statusLabel = new JLabel();
		contentContainer.add(this.statusLabel);
		
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(this.cardPaymentButton)) {
			this.statusLabel.setText("The user paid the full amount by card.");
			PaymentManager.getInstance().makePayment(0);
		}
		else {
			//Check if it was one of the amount buttons.
			for (Entry<Float, JButton> pair : this.amountButtons.entrySet()) {
				if (e.getSource().equals(pair.getValue())) {
					this.statusLabel.setText("The user paid CHF " + String.format("%.02f", pair.getKey()) + ".");
					PaymentManager.getInstance().makePayment(pair.getKey());
					break;
				}
			}
		}
	}
	
}