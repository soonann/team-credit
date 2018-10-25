package teamCreditProjectApp.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


import teamCreditProjectApp.dataAccess.CardDetailsDA;
import teamCreditProjectApp.dataAccess.RewardsCartDA;
import teamCreditProjectApp.entity.CardDetails;
import teamCreditProjectApp.entity.Login;
import teamCreditProjectApp.entity.RewardsCart;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OCBCRewardMainPanel extends MasterPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table_1;

	/**
	 * Create the panel.
	 */
	public OCBCRewardMainPanel(JFrame mf, String cardNumber,Login l1) {
		super(mf,l1);
		setLayout(null);
	

		JInternalFrame internalFrame = new JInternalFrame("New JInternalFrame");
		internalFrame.setBounds(216, 145, 543, 396);
		add(internalFrame);
		internalFrame.setVisible(false);
		internalFrame.setClosable(true);
		
		JPanel panel = new JPanel();
		internalFrame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblDescription = new JLabel("Product Description");
		lblDescription.setForeground(Color.BLUE);
		lblDescription.setFont(new Font("Arial", Font.PLAIN, 25));
		lblDescription.setBounds(200, 35, 240, 20);
		panel.add(lblDescription);
		
		JLabel lblTermsAndConditions = new JLabel("Terms and Conditions");
		lblTermsAndConditions.setForeground(Color.BLUE);
		lblTermsAndConditions.setFont(new Font("Arial", Font.PLAIN, 25));
		lblTermsAndConditions.setBounds(200, 115, 269, 20);
		panel.add(lblTermsAndConditions);
		
		JLabel lblItemName = new JLabel("New label");
		lblItemName.setFont(new Font("Arial", Font.PLAIN, 20));
		lblItemName.setBounds(200, 63, 287, 36);
		panel.add(lblItemName);
		
		ArrayList<RewardsCart> alist = RewardsCartDA.retrieveAllRewardsCart();
		lblItemName.setText(alist.get(5).getItemName());
		
		JLabel lblItemPoints = new JLabel("New label");
		lblItemPoints.setForeground(Color.RED);
		lblItemPoints.setBounds(15, 164, 96, 20);
		panel.add(lblItemPoints);
		
		ArrayList<RewardsCart> blist = RewardsCartDA.retrieveAllRewardsCart();
		lblItemPoints.setText(blist.get(5).getItemPoints());
		
		JLabel lblItemType = new JLabel("");
		lblItemType.setForeground(Color.BLUE);
		lblItemType.setFont(new Font("Arial", Font.PLAIN, 25));
		lblItemType.setBounds(15, 138, 108, 20);
		panel.add(lblItemType);
		
		ArrayList<RewardsCart> clist = RewardsCartDA.retrieveAllRewardsCart();
		lblItemType.setText(clist.get(5).getItemType());
		
		JTextArea textItemDescription = new JTextArea();
		textItemDescription.setBounds(195, 164, 322, 104);
		panel.add(textItemDescription);
		
		ArrayList<RewardsCart> dlist = RewardsCartDA.retrieveAllRewardsCart();
		textItemDescription.setText("Voucher is in a form of redemption slip.\r\nMultiple redemptions are allowed.\r\n");
		
		ArrayList<RewardsCart> elist = RewardsCartDA.retrieveAllRewardsCart();
		//lblItemDescription.setText(elist.get(5).getItemPic());
		JLabel lblItemPic = new JLabel("");
		lblItemPic.setIcon(new ImageIcon(OCBCRewardMainPanel.class.getResource("/Reward/img/" + elist.get(5).getItemPic())));
		lblItemPic.setBounds(4, 35, 194, 100);
		panel.add(lblItemPic);
		
		JButton btnRedeemcart = new JButton("Redeem Cart");
		btnRedeemcart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				RewardsCart a_1 = new RewardsCart("CALTEX $10 Voucher",1,"3500 Points");
				aList.add(a_1);
				
		for(int i = 0; i<aList.size();i++){
					
					String name = aList.get(i).getItemName();
					System.out.println(name);
					int quantity = aList.get(i).getQuantity();
					System.out.println(quantity);
					String points = aList.get(i).getItemPoints();
					System.out.println(points);
					}
		
				JPanel contentPane = new RedeemCartPanel (myFrame, l1,cardNumber);
				myFrame.setContentPane(contentPane);
				myFrame.setVisible(true);
				

			}
		});
		btnRedeemcart.setFont(new Font("Arial", Font.PLAIN, 18));
		btnRedeemcart.setIcon(new ImageIcon(DBSRewardMainPanel.class.getResource("/Redeem/img/Cart.png")));
		btnRedeemcart.setBounds(289, 284, 176, 29);
		panel.add(btnRedeemcart);
		

		
		JInternalFrame internalFrame_1 = new JInternalFrame("New JInternalFrame");
		internalFrame_1.setBounds(216, 120, 543, 396);
		add(internalFrame_1);
		internalFrame_1.setVisible(false);
		internalFrame_1.setClosable(true);
		
		JPanel panel_1 = new JPanel();
		internalFrame_1.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		JLabel lblDescription_1 = new JLabel("Product Description");
		lblDescription_1.setForeground(Color.BLUE);
		lblDescription_1.setFont(new Font("Arial", Font.PLAIN, 25));
		lblDescription_1.setBounds(200, 35, 240, 20);
		panel_1.add(lblDescription_1);
		
		JLabel lblTermsAndConditions_1 = new JLabel("Terms and Conditions");
		lblTermsAndConditions_1.setForeground(Color.BLUE);
		lblTermsAndConditions_1.setFont(new Font("Arial", Font.PLAIN, 25));
		lblTermsAndConditions_1.setBounds(200, 115, 269, 20);
		panel_1.add(lblTermsAndConditions_1);
		
		JLabel lblItemName_1 = new JLabel("New label");
		lblItemName_1.setFont(new Font("Arial", Font.PLAIN, 20));
		lblItemName_1.setBounds(200, 63, 215, 36);
		panel_1.add(lblItemName_1);
		
		ArrayList<RewardsCart> alist_1 = RewardsCartDA.retrieveAllRewardsCart();
		lblItemName_1.setText(alist_1.get(6).getItemName());
		
		JLabel lblItemPoints_1 = new JLabel("New label");
		lblItemPoints_1.setForeground(Color.RED);
		lblItemPoints_1.setBounds(15, 164, 96, 20);
		panel_1.add(lblItemPoints_1);
		
		ArrayList<RewardsCart> blist_1 = RewardsCartDA.retrieveAllRewardsCart();
		lblItemPoints_1.setText(blist_1.get(6).getItemPoints());
		
		JLabel lblItemType_1 = new JLabel("");
		lblItemType_1.setForeground(Color.BLUE);
		lblItemType_1.setFont(new Font("Arial", Font.PLAIN, 25));
		lblItemType_1.setBounds(15, 138, 77, 20);
		panel_1.add(lblItemType_1);
		
		ArrayList<RewardsCart> clist_1 = RewardsCartDA.retrieveAllRewardsCart();
		lblItemType_1.setText(clist_1.get(6).getItemType());
		
		JTextArea textItemDescription_1 = new JTextArea();
		textItemDescription_1.setBounds(195, 164, 317, 104);
		panel_1.add(textItemDescription_1);
		
		ArrayList<RewardsCart> dlist_1 = RewardsCartDA.retrieveAllRewardsCart();
		textItemDescription_1.setText(dlist_1.get(6).getItemDescription());
		
		ArrayList<RewardsCart> elist_1 = RewardsCartDA.retrieveAllRewardsCart();
		//lblItemDescription.setText(elist_1.get(6).getItemPic());
		JLabel lblItemPic_1 = new JLabel("");
		lblItemPic_1.setIcon(new ImageIcon(OCBCRewardMainPanel.class.getResource("/Reward/img/" + elist_1.get(6).getItemPic())));
		lblItemPic_1.setBounds(4, 35, 194, 100);
		panel_1.add(lblItemPic_1);
		
		JButton btnRedeemcart_1 = new JButton("Redeem Cart");
		btnRedeemcart_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				RewardsCart b_1 = new RewardsCart("Swensen's S$10 Voucher",1,"3500 Points");
				aList.add(b_1);
				
				for(int i = 0; i<aList.size();i++){
					
					String name = aList.get(i).getItemName();
					System.out.println(name);
					int quantity = aList.get(i).getQuantity();
					System.out.println(quantity);
					String points = aList.get(i).getItemPoints();
					System.out.println(points);
					}
				
				JPanel contentPane = new RedeemCartPanel (myFrame, l1,cardNumber);
		myFrame.setContentPane(contentPane);
		myFrame.setVisible(true);
		

			}
		});
		btnRedeemcart_1.setFont(new Font("Arial", Font.PLAIN, 18));
		btnRedeemcart_1.setIcon(new ImageIcon(DBSRewardMainPanel.class.getResource("/Redeem/img/Cart.png")));
		btnRedeemcart_1.setBounds(289, 284, 176, 29);
		panel_1.add(btnRedeemcart_1);
		
		JInternalFrame internalFrame_2 = new JInternalFrame("New JInternalFrame");
		internalFrame_2.setBounds(216, 120, 543, 396);
		add(internalFrame_2);
		internalFrame_2.setVisible(false);
		internalFrame_2.setClosable(true);
		
		JPanel panel_2 = new JPanel();
		internalFrame_2.getContentPane().add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(null);
		
		JLabel lblDescription_2 = new JLabel("Product Description");
		lblDescription_2.setForeground(Color.BLUE);
		lblDescription_2.setFont(new Font("Arial", Font.PLAIN, 25));
		lblDescription_2.setBounds(200, 35, 240, 20);
		panel_2.add(lblDescription_2);
		
		JLabel lblTermsAndConditions_2 = new JLabel("Terms and Conditions");
		lblTermsAndConditions_2.setForeground(Color.BLUE);
		lblTermsAndConditions_2.setFont(new Font("Arial", Font.PLAIN, 25));
		lblTermsAndConditions_2.setBounds(200, 115, 269, 20);
		panel_2.add(lblTermsAndConditions_2);
		
		JLabel lblItemName_2 = new JLabel("New label");
		lblItemName_2.setFont(new Font("Arial", Font.PLAIN, 20));
		lblItemName_2.setBounds(200, 63, 215, 36);
		panel_2.add(lblItemName_2);
		
		ArrayList<RewardsCart> alist_2 = RewardsCartDA.retrieveAllRewardsCart();
		lblItemName_2.setText(alist_2.get(7).getItemName());
		
		JLabel lblItemPoints_2 = new JLabel("New label");
		lblItemPoints_2.setForeground(Color.RED);
		lblItemPoints_2.setBounds(15, 164, 96, 20);
		panel_2.add(lblItemPoints_2);
		
		ArrayList<RewardsCart> blist_2 = RewardsCartDA.retrieveAllRewardsCart();
		lblItemPoints_2.setText(blist_2.get(7).getItemPoints());
		
		JLabel lblItemType_2 = new JLabel("");
		lblItemType_2.setForeground(Color.BLUE);
		lblItemType_2.setFont(new Font("Arial", Font.PLAIN, 25));
		lblItemType_2.setBounds(15, 138, 77, 20);
		panel_2.add(lblItemType_2);
		
		ArrayList<RewardsCart> clist_2 = RewardsCartDA.retrieveAllRewardsCart();
		lblItemType_2.setText(clist_2.get(7).getItemType());
		
		JTextArea textItemDescription_2 = new JTextArea();
		textItemDescription_2.setBounds(195, 164, 317, 104);
		panel_2.add(textItemDescription_2);
		
		ArrayList<RewardsCart> dlist_2 = RewardsCartDA.retrieveAllRewardsCart();
		textItemDescription_2.setText(dlist_2.get(7).getItemDescription());
		
		ArrayList<RewardsCart> elist_2 = RewardsCartDA.retrieveAllRewardsCart();
		//lblItemDescription.setText(elist_2.get(7).getItemPic());
		JLabel lblItemPic_2 = new JLabel("");
		lblItemPic_2.setIcon(new ImageIcon(OCBCRewardMainPanel.class.getResource("/Reward/img/" + elist_2.get(7).getItemPic())));
		lblItemPic_2.setBounds(4, 35, 194, 100);
		panel_2.add(lblItemPic_2);
		
		JButton btnRedeemcart_2 = new JButton("Redeem Cart");
		btnRedeemcart_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				RewardsCart c_1 = new RewardsCart("KONSORTIUM EXPRESS AND TOURS",1,"3700 Points");
				aList.add(c_1);
				
				for(int i = 0; i<aList.size();i++){
					
					String name = aList.get(i).getItemName();
					System.out.println(name);
					int quantity = aList.get(i).getQuantity();
					System.out.println(quantity);
					String points = aList.get(i).getItemPoints();
					System.out.println(points);
					}
				
				JPanel contentPane = new RedeemCartPanel (myFrame, l1,cardNumber);
		myFrame.setContentPane(contentPane);
		myFrame.setVisible(true);
		

			}
		});
		btnRedeemcart_2.setFont(new Font("Arial", Font.PLAIN, 18));
		btnRedeemcart_2.setIcon(new ImageIcon(DBSRewardMainPanel.class.getResource("/Redeem/img/Cart.png")));
		btnRedeemcart_2.setBounds(289, 284, 176, 29);
		panel_2.add(btnRedeemcart_2);
		
		JInternalFrame internalFrame_3 = new JInternalFrame("New JInternalFrame");
		internalFrame_3.setBounds(216, 120, 543, 396);
		add(internalFrame_3);
		internalFrame_3.setVisible(false);
		internalFrame_3.setClosable(true);
		
		JPanel panel_3 = new JPanel();
		internalFrame_3.getContentPane().add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(null);
		
		JLabel lblDescription_3 = new JLabel("Product Description");
		lblDescription_3.setForeground(Color.BLUE);
		lblDescription_3.setFont(new Font("Arial", Font.PLAIN, 25));
		lblDescription_3.setBounds(200, 35, 240, 20);
		panel_3.add(lblDescription_3);
		
		JLabel lblTermsAndConditions_3 = new JLabel("Terms and Conditions");
		lblTermsAndConditions_3.setForeground(Color.BLUE);
		lblTermsAndConditions_3.setFont(new Font("Arial", Font.PLAIN, 25));
		lblTermsAndConditions_3.setBounds(200, 115, 269, 20);
		panel_3.add(lblTermsAndConditions_3);
		
		JLabel lblItemName_3 = new JLabel("New label");
		lblItemName_3.setFont(new Font("Arial", Font.PLAIN, 20));
		lblItemName_3.setBounds(200, 63, 215, 36);
		panel_3.add(lblItemName_3);
		
		ArrayList<RewardsCart> alist_3 = RewardsCartDA.retrieveAllRewardsCart();
		lblItemName_3.setText(alist_3.get(8).getItemName());
		
		JLabel lblItemPoints_3 = new JLabel("New label");
		lblItemPoints_3.setForeground(Color.RED);
		lblItemPoints_3.setBounds(15, 164, 96, 20);
		panel_3.add(lblItemPoints_3);
		
		ArrayList<RewardsCart> blist_3 = RewardsCartDA.retrieveAllRewardsCart();
		lblItemPoints_3.setText(blist_3.get(8).getItemPoints());
		
		JLabel lblItemType_3 = new JLabel("");
		lblItemType_3.setForeground(Color.BLUE);
		lblItemType_3.setFont(new Font("Arial", Font.PLAIN, 25));
		lblItemType_3.setBounds(15, 138, 77, 20);
		panel_3.add(lblItemType_3);
		
		ArrayList<RewardsCart> clist_3 = RewardsCartDA.retrieveAllRewardsCart();
		lblItemType_3.setText(clist_3.get(8).getItemType());
		
		JTextArea textItemDescription_3 = new JTextArea();
		textItemDescription_3.setBounds(195, 164, 317, 104);
		panel_3.add(textItemDescription_3);
		
		ArrayList<RewardsCart> dlist_3 = RewardsCartDA.retrieveAllRewardsCart();
		textItemDescription_3.setText(dlist_3.get(8).getItemDescription());
		
		ArrayList<RewardsCart> elist_3 = RewardsCartDA.retrieveAllRewardsCart();
		//lblItemDescription.setText(elist_3.get(8).getItemPic());
		JLabel lblItemPic_3 = new JLabel("");
		lblItemPic_3.setIcon(new ImageIcon(OCBCRewardMainPanel.class.getResource("/Reward/img/" + elist_3.get(8).getItemPic())));
		lblItemPic_3.setBounds(4, 35, 194, 100);
		panel_3.add(lblItemPic_3);
		
		JButton btnRedeemcart_3 = new JButton("Redeem Cart");
		btnRedeemcart_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				RewardsCart d_1 = new RewardsCart("ISETAN SCOTTS $10 Voucher",1,"3900 Points");
				aList.add(d_1);
				
				for(int i = 0; i<aList.size();i++){
					
					String name = aList.get(i).getItemName();
					System.out.println(name);
					int quantity = aList.get(i).getQuantity();
					System.out.println(quantity);
					String points = aList.get(i).getItemPoints();
					System.out.println(points);
					}
				
				JPanel contentPane = new RedeemCartPanel (myFrame, l1,cardNumber);
		myFrame.setContentPane(contentPane);
		myFrame.setVisible(true);
		

			}
		});
		btnRedeemcart_3.setFont(new Font("Arial", Font.PLAIN, 18));
		btnRedeemcart_3.setIcon(new ImageIcon(DBSRewardMainPanel.class.getResource("/Redeem/img/Cart.png")));
		btnRedeemcart_3.setBounds(289, 284, 176, 29);
		panel_3.add(btnRedeemcart_3);
		
		JLabel lblAll = new JLabel("ALL");
		lblAll.setFont(new Font("Arial", Font.PLAIN, 25));
		lblAll.setBounds(25, 287, 60, 20);
		add(lblAll);
		
		JLabel lblDine = new JLabel("DINE");
		lblDine.setFont(new Font("Arial", Font.PLAIN, 25));
		lblDine.setBounds(135, 287, 76, 20);
		add(lblDine);
		
		JLabel lblPlay = new JLabel("PLAY");
		lblPlay.setFont(new Font("Arial", Font.PLAIN, 25));
		lblPlay.setBounds(245, 287, 69, 20);
		add(lblPlay);
		
		JLabel lblService = new JLabel("SERVICE");
		lblService.setFont(new Font("Arial", Font.PLAIN, 25));
		lblService.setBounds(329, 287, 110, 20);
		add(lblService);
		
		JLabel lblShop = new JLabel("SHOP");
		lblShop.setFont(new Font("Arial", Font.PLAIN, 25));
		lblShop.setBounds(465, 287, 76, 20);
		add(lblShop);
		
		JLabel lblTravel = new JLabel("TRAVEL");
		lblTravel.setFont(new Font("Arial", Font.PLAIN, 25));
		lblTravel.setBounds(556, 287, 102, 20);
		add(lblTravel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(-15, 323, 1215, 4);
		add(separator);
	
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 364, 1200, 4);
		add(separator_1);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel contentPane = new OCBCRewardMainPanel(myFrame,cardNumber, l1);
				myFrame.setContentPane(contentPane);
				myFrame.setVisible(true);
			}
		});
		button.setIcon(new ImageIcon(OCBCRewardMainPanel.class.getResource("/Redeem/img/All1.png")));
		button.setBounds(25, 211, 60, 60);
		add(button);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel contentPane = new OCBCRewardDinePanel(myFrame, l1,cardNumber);
				myFrame.setContentPane(contentPane);
				myFrame.setVisible(true);
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(OCBCRewardDinePanel.class.getResource("/Redeem/img/Dine1.png")));
		btnNewButton_1.setBounds(135, 211, 60, 60);
		add(btnNewButton_1);
		
		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel contentPane = new OCBCRewardPlayPanel(myFrame, l1,cardNumber);
				myFrame.setContentPane(contentPane);
				myFrame.setVisible(true);
			}
		});
		button_1.setIcon(new ImageIcon(OCBCRewardPlayPanel.class.getResource("/Redeem/img/Play1.png")));
		button_1.setBounds(245, 211, 60, 60);
		add(button_1);
		
		JButton button_2 = new JButton("");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel contentPane = new OCBCRewardServicePanel(myFrame, l1,cardNumber);
				myFrame.setContentPane(contentPane);
				myFrame.setVisible(true);
			}
		});
		button_2.setIcon(new ImageIcon(OCBCRewardServicePanel.class.getResource("/Redeem/img/Service1.png")));
		button_2.setBounds(355, 211, 60, 60);
		add(button_2);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel contentPane = new OCBCRewardShopPanel(myFrame, l1,cardNumber);
				myFrame.setContentPane(contentPane);
				myFrame.setVisible(true);
			}
		});
		btnNewButton_2.setIcon(new ImageIcon(OCBCRewardShopPanel.class.getResource("/Redeem/img/Shop1.png")));
		btnNewButton_2.setBounds(464, 211, 60, 60);
		add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel contentPane = new OCBCRewardTravelPanel(myFrame, l1,cardNumber);
				myFrame.setContentPane(contentPane);
				myFrame.setVisible(true);
			}
		});
		btnNewButton_3.setIcon(new ImageIcon(OCBCRewardTravelPanel.class.getResource("/Redeem/img/Travel1.png")));
		btnNewButton_3.setBounds(575, 211, 60, 60);
		add(btnNewButton_3);
		
		JLabel lblNewLabel = new JLabel("CALTEX $10 Voucher");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel.setBounds(15, 496, 175, 20);
		add(lblNewLabel);
		
		JLabel lblNewLabel_3 = new JLabel("3500 Points");
		lblNewLabel_3.setForeground(Color.RED);
		lblNewLabel_3.setBounds(15, 521, 110, 20);
		add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Swensen's S$10 Voucher");
		lblNewLabel_4.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel_4.setBounds(206, 496, 176, 20);
		add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("3500 Points");
		lblNewLabel_5.setForeground(Color.RED);
		lblNewLabel_5.setBounds(206, 521, 99, 20);
		add(lblNewLabel_5);
		
		JLabel lblNewLabel_9 = new JLabel("KONSORTIUM EXPRESS AND TOURS");
		lblNewLabel_9.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel_9.setBounds(396, 496, 170, 20);
		add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("3700 Points");
		lblNewLabel_10.setForeground(Color.RED);
		lblNewLabel_10.setBounds(396, 521, 110, 20);
		add(lblNewLabel_10);
		
		JLabel lblCharlesKeith = new JLabel("ISETAN SCOTTS $10 Voucher");
		lblCharlesKeith.setFont(new Font("Arial", Font.PLAIN, 20));
		lblCharlesKeith.setBounds(584, 496, 175, 20);
		add(lblCharlesKeith);
		
		JLabel lblPoints_6 = new JLabel("3900 Points");
		lblPoints_6.setForeground(Color.RED);
		lblPoints_6.setBounds(582, 521, 141, 20);
		add(lblPoints_6);
		
		String rewardPoints;
	
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(245, 134, 661, 69);
		add(scrollPane);
		
		

		table_1 = new JTable();

		setTableModelFromDB(l1,cardNumber);
		
		scrollPane.setViewportView(table_1);
		scrollPane.setColumnHeaderView(table_1.getTableHeader());
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(0, 203, 1200, 4);
		add(separator_2);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(OCBCRewardMainPanel.class.getResource("/Redeem/img/OCBC.jpg")));
		label_1.setBounds(25, 134, 227, 69);
		add(label_1);
		
		JButton btnNewButton_4 = new JButton("");
		btnNewButton_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				internalFrame.setVisible(true);
			}
		});
		btnNewButton_4.setIcon(new ImageIcon(OCBCRewardMainPanel.class.getResource("/Reward/img/caltext.jpg")));
		btnNewButton_4.setBounds(15, 384, 175, 110);
		add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("");
		btnNewButton_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				internalFrame_1.setVisible(true);
			}
		});
		btnNewButton_5.setIcon(new ImageIcon(OCBCRewardMainPanel.class.getResource("/Reward/img/Swensen's S$10 Voucher.jpg")));
		btnNewButton_5.setBounds(206, 384, 175, 110);
		add(btnNewButton_5);
		
		JButton btnNewButton_8 = new JButton("");
		btnNewButton_8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				internalFrame_2.setVisible(true);
			}
		});
		btnNewButton_8.setIcon(new ImageIcon(OCBCRewardMainPanel.class.getResource("/Reward/img/KONSORTIUM EXPRESS AND TOURS.jpg")));
		btnNewButton_8.setBounds(396, 384, 175, 110);
		add(btnNewButton_8);
		
		JButton button_9 = new JButton("");
		button_9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				internalFrame_3.setVisible(true);
			}
		});
		button_9.setIcon(new ImageIcon(OCBCRewardMainPanel.class.getResource("/Reward/img/Isetan S$10 Voucher.jpg")));
		button_9.setBounds(584, 384, 175, 110);
		add(button_9);
		
		JButton btnRedeemCart = new JButton("Redeem Cart");
		btnRedeemCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JPanel contentPane = new RedeemCartPanel(myFrame, l1,cardNumber);
				myFrame.setContentPane(contentPane);
				myFrame.setVisible(true);
			}
		});
		btnRedeemCart.setHorizontalAlignment(SwingConstants.LEFT);
		btnRedeemCart.setFont(new Font("Arial", Font.PLAIN, 25));
		btnRedeemCart.setIcon(new ImageIcon(OCBCRewardMainPanel.class.getResource("/Redeem/img/Cart.png")));
		btnRedeemCart.setBounds(700, 262, 249, 35);
		add(btnRedeemCart);

		
		}
		private void setTableModelFromDB(Login l1,String cardNumber){
			ArrayList<CardDetails> result = CardDetailsDA.retrieveCardDetailsBySingpassID(l1.getSingpassID());
			ArrayList<CardDetails> result1 = CardDetailsDA.retrieveCardDetailsByCardNumber(cardNumber);
			RedeemInfoTableModel model = new RedeemInfoTableModel(result1);
			
			table_1.setModel(model);
			/*
			 * 
			table_1.setModel(new DefaultTableModel(
				new Object[][] {
					{new Integer(200), new Integer(100), "13/1"},
					{new Integer(500), new Integer(300), "2/1"},
				},
				new String[] {
					"Reward Points", "Expiring Reward Points", "Expiring Reward Points Date"
				}
			) {
				boolean[] columnEditables = new boolean[] {
					false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});*/
	}
}
