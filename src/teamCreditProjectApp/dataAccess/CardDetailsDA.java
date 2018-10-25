package teamCreditProjectApp.dataAccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import teamCreditProjectApp.entity.CardDetails;

public class CardDetailsDA {
	
	

	private static int getNextCardDetailsId() {
		// declare local variables
		int id = 0;
		ResultSet rs = null;
		DBController db = new DBController();
		String dbQuery;
		PreparedStatement pstmt;

		// step 1 - connect to database
		db.getConnection();

		// step 2 - declare the SQL statement
		dbQuery = "SELECT MAX(id) FROM carddetails";
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
	

	private static CardDetails convertToCardDetails(ResultSet rs) throws SQLException {
		
		CardDetails carddetails;
		
		int id = rs.getInt("id");
		String singpassID = rs.getString("singpassID");
		String cardNumber = rs.getString("cardNumber");
		int activationStatus = rs.getInt("activationStatus");
		String startDate = rs.getString("startDate");
		String endDate = rs.getString("endDate");
		int rewardPoints = rs.getInt("rewardPoints");
		int expiringRewardPoints = rs.getInt("expiringRewardPoints");
		String expiringRewardPointsDate = rs.getString("expiringRewardPointsDate");
		String cardType = rs.getString("cardType");
		double creditLimit = rs.getDouble("creditLimit");
		double usageLimit = rs.getDouble("usageLimit");
		String bankName = rs.getString("bankName");
		
		
		
		
		
		carddetails = new CardDetails(id, singpassID, cardNumber,activationStatus,startDate,endDate, rewardPoints,expiringRewardPoints, expiringRewardPointsDate,cardType, creditLimit,  usageLimit,  bankName);

		return carddetails;
	}
	
	
	
	
	public static ArrayList<CardDetails> retrieveAllCardDetails() {
		// declare local variables
		ArrayList<CardDetails> list = new ArrayList<CardDetails>();
		ResultSet rs = null;
		DBController db = new DBController();
		String dbQuery;

		// Step 1 - connect to database
		db.getConnection();

		// step 2 - declare the SQL statement
		dbQuery = "SELECT * FROM carddetails";

		// step 3 - using DBControlle readRequest method
		rs = db.readRequest(dbQuery);
		try {
			while (rs.next()) {
				CardDetails carddetails = convertToCardDetails(rs);
				list.add(carddetails);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// step 4 - close connection
		db.terminate();

		return list;
	}
	
	
	
	public static ArrayList<CardDetails> retrieveCardDetailsBySingpassID(String singpassID) {
		// declare local variables
		ArrayList<CardDetails> list = new ArrayList<CardDetails>();
		ResultSet rs = null;
		DBController db = new DBController();
		String dbQuery;
		PreparedStatement pstmt;

		// step 1 - connect to database
		db.getConnection();

		// step 2 - declare the SQL statement
		dbQuery = "SELECT * FROM carddetails WHERE singpassID = ?";
		pstmt = db.getPreparedStatement(dbQuery);

		// step 3 - execute query
		try {
			pstmt.setString(1, singpassID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				CardDetails carddetails = convertToCardDetails(rs);
				list.add(carddetails);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// step 4 - close connection
		db.terminate();

		return list;
	}

	
	public static ArrayList<CardDetails> retrieveCardDetailsByCardNumber(String cardNumber) {
		// declare local variables
		ArrayList<CardDetails> list = new ArrayList<CardDetails>();
		ResultSet rs = null;
		DBController db = new DBController();
		String dbQuery;
		PreparedStatement pstmt;

		// step 1 - connect to database
		db.getConnection();

		// step 2 - declare the SQL statement
		dbQuery = "SELECT * FROM carddetails WHERE cardNumber = ?";
		pstmt = db.getPreparedStatement(dbQuery);

		// step 3 - execute query
		try {
			pstmt.setString(1, cardNumber);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				CardDetails carddetails = convertToCardDetails(rs);
				list.add(carddetails);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// step 4 - close connection
		db.terminate();

		return list;
	}
	
	
	public static ArrayList<CardDetails> retrieveCardDetailsBySingpassIDAndBankName(String singpassID , String bankName) {
		// declare local variables
		ArrayList<CardDetails> list = new ArrayList<CardDetails>();
		ResultSet rs = null;
		DBController db = new DBController();
		String dbQuery;
		PreparedStatement pstmt;

		// step 1 - connect to database
		db.getConnection();

		// step 2 - declare the SQL statement
		dbQuery = "SELECT * FROM carddetails WHERE singpassID = ? AND bankName = ?";
		pstmt = db.getPreparedStatement(dbQuery);

		// step 3 - execute query
		try {
			pstmt.setString(1, singpassID);
			pstmt.setString(2, bankName);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				CardDetails carddetails = convertToCardDetails(rs);
				list.add(carddetails);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// step 4 - close connection
		db.terminate();

		return list;
	}
	
	public static boolean updateCardDetails(CardDetails carddetails) {
		//declare local variables
		boolean success = false;
		DBController db = new DBController();
		String dbQuery;	
		PreparedStatement pstmt;
		
		//step 1 - establish connection to database
		db.getConnection();		

		//step 2 - declare the SQL statement
		dbQuery = "UPDATE carddetails SET  id = ?  , activationStatus = ? , startDate = ? , endDate= ? , rewardPoints= ? , expiringRewardPoints= ? , expiringRewardPointsDate= ? , cardType= ? , creditLimit= ? , usageLimit= ? , bankName= ? , singpassID = ? WHERE cardNumber = ?";
		pstmt = db.getPreparedStatement(dbQuery);
		
		//step 3 - to update record using executeUpdate method
		try {
			pstmt.setInt(1,carddetails.getId());
			pstmt.setInt(2, carddetails.getActivationStatus());
			pstmt.setString(3, carddetails.getStartDate());
			pstmt.setString(4, carddetails.getEndDate());
			pstmt.setInt(5, carddetails.getRewardPoints());
			pstmt.setInt(6, carddetails.getExpiringRewardPoints());
			pstmt.setString(7,carddetails.getExpiringRewardPointsDate());
			pstmt.setString(8, carddetails.getCardType());
			pstmt.setDouble(9, carddetails.getCreditLimit());
			pstmt.setDouble(10, carddetails.getUsageLimit());
			pstmt.setString(11, carddetails.getBankName());
			pstmt.setString(12, carddetails.getSingpassID());
			pstmt.setString(13, carddetails.getCardNumber());
			
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

	
	public static boolean createCardDetails(CardDetails cardDetails) {
		// declare local variables
		boolean success = false;
		DBController db = new DBController();
		String dbQuery;
		PreparedStatement pstmt;

		// step 1 - establish connection to database
		db.getConnection();

		// step 2 - declare the SQL statement
		dbQuery = "INSERT INTO carddetails(id, singpassId , cardNumber, activationStatus, startDate, endDate, rewardPoints, expiringRewardPoints, expiringRewardPointsDate , cardType , creditLimit , usageLimit, bankName ) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? , ? )";
		pstmt = db.getPreparedStatement(dbQuery);

		// step 3 - to insert record using executeUpdate method
		try {
			pstmt.setInt(1, getNextCardDetailsId());
			pstmt.setString(2, cardDetails.getSingpassID());
			pstmt.setString(3, cardDetails.getCardNumber());
			pstmt.setInt(4, cardDetails.getActivationStatus());
			pstmt.setString(5, cardDetails.getStartDate());
			pstmt.setString(6, cardDetails.getEndDate());
			pstmt.setInt(7, cardDetails.getRewardPoints());
			pstmt.setInt(8, cardDetails.getExpiringRewardPoints());
			pstmt.setString(9, cardDetails.getExpiringRewardPointsDate());
			pstmt.setString(10, cardDetails.getCardType());
			pstmt.setDouble(11, cardDetails.getCreditLimit());
			pstmt.setDouble(12, cardDetails.getUsageLimit());
			pstmt.setString(13, cardDetails.getBankName());
			
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
	
	
	public static void main(String[] args) {
		
	}
}
