package teamCreditProjectApp.ui;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import teamCreditProjectApp.entity.Transaction;

public class OverseasTransactionTable extends AbstractTableModel {

	private int rowCount, colCount;
	private String[] colName = {"Date", "Description", "Category", "Amount"};
	private Object[][] data;
	
	public OverseasTransactionTable(ArrayList<Transaction> listOfObject) {
        rowCount = listOfObject.size();
        colCount = colName.length;
        data = new Object[rowCount][colCount];
        for (int i = 0; i < rowCount; i++) {

        	Transaction e1 = (Transaction)(listOfObject.get(i)); 

        	data[i][0] = e1.getTransactionDate();
        	data[i][1] = e1.getDescription();	
            data[i][2] = e1.getCategory();
            data[i][3] = e1.getTransactionAmount();
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

}