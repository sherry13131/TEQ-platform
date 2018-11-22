package com.teqlip.database;

import com.teqlip.database.DatabaseInserter;
import com.teqlip.excel.ExcelBook;
import com.teqlip.excel.ExcelRow;
import com.teqlip.excel.ExcelSheet;
import com.teqlip.excel.FileHelper;
import com.teqlip.Role.RoleEnum;
import com.teqlip.database.DatabaseDriverHelper;
import com.teqlip.exceptions.DatabaseInsertException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

public class DatabaseInsertHelper extends DatabaseInserter {
	
	/**
	 * insert a new user login account info
	 * @param username
	 * @param password
	 * @param firstName
	 * @param lastName
	 * @param middleName can be null
	 * @param role
	 * @param email
	 * @param phoneNumber can be null
	 * @param active
	 * @return userID that newly created
	 * @throws DatabaseInsertException
	 * @throws SQLException
	 */
	public static int createNewUserAccount(String username, String password,
			  String firstName,String lastName, String middleName, RoleEnum role,
			  String email, String phoneNumber, int active) throws DatabaseInsertException, SQLException {
		Connection con = DatabaseDriverHelper.connectOrCreateDataBase();
		int userID = -1;
		boolean valid = DataValidator.createNewAccountValidation(username, password, firstName, lastName, role, email);
		if (valid) {
			userID = DatabaseInsertHelper.insertNewUserAccount(username, password, firstName, lastName, middleName, role, email, phoneNumber, active, con);
		}
		return userID;
	}

	/**
	   * Use this to insert a new user.
	   * @param name the user's name.
	   * @param age the user's age.
	   * @param address the user's address.
	   * @param password the user's password (not hashsed).
	   * @param connection the database connection.
	   * @return the user id
	   * @throws DatabaseInsertException if there is a failure on the insert
	   * @throws SQLException 
	   */
	  public static int insertNewUserAccount(String username, String password,
			  String firstName,String lastName, String middleName, RoleEnum role,
			  String email, String phoneNumber, int active, 
			  Connection con) throws DatabaseInsertException, SQLException {
		// get -1 means error
		int roleID = DatabaseSelectHelper.getRoleId(role);
	    int userID = DatabaseInserter.insertUser(firstName, lastName, middleName, roleID, email, phoneNumber, con);
	    DatabaseInserter.insertUserLogin(userID, username, active, con);
	    if (userID != -1) {
	    	DatabaseInserter.insertPassword(password, userID, con);
	      return userID;
	    }
	    throw new DatabaseInsertException();
	  }
	  
	  /**
	   * Use this to insert a new template and its file.
	   * @param name of template.
	   * @param path to xslx file.
	   * @param connection the database connection.
	   * @return 1 if insert suceeded, -1 is failed, 
	   * @throws DatabaseInsertException if name is not unique
	   * @throws SQLException 
	   */
	  public static int insertNewTemplate(String templateName, String templatePath,
			  Connection con) throws DatabaseInsertException, SQLException {
		// this make sure the template names are unique
		List<String> templateNames = DatabaseSelectHelper.getTemplatesName();
	    if (!templateNames.contains(templateName)) {
	      return DatabaseInserter.insertTemplate(templateName, templatePath, con);
	    }
	    throw new DatabaseInsertException();
	  }
	  
	  /**
	   * Use this to insert a new query.
	   * @param query.
	   * @param connection the database connection.
	   * @return 1 if insert suceeded, -1 is failed, 
	   * @throws DatabaseInsertException if query is not unique
	   * @throws SQLException 
	   */
	  public static int insertNewQuery(String query, Connection con) 
			  throws DatabaseInsertException, SQLException {
		// this make sure the query are unique
		List<String> queries = DatabaseSelectHelper.getSavedQueries();
	    if (!queries.contains(query)) {
	      return DatabaseInserter.insertQuery(query,con);
	    }
	    throw new DatabaseInsertException();
	  }
	  
	  public static void insertLTClientExit(Connection con) throws SQLException, ParseException  {
	    String filename = "New_iCARE_Template_Comb_with_Examples.xlsx";
	    String sql = "INSERT ignore INTO `assignmentdb`.`lt client exit` " + 
	        "(`Processing Details`, " + 
	        "`Update record ID`, " + 
	        "`Unique Identifier Type`, " + 
	        "`Unique Identifier Value`, " + 
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
	        "`Reason for update`) " + 
	        "VALUES " + 
	        "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
	    PreparedStatement ps = con.prepareStatement(sql);
	    SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
	    ExcelBook excelBook = new ExcelBook(filename);
	    System.out.println(excelBook.getSheetMap().get("LT Client Exit"));
	    ExcelSheet a = excelBook.getSheetMap().get("LT Client Exit");
	    java.util.Date date;
	    java.sql.Date sqlDate;  
	    System.out.println(a);
	    List<ExcelRow> excelrowlist = new ArrayList<ExcelRow>();
	    List<Object> list = new ArrayList<Object>();
	    boolean allblank = true;
	    Object cellData;
	    ExcelRow excelrow = null;
//	    for (ExcelRow row : a.getRows()) {
//	    for (int i = 0; i<a.myGetSize() ;i++) {
//	      list = row.getCells();
	      excelrowlist = a.getRows();
	      for (int i=3 ; i<excelrowlist.size();i++) {
	        excelrow = excelrowlist.get(i);
	        list = excelrow.getCells();
	        for (int j = 0; j<excelrow.getCells().size();j++) {
	          allblank = true;
	          cellData = list.get(j);
            if (cellData.equals("NULL")) {
              ps.setNull(j+1, java.sql.Types.NULL);
//              System.out.println(list.get(j) + " this is null");
              continue;
            }
  	        if (j==4 || j==7) {
  	          date = sdf1.parse(list.get(j).toString());
  	          sqlDate = new java.sql.Date(date.getTime());  
  	          ps.setDate(j+1, sqlDate);
  	          System.out.println(list.get(j));
  	          allblank = false;
  	        } else {
  	          ps.setString(j+1, list.get(j).toString());
  	          System.out.println(list.get(j));
  	          allblank = false;
  	        }
	        }
	        if (!allblank) {
            ps.executeUpdate();
          } else {
            break;
          }
       }     
	    
	  }
	  
	   public static void insertClientProfile(Connection con) throws SQLException, ParseException  {
	      String filename = "New_iCARE_Template_Comb_with_Examples.xlsx";
	      String sql = "INSERT ignore INTO `assignmentdb`.`clientprofile` " + 
	          "(`Processing Details`, " + 
	          "`Unique Identifier`, " + 
	          "`Unique Identifier Value`, " + 
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
	          "`Consent for Future Research/Consultation`) " + 
	          "VALUES " + 
	          "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
	      PreparedStatement ps = con.prepareStatement(sql);
	      SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
	      ExcelBook excelBook = new ExcelBook(filename);
	      System.out.println(excelBook.getSheetMap().get("Client Profile"));
	      ExcelSheet a = excelBook.getSheetMap().get("Client Profile");
	      java.util.Date date;
	      java.sql.Date sqlDate;  
	      System.out.println(a);
	      List<ExcelRow> excelrowlist = new ArrayList<ExcelRow>();
	      List<Object> list = new ArrayList<Object>();
	      ExcelRow excelrow = null;
	      Object cellData;
	      boolean allblank = true;
	        excelrowlist = a.getRows();
	        for (int i=3 ; i<excelrowlist.size();i++) {
	          excelrow = excelrowlist.get(i);
	          list = excelrow.getCells();
	          for (int j = 0; j<excelrow.getCells().size();j++) {
	            allblank = true;
	            cellData = list.get(j);
	            if (cellData.equals("NULL")) {
	              ps.setNull(j+1, java.sql.Types.NULL);
//	              System.out.println(list.get(j) + " this is null");
	              continue;
	            }
	            if (j==3) {
	              date = sdf1.parse(list.get(j).toString());
	              sqlDate = new java.sql.Date(date.getTime());  
	              ps.setDate(j+1, sqlDate);
	              System.out.println(list.get(j));
	              allblank = false;
	            } else {
	              ps.setString(j+1, list.get(j).toString());
	              System.out.println(list.get(j));
	              allblank = false;
	            }
	          }
	           if (!allblank) {
	             ps.executeUpdate();
	           } else {
	             break;
	           }
	        }	      
	      
	    }
	   
	   public static void insertNeedsAssessmentReferrals(Connection con) throws SQLException, ParseException  {
       String filename = "New_iCARE_Template_Comb_with_Examples.xlsx";
       String sql = "INSERT ignore INTO `assignmentdb`.`needsassessmentandreferrals` " + 
           "(`Processing Details`, " + 
           "`Update Record ID`, " + 
           "`Unique Identifier`, " + 
           "`Unique Identifier Value`, " + 
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
           "`Reason for update`) " + 
           "VALUES " + 
           "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
       PreparedStatement ps = con.prepareStatement(sql);
       SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
       ExcelBook excelBook = new ExcelBook(filename);
       System.out.println(excelBook.getSheetMap().get("Needs Assessment&Referrals"));
       ExcelSheet a = excelBook.getSheetMap().get("Needs Assessment&Referrals");
       java.util.Date date;
       java.sql.Date sqlDate;  
       System.out.println(a);
       List<ExcelRow> excelrowlist = new ArrayList<ExcelRow>();
       List<Object> list = new ArrayList<Object>();
       boolean allblank = true;
       Object cellData;
       ExcelRow excelrow = null;
         excelrowlist = a.getRows();
         for (int i=3 ; i<excelrowlist.size()-1;i++) {
           allblank = true;
           excelrow = excelrowlist.get(i);
           list = excelrow.getCells();
           for (int j = 0; j<excelrow.getCells().size();j++) {
             cellData = list.get(j);
             if (cellData.equals("NULL")) {
               ps.setNull(j+1, java.sql.Types.NULL);
//               System.out.println(list.get(j) + " this is null");
               continue;
             }
             if (j==4 || j==6 || j==90) {
//               System.out.println("date");
               date = sdf1.parse(list.get(j).toString());
               sqlDate = new java.sql.Date(date.getTime());  
               ps.setDate(j+1, sqlDate);
               System.out.println(list.get(j) + " " + j);
               allblank = false;
             } else {
//               System.out.println("string");
               ps.setString(j+1, list.get(j).toString());
               System.out.println(list.get(j));
               allblank = false;
             }
           }
           if (!allblank) {
             ps.executeUpdate();
           } else {
             break;
           }
         }
     }
	   
	   public static void insertCommunityConnections(Connection con) throws SQLException, ParseException  {
       String filename = "New_iCARE_Template_Comb_with_Examples.xlsx";
       String sql = "INSERT INTO `assignmentdb`.`community connections`\n" + 
           "(`Processing Details`,\n" + 
           "`Update Record ID`,\n" + 
           "`Unique Identifier`,\n" + 
           "`Unique Identifier Value`,\n" + 
           "`Date of Birth (YYYY-MM-DD)`,\n" + 
           "`Postal Code where the service was received`,\n" + 
           "`Language of Service`,\n" + 
           "`Official Language of Preference`,\n" + 
           "`Referred By`,\n" + 
           "`Activity Under Which Client Received Services`,\n" + 
           "`Type of Institution/Organization Where Client Received Services`,\n" + 
           "`Type of Event Attended`,\n" + 
           "`Type of Service`,\n" + 
           "`Main Topic/Focus of the Service Received`,\n" + 
           "`Service Received`,\n" + 
           "`Number of Unique Participants`,\n" + 
           "`Did Volunteers from the Host Community Participate?`,\n" + 
           "`Directed at a Specific Target Group`,\n" + 
           "`Target Group: Children (0-14 yrs)`,\n" + 
           "`Target Group: Youth (15-24 yrs)`,\n" + 
           "`Target Group: Senior`,\n" + 
           "`Target Group: Gender-specific`,\n" + 
           "`Target Group: Refugees`,\n" + 
           "`Target Group: Ethnic/cultural/linguistic group`,\n" + 
           "`Target Group: Deaf or Hard of Hearing`,\n" + 
           "`Target Group: Blind or Partially Sighted`,\n" + 
           "`Target Group: Lesbian, Gay, Bisexual, Transgender, Queer (LGBTQ)`,\n" + 
           "`Target Group: Families/Parents`,\n" + 
           "`Target Group: Other impairments (physical, mental)`,\n" + 
           "`TG: Clients w international training in a regulated profession`,\n" + 
           "`TG: Clients with international training in a regulated trade`,\n" + 
           "`Target Group: Official language minorities`,\n" + 
           "`Status of Service`,\n" + 
           "`Reason for Leaving Service`,\n" + 
           "`Start Date (YYYY-MM-DD)`,\n" + 
           "`End Date (YYYY-MM-DD)`,\n" + 
           "`Projected End Date (YYYY-MM-DD)`,\n" + 
           "`Essential Skills and Aptitudes Training Received?`,\n" + 
           "`Computer Skills`,\n" + 
           "`Document Use`,\n" + 
           "`Interpersonal Skills and Workplace Culture`,\n" + 
           "`Leadership Training`,\n" + 
           "`Life Skills`,\n" + 
           "`Numeracy`,\n" + 
           "`Support Services Received`,\n" + 
           "`Care for Newcomer Children`,\n" + 
           "`Child 1: Age`,\n" + 
           "`Child 1: Type of Care`,\n" + 
           "`Child 2: Age`,\n" + 
           "`Child 2: Type of Care`,\n" + 
           "`Child 3: Age`,\n" + 
           "`Child 3: Type of Care`,\n" + 
           "`Child 4: Age`,\n" + 
           "`Child 4: Type of Care`,\n" + 
           "`Child 5: Age`,\n" + 
           "`Child 5: Type of Care`,\n" + 
           "`Transportation`,\n" + 
           "`Provisions for Disabilities`,\n" + 
           "`Translation`,\n" + 
           "`Translation language Between`,\n" + 
           "`Translation language And`,\n" + 
           "`Interpretation`,\n" + 
           "`Interpretation language Between`,\n" + 
           "`Interpretation language And`,\n" + 
           "`Crisis Counselling`,\n" + 
           "`Total Length of Service: Hours`,\n" + 
           "`Total Length of Service: Minutes`,\n" + 
           "`Reason for update`)\n" + 
           "VALUES\n" + 
           "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
       PreparedStatement ps = con.prepareStatement(sql);
       SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
       ExcelBook excelBook = new ExcelBook(filename);
       System.out.println(excelBook.getSheetMap().get("Community Connections"));
       ExcelSheet a = excelBook.getSheetMap().get("Community Connections");
       java.util.Date date;
       java.sql.Date sqlDate;  
       System.out.println(a);
       List<ExcelRow> excelrowlist = new ArrayList<ExcelRow>();
       List<Object> list = new ArrayList<Object>();
       boolean allblank = true;
       Object cellData;
       ExcelRow excelrow = null;
//       for (ExcelRow row : a.getRows()) {
//       for (int i = 0; i<a.myGetSize() ;i++) {
//         list = row.getCells();
         excelrowlist = a.getRows();
         for (int i=3 ; i<excelrowlist.size();i++) {
           allblank = true;
           excelrow = excelrowlist.get(i);
           list = excelrow.getCells();
           for (int j = 0; j<excelrow.getCells().size();j++) {
             cellData = list.get(j);
             if (cellData.equals("NULL")) {
               ps.setNull(j+1, java.sql.Types.NULL);
//               System.out.println(list.get(j) + " this is null");
               continue;
             }
             if (j==4 || j == 34 || j ==35 || j== 36) {
//               System.out.println("date");
               date = sdf1.parse(list.get(j).toString());
               sqlDate = new java.sql.Date(date.getTime());  
               ps.setDate(j+1, sqlDate);
               System.out.println(list.get(j) + " " + j);
               allblank = false;
             } else if (j == 65 || j == 66) {
               ps.setInt(j+1, Integer.parseInt((String) list.get(j)));
               System.out.println(list.get(j));
             } else {
//               System.out.println("string");
               ps.setString(j+1, list.get(j).toString());
               System.out.println(list.get(j));
               allblank = false;
             }
           }
           if (!allblank) {
             ps.executeUpdate();
           } else {
             break;
           }
         }       
     }
	   
	   public static void insertInfoAndOrien(Connection con) throws SQLException, ParseException  {
       String filename = "New_iCARE_Template_Comb_with_Examples.xlsx";
       String sql = "INSERT INTO `assignmentdb`.`info&orien`\n" + 
           "(`Processing Details`,\n" + 
           "`Update Record ID`,\n" + 
           "`Unique Identifier`,\n" + 
           "`Unique Identifier Value`,\n" + 
           "`Date of Birth (YYYY-MM-DD)`,\n" + 
           "`Postal Code where the service was received`,\n" + 
           "`Start Date of Service (YYYY-MM-DD)`,\n" + 
           "`Language of Service`,\n" + 
           "`Official Language of Preference`,\n" + 
           "`Type of Institution/Organization Where Client Received Services`,\n" + 
           "`Referred By`,\n" + 
           "`Services Received`,\n" + 
           "`Total Length of Orientation`,\n" + 
           "`Total Length of Orientation: Hours`,\n" + 
           "`Total Length of Orientation: Minutes`,\n" + 
           "`Number of Clients in Group`,\n" + 
           "`Directed at a specific Target Group`,\n" + 
           "`Target Group: Children (0-14 yrs)`,\n" + 
           "`Target Group: Youth (15-24 yrs)`,\n" + 
           "`Target Group: Seniors`,\n" + 
           "`Target Group: Gender-specific`,\n" + 
           "`Target Group: Refugees`,\n" + 
           "`Target Group: Ethnic/cultural/linguistic group`,\n" + 
           "`Target Group: Deaf or Hard of Hearing`,\n" + 
           "`Target Group: Blind or Partially Sighted`,\n" + 
           "`TG: Lesbian, Gay, Bisexual, Transgender, Queer (LGBTQ)`,\n" + 
           "`Target Group: Families/Parents`,\n" + 
           "`Target Group: Clients with other impairments (physical, mental)`,\n" + 
           "`TG: Clients w international training in a regulated profession`,\n" + 
           "`TG: Clients with international training in a regulated trade`,\n" + 
           "`Target Group: Official Language minorities`,\n" + 
           "`Overview of Canada`,\n" + 
           "`Overview of Canada Referrals`,\n" + 
           "`Sources of Information`,\n" + 
           "`Sources of Information Referrals`,\n" + 
           "`Rights and Freedoms`,\n" + 
           "`Rights and Freedoms Referrals`,\n" + 
           "`Canadian Law and Justice`,\n" + 
           "`Canadian Law and Justice Referrals`,\n" + 
           "`Important Documents`,\n" + 
           "`Important Documents Referrals`,\n" + 
           "`Improving English or French`,\n" + 
           "`Improving English or French Referrals`,\n" + 
           "`Employment and Income`,\n" + 
           "`Employment and Income Referrals`,\n" + 
           "`Education`,\n" + 
           "`Education Referrals`,\n" + 
           "`Housing`,\n" + 
           "`Housing Referrals`,\n" + 
           "`Health`,\n" + 
           "`Health Referrals`,\n" + 
           "`Money and Finances`,\n" + 
           "`Money and Finances Referrals`,\n" + 
           "`Transportation1`,\n" + 
           "`Transportation Referrals`,\n" + 
           "`Communications and Media`,\n" + 
           "`Communications and Media Referrals`,\n" + 
           "`Community Engagement`,\n" + 
           "`Community Engagement Referrals`,\n" + 
           "`Becoming a Canadian Citizen`,\n" + 
           "`Becoming a Canadian Citizen Referrals`,\n" + 
           "`Interpersonal Conflict`,\n" + 
           "`Interpersonal Conflict Referrals`,\n" + 
           "`Essential Skills and Aptitude Training Received?`,\n" + 
           "`Computer skills`,\n" + 
           "`Document Use`,\n" + 
           "`Interpersonal Skills and Workplace Culture`,\n" + 
           "`Leadership Training`,\n" + 
           "`Numeracy`,\n" + 
           "`Skills or Responsibilities of Citizenship Information Received?`,\n" + 
           "`Life Skills`,\n" + 
           "`Rights and Responsibilities of Citizenship`,\n" + 
           "`Support Services Received`,\n" + 
           "`Care for Newcomer Children`,\n" + 
           "`Child 1: Age`,\n" + 
           "`Child 1: Type of Care`,\n" + 
           "`Child 2: Age`,\n" + 
           "`Child 2: Type of Care`,\n" + 
           "`Child 3: Age`,\n" + 
           "`Child 3: Type of Care`,\n" + 
           "`Child 4: Age`,\n" + 
           "`Child 4: Type of Care`,\n" + 
           "`Child 5: Age`,\n" + 
           "`Child 5: Type of Care`,\n" + 
           "`Transportation`,\n" + 
           "`Provisions for Disabilities`,\n" + 
           "`Translation`,\n" + 
           "`Translation language Between`,\n" + 
           "`Translation language And`,\n" + 
           "`Interpretation`,\n" + 
           "`Interpretation language Between`,\n" + 
           "`Interpretation language And`,\n" + 
           "`Crisis Counselling`,\n" + 
           "`End Date of Service (YYYY-MM-DD)`,\n" + 
           "`Reason for update`)\n" + 
           "VALUES\n" + 
           "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
           + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
       PreparedStatement ps = con.prepareStatement(sql);
       SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
       ExcelBook excelBook = new ExcelBook(filename);
       System.out.println(excelBook.getSheetMap().get("Info&Orien"));
       ExcelSheet a = excelBook.getSheetMap().get("Info&Orien");
       java.util.Date date;
       java.sql.Date sqlDate;  
       System.out.println(a);
       List<ExcelRow> excelrowlist = new ArrayList<ExcelRow>();
       List<Object> list = new ArrayList<Object>();
       boolean allblank = true;
       Object cellData;
       ExcelRow excelrow = null;
//       for (ExcelRow row : a.getRows()) {
//       for (int i = 0; i<a.myGetSize() ;i++) {
//         list = row.getCells();
         excelrowlist = a.getRows();
         for (int i=3 ; i<excelrowlist.size();i++) {
           allblank = true;
           excelrow = excelrowlist.get(i);
           list = excelrow.getCells();
           for (int j = 0; j<excelrow.getCells().size();j++) {
             cellData = list.get(j);
             if (cellData.equals("NULL")) {
               ps.setNull(j+1, java.sql.Types.NULL);
//               System.out.println(list.get(j) + " this is null");
               continue;
             }
             if (j==4 || j == 6 || j == 93) {
//               System.out.println("date");
               date = sdf1.parse(list.get(j).toString());
               sqlDate = new java.sql.Date(date.getTime());  
               ps.setDate(j+1, sqlDate);
               System.out.println(list.get(j) + " " + j);
               allblank = false;
             } else if (j == 13 || j == 14) {
               ps.setInt(j+1, Integer.parseInt((String) list.get(j)));
               System.out.println(list.get(j));
             } else {
//               System.out.println("string");
               ps.setString(j+1, list.get(j).toString());
               System.out.println(list.get(j));
               allblank = false;
             }
           }
           if (!allblank) {
             ps.executeUpdate();
           } else {
             break;
           }
         }       
     }
	   
	   public static void insertEmploymentTemplateData(Connection con) throws SQLException, ParseException  {
       String filename = "New_iCARE_Template_Comb_with_Examples.xlsx";
       String sql = "INSERT INTO `assignmentdb`.`employment`\n" + 
           "(`Processing Details`,\n" + 
           "`Update Record ID`,\n" + 
           "`Unique Identifier`,\n" + 
           "`Unique Identifier Value`,\n" + 
           "`Date of Birth (YYYY-MM-DD)`,\n" + 
           "`Postal Code where the service was received`,\n" + 
           "`Registration in an employment intervention`,\n" + 
           "`A referral to`,\n" + 
           "`Language of Service`,\n" + 
           "`Official Language of Preference`,\n" + 
           "`Type of Institution/Organization Where Client Received Services`,\n" + 
           "`Referred By`,\n" + 
           "`Referral Date (YYYY-MM-DD)`,\n" + 
           "`Employment Status`,\n" + 
           "`Education Status`,\n" + 
           "`Occupation in Canada`,\n" + 
           "`Intended Occupation`,\n" + 
           "`Intervention Type`,\n" + 
           "`Long Term Intervention: Intervention Received`,\n" + 
           "`Long Term Intervention: Status of Intervention`,\n" + 
           "`Long Term Intervention: Reason For Leaving Intervention`,\n" + 
           "`Long Term Intervention: Start Date (YYYY-MM-DD)`,\n" + 
           "`Long Term Intervention: End Date (YYYY-MM-DD)`,\n" + 
           "`Long Term Intervention: Size of Employer`,\n" + 
           "`Long Term Intervention: Placement Was`,\n" + 
           "`Long Term Intervention: Hours Per Week`,\n" + 
           "`Long Term Intervention: Client Met Mentor Regularly at`,\n" + 
           "`LTI: Average Hours/Week in Contact with Mentor`,\n" + 
           "`LTI: Profession/Trade For Which Services Were Received`,\n" + 
           "`Skills and Aptitude Training Received as Part of this Service?`,\n" + 
           "`Computer skills`,\n" + 
           "`Document Use`,\n" + 
           "`Interpersonal Skills and Workplace Culture`,\n" + 
           "`Leadership Training`,\n" + 
           "`Numeracy`,\n" + 
           "`Short Term Intervention: Service Received 1`,\n" + 
           "`Short Term Intervention: Date 1 (YYYY-MM-DD)`,\n" + 
           "`Short Term Intervention: Service Received 2`,\n" + 
           "`Short Term Intervention: Date 2 (YYYY-MM-DD)`,\n" + 
           "`Short Term Intervention: Service Received 3`,\n" + 
           "`Short Term Intervention: Date 3 (YYYY-MM-DD)`,\n" + 
           "`Short Term Intervention: Service Received 4`,\n" + 
           "`Short Term Intervention: Date 4 (YYYY-MM-DD)`,\n" + 
           "`Short Term Intervention: Service Received 5`,\n" + 
           "`Short Term Intervention: Date 5 (YYYY-MM-DD)`,\n" + 
           "`Support Services Received`,\n" + 
           "`Care for Newcomer Children`,\n" + 
           "`Child 1: Age`,\n" + 
           "`Child 1: Type of Care`,\n" + 
           "`Child 2: Age`,\n" + 
           "`Child 2: Type of Care`,\n" + 
           "`Child 3: Age`,\n" + 
           "`Child 3: Type of Care`,\n" + 
           "`Child 4: Age`,\n" + 
           "`Child 4: Type of Care`,\n" + 
           "`Child 5: Age`,\n" + 
           "`Child 5: Type of Care`,\n" + 
           "`Transportation`,\n" + 
           "`Provisions for Disabilities`,\n" + 
           "`Translation`,\n" + 
           "`Translation language Between`,\n" + 
           "`Translation language And`,\n" + 
           "`Interpretation`,\n" + 
           "`Interpretation language Between`,\n" + 
           "`Interpretation language And`,\n" + 
           "`Crisis Counselling`,\n" + 
           "`Time Spent With Client's Employment Needs: Hours`,\n" + 
           "`Time Spent With Client's Employment Needs: Minutes`,\n" + 
           "`Reason for update`)\n" + 
           "VALUES\n" + 
           "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
       PreparedStatement ps = con.prepareStatement(sql);
       SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
       ExcelBook excelBook = new ExcelBook(filename);
       System.out.println(excelBook.getSheetMap().get("Employment"));
       ExcelSheet a = excelBook.getSheetMap().get("Employment");
       java.util.Date date;
       java.sql.Date sqlDate;  
       System.out.println(a);
       List<ExcelRow> excelrowlist = new ArrayList<ExcelRow>();
       List<Object> list = new ArrayList<Object>();
       boolean allblank = true;
       ExcelRow excelrow = null;
//       for (ExcelRow row : a.getRows()) {
//       for (int i = 0; i<a.myGetSize() ;i++) {
//         list = row.getCells();
         excelrowlist = a.getRows();
         for (int i=3 ; i<excelrowlist.size();i++) {
           allblank = true;
           excelrow = excelrowlist.get(i);
           list = excelrow.getCells();
           for (int j = 0; j<excelrow.getCells().size();j++) {
             Object cellData = list.get(j);
             if (cellData.equals("NULL")) {
               ps.setNull(j+1, java.sql.Types.NULL);
//               System.out.println(list.get(j) + " this is null");
               continue;
             }
             if (j==4 || j == 12 || j == 21 || j == 22 || j == 36 || j== 38 || j == 40 || j == 42 || j == 44) {
//               System.out.println("date");
               date = sdf1.parse(list.get(j).toString());
               sqlDate = new java.sql.Date(date.getTime());  
               ps.setDate(j+1, sqlDate);
               System.out.println(list.get(j) + " " + j);
               allblank = false;
             } else if (j == 66|| j == 67) {
               ps.setInt(j+1, Integer.parseInt((String) list.get(j)));
               System.out.println(list.get(j));
             } else {
//               System.out.println("string");
               ps.setString(j+1, list.get(j).toString());
               System.out.println(list.get(j));
               allblank = false;
             }
           }
           if (!allblank) {
             ps.executeUpdate();
           } else {
             break;
           }
         }       
     }
	   
	   public static void insertLTClientEnroll(Connection con) throws SQLException, ParseException  {
       String filename = "New_iCARE_Template_Comb_with_Examples.xlsx";
       String sql = "INSERT INTO `assignmentdb`.`lt client enrol`\n" + 
           "(`Processing Details`,\n" + 
           "`Update record ID`,\n" + 
           "`Unique Identifier Type`,\n" + 
           "`Unique Identifier Value`,\n" + 
           "`Client Date of Birth (YYYY-MM-DD)`,\n" + 
           "`Postal Code where the service was received`,\n" + 
           "`Course Code`,\n" + 
           "`Date of Client's First Class (YYYY-MM-DD)`,\n" + 
           "`Official Language of Preference`,\n" + 
           "`Support services received`,\n" + 
           "`Care for newcomer children`,\n" + 
           "`Child 1: Age`,\n" + 
           "`Child 1: Type of Care`,\n" + 
           "`Child 2: Age`,\n" + 
           "`Child 2: Type of Care`,\n" + 
           "`Child 3: Age`,\n" + 
           "`Child 3: Type of Care`,\n" + 
           "`Child 4: Age`,\n" + 
           "`Child 4: Type of Care`,\n" + 
           "`Child 5: Age`,\n" + 
           "`Child 5: Type of Care`,\n" + 
           "`Transportation`,\n" + 
           "`Provisions for disabilities`,\n" + 
           "`Translation`,\n" + 
           "`Translation language Between`,\n" + 
           "`Translation language And`,\n" + 
           "`Interpretation`,\n" + 
           "`Interpretation language Between`,\n" + 
           "`Interpretation language And`,\n" + 
           "`Crisis Counselling`,\n" + 
           "`Reason for update`)\n" + 
           "VALUES\n" + 
           "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
       PreparedStatement ps = con.prepareStatement(sql);
       SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
       ExcelBook excelBook = new ExcelBook(filename);
       System.out.println(excelBook.getSheetMap().get("LT Client Enrol"));
       ExcelSheet a = excelBook.getSheetMap().get("LT Client Enrol");
       java.util.Date date;
       java.sql.Date sqlDate;  
       System.out.println(a);
       List<ExcelRow> excelrowlist = new ArrayList<ExcelRow>();
       List<Object> list = new ArrayList<Object>();
       boolean allblank = true;
       ExcelRow excelrow = null;
//       for (ExcelRow row : a.getRows()) {
//       for (int i = 0; i<a.myGetSize() ;i++) {
//         list = row.getCells();
         excelrowlist = a.getRows();
         for (int i=3 ; i<excelrowlist.size();i++) {
           allblank = true;
           excelrow = excelrowlist.get(i);
           list = excelrow.getCells();
           for (int j = 0; j<excelrow.getCells().size();j++) {
             Object cellData = list.get(j);
             if (cellData.equals("NULL")) {
               ps.setNull(j+1, java.sql.Types.NULL);
//               System.out.println(list.get(j) + " this is null");
               continue;
             }
             if (j==4 || j == 7) {
               date = sdf1.parse(list.get(j).toString());
               sqlDate = new java.sql.Date(date.getTime());  
               ps.setDate(j+1, sqlDate);
               System.out.println(list.get(j) + " " + j);
               allblank = false;
             } else {
               ps.setString(j+1, list.get(j).toString());
               System.out.println(list.get(j));
               allblank = false;
             }
           }
           if (!allblank) {
             ps.executeUpdate();
           } else {
             break;
           }
         }       
     }
	   
	   public static void insertLTCourseSetup(Connection con) throws SQLException, ParseException  {
       String filename = "New_iCARE_Template_Comb_with_Examples.xlsx";
       String sql = "INSERT INTO `assignmentdb`.`lt couse setup`\n" + 
           "(`Processing Details`,\n" + 
           "`Update record ID`,\n" + 
           "`Course Code`,\n" + 
           "`Notes`,\n" + 
           "`Course Held On An Ongoing Basis`,\n" + 
           "`Official Language of Course`,\n" + 
           "`Format of Training Provided`,\n" + 
           "`Classes Held At`,\n" + 
           "`In-Person Instruction (%)`,\n" + 
           "`Online/Distance Instruction (%)`,\n" + 
           "`Total Number of Spots in Course`,\n" + 
           "`Number of IRCC-Funded Spots in Course`,\n" + 
           "`New Students Can Enrol in the Course`,\n" + 
           "`Support Services Available for Client in this Course`,\n" + 
           "`Care for Newcomer Children`,\n" + 
           "`Transportation`,\n" + 
           "`Provisions for Disabilities`,\n" + 
           "`Course Start Date (YYYY-MM-DD)`,\n" + 
           "`Course End Date (YYYY-MM-DD)`,\n" + 
           "`Schedule: Morning`,\n" + 
           "`Schedule: Afternoon`,\n" + 
           "`Schedule: Evening`,\n" + 
           "`Schedule: Weekend`,\n" + 
           "`Schedule: Anytime`,\n" + 
           "`Schedule: Online`,\n" + 
           "`Instructional Hours Per Class`,\n" + 
           "`Classes Per Week`,\n" + 
           "`Weeks of Instruction`,\n" + 
           "`Weeks of Instruction Per Year`,\n" + 
           "`Dominant Focus of the Course`,\n" + 
           "`Course Directed at a Specific Target Group`,\n" + 
           "`Children (0-14 yrs)`,\n" + 
           "`Youth (15-24 yrs)`,\n" + 
           "`Senior`,\n" + 
           "`Gender-specific`,\n" + 
           "`Refugees`,\n" + 
           "`Official language minorities`,\n" + 
           "`Ethnic/cultural/linguistic group`,\n" + 
           "`Deaf or Hard of Hearing`,\n" + 
           "`Blind or Partially Sighted`,\n" + 
           "`Clients with other impairments (physical, mental)`,\n" + 
           "`Lesbian, Gay, Bisexual, Transgender, Queer (LGBTQ)`,\n" + 
           "`Families/Parents`,\n" + 
           "`Clients with international training in a regulated profession`,\n" + 
           "`Clients with international training in a regulated trade`,\n" + 
           "`Materials Used in Course`,\n" + 
           "`Citizenship preparation`,\n" + 
           "`PBLA language companion`,\n" + 
           "`Contact Name`,\n" + 
           "`Street Number`,\n" + 
           "`Street Name`,\n" + 
           "`Street Type`,\n" + 
           "`Street Direction`,\n" + 
           "`Unit/Suite`,\n" + 
           "`Province`,\n" + 
           "`City`,\n" + 
           "`Postal Code (A#A#A#)`,\n" + 
           "`Telephone Number (###-###-####)`,\n" + 
           "`Telephone Extension`,\n" + 
           "`Email Address`,\n" + 
           "`Listening Skill Level 1`,\n" + 
           "`Listening Skill Level 2`,\n" + 
           "`Listening Skill Level 3`,\n" + 
           "`Listening Skill Level 4`,\n" + 
           "`Listening Skill Level 5`,\n" + 
           "`Listening Skill Level 6`,\n" + 
           "`Listening Skill Level 7`,\n" + 
           "`Listening Skill Level 8`,\n" + 
           "`Listening Skill Level 9`,\n" + 
           "`Listening Skill Level 10`,\n" + 
           "`Listening Skill Level 11`,\n" + 
           "`Listening Skill Level 12`,\n" + 
           "`Speaking Skill Level 1`,\n" + 
           "`Speaking Skill Level 2`,\n" + 
           "`Speaking Skill Level 3`,\n" + 
           "`Speaking Skill Level 4`,\n" + 
           "`Speaking Skill Level 5`,\n" + 
           "`Speaking Skill Level 6`,\n" + 
           "`Speaking Skill Level 7`,\n" + 
           "`Speaking Skill Level 8`,\n" + 
           "`Speaking Skill Level 9`,\n" + 
           "`Speaking Skill Level 10`,\n" + 
           "`Speaking Skill Level 11`,\n" + 
           "`Speaking Skill Level 12`,\n" + 
           "`Reading Skill Level 1`,\n" + 
           "`Reading Skill Level 2`,\n" + 
           "`Reading Skill Level 3`,\n" + 
           "`Reading Skill Level 4`,\n" + 
           "`Reading Skill Level 5`,\n" + 
           "`Reading Skill Level 6`,\n" + 
           "`Reading Skill Level 7`,\n" + 
           "`Reading Skill Level 8`,\n" + 
           "`Reading Skill Level 9`,\n" + 
           "`Reading Skill Level 10`,\n" + 
           "`Reading Skill Level 11`,\n" + 
           "`Reading Skill Level 12`,\n" + 
           "`Reading Skill Level 13`,\n" + 
           "`Reading Skill Level 14`,\n" + 
           "`Reading Skill Level 15`,\n" + 
           "`Reading Skill Level 16`,\n" + 
           "`Reading Skill Level 17`,\n" + 
           "`Writing Skill Level 1`,\n" + 
           "`Writing Skill Level 2`,\n" + 
           "`Writing Skill Level 3`,\n" + 
           "`Writing Skill Level 4`,\n" + 
           "`Writing Skill Level 5`,\n" + 
           "`Writing Skill Level 6`,\n" + 
           "`Writing Skill Level 7`,\n" + 
           "`Writing Skill Level 8`,\n" + 
           "`Writing Skill Level 9`,\n" + 
           "`Writing Skill Level 10`,\n" + 
           "`Writing Skill Level 11`,\n" + 
           "`Writing Skill Level 12`,\n" + 
           "`Writing Skill Level 13`,\n" + 
           "`Writing Skill Level 14`,\n" + 
           "`Writing Skill Level 15`,\n" + 
           "`Writing Skill Level 16`,\n" + 
           "`Writing Skill Level 17`)\n" + 
           "VALUES\n" + 
           "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
           + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
           + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
       PreparedStatement ps = con.prepareStatement(sql);
       SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
       ExcelBook excelBook = new ExcelBook(filename);
       System.out.println(excelBook.getSheetMap().get("LT Course Setup"));
       ExcelSheet a = excelBook.getSheetMap().get("LT Course Setup");
       java.util.Date date;
       java.sql.Date sqlDate;  
       System.out.println(a);
       List<ExcelRow> excelrowlist = new ArrayList<ExcelRow>();
       List<Object> list = new ArrayList<Object>();
       boolean allblank = true;
       ExcelRow excelrow = null;
//       for (ExcelRow row : a.getRows()) {
//       for (int i = 0; i<a.myGetSize() ;i++) {
//         list = row.getCells();
         excelrowlist = a.getRows();
//         int[] varchar_ = {0,1,2,3,5,6,7,12,29,48,50,51,52,54,55,56,57,58,59};
         int[] int_ = {10,11,26,27,28,49,53};
         int[] bool_ = {4,13,14,15,16,19,20,21,22,23,24,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47};
         int[] float_ = {8,9,25};
         int[] date_ = {17,18};
         List<Integer> intlist = convertArrayToList(int_);
         List<Integer> boollist = convertArrayToList(bool_);
         List<Integer> floatlist = convertArrayToList(float_);
         List<Integer> datelist = convertArrayToList(date_);
         for (int i=3 ; i<excelrowlist.size();i++) {
           allblank = true;
           excelrow = excelrowlist.get(i);
           list = excelrow.getCells();
           for (int j = 0; j<excelrow.getCells().size();j++) {
//             Object cellData = list.get(j);
             if (list.get(j).equals("NULL")) {
               ps.setNull(j+1, java.sql.Types.NULL);
//               System.out.println(list.get(j) + " this is null");
               continue;
             }
             if (datelist.contains(j)) {
               date = sdf1.parse(list.get(j).toString());
               sqlDate = new java.sql.Date(date.getTime());  
               ps.setDate(j+1, sqlDate);
               allblank = false;
             } else if (boollist.contains(j)) {
               if (list.get(j).toString().equalsIgnoreCase("yes")) {
                 ps.setBoolean(j+1, true);
               } else if (list.get(j).toString().equalsIgnoreCase("no")) {
                 ps.setBoolean(j+1, false);
               } else {
                 throw new SQLException();
               }
             } else if (floatlist.contains(j)) {
               ps.setFloat(j+1, Float.parseFloat(list.get(j).toString()));
             } else if (intlist.contains(j)) {
                 ps.setInt(j+1, Integer.parseInt((String) list.get(j)));
             } else {
               ps.setString(j+1, list.get(j).toString());
               allblank = false;
             }
             System.out.println(list.get(j));
           }
           if (!allblank) {
             ps.executeUpdate();
           } else {
             break;
           }
         }       
     }

	  
	  public static void main(String args[]) throws IOException, SQLException, ParseException {
	    Connection con = DatabaseDriverHelper.connectOrCreateDataBase();
	    insertClientProfile(con);
	    insertLTClientExit(con);
	    insertNeedsAssessmentReferrals(con);
	    insertCommunityConnections(con);
	    insertInfoAndOrien(con);
	    insertEmploymentTemplateData(con);
	    insertLTClientEnroll(con);
	    insertLTCourseSetup(con);
	  }
	  
	  public static List<Integer> convertArrayToList(int[] array) {
	    List<Integer> arrayList = new ArrayList<Integer>();
	     for (int a: array) {
	       arrayList.add(a);
	     }
	     return arrayList;
	  }
}
