package teamCreditProjectApp.dataAccess;

import java.sql.PreparedStatement;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import teamCreditProjectApp.entity.CardDetails;
import teamCreditProjectApp.entity.CardUsage;

public class CardUsageDA {
	private static int getNextCardUsageId() {
		// declare local variables
		int id = 0;
		ResultSet rs = null;
		DBController db = new DBController();
		String dbQuery;
		PreparedStatement pstmt;

		// step 1 - connect to database
		db.getConnection();

		// step 2 - declare the SQL statement
		dbQuery = "SELECT MAX(id) FROM cardUsage";
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
	private static CardUsage convertToCardUsage(ResultSet rs) throws SQLException {
		
		CardUsage cardUsage;
		
		int id = rs.getInt("id");
		String cardNumber = rs.getString("cardNumber");
		double usageLimit = rs.getDouble("usageLimit");
		String dateEffective = rs.getString("dateEffective");
		
		
		
		
		
		cardUsage = new CardUsage(id, cardNumber,usageLimit,dateEffective);
		return cardUsage;
	}
	
	public static ArrayList<CardUsage> retrieveAllCardUsage() {
		// declare local variables
		ArrayList<CardUsage> list = new ArrayList<CardUsage>();
		ResultSet rs = null;
		DBController db = new DBController();
		String dbQuery;
	
		// Step 1 - connect to database
		db.getConnection();
	
		// step 2 - declare the SQL statement
		dbQuery = "SELECT * FROM cardusage";
	
		// step 3 - using DBControlle readRequest method
		rs = db.readRequest(dbQuery);
		try {
			while (rs.next()) {
				CardUsage cardUsage = convertToCardUsage(rs);
				list.add(cardUsage);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		// step 4 - close connection
		db.terminate();
	
		return list;
	}
	
	public static ArrayList<CardUsage> retrieveCardUsageByMonth(String Month) {
		// declare local variables
		ArrayList<CardUsage> list = new ArrayList<CardUsage>();
		ResultSet rs = null;
		DBController db = new DBController();
		String dbQuery;
		PreparedStatement pstmt;

		// step 1 - connect to database
		db.getConnection();

		// step 2 - declare the SQL statement
		dbQuery = "SELECT * FROM cardusage";
		pstmt = db.getPreparedStatement(dbQuery);

		// step 3 - execute query
		try {
			pstmt.setString(1, Month);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				CardUsage cardUsage = convertToCardUsage(rs);
				list.add(cardUsage);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// step 4 - close connection
		db.terminate();

		return list;
	}
	
	public static boolean createCardUsage(CardUsage cardUsage) {
		// declare local variables
		boolean success = false;
		DBController db = new DBController();
		String dbQuery;
		PreparedStatement pstmt;

		// step 1 - establish connection to database
		db.getConnection();

		// step 2 - declare the SQL statement
		dbQuery = "INSERT INTO cardusage(id, cardNumber, usageLimit, dateEffective) VALUES(?, ?, ?, ?)";
		pstmt = db.getPreparedStatement(dbQuery);

		// step 3 - to insert record using executeUpdate method
		try {
			pstmt.setInt(1, getNextCardUsageId());
			pstmt.setString(2, cardUsage.getCardNumber());
			pstmt.setDouble(3, cardUsage.getUsageLimit());
			pstmt.setString(4, cardUsage.getDateEffective());

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
	
	public static boolean updateCardUsage(CardUsage cardUsage) {
		//declare local variables
		boolean success = false;
		DBController db = new DBController();
		String dbQuery;	
		PreparedStatement pstmt;
		
		//step 1 - establish connection to database
		db.getConnection();		

		//step 2 - declare the SQL statement
		dbQuery = "UPDATE cardusage SET cardNumber = ?, usageLimit = ?, dateEffective = ? WHERE id = ?";
		pstmt = db.getPreparedStatement(dbQuery);
		
		//step 3 - to update record using executeUpdate method
		try {
			pstmt.setString(1, cardUsage.getCardNumber());
			pstmt.setDouble(2, cardUsage.getUsageLimit());
			pstmt.setString(3, cardUsage.getDateEffective());

			pstmt.setInt(4, cardUsage.getId());
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
	public static ArrayList<CardUsage> retrieveCardUsageByCardNumber(String cardNumber) {
		// declare local variables
		ArrayList<CardUsage> list = new ArrayList<CardUsage>();
		ResultSet rs = null;
		DBController db = new DBController();
		String dbQuery;
		PreparedStatement pstmt;

		// step 1 - connect to database
		db.getConnection();

		// step 2 - declare the SQL statement
		dbQuery = "SELECT * FROM cardusage WHERE cardNumber = ?";
		pstmt = db.getPreparedStatement(dbQuery);

		// step 3 - execute query
		try {
			pstmt.setString(1, cardNumber);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				CardUsage carddetails = convertToCardUsage(rs);
				list.add(carddetails);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// step 4 - close connection
		db.terminate();

		return list;
	}
	public static void main(String[] args) {
		
		
		
	}
	
}
