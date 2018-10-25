package teamCreditProjectApp.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.GregorianCalendar;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import teamCreditProjectApp.dataAccess.LoginDA;
import teamCreditProjectApp.entity.Login;

public class MainFrame extends JFrame {


	private JPanel contentPane;
	private JFrame myFrame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
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
	public MainFrame() {
		
		
		myFrame = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 680);
		contentPane = new LoginPanel(this);
		setContentPane(contentPane);
		
		
		
	}

}
