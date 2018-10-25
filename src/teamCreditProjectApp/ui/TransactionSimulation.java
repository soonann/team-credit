
package teamCreditProjectApp.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import teamCreditProjectApp.dataAccess.CardDetailsDA;
import teamCreditProjectApp.dataAccess.ConversionDA;
import teamCreditProjectApp.dataAccess.TransactionDA;
import teamCreditProjectApp.entity.CardDetails;
import teamCreditProjectApp.entity.Conversion;
import teamCreditProjectApp.entity.Transaction;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;

public class TransactionSimulation extends JPanel{
	

	private JTextField txtAmount;
	private JTextField txtCardNumber;
	private JComboBox txtCategory;
	private static JFrame myFrame;
	private JTextField txtDescription;
	private JComboBox currency;
	private ArrayList<Conversion> cList;
	
	/**
	 * Create the panel.
	 */
	public TransactionSimulation(JFrame mf) {
		setBackground(Color.LIGHT_GRAY);
		
		setBounds(0,0, 490,490);
		setLayout(null);
		myFrame = mf;
		
		
		
		
		txtAmount = new JTextField();
		txtAmount.setToolTipText("");
		txtAmount.setBounds(263, 187, 107, 24);
		add(txtAmount);
		txtAmount.setColumns(10);
		
		
		JLabel lblAmount = new JLabel("Amount : ");
		lblAmount.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAmount.setBounds(116, 188, 81, 23);
		add(lblAmount);
		
		JButton btnSimulate = new JButton("Transact");
		
		btnSimulate.setBackground(Color.WHITE);
		btnSimulate.setBounds(278, 364, 92, 23);
		add(btnSimulate);
		
		JLabel lblCategory = new JLabel("Category :");
		lblCategory.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCategory.setBounds(124, 233, 73, 19);
		add(lblCategory);
		
		JLabel lblCard = new JLabel("Card :");
		lblCard.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCard.setBounds(144, 147, 53, 19);
		add(lblCard);
		
		txtCardNumber = new JTextField();
		txtCardNumber.setBounds(207, 145, 163, 30);
		add(txtCardNumber);
		txtCardNumber.setColumns(10);
		
		txtCategory = new JComboBox();
		txtCategory.addItem("");
		ArrayList<String> categories = TransactionDA.retrieveListOfCategoryFromTransaction();
		for(String list : categories){
			txtCategory.addItem(list);
		}
		
		txtCategory.setBounds(207, 231, 163, 21);
		
		
		add(txtCategory);
		
		
		JLabel lblDescription = new JLabel("Description :");
		lblDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescription.setBounds(116, 279, 82, 19);
		add(lblDescription);
		
		txtDescription = new JTextField();
		txtDescription.setColumns(10);
		txtDescription.setBounds(207, 277, 163, 25);
		add(txtDescription);
		
		JLabel lblPayment = new JLabel("Transaction Simulation");
		lblPayment.setHorizontalAlignment(SwingConstants.CENTER);
		lblPayment.setFont(new Font("Verdana", Font.BOLD, 15));
		lblPayment.setBounds(103, 77, 289, 40);
		add(lblPayment);
		
		currency = new JComboBox();
		currency.setBounds(207, 187, 53, 24);
		
		
		ArrayList<Conversion> cList = ConversionDA.retrieveAllConversion();
		cList.set(0,new Conversion("SGD",1));
		for(int i = 0 ; i < cList.size() ; i++){
			
			currency.addItem(cList.get(i).getCurrency());	
			
		}
		
		add(currency);
		this.cList = cList;
		
		
		//
				ArrayList<Transaction> tempList = TransactionDA.retrieveAllTransaction();
				Transaction [] tempArrList = new Transaction[tempList.size()];
				String [] cardNumberArr = new String [tempArrList.length];
				
				for(int i = 0 ; i < tempList.size() ; i++){
					
					tempArrList[i] = tempList.get(i);
					
					
				}
		//
		btnSimulate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				
				transactionSimulate();
			
				
			}
		});
		
		

	}
	
	private void transactionSimulate(){
		
		
	
		
		if(validateTransaction()){
			
			
		String cardNumber = txtCardNumber.getText() ;
		String amount = txtAmount.getText() ;
		String category = String.valueOf(txtCategory.getSelectedItem());
		String description = txtDescription.getText();
		double originalAmount = 0;
		double conversionRate = 0;
		
		
			
		double amountConverted = Double.parseDouble(amount);
		
		if(!(String.valueOf(currency.getSelectedItem()).equals("SGD"))){
			conversionRate = cList.get(currency.getSelectedIndex()).getConversionRate();
			originalAmount = amountConverted;
			amountConverted = (amountConverted*conversionRate);
			
		}
		
		GregorianCalendar c1 = new GregorianCalendar();
			
		String currentDate = "";
		String currentMonth;
		String currentDay;
		
			if((c1.get(GregorianCalendar.MONTH)+1) < 10 ){
				
				currentMonth = "0"+(Integer.toString(c1.get(GregorianCalendar.MONTH)+1));
				
			}
			else{
				currentMonth = Integer.toString(c1.get(GregorianCalendar.MONTH)+1);
			}
				
		
			
			if((c1.get(GregorianCalendar.DAY_OF_MONTH))<10){
				currentDay = "0"+Integer.toString(c1.get(GregorianCalendar.DAY_OF_MONTH));
			}
			
			else{
				
				currentDay = Integer.toString(c1.get(GregorianCalendar.DAY_OF_MONTH));
				
			}
			
			
			currentDate = currentDay + "/" + currentMonth + "/" + Integer.toString(c1.get(GregorianCalendar.YEAR));
			//credit limit checking
			cardNumber = cardNumber.substring(0,4)+ "-"+cardNumber.substring(4,8)+ "-"+ cardNumber.substring(8,12)+ "-"+cardNumber.substring(12,16);
			String bankName = "";
			String singpassID = "";
			
			ArrayList<CardDetails> cardCheck = CardDetailsDA.retrieveAllCardDetails();
			boolean exists = false;
			for(int i = 0 ; i < cardCheck.size(); i++){
				
				if(cardNumber.equals(cardCheck.get(i).getCardNumber())){
					exists = true;
					bankName = cardCheck.get(i).getBankName();
					singpassID = cardCheck.get(i).getSingpassID();
					
					
				}
				
				
				
			}
				

			
			if(exists){
				
				
				String dateRange = "%"+currentMonth + "/" + Integer.toString(c1.get(GregorianCalendar.YEAR));
				double totalAmount = 0 ;
				
				ArrayList<CardDetails> allUsersCards = CardDetailsDA.retrieveCardDetailsBySingpassIDAndBankName(singpassID,bankName);
				
				for(int i = 0 ; i < allUsersCards.size() ; i++){
					
					ArrayList<Transaction> transDateCard = TransactionDA.retrieveTransactionsByDateAndCardNumber(dateRange,allUsersCards.get(i).getCardNumber());
					
					for(int j = 0 ; j < transDateCard.size() ; j++){
						
						totalAmount +=	transDateCard.get(j).getTransactionAmount();
						
					}		
					
				}
				
				
				CardDetails cardExceededAmount = new CardDetails();;		
				ArrayList<CardDetails> cardExceed = CardDetailsDA.retrieveCardDetailsByCardNumber(cardNumber);		
				for(int i = 0 ; i < cardExceed.size() ; i++){
					
					if((cardExceed.get(i).getCardNumber()).equals(cardNumber)){
						
						cardExceededAmount = cardExceed.get(i);
						
					}
					
				}
				DecimalFormat df = new DecimalFormat("00.00");
				amountConverted = Double.parseDouble(df.format(amountConverted));
					
				// check bank credit limit 
				
				boolean createSuccess = false;
				
				if( (totalAmount+amountConverted) <= cardExceededAmount.getCreditLimit() ){	
						Transaction t1 = new Transaction();
						
						if(!(String.valueOf(currency.getSelectedItem()).equals("SGD"))){
							if(cardExceededAmount.getActivationStatus() == 1){
								t1 = new Transaction(0,cardNumber,  currentDate ,amountConverted,String.valueOf(currency.getSelectedItem()) ,originalAmount , conversionRate ,description,category );
							}
							else{
								//
								JOptionPane.showMessageDialog(myFrame,  "Card is not activated for overseas use !", "Alert", JOptionPane.INFORMATION_MESSAGE);
							}
						}
						else{
							
							t1 = new Transaction(0,cardNumber,  currentDate ,amountConverted,description,category );
							
						}
						
						if(!(String.valueOf(currency.getSelectedItem()).equals("SGD"))){
							if(cardExceededAmount.getActivationStatus() == 1){
								if(TransactionDA.createTransaction(t1)){
									createSuccess = true;
								}
							}
						}
						else{
							
							if(TransactionDA.createTransactionLocal(t1)){
								createSuccess = true;
							}
							
						}
						
						if(createSuccess){
							
							
							
							JOptionPane.showMessageDialog(myFrame,  "Transaction Successful !", "Alert", JOptionPane.INFORMATION_MESSAGE);
							clearFields();
							rewardsCalculation(cardNumber,amountConverted,bankName);
							if(amountConverted >=1000 ){
								
								HttpClientApache http = new HttpClientApache();
	
								System.out.println("Testing 1 - Send Http GET request");
								String messagePart1 = "You've spend $"+df.format(amountConverted) +" (Card No: XXXX-XXXX-XXXX-"+ cardNumber.substring(15,19)+") at "+description+"." ;
								String messagePart2 = " Please call +65 91759586 if it is not done by you.";
								try {
									http.sendGet(messagePart1,messagePart2);
								} catch (Exception e) {
									
									e.printStackTrace();
								}
								
								// send sms cardNumber,currentDate,amountConverted	
							}
						}
						else{
							
							JOptionPane.showMessageDialog(myFrame,  "Transaction Unsuccessful !", "Alert", JOptionPane.ERROR_MESSAGE);
						}	
						
						
					
				}
				else{
					
					JOptionPane.showMessageDialog(myFrame,  "Transaction Unsuccessful !\nThis transaction exceeds your credit limit !", "Alert", JOptionPane.ERROR_MESSAGE);
					
				}
				
				
			}
			else{
				
				JOptionPane.showMessageDialog(myFrame,  "Please enter a valid card number !", "Alert", JOptionPane.ERROR_MESSAGE);
			
			}
	
			//end of creating
				
				
				
				
		}	
		
		
		
	}
	
	
	private boolean validateTransaction(){
	
	
		
		String cardNumber = txtCardNumber.getText() ;
		String amount = txtAmount.getText();
		String description = txtDescription.getText();
		String category = String.valueOf(txtCategory.getSelectedItem());
		
		boolean valid = true;
		String msg = "";
		
		
		if(!cardNumber.equals("")){
			try{
				
				Integer.parseInt(cardNumber.substring(0, 8));
				Integer.parseInt(cardNumber.substring(8, 16));
				
			}catch(Exception e){
				
				if (e instanceof StringIndexOutOfBoundsException || e instanceof NumberFormatException){
				
				msg +="Please enter your 16 digit card number without dashes !\n";
				valid = false;
				 }
				
			}	
		}
		else{
			
			msg += "Please enter your card number !\n";
			valid = false;
		}
		
		
		if( !amount.equals("") ){
			
			//empty
			try{
				
				if(!(Double.parseDouble(txtAmount.getText())>0)){
					valid = false;
					msg += "Please enter a valid amount !\n";
					
				} 
				
			}
			catch(NumberFormatException e){
				valid = false;
				msg += "Please enter only numbers for amount !\n";
				
			}
		}
		
		
		else{
			
			//not empty
			msg += "Please enter your amount !\n";
			valid = false;
		}
		
		
	
		if(description.equals("")){
			valid = false;
			msg += "Please enter a description for your transaction !\n";
			
		}
		
		if(category.equals("")){
			valid = false;
			msg += "Please choose a category for your transaction !\n";
			
		}
			
		if(!valid){
			
			JOptionPane.showMessageDialog(myFrame, msg , "Alert", JOptionPane.ERROR_MESSAGE);
		
		}
		
		return valid;
		
	}
	
	
	private void rewardsCalculation(String cardNumber, double amountConverted,String bankName){
	
		DecimalFormat df = new DecimalFormat("00");
		ArrayList<CardDetails> cardList =  CardDetailsDA.retrieveCardDetailsByCardNumber(cardNumber);
		CardDetails addPoints = cardList.get(0);
		
		if(bankName.equalsIgnoreCase("dbs") || bankName.equalsIgnoreCase("uob")){
		addPoints.setRewardPoints(Integer.parseInt(df.format(addPoints.getRewardPoints() + ( Integer.parseInt(df.format(amountConverted))/5))) );
		}
		else{
			
			addPoints.setRewardPoints(Integer.parseInt(df.format(addPoints.getRewardPoints() + ( Integer.parseInt(df.format(amountConverted))))) );
			
		}
		
		if(CardDetailsDA.updateCardDetails(addPoints)){
			System.out.println("Success!");
			
		}
		else{
			
			System.out.println("failed !");
		}
		;
	
	}

	
	
	
	private void clearFields(){
		
	
		txtAmount.setText("");
		txtCardNumber.setText("");
		txtDescription.setText("");
		
		txtCategory.setSelectedIndex(0);
		
		
	}
}
