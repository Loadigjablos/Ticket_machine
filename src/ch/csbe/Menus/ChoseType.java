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
 * like the name says you will be choosing the ticket type in this frame.
 * @author domin
 */
public class ChoseType extends Frame implements ActionListener {

	private static final long serialVersionUID = 3616706532120227161L;
	
	private static ChoseType instance; // this instance
	
	private Button singel = new Button("SingelTicket", "Einzelbillet", "Un seul billet", 1);  // the user can set his ticket to single
	//describes the offer of single
	private Label singelDescription = new Label("<html><p>You can drive once <p><br><p/>to your desired location<p/><html/>",
												"<html><p>Sie können einmal <p><br><p/>zu ihrem gewünschten ort fahren<p/><html>", 
												"<html><p>Vous pouvez conduire une <p><br><p/>fois à l'endroit souhaité<p/><html/>");
	private JPanel singelRow = new JPanel();// a flow layout for button and label
	
	private Button returnn = new Button("ReturnTicket", "RetourBillet", "Billet de retour", 1);  // the user can set his ticket to return
	//describes the offer of return
	private Label returnDescription = new Label("<html><p>You can drive to your desired location and back.<p><br><p/> To go back you have to stamp it<p/><html/>", 
												"<html><p>Sie können zu ihrem gewünschten ort fahren und zurück.<p><br><p/> Um zurück zu fahren müssen sie es abstempeln<p/><html/>", 
												"<html><p>Vous pouvez conduire jusqu'à l'endroit de votre choix et revenir.<p><br><p/> Pour revenir en arrière, vous devez le tamponner<p/><html/>");
	private JPanel returnRow = new JPanel();// a flow layout for button and label
	
	private Button multi = new Button("MultiTicket", "MehrfahrBillet", "Billet multiple", 1);  // the user can set his ticket to multi
	//describes the offer of multi
	private Label multiDescription = new Label("<html><p>You can drive five times.<p><br><p/> must be stamped each time per trip<p/><html/>", 
											   "<html><p>Sie können fünf mal fahren.<p><br><p/> muss Pro fahrt jedes mall abgestempelt werden<p/><html/>", 
											   "<html><p>Vous pouvez conduire cinq fois.<p><br><p/> doit être estampillé à chaque fois par voyage<p/><html/>");
	private JPanel multiRow = new JPanel();// a flow layout for button and label
	
	private JPanel container = new JPanel();// a box layout for the classRows
	
	/**
	 * The Singleton method
	 * @return this instance
	 */
	public static ChoseType getInstance() {
		if (instance == null) {
			instance = new ChoseType();
		}
		
		return instance;
	}
	/**
	 * adds buttons to a Frame
	 */
	private ChoseType() {
		
		this.container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		
		this.singelRow.add(singel);
		this.singelRow.add(singelDescription);
		this.singel.addActionListener(this);
		
		this.returnRow.add(returnn);
		this.returnRow.add(returnDescription);
		this.returnn.addActionListener(this);
		
		this.multiRow.add(multi);
		this.multiRow.add(multiDescription);
		this.multi.addActionListener(this);
		
		this.container.add(singelRow);
		this.container.add(returnRow);
		this.container.add(multiRow);
		
		this.add(container, BorderLayout.CENTER);
		
		super.finischRender();
	}
	/**
	 * the button will make the Ticket type, then goes to the next screen.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		if (e.getSource().equals(singel)) {
			TicketValues.type = "singel";
			
			this.setVisible(false);
			ChoseClass.getInstance().setVisible(true);
		} else if (e.getSource().equals(returnn)) {
			TicketValues.type = "return";
			
			this.setVisible(false);
			ChoseClass.getInstance().setVisible(true);
		} else if (e.getSource().equals(multi)) {
			TicketValues.type = "multi";

			this.setVisible(false);
			ChoseClass.getInstance().setVisible(true);
		}
	}
}
