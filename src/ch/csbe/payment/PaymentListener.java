package ch.csbe.payment;

/**
 * A payment listener is notified every time a partial or full payment has been made using the physical payment machine.
 */
public interface PaymentListener {

	/**
	 * This method is called when a partial or full payment was made using the physical payment machine.
	 * @param amount The amount that has been paid.
	 */
	public void paymentReceived(float amount);
	
}
