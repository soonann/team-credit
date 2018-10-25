package teamCreditProjectApp.dataAccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import teamCreditProjectApp.entity.Chart;

import teamCreditProjectApp.entity.Transaction;

public class TransactionDA {
	/**
	 * Purpose:	This method gets the max id from the expense table
	 * 			and returns it to the calling problem
	 * Input:	Nil
	 * Return:	int
	 */
	
	
	private static int getNextTransactionId() {
		// declare local variables
		int id = 0;
		ResultSet rs = null;
		DBController db = new DBController();
		String dbQuery;
		PreparedStatement pstmt;

		// step 1 - connect to database
		db.getConnection();

		// step 2 - declare the SQL statement
		dbQuery = "SELECT MAX(id) FROM transaction";
		pstmt = db.getPreparedStatement(dbQuery);

		// step 3 - execute query
		try {
			rs = pstmt.executeQuery();
			if (rs.next()) { // first record found
				id = rs.getInt(1) + 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// step 4 - close connection
		db.terminate();
		return id;
	}
	
	
	/**
	 * Purpose:	This method converts a ResultSet object into an
	 * 			Expense object
	 * Input:	ResultSet object
	 * Return:	Expense object
	 */
	
	
	
	private static Transaction convertToTransaction(ResultSet rs) throws SQLException {
		Transaction transaction;
		int id = rs.getInt("id");
		String cardNumber = rs.getString("cardNumber");
		String transactionDate = rs.getString("transactionDate");
		double transactionAmount = rs.getDouble("transactionAmount");
		String transactionCurrency = rs.getString("transactionCurrency");
		double originalAmount = rs.getDouble("originalAmount");
		double conversionRate = rs.getDouble("conversionRate");
		String description = rs.getString("description");
		String category = rs.getString("category");
		transaction = new Transaction(id,cardNumber,transactionDate,transactionAmount,transactionCurrency,originalAmount,conversionRate,description,category);

		return transaction;
	}
	private static String convertToString(ResultSet rs) throws SQLException {
		
		
		String category = rs.getString("category");
		

		return category;
	}
	
	private static double convertToTransactionSum(ResultSet rs) throws SQLException {
		
		
		double transactionSum = rs.getDouble("sum(transactionAmount)");
		

		return transactionSum;
	}
	private static Chart convertToChart(ResultSet rs) throws SQLException {
		Chart chart;

		String category = rs.getString("category");
		double totalAmount = rs.getDouble("sum(transactionAmount)");
		
		chart = new Chart(category, totalAmount);
		
		return chart;
	}
	/**
	 * Purpose:	This method takes in an Expense object and
	 * 			store it into the database.
	 * Input:	Expense object
	 * Return:	boolean
	 */
	
	public static boolean createTransactionOld(Transaction transaction) {
		// declare local variables
		boolean success = false;
		DBController db = new DBController();
		String dbQuery;
		PreparedStatement pstmt;

		// step 1 - establish connection to database
		db.getConnection();

		// step 2 - declare the SQL statement
		dbQuery = "INSERT INTO transaction(id, cardNumber, transactionDate, description, category, transactionAmount) VALUES(?, ?, ?, ?, ?, ?)";
		pstmt = db.getPreparedStatement(dbQuery);

		// step 3 - to insert record using executeUpdate method
		try {
			pstmt.setInt(1, getNextTransactionId());
			pstmt.setString(2, transaction.getCardNumber());
			pstmt.setString(3, transaction.getTransactionDate());
			pstmt.setString(4, transaction.getDescription());
			pstmt.setString(5, transaction.getCategory());
			pstmt.setDouble(6, transaction.getTransactionAmount());
			if (pstmt.executeUpdate() == 1)
				success = true;
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// step 4 - close connection
		db.terminate();

		return success;
	}
	
	
	
	public static boolean createTransaction(Transaction transaction) {
		// declare local variables
		boolean success = false;
		DBController db = new DBController();
		String dbQuery;
		PreparedStatement pstmt;

		// step 1 - establish connection to database
		db.getConnection();

		// step 2 - declare the SQL statement
		dbQuery = "INSERT INTO transaction(id, cardNumber, transactionDate, transactionAmount, transactionCurrency, originalAmount, conversionRate, description, category) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		pstmt = db.getPreparedStatement(dbQuery);

		// step 3 - to insert record using executeUpdate method
		try {
			pstmt.setInt(1, getNextTransactionId());
			pstmt.setString(2, transaction.getCardNumber());
			pstmt.setString(3, transaction.getTransactionDate());
			pstmt.setDouble(4, transaction.getTransactionAmount());
			pstmt.setString(5, transaction.getTransactionCurrency());
			pstmt.setDouble(6, transaction.getOriginalAmount());
			pstmt.setDouble(7, transaction.getConversionRate());
			pstmt.setString(8, transaction.getDescription());
			pstmt.setString(9, transaction.getCategory());
			if (pstmt.executeUpdate() == 1)
				success = true;
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// step 4 - close connection
		db.terminate();

		return success;
	}
	
	public static boolean createTransactionLocal(Transaction transaction) {
		// declare local variables
		boolean success = false;
		DBController db = new DBController();
		String dbQuery;
		PreparedStatement pstmt;

		// step 1 - establish connection to database
		db.getConnection();

		// step 2 - declare the SQL statement
		dbQuery = "INSERT INTO transaction(id, cardNumber, transactionDate, transactionAmount, description, category) VALUES(?, ?, ?, ?, ?, ?)";
		pstmt = db.getPreparedStatement(dbQuery);

		// step 3 - to insert record using executeUpdate method
		try {
			pstmt.setInt(1, getNextTransactionId());
			pstmt.setString(2, transaction.getCardNumber());
			pstmt.setString(3, transaction.getTransactionDate());
			pstmt.setDouble(4, transaction.getTransactionAmount());
			pstmt.setString(5, transaction.getDescription());
			pstmt.setString(6, transaction.getCategory());
			if (pstmt.executeUpdate() == 1)
				success = true;
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// step 4 - close connection
		db.terminate();

		return success;
	}
	/**
	 * Purpose:	This method retrieves all the expense records in the
	 * 			database and returns them in an ArrayList.
	 * Input:	Nil
	 * Return:	ArrayList of Expense objects
	 */
	
	
	
	public static ArrayList<Transaction> retrieveAllTransaction() {
		// declare local variables
		ArrayList<Transaction> list = new ArrayList<Transaction>();
		ResultSet rs = null;
		DBController db = new DBController();
		String dbQuery;

		// Step 1 - connect to database
		db.getConnection();

		// step 2 - declare the SQL statement
		dbQuery = "SELECT * FROM transaction";

		// step 3 - using DBControlle readRequest method
		rs = db.readRequest(dbQuery);
		try {
			while (rs.next()) {
				Transaction transaction = convertToTransaction(rs);
				list.add(transaction);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// step 4 - close connection
		db.terminate();

		return list;
	}
	
	public static Transaction retrieveTransactionById(int id) {
		// declare local variables
		Transaction transaction = null;
		ResultSet rs = null;
		DBController db = new DBController();
		String dbQuery;
		PreparedStatement pstmt;

		// step 1 - connect to database
		db.getConnection();

		// step 2 - declare the SQL statement
		dbQuery = "SELECT * FROM transaction WHERE id = ?";
		pstmt = db.getPreparedStatement(dbQuery);

		// step 3 - execute query
		try {
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) { // first record found
				transaction = convertToTransaction(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// step 4 - close connection
		db.terminate();
		return transaction;
	}
	
	public static ArrayList<String> retrieveListOfCategoryFromTransaction() {
		
		// declare local variables
				ArrayList<String> list = new ArrayList<String>();
				ResultSet rs = null;
				DBController db = new DBController();
				String dbQuery;
				PreparedStatement pstmt;

				// step 1 - connect to database
				db.getConnection();

				// step 2 - declare the SQL statement
				dbQuery = "SELECT DISTINCT category FROM transaction";
				pstmt = db.getPreparedStatement(dbQuery);

				// step 3 - execute query
				try {
					
					rs = pstmt.executeQuery();
					while (rs.next()) {
						String category= convertToString(rs);
						list.add(category);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				// step 4 - close connection
				db.terminate();

				return list;
		
		
	}

	
	public static ArrayList<Transaction> retrieveTransactionsByDateAndCardNumber(String monthYearDate, String cardNumber) {
		
		// declare local variables
				ArrayList<Transaction> list = new ArrayList<Transaction>();
				ResultSet rs = null;
				DBController db = new DBController();
				String dbQuery;
				PreparedStatement pstmt;

				// step 1 - connect to database
				db.getConnection();

				// step 2 - declare the SQL statement
				dbQuery = "SELECT * FROM transaction WHERE cardNumber = ? AND transactionDate LIKE ?";
				pstmt = db.getPreparedStatement(dbQuery);

				// step 3 - execute query
				try {
					pstmt.setString(1, cardNumber);
					pstmt.setString(2, monthYearDate);
					rs = pstmt.executeQuery();
					while (rs.next()) {
						Transaction transaction = convertToTransaction(rs);
						list.add(transaction);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				// step 4 - close connection
				db.terminate();

				return list;
		
		
	}

public static ArrayList<Transaction> retrieveTransactionByCategoryTransactionDateAndCardNumbers(String category, String monthYearDate, ArrayList<String> cardNumber) {
		
		// declare local variables
				ArrayList<Transaction> list = new ArrayList<Transaction>();
				ResultSet rs = null;
				DBController db = new DBController();
				String dbQuery;
				PreparedStatement pstmt;
				

				// step 1 - connect to database
				db.getConnection();

				// step 2 - declare the SQL statement
				dbQuery = "SELECT * FROM transaction WHERE category = ? AND transactionDate LIKE ? AND (cardNumber= ?" ; 
				if(cardNumber.size() > 1){
						
						for(int i = 1 ; i < cardNumber.size() ; i++){
							
							dbQuery+= " OR cardNumber= ?";
							
							
						}
				}
				
				dbQuery+= ") ORDER BY transactionDate DESC";
				pstmt = db.getPreparedStatement(dbQuery);
				
				// step 3 - execute query	
				try {
					
						
					
					if(cardNumber.size() > 1){
						
						
						for(int i = 1 ; i < cardNumber.size() ; i++ ){
							
							pstmt.setString((i+3), cardNumber.get(i));

						}
					
					}
					
					pstmt.setString(1, category);
					pstmt.setString(2, monthYearDate);
					pstmt.setString(3, cardNumber.get(0));	
					
					rs = pstmt.executeQuery();
					while (rs.next()) {
						Transaction transaction = convertToTransaction(rs);
						list.add(transaction);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				// step 4 - close connection
				db.terminate();

				return list;
		
		
	}
	
	public static ArrayList<Chart> retrieveTransactionSumByTotalAmountDateAndCardNumber(String monthYearDate, ArrayList<String> cardNumber) {
		
		// declare local variables
				ArrayList<Chart> list = new ArrayList<Chart>();
				ResultSet rs = null;
				DBController db = new DBController();
				String dbQuery;
				PreparedStatement pstmt;
				

				// step 1 - connect to database
				db.getConnection();

				// step 2 - declare the SQL statement
				dbQuery = "SELECT sum(transactionAmount), category FROM transaction WHERE transactionDate LIKE ? AND (cardNumber= ? " ; 
				if(cardNumber.size() > 1){
						
						for(int i = 1 ; i < cardNumber.size() ; i++){
							
							dbQuery+= " OR cardNumber= ?";
							
							
						}
				}
				
				dbQuery+= ") GROUP BY category";
				pstmt = db.getPreparedStatement(dbQuery);
				
				// step 3 - execute query	
				try {
					
						
					
					if(cardNumber.size() > 1){
						
						
						for(int i = 1 ; i < cardNumber.size() ; i++ ){
							
							pstmt.setString((i+2), cardNumber.get(i));

						}
					
					}
					
					
					pstmt.setString(1, monthYearDate);
					pstmt.setString(2, cardNumber.get(0));	
					
					rs = pstmt.executeQuery();
					while (rs.next()) {
						Chart chart = convertToChart(rs);
						list.add(chart);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				// step 4 - close connection
				db.terminate();

				return list;
		
		
	}
	
	
	
	public static double retrieveSumOfTransactionByCardNumberAndMonth(String monthYearDate, String cardNumber) {
		
		// declare local variables
				double transactionSum = 22;
				ResultSet rs = null;
				DBController db = new DBController();
				String dbQuery;
				PreparedStatement pstmt;
				

				// step 1 - connect to database
				db.getConnection();

				// step 2 - declare the SQL statement
				dbQuery = "SELECT sum(transactionAmount) FROM oopdb.transaction WHERE transactionDate LIKE ? AND cardNumber= ?" ; 
			
				
				pstmt = db.getPreparedStatement(dbQuery);
				
				// step 3 - execute query	
				try {

				
					pstmt.setString(1, monthYearDate);
					pstmt.setString(2, cardNumber);	
					
					rs = pstmt.executeQuery();
					while (rs.next()) {
						transactionSum = convertToTransactionSum(rs);
						
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				// step 4 - close connection
				db.terminate();

				return transactionSum;
		
		
	}
	
	
	
	/**
	 * Purpose:	This method retrieves an expense record by id
	 * 			and returns it to the calling program.
	 * Input:	int 
	 * Return:	Expense object
	 */
	
	/*
	public static Transaction retrieveExpenseById(int id) {
		// declare local variables
		Expense expense = null;
		ResultSet rs = null;
		DBController db = new DBController();
		String dbQuery;
		PreparedStatement pstmt;

		// step 1 - connect to database
		db.getConnection();

		// step 2 - declare the SQL statement
		dbQuery = "SELECT * FROM expense WHERE id = ?";
		pstmt = db.getPreparedStatement(dbQuery);

		// step 3 - execute query
		try {
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) { // first record found
				expense = convertToExpense(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// step 4 - close connection
		db.terminate();
		return expense;
	}
	
	
	*/
	public static ArrayList<Transaction> retrieveTransactionByCardNumber(String cardNumber) {
		// declare local variables
		ArrayList<Transaction> list = new ArrayList<Transaction>();
		ResultSet rs = null;
		DBController db = new DBController();
		String dbQuery;
		PreparedStatement pstmt;

		// step 1 - connect to database
		db.getConnection();

		// step 2 - declare the SQL statement
		dbQuery = "SELECT * FROM transaction WHERE cardNumber = ?";
		pstmt = db.getPreparedStatement(dbQuery);

		// step 3 - execute query
		try {
			pstmt.setString(1, cardNumber);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Transaction transaction = convertToTransaction(rs);
				list.add(transaction);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// step 4 - close connection
		db.terminate();

		return list;
	}

	
	
	
	public static boolean updateTransaction(Transaction transaction) {
		//declare local variables
		boolean success = false;
		DBController db = new DBController();
		String dbQuery;	
		PreparedStatement pstmt;
		
		//step 1 - establish connection to database
		db.getConnection();		

		//step 2 - declare the SQL statement
		dbQuery = "UPDATE transaction SET cardNumber = ?, transactionDate = ?, description = ?, category = ?, transactionAmount = ? WHERE id = ?";
		pstmt = db.getPreparedStatement(dbQuery);
		//step 3 - to update record using executeUpdate method
		try {
			pstmt.setString(1, transaction.getCardNumber());
			pstmt.setString(2, transaction.getTransactionDate());
			pstmt.setString(3, transaction.getDescription());
			pstmt.setString(4, transaction.getCategory());
			pstmt.setDouble(5, transaction.getTransactionAmount());
			pstmt.setInt(6, transaction.getId());
			if (pstmt.executeUpdate() == 1)
				success = true;
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(success);
	    //step 4 - close connection
		db.terminate();
		
		return success;		
	}
	

	
	//other da 

	public static ArrayList<Transaction> retrieveTransactionByCardNumberAndDate(String cardNumber,String month, int year) {
		// declare local variables
		ArrayList<Transaction> list = new ArrayList<Transaction>();
		ResultSet rs = null;
		DBController db = new DBController();
		String dbQuery;
		PreparedStatement pstmt;
		

		// step 1 - connect to database
		db.getConnection();

		// step 2 - declare the SQL statement
		dbQuery = "SELECT * FROM transaction WHERE transactionDate LIKE ? AND cardNumber = ?";
		
		String temp ="%%/"+month+"/"+year;

		pstmt = db.getPreparedStatement(dbQuery);

		// step 3 - execute query
		try {
			pstmt.setString(1, temp);
			pstmt.setString(2, cardNumber);
			
			
			

			rs = pstmt.executeQuery();
			while (rs.next()) {
				Transaction transaction = convertToTransaction(rs);
				list.add(transaction);
			}
		} catch (Exception e) {
			e.printStackTrace();

		}

		// step 4 - close connection
		db.terminate();

		return list;
	}

	public static ArrayList<Transaction> retrieveTransactionByYear(int year) {
		// declare local variables
		ArrayList<Transaction> list = new ArrayList<Transaction>();
		ResultSet rs = null;
		DBController db = new DBController();
		String dbQuery;
		PreparedStatement pstmt;
		

		// step 1 - connect to database
		db.getConnection();

		// step 2 - declare the SQL statement
		dbQuery = "SELECT * FROM transaction WHERE transactionDate LIKE ?";
		
		String date = "%%/%%/"+year;
		

		pstmt = db.getPreparedStatement(dbQuery);

		// step 3 - execute query
		try {
			pstmt.setString(1, date);
			
			
			

			rs = pstmt.executeQuery();
			while (rs.next()) {
				Transaction transaction = convertToTransaction(rs);
				list.add(transaction);
			}
		} catch (Exception e) {
			e.printStackTrace();

		}

		// step 4 - close connection
		db.terminate();

		return list;
	}

	public static void main(String[] args) {
		/*
		ArrayList <String> s = new ArrayList<String>();
		s.add("4628-1111-1111-1111");
		s.add("4628-1234-1234-1234");
		
		ArrayList<Chart> c1 = retrieveTransactionSumByTotalAmountDateAndCardNumber("%/12/2016",s);
		System.out.println(c1.get(0).getCategoryName());
		*/
		 System.out.print(retrieveSumOfTransactionByCardNumberAndMonth("%/03/2016", "4628-1234-1234-1234"));
		
	}
}
