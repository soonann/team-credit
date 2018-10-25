package teamCreditProjectApp.dataAccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import teamCreditProjectApp.entity.RewardsCart;

public class RewardsCartDA {
	/**
	 * Purpose:	This method gets the max id from the expense table
	 * 			and returns it to the calling problem
	 * Input:	Nil
	 * Return:	int
	 */
	

	
	
	
	private static RewardsCart convertToRewardsCart(ResultSet rs) throws SQLException {
		RewardsCart rewardsCart;
		int id = rs.getInt("id");
		String cardNumber = rs.getString("cardNumber");
		int itemID = rs.getInt("itemID");
		int quantity = rs.getInt("quantity");
		String dateRedeemed = rs.getString("dateRedeemed");
		String itemName= rs.getString("itemName");
		String itemPoints= rs.getString("itemPoints");
		String itemPic= rs.getString("itemPic");
		String itemType= rs.getString("itemType");
		String itemDescription= rs.getString("itemDescription");
		String BankName= rs.getString("BankName");
		rewardsCart = new RewardsCart(id,cardNumber,itemID,quantity,dateRedeemed, itemName,itemPoints,itemPic,itemType,itemDescription,BankName);

		return rewardsCart;
	}

	
	public static ArrayList<RewardsCart> retrieveAllRewardsCart() {
		// declare local variables
		ArrayList<RewardsCart> list = new ArrayList<RewardsCart>();
		ResultSet rs = null;
		DBController db = new DBController();
		String dbQuery;

		// Step 1 - connect to database
		db.getConnection();

		// step 2 - declare the SQL statement
		dbQuery = "SELECT * FROM rewardscart";

		// step 3 - using DBControlle readRequest method
		rs = db.readRequest(dbQuery);
		try {
			while (rs.next()) {
				RewardsCart rewardsCart = convertToRewardsCart(rs);
				list.add(rewardsCart);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// step 4 - close connection
		db.terminate();

		return list;
	}

	
	public static ArrayList<RewardsCart> retrieveRewardsCartByItemPic(String itemPic) {
		// declare local variables
		ArrayList<RewardsCart> list = new ArrayList<RewardsCart>();
		ResultSet rs = null;
		DBController db = new DBController();
		String dbQuery;
		PreparedStatement pstmt;

		// step 1 - connect to database
		db.getConnection();

		// step 2 - declare the SQL statement
		dbQuery = "SELECT * FROM RewardsCart WHERE itemPic = ?";
		pstmt = db.getPreparedStatement(dbQuery);

		// step 3 - execute query
		try {
			pstmt.setString(1, itemPic);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				RewardsCart RewardsCart = convertToRewardsCart(rs);
				list.add(RewardsCart);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// step 4 - close connection
		db.terminate();

		return list;
	}
	public static ArrayList<RewardsCart> retrieveRewardsCartByItemName(String itemName) {
		// declare local variables
		ArrayList<RewardsCart> list = new ArrayList<RewardsCart>();
		ResultSet rs = null;
		DBController db = new DBController();
		String dbQuery;
		PreparedStatement pstmt;

		// step 1 - connect to database
		db.getConnection();

		// step 2 - declare the SQL statement
		dbQuery = "SELECT * FROM RewardsCart WHERE itemName = ?";
		pstmt = db.getPreparedStatement(dbQuery);

		// step 3 - execute query
		try {
			pstmt.setString(1, itemName);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				RewardsCart RewardsCart = convertToRewardsCart(rs);
				list.add(RewardsCart);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// step 4 - close connection
		db.terminate();

		return list;
	}
	
	public static ArrayList<RewardsCart> retrieveRewardsCartByItemPoints(String itemPoints) {
		// declare local variables
		ArrayList<RewardsCart> list = new ArrayList<RewardsCart>();
		ResultSet rs = null;
		DBController db = new DBController();
		String dbQuery;
		PreparedStatement pstmt;

		// step 1 - connect to database
		db.getConnection();

		// step 2 - declare the SQL statement
		dbQuery = "SELECT * FROM RewardsCart WHERE itemPoints = ?";
		pstmt = db.getPreparedStatement(dbQuery);

		// step 3 - execute query
		try {
			pstmt.setString(1, itemPoints);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				RewardsCart RewardsCart = convertToRewardsCart(rs);
				list.add(RewardsCart);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// step 4 - close connection
		db.terminate();

		return list;
	}
	
	public static ArrayList<RewardsCart> retrieveRewardsCartByItemType(String itemType) {
		// declare local variables
		ArrayList<RewardsCart> list = new ArrayList<RewardsCart>();
		ResultSet rs = null;
		DBController db = new DBController();
		String dbQuery;
		PreparedStatement pstmt;

		// step 1 - connect to database
		db.getConnection();

		// step 2 - declare the SQL statement
		dbQuery = "SELECT * FROM RewardsCart WHERE itemType = ?";
		pstmt = db.getPreparedStatement(dbQuery);

		// step 3 - execute query
		try {
			pstmt.setString(1, itemType);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				RewardsCart RewardsCart = convertToRewardsCart(rs);
				list.add(RewardsCart);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// step 4 - close connection
		db.terminate();

		return list;
	}
	
	public static ArrayList<RewardsCart> retrieveRewardsCartByItemDescription(String itemDescription) {
		// declare local variables
		ArrayList<RewardsCart> list = new ArrayList<RewardsCart>();
		ResultSet rs = null;
		DBController db = new DBController();
		String dbQuery;
		PreparedStatement pstmt;

		// step 1 - connect to database
		db.getConnection();

		// step 2 - declare the SQL statement
		dbQuery = "SELECT * FROM RewardsCart WHERE itemDescription = ?";
		pstmt = db.getPreparedStatement(dbQuery);

		// step 3 - execute query
		try {
			pstmt.setString(1, itemDescription);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				RewardsCart RewardsCart = convertToRewardsCart(rs);
				list.add(RewardsCart);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// step 4 - close connection
		db.terminate();

		return list;
	}
	
	public static ArrayList<RewardsCart> retrieveRewardsCartByBankName(String bankName) {
		// declare local variables
		ArrayList<RewardsCart> list = new ArrayList<RewardsCart>();
		ResultSet rs = null;
		DBController db = new DBController();
		String dbQuery;
		PreparedStatement pstmt;

		// step 1 - connect to database
		db.getConnection();

		// step 2 - declare the SQL statement
		dbQuery = "SELECT * FROM RewardsCart WHERE bankName = ?";
		pstmt = db.getPreparedStatement(dbQuery);

		// step 3 - execute query
		try {
			pstmt.setString(1, bankName);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				RewardsCart RewardsCart = convertToRewardsCart(rs);
				list.add(RewardsCart);
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
