package teamCreditProjectApp.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MerchantMainFrame extends JFrame {

	private JPanel contentPane;
	protected JFrame myFrame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MerchantMainFrame frame = new MerchantMainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();

				}
			} 
			
		});
	}

	/**
	 * Create the frame.
	 */
	public MerchantMainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 500, 500);
		myFrame = this;
		contentPane = new TransactionSimulation(this);
		setContentPane(contentPane);
		
	}

}
