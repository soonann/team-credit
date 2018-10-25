package teamCreditProjectApp.ui;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import teamCreditProjectApp.entity.CardDetails;

public class RedeemInfoTableModel extends AbstractTableModel {

	private int rowCount, colCount;
	private String[] colName = {"Reward Points", "Expiring Reward Points"};
	private Object[][]data;
	
	   public RedeemInfoTableModel(ArrayList<CardDetails> listOfObject) {
		   rowCount = listOfObject.size();
	        colCount = colName.length;
	        data = new Object[rowCount][colCount];
	        for (int i = 0; i < rowCount; i++) {
	           /*Copy an ArrayList element to an instance of Expenses*/
	        	CardDetails r1 = (CardDetails)(listOfObject.get(i)); 
	                        
	            data[i][0] = r1.getRewardPoints();
	            data[i][1] = r1.getExpiringRewardPoints();
	        
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
	
	@Override
	public String getColumnName(int col) {
		return colName[col];
	}

}
