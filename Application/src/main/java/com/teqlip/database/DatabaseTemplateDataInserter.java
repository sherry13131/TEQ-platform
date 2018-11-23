package com.teqlip.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.teqlip.excel.ExcelBook;
import com.teqlip.excel.ExcelRow;
import com.teqlip.excel.ExcelSheet;

public class DatabaseTemplateDataInserter {
  public static void insertLTClientExit(String filename, ExcelSheet a, Connection con) throws SQLException, ParseException  {
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
    java.util.Date date;
    java.sql.Date sqlDate;
    List<ExcelRow> excelrowlist = new ArrayList<ExcelRow>();
    List<Object> list = new ArrayList<Object>();
    boolean allblank = true;
    ExcelRow excelrow = null;
    excelrowlist = a.getRows();
    for (int i=3 ; i<excelrowlist.size();i++) {
      excelrow = excelrowlist.get(i);
      list = excelrow.getCells();
      for (int j = 0; j<excelrow.getCells().size();j++) {
        allblank = true;
        if (list.get(j).equals("NULL")) {
          ps.setNull(j+1, java.sql.Types.NULL);
          continue;
        }
        if (j==4 || j==7) {
          date = sdf1.parse(list.get(j).toString());
          sqlDate = new java.sql.Date(date.getTime());  
          ps.setDate(j+1, sqlDate);
          allblank = false;
        } else {
          ps.setString(j+1, list.get(j).toString());
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
  
   public static void insertClientProfile(String filename, ExcelSheet a, Connection con) throws SQLException, ParseException  {
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
      java.util.Date date;
      java.sql.Date sqlDate;
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
              continue;
            }
            if (j==3) {
              date = sdf1.parse(list.get(j).toString());
              sqlDate = new java.sql.Date(date.getTime());  
              ps.setDate(j+1, sqlDate);
              allblank = false;
            } else {
              ps.setString(j+1, list.get(j).toString());
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
   
   public static void insertNeedsAssessmentReferrals(String filename, ExcelSheet a, Connection con) throws SQLException, ParseException  {
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
         "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
         + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     PreparedStatement ps = con.prepareStatement(sql);
     SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
     java.util.Date date;
     java.sql.Date sqlDate;
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
             continue;
           }
           if (j==4 || j==6 || j==90) {
             date = sdf1.parse(list.get(j).toString());
             sqlDate = new java.sql.Date(date.getTime());  
             ps.setDate(j+1, sqlDate);
             allblank = false;
           } else {
             ps.setString(j+1, list.get(j).toString());
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
   
   public static void insertCommunityConnections(String filename, ExcelSheet a, Connection con) throws SQLException, ParseException  {
     String sql = "INSERT INTO `assignmentdb`.`community connections` " + 
         "(`Processing Details`, " + 
         "`Update Record ID`, " + 
         "`Unique Identifier`, " + 
         "`Unique Identifier Value`, " + 
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
         "`Reason for update`) " + 
         "VALUES " + 
         "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
         + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
     PreparedStatement ps = con.prepareStatement(sql);
     SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
     java.util.Date date;
     java.sql.Date sqlDate;
     List<ExcelRow> excelrowlist = new ArrayList<ExcelRow>();
     List<Object> list = new ArrayList<Object>();
     boolean allblank = true;
     Object cellData;
     ExcelRow excelrow = null;
       excelrowlist = a.getRows();
       for (int i=3 ; i<excelrowlist.size();i++) {
         allblank = true;
         excelrow = excelrowlist.get(i);
         list = excelrow.getCells();
         for (int j = 0; j<excelrow.getCells().size();j++) {
           cellData = list.get(j);
           if (cellData.equals("NULL")) {
             ps.setNull(j+1, java.sql.Types.NULL);
             continue;
           }
           if (j==4 || j == 34 || j ==35 || j== 36) {
             date = sdf1.parse(list.get(j).toString());
             sqlDate = new java.sql.Date(date.getTime());  
             ps.setDate(j+1, sqlDate);
             allblank = false;
           } else if (j == 65 || j == 66) {
             ps.setInt(j+1, Integer.parseInt((String) list.get(j)));
           } else {
             ps.setString(j+1, list.get(j).toString());
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
   
   public static void insertInfoAndOrien(String filename, ExcelSheet a, Connection con) throws SQLException, ParseException  {
     String sql = "INSERT INTO `assignmentdb`.`info&orien` " + 
         "(`Processing Details`, " + 
         "`Update Record ID`, " + 
         "`Unique Identifier`, " + 
         "`Unique Identifier Value`, " + 
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
         "`Reason for update`) " + 
         "VALUES " + 
         "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
         + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
     PreparedStatement ps = con.prepareStatement(sql);
     SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
     java.util.Date date;
     java.sql.Date sqlDate;
     List<ExcelRow> excelrowlist = new ArrayList<ExcelRow>();
     List<Object> list = new ArrayList<Object>();
     boolean allblank = true;
     Object cellData;
     ExcelRow excelrow = null;
       excelrowlist = a.getRows();
       for (int i=3 ; i<excelrowlist.size();i++) {
         allblank = true;
         excelrow = excelrowlist.get(i);
         list = excelrow.getCells();
         for (int j = 0; j<excelrow.getCells().size();j++) {
           cellData = list.get(j);
           if (cellData.equals("NULL")) {
             ps.setNull(j+1, java.sql.Types.NULL);
             continue;
           }
           if (j==4 || j == 6 || j == 93) {
             date = sdf1.parse(list.get(j).toString());
             sqlDate = new java.sql.Date(date.getTime());  
             ps.setDate(j+1, sqlDate);
             allblank = false;
           } else if (j == 13 || j == 14) {
             ps.setInt(j+1, Integer.parseInt((String) list.get(j)));
           } else {
             ps.setString(j+1, list.get(j).toString());
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
   
   public static void insertEmploymentTemplateData(String filename, ExcelSheet a, Connection con) throws SQLException, ParseException  {
     String sql = "INSERT ignore INTO `assignmentdb`.`employment` " + 
         "(`Processing Details`, " + 
         "`Update Record ID`, " + 
         "`Unique Identifier`, " + 
         "`Unique Identifier Value`, " + 
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
         "`Reason for update`) " + 
         "VALUES " + 
         "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
         + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
     PreparedStatement ps = con.prepareStatement(sql);
     SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
     java.util.Date date;
     java.sql.Date sqlDate;
     List<ExcelRow> excelrowlist = new ArrayList<ExcelRow>();
     List<Object> list = new ArrayList<Object>();
     boolean allblank = true;
     ExcelRow excelrow = null;
       excelrowlist = a.getRows();
       for (int i=3 ; i<excelrowlist.size();i++) {
         allblank = true;
         excelrow = excelrowlist.get(i);
         list = excelrow.getCells();
         for (int j = 0; j<excelrow.getCells().size();j++) {
           Object cellData = list.get(j);
           if (cellData.equals("NULL")) {
             ps.setNull(j+1, java.sql.Types.NULL);
             continue;
           }
           if (j==4 || j == 12 || j == 21 || j == 22 || j == 36 || j== 38 || j == 40 || j == 42 || j == 44) {
             date = sdf1.parse(list.get(j).toString());
             sqlDate = new java.sql.Date(date.getTime());  
             ps.setDate(j+1, sqlDate);
             allblank = false;
           } else if (j == 66|| j == 67) {
             ps.setInt(j+1, Integer.parseInt((String) list.get(j)));
           } else {
             ps.setString(j+1, list.get(j).toString());
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
   
   public static void insertLTClientEnroll(String filename, ExcelSheet a, Connection con) throws SQLException, ParseException  {
     String sql = "INSERT INTO `assignmentdb`.`lt client enrol` " + 
         "(`Processing Details`, " + 
         "`Update record ID`, " + 
         "`Unique Identifier Type`, " + 
         "`Unique Identifier Value`, " + 
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
         "`Reason for update`) " + 
         "VALUES " + 
         "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
     PreparedStatement ps = con.prepareStatement(sql);
     SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
     java.util.Date date;
     java.sql.Date sqlDate;
     List<ExcelRow> excelrowlist = new ArrayList<ExcelRow>();
     List<Object> list = new ArrayList<Object>();
     boolean allblank = true;
     ExcelRow excelrow = null;
       excelrowlist = a.getRows();
       for (int i=3 ; i<excelrowlist.size();i++) {
         allblank = true;
         excelrow = excelrowlist.get(i);
         list = excelrow.getCells();
         for (int j = 0; j<excelrow.getCells().size();j++) {
           Object cellData = list.get(j);
           if (cellData.equals("NULL")) {
             ps.setNull(j+1, java.sql.Types.NULL);
             continue;
           }
           if (j==4 || j == 7) {
             date = sdf1.parse(list.get(j).toString());
             sqlDate = new java.sql.Date(date.getTime());  
             ps.setDate(j+1, sqlDate);
             allblank = false;
           } else {
             ps.setString(j+1, list.get(j).toString());
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
   
   public static void insertLTCourseSetup(String filename, ExcelSheet a, Connection con) throws SQLException, ParseException  {
     String sql = "INSERT INTO `assignmentdb`.`lt course setup` " + 
         "(`Processing Details`, " + 
         "`Update record ID`, " + 
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
         "`Writing Skill Level 17`) " + 
         "VALUES " + 
         "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
         + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
         + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
     PreparedStatement ps = con.prepareStatement(sql);
     SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
     java.util.Date date;
     java.sql.Date sqlDate;
     List<ExcelRow> excelrowlist = new ArrayList<ExcelRow>();
     List<Object> list = new ArrayList<Object>();
     boolean allblank = true;
     ExcelRow excelrow = null;
       excelrowlist = a.getRows();
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
           if (list.get(j).equals("NULL")) {
             ps.setNull(j+1, java.sql.Types.NULL);
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
         }
         if (!allblank) {
           ps.executeUpdate();
         } else {
           break;
         }
       }       
   }

  
//  public static void main(String args[]) throws IOException, SQLException, ParseException {
//    Connection con = DatabaseDriverHelper.connectOrCreateDataBase();
//    insertClientProfile(con);
//    insertLTClientExit(con);
//    insertNeedsAssessmentReferrals(con);
//    insertCommunityConnections(con);
//    insertInfoAndOrien(con);
//    insertEmploymentTemplateData(con);
//    insertLTClientEnroll(con);
//    insertLTCourseSetup(con);
//  }
  
  private static List<Integer> convertArrayToList(int[] array) {
    List<Integer> arrayList = new ArrayList<Integer>();
     for (int a: array) {
       arrayList.add(a);
     }
     return arrayList;
  }
}
