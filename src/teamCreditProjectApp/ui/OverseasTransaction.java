package teamCreditProjectApp.ui;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Panel;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import teamCreditProjectApp.dataAccess.CardDetailsDA;
import teamCreditProjectApp.dataAccess.TransactionDA;
import teamCreditProjectApp.entity.CardDetails;
import teamCreditProjectApp.entity.Login;
import teamCreditProjectApp.entity.Transaction;

import javax.swing.UIManager;
import java.awt.Choice;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import java.text.*;
import java.awt.print.*;

public class OverseasTransaction extends MasterPanel {
	private JTable table;
private JLabel totalAmount;
	/**
	 * Create the panel.
	 */
	public OverseasTransaction(JFrame mf,Login l1) {
		super(mf,l1);
		setLayout(null);
		
		JButton backButton = new JButton("");
		backButton.setIcon(new ImageIcon(OverseasTransaction.class.getResource("/teamCreditProjectApp/images/back icon.png")));
		backButton.setFont(new Font("Arial", Font.PLAIN, 18));
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JPanel contentPane = new LoginSummaryPanel(myFrame,loggedIn);
				myFrame.setContentPane(contentPane);
				myFrame.setVisible(true);
			}
		});
		backButton.setBounds(41, 177, 39, 36);
		add(backButton);
		
		
		JLabel overseasTransactionLabel = new JLabel("Overseas Transaction");
		overseasTransactionLabel.setFont(new Font("Arial", Font.PLAIN, 25));
		overseasTransactionLabel.setBounds(378, 131, 264, 36);
		add(overseasTransactionLabel);
		
		JScrollPane overseasTransaction = new JScrollPane();
		overseasTransaction.setBounds(81, 177, 831, 359);
		add(overseasTransaction);
		
		table = new JTable();
		totalAmount = new JLabel("");
		totalAmount.setBounds(172, 545, 99, 26);
		add(totalAmount);
		setTableModelFromDB(l1);
		overseasTransaction.setViewportView(table);
		overseasTransaction.setColumnHeaderView(table.getTableHeader());
		
		JButton printer = new JButton("");
		printer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				MessageFormat header = new MessageFormat("Overseas Transaction");
				MessageFormat footer = new MessageFormat("Page{0,number,integer}");
				try{					
					table.print(JTable.PrintMode.NORMAL, header,footer);
				} catch (java.awt.print.PrinterException e){
					System.err.format("unable to print %s%n",e.getMessage());
				}
			}
		});
		printer.setIcon(new ImageIcon(OverseasTransaction.class.getResource("/teamCreditProjectApp/images/printer icon.png")));
		printer.setBounds(913, 500, 39, 36);
		add(printer);
		
		JLabel totalAmountLabel = new JLabel("Total Amount:");
		totalAmountLabel.setBounds(91, 545, 78, 26);
		add(totalAmountLabel);
		
		
		}
	
		private void setTableModelFromDB(Login l1) {
			
			GregorianCalendar today  = new GregorianCalendar();
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
			
	
			
			String todaysDate =   monthString + "/" + year;
			double totalTransactionAmount = 0;
			ArrayList<Transaction> newList = new ArrayList<Transaction>();
			ArrayList <CardDetails> list = CardDetailsDA.retrieveCardDetailsBySingpassID(l1.getSingpassID());
			
			
			
			for(int j = 0 ; j < list.size() ; j ++){
			ArrayList<Transaction> result = TransactionDA.retrieveTransactionByCardNumber(list.get(j).getCardNumber());
			
			
				for(int i = 0 ; i < result.size() ; i++){
					if(result.get(i).getTransactionCurrency() != null && (result.get(i).getTransactionDate().substring(3, 10)).equals(todaysDate) ){
					totalTransactionAmount += result.get(i).getTransactionAmount();
					newList.add(result.get(i));
					}
				}
			}
			
			totalAmount.setText("$ " + totalTransactionAmount);
			if(newList.size()!=0){
			OverseasTransactionTable model = new OverseasTransactionTable(newList);
			table.setModel(model);
			}
		}
}