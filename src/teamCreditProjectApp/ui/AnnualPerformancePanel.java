package teamCreditProjectApp.ui;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;



import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;


import org.jfree.chart.ChartFactory;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;



import teamCreditProjectApp.dataAccess.TransactionDA;
import teamCreditProjectApp.entity.Login;
import teamCreditProjectApp.entity.Transaction;

public class AnnualPerformancePanel extends MasterPanel {
		public static int selectedYear;
		

	public AnnualPerformancePanel(JFrame mf, int yearContainer , Login l1) {
		
		super(mf,l1);
		
	
		JPanel ChartPanel = new JPanel();
		ChartPanel.setBounds(15, 156, 960, 415);
		add(ChartPanel);
		
		

		
		JLabel lblAnnualPerformance = new JLabel("Annual Performance");
		lblAnnualPerformance.setFont(new Font("Verdana", Font.BOLD, 25));
		lblAnnualPerformance.setBounds(362, 71, 304, 32);
		add(lblAnnualPerformance);
		
		JLabel lblAnnualStatement = new JLabel("Annual Statement");
		lblAnnualStatement.setFont(new Font("Verdana", Font.PLAIN, 15));
		lblAnnualStatement.setBounds(15, 109, 146, 20);
		add(lblAnnualStatement);
		

		GregorianCalendar calendar = new GregorianCalendar();
		int calendarYear = calendar.getTime().getYear() - 100 +2000;
		int calendarMonth = calendar.getTime().getMonth();
		String[] year = new String[5];
		ArrayList<Integer> index = new ArrayList<Integer>();
		for(int i=0; i<year.length; i++){
			int yr = 2017;
			yr -= i;
			year[i] = Integer.toString(yr);
			index.add(i);
			
		}

		JComboBox YearFilter = new JComboBox(year);
		YearFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for(int i=0; i<year.length;i++){
					
				selectedYear = Integer.parseInt(YearFilter.getSelectedItem().toString());

				}
				
			}
		});
		

		JFreeChart barChart = ChartFactory.createBarChart("","Month","Amount",BarChart_AWT.createDataset(yearContainer),PlotOrientation.VERTICAL,true, true, false);  
		ChartPanel chartPanel = new ChartPanel( barChart );        
		chartPanel.setPreferredSize(new java.awt.Dimension( 900 , 400 ) );    
		ChartPanel.add(chartPanel);

		
		YearFilter.setBounds(162, 109, 82, 20);
		add(YearFilter);
		for(int j=0; j<index.size();j++){
			int yr = 2017;
			yr -= j;
			if(calendarYear == yr){
				YearFilter.setSelectedIndex(j);
			}
			
		}
		


		double totalAmount = 0;
		ArrayList<Transaction> result = TransactionDA.retrieveAllTransaction();
		for(int i =0; i<result.size(); i++){
			if(Integer.parseInt(result.get(i).getTransactionDate().substring(6,8)) == calendarYear){
			totalAmount += result.get(i).getTransactionAmount();
			}
		}
		

		JButton submitBtn = new JButton("Submit");
		submitBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		submitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				JPanel contentPane = new AnnualPerformancePanel(myFrame,selectedYear , l1);
				myFrame.setContentPane(contentPane);
				myFrame.setVisible(true);

			}
		});

		
		submitBtn.setBounds(259, 109, 107, 20);
		add(submitBtn);
		
		JLabel lblNewLabel = new JLabel("Annual Performance");
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
		lblNewLabel.setBounds(425, 134, 205, 22);
		add(lblNewLabel);
		
	
	}
}
