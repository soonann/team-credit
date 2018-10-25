package teamCreditProjectApp.ui;
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import teamCreditProjectApp.dataAccess.TransactionDA;
import teamCreditProjectApp.entity.Transaction;

public class EvenOddRenderer implements TableCellRenderer{

	  public static final DefaultTableCellRenderer DEFAULT_RENDERER = new DefaultTableCellRenderer();

	  private String cardNo;
	  private String month;
	  private int year;

	private static final JFrame JFrame = null;

	public EvenOddRenderer(String cardNo,String month,int year){
		this.cardNo = cardNo;
		this.month = month;
		this.year = year;
	
	  
	}

	  public Component getTableCellRendererComponent(JTable table, Object value,
	      boolean isSelected, boolean hasFocus, int row, int column) {
	    Component renderer = DEFAULT_RENDERER.getTableCellRendererComponent(
	        table, value, isSelected, hasFocus, row, column);
	    ((JComponent) renderer).setOpaque(true);
	    Color foreground = null, background = null;
	   // CardDetailsPanel cdp = new CardDetailsPanel(null);

	    ArrayList<Transaction> tlist = TransactionDA.retrieveTransactionByCardNumberAndDate(cardNo, 
	    		month, year);

	    ArrayList<Integer> newList = new ArrayList<Integer>();
	    for(int i=0;i<tlist.size();i++){
	    	if(!(tlist.get(i).getTransactionCurrency()== null)){
	    	    
	    		newList.add(i);
	    	}

	    }

	    for(int k=0;k<newList.size(); k++){
	    	if(newList.get(k) == row){
	    		background = Color.GRAY;
	    	}

	
	    }
	    


	 /*   if(row == 2){
	        background = Color.BLACK;
	    }*/
	    	
	   
	    
	    	    renderer.setForeground(foreground);
	    	    renderer.setBackground(background);
	    	    return renderer;
	}
}
	
