package teamCreditProjectApp.ui;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;



import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JComboBox;



import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.TableCellRenderer;


import java.awt.Color;


import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.JButton;

import teamCreditProjectApp.dataAccess.CardDetailsDA;
import teamCreditProjectApp.dataAccess.CardUsageDA;
import teamCreditProjectApp.dataAccess.TransactionDA;
import teamCreditProjectApp.entity.CardDetails;
import teamCreditProjectApp.entity.CardUsage;
import teamCreditProjectApp.entity.Login;
import teamCreditProjectApp.entity.Transaction;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

import java.util.GregorianCalendar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CardDetailsPanel extends MasterPanel {
	/**
	 * Create the panel.
	 */
	DecimalFormat fm = new DecimalFormat("0.00");
	DecimalFormat dfm = new DecimalFormat("00");
	
	public JTable tblList;
	public String cardNoContainer;
	public String statementMonthContainer;
	public  int yearContainer ;
	
	
	public CardDetailsPanel(JFrame mf, Login l1, String cardPassed ,int monthPassed ) {
		super(mf,l1);
		//cardNoContainer = cardPassed;
		setLayout(null);
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 71, 990, 2);
		add(separator);
		
		JLabel lblTitle = new JLabel("Card Details");
		lblTitle.setFont(new Font("Verdana", Font.BOLD, 25));
		lblTitle.setBounds(403, 71, 183, 29);
		add(lblTitle);
		

		ArrayList<CardDetails> cardNumber = CardDetailsDA.retrieveCardDetailsBySingpassID(l1.getSingpassID());

		String[] number = new String[cardNumber.size()];
		for(int i=0;i<cardNumber.size(); i++){
			number[i] = cardNumber.get(i).getCardNumber();
		}
		JComboBox cardNumberFilter = new JComboBox(number);
		String[] month = {"January","February","March","April","May","June","July","August","September","October","November","December"};
		JComboBox lblStatementDateFilter = new JComboBox(month);
		
		if(cardNumber.size()>0){
			if(cardPassed != ""){
				for(int i = 0 ; i < cardNumber.size() ; i ++){	
					
					if(cardNumber.get(i).getCardNumber().equalsIgnoreCase(cardPassed)){
					cardNumberFilter.setSelectedIndex(i);
					
					}
				}
				
			
			}
			else{
				
				cardNumberFilter.setSelectedIndex(0);
				
			}
			
		}
		
		
		lblStatementDateFilter.setSelectedIndex(monthPassed);
		
		
		System.out.println(monthPassed +"is monthPassed");
		
		
		JLabel lblStatementMonth = new JLabel("Statement Month : ");
		lblStatementMonth.setFont(new Font("Verdana", Font.PLAIN, 15));
		lblStatementMonth.setBounds(15, 185, 155, 20);
		add(lblStatementMonth);
		
		
		GregorianCalendar date = new GregorianCalendar();
		
		int calendarMonth = date.getTime().getMonth();
		int year = date.getTime().getYear() - 100;
		yearContainer = year +2000;
		

		tblList = new JTable();
	
		
		JLabel lblOverseasStatus = new JLabel("Overseas Status");
		lblOverseasStatus .setFont(new Font("Verdana", Font.PLAIN, 15));
		lblOverseasStatus .setBounds(677, 117, 155, 20);
		add(lblOverseasStatus );
		

		
		JLabel lblActivationBtn = new JLabel("");
		lblActivationBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				JPanel contentPane = new OverseasActivation(myFrame,loggedIn,cardNoContainer);
				myFrame.setContentPane(contentPane);
				myFrame.setVisible(true);
				
			}
		});
		lblActivationBtn.setIcon(new ImageIcon(CardDetailsPanel.class.getResource("/ProjectApp/images/off.png")));
		lblActivationBtn.setBounds(827, 111, 79, 36);
		add(lblActivationBtn);
		
		
		JLabel lblUsageLimiter = new JLabel("Card Details");
		lblUsageLimiter.setFont(new Font("Verdana", Font.PLAIN, 15));
		lblUsageLimiter.setBounds(15, 237, 118, 20);
		add(lblUsageLimiter);
		
		JPanel usageLimiterHeader = new JPanel();
		usageLimiterHeader.setBackground(new Color(0, 153, 255));
		usageLimiterHeader.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0), 2), new LineBorder(new Color(0, 0, 0))));
		usageLimiterHeader.setBounds(15, 260, 960, 36);
		add(usageLimiterHeader);
		usageLimiterHeader.setLayout(null);
		
		JLabel lblCreditLimit = new JLabel("Credit Limit($)");
		lblCreditLimit.setBounds(58, 2, 131, 26);
		usageLimiterHeader.add(lblCreditLimit);
		lblCreditLimit.setFont(new Font("Verdana", Font.BOLD, 15));
		lblCreditLimit.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreditLimit.setBackground(new Color(0, 0, 204));
		

		
		JLabel lblCreditUsed = new JLabel("Credit Used($)");
		lblCreditUsed.setBounds(273, 0, 155, 30);
		usageLimiterHeader.add(lblCreditUsed);
		lblCreditUsed.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreditUsed.setFont(new Font("Verdana", Font.BOLD, 15));
		lblCreditUsed.setBackground(new Color(0, 0, 204));
		
		JLabel lblCreditLeft = new JLabel("Credit Left($)");
		lblCreditLeft.setBounds(498, 0, 155, 30);
		usageLimiterHeader.add(lblCreditLeft);
		lblCreditLeft.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreditLeft.setFont(new Font("Verdana", Font.BOLD, 15));
		lblCreditLeft.setBackground(new Color(0, 0, 204));
		
		JLabel lblCreditUsage = new JLabel("Credit Usage");
		lblCreditUsage.setBounds(730, 1, 155, 28);
		usageLimiterHeader.add(lblCreditUsage);
		lblCreditUsage.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreditUsage.setFont(new Font("Verdana", Font.BOLD, 15));
		lblCreditUsage.setBackground(new Color(0, 0, 204));
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(238, 0, 1, 66);
		usageLimiterHeader.add(separator_1);
		
		JPanel usageLimiterAmt = new JPanel();
		usageLimiterAmt.setLayout(null);
		usageLimiterAmt.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0), 2), new LineBorder(new Color(0, 0, 0))));
		usageLimiterAmt.setBackground(Color.WHITE);
		usageLimiterAmt.setBounds(15, 294, 960, 36);
		add(usageLimiterAmt);
		
	
		JLabel creditUsageAmt = new JLabel("60%");
		creditUsageAmt.setHorizontalAlignment(SwingConstants.CENTER);
		creditUsageAmt.setFont(new Font("Verdana", Font.PLAIN, 15));
		creditUsageAmt.setBackground(Color.PINK);
		creditUsageAmt.setBounds(722, 0, 155, 30);
		usageLimiterAmt.add(creditUsageAmt);	
		
		
		JLabel creditLimitAmt = new JLabel();
		creditLimitAmt.setHorizontalAlignment(SwingConstants.CENTER);
		creditLimitAmt.setFont(new Font("Verdana", Font.PLAIN, 15));
		creditLimitAmt.setBackground(new Color(0, 0, 204));
		creditLimitAmt.setBounds(56, 2, 131, 26);
		usageLimiterAmt.add(creditLimitAmt);
		
		JLabel creditUsedAmt = new JLabel("5000.00");
		creditUsedAmt.setHorizontalAlignment(SwingConstants.CENTER);
		creditUsedAmt.setFont(new Font("Verdana", Font.PLAIN, 15));
		creditUsedAmt.setBackground(new Color(0, 0, 204));
		creditUsedAmt.setBounds(276, 0, 155, 30);
		usageLimiterAmt.add(creditUsedAmt);


		JLabel creditLeftAmt = new JLabel("7000.00");
		creditLeftAmt.setHorizontalAlignment(SwingConstants.CENTER);
		creditLeftAmt.setFont(new Font("Verdana", Font.PLAIN, 15));
		creditLeftAmt.setBackground(new Color(0, 0, 204));
		creditLeftAmt.setBounds(494, 0, 155, 30);
		usageLimiterAmt.add(creditLeftAmt);

		
		JLabel MinimumAmount = new JLabel("New label");
		MinimumAmount.setFont(new Font("Verdana", Font.PLAIN, 15));
		MinimumAmount.setBounds(809, 185, 118, 20);
		add(MinimumAmount);

		statementMonthContainer = dfm.format(lblStatementDateFilter.getSelectedIndex()+1);
		cardNoContainer = (String) cardNumberFilter.getSelectedItem();
		
		double amountLeft = setUpdatedTableModelFromDB(cardNoContainer,statementMonthContainer,yearContainer);
		ArrayList<CardDetails> cardFilter = setCreditLimit(cardNoContainer);
		
		for(int i=0;i<cardFilter.size();i++){
			
			creditLimitAmt.setText(Double.toString(cardFilter.get(i).getCreditLimit()));
			
			creditUsedAmt.setText(fm.format(amountLeft));
			double creditLeft = Double.parseDouble(creditLimitAmt.getText()) - Double.parseDouble(creditUsedAmt.getText());
			creditLeftAmt.setText(fm.format(creditLeft));
			
			String highest = minimumPayment(creditUsedAmt.getText());
			MinimumAmount.setText("SGD($)"+highest);
		
			
		
		}
		pdf pdf = new pdf(cardNoContainer,statementMonthContainer,yearContainer,creditLimitAmt.getText(), MinimumAmount.getText(),tblList);
		
		ArrayList<CardUsage> cardUsage = CardUsageDA.retrieveCardUsageByCardNumber(cardNoContainer);
		for(int i=0; i<cardUsage.size(); i++){
			creditUsageAmt.setText(cardUsage.get(cardUsage.size()-1).getUsageLimit()+"%");
		}
		JButton btnChange = new JButton("Change");
		btnChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JPanel contentPane = new CreditUsage(myFrame,loggedIn,cardNoContainer);
				myFrame.setContentPane(contentPane);
				myFrame.setVisible(true);
				
				
			}
		});
		btnChange.setBounds(860, 5, 85, 20);
		usageLimiterAmt.add(btnChange);

		
		JLabel lblTransactionHistory = new JLabel("Transaction History");
		lblTransactionHistory.setFont(new Font("Verdana", Font.PLAIN, 15));
		lblTransactionHistory.setBounds(15, 346, 155, 20);
		add(lblTransactionHistory);
		
		JLabel lblLocal = new JLabel("Local");
		lblLocal.setFont(new Font("Verdana", Font.PLAIN, 15));
		lblLocal.setBounds(15, 370, 48, 20);
		add(lblLocal);
		
		JPanel panelLocal = new JPanel();
		panelLocal.setBackground(Color.WHITE);
		panelLocal.setBounds(63, 372, 31, 18);
		add(panelLocal);
		
		JLabel lblOverseas = new JLabel("Overseas");
		lblOverseas.setFont(new Font("Verdana", Font.PLAIN, 15));
		lblOverseas.setBounds(109, 370, 79, 20);
		add(lblOverseas);
		
		JPanel panelOverseas = new JPanel();
		panelOverseas.setBackground(Color.GRAY);
		panelOverseas.setBounds(190, 372, 31, 18);
		add(panelOverseas);
	
		

		JLabel MinimumPayment = new JLabel("Minimum Payment : ");
		MinimumPayment.setFont(new Font("Verdana", Font.PLAIN, 15));
		MinimumPayment.setBounds(649, 185, 163, 20);
		add(MinimumPayment);
	
		
		JScrollPane transactionScrollPane = new JScrollPane();
		transactionScrollPane.setBounds(15, 406, 960, 168);
		add(transactionScrollPane);
		
		
		transactionScrollPane.setViewportView(tblList);
		transactionScrollPane.setColumnHeaderView(tblList.getTableHeader());

				ArrayList<CardDetails> cardDetails = CardDetailsDA.retrieveCardDetailsByCardNumber(cardNoContainer);
				if(cardDetails.get(0).getActivationStatus() == 1){
					lblActivationBtn.setIcon(new ImageIcon(CardDetailsPanel.class.getResource("/MyDesign/on.png")));	
					
				}	
				else{
					lblActivationBtn.setIcon(new ImageIcon(CardDetailsPanel.class.getResource("/MyDesign/off.png")));

				}



		cardNumberFilter.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				statementMonthContainer = dfm.format(lblStatementDateFilter.getSelectedIndex()+1);
				cardNoContainer = (String) cardNumberFilter.getSelectedItem();

				double amountLeft = setUpdatedTableModelFromDB(cardNoContainer,statementMonthContainer,yearContainer);
				
				ArrayList<CardDetails> cardFilter = setCreditLimit(cardNoContainer);
				System.out.println("--------------"+cardFilter.get(0).getCreditLimit());
				for(int i=0;i<cardFilter.size();i++){
				creditLimitAmt.setText(Double.toString(cardFilter.get(i).getCreditLimit()));

				creditUsedAmt.setText(fm.format(amountLeft));
				double creditLeft = Double.parseDouble(creditLimitAmt.getText()) - Double.parseDouble(creditUsedAmt.getText());
				creditLeftAmt.setText(fm.format(creditLeft));
				
				String highest = minimumPayment(creditUsedAmt.getText());
				MinimumAmount.setText("SGD($)"+highest);
				}
				
				pdf pdf = new pdf(cardNoContainer,statementMonthContainer,yearContainer,creditLimitAmt.getText(), MinimumAmount.getText(),tblList);
				
				
				ArrayList<CardUsage> cardUsage = CardUsageDA.retrieveCardUsageByCardNumber(cardNoContainer);
				
					creditUsageAmt.setText(cardUsage.get(cardUsage.size()-1).getUsageLimit()+"%");
				
				
				
				
				
					ArrayList<CardDetails> cardDetails = CardDetailsDA.retrieveCardDetailsByCardNumber(cardNoContainer);
					if(cardDetails.get(0).getActivationStatus() == 1){
						lblActivationBtn.setIcon(new ImageIcon(CardDetailsPanel.class.getResource("/MyDesign/on.png")));	
						
					}	
					else{
						lblActivationBtn.setIcon(new ImageIcon(CardDetailsPanel.class.getResource("/MyDesign/off.png")));

					}
			}
		});
		cardNumberFilter.setFont(new Font("Verdana", Font.PLAIN, 15));
		cardNumberFilter.setBounds(129, 129, 220, 18);
		add(cardNumberFilter);
		

		lblStatementDateFilter.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent arg0) {

					statementMonthContainer = dfm.format(lblStatementDateFilter.getSelectedIndex()+1);
					cardNoContainer = (String) cardNumberFilter.getSelectedItem();
					System.out.println("-----------------------"+lblStatementDateFilter.getSelectedItem());

					
					double amountLeft = setUpdatedTableModelFromDB(cardNoContainer,statementMonthContainer,yearContainer);
					ArrayList<CardDetails> cardFilter = setCreditLimit(cardNoContainer);
	
					for(int i=0;i<cardFilter.size();i++){
					creditLimitAmt.setText(Double.toString(cardFilter.get(i).getCreditLimit()));

					creditUsedAmt.setText(fm.format(amountLeft));
					double creditLeft = Double.parseDouble(creditLimitAmt.getText()) - Double.parseDouble(creditUsedAmt.getText());
					creditLeftAmt.setText(fm.format(creditLeft));
					
					String highest = minimumPayment(creditUsedAmt.getText());
					MinimumAmount.setText("SGD($)"+highest);
					}
					
					
					pdf pdf = new pdf(cardNoContainer,statementMonthContainer,yearContainer,creditLimitAmt.getText(), MinimumAmount.getText(),tblList);

					ArrayList<CardUsage> cardUsage = CardUsageDA.retrieveCardUsageByCardNumber(cardNoContainer);

						creditUsageAmt.setText(cardUsage.get(cardUsage.size()-1).getUsageLimit()+"%");
					
					
					

			}		
		});
		
	
		
		
		lblStatementDateFilter.setBounds(164, 186, 94, 18);
		add(lblStatementDateFilter);
		
		
		JLabel lblCardNumber = new JLabel("Card Number : ");
		lblCardNumber.setFont(new Font("Verdana", Font.PLAIN, 15));
		lblCardNumber.setBounds(15, 127, 118, 20);
		add(lblCardNumber);
		
		JButton printBtn = new JButton("Display PDF");
		printBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+"CardDetails.pdf");
				}catch (Exception arg0){
					JOptionPane.showMessageDialog(null, "Error");
				}
					
				
			}
		});
		printBtn.setBounds(845, 578, 130, 29);
		add(printBtn);

		
		}
	
	
	

	
	
/*	private void setTableModelFromDB(){
		GregorianCalendar calendar = new GregorianCalendar();
		
		int month = calendar.getTime().getMonth() + 1;
		int year = calendar.getTime().getYear() - 100;
	
		ArrayList<Transaction> result = TransactionDA.retrieveAllTransaction();
		ArrayList<Transaction> date = new ArrayList<Transaction>();
		for(int i = 0; i<result.size(); i++){
			if(Integer.parseInt(result.get(i).getTransactionDate().substring(3,5)) == month){
			if(Integer.parseInt(result.get(i).getTransactionDate().substring(8,10)) == year)
				date.add(result.get(i));
			
				
		//	}
			}
		TransactionTableModel model = new TransactionTableModel(date);
		tblList.setModel(model);
		}
	}*/
	public String minimumPayment(String a){
		double highest = (Double.parseDouble(a)*0.05);
		if(highest == 0){
			highest = 0;
		}
		else if(highest<50){
			highest = 50.0;
		}
		return fm.format(highest);
		
	}
	
	//table model based on filter
	public double setUpdatedTableModelFromDB(String cardNumber, String month, int year){

		double creditUsed = 0;
		ArrayList<Transaction> t1 = TransactionDA.retrieveTransactionByCardNumberAndDate(cardNumber,month,year);
		TransactionTableModel model1 = new TransactionTableModel(t1);
		tblList.setModel(model1);
		TableCellRenderer renderer = new EvenOddRenderer(cardNoContainer, statementMonthContainer, yearContainer);
		tblList.setDefaultRenderer(Object.class, renderer);
		

		for(int i=0;i<t1.size(); i++){
			creditUsed += t1.get(i).getTransactionAmount();
		}
		return creditUsed;
	}
	
	public ArrayList<CardDetails> setCreditLimit(String cardNumber){
		ArrayList<CardDetails> cardDetails = CardDetailsDA.retrieveAllCardDetails();
		ArrayList<CardDetails> filteredList = new ArrayList<CardDetails>();
		for(int i =0;i<cardDetails.size();i++){
				if(cardNumber.equalsIgnoreCase(cardDetails.get(i).getCardNumber())){
					filteredList.add(cardDetails.get(i));
				}
		}
		return filteredList;
	}
}
