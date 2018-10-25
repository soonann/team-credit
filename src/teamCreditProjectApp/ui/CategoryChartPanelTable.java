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
import teamCreditProjectApp.entity.CardDetails;
import teamCreditProjectApp.entity.Transaction;

public class CategoryChartPanelTable extends AbstractTableModel {

	private int rowCount, colCount;
	private String[] colName = {"Card Number","Date","Amount(SGD)"};
	private Object[][] data;
	
	
	public CategoryChartPanelTable(ArrayList<Transaction> listOfObject) {
        rowCount = listOfObject.size();
        colCount = colName.length;
        data = new Object[rowCount][colCount];
        DecimalFormat df = new DecimalFormat("0.00");
        for (int i = 0; i < rowCount; i++) {
          
            Transaction ts = (Transaction)(listOfObject.get(i)); 
            
            data[i][0] = ts.getCardNumber();
           
            data[i][1] = ts.getTransactionDate();

            data[i][2] = "$" +df.format(ts.getTransactionAmount());
         
           
            
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
