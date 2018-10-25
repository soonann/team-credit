package teamCreditProjectApp.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JInternalFrame;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;
import javax.swing.JProgressBar;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;

import java.awt.BorderLayout;
import javax.swing.JSeparator;
import java.awt.SystemColor;
import javax.swing.border.EtchedBorder;
import teamCreditProjectApp.entity.Login;

import teamCreditProjectApp.dataAccess.LoginDA;

import teamCreditProjectApp.dataAccess.TransactionDA;
import teamCreditProjectApp.entity.Transaction;
import teamCreditProjectApp.entity.CardDetails;
import teamCreditProjectApp.entity.CardPopout;
import teamCreditProjectApp.dataAccess.CardDetailsDA;
import java.util.GregorianCalendar;
import javax.swing.UIManager;
import java.text.DecimalFormat;
import javax.swing.ListSelectionModel;


public class LoginSummaryPanel extends MasterPanel {


	
	private static final long serialVersionUID = 1L;

	private JTable overseasuseTable;
	private JTable rewardsTable;
	private JLabel currentMonthSpendings = new JLabel("");
	private JLabel monthOneSpendings = new JLabel("");
	private JLabel currentMonthLabel = new JLabel("");
	private JPanel monthOnePanel = new JPanel();
	private JLabel monthOneLabel = new JLabel("");
	private JLabel monthTwoLabel = new JLabel("");
	private JLabel monthTwoSpendings = new JLabel("");
	private ArrayList<CardPopout> transact;
	private String currentSelectedLabel ;
	JInternalFrame cardDetailsPopout;
	private JTable cardDetailsTable;
	
	public LoginSummaryPanel(JFrame mf,Login l1) {
		
		super(mf,l1);
		

		refreshRewardsTransactionDatabase(loggedIn);
		deactivatedCards(loggedIn);
		setBounds(0,0,990,650);
		
		
		cardDetailsPopout = new JInternalFrame("");
		cardDetailsPopout.setClosable(true);
		cardDetailsPopout.setBounds(242, 222, 502, 202);
		add(cardDetailsPopout);
		
		JScrollPane cardDetailsScrollpane = new JScrollPane();
		cardDetailsPopout.getContentPane().add(cardDetailsScrollpane, BorderLayout.NORTH);
		
		cardDetailsTable = new JTable();
		cardDetailsTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int selectedRow = cardDetailsTable.getSelectedRow();
				
				if( selectedRow  >= 0){
					
					
					 JPanel contentPane = new CardDetailsPanel(mf,l1,transact.get(selectedRow).getCardNumber(),monthIndex(currentSelectedLabel));
					 myFrame.setContentPane(contentPane);
					 myFrame.setVisible(true);
					
				}
				
				
			}
		});
		cardDetailsScrollpane.setViewportView(cardDetailsTable);
		cardDetailsPopout.setVisible(true);
		
		
		JLabel spendingsLabel = new JLabel("Card Spendings :");
		spendingsLabel.setHorizontalAlignment(SwingConstants.LEFT);
		spendingsLabel.setFont(new Font("Verdana", Font.BOLD, 15));
		spendingsLabel.setBounds(724, 122, 170, 28);
		add(spendingsLabel);
		
		JLabel overseasUsageLabel = new JLabel("Overseas Usage :");
		overseasUsageLabel.setFont(new Font("Verdana", Font.BOLD, 15));
		overseasUsageLabel.setHorizontalAlignment(SwingConstants.LEFT);
		overseasUsageLabel.setBounds(33, 116, 159, 40);
		add(overseasUsageLabel);
		
		JLabel overseasuseAmountLabel = new JLabel("");
		overseasuseAmountLabel.setFont(new Font("Verdana", Font.PLAIN, 15));
		overseasuseAmountLabel.setHorizontalAlignment(SwingConstants.CENTER);
		overseasuseAmountLabel.setBounds(120, 155, 151, 40);
		overseasuseAmountLabel.setBorder(new LineBorder(new Color(0,0,0)));
		add(overseasuseAmountLabel);
		
		JPanel currentMonthPanel = new JPanel();
		currentMonthPanel.setBackground(Color.LIGHT_GRAY);
		currentMonthPanel.setBorder(new LineBorder(new Color(0, 0, 0), 4));
		currentMonthPanel.setBounds(737, 173, 188, 105);
		add(currentMonthPanel);
		currentMonthPanel.setLayout(null);
		currentMonthLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			
				transact = setTransactionTableModelFromDB(retrieveTableDetails("current",l1),currentMonthLabel.getText());
				currentSelectedLabel = currentMonthLabel.getText();
				
				
			}
		});
		
		currentMonthLabel.setBackground(UIManager.getColor("Button.disabledShadow"));
		currentMonthLabel.setForeground(Color.BLACK);
		currentMonthLabel.setHorizontalAlignment(SwingConstants.CENTER);
		currentMonthLabel.setBounds(20, 24, 110, 30);
		currentMonthLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
		currentMonthPanel.add(currentMonthLabel);
			
		currentMonthSpendings.setFont(new Font("Tahoma", Font.PLAIN, 15));
		currentMonthSpendings.setForeground(Color.BLACK);
		currentMonthSpendings.setHorizontalAlignment(SwingConstants.CENTER);
		currentMonthSpendings.setBounds(10, 65, 168, 23);
		currentMonthPanel.add(currentMonthSpendings);	
		
		JLabel currentMonthStatistics = new JLabel("");
		currentMonthStatistics.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				JPanel contentPane = new CategoryChartPanel(mf,l1, "/"+getMonths()[0], currentMonthLabel.getText());
				myFrame.setContentPane(contentPane);
				myFrame.setVisible(true);
				
				
				
			}
		});
		currentMonthStatistics.setHorizontalAlignment(SwingConstants.CENTER);
		currentMonthStatistics.setIcon(new ImageIcon(LoginSummaryPanel.class.getResource("/ProjectApp/images/Statistics1.png")));
		currentMonthStatistics.setBounds(140, 24, 30, 30);
		currentMonthStatistics.setBorder(new LineBorder(new Color(0, 0, 0), 1));
		currentMonthPanel.add(currentMonthStatistics);
		
		monthOnePanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		monthOnePanel.setLayout(null);
		monthOnePanel.setBounds(754, 312, 159, 90);
		add(monthOnePanel);
		monthOneLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			transact = setTransactionTableModelFromDB(retrieveTableDetails("second",l1),monthOneLabel.getText());
			currentSelectedLabel = monthOneLabel.getText();
			}
		});
		
		monthOneLabel.setBackground(Color.WHITE);
		monthOneLabel.setHorizontalAlignment(SwingConstants.CENTER);
		monthOneLabel.setBounds(10, 11, 102, 29);
		monthOneLabel.setBorder(new LineBorder(new Color(0, 0, 0), 1));
		monthOnePanel.add(monthOneLabel);
				
		monthOneSpendings.setFont(new Font("Tahoma", Font.PLAIN, 15));
		monthOneSpendings.setForeground(Color.BLACK);
		monthOneSpendings.setHorizontalAlignment(SwingConstants.CENTER);
		monthOneSpendings.setBounds(10, 51, 139, 28);
		monthOnePanel.add(monthOneSpendings);
		
		JLabel monthOneStatistics = new JLabel("");
		monthOneStatistics.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				JPanel contentPane = new CategoryChartPanel(mf,l1, "/"+getMonths()[1],monthOneLabel.getText());
				myFrame.setContentPane(contentPane);
				myFrame.setVisible(true);
				
			}
		});
		
	
		
		monthOneStatistics.setIcon(new ImageIcon(LoginSummaryPanel.class.getResource("/ProjectApp/images/Statistics1.png")));
		monthOneStatistics.setHorizontalAlignment(SwingConstants.CENTER);
		monthOneStatistics.setBounds(119, 11, 30, 29);
		monthOneStatistics.setBorder(new LineBorder(new Color(0, 0, 0), 1));
		monthOnePanel.add(monthOneStatistics);
		
		JPanel monthTwoPanel = new JPanel();
		monthTwoPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		monthTwoPanel.setLayout(null);
		monthTwoPanel.setBounds(754, 428, 159, 90);
		add(monthTwoPanel);
		monthTwoLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				transact = setTransactionTableModelFromDB(retrieveTableDetails("third",l1),monthTwoLabel.getText());
				currentSelectedLabel = monthTwoLabel.getText();
			}
		});
				
		monthTwoLabel.setBackground(Color.WHITE);
		monthTwoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		monthTwoLabel.setBorder(new LineBorder(new Color(0, 0, 0), 1));
		monthTwoLabel.setBounds(10, 11, 102, 29);
		monthTwoPanel.add(monthTwoLabel);
			
		monthTwoSpendings.setFont(new Font("Tahoma", Font.PLAIN, 15));
		monthTwoSpendings.setForeground(Color.BLACK);
		monthTwoSpendings.setHorizontalAlignment(SwingConstants.CENTER);
		monthTwoSpendings.setBounds(10, 51, 139, 28);
		monthTwoPanel.add(monthTwoSpendings);
		
		JLabel monthTwoStatistics = new JLabel("");
		monthTwoStatistics.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JPanel contentPane = new CategoryChartPanel(mf, l1, "/"+getMonths()[2] , monthTwoLabel.getText());
				myFrame.setContentPane(contentPane);
				myFrame.setVisible(true);
			}
		});
		monthTwoStatistics.setIcon(new ImageIcon(LoginSummaryPanel.class.getResource("/ProjectApp/images/Statistics1.png")));
		monthTwoStatistics.setHorizontalAlignment(SwingConstants.CENTER);
		monthTwoStatistics.setBounds(119, 11, 30, 29);
		monthTwoStatistics.setBorder(new LineBorder(new Color(0, 0, 0), 1));
		monthTwoPanel.add(monthTwoStatistics);
		
		JLabel viewAnnualPerformanceLabel = new JLabel("");
		viewAnnualPerformanceLabel.setBorder(new LineBorder(new Color(0, 0, 0)));	
		viewAnnualPerformanceLabel.setBackground(Color.LIGHT_GRAY);
		viewAnnualPerformanceLabel.setIcon(new ImageIcon(LoginSummaryPanel.class.getResource("/ProjectApp/images/annual performance.png")));
		viewAnnualPerformanceLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
		viewAnnualPerformanceLabel.setBounds(724, 541, 219, 40);
		add(viewAnnualPerformanceLabel);
		
		JScrollPane overseasuseScrollPane = new JScrollPane();
		overseasuseScrollPane.setBounds(33, 273, 618, 113);
		add(overseasuseScrollPane);
			
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 399, 673, 3);
		add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(671, 100, 2, 550);
		add(separator_1);
		
		JLabel lblCardActivation = new JLabel("Activated for Overseas Use :");
		lblCardActivation.setHorizontalAlignment(SwingConstants.LEFT);
		lblCardActivation.setFont(new Font("Verdana", Font.BOLD, 15));
		lblCardActivation.setBounds(33, 234, 293, 28);
		add(lblCardActivation);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(0, 206, 673, 3);
		add(separator_2);
		
		JLabel expiringRewardPointsTitleLabel = new JLabel("Reward Points :");
		expiringRewardPointsTitleLabel.setHorizontalAlignment(SwingConstants.LEFT);
		expiringRewardPointsTitleLabel.setFont(new Font("Verdana", Font.BOLD, 15));
		expiringRewardPointsTitleLabel.setBounds(33, 422, 159, 28);
		add(expiringRewardPointsTitleLabel);
		
		JScrollPane rewardsPointScrollPane = new JScrollPane();
		
		rewardsPointScrollPane.setBounds(33, 461, 618, 119);
		add(rewardsPointScrollPane);
		
		JLabel lblOverseasPrefix = new JLabel("You've spent ");
		lblOverseasPrefix.setHorizontalAlignment(SwingConstants.LEFT);
		lblOverseasPrefix.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblOverseasPrefix.setBounds(25, 155, 103, 40);
		add(lblOverseasPrefix);
		
		JLabel transactionsLabel = new JLabel("");
		transactionsLabel.setHorizontalAlignment(SwingConstants.LEFT);
		transactionsLabel.setIcon(new ImageIcon(LoginSummaryPanel.class.getResource("/ProjectApp/images/transactions.png")));
		transactionsLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
		transactionsLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
		transactionsLabel.setBackground(Color.LIGHT_GRAY);
		transactionsLabel.setBounds(461, 155, 29, 40);
		add(transactionsLabel);
		
		JLabel lblOverseasPostfix = new JLabel("overseas this month. Click        to view the transactions.");
		lblOverseasPostfix.setHorizontalAlignment(SwingConstants.LEFT);
		lblOverseasPostfix.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblOverseasPostfix.setBounds(279, 155, 404, 40);
		add(lblOverseasPostfix);
		
		transactionsLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				JPanel contentPane = new OverseasTransaction(myFrame,loggedIn);
				myFrame.setContentPane(contentPane);
				myFrame.setVisible(true);
				
			}
		});
		
		viewAnnualPerformanceLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				GregorianCalendar g1 = new GregorianCalendar();
				
				JPanel contentPane = new AnnualPerformancePanel(mf,g1.get(GregorianCalendar.YEAR),l1);
				myFrame.setContentPane(contentPane);
				myFrame.setVisible(true);
				
			}
		});
		
		overseasuseTable = new JTable();
		
		overseasuseTable.setSurrendersFocusOnKeystroke(true);
		overseasuseTable.setRowSelectionAllowed(false);
		overseasuseTable.setFillsViewportHeight(true);
		overseasuseTable.setFont(new Font("Verdana", Font.PLAIN, 11));
		overseasuseScrollPane.setColumnHeaderView(overseasuseTable);
		
		
		rewardsTable = new JTable();
		rewardsTable.setFont(new Font("Verdana", Font.PLAIN, 11));
		rewardsTable.setFillsViewportHeight(true);
		rewardsTable.setColumnSelectionAllowed(true);
		rewardsTable.setCellSelectionEnabled(true);
		rewardsPointScrollPane.setColumnHeaderView(rewardsTable);		
		
		//l1 var from previous page
		ArrayList<CardDetails> cdList = CardDetailsDA.retrieveCardDetailsBySingpassID(l1.getSingpassID());
		ArrayList<Transaction> tList = new ArrayList<Transaction>();
		for(int i = 0 ; i < cdList.size() ; i++){
			
			ArrayList<Transaction> tempList = TransactionDA.retrieveTransactionByCardNumber(cdList.get(i).getCardNumber());
			
			for(int j = 0 ; j < tempList.size() ; j++){
				
				tList.add(tempList.get(j));
				
			}
		
		}
		
			// setting transactions labels 
			String monthYearCurrent = setTransactionsLabels(tList);
			
			
			double totalOverseasAmt = 0;
			for(int i = 0 ; i < tList.size() ; i++){
				
				String transDate = (tList.get(i).getTransactionDate()).substring(3,tList.get(i).getTransactionDate().length());
				if(	transDate.equals(monthYearCurrent) && tList.get(i).getTransactionCurrency() != null  ){
					
					totalOverseasAmt +=tList.get(i).getTransactionAmount();
					
					}
			
		}
		
		
			
		ArrayList<CardDetails> returnedResult = setOverseasTableModelFromDB(l1);
		overseasuseScrollPane.setViewportView(overseasuseTable);
		overseasuseScrollPane.setColumnHeaderView(overseasuseTable.getTableHeader());
		
	
		overseasuseTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
						/*
						if(overseasTable.getSelectedColumn() == 3){
							
							ArrayList<CardDetails> updateList = getUpdatedCopyCardDetails(l1);
							int rowselected = overseasTable.getSelectedRow();

							JPanel contentPane = new (myFrame,l1,updateList.get(rowselected).getCardNumber());
							myFrame.setContentPane(contentPane);
							myFrame.setVisible(true);
							
						}
						 */
					
						if(overseasuseTable.getSelectedColumn() == 2){
							
							ArrayList<CardDetails> updateList = getUpdatedCopyCardDetails(l1);						
							int rowselected = overseasuseTable.getSelectedRow();
							
							if(rowselected >=0){
								
								int response = 	JOptionPane.showConfirmDialog(myFrame, "Are you sure you want to deactivate Card No: "+updateList.get(rowselected).getCardNumber() +" ?");
								if(response == 0){
									updateList.get(rowselected).setActivationStatus(0);
									updateList.get(rowselected).setStartDate("");
									updateList.get(rowselected).setEndDate("");
									
									
									if(CardDetailsDA.updateCardDetails(updateList.get(rowselected))){	
										
										
										overseasuseTable.clearSelection();		
										setOverseasTableModelFromDB(l1);
										JOptionPane.showMessageDialog(myFrame, "Successfully deactivated Card number: "+ updateList.get(rowselected).getCardNumber(),"Alert",JOptionPane.INFORMATION_MESSAGE );
									
									}
									else{
										
										JOptionPane.showMessageDialog(myFrame, "Deactivation Failed.\nPlease try again later.","Alert",JOptionPane.ERROR_MESSAGE );
									
									}
								
								}
							}

							
						}
	
					}
			
			}
		);
		
		
		
	
	
			DecimalFormat df = new DecimalFormat("0.00");
			overseasuseAmountLabel.setText("SGD $"+df.format(totalOverseasAmt));
			rewardsPointScrollPane.setViewportView(rewardsTable);
			rewardsPointScrollPane.setColumnHeaderView(rewardsTable.getTableHeader());
		
			setRewardsTableModelFromDB(l1);
			rewardsTable.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					
				if(rewardsTable.getSelectedColumn() == 3){
					
					ArrayList<CardDetails> results = CardDetailsDA.retrieveCardDetailsBySingpassID(l1.getSingpassID());
					CardDetails selectedRow = results.get(rewardsTable.getSelectedRow());
					String bankName = selectedRow.getBankName();
					JPanel contentPane = new JPanel();
					
					if(bankName.equalsIgnoreCase("DBS")){
						contentPane = new DBSRewardMainPanel(myFrame,selectedRow.getCardNumber(),loggedIn);
					}	
						else if(bankName.equalsIgnoreCase("OCBC")){
							contentPane = new OCBCRewardMainPanel(myFrame,selectedRow.getCardNumber(),loggedIn);
						}
						else{
							
							contentPane = new UOBRewardMainPanel(myFrame,selectedRow.getCardNumber(),loggedIn);
						}
					
					myFrame.setContentPane(contentPane);
					myFrame.setVisible(true);
				}
		
					
				}
			});
			
		
			overseasuseTable.getColumnModel().getColumn(0).setMinWidth(30);
			overseasuseTable.setRowHeight(22);
			rewardsTable.getColumnModel().getColumn(0).setMinWidth(120);
			
			rewardsTable.getColumnModel().getColumn(3).setMinWidth(120);
			rewardsTable.setRowHeight(22);
			
			rewardsTable.getTableHeader().setReorderingAllowed(false);
			overseasuseTable.getTableHeader().setReorderingAllowed(false);
			
			cardDetailsTable.setRowHeight(22);
			cardDetailsTable.getTableHeader().setReorderingAllowed(false);
			cardDetailsTable.setAutoCreateRowSorter(true);
			
			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment( JLabel.CENTER );
			
			
			for(int i = 0 ; i < rewardsTable.getColumnCount()-1 ; i++){
			
					rewardsTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
				
			}
			for(int i = 0 ; i < overseasuseTable.getColumnCount()-1 ; i++){
				overseasuseTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);		
			}
			
						
		
			cardDetailsPopout.setVisible(false);
			
			
			
			// end of constructor body 
			
		
	}
	
	// start of methods 
	
	private String[] getMonths(){
		
		
		GregorianCalendar g1 = new GregorianCalendar();
		String monthYearCurrent,monthYearOne, monthYearTwo;
		int currentMonth, oneMonthBefore,twoMonthBefore;
		
		
		if(g1.get(GregorianCalendar.MONTH) <2){
			
			if(g1.get(GregorianCalendar.MONTH) == 1){
				
				currentMonth = g1.get(GregorianCalendar.MONTH);
				oneMonthBefore = 0 ;
				twoMonthBefore = 11;
				monthYearCurrent = "0"+(currentMonth+1) + "/"+Integer.toString(g1.get(GregorianCalendar.YEAR)); 
				monthYearOne = "0"+(oneMonthBefore+1) +"/"+ Integer.toString(g1.get(GregorianCalendar.YEAR));
				monthYearTwo = (twoMonthBefore+1)+ "/" + Integer.toString(g1.get(GregorianCalendar.YEAR)-1);
				
			}
			else{
				
				currentMonth = g1.get(GregorianCalendar.MONTH);
				oneMonthBefore =  11 ;
				twoMonthBefore = 10;
				monthYearCurrent = "0"+(currentMonth+1) + "/"+Integer.toString(g1.get(GregorianCalendar.YEAR)); 
				monthYearOne = (oneMonthBefore+1) +"/"+ Integer.toString(g1.get(GregorianCalendar.YEAR)-1);
				monthYearTwo = (twoMonthBefore+1)+ "/" + Integer.toString(g1.get(GregorianCalendar.YEAR)-1);
				
				
			}
			
		}
		
		else{	
			currentMonth = g1.get(GregorianCalendar.MONTH);
			oneMonthBefore = g1.get(GregorianCalendar.MONTH) - 1;
			twoMonthBefore = g1.get(GregorianCalendar.MONTH) - 2;
			if((currentMonth+1) < 10)
			monthYearCurrent ="0" +(currentMonth+1) + "/"+Integer.toString(g1.get(GregorianCalendar.YEAR)); 
			else
			monthYearCurrent = (currentMonth+1) + "/"+Integer.toString(g1.get(GregorianCalendar.YEAR));
			
			
			if((oneMonthBefore+1)<10)
				monthYearOne = "0"+(oneMonthBefore+1) +"/"+ Integer.toString(g1.get(GregorianCalendar.YEAR));
			else
				monthYearOne = (oneMonthBefore+1) +"/"+ Integer.toString(g1.get(GregorianCalendar.YEAR));
				
			
			if((twoMonthBefore+1)<10)
				monthYearTwo = "0"+(twoMonthBefore+1)+ "/" + Integer.toString(g1.get(GregorianCalendar.YEAR));
			else
				monthYearTwo = (twoMonthBefore+1)+ "/" + Integer.toString(g1.get(GregorianCalendar.YEAR));
			
		}

		String [] threeMonths = {monthYearCurrent,monthYearOne,monthYearTwo};
		
		
		
		return threeMonths;
		
	}
	
	private ArrayList<CardPopout> retrieveTableDetails( String labelName , Login l1){
		
		GregorianCalendar g1 = new GregorianCalendar();
	
		int currentMonth, oneMonthBefore,twoMonthBefore;
		String monthYearCurrent,monthYearOne, monthYearTwo;
		
		
		if(g1.get(GregorianCalendar.MONTH) <2){
			
			if(g1.get(GregorianCalendar.MONTH) == 1){
				
				currentMonth = g1.get(GregorianCalendar.MONTH);
				oneMonthBefore = 0 ;
				twoMonthBefore = 11;
				monthYearCurrent = "0"+(currentMonth+1) + "/"+Integer.toString(g1.get(GregorianCalendar.YEAR)); 
				monthYearOne = "0"+(oneMonthBefore+1) +"/"+ Integer.toString(g1.get(GregorianCalendar.YEAR));
				monthYearTwo = (twoMonthBefore+1)+ "/" + Integer.toString(g1.get(GregorianCalendar.YEAR)-1);
				
			}
			else{
				
				currentMonth = g1.get(GregorianCalendar.MONTH);
				oneMonthBefore =  11 ;
				twoMonthBefore = 10;
				monthYearCurrent = "0"+(currentMonth+1) + "/"+Integer.toString(g1.get(GregorianCalendar.YEAR)); 
				monthYearOne = (oneMonthBefore+1) +"/"+ Integer.toString(g1.get(GregorianCalendar.YEAR)-1);
				monthYearTwo = (twoMonthBefore+1)+ "/" + Integer.toString(g1.get(GregorianCalendar.YEAR)-1);
				
				
			}
			
		}
		
		else{	
			currentMonth = g1.get(GregorianCalendar.MONTH);
			oneMonthBefore = g1.get(GregorianCalendar.MONTH) - 1;
			twoMonthBefore = g1.get(GregorianCalendar.MONTH) - 2;
			if((currentMonth+1) < 10)
			monthYearCurrent ="0" +(currentMonth+1) + "/"+Integer.toString(g1.get(GregorianCalendar.YEAR)); 
			else
			monthYearCurrent = (currentMonth+1) + "/"+Integer.toString(g1.get(GregorianCalendar.YEAR));
			
			
			if((oneMonthBefore+1)<10)
				monthYearOne = "0"+(oneMonthBefore+1) +"/"+ Integer.toString(g1.get(GregorianCalendar.YEAR));
			else
				monthYearOne = (oneMonthBefore+1) +"/"+ Integer.toString(g1.get(GregorianCalendar.YEAR));
				
			
			if((twoMonthBefore+1)<10)
				monthYearTwo = "0"+(twoMonthBefore+1)+ "/" + Integer.toString(g1.get(GregorianCalendar.YEAR));
			else
				monthYearTwo = (twoMonthBefore+1)+ "/" + Integer.toString(g1.get(GregorianCalendar.YEAR));
			
		}
		String datePassed = "";
		if(labelName.equals("current")){
			datePassed = monthYearCurrent;
		}
		else if(labelName.equals("second")){
			datePassed = monthYearOne;
		}
		
		else{
			datePassed = monthYearTwo;
		}
		
		
		ArrayList<CardPopout> totalList = new ArrayList<CardPopout>();
		ArrayList<CardDetails> cdList = CardDetailsDA.retrieveCardDetailsBySingpassID(l1.getSingpassID());
		for(int i = 0 ; i < cdList.size() ; i++ ){
			double amountSpent = 0;
			amountSpent = TransactionDA.retrieveSumOfTransactionByCardNumberAndMonth("%"+datePassed, cdList.get(i).getCardNumber());
			
			CardPopout cardpop = new CardPopout(cdList.get(i).getCardType(),cdList.get(i).getCardNumber(),amountSpent);
			
			if(amountSpent > 0 ){
			totalList.add(cardpop);
			}
				
				
		}
		
		return totalList;
		
		
	}
	private String setTransactionsLabels(ArrayList<Transaction> tList){
		
		String [] months = {"January" , "February" , "March" , "April", "May",
		        "June", "July", "August", "September", "October",
		        "November", "December" };
		
		GregorianCalendar g1 = new GregorianCalendar();

		int currentMonth, oneMonthBefore,twoMonthBefore;
		String monthYearCurrent,monthYearOne, monthYearTwo;
		
		
		if(g1.get(GregorianCalendar.MONTH) <2){
			
			if(g1.get(GregorianCalendar.MONTH) == 1){
				
				currentMonth = g1.get(GregorianCalendar.MONTH);
				oneMonthBefore = 0 ;
				twoMonthBefore = 11;
				monthYearCurrent = "0"+(currentMonth+1) + "/"+Integer.toString(g1.get(GregorianCalendar.YEAR)); 
				monthYearOne = "0"+(oneMonthBefore+1) +"/"+ Integer.toString(g1.get(GregorianCalendar.YEAR));
				monthYearTwo = (twoMonthBefore+1)+ "/" + Integer.toString(g1.get(GregorianCalendar.YEAR)-1);
				
			}
			else{
				
				currentMonth = g1.get(GregorianCalendar.MONTH);
				oneMonthBefore =  11 ;
				twoMonthBefore = 10;
				monthYearCurrent = "0"+(currentMonth+1) + "/"+Integer.toString(g1.get(GregorianCalendar.YEAR)); 
				monthYearOne = (oneMonthBefore+1) +"/"+ Integer.toString(g1.get(GregorianCalendar.YEAR)-1);
				monthYearTwo = (twoMonthBefore+1)+ "/" + Integer.toString(g1.get(GregorianCalendar.YEAR)-1);
				
				
			}
			
		}
		
		else{	
			currentMonth = g1.get(GregorianCalendar.MONTH);
			oneMonthBefore = g1.get(GregorianCalendar.MONTH) - 1;
			twoMonthBefore = g1.get(GregorianCalendar.MONTH) - 2;
			if((currentMonth+1) < 10)
			monthYearCurrent ="0" +(currentMonth+1) + "/"+Integer.toString(g1.get(GregorianCalendar.YEAR)); 
			else
			monthYearCurrent = (currentMonth+1) + "/"+Integer.toString(g1.get(GregorianCalendar.YEAR));
			
			
			if((oneMonthBefore+1)<10)
				monthYearOne = "0"+(oneMonthBefore+1) +"/"+ Integer.toString(g1.get(GregorianCalendar.YEAR));
			else
				monthYearOne = (oneMonthBefore+1) +"/"+ Integer.toString(g1.get(GregorianCalendar.YEAR));
				
			
			if((twoMonthBefore+1)<10)
				monthYearTwo = "0"+(twoMonthBefore+1)+ "/" + Integer.toString(g1.get(GregorianCalendar.YEAR));
			else
				monthYearTwo = (twoMonthBefore+1)+ "/" + Integer.toString(g1.get(GregorianCalendar.YEAR));
			
		}
		
		currentMonthLabel.setText(months[currentMonth]);
		monthOneLabel.setText(months[oneMonthBefore]);
		monthTwoLabel.setText(months[twoMonthBefore]);
		
		double totalCurrentMonth =0;
		double totalMonthOne = 0;
		double totalMonthTwo = 0;
		
	
		for(int i = 0 ; i < tList.size() ; i++){
			String transDate = (tList.get(i).getTransactionDate()).substring(3,tList.get(i).getTransactionDate().length());
			
			if(	transDate.equals(monthYearCurrent) ){
					totalCurrentMonth +=tList.get(i).getTransactionAmount();
				}
			
			else if(transDate.equals(monthYearOne)){
					totalMonthOne += tList.get(i).getTransactionAmount();
				
			}	
			else if(transDate.equals(monthYearTwo)){
				
				totalMonthTwo += tList.get(i).getTransactionAmount();
				
			}		
				
		}
		
		DecimalFormat df = new DecimalFormat("0.00");
		
		currentMonthSpendings.setText("SGD $"+ df.format(totalCurrentMonth));
		monthOneSpendings.setText("SGD $"+df.format(totalMonthOne));
		monthTwoSpendings.setText("SGD $"+df.format(totalMonthTwo));

		
		return monthYearCurrent;
		
	}
	

	
	private ArrayList<CardDetails> setRewardsTableModelFromDB(Login log) {
		ArrayList<CardDetails> results = CardDetailsDA.retrieveCardDetailsBySingpassID(log.getSingpassID());
		LoginSummaryPointsTableModel model = new LoginSummaryPointsTableModel(results);
		rewardsTable.setModel(model);		
		 return results;
	}
	
	
	private ArrayList<CardDetails> getUpdatedCopyCardDetails(Login log){
		
		ArrayList<CardDetails> result = CardDetailsDA.retrieveCardDetailsBySingpassID(log.getSingpassID());
		ArrayList<CardDetails> filteredResults = new ArrayList<CardDetails>();
		for(int i = 0 ; i < result.size() ; i++){
			
				if(result.get(i).getActivationStatus() == 1){
					filteredResults.add(result.get(i));
				}
			
		}
		
		return filteredResults;
	}
	
	
	private ArrayList<CardDetails> setOverseasTableModelFromDB(Login log) {
	
		ArrayList<CardDetails> result = CardDetailsDA.retrieveCardDetailsBySingpassID(log.getSingpassID());
		ArrayList<CardDetails> filteredResults = new ArrayList<CardDetails>();
		for(int i = 0 ; i < result.size() ; i++){
			
			if(result.get(i).getActivationStatus() == 1){
				filteredResults.add(result.get(i));
				
			}
			
		}
		
		LoginSummaryOverseasTableModel model = new LoginSummaryOverseasTableModel(filteredResults);
		overseasuseTable.setModel(model);
		 return filteredResults;
	}
	
	private ArrayList<CardPopout> setTransactionTableModelFromDB( ArrayList<CardPopout> tList , String month) {
				if(tList.size()>=1){
		LoginSummaryCardsTableModel model = new LoginSummaryCardsTableModel(tList);
		
		cardDetailsTable.setModel(model);
		cardDetailsPopout.setVisible(true);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		for(int i = 0 ; i < cardDetailsTable.getColumnCount(); i ++){
			cardDetailsTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);;
		}
		cardDetailsPopout.setTitle(month +" Card Spendings ");
				}
		return tList;
		
	}
	
	
	private int monthIndex(String monthSelected){
		
		String [] months = {"January" , "February" , "March" , "April", "May",
		        "June", "July", "August", "September", "October",
		        "November", "December" };
		
		int index = 0;
		
		for(int i = 0 ; i < months.length ; i ++){
			
			if(months[i].equalsIgnoreCase(monthSelected)){
				
				index = i;
			}
			
		}
		return index;
	}
	
}
