package teamCreditProjectApp.ui;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSlider;
import javax.swing.JRadioButton;
import java.awt.Choice;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import teamCreditProjectApp.dataAccess.CardDetailsDA;
import teamCreditProjectApp.entity.CardDetails;
import teamCreditProjectApp.entity.Login;

public class OverseasActivation extends MasterPanel {
	private JTextField creditLimitTextField;
	private CardDetails cardDetails;
	private String cardNo;

		/**
		 * Create the panel.
		 */
	public OverseasActivation(JFrame mf,Login l1,String cardNo) {
		super(mf,l1);
		this.cardNo = cardNo;
		setLayout(null);
		
		JButton activationButton = new JButton("");
		activationButton.setIcon(new ImageIcon(OverseasActivation.class.getResource("/teamCreditProjectApp/images/off.png")));
		activationButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		activationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

						JPanel contentPane = new OverseasActivationOn(mf,l1,cardNo);
						mf.setContentPane(contentPane);
						mf.setVisible(true);

			}
		
		});
		
		JLabel overseasActivationLabel = new JLabel("Credit Card Overseas Activation");
		overseasActivationLabel.setBounds(235, 256, 423, 45);
		overseasActivationLabel.setFont(new Font("Arial", Font.PLAIN, 30));
		add(overseasActivationLabel);
		activationButton.setBounds(688, 256, 77, 45);
		add(activationButton);
		
		JLabel cardNumberLabel = new JLabel("4628123465657788");
		cardNumberLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cardNumberLabel.setBounds(585, 166, 211, 23);
		add(cardNumberLabel);
		cardNumberLabel.setText(cardNo);
		
		JLabel cardNumberIsLabel = new JLabel("Card Number: ");
		cardNumberIsLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cardNumberIsLabel.setBounds(480, 166, 115, 23);
		add(cardNumberIsLabel);
		
		JButton submitButton = new JButton("");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardDetails = new CardDetails("4628-1234-6565-7788", 0, "", "");
				actionPerformedCreate();			
			}
			public void actionPerformedCreate(){
				// insert to database and check return value
				if (CardDetailsDA.createCardDetails(cardDetails)) {
					JOptionPane.showMessageDialog(myFrame,
						"Record created successfully", "Alert",
						JOptionPane.INFORMATION_MESSAGE);
					// reset text field for next record.
					
				} 
				else
					JOptionPane.showMessageDialog(myFrame,
							"Database Error. Record not created.", "Alert",
								JOptionPane.ERROR_MESSAGE);
				}
		});
		submitButton.setIcon(new ImageIcon(OverseasActivation.class.getResource("/teamCreditProjectApp/images/submit2 icon.png")));
		submitButton.setFont(new Font("Arial", Font.PLAIN, 18));
		submitButton.setBounds(724, 484, 41, 39);
		add(submitButton);
		
		JLabel labelBackground = new JLabel("");
		labelBackground.setIcon(new ImageIcon(OverseasActivationSetTime.class.getResource("/teamCreditProjectApp/images/bgfoa.PNG")));
		labelBackground.setBounds(158, 183, 671, 401);
		add(labelBackground);

	}
}
