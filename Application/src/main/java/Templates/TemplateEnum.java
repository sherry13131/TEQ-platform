package Templates;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import com.teqlip.database.DatabaseTemplateDataInserter;
import com.teqlip.excel.ExcelSheet;

public enum TemplateEnum {
	Client_Profile{
		public String getString() {
	        return "Client Profile";
	    }

    @Override
    public void insertThisSheet(String filename, ExcelSheet excelSheet, Connection con) throws SQLException, ParseException {
      DatabaseTemplateDataInserter.insertClientProfile(filename, excelSheet, con);
    }

    @Override
    public String getTableName() {
      return "client profile";
    }
		
	},
	Community_Connections{
		public String getString() {
	        return "Community Connections";
	    }

    @Override
    public void insertThisSheet(String filename, ExcelSheet excelSheet, Connection con)
        throws SQLException, ParseException {
      DatabaseTemplateDataInserter.insertCommunityConnections(filename, excelSheet, con);
    }

    @Override
    public String getTableName() {
      return "community connections";
    }
	},
	Employment{
		public String getString() {
	        return "Employment";
	    }

    @Override
    public void insertThisSheet(String filename, ExcelSheet excelSheet, Connection con)
        throws SQLException, ParseException {
      DatabaseTemplateDataInserter.insertEmploymentTemplateData(filename, excelSheet, con);
    }

    @Override
    public String getTableName() {
      return "employment";
    }
	},
	Info_And_Orien{
		public String getString() {
	        return "Info&Orien";
	    }

    @Override
    public void insertThisSheet(String filename, ExcelSheet excelSheet, Connection con)
        throws SQLException, ParseException {
      DatabaseTemplateDataInserter.insertInfoAndOrien(filename, excelSheet, con);
    }

    @Override
    public String getTableName() {
      return "info&orien";
    }
	},
	LT_Client_Enrol{
		public String getString() {
	        return "LT Client Enrol";
	    }

    @Override
    public void insertThisSheet(String filename, ExcelSheet excelSheet, Connection con)
        throws SQLException, ParseException {
      DatabaseTemplateDataInserter.insertLTClientEnroll(filename, excelSheet, con);
    }

    @Override
    public String getTableName() {
      return "lt client enrol";
    }
	},
	LT_Client_Exit{
		public String getString() {
	        return "LT Client Exit";
	    }

    @Override
    public void insertThisSheet(String filename, ExcelSheet excelSheet, Connection con)
        throws SQLException, ParseException {
      DatabaseTemplateDataInserter.insertLTClientExit(filename, excelSheet, con);
    }

    @Override
    public String getTableName() {
      return "lt client exit";
    }
	},
	LT_Course_Setup{
		public String getString() {
	        return "LT Course Setup";
	    }

    @Override
    public void insertThisSheet(String filename, ExcelSheet excelSheet, Connection con)
        throws SQLException, ParseException {
      DatabaseTemplateDataInserter.insertLTCourseSetup(filename, excelSheet, con);
    }

    @Override
    public String getTableName() {
      return "lt course setup";
    }
	},
	Needs_Assessment_And_Referrals{
		public String getString() {
	        return "Needs Assessment&Referrals";
	    }

    @Override
    public void insertThisSheet(String filename, ExcelSheet excelSheet, Connection con)
        throws SQLException, ParseException {
      DatabaseTemplateDataInserter.insertNeedsAssessmentReferrals(filename, excelSheet, con);
    }

    @Override
    public String getTableName() {
      return "needs assessment&referrals";
    }
	};

    public static ArrayList<TemplateEnum> enumIteration() {  
      TemplateEnum[] templateEnums = TemplateEnum.values(); 
      ArrayList<TemplateEnum> alltempEnum = new ArrayList<TemplateEnum>();
      for (TemplateEnum e : templateEnums) {
        alltempEnum.add(e);  
      }
      return alltempEnum;  
    }
	
	public abstract String getString();
	public abstract void insertThisSheet(String filename, ExcelSheet excelSheet,Connection con) throws SQLException, ParseException;
	public abstract String getTableName();

}
