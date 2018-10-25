package teamCreditProjectApp.ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;

import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import teamCreditProjectApp.dataAccess.CardDetailsDA;
import teamCreditProjectApp.entity.CardDetails;
import teamCreditProjectApp.entity.Login;

import com.mysql.jdbc.PreparedStatement;

import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class RedeemInfoPanel extends MasterPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public RedeemInfoPanel(JFrame mf, Login l1) {
		
		super(mf,l1);
		
		setLayout(null);
		
		JLabel lblCreditCardNo = new JLabel("Credit Card No:");
		lblCreditCardNo.setBounds(206, 303, 173, 20);
		lblCreditCardNo.setFont(new Font("Arial", Font.PLAIN, 24));
		add(lblCreditCardNo);
		
		
		
		ArrayList<CardDetails> result = CardDetailsDA.retrieveCardDetailsBySingpassID(l1.getSingpassID());
		String [] stringResult = new String[result.size()];
		
			for(int i = 0; i<result.size();i++){
				
				stringResult[i]	= result.get(i).getCardNumber();
	
			}
		
		JComboBox comboBox_1 = new JComboBox(stringResult);
		comboBox_1.setBounds(410, 302, 248, 30);
		comboBox_1.setFont(new Font("Arial", Font.PLAIN, 20));
		comboBox_1.setMaximumRowCount(6);
		add(comboBox_1);	
		comboBox_1.setSelectedIndex(0);
		
		JButton btnSubmit_1 = new JButton("Submit");
		btnSubmit_1.setBounds(668, 300, 150, 36);
		add(btnSubmit_1);
	
		
		btnSubmit_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
		

		if((result.get(comboBox_1.getSelectedIndex()).getBankName() ).equalsIgnoreCase("DBS")){
			
			
			JPanel contentPane = new DBSRewardMainPanel(myFrame, result.get(comboBox_1.getSelectedIndex()).getCardNumber(),l1);
		myFrame.setContentPane(contentPane);
		myFrame.setVisible(true);
			
		
		}else if( (result.get(comboBox_1.getSelectedIndex()).getBankName() ).equalsIgnoreCase("OCBC")){
			JPanel contentPane = new OCBCRewardMainPanel(myFrame, result.get(comboBox_1.getSelectedIndex()).getCardNumber(),l1);
			myFrame.setContentPane(contentPane);
			myFrame.setVisible(true);
				
		}else{
			
			JPanel contentPane = new UOBRewardMainPanel(myFrame, result.get(comboBox_1.getSelectedIndex()).getCardNumber(),l1);
			myFrame.setContentPane(contentPane);
			myFrame.setVisible(true);
			
		}
			
		
		
			}
		});

	}}
