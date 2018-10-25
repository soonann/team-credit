package teamCreditProjectApp.ui;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.table.AbstractTableModel;

import teamCreditProjectApp.entity.Transaction;

public class TransactionTableModel extends AbstractTableModel {

	private int rowCount, colCount;
	private String[] colName = {"Date", "Description", "TransactionAmount", "OverseasAmount"};
	private Object[][] data;

	public TransactionTableModel(ArrayList<Transaction> listOfObject) {
      rowCount = listOfObject.size();
      colCount = colName.length;
      data = new Object[rowCount][colCount];
      System.out.println("======"+listOfObject.size());
      for (int i = 0; i < rowCount; i++) {
      Transaction e1 = (Transaction)(listOfObject.get(i));   
      
        data[i][0] = e1.getTransactionDate();
        data[i][1] = e1.getDescription();
        data[i][2] = "SGD$"+e1.getTransactionAmount();
        
        
        if(!(listOfObject.get(i).getTransactionCurrency() == null) ){
        	 data[i][3] = listOfObject.get(i).getTransactionCurrency()+e1.getOriginalAmount();
        }
        else{
        	data[i][3] = e1.getOriginalAmount();
        }
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
	public Object[][] getData() {
		return data;
	}

	public void setData(Object[][] data) {
		this.data = data;
	}

	@Override
	public String getColumnName(int col){
		return colName[col];
	}
	
}
