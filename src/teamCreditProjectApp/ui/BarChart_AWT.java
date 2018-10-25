
package teamCreditProjectApp.ui;
import org.jfree.ui.ApplicationFrame;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset; 
import org.jfree.data.category.DefaultCategoryDataset; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities;

import teamCreditProjectApp.dataAccess.TransactionDA;
import teamCreditProjectApp.entity.Transaction; 

public class BarChart_AWT extends JPanel {
	
	 
	   public static CategoryDataset createDataset(int year){
		
		   ArrayList<Transaction> result = TransactionDA.retrieveTransactionByYear(year);
	      for(int i=0;i<result.size();i++){
	      }
		   String amountPaid = "Amount Paid";        
	      String outstandingAmount = "Outstanding Amount";        
	      String currentExpense = "Current Month Expense";    
	     
	      GregorianCalendar gc = new GregorianCalendar();
	      int calendarMonth = gc.getTime().getMonth();

	      String[] month = {"January","February","March","April","May","June","July",
	    		  "August","September","October","November","December"};


	      double totalAmount =0;
	      DefaultCategoryDataset dataset = new DefaultCategoryDataset();  
	      	for(int j=0; j<12; j++){
	      		totalAmount =0;
	      			for(int i=0; i < result.size(); i++){	
	      					if(Integer.parseInt(result.get(i).getTransactionDate().substring(3, 5)) == (calendarMonth+j)){  
	      						totalAmount += result.get(i).getTransactionAmount();
	      						dataset.addValue( totalAmount , currentExpense, month[calendarMonth+(j-1)]);
	      					}	
	      			}
	      	}
	      return dataset; 
	   }
	   
	   public static void main( String[ ] args )
	   {
		   
		    
	   }

}
