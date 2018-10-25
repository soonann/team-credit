package teamCreditProjectApp.ui;


import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;



import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.Color;

import teamCreditProjectApp.entity.CardDetails;
import teamCreditProjectApp.entity.Login;
import teamCreditProjectApp.dataAccess.CardDetailsDA;
import teamCreditProjectApp.dataAccess.LoginDA;



public class LoginPanel extends JPanel{
	
	private JTextField loginId;
	private JPasswordField passwordField;
	private JFrame myFrame;

	
	public LoginPanel(JFrame mf){
	
		myFrame = mf;
		setBackground(Color.WHITE);	
		setBounds(0,0, 990,650);
		setLayout(null);
		
		JLabel loginLabel = new JLabel("Login");
		loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
		loginLabel.setFont(new Font("Verdana", Font.BOLD, 25));
		loginLabel.setBounds(418, 115, 213, 45);
		add(loginLabel);
		
		JLabel loginIdLabel = new JLabel("SingPass ID :");
		loginIdLabel.setFont(new Font("Verdana", Font.PLAIN, 15));
		loginIdLabel.setHorizontalAlignment(SwingConstants.CENTER);
		loginIdLabel.setBounds(363, 376, 98, 20);
		add(loginIdLabel);
		
		JLabel loginPasswordLabel = new JLabel("Password :");
		loginPasswordLabel.setFont(new Font("Verdana", Font.PLAIN, 15));
		loginPasswordLabel.setHorizontalAlignment(SwingConstants.CENTER);
		loginPasswordLabel.setBounds(377, 424, 84, 20);
		add(loginPasswordLabel);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBackground(Color.LIGHT_GRAY);
		
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
			
				
				if(loginId.getText() != null && loginId.getText()!= ""){
					
					try{
					
					Login l1 = LoginDA.retrieveLoginBySingpassID(loginId.getText()); 
					
					
					
					if(l1.getPassword().equals(passwordField.getText())){
						
						if(myFrame != null && l1 != null ){
							
						JPanel contentPane = new LoginSummaryPanel(myFrame,l1);
						myFrame.setContentPane(contentPane);
						myFrame.setVisible(true);
						
						}
				
					}
					
					else{
						
						JOptionPane.showMessageDialog(myFrame,  "You have entered an Invalid Singpass ID or Password !", "Alert", JOptionPane.ERROR_MESSAGE);
						}	
						
					}catch(NullPointerException e){
					
						JOptionPane.showMessageDialog(myFrame,  "You have entered an Invalid Singpass ID or Password !!", "Alert", JOptionPane.ERROR_MESSAGE);
					
					}
				}
				else{
					
					JOptionPane.showMessageDialog(myFrame,  "Please enter both fields !", "Alert", JOptionPane.ERROR_MESSAGE);
					
				}
				
				
		}});
		btnLogin.setFont(new Font("Verdana", Font.PLAIN, 15));
		btnLogin.setBounds(547, 500, 84, 29);
		add(btnLogin);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Verdana", Font.PLAIN, 15));
		passwordField.setBounds(479, 420, 156, 29);
		add(passwordField);
		
		loginId = new JTextField();
		loginId.setFont(new Font("Verdana", Font.PLAIN, 15));
		loginId.setBounds(479, 372, 156, 29);
		add(loginId);
		loginId.setColumns(10);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(LoginPanel.class.getResource("/ProjectApp/images/Singpass-4.jpg.png")));
		label.setBounds(265, 165, 452, 202);
		add(label);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(LoginPanel.class.getResource("/CreditLimiter/logo.png")));
		label_1.setBounds(479, 0, 138, 122);
		add(label_1);
		
		
	
	
		
		
	}
	

	
}
