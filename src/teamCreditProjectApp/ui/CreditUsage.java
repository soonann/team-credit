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
import javax.swing.event.ChangeListener;

import teamCreditProjectApp.dataAccess.CardDetailsDA;
import teamCreditProjectApp.dataAccess.CardUsageDA;
import teamCreditProjectApp.entity.CardDetails;
import teamCreditProjectApp.entity.CardUsage;
import teamCreditProjectApp.entity.Login;

import javax.swing.event.ChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.GregorianCalendar;
import java.beans.PropertyChangeEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CreditUsage extends MasterPanel {
	private JTextField creditLimitTextField;
	private CardUsage cardUsage;
	private JSlider sliderCreditUsage;
	private String cardNo;
	/**
	 * Create the panel.
	 */
	public CreditUsage(JFrame mf,Login l1,String cardNo) {
		super(mf,l1);
		this.cardNo = cardNo;
		setLayout(null);
		
		JLabel creditUsageLabel = new JLabel("Credit Usage");
		creditUsageLabel.setFont(new Font("Arial", Font.PLAIN, 25));
		creditUsageLabel.setBounds(224, 284, 160, 35);
		add(creditUsageLabel);
		
		sliderCreditUsage = new JSlider();
		sliderCreditUsage.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				creditLimitTextField.setText(""+sliderCreditUsage.getValue());
			}
		});
		sliderCreditUsage.setBounds(376, 284, 388, 35);
		add(sliderCreditUsage);
		
		creditLimitTextField = new JTextField();
		creditLimitTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(verifyPercentage(creditLimitTextField.getText())){
					
					int percent = Integer.parseInt(creditLimitTextField.getText());
					
					sliderCreditUsage.setValue(percent);}
				
			}
		});
		
	
		creditLimitTextField.setHorizontalAlignment(SwingConstants.CENTER);
		creditLimitTextField.setFont(new Font("Arial", Font.PLAIN, 15));
		creditLimitTextField.setBounds(639, 350, 86, 35);
		add(creditLimitTextField);
		creditLimitTextField.setColumns(10);
		
		JLabel percentageLabel = new JLabel(" %");
		percentageLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		percentageLabel.setBounds(722, 350, 28, 35);
		add(percentageLabel);
		
		JButton submitButton = new JButton("submit");
		submitButton.setFont(new Font("Arial", Font.PLAIN, 18));
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedOk();
			}
				private void actionPerformedOk() {	
							String creditLimit = creditLimitTextField.getText();
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
							
							cardUsage = new CardUsage(0,cardNo,Integer.parseInt(creditLimit),todaysDate);
							actionPerformedCreate();
									
					
				}

				public void actionPerformedUpdate(){
					// Update record in database and check return value
					if (CardUsageDA.updateCardUsage(cardUsage)) {
						JOptionPane.showMessageDialog(myFrame,
							"Record updated successfully", "Alert",
							JOptionPane.INFORMATION_MESSAGE);
						// reset text field for next record.
						creditLimitTextField.setEditable(false);
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
					if (CardUsageDA.createCardUsage(cardUsage)) {
						JOptionPane.showMessageDialog(myFrame,
							"Record created successfully", "Alert",
							JOptionPane.INFORMATION_MESSAGE);
						// reset text field for next record.
						creditLimitTextField.setText("");

					} 
					else
						JOptionPane.showMessageDialog(myFrame,
								"Database Error. Record not created.", "Alert",
									JOptionPane.ERROR_MESSAGE);

				
				JPanel contentPane = new CreditUsage(myFrame,loggedIn,cardNo);
				myFrame.setContentPane(contentPane);
				myFrame.setVisible(true);
			}
		});
		submitButton.setBounds(362, 425, 100, 35);
		add(submitButton);
		
		JButton cancelButton = new JButton("cancel");
		cancelButton.setFont(new Font("Arial", Font.PLAIN, 18));
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GregorianCalendar g1 = new GregorianCalendar();
				
				JPanel contentPane = new CardDetailsPanel(mf,loggedIn,cardNo,g1.get(GregorianCalendar.MONTH));
				mf.setContentPane(contentPane);
				mf.setVisible(true);
			}
		});
		cancelButton.setBounds(530, 425, 100, 35);
		add(cancelButton);
		
		JLabel cardNumberLabel = new JLabel("4628123465657788");
		cardNumberLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cardNumberLabel.setBounds(552, 143, 228, 23);
		add(cardNumberLabel);
		cardNumberLabel.setText(cardNo);
		
		JLabel cardNumberIsLabel = new JLabel("Card Number: ");
		cardNumberIsLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cardNumberIsLabel.setBounds(449, 143, 115, 23);
		add(cardNumberIsLabel);
		
		
		JLabel labelBackground = new JLabel("");
		labelBackground.setIcon(new ImageIcon(OverseasActivationSetTime.class.getResource("/teamCreditProjectApp/images/bgfoa.PNG")));
		labelBackground.setBounds(158, 158, 671, 400);
		add(labelBackground);

	}
	public static boolean verifyPercentage(String str) {
		boolean result = true;
		try {
			
			int percent = Integer.parseInt(str);
			if(percent < 0 || percent >100){
				
				result = false;
			}
			
		} catch (NumberFormatException e) {
			result = false;
		}
		return result;
	}
}
