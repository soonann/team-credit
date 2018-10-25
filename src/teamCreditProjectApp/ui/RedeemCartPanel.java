package teamCreditProjectApp.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import teamCreditProjectApp.dataAccess.CardDetailsDA;
import teamCreditProjectApp.dataAccess.RewardsCartDA;
import teamCreditProjectApp.entity.CardDetails;
import teamCreditProjectApp.entity.Login;
import teamCreditProjectApp.entity.RewardsCart;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JInternalFrame;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;



public class RedeemCartPanel extends MasterPanel {
	private JTable tblList;
	private int total = 0;
	private JTextArea textArea;
	/**
	 * Create the panel.
	 */
	public RedeemCartPanel(JFrame mf, Login l1,String cardNumber) {
		
		
		super(mf,l1);

		setLayout(null);
		
		for(int i = 0; i<aList.size();i++){
			
			String name = aList.get(i).getItemName();
			System.out.println(name);
			int quantity = aList.get(i).getQuantity();
			System.out.println(quantity);
			String points = aList.get(i).getItemPoints();
			System.out.println(points);
			

			}
		
		JInternalFrame internalFrame = new JInternalFrame("New JInternalFrame");
		internalFrame.setBounds(223, 219, 391, 332);
		add(internalFrame);
		internalFrame.setVisible(false);
		internalFrame.setClosable(true);
		
		JPanel panel = new JPanel();
		internalFrame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		textArea = new JTextArea();
		textArea.setBounds(77, 61, 208, 65);
		panel.add(textArea);
		
		ArrayList<CardDetails> pts = CardDetailsDA.retrieveAllCardDetails();
		textArea.setText(Integer.toString(pts.get(0).getRewardPoints()));
		

	
		for(int i = 0; i<aList.size();i++){
			String points = aList.get(i).getItemPoints();		
			int index = points.indexOf("Points");
			total += Integer.parseInt(points.substring(0, index-1));
			
			System.out.println("Your Points is: " + total);
			
			
			
		}
		
		JButton btnDone = new JButton("DONE");
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JPanel contentPane = new RedeemInfoPanel (myFrame, l1);
				myFrame.setContentPane(contentPane);
				myFrame.setVisible(true);
			}
		});
		btnDone.setBounds(218, 230, 115, 29);
		panel.add(btnDone);
		

		JLabel lblMyFavorite = new JLabel("Redeem Cart");
		lblMyFavorite.setBounds(15, 127, 245, 64);
		lblMyFavorite.setFont(new Font("Arial", Font.PLAIN, 30));
		lblMyFavorite.setIcon(new ImageIcon(RedeemCartPanel.class.getResource("/Redeem/img/Redeem Cart.png")));
		add(lblMyFavorite);

		JSeparator separator = new JSeparator();
		separator.setBounds(347, 67, 0, 2);
		add(separator);
		
		JLabel lblYourTotalPoints = new JLabel("Your Current Points: ");
		lblYourTotalPoints.setBounds(271, 203, 254, 29);
		lblYourTotalPoints.setFont(new Font("Arial", Font.PLAIN, 25));
		add(lblYourTotalPoints);
		
		JButton btnCheckOut = new JButton("CHECK OUT");
		btnCheckOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				deductPoints(cardNumber,total);
				
				internalFrame.setVisible(true);
				
			}
		});
		btnCheckOut.setBounds(709, 551, 119, 29);
		add(btnCheckOut);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(19, 260, 675, 320);
		add(scrollPane);
		

	
		tblList = new JTable();
		setTableModelFromDB();
		scrollPane.setViewportView(tblList);
		scrollPane.setColumnHeaderView(tblList.getTableHeader());
		
		JLabel lblRewardPoints = new JLabel("");
		lblRewardPoints.setFont(new Font("Arial", Font.PLAIN, 25));
		lblRewardPoints.setBounds(540, 212, 102, 20);
		add(lblRewardPoints);
		
		ArrayList<CardDetails> arr = CardDetailsDA.retrieveAllCardDetails();
		lblRewardPoints.setText(Integer.toString(arr.get(0).getRewardPoints()));
		
	}
		
		private void setTableModelFromDB(){
			
			RewardsCartTableModel model = new RewardsCartTableModel(aList);
			tblList.setModel(model);
			


		
	}
		
		
		private boolean deductPoints(String cardNumber, int amountDeducted){
			
			
			ArrayList<CardDetails> card = CardDetailsDA.retrieveCardDetailsByCardNumber(cardNumber);
			int expiringPoints = card.get(0).getExpiringRewardPoints();
			int totalPoints = card.get(0).getRewardPoints();
			
			if(amountDeducted > totalPoints){
				
				return false;
			}
			else{
				int newPoints =	totalPoints-amountDeducted;
				if(amountDeducted > expiringPoints){
					
					card.get(0).setExpiringRewardPoints(0);
					
				}
				textArea.setText("Your new points is " + newPoints+" Points." );
				
				card.get(0).setRewardPoints(newPoints);
				
				if(CardDetailsDA.updateCardDetails(card.get(0))){
					
					aList.clear();
				
					return true;
				}
				else{
					
					return false;
				}
		
				
				
				
			}
			
			
			
			
			
		}
		
}
