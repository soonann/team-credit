package teamCreditProjectApp.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


import teamCreditProjectApp.entity.Login;

public class OCBCRewardPlayPanel extends MasterPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Create the panel.
	 */
	public OCBCRewardPlayPanel(JFrame mf,Login l1,String cardNumber) {
		super(mf,l1);
		
	
		setLayout(null);
		
		
				
				JLabel lblAll = new JLabel("ALL");
				lblAll.setFont(new Font("Arial", Font.PLAIN, 25));
				lblAll.setBounds(35, 215, 60, 20);
				add(lblAll);
				
				JLabel lblDine = new JLabel("DINE");
				lblDine.setFont(new Font("Arial", Font.PLAIN, 25));
				lblDine.setBounds(135, 215, 76, 20);
				add(lblDine);
				
				JLabel lblPlay = new JLabel("PLAY");
				lblPlay.setFont(new Font("Arial", Font.PLAIN, 25));
				lblPlay.setBounds(245, 215, 69, 20);
				add(lblPlay);
				
				JLabel lblService = new JLabel("SERVICE");
				lblService.setFont(new Font("Arial", Font.PLAIN, 25));
				lblService.setBounds(329, 215, 110, 20);
				add(lblService);
				
				JLabel lblShop = new JLabel("SHOP");
				lblShop.setFont(new Font("Arial", Font.PLAIN, 25));
				lblShop.setBounds(462, 215, 76, 20);
				add(lblShop);
				
				JLabel lblTravel = new JLabel("TRAVEL");
				lblTravel.setFont(new Font("Arial", Font.PLAIN, 25));
				lblTravel.setBounds(560, 215, 102, 20);
				add(lblTravel);
				
				JSeparator separator = new JSeparator();
				separator.setBounds(-15, 245, 1215, 4);
				add(separator);
				
				textField = new JTextField();
				textField.setBounds(462, 262, 193, 30);
				add(textField);
				textField.setColumns(10);
				
				JButton btnNewButton = new JButton("");
				btnNewButton.setIcon(new ImageIcon(OCBCRewardPlayPanel.class.getResource("/Redeem/img/Search.png")));
				btnNewButton.setBounds(659, 262, 30, 30);
				add(btnNewButton);
				
				JLabel lblNewLabel_2 = new JLabel("Range From:");
				lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 25));
				lblNewLabel_2.setBounds(15, 262, 147, 30);
				add(lblNewLabel_2);
				
				textField_1 = new JTextField();
				textField_1.setBounds(184, 262, 60, 30);
				add(textField_1);
				textField_1.setColumns(10);
				
				JLabel label = new JLabel("-");
				label.setFont(new Font("Arial", Font.PLAIN, 30));
				label.setBounds(255, 262, 18, 30);
				add(label);
				
				textField_2 = new JTextField();
				textField_2.setColumns(10);
				textField_2.setBounds(272, 262, 60, 30);
				add(textField_2);
				
				JLabel lblPoints = new JLabel("Points");
				lblPoints.setFont(new Font("Arial", Font.PLAIN, 25));
				lblPoints.setBounds(355, 262, 93, 30);
				add(lblPoints);
				
				JSeparator separator_1 = new JSeparator();
				separator_1.setBounds(0, 299, 1200, 4);
				add(separator_1);
				
				JButton button = new JButton("");
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String cardNumber = null;
						JPanel contentPane = new OCBCRewardMainPanel(myFrame, cardNumber, l1);
						myFrame.setContentPane(contentPane);
						myFrame.setVisible(true);
					}
				});
				button.setIcon(new ImageIcon(OCBCRewardPlayPanel.class.getResource("/Redeem/img/All1.png")));
				button.setBounds(25, 139, 60, 60);
				add(button);
				
				JButton btnNewButton_1 = new JButton("");
				btnNewButton_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JPanel contentPane = new OCBCRewardDinePanel(myFrame, l1,cardNumber);
						myFrame.setContentPane(contentPane);
						myFrame.setVisible(true);
					}
				});
				btnNewButton_1.setIcon(new ImageIcon(OCBCRewardPlayPanel.class.getResource("/Redeem/img/Dine1.png")));
				btnNewButton_1.setBounds(135, 139, 60, 60);
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
				button_1.setBounds(245, 139, 60, 60);
				add(button_1);
				
				JButton button_2 = new JButton("");
				button_2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JPanel contentPane = new OCBCRewardServicePanel(myFrame, l1,cardNumber);
						myFrame.setContentPane(contentPane);
						myFrame.setVisible(true);
					}
				});
				button_2.setIcon(new ImageIcon(OCBCRewardPlayPanel.class.getResource("/Redeem/img/Service1.png")));
				button_2.setBounds(355, 139, 60, 60);
				add(button_2);
				
				JButton btnNewButton_2 = new JButton("");
				btnNewButton_2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JPanel contentPane = new OCBCRewardShopPanel(myFrame, l1,cardNumber);
						myFrame.setContentPane(contentPane);
						myFrame.setVisible(true);
					}
				});
				btnNewButton_2.setIcon(new ImageIcon(OCBCRewardPlayPanel.class.getResource("/Redeem/img/Shop1.png")));
				btnNewButton_2.setBounds(465, 139, 60, 60);
				add(btnNewButton_2);
				
				JButton btnNewButton_3 = new JButton("");
				btnNewButton_3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JPanel contentPane = new OCBCRewardTravelPanel(myFrame, l1,cardNumber);
						myFrame.setContentPane(contentPane);
						myFrame.setVisible(true);
					}
				});
				btnNewButton_3.setIcon(new ImageIcon(OCBCRewardPlayPanel.class.getResource("/Redeem/img/Travel1.png")));
				btnNewButton_3.setBounds(575, 139, 60, 60);
				add(btnNewButton_3);

				JButton btnRedeemCart = new JButton("Redeem Cart");
				btnRedeemCart.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JPanel contentPane = new RedeemCartPanel(myFrame, l1,cardNumber);
						myFrame.setContentPane(contentPane);
						myFrame.setVisible(true);
					}
				});
				btnRedeemCart.setHorizontalAlignment(SwingConstants.LEFT);
				btnRedeemCart.setFont(new Font("Arial", Font.PLAIN, 25));
				btnRedeemCart.setIcon(new ImageIcon(OCBCRewardPlayPanel.class.getResource("/Redeem/img/Cart.png")));
				btnRedeemCart.setBounds(720, 187, 249, 35);
				add(btnRedeemCart);
				
				
	}

}
