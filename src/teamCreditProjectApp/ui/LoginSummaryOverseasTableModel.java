package teamCreditProjectApp.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import javax.swing.table.AbstractTableModel;
import teamCreditProjectApp.entity.CardDetails;

public class LoginSummaryOverseasTableModel extends AbstractTableModel {

	private int rowCount, colCount;
	private String[] colName = {"Card Number","Activation Period","Deactivate Cards"};
	private Object[][] data;
	
	
	public LoginSummaryOverseasTableModel(ArrayList<CardDetails> listOfObject) {
        rowCount = listOfObject.size();
        colCount = colName.length;
        data = new Object[rowCount][colCount];
        for (int i = 0; i < rowCount; i++) {
          
            CardDetails cd = (CardDetails)(listOfObject.get(i)); 
            
            data[i][0] = cd.getCardNumber();
            if(cd.getEndDate() == null )
            {data[i][1] = cd.getStartDate() + " - "; }
            else 
            	{data[i][1] = cd.getStartDate() + " - " +cd.getEndDate() ; }
            
            data[i][2] = new ImageIcon(LoginSummaryOverseasTableModel.class.getResource("/ProjectApp/images/deactivation.png"));
         
           
            
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
