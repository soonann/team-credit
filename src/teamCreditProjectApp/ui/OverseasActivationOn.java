package teamCreditProjectApp.ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import javax.swing.SwingConstants;

import teamCreditProjectApp.dataAccess.CardDetailsDA;
import teamCreditProjectApp.entity.CardDetails;
import teamCreditProjectApp.entity.Login;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.Choice;
import java.awt.Color;
import javax.swing.JRadioButton;
import java.awt.Panel;

public class OverseasActivationOn extends MasterPanel {
	private JRadioButton permanentRadioButton;
	private GregorianCalendar enrolmentDate;
	private CardDetails cardDetails;
	private String cardNo;
	/**
	 * Create the panel.
	 */
	public OverseasActivationOn(JFrame	mf,Login l1,String cardNo) {
		super(mf,l1);
		this.cardNo = cardNo;
		setLayout(null);
		
		JButton activationButton = new JButton("");
		activationButton.setIcon(new ImageIcon(OverseasActivationOn.class.getResource("/teamCreditProjectApp/images/on.png")));
		activationButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		activationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

						JPanel contentPane = new OverseasActivation(mf,l1,cardNo);
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
		
		permanentRadioButton = new JRadioButton("permanent");
		permanentRadioButton.setSelected(true);
		permanentRadioButton.setFont(new Font("Arial", Font.PLAIN, 18));
		permanentRadioButton.setBounds(235, 308, 123, 23);
		add(permanentRadioButton);
		
		JRadioButton expireOnRadioButton = new JRadioButton("expire on:");
		expireOnRadioButton.setFont(new Font("Arial", Font.PLAIN, 18));
		expireOnRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JPanel contentPane = new OverseasActivationSetTime(myFrame,loggedIn,cardNo);
				myFrame.setContentPane(contentPane);
				myFrame.setVisible(true);
			}
		});
		expireOnRadioButton.setBounds(235, 334, 115, 23);
		add(expireOnRadioButton);
		
		JLabel cardNumberLabel = new JLabel("4628123465657788");
		cardNumberLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cardNumberLabel.setBounds(585, 166, 208, 23);
		add(cardNumberLabel);
		cardNumberLabel.setText(cardNo);
		
		JLabel cardNumberIsLabel = new JLabel("Card Number: ");
		cardNumberIsLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cardNumberIsLabel.setBounds(480, 166, 115, 23);
		add(cardNumberIsLabel);
		
		JButton submitButton = new JButton("");
		submitButton.setIcon(new ImageIcon(OverseasActivationOn.class.getResource("/teamCreditProjectApp/images/submit2 icon.png")));
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(permanentRadioButton.isSelected()){
					GregorianCalendar today = new GregorianCalendar();
					enrolmentDate = today; //now enrolmentDate is set to current date
					int year = enrolmentDate.get(GregorianCalendar.YEAR); //this will give you the year from enrolmentDate
					int month = enrolmentDate.get(GregorianCalendar.MONTH)+1; //this will give you the month from enrolmentDate
					int day = enrolmentDate.get(GregorianCalendar.DAY_OF_MONTH); //this will give you the day from enrolmentDate
					//Please take note. For month January, int month = enrolmentDate.get(GregorianCalendar.MONTH); will return you 0 instead of 1
					//So you need to +1 to the month
					
					String monthString = "" ;
					String dayString = "";
					
					if(month <10){
						monthString = "0"+month;
					}
					else{
						monthString =  ""+month;
					}
					
					if(day < 10){
						dayString= "0" + day;
						
					}
					else{
						dayString = Integer.toString(day);
					}
					
					String todaysDate = dayString + "/" + monthString + "/" + year;
					
					
					//System.out.println("Enrolment Date: " + day + "/" + (month + 1) + "/" + year);
					
					cardDetails = new CardDetails(0,l1.getSingpassID(),cardNo, 1, todaysDate, " " , 1000,  0, todaysDate , " " , 12000 , 50 , "DBS");
					actionPerformedCreate();
				}
				
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
		submitButton.setFont(new Font("Arial", Font.PLAIN, 18));
		submitButton.setBounds(724, 484, 41, 39);
		add(submitButton);
		
		JLabel labelBackground = new JLabel("");
		labelBackground.setIcon(new ImageIcon(OverseasActivationSetTime.class.getResource("/teamCreditProjectApp/images/bgfoa.PNG")));
		labelBackground.setBounds(158, 183, 671, 401);
		add(labelBackground);

	}
}

