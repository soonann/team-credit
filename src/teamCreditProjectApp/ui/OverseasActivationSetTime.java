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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.Choice;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Panel;
import javax.swing.SpringLayout;
import javax.swing.JSeparator;

public class OverseasActivationSetTime extends MasterPanel {
	private String cardNumber;
	private int activationStatus;
	private JTextField dateStartTextField;
	private JTextField dateEndTextField;
	private CardDetails cardDetails;
	private JButton submitButton;
	private String actions;
	private String activation;
	private GregorianCalendar enrolmentDate;
	private String cardNo;
	


	/**
	 * Create the panel.
	 */
	public OverseasActivationSetTime(JFrame	mf,Login l1,String cardNo) {
		super(mf,l1);
		this.cardNo = cardNo;
		setLayout(null);
		
		JButton activationButton = new JButton("");
		activationButton.setIcon(new ImageIcon(OverseasActivationSetTime.class.getResource("/teamCreditProjectApp/images/on.png")));
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
		
		JRadioButton permanentRadioButton = new JRadioButton("permanent");
		permanentRadioButton.setFont(new Font("Arial", Font.PLAIN, 18));
		permanentRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				JPanel contentPane = new OverseasActivationOn(mf,l1,cardNo);
				mf.setContentPane(contentPane);
				mf.setVisible(true);
			}
		});
		permanentRadioButton.setBounds(235, 308, 123, 23);
		add(permanentRadioButton);
		
		JRadioButton expireOnRadioButton = new JRadioButton("expire on:");
		expireOnRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		expireOnRadioButton.setSelected(true);
		expireOnRadioButton.setFont(new Font("Arial", Font.PLAIN, 18));
		expireOnRadioButton.setBounds(235, 334, 115, 23);
		add(expireOnRadioButton);
		
		JLabel startDateLabel = new JLabel("Start Date");
		startDateLabel.setBounds(314, 379, 98, 19);
		add(startDateLabel);
		startDateLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		
		JPanel startDate = new JPanel();
		startDate.setBounds(314, 398, 164, 23);
		add(startDate);
		startDate.setBackground(Color.WHITE);
		SpringLayout sl_startDate = new SpringLayout();
		startDate.setLayout(sl_startDate);
		
		dateStartTextField = new JTextField();
		sl_startDate.putConstraint(SpringLayout.NORTH, dateStartTextField, 0, SpringLayout.NORTH, startDate);
		sl_startDate.putConstraint(SpringLayout.WEST, dateStartTextField, 0, SpringLayout.WEST, startDate);
		sl_startDate.putConstraint(SpringLayout.SOUTH, dateStartTextField, 23, SpringLayout.NORTH, startDate);
		sl_startDate.putConstraint(SpringLayout.EAST, dateStartTextField, 165, SpringLayout.WEST, startDate);
		dateStartTextField.setColumns(10);
		startDate.add(dateStartTextField);
		
		GregorianCalendar today = new GregorianCalendar();

		int year = today.get(GregorianCalendar.YEAR); //this will give you the year from enrolmentDate
		int month =  today.get(GregorianCalendar.MONTH)+1; //this will give you the month from enrolmentDate
		int day =  today.get(GregorianCalendar.DAY_OF_MONTH); //this will give you the day from enrolmentDate
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
		dateStartTextField.setText(todaysDate);
		
		JLabel endDateLabel = new JLabel("End Date");
		endDateLabel.setBounds(530, 379, 92, 19);
		add(endDateLabel);
		endDateLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		
		JPanel endDate = new JPanel();
		endDate.setBounds(530, 398, 164, 23);
		add(endDate);
		endDate.setLayout(null);
		endDate.setBackground(Color.WHITE);
		
		dateEndTextField = new JTextField();
		dateEndTextField.setColumns(10);
		dateEndTextField.setBounds(0, 0, 165, 23);
		endDate.add(dateEndTextField);
		
		JButton submitButton = new JButton("");
		submitButton.setIcon(new ImageIcon(OverseasActivationSetTime.class.getResource("/teamCreditProjectApp/images/submit2 icon.png")));
		submitButton.setBounds(724, 484, 41, 39);
		add(submitButton);
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				try {
					actionPerformedOk();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			private boolean validateInput() {
				boolean result = false;
				String msg = "";
				int msgType = JOptionPane.ERROR_MESSAGE;
				// retrieve the user input from the text box/area provided
				String start = dateStartTextField.getText();
				String end = dateEndTextField.getText();

				if (start.length() != 10)
					msg += "Please enter date in DD/MM/YYYY format.\n";
				if (end.length() != 10)
					msg += "Please enter date in DD/MM/YYYY format.\n";
				if (msg.length() == 0)
					result = true;
				else
					JOptionPane.showMessageDialog(myFrame, msg, "Alert", msgType);
				return result;
			}

		private void actionPerformedOk() throws ParseException {
			if (validateInput()) {
				int year = today.get(GregorianCalendar.YEAR); //this will give you the year from enrolmentDate
				int month =  today.get(GregorianCalendar.MONTH)+1; //this will give you the month from enrolmentDate
				int day =  today.get(GregorianCalendar.DAY_OF_MONTH); //this will give you the day from enrolmentDate
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
				if(expireOnRadioButton.isSelected()){
					
					String startDate = dateStartTextField.getText();
					String endDate = dateEndTextField.getText();
					SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
					Date date1 = sdf.parse(startDate);
					Date date2 = sdf.parse(endDate);
					Date date3 = sdf.parse(todaysDate);
					if(date2.after(date1) && (date1.after(date3) || date1.equals(date3))){
						cardDetails = new CardDetails(0,l1.getSingpassID(),cardNo, 1, startDate, endDate , 1000,  0, todaysDate , " " , 12000 , 50 , "DBS");
					actionPerformedCreate();
					
					}		
					else{
						String msg = "Error!";
						JOptionPane.showMessageDialog(myFrame, msg);
					}
					}
			}
		}

		public void actionPerformedUpdate(){
			// Update record in database and check return value
			if (CardDetailsDA.updateCardDetails(cardDetails)) {
				JOptionPane.showMessageDialog(myFrame,
					"Record updated successfully", "Alert",
					JOptionPane.INFORMATION_MESSAGE);
				// reset text field for next record.
				dateStartTextField.setEditable(false);
				dateEndTextField.setEditable(false);
				submitButton.setEnabled(false);
			} 
			else {
				JOptionPane.showMessageDialog(myFrame,
						"Database Error. Record not updated.", "Alert",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		public void actionPerformedCreate(){
			// insert to database and check return value
			if (CardDetailsDA.createCardDetails(cardDetails)) {
				JOptionPane.showMessageDialog(myFrame,
					"Record created successfully", "Alert",
					JOptionPane.INFORMATION_MESSAGE);
				// reset text field for next record.
				dateStartTextField.setText("");
				dateEndTextField.setText("");

			} 
			else
				JOptionPane.showMessageDialog(myFrame,
						"Database Error. Record not created.", "Alert",
							JOptionPane.ERROR_MESSAGE);
			}
		});
			submitButton.setFont(new Font("Arial", Font.PLAIN, 18));
			
			JLabel cardNumberLabel = new JLabel("4628123465657788");
			cardNumberLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
			cardNumberLabel.setBounds(585, 166, 194, 23);
			add(cardNumberLabel);
			cardNumberLabel.setText(cardNo);
			
			JLabel cardNumberIsLabel = new JLabel("Card Number: ");
			cardNumberIsLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
			cardNumberIsLabel.setBounds(480, 166, 115, 23);
			add(cardNumberIsLabel);
			
			JLabel labelBackground = new JLabel("");
			labelBackground.setIcon(new ImageIcon(OverseasActivationSetTime.class.getResource("/teamCreditProjectApp/images/bgfoa.PNG")));
			labelBackground.setBounds(158, 183, 671, 401);
			add(labelBackground);

	}
}
