package teamCreditProjectApp.dataAccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import teamCreditProjectApp.entity.Login;

public class LoginDA {
	
	/**
	 * Purpose:	This method converts a ResultSet object into an
	 * 			Expense object
	 * Input:	ResultSet object
	 * Return:	Expense object
	 */
	private static Login convertToLogin(ResultSet rs) throws SQLException {
		
		Login login;
		
		String singpassID = rs.getString("singpassID");
		String password = rs.getString("password");
		String userName = rs.getString("userName");
	
		login = new Login(singpassID , password, userName);
		return login;
		
	}
	
	
	/**
	 * Purpose:	This method retrieves an expense record by id
	 * 			and returns it to the calling program.
	 * Input:	int 
	 * Return:	Expense object
	 */
	public static Login retrieveLoginBySingpassID(String singpassID) {
		// declare local variables
		Login login = null;
		ResultSet rs = null;
		DBController db = new DBController();
		String dbQuery;
		PreparedStatement pstmt;

		// step 1 - connect to database
		db.getConnection();

		// step 2 - declare the SQL statement
		dbQuery = "SELECT * FROM Login WHERE singpassID = ?";
		pstmt = db.getPreparedStatement(dbQuery);

		// step 3 - execute query
		try {
			pstmt.setString(1, singpassID);
			rs = pstmt.executeQuery();
			if (rs.next()) { // first record found
				login = convertToLogin(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// step 4 - close connection
		db.terminate();
		return login;
	}
	
		
		
	
	
	
	
	}

