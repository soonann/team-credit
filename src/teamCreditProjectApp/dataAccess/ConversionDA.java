package teamCreditProjectApp.dataAccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import teamCreditProjectApp.entity.Conversion;
import teamCreditProjectApp.entity.Payment;

public class ConversionDA {
	/**
	 * Purpose:	This method gets the max id from the expense table
	 * 			and returns it to the calling problem
	 * Input:	Nil
	 * Return:	int
	 */
	
	/*
	private static int getNextExpenseId() {
		// declare local variables
		int id = 0;
		ResultSet rs = null;
		DBController db = new DBController();
		String dbQuery;
		PreparedStatement pstmt;

		// step 1 - connect to database
		db.getConnection();

		// step 2 - declare the SQL statement
		dbQuery = "SELECT MAX(id) FROM expense";
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
	*/
	
	/**
	 * Purpose:	This method converts a ResultSet object into an
	 * 			Expense object
	 * Input:	ResultSet object
	 * Return:	Expense object
	 */
	
	
	
	private static Conversion convertToConversion(ResultSet rs) throws SQLException {
		Conversion conversion;
		
		String currency = rs.getString("currency");
		double conversionRate = rs.getDouble("conversionRate");
		
		conversion = new Conversion(currency,conversionRate);

		return conversion;
	}
	/**
	 * Purpose:	This method takes in an Expense object and
	 * 			store it into the database.
	 * Input:	Expense object
	 * Return:	boolean
	 */
	
	/*
	public static boolean createExpense(Expense expense) {
		// declare local variables
		boolean success = false;
		DBController db = new DBController();
		String dbQuery;
		PreparedStatement pstmt;

		// step 1 - establish connection to database
		db.getConnection();

		// step 2 - declare the SQL statement
		dbQuery = "INSERT INTO expense(id, dateSpend, category, amount, content) VALUES(?, ?, ?, ?, ?)";
		pstmt = db.getPreparedStatement(dbQuery);

		// step 3 - to insert record using executeUpdate method
		try {
			pstmt.setInt(1, getNextExpenseId());
			pstmt.setString(2, expense.getDateSpend());
			pstmt.setString(3, expense.getCategory());
			pstmt.setDouble(4, expense.getAmount());
			pstmt.setString(5, expense.getContent());
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
	
	*/
	/**
	 * Purpose:	This method retrieves all the expense records in the
	 * 			database and returns them in an ArrayList.
	 * Input:	Nil
	 * Return:	ArrayList of Expense objects
	 */
	
	
	
	public static ArrayList<Conversion> retrieveAllConversion() {
		// declare local variables
		ArrayList<Conversion> list = new ArrayList<Conversion>();
		ResultSet rs = null;
		DBController db = new DBController();
		String dbQuery;

		// Step 1 - connect to database
		db.getConnection();

		// step 2 - declare the SQL statement
		dbQuery = "SELECT * FROM conversion";

		// step 3 - using DBControlle readRequest method
		rs = db.readRequest(dbQuery);
		try {
			while (rs.next()) {
				Conversion conversion = convertToConversion(rs);
				list.add(conversion);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// step 4 - close connection
		db.terminate();

		return list;
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

	public static ArrayList<Expense> retrieveExpenseByCategory(String category) {
		// declare local variables
		ArrayList<Expense> list = new ArrayList<Expense>();
		ResultSet rs = null;
		DBController db = new DBController();
		String dbQuery;
		PreparedStatement pstmt;

		// step 1 - connect to database
		db.getConnection();

		// step 2 - declare the SQL statement
		dbQuery = "SELECT * FROM expense WHERE category = ?";
		pstmt = db.getPreparedStatement(dbQuery);

		// step 3 - execute query
		try {
			pstmt.setString(1, category);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Expense expense = convertToExpense(rs);
				list.add(expense);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// step 4 - close connection
		db.terminate();

		return list;
	}

	*/
	
	
	/*
	public static boolean updateExpense(Expense expense) {
		//declare local variables
		boolean success = false;
		DBController db = new DBController();
		String dbQuery;	
		PreparedStatement pstmt;
		
		//step 1 - establish connection to database
		db.getConnection();		

		//step 2 - declare the SQL statement
		dbQuery = "UPDATE expense SET dateSpend = ?, category = ?, amount = ?, content = ? WHERE id = ?";
		pstmt = db.getPreparedStatement(dbQuery);
		
		//step 3 - to update record using executeUpdate method
		try {
			pstmt.setString(1, expense.getDateSpend());
			pstmt.setString(2, expense.getCategory());
			pstmt.setDouble(3, expense.getAmount());
			pstmt.setString(4, expense.getContent());
			pstmt.setInt(5, expense.getId());
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
	public static boolean deleteExpense(int id) {
		//declare local variables
		boolean success = false;
		DBController db = new DBController();
		String dbQuery;	
		PreparedStatement pstmt;
		
		//step 1 - establish connection to database
		db.getConnection();		

		//step 2 - declare the SQL statement
		dbQuery = "DELETE FROM expense WHERE id = ?";
		pstmt = db.getPreparedStatement(dbQuery);
		
		//step 3 - to delete record using executeUpdate method
		try {
			pstmt.setInt(1, id);
			if (pstmt.executeUpdate() == 1)
				success = true;
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	    //step 4 - close connection
		db.terminate();
		
		return success;		
	}
*/
	public static void main(String[] args) {
		
	}
}
