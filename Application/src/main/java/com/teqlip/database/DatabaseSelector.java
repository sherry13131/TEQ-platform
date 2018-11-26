package com.teqlip.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSelector {
	
	/**
	 * get all the roles
	 * @param connection db connection
	 * @return a ResultSet containing all rows of the roles table.
	 * @throws SQLException
	 *             thrown if an SQLException occurs.
	 */
	protected static ResultSet getRoles(Connection con) throws SQLException {
		Statement stmt = con.createStatement();
		ResultSet results = stmt.executeQuery("SELECT * FROM roles;");
		return results;
	}

	/**
	 * get the role name by roleID
	 * @param id
	 * @param connection db connection
	 * @return String - role name
	 * @throws SQLException
	 *             thrown when something goes wrong with query.
	 */
	protected static String getRole(int roleID, Connection con) throws SQLException {
		String sql = "SELECT role FROM roles WHERE roleID = ?";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setInt(1, roleID);
		ResultSet results = preparedStatement.executeQuery();
		results.next();
		String role = results.getString("role");
		results.close();
		return role;
	}
	
	/**
	 * get the queryID by query String
	 * @param query
	 * @param connection db connection
	 * @return queryID
	 * @throws SQLException
	 */
	protected static int getQueryID(String queryString, Connection con) throws SQLException {
		String sql = "SELECT queryID FROM queries WHERE  query = ?";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setString(1, queryString);
		ResultSet results = preparedStatement.executeQuery();
		results.next();
		int queryID = results.getInt("queryID");
		results.close();
		return queryID;
	}
	

	/**
	 * get the user's role by userID
	 * @param userId
	 * @param connection db connection
	 * @return the roleID of that user
	 * @throws SQLException
	 */
	protected static int getUserRoleID(int userId, Connection con) throws SQLException {
		String sql = "SELECT roleID FROM users WHERE userID = ?";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setInt(1, userId);
		ResultSet results = preparedStatement.executeQuery();
		results.next();
		int userRoleID = results.getInt("roleID");
		results.close();
		return userRoleID;
	}

	/**
	 * get a result set of userID with a specific roleID
	 * @param roleId
	 * @param connection db connection
	 * @return a result set of userID
	 * @throws SQLException
	 */
	protected static ResultSet getUsersByRole(int roleId, Connection con) throws SQLException {
		String sql = "SELECT userID FROM users WHERE roleID = ?";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setInt(1, roleId);
		return preparedStatement.executeQuery();
	}

	/**
	 * Return all users' infomation from the database
	 * @param connection db connection
	 * @return a results set of all rows in the table users
	 * @throws SQLException
	 */
	protected static ResultSet getUsersDetails(Connection con) throws SQLException {
		String sql = "SELECT * FROM users";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		return preparedStatement.executeQuery();
	}

	/**
	 * find all the details about a given user
	 * @param userId
	 * @param connection db connection
	 * @return a result set with the details of the user
	 * @throws SQLException
	 */
	protected static ResultSet getUserDetails(int userId, Connection con) throws SQLException {
		String sql = "SELECT * FROM users WHERE userID = ?";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setInt(1, userId);
		return preparedStatement.executeQuery();
	}
	
	/**
	 * get a result set of all usernames
	 * @param con
	 * @return a result set of all usernames
	 * @throws SQLException
	 */
	public static ResultSet getUsernames(Connection con) throws SQLException {
		Statement stmt = con.createStatement();
		ResultSet results = stmt.executeQuery("SELECT username FROM user_login;");
		return results;
	}
	
	/**
	 * get a username by username
	 * @param username
	 * @param con
	 * @return a username if found
	 * @throws SQLException
	 */
	public static ResultSet getUsername(String username, Connection con) throws SQLException {
		String sql = "SELECT username FROM user_login where username = ?;";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setString(1, username);
		return preparedStatement.executeQuery();
	}
	
	/**
	 * get all users status (active)
	 * @param con
	 * @return a result set with username and active columns 
	 * @throws SQLException
	 */
	public static ResultSet getUsersStatus(Connection con) throws SQLException {
		//get the associated userID and activity
		Statement stmt = con.createStatement();
		String sql="SELECT username, active FROM user_login";
		ResultSet result = stmt.executeQuery(sql);
		return result;
	}
	
	/**
	 * get all userIDs with username
	 * @param con
	 * @return a result set with userID and username columns
	 * @throws SQLException
	 */
	public static ResultSet getUserIds(Connection con) throws SQLException {
		Statement stmt = con.createStatement();
		String sql="SELECT userID, username FROM user_login";
		ResultSet result=stmt.executeQuery(sql);
		return result;
	}

	/**
	 * get the hashed password
	 * @param userId
	 * @param connection db connection
	 * @return the hashed password
	 * @throws SQLException
	 */
	protected static String getPassword(int userId, Connection con) throws SQLException {
		String sql = "SELECT password FROM user_password WHERE userID = ?";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setInt(1, userId);
		ResultSet results = preparedStatement.executeQuery();
		String hashPassword = results.getString("password");
		results.close();
		return hashPassword;
	}

	/**
	 * get the total number of users (with login account) in db
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	public static int getTotalNumOfUsers(Connection con) throws SQLException {
		int newUserID = -1;
		Statement stmt = con.createStatement();
		String sql="SELECT count(*) found FROM users;";
		ResultSet rs=stmt.executeQuery(sql);
		//get the largest userID in db
		while (rs.next()) {
			newUserID = rs.getInt("found");
		}
		return newUserID;
	}
	
	/**
	 * get the templates in db
	 * @param con
	 * @return 
	 * @throws SQLException
	 */
	public static ResultSet getTemplates(Connection con) throws SQLException {
		Statement stmt = con.createStatement();
		ResultSet results = stmt.executeQuery("SELECT * FROM Template;");
		return results;
	}
	/**
	 * get the saved query in db
	 * @param con
	 * @return 
	 * @throws SQLException
	 */
	public static ResultSet getQueries(Connection con) throws SQLException {
		Statement stmt = con.createStatement();
		ResultSet results = stmt.executeQuery("SELECT * FROM queries;");
		return results;
	}
	/**
	 * get the anonymized data given a table(template) name, no identifier
	 * @param con
	 * @return Result set that is the anonymized data, without identifier
	 * @throws SQLException
	 */
	public static ResultSet getAnonymizedData(Connection con, String tableName) throws SQLException {
		Statement stmt = con.createStatement();
		ResultSet results = null;
		String sql = "";
		if (tableName.equals("employment")) {
			sql = "SELECT `Processing Details`, " + 
			         "`Date of Birth (YYYY-MM-DD)`, " + 
			         "`Postal Code where the service was received`, " + 
			         "`Registration in an employment intervention`, " + 
			         "`A referral to`, " + 
			         "`Language of Service`, " + 
			         "`Official Language of Preference`, " + 
			         "`Type of Institution/Organization Where Client Received Services`, " + 
			         "`Referred By`, " + 
			         "`Referral Date (YYYY-MM-DD)`, " + 
			         "`Employment Status`, " + 
			         "`Education Status`, " + 
			         "`Occupation in Canada`, " + 
			         "`Intended Occupation`, " + 
			         "`Intervention Type`, " + 
			         "`Long Term Intervention: Intervention Received`, " + 
			         "`Long Term Intervention: Status of Intervention`, " + 
			         "`Long Term Intervention: Reason For Leaving Intervention`, " + 
			         "`Long Term Intervention: Start Date (YYYY-MM-DD)`, " + 
			         "`Long Term Intervention: End Date (YYYY-MM-DD)`, " + 
			         "`Long Term Intervention: Size of Employer`, " + 
			         "`Long Term Intervention: Placement Was`, " + 
			         "`Long Term Intervention: Hours Per Week`, " + 
			         "`Long Term Intervention: Client Met Mentor Regularly at`, " + 
			         "`LTI: Average Hours/Week in Contact with Mentor`, " + 
			         "`LTI: Profession/Trade For Which Services Were Received`, " + 
			         "`Skills and Aptitude Training Received as Part of this Service?`, " + 
			         "`Computer skills`, " + 
			         "`Document Use`, " + 
			         "`Interpersonal Skills and Workplace Culture`, " + 
			         "`Leadership Training`, " + 
			         "`Numeracy`, " + 
			         "`Short Term Intervention: Service Received 1`, " + 
			         "`Short Term Intervention: Date 1 (YYYY-MM-DD)`, " + 
			         "`Short Term Intervention: Service Received 2`, " + 
			         "`Short Term Intervention: Date 2 (YYYY-MM-DD)`, " + 
			         "`Short Term Intervention: Service Received 3`, " + 
			         "`Short Term Intervention: Date 3 (YYYY-MM-DD)`, " + 
			         "`Short Term Intervention: Service Received 4`, " + 
			         "`Short Term Intervention: Date 4 (YYYY-MM-DD)`, " + 
			         "`Short Term Intervention: Service Received 5`, " + 
			         "`Short Term Intervention: Date 5 (YYYY-MM-DD)`, " + 
			         "`Support Services Received`, " + 
			         "`Care for Newcomer Children`, " + 
			         "`Child 1: Age`, " + 
			         "`Child 1: Type of Care`, " + 
			         "`Child 2: Age`, " + 
			         "`Child 2: Type of Care`, " + 
			         "`Child 3: Age`, " + 
			         "`Child 3: Type of Care`, " + 
			         "`Child 4: Age`, " + 
			         "`Child 4: Type of Care`, " + 
			         "`Child 5: Age`, " + 
			         "`Child 5: Type of Care`, " + 
			         "`Transportation`, " + 
			         "`Provisions for Disabilities`, " + 
			         "`Translation`, " + 
			         "`Translation language Between`, " + 
			         "`Translation language And`, " + 
			         "`Interpretation`, " + 
			         "`Interpretation language Between`, " + 
			         "`Interpretation language And`, " + 
			         "`Crisis Counselling`, " + 
			         "`Time Spent With Client's Employment Needs: Hours`, " + 
			         "`Time Spent With Client's Employment Needs: Minutes`, " + 
			         "`Reason for update` FROM `" + tableName +"`;";
		}
		else if (tableName.equals("lt client enrol")) {
			sql = "SELECT `Processing Details`, " + 
			         "`Client Date of Birth (YYYY-MM-DD)`, " + 
			         "`Postal Code where the service was received`, " + 
			         "`Course Code`, " + 
			         "`Date of Client's First Class (YYYY-MM-DD)`, " + 
			         "`Official Language of Preference`, " + 
			         "`Support services received`, " + 
			         "`Care for newcomer children`, " + 
			         "`Child 1: Age`, " + 
			         "`Child 1: Type of Care`, " + 
			         "`Child 2: Age`, " + 
			         "`Child 2: Type of Care`, " + 
			         "`Child 3: Age`, " + 
			         "`Child 3: Type of Care`, " + 
			         "`Child 4: Age`, " + 
			         "`Child 4: Type of Care`, " + 
			         "`Child 5: Age`, " + 
			         "`Child 5: Type of Care`, " + 
			         "`Transportation`, " + 
			         "`Provisions for disabilities`, " + 
			         "`Translation`, " + 
			         "`Translation language Between`, " + 
			         "`Translation language And`, " + 
			         "`Interpretation`, " + 
			         "`Interpretation language Between`, " + 
			         "`Interpretation language And`, " + 
			         "`Crisis Counselling`, " +
			         "`Reason for update` FROM `" + tableName +"`;";
		}
		else if (tableName.equals("lt course setup")) {
			sql = "SELECT `Processing Details`, " + 
			         "`Course Code`, " + 
			         "`Notes`, " + 
			         "`Course Held On An Ongoing Basis`, " + 
			         "`Official Language of Course`, " + 
			         "`Format of Training Provided`, " + 
			         "`Classes Held At`, " + 
			         "`In-Person Instruction (%)`, " + 
			         "`Online/Distance Instruction (%)`, " + 
			         "`Total Number of Spots in Course`, " + 
			         "`Number of IRCC-Funded Spots in Course`, " + 
			         "`New Students Can Enrol in the Course`, " + 
			         "`Support Services Available for Client in this Course`, " + 
			         "`Care for Newcomer Children`, " + 
			         "`Transportation`, " + 
			         "`Provisions for Disabilities`, " + 
			         "`Course Start Date (YYYY-MM-DD)`, " + 
			         "`Course End Date (YYYY-MM-DD)`, " + 
			         "`Schedule: Morning`, " + 
			         "`Schedule: Afternoon`, " + 
			         "`Schedule: Evening`, " + 
			         "`Schedule: Weekend`, " + 
			         "`Schedule: Anytime`, " + 
			         "`Schedule: Online`, " + 
			         "`Instructional Hours Per Class`, " + 
			         "`Classes Per Week`, " + 
			         "`Weeks of Instruction`, " + 
			         "`Weeks of Instruction Per Year`, " + 
			         "`Dominant Focus of the Course`, " + 
			         "`Course Directed at a Specific Target Group`, " + 
			         "`Children (0-14 yrs)`, " + 
			         "`Youth (15-24 yrs)`, " + 
			         "`Senior`, " + 
			         "`Gender-specific`, " + 
			         "`Refugees`, " + 
			         "`Official language minorities`, " + 
			         "`Ethnic/cultural/linguistic group`, " + 
			         "`Deaf or Hard of Hearing`, " + 
			         "`Blind or Partially Sighted`, " + 
			         "`Clients with other impairments (physical, mental)`, " + 
			         "`Lesbian, Gay, Bisexual, Transgender, Queer (LGBTQ)`, " + 
			         "`Families/Parents`, " + 
			         "`Clients with international training in a regulated profession`, " + 
			         "`Clients with international training in a regulated trade`, " + 
			         "`Materials Used in Course`, " + 
			         "`Citizenship preparation`, " + 
			         "`PBLA language companion`, " + 
			         "`Contact Name`, " + 
			         "`Street Number`, " + 
			         "`Street Name`, " + 
			         "`Street Type`, " + 
			         "`Street Direction`, " + 
			         "`Unit/Suite`, " + 
			         "`Province`, " + 
			         "`City`, " + 
			         "`Postal Code (A#A#A#)`, " + 
			         "`Telephone Number (###-###-####)`, " + 
			         "`Telephone Extension`, " + 
			         "`Email Address`, " + 
			         "`Listening Skill Level 1`, " + 
			         "`Listening Skill Level 2`, " + 
			         "`Listening Skill Level 3`, " + 
			         "`Listening Skill Level 4`, " + 
			         "`Listening Skill Level 5`, " + 
			         "`Listening Skill Level 6`, " + 
			         "`Listening Skill Level 7`, " + 
			         "`Listening Skill Level 8`, " + 
			         "`Listening Skill Level 9`, " + 
			         "`Listening Skill Level 10`, " + 
			         "`Listening Skill Level 11`, " + 
			         "`Listening Skill Level 12`, " + 
			         "`Speaking Skill Level 1`, " + 
			         "`Speaking Skill Level 2`, " + 
			         "`Speaking Skill Level 3`, " + 
			         "`Speaking Skill Level 4`, " + 
			         "`Speaking Skill Level 5`, " + 
			         "`Speaking Skill Level 6`, " + 
			         "`Speaking Skill Level 7`, " + 
			         "`Speaking Skill Level 8`, " + 
			         "`Speaking Skill Level 9`, " + 
			         "`Speaking Skill Level 10`, " + 
			         "`Speaking Skill Level 11`, " + 
			         "`Speaking Skill Level 12`, " + 
			         "`Reading Skill Level 1`, " + 
			         "`Reading Skill Level 2`, " + 
			         "`Reading Skill Level 3`, " + 
			         "`Reading Skill Level 4`, " + 
			         "`Reading Skill Level 5`, " + 
			         "`Reading Skill Level 6`, " + 
			         "`Reading Skill Level 7`, " + 
			         "`Reading Skill Level 8`, " + 
			         "`Reading Skill Level 9`, " + 
			         "`Reading Skill Level 10`, " + 
			         "`Reading Skill Level 11`, " + 
			         "`Reading Skill Level 12`, " + 
			         "`Reading Skill Level 13`, " + 
			         "`Reading Skill Level 14`, " + 
			         "`Reading Skill Level 15`, " + 
			         "`Reading Skill Level 16`, " + 
			         "`Reading Skill Level 17`, " + 
			         "`Writing Skill Level 1`, " + 
			         "`Writing Skill Level 2`, " + 
			         "`Writing Skill Level 3`, " + 
			         "`Writing Skill Level 4`, " + 
			         "`Writing Skill Level 5`, " + 
			         "`Writing Skill Level 6`, " + 
			         "`Writing Skill Level 7`, " + 
			         "`Writing Skill Level 8`, " + 
			         "`Writing Skill Level 9`, " + 
			         "`Writing Skill Level 10`, " + 
			         "`Writing Skill Level 11`, " + 
			         "`Writing Skill Level 12`, " + 
			         "`Writing Skill Level 13`, " + 
			         "`Writing Skill Level 14`, " + 
			         "`Writing Skill Level 15`, " + 
			         "`Writing Skill Level 16`, " + 
			         "`Writing Skill Level 17` " +
			         "FROM `" + tableName +"`;";
		}
		else if (tableName.equals("info&orien")) {
			sql = "SELECT `Processing Details`, " + 
			         "`Date of Birth (YYYY-MM-DD)`, " + 
			         "`Postal Code where the service was received`, " + 
			         "`Start Date of Service (YYYY-MM-DD)`, " + 
			         "`Language of Service`, " + 
			         "`Official Language of Preference`, " + 
			         "`Type of Institution/Organization Where Client Received Services`, " + 
			         "`Referred By`, " + 
			         "`Services Received`, " + 
			         "`Total Length of Orientation`, " + 
			         "`Total Length of Orientation: Hours`, " + 
			         "`Total Length of Orientation: Minutes`, " + 
			         "`Number of Clients in Group`, " + 
			         "`Directed at a specific Target Group`, " + 
			         "`Target Group: Children (0-14 yrs)`, " + 
			         "`Target Group: Youth (15-24 yrs)`, " + 
			         "`Target Group: Seniors`, " + 
			         "`Target Group: Gender-specific`, " + 
			         "`Target Group: Refugees`, " + 
			         "`Target Group: Ethnic/cultural/linguistic group`, " + 
			         "`Target Group: Deaf or Hard of Hearing`, " + 
			         "`Target Group: Blind or Partially Sighted`, " + 
			         "`TG: Lesbian, Gay, Bisexual, Transgender, Queer (LGBTQ)`, " + 
			         "`Target Group: Families/Parents`, " + 
			         "`Target Group: Clients with other impairments (physical, mental)`, " + 
			         "`TG: Clients w international training in a regulated profession`, " + 
			         "`TG: Clients with international training in a regulated trade`, " + 
			         "`Target Group: Official Language minorities`, " + 
			         "`Overview of Canada`, " + 
			         "`Overview of Canada Referrals`, " + 
			         "`Sources of Information`, " + 
			         "`Sources of Information Referrals`, " + 
			         "`Rights and Freedoms`, " + 
			         "`Rights and Freedoms Referrals`, " + 
			         "`Canadian Law and Justice`, " + 
			         "`Canadian Law and Justice Referrals`, " + 
			         "`Important Documents`, " + 
			         "`Important Documents Referrals`, " + 
			         "`Improving English or French`, " + 
			         "`Improving English or French Referrals`, " + 
			         "`Employment and Income`, " + 
			         "`Employment and Income Referrals`, " + 
			         "`Education`, " + 
			         "`Education Referrals`, " + 
			         "`Housing`, " + 
			         "`Housing Referrals`, " + 
			         "`Health`, " + 
			         "`Health Referrals`, " + 
			         "`Money and Finances`, " + 
			         "`Money and Finances Referrals`, " + 
			         "`Transportation1`, " + 
			         "`Transportation Referrals`, " + 
			         "`Communications and Media`, " + 
			         "`Communications and Media Referrals`, " + 
			         "`Community Engagement`, " + 
			         "`Community Engagement Referrals`, " + 
			         "`Becoming a Canadian Citizen`, " + 
			         "`Becoming a Canadian Citizen Referrals`, " + 
			         "`Interpersonal Conflict`, " + 
			         "`Interpersonal Conflict Referrals`, " + 
			         "`Essential Skills and Aptitude Training Received?`, " + 
			         "`Computer skills`, " + 
			         "`Document Use`, " + 
			         "`Interpersonal Skills and Workplace Culture`, " + 
			         "`Leadership Training`, " + 
			         "`Numeracy`, " + 
			         "`Skills or Responsibilities of Citizenship Information Received?`, " + 
			         "`Life Skills`, " + 
			         "`Rights and Responsibilities of Citizenship`, " + 
			         "`Support Services Received`, " + 
			         "`Care for Newcomer Children`, " + 
			         "`Child 1: Age`, " + 
			         "`Child 1: Type of Care`, " + 
			         "`Child 2: Age`, " + 
			         "`Child 2: Type of Care`, " + 
			         "`Child 3: Age`, " + 
			         "`Child 3: Type of Care`, " + 
			         "`Child 4: Age`, " + 
			         "`Child 4: Type of Care`, " + 
			         "`Child 5: Age`, " + 
			         "`Child 5: Type of Care`, " + 
			         "`Transportation`, " + 
			         "`Provisions for Disabilities`, " + 
			         "`Translation`, " + 
			         "`Translation language Between`, " + 
			         "`Translation language And`, " + 
			         "`Interpretation`, " + 
			         "`Interpretation language Between`, " + 
			         "`Interpretation language And`, " + 
			         "`Crisis Counselling`, " + 
			         "`End Date of Service (YYYY-MM-DD)`, " + 
			         "`Reason for update` FROM `" + tableName +"`;";
		}
		else if (tableName.equals("community connections")) {
			sql = "SELECT `Processing Details`, " + 
			         "`Date of Birth (YYYY-MM-DD)`, " + 
			         "`Postal Code where the service was received`, " + 
			         "`Language of Service`, " + 
			         "`Official Language of Preference`, " + 
			         "`Referred By`, " + 
			         "`Activity Under Which Client Received Services`, " + 
			         "`Type of Institution/Organization Where Client Received Services`, " + 
			         "`Type of Event Attended`, " + 
			         "`Type of Service`, " + 
			         "`Main Topic/Focus of the Service Received`, " + 
			         "`Service Received`, " + 
			         "`Number of Unique Participants`, " + 
			         "`Did Volunteers from the Host Community Participate?`, " + 
			         "`Directed at a Specific Target Group`, " + 
			         "`Target Group: Children (0-14 yrs)`, " + 
			         "`Target Group: Youth (15-24 yrs)`, " + 
			         "`Target Group: Senior`, " + 
			         "`Target Group: Gender-specific`, " + 
			         "`Target Group: Refugees`, " + 
			         "`Target Group: Ethnic/cultural/linguistic group`, " + 
			         "`Target Group: Deaf or Hard of Hearing`, " + 
			         "`Target Group: Blind or Partially Sighted`, " + 
			         "`Target Group: Lesbian, Gay, Bisexual, Transgender, Queer (LGBTQ)`, " + 
			         "`Target Group: Families/Parents`, " + 
			         "`Target Group: Other impairments (physical, mental)`, " + 
			         "`TG: Clients w international training in a regulated profession`, " + 
			         "`TG: Clients with international training in a regulated trade`, " + 
			         "`Target Group: Official language minorities`, " + 
			         "`Status of Service`, " + 
			         "`Reason for Leaving Service`, " + 
			         "`Start Date (YYYY-MM-DD)`, " + 
			         "`End Date (YYYY-MM-DD)`, " + 
			         "`Projected End Date (YYYY-MM-DD)`, " + 
			         "`Essential Skills and Aptitudes Training Received?`, " + 
			         "`Computer Skills`, " + 
			         "`Document Use`, " + 
			         "`Interpersonal Skills and Workplace Culture`, " + 
			         "`Leadership Training`, " + 
			         "`Life Skills`, " + 
			         "`Numeracy`, " + 
			         "`Support Services Received`, " + 
			         "`Care for Newcomer Children`, " + 
			         "`Child 1: Age`, " + 
			         "`Child 1: Type of Care`, " + 
			         "`Child 2: Age`, " + 
			         "`Child 2: Type of Care`, " + 
			         "`Child 3: Age`, " + 
			         "`Child 3: Type of Care`, " + 
			         "`Child 4: Age`, " + 
			         "`Child 4: Type of Care`, " + 
			         "`Child 5: Age`, " + 
			         "`Child 5: Type of Care`, " + 
			         "`Transportation`, " + 
			         "`Provisions for Disabilities`, " + 
			         "`Translation`, " + 
			         "`Translation language Between`, " + 
			         "`Translation language And`, " + 
			         "`Interpretation`, " + 
			         "`Interpretation language Between`, " + 
			         "`Interpretation language And`, " + 
			         "`Crisis Counselling`, " + 
			         "`Total Length of Service: Hours`, " + 
			         "`Total Length of Service: Minutes`, " + 
			         "`Reason for update` FROM `" + tableName +"`;";
		}
		else if (tableName.equals("needs assessment&referrals")) {
			sql = "SELECT `Processing Details`, " + 
			         "`Date of Birth (YYYY-MM-DD)`, " + 
			         "`Postal Code where the service was recieved`, " + 
			         "`Start Date of Assessment (YYYY-MM-DD)`, " + 
			         "`Language of Service`, " + 
			         "`Official Language of Preference`, " + 
			         "`Type of Institution/Organization Where Client Received Services`, " + 
			         "`Referred By`, " + 
			         "`Increase knowledge of: Life in Canada`, " + 
			         "`Increase knowledge of: Life in Canada Referrals`, " + 
			         "`Increase knowledge of: Community and Government Services`, " + 
			         "`Increase knowledge of: Community and Government Services R`, " + 
			         "`Increase knowledge of: Working in Canada`, " + 
			         "`Increase knowledge of: Working in Canada Referrals`, " + 
			         "`Increase knowledge of: Education in Canada`, " + 
			         "`Increase knowledge of: Education in Canada Referrals`, " + 
			         "`Increase the following: Social networks`, " + 
			         "`Increase the following: Social networks Referrals`, " + 
			         "`Increase the following: Professional networks`, " + 
			         "`Increase the following: Professional networks Referrals`, " + 
			         "`Increase the following: Access to local community services`, " + 
			         "`Increase the following: Access to local community services R`, " + 
			         "`Increase the following: Level of community involvement`, " + 
			         "`Increase the following: Level of community involvement R`, " + 
			         "`Improve Language Skills`, " + 
			         "`Improve Language Skills Referrals`, " + 
			         "`Improve Language Skills to`, " + 
			         "`Improve Other Skills`, " + 
			         "`Improve Other Skills Referrals`, " + 
			         "`Improve Other Skills to`, " + 
			         "`Find employment`, " + 
			         "`Find employment Referrals`, " + 
			         "`Find employment: TimeFrame`, " + 
			         "`Find employment: Minimum one year's work experience?`, " + 
			         "`Find employment: Intends  Classification skill level?`, " + 
			         "`Find employment: Intends to have license to work in Canada?`, " + 
			         "`Client intends to become a Canadian citizen?`, " + 
			         "`Support services may be required`, " + 
			         "`Care for Newcomer Children1`, " + 
			         "`Transportation1`, " + 
			         "`Provisions for Disability`, " + 
			         "`Translation Required`, " + 
			         "`Interpretation Required`, " + 
			         "`Crisis Counselling1`, " + 
			         "`Non-IRCC program services needed`, " + 
			         "`Food/Clothing/Other Material Needs`, " + 
			         "`NeedsAssessmentAndReferralscol`, " + 
			         "`Housing/Accommodation`, " + 
			         "`Housing/Accommodation Referrals`, " + 
			         "`Health/Mental Health/Well Being`, " + 
			         "`Health/Mental Health/Well Being Referrals`, " + 
			         "`Financial`, " + 
			         "`Financial Referrals`, " + 
			         "`Family Support`, " + 
			         "`Family Support Referrals`, " + 
			         "`Language (Non-IRCC)`, " + 
			         "`Language (Non-IRCC) Referrals`, " + 
			         "`Education/Skills Development`, " + 
			         "`Education/Skills Development Referrals`, " + 
			         "`Employment-related`, " + 
			         "`Employment-related Referrals`, " + 
			         "`Legal Information and Services`, " + 
			         "`Legal Information and Services Referrals`, " + 
			         "`Community Services`, " + 
			         "`Community Services Referrals`, " + 
			         "`Support Services Received`, " + 
			         "`Care for Newcomer Children`, " + 
			         "`Child 1: Age`, " + 
			         "`Child 1: Type of Care`, " + 
			         "`Child 2: Age`, " + 
			         "`Child 2: Type of Care`, " + 
			         "`Child 3: Age`, " + 
			         "`Child 3: Type of Care`, " + 
			         "`Child 4: Age`, " + 
			         "`Child 4: Type of Care`, " + 
			         "`Child 5: Age`, " + 
			         "`Child 5: Type of Care`, " + 
			         "`Transportation`, " + 
			         "`Provisions for Disabilities`, " + 
			         "`Translation`, " + 
			         "`Translation language Between`, " + 
			         "`Translation language And`, " + 
			         "`Interpretation`, " + 
			         "`Interpretation language Between`, " + 
			         "`Interpretation language And`, " + 
			         "`Crisis Counselling`, " + 
			         "`Settlement Plan completed and shared with client`, " + 
			         "`End Date of Assessment (YYYY-MM-DD)`, " + 
			         "`Reason for update` FROM `" + tableName +"`;";
		}
		else if (tableName.equals("client profile")) {
			sql = "SELECT `Processing Details`, " + 
			          "`Date of Birth (YYYY-MM-DD)`, " + 
			          "`Telephone Number (###-###-####)`, " + 
			          "`Does the Client Have an Email Address`, " + 
			          "`Email Address`, " + 
			          "`Street Number`, " + 
			          "`Street Name`, " + 
			          "`Street Type`, " + 
			          "`Street Direction`, " + 
			          "`Unit/Suite/Apt`, " + 
			          "`City`, " + 
			          "`Province`, " + 
			          "`Postal Code`, " + 
			          "`Official Language of Preference`, " + 
			          "`Consent for Future Research/Consultation` " + 
			         " FROM `" + tableName +"`;";
		}
		else if (tableName.equals("lt client exit")) {
			sql = "SELECT `Processing Details`, " + 
			        "`Client Date of Birth (YYYY-MM-DD)`, " + 
			        "`Course Code`, " + 
			        "`Client's Training Status`, " + 
			        "`Date Client Exited Course (YYYY-MM-DD)`, " + 
			        "`Reason for Exiting course`, " + 
			        "`Listening CLB Level`, " + 
			        "`Speaking CLB Level`, " + 
			        "`Reading CLB Level`, " + 
			        "`Writing CLB Level`, " + 
			        "`Was a Certificate issued to the client?`, " + 
			        "`Listening level indicated on Certificate`, " + 
			        "`Speaking level indicated on Certificate`, " + 
			        "`Support services received`, " + 
			        "`Care for newcomer children`, " + 
			        "`Child 1: Age`, " + 
			        "`Child 1: Type of Care`, " + 
			        "`Child 2: Age`, " + 
			        "`Child 2: Type of Care`, " + 
			        "`Child 3: Age`, " + 
			        "`Child 3: Type of Care`, " + 
			        "`Child 4: Age`, " + 
			        "`Child 4: Type of Care`, " + 
			        "`Child 5: Age`, " + 
			        "`Child 5: Type of Care`, " + 
			        "`Transportation`, " + 
			        "`Provisions for disabilities`, " + 
			        "`Translation`, " + 
			        "`Translation language Between`, " + 
			        "`Translation language And`, " + 
			        "`Interpretation`, " + 
			        "`Interpretation language Between`, " + 
			        "`Interpretation language And`, " + 
			        "`Crisis Counselling`, " + 
			        "`Reason for update` " + 
			         " FROM `" + tableName +"`;";
		}
		else {
			throw new SQLException();
		}
		results = stmt.executeQuery(sql);
		return results;
	}

  public static ResultSet queryData(Connection con, String query) throws SQLException {
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery(query + ";");
      return rs;
  }
  
  public static String getUserEmail(Connection con, int userId) throws SQLException {
    String sql = "SELECT email FROM users where userID = ?;";
    PreparedStatement preparedStatement = con.prepareStatement(sql);
    preparedStatement.setInt(1, userId);
    ResultSet results = preparedStatement.executeQuery();
    results.next();
    String email = results.getString("email");
    results.close();
    return email;
  }
}
