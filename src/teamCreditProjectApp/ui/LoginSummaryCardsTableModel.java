package teamCreditProjectApp.ui;




	import java.awt.Color;
	import java.awt.Font;
	import java.awt.event.MouseAdapter;
	import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;

	import javax.swing.ImageIcon;
	import javax.swing.JLabel;
	import javax.swing.border.LineBorder;
	import javax.swing.table.AbstractTableModel;

import teamCreditProjectApp.entity.CardPopout;
import teamCreditProjectApp.entity.Transaction;

	public class LoginSummaryCardsTableModel extends AbstractTableModel {
		
		DecimalFormat df = new DecimalFormat("00.00");
		private int rowCount, colCount;
		private String[] colName = {"Card Name","Card Number","Total Amount Spent (SGD)"};
		private Object[][] data;
		
		
		public LoginSummaryCardsTableModel(ArrayList<CardPopout> listOfObject) {
	        rowCount = listOfObject.size();
	        colCount = colName.length;
	        data = new Object[rowCount][colCount];
	        for (int i = 0; i < rowCount; i++) {
	          
	        	CardPopout cd = (CardPopout)(listOfObject.get(i)); 
	            
	            data[i][0] = cd.getCardType();
	         
	            data[i][1] = cd.getCardNumber();

	            data[i][2] = "$"+ df.format(cd.getTransactionAmount());
	         
	           
	            
	        }              
	    } 
		
		

		
		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return colCount;
		}

		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return rowCount;
		}

		@Override
		public Object getValueAt(int row, int col) {
			// TODO Auto-generated method stub
			return data[row][col];
		}

		public String getColumnName(int col){

			return colName[col];
		}
		
		public Class getColumnClass(int c) {
			
	        return getValueAt(0, c).getClass();
	        
	    }
		
		
	
}
