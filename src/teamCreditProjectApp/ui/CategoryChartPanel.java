package teamCreditProjectApp.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import teamCreditProjectApp.dataAccess.CardDetailsDA;
import teamCreditProjectApp.dataAccess.LoginDA;
import teamCreditProjectApp.dataAccess.TransactionDA;
import teamCreditProjectApp.entity.CardDetails;
import teamCreditProjectApp.entity.Login;
import teamCreditProjectApp.entity.Transaction;
import teamCreditProjectApp.entity.Chart;

import org.jfree.chart.*;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.event.MouseAdapter;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;



public class CategoryChartPanel extends MasterPanel  {
	private JInternalFrame internalFrame;
	private JScrollPane scrollPane;
	private JTable table;
	private JLabel lblNewLabel_1;
	/**
	 * Create the panel.
	 */
	public CategoryChartPanel(JFrame mf,Login l1, String monthYear, String month) {
// pass in from constructor  
		
		super(mf,l1);
		
	
		
		
		ArrayList<String> listOfCategory = TransactionDA.retrieveListOfCategoryFromTransaction();
		ArrayList<Transaction> pieChartData = new ArrayList<Transaction>();
		ArrayList<CardDetails> numberOfCardDetails = CardDetailsDA.retrieveCardDetailsBySingpassID(l1.getSingpassID());
		ArrayList<String> cardList = new ArrayList<String>();
		
		for(int i = 0 ; i < numberOfCardDetails.size() ; i++){
			
			cardList.add(numberOfCardDetails.get(i).getCardNumber());
			
		}
		System.out.println(monthYear);
		ArrayList<Chart> generalPieChartData = TransactionDA.retrieveTransactionSumByTotalAmountDateAndCardNumber(("%"+monthYear) ,cardList);
		for(Chart c1 : generalPieChartData){
			
		System.out.println(c1.getCategoryName());
		System.out.println(	c1.getTotalAmount());
			
		}
				 
		DecimalFormat df = new DecimalFormat("0.00");
		DefaultPieDataset dataset = new DefaultPieDataset( );
	      //dataset.setValue("name of part", new Double() )
		for(int i = 0 ; i < generalPieChartData.size();i++ ){
			dataset.setValue(generalPieChartData.get(i).getCategoryName()+" ($" + df.format(generalPieChartData.get(i).getTotalAmount())+")", new Double(generalPieChartData.get(i).getTotalAmount()) );	
		}

	     //											title , data, legend tooltips 						
		JFreeChart chart = ChartFactory.createPieChart("", dataset, false,false, false);

		ChartMouseListener chartListener = new ChartMouseListener(){


			public void chartMouseClicked(ChartMouseEvent chartmouseevent) {
				// TODO Auto-generated method stub
				
					ChartEntity chartentity = chartmouseevent.getEntity();

					if (chartentity != null  && chartentity.getShapeType().equals("poly")){
						
					String search = chartentity.toString();	
					String category = "";
							
					for(int i = 0 ; i < listOfCategory.size() ; i++){
						
						if(search.indexOf(listOfCategory.get(i)) != -1 ){
							
							category = listOfCategory.get(i);
						}
						
					}
												
						ArrayList<Transaction> filteredList = TransactionDA.retrieveTransactionByCategoryTransactionDateAndCardNumbers(category, "%"+monthYear, cardList);
						setTableModelOfFrame(filteredList);				
						internalFrame.setTitle("Category: "+category );			
						internalFrame.setVisible(true);
				
					}
					else{
					System.out.println(chartentity.getURLText());
					System.out.println("Mouse clicked: null entity.");
				
					}
			}
		
			public void chartMouseMoved(ChartMouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		};
		
		internalFrame = new JInternalFrame("");
		internalFrame.setClosable(true);
		internalFrame.setBounds(255, 232, 526, 290);
		add(internalFrame);
		
		scrollPane = new JScrollPane();
		
		internalFrame.getContentPane().add(scrollPane, BorderLayout.NORTH);
		
		table = new JTable();
		/*
		table.getTableHeader().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			System.out.print(	table.getTableHeader().columnAtPoint(e.get)) ;
				
			}
		})
		;
		*/
		table.setFillsViewportHeight(true);
		table.setColumnSelectionAllowed(true);
		table.setAutoCreateRowSorter(true);
		//internalFrame.getContentPane().add(table, BorderLayout.SOUTH);
		scrollPane.setViewportView(table);
		
	
		ChartPanel chartPane = new ChartPanel(chart);
		chartPane.addChartMouseListener(chartListener);
		chartPane.setBounds(195, 249, 650, 339);
		add(chartPane);
		
		JLabel lblNewLabel = new JLabel("Spendings for the month of : ");
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 18));
		lblNewLabel.setBounds(284, 192, 303, 59);
		add(lblNewLabel);
		
		JLabel monthSet = new JLabel("");
		monthSet.setFont(new Font("Verdana", Font.PLAIN, 18));
		monthSet.setBounds(594, 192, 154, 59);
		add(monthSet);
		internalFrame.setVisible(false);
		
		monthSet.setText(month);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				JPanel contentPane = new LoginSummaryPanel(myFrame,l1);
				myFrame.setContentPane(contentPane);
				myFrame.setVisible(true);
				
			}
		});
		lblNewLabel_1.setBorder(null);
		lblNewLabel_1.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblNewLabel_1.setIcon(new ImageIcon(CategoryChartPanel.class.getResource("/ProjectApp/images/Picture1.png")));
		lblNewLabel_1.setBounds(22, 114, 64, 64);
		add(lblNewLabel_1);
		table.getTableHeader().setReorderingAllowed(false);
	}
	
	private void setTableModelOfFrame(ArrayList<Transaction> trans ){
		
		CategoryChartPanelTable ccpt = new CategoryChartPanelTable(trans);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		
	
		table.setModel(ccpt);
		table.setRowHeight(22);
		for(int i = 0 ; i < table.getColumnCount() ; i++){
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);		
		}
	
	}
}
