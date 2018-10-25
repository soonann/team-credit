package teamCreditProjectApp.ui;
import java.awt.BorderLayout;
import java.awt.Graphics2D;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

import teamCreditProjectApp.dataAccess.TransactionDA;
import teamCreditProjectApp.entity.Transaction;

public class pdf extends JFrame{
	private static String cardNo;
	private static String month;
	private static int year;
	private static String creditLimit;
	private static String minimumSum;
	private static JTable tblList;
	
	




	public pdf(String cardNo, String month, int year, String creditLimit, String minimumSum, JTable tblList) {
		super();
		this.cardNo = cardNo;
		this.month = month;
		this.year = year;
		this.creditLimit = creditLimit;
		this.minimumSum = minimumSum;
		this.tblList= tblList;
		
		String[] colName = {"Date", "Description", "TransactionAmount", "OverseasAmount"};
		Object[][] data = {{"01/02/2016","Cotton On@TampinesMall","$56.80","0"}};

		
		JTable table = new JTable(data, colName);
		//JPanel tablePanel = new JPanel(new BorderLayout());
		//tablePanel.add(table.getTableHeader(), BorderLayout.NORTH);
		//tablePanel.add(table, BorderLayout.CENTER);


		
		
		ArrayList<Transaction> tlist = TransactionDA.retrieveTransactionByCardNumberAndDate(cardNo, month, year);
		Document document = new Document();
		double total = 0;
		try{
			PdfWriter writer;
			writer = PdfWriter.getInstance(document, new FileOutputStream("CardDetails.pdf"));
			PdfPTable pdfTable = new PdfPTable(tblList.getColumnCount());
			for (int i = 0; i < tblList.getColumnCount(); i++) {
                pdfTable.addCell(tblList.getColumnName(i));
                
            }
			
			for (int i = 0; i < tblList.getRowCount(); i++) {
				pdfTable.addCell(tblList.getValueAt(i, 0).toString());
				pdfTable.addCell(tblList.getValueAt(i, 1).toString());
				pdfTable.addCell(tblList.getValueAt(i, 2).toString());
				pdfTable.addCell(tblList.getValueAt(i, 3).toString());
				pdfTable.setWidthPercentage(100);
				String totalVal = tblList.getValueAt(i, 2).toString();
				total += Double.parseDouble(totalVal.substring(4,totalVal.length()));
				// fixed 
            }

			
			
			document.open();
			//title
			Paragraph title = new Paragraph();
			Paragraph line = new Paragraph();
			
			title.add("CardDetails PDF");
			title.setAlignment(Element.ALIGN_CENTER);
			line.add("--------------------------------------------------------------");
			line.setAlignment(Element.ALIGN_CENTER);

			document.add(title);
			document.add(line);
			
		
			//body
			Paragraph statementDate = new Paragraph();
			Paragraph cardNumber = new Paragraph();
			Paragraph cLimit = new Paragraph();
			Paragraph minimumPayment = new Paragraph();
			Paragraph space = new Paragraph();
			Paragraph totalPayment = new Paragraph();
			statementDate.add("Statement Date : 31/"+month+"/"+year);
			cardNumber.add("Card Number : "+cardNo);
			cLimit.add("Credit Limit : SGD($)"+creditLimit);
			minimumPayment.add("Minimum Payment : "+minimumSum);
			space.add(" ");
			totalPayment.add("Total Payment : SGD$"+total);
			
			
			document.add(statementDate);
			document.add(cardNumber);
			document.add(cLimit);
			document.add(minimumPayment);
			document.add(totalPayment);
			document.add(space);
			document.add(pdfTable);
			
			
			PdfContentByte cb = writer.getDirectContent();
		    PdfTemplate tp = cb.createTemplate(500, 500);
		    Graphics2D g2;
		    
		    g2 = tp.createGraphicsShapes(500, 500);
		    table.print(g2);
		    g2.dispose();
		    cb.addTemplate(tp, 30, 300);
		    
		    
			
			document.close();
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	





	
}
