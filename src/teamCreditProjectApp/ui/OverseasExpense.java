package teamCreditProjectApp.ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import teamCreditProjectApp.dataAccess.TransactionDA;
import teamCreditProjectApp.entity.Login;
import teamCreditProjectApp.entity.Transaction;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OverseasExpense extends MasterPanel {
	private JTextField dateTextField;
	private JTextField descriptionTextField;
	private JTextField categoryTextField;
	private JTextField amountTextField;
	private Transaction transaction;
	private JButton okButton;
	private String actions;

	/**
	 * Create the panel.
	 * @wbp.parser.constructor
	 */
	public OverseasExpense(JFrame	mf,Login l1) {
		super(mf,l1);
		setLayout(null);
		
		JLabel addExpenseLabel = new JLabel("Add Expense");
		addExpenseLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		addExpenseLabel.setBounds(385, 165, 133, 31);
		add(addExpenseLabel);
		
		JLabel dateLabel = new JLabel("Date:");
		dateLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		dateLabel.setBounds(273, 238, 100, 19);
		add(dateLabel);
		
		dateTextField = new JTextField();
		dateTextField.setBounds(376, 238, 221, 20);
		add(dateTextField);
		dateTextField.setColumns(10);
		
		JLabel descriptionLabel = new JLabel("Description:");
		descriptionLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		descriptionLabel.setBounds(273, 269, 100, 19);
		add(descriptionLabel);
		
		descriptionTextField = new JTextField();
		descriptionTextField.setColumns(10);
		descriptionTextField.setBounds(376, 268, 221, 20);
		add(descriptionTextField);
		
		JLabel categoryLabel = new JLabel("Category:");
		categoryLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		categoryLabel.setBounds(273, 298, 100, 19);
		add(categoryLabel);
		
		categoryTextField = new JTextField();
		categoryTextField.setColumns(10);
		categoryTextField.setBounds(376, 299, 221, 20);
		add(categoryTextField);
		
		JLabel amountLabel = new JLabel("Amount:");
		amountLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		amountLabel.setBounds(273, 328, 100, 19);
		add(amountLabel);
		
		amountTextField = new JTextField();
		amountTextField.setColumns(10);
		amountTextField.setBounds(376, 328, 221, 20);
		add(amountTextField);
		
		JButton okButton = new JButton("OK");
		okButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedOk();
			}
			
			private boolean validateInput() {
				boolean result = false;
				String msg = "";
				int msgType = JOptionPane.ERROR_MESSAGE;
				// retrieve the user input from the text box/area provided
				String date = dateTextField.getText();
				String description = descriptionTextField.getText();
				String category = categoryTextField.getText();
				String amount = amountTextField.getText();

				if (date.length() != 10)
					msg += "Please enter date in DD/MM/YYYY format.\n";
				
				if (description.length() == 0)
					msg += "Please enter description.\n";
				
				if (category.length() == 0)
					msg += "Please enter category.\n";
				
				if (amount.length() == 0 || amount.length()<=0)
					msg += "Please enter amount.\n";

				if (msg.length() == 0)
					result = true;
				else
					JOptionPane.showMessageDialog(myFrame, msg, "Alert", msgType);
				return result;
			}
			
			private void actionPerformedOk() {
				int id = 0;
				double transactionAmount = 0;
				String transactionDate = dateTextField.getText();
				String description = descriptionTextField.getText();
				String category = categoryTextField.getText();
				// retrieve the user input from the text box/area provided
				if (validateInput()) {
					//category = String.parseString(categoryTextField.getText());
					transactionAmount = Double.parseDouble(amountTextField.getText());
					transaction = new Transaction(id,"4628-1234-1234-1234",transactionDate,transactionAmount,description,category);	
						actionPerformedCreate();
					}
				
			}
			/**
			 *	Purpose:	This method updates the expense record
			 *				in the database.
			 *	Input:		Nil
			 *	Return:		void
			 */
			
			public void actionPerformedUpdate(){
				// Update record in database and check return value
				if (TransactionDA.updateTransaction(transaction)) {
					JOptionPane.showMessageDialog(myFrame,
						"Record updated successfully", "Alert",
						JOptionPane.INFORMATION_MESSAGE);
					// reset text field for next record.
					dateTextField.setEditable(false);
					descriptionTextField.setEditable(false);
					categoryTextField.setEditable(false);
					amountTextField.setEditable(false);
					okButton.setEnabled(false);
				} 
				else {
					JOptionPane.showMessageDialog(myFrame,
							"Database Error. Record not updated.", "Alert",
							JOptionPane.ERROR_MESSAGE);
				}
			}
			
			/**
			 *	Purpose:	This method creates the expense record
			 *				in the database.
			 *	Input:		Nil
			 *	Return:		void
			 */
			public void actionPerformedCreate(){
				// insert to database and check return value
				if (TransactionDA.createTransaction(transaction)) {
					JOptionPane.showMessageDialog(myFrame,
						"Record created successfully", "Alert",
						JOptionPane.INFORMATION_MESSAGE);
					// reset text field for next record.
					dateTextField.setText("");
					descriptionTextField.setText("");
					categoryTextField.setText("");
					amountTextField.setText("");
				} 
				else
					JOptionPane.showMessageDialog(myFrame,
							"Database Error. Record not created.", "Alert",
							JOptionPane.ERROR_MESSAGE);
			}
		});
		okButton.setBounds(335, 388, 92, 31);
		add(okButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JPanel contentPane = new MasterPanel(myFrame,l1);
				myFrame.getContentPane().removeAll();
				myFrame.setContentPane(contentPane);
				myFrame.setVisible(true);
			}
		});
		cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cancelButton.setBounds(449, 388, 92, 31);
		add(cancelButton);
		
	}
	public OverseasExpense(JFrame	mf, Transaction e1,Login l1) {
		this(mf,l1);
	}
	
}
