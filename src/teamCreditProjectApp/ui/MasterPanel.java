package teamCreditProjectApp.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import teamCreditProjectApp.dataAccess.CardDetailsDA;
import teamCreditProjectApp.dataAccess.TransactionDA;
import teamCreditProjectApp.entity.Login;
import teamCreditProjectApp.entity.RewardsCart;
import teamCreditProjectApp.entity.CardDetails;
import teamCreditProjectApp.entity.Transaction;


import javax.swing.JSeparator;
import java.awt.SystemColor;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class MasterPanel extends JPanel {

	protected static ArrayList<RewardsCart> aList = new ArrayList<RewardsCart>();

	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	protected JFrame myFrame = null;
	protected Login loggedIn = null;
	protected JLabel nameLabel;
	
	
	public MasterPanel(JFrame mf,Login l1) {

		myFrame = mf;
		loggedIn = l1;
		nameLabel = new JLabel("");

		nameLabel.setBounds(755, 78, 170, 22);
		add(nameLabel);
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nameLabel.setForeground(Color.WHITE);
		nameLabel.setFont(new Font("Verdana", Font.BOLD, 15));
		
		JLabel profilePictureLabel = new JLabel("");
		profilePictureLabel.setIcon(new ImageIcon(MasterPanel.class.getResource("/ProjectApp/images/profilepicdefault.png")));
		profilePictureLabel.setHorizontalAlignment(SwingConstants.CENTER);
		profilePictureLabel.setBackground(Color.WHITE);
		profilePictureLabel.setBounds(807, 8, 70, 70);
		add(profilePictureLabel);
		profilePictureLabel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);			
		panel.setVisible(false);
		JPanel navPanel = new JPanel();
		
		navPanel.setLayout(null);
		navPanel.setVisible(false);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(MasterPanel.class.getResource("/CreditLimiter/logo.png")));
		lblNewLabel.setBounds(458, 8, 123, 92);
		add(lblNewLabel);
		
		navPanel.setBackground(new Color(51, 153, 204));
		navPanel.setBounds(0, 0, 248, 624);
		add(navPanel);
		
		setBackground(new Color(255, 255, 255));
		setBounds(0, 0, 990, 650);
		setLayout(null);
		JButton navbarButton = new JButton("");
		navbarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				navPanel.setVisible(true);
				panel.setVisible(true);
				
			}
		});
		navbarButton.setBounds(39, 39, 45, 45);
		add(navbarButton);
		navbarButton.setIcon(new ImageIcon(MasterPanel.class.getResource("/ProjectApp/images/menu-icon-resized.png")));
		navbarButton.setOpaque(false);
		navbarButton.setBorder(null);
		
		JLabel headerBackground = new JLabel("");
		headerBackground.setBackground(new Color(51, 102, 204));
		headerBackground.setOpaque(true);
		
		headerBackground.setBounds(0, 0, 990, 100);
		add(headerBackground);
		
		if(myFrame != null && loggedIn != null ){
			
		
		
			nameLabel.setText(l1.getUserName());
		
		
		
	
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			navPanel.setVisible(false);
			panel.setVisible(false);
			
			}
		});
		panel.setBounds(251, 0, 739, 624);
		add(panel);
		panel.setOpaque(false);
		
		
		
		
		
		JButton summaryNav = new JButton("");
		summaryNav.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				JPanel contentPane = new LoginSummaryPanel(myFrame,loggedIn);
				myFrame.setContentPane(contentPane);
				myFrame.setVisible(true);
				
			
			}
		});
		summaryNav.setIcon(new ImageIcon(MasterPanel.class.getResource("/ProjectApp/images/summary.png")));
		summaryNav.setBackground(SystemColor.text);
		summaryNav.setBounds(10, 143, 240, 60);
		summaryNav.setOpaque(false);
		summaryNav.setBorder(null);
		navPanel.add(summaryNav);
		
		JButton cardDetailsNav = new JButton("");
		cardDetailsNav.setIcon(new ImageIcon(MasterPanel.class.getResource("/ProjectApp/images/carddetails.png")));
		cardDetailsNav.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				GregorianCalendar g1 = new GregorianCalendar();
				
				JPanel contentPane = new CardDetailsPanel(mf,loggedIn, "" , g1.get(GregorianCalendar.MONTH));
				mf.setContentPane(contentPane);
				mf.setVisible(true);
				
			}
		});
		cardDetailsNav.setBounds(10, 203, 240, 60);
		cardDetailsNav.setBackground(SystemColor.text);
		cardDetailsNav.setOpaque(false);
		cardDetailsNav.setBorder(null);
		
		navPanel.add(cardDetailsNav);
		
		JButton performanceNav = new JButton("");
		performanceNav.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GregorianCalendar g1 = new GregorianCalendar();
				
				JPanel contentPane = new AnnualPerformancePanel(mf,g1.get(GregorianCalendar.YEAR),loggedIn);
				myFrame.setContentPane(contentPane);
				myFrame.setVisible(true);
				
			}
		});
		performanceNav.setIcon(new ImageIcon(MasterPanel.class.getResource("/ProjectApp/images/performance.png")));
		performanceNav.setBounds(10, 263, 240, 60);
		performanceNav.setBackground(SystemColor.text);
		performanceNav.setOpaque(false);
		performanceNav.setBorder(null);
		
		navPanel.add(performanceNav);
		
		JButton overseasuseNav = new JButton("");
		overseasuseNav.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				JPanel contentPane = new CardDeactivationSelection(myFrame,loggedIn);
				myFrame.setContentPane(contentPane);
				myFrame.setVisible(true);
				
			}
		});
		
		overseasuseNav.setIcon(new ImageIcon(MasterPanel.class.getResource("/ProjectApp/images/overseasuse.png")));
		overseasuseNav.setBounds(10, 383, 240, 60);
		overseasuseNav.setBackground(SystemColor.text);
		overseasuseNav.setOpaque(false);
		overseasuseNav.setBorder(null);
		
		navPanel.add(overseasuseNav);
		
		JButton signoutNav = new JButton("");
		signoutNav.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		
			JPanel contentPane = new LoginPanel(myFrame);
			myFrame.setContentPane(contentPane);
			myFrame.setVisible(true);
		
				
			}
		});
		signoutNav.setIcon(new ImageIcon(MasterPanel.class.getResource("/ProjectApp/images/sign-out.png")));
		signoutNav.setBounds(10, 442, 240, 50);
		signoutNav.setBackground(SystemColor.text);
		signoutNav.setOpaque(false);
		signoutNav.setBorder(null);
		
		navPanel.add(signoutNav);
		
		JButton rewardsNav = new JButton("");
		rewardsNav.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				JPanel contentPane = new RedeemInfoPanel(myFrame,loggedIn);
				myFrame.setContentPane(contentPane);
				myFrame.setVisible(true);
				
			}
		});
		rewardsNav.setIcon(new ImageIcon(MasterPanel.class.getResource("/ProjectApp/images/rewards.png")));
		rewardsNav.setBackground(Color.WHITE);
		rewardsNav.setBounds(10, 323, 240, 60);
		rewardsNav.setOpaque(false);
		rewardsNav.setBorder(null);
		navPanel.add(rewardsNav);
		
		
		
		
		
		navbarButton.setBackground(SystemColor.menu);
	
		
		
		
		
	
	
		
	
		
	}
	
	}
		
		public void refreshRewardsTransactionDatabase(Login l1){
			
			ArrayList<CardDetails> usersCards = CardDetailsDA.retrieveCardDetailsBySingpassID(l1.getSingpassID()); 
			GregorianCalendar g1 = new GregorianCalendar();
			int pointsExpiring = 0 ;

			
			for(int i = 0 ; i < usersCards.size() ; i++){
				
				int rewardsComparingMonth  = Integer.parseInt(usersCards.get(i).getExpiringRewardPointsDate().substring(3,5));
				int rewardsComparingYear = Integer.parseInt(usersCards.get(i).getExpiringRewardPointsDate().substring(6,10));
				
				int currentMonth = g1.get(GregorianCalendar.MONTH) + 1 ;
				int currentYear = g1.get(GregorianCalendar.YEAR) ;
				
			if(!(((rewardsComparingYear > currentYear) && (rewardsComparingMonth <= currentMonth))  || ((rewardsComparingYear == currentYear) && (rewardsComparingMonth > currentMonth))) ){
				
				while((rewardsComparingYear != currentYear) || (rewardsComparingMonth != currentMonth)){
				
					
					
					if(currentYear > rewardsComparingYear){
						
						if(rewardsComparingMonth == 12){
							
							rewardsComparingYear++;
							rewardsComparingMonth = 1;
						
						}
						else{
							
							rewardsComparingMonth++;
					
						}
						
						String updatedMonth;
						String nextMonthExpiryDate;
						if(rewardsComparingMonth <10){
							
							updatedMonth = "01/0"+rewardsComparingMonth+"/"+rewardsComparingYear;
							nextMonthExpiryDate =  "/0"+rewardsComparingMonth+"/"+(rewardsComparingYear-1);
						}
						else{
							
							updatedMonth = "01/"+rewardsComparingMonth+"/"+rewardsComparingYear;
							nextMonthExpiryDate =  "/"+rewardsComparingMonth+"/"+(rewardsComparingYear-1);
						}
				
						int points = usersCards.get(i).getRewardPoints() - usersCards.get(i).getExpiringRewardPoints();
						double amountSpent = TransactionDA.retrieveSumOfTransactionByCardNumberAndMonth("%"+nextMonthExpiryDate,usersCards.get(i).getCardNumber());
						System.out.println("amtspent" + amountSpent);
						int expiringPointsNextMonth = 0 ;
						if((usersCards.get(i).getBankName()).equalsIgnoreCase("dbs") || (usersCards.get(i).getBankName()).equalsIgnoreCase("uob")){
							
							expiringPointsNextMonth = (int)(amountSpent/5);
							
						}
						else{
							
							expiringPointsNextMonth = (int)amountSpent;
						}
						
						CardDetails updatedCopy = usersCards.get(i);
						updatedCopy.setExpiringRewardPoints(expiringPointsNextMonth);
						updatedCopy.setRewardPoints(points);
						updatedCopy.setMonth(updatedMonth);
						System.out.println("expiringPointsNextMonth" + expiringPointsNextMonth);
						CardDetailsDA.updateCardDetails(updatedCopy);
						
						
					}		
						else if((currentYear == rewardsComparingYear) && (currentMonth > rewardsComparingMonth)){
					
						rewardsComparingMonth++;	
						String updatedMonth;
						String nextMonthExpiryDate;
	
						if(rewardsComparingMonth <10){
							
							updatedMonth = "01/0"+rewardsComparingMonth+"/"+rewardsComparingYear;
							nextMonthExpiryDate =  "/0"+rewardsComparingMonth+"/"+(rewardsComparingYear-1);
						}
						else{
							
							updatedMonth = "01/"+rewardsComparingMonth+"/"+rewardsComparingYear;
							nextMonthExpiryDate =  "/"+rewardsComparingMonth+"/"+(rewardsComparingYear-1);
						}
						
						int points = usersCards.get(i).getRewardPoints() - usersCards.get(i).getExpiringRewardPoints();
						double amountSpent = TransactionDA.retrieveSumOfTransactionByCardNumberAndMonth("%"+nextMonthExpiryDate,usersCards.get(i).getCardNumber());
						System.out.println("amtspent" + amountSpent);
						int expiringPointsNextMonth = 0 ;
						
						if((usersCards.get(i).getBankName()).equalsIgnoreCase("dbs") || (usersCards.get(i).getBankName()).equalsIgnoreCase("uob")){
							
							expiringPointsNextMonth = (int)(amountSpent/5);
							
						}
						else{
							
							expiringPointsNextMonth = (int)amountSpent;
						}
						
						System.out.println("expiringPointsNextMonth" + expiringPointsNextMonth);
						CardDetails updatedCopy = usersCards.get(i);
						updatedCopy.setExpiringRewardPoints(expiringPointsNextMonth);
						updatedCopy.setRewardPoints(points);
						updatedCopy.setMonth(updatedMonth);
						CardDetailsDA.updateCardDetails(updatedCopy);
					}
			
				}	
				
			}
			
			else{
				
				String thisMonth = "";
				
				if(currentMonth < 10)
					thisMonth =  "01/0"+ currentMonth + "/"+currentYear; 
				else
					thisMonth =  "01/"+ currentMonth + "/"+currentYear; 
				
				CardDetails updatedCopy = usersCards.get(i);
				updatedCopy.setMonth(thisMonth);
				CardDetailsDA.updateCardDetails(updatedCopy);
				
				
			}
				
		}
		
		
	}
		
		
		public void deactivatedCards(Login l1){
			
			ArrayList<CardDetails> cardList = CardDetailsDA.retrieveCardDetailsBySingpassID(l1.getSingpassID());
			
			if(cardList.size()>0 ){
				
			
			for(int i = 0 ; i <cardList.size(); i++){
				
				if(cardList.get(i).getEndDate() != null){
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				GregorianCalendar g1 = new GregorianCalendar();
				int currentMonth = g1.get(GregorianCalendar.MONTH) + 1 ;
				int currentYear = g1.get(GregorianCalendar.YEAR);
				int currentDay = g1.get(GregorianCalendar.DAY_OF_MONTH);
				String ddmmyyyy = "";
				if(currentMonth <10 && currentDay <10 ){					
					ddmmyyyy ="0"+currentDay+ "/0" +currentMonth+ "/" + currentYear;
				}
				else if(currentMonth <10){
					ddmmyyyy = ""+currentDay+ "/0" +currentMonth+ "/" + currentYear;
				}
				else if(currentDay <10){
					ddmmyyyy = "0"+currentDay+ "/" +currentMonth+ "/" + currentYear;
				}
				
				try{
					
					Date d1 = format.parse(ddmmyyyy);
					Date d2 = format.parse(cardList.get(i).getEndDate());
					if( d1.after(d2) ){
						
						cardList.get(i).setActivationStatus(0);
						cardList.get(i).setStartDate("");
						cardList.get(i).setEndDate("");
						
						
						if(CardDetailsDA.updateCardDetails(cardList.get(i))){
						
							System.out.println("success !!!!!!! ");
						
						}
					}
				}
				
				catch(ParseException e){
					
					System.out.print("Date Parse Error !");
					
				}
				
				/*
				String cardEndDate = cardList.get(i).getEndDate();
				int dayOfEnd = Integer.parseInt(cardEndDate.substring(0, 2));
				int monthOfEnd = Integer.parseInt(cardEndDate.substring(3, 5));
				int yearOfEnd = Integer.parseInt(cardEndDate.substring(6, 10));		
				
				*/
				
				
				
			}
			
			}
			}
		}
}

	

