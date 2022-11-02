package ch.csbe.Menus;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ch.csbe.defaults.Button;
import ch.csbe.defaults.Frame;
import ch.csbe.defaults.Label;
import ch.csbe.statics.Main;
import ch.csbe.statics.TicketValues;
/**
 * the user can chose their location and destination.
 * @author dominic
 */
public class From extends Frame implements ActionListener {

	private static final long serialVersionUID = 7871887546523194416L;
	
	private static From instance; // this instance
	
	private JList<String> from; // a list where you can chose where you are
	private JList<String> to; // a list where you can chose where you want to go
	
	private Label fromtxt = new Label("From:", "Von:", "De:");//indicates where you are
	private Label totxt = new Label("To:", "Zu:", "à:");//indicates where you wana go
	
	private JPanel fromContainer = new JPanel(); // holds a list and text
	private JPanel toContainer = new JPanel(); // holds a list and text
	
	private JPanel container = new JPanel(); // for the img and continue button
	
	private Button Conntinue = new Button("Conntinue", "Weiter", "Continuer", 1); // goes to the next seting
	
	private BufferedImage img; // The Map
	
	private String fromTo[] = { //every Location there is
			"schwarzenburg", "riedstättKalchstten (Halt auf Verlangen)", "plaffeien", "passelb", "chrachen",
			"parmathaux", "laRocheRoulin", "laRoche (Halt auf Verlangen)", "hauteville (Halt auf Verlangen)",
			"corbieres", "villarvolard (Halt auf Verlangen)", "botterens (Halt auf Verlangen)", "brocFabrique",
			"brocVillage"
			};
	
	/**
	 * The Singleton method
	 * @return this instance
	 */
	public static From getInstance() {
		if (instance == null) {
			instance = new From();
		}
		
		return instance;
	}
	/**
	 * adds buttons lists labels images to a frame
	 */
	private From() {
		
		this.container.setLayout(new BorderLayout());
		
		this.fromContainer.add(fromtxt, BorderLayout.PAGE_START);
		this.from = new JList<String>(fromTo);
		this.from.setFont(this.from.getFont().deriveFont(25f));
		this.fromContainer.add(from, BorderLayout.CENTER);
		
		/*
		 * sets the list index to the current location
		 */
		this.from.setSelectedIndex(1);
		for (int i = 0; i < fromTo.length; i++) {
			String check[] = fromTo[i].split(" ");
			if (Main.getStartPosition().equals(check[0])) {
				this.from.setSelectedIndex(i);
			}
		}
		
		this.toContainer.add(totxt, BorderLayout.PAGE_START);
		this.to = new JList<String>(fromTo);
		this.to.setFont(this.to.getFont().deriveFont(25f));
		this.to.setSelectedIndex(1);
		this.toContainer.add(to, BorderLayout.CENTER);
		
		this.add(fromContainer, BorderLayout.LINE_START);
		this.add(toContainer, BorderLayout.LINE_END);
		
		/*
		 * either the default image is being put on the frame or one with a marker
		 */
		boolean alternative = false;
		JLabel imageLabel = new JLabel("", JLabel.CENTER);
		this.add(imageLabel, BorderLayout.CENTER);
		try {
			img = ImageIO.read(getClass().getClassLoader().getResourceAsStream("map/locations/" + Main.getStartPosition() + ".PNG"));
			imageLabel.setIcon(new ImageIcon(img));
			this.container.add(imageLabel, BorderLayout.CENTER);
		} catch (IOException e) {
			alternative = true;
		}
		if (alternative)  {
			try {
				img = ImageIO.read(getClass().getClassLoader().getResourceAsStream("map/general-area.PNG"));
				imageLabel.setIcon(new ImageIcon(img));
				this.container.add(imageLabel, BorderLayout.CENTER);
			} catch (IOException x) {}
		}
		
		this.container.add(Conntinue, BorderLayout.PAGE_END);
		this.Conntinue.addActionListener(this);
		
		this.add(container, BorderLayout.CENTER);
		
		super.back.setVisible(true);
		super.back.addActionListener(this);
		
		super.finischRender();
	}
	/**
	 * the button back makes a new instance of choseClass
	 * the button Continue will make a new instance of NumberAndHalftax and it will alter Ticket Information
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		if (e.getSource().equals(back)) {
			
			this.setVisible(false);
			ChoseClass.getInstance().setVisible(true);
		} else if (e.getSource().equals(Conntinue)) {
			if (this.from.getSelectedIndex() == this.to.getSelectedIndex()) {
				JOptionPane.showMessageDialog(this, " You cant select the same from and to!\n sie können nicht die selbe von und zu wählen!\n vous ne pouvez pas choisir le même de et vers !", "ERROR", JOptionPane.ERROR_MESSAGE);
			} else {
				try {
					String checkfrom[] = fromTo[this.from.getSelectedIndex()].split(" ");
					TicketValues.from = checkfrom[0];
					
					String checkto[] = fromTo[this.to.getSelectedIndex()].split(" ");
					TicketValues.to = checkto[0];
					
					TicketValues.stations = this.from.getSelectedIndex() - this.to.getSelectedIndex();
					if (TicketValues.stations < 0) {
						TicketValues.stations *= -1;
					}
					
					this.setVisible(false);
					NumberAndHalftax.getInstance().updateText();
					NumberAndHalftax.getInstance().setVisible(true);
				} catch (Exception x) { }
			}
		}
	}
}
