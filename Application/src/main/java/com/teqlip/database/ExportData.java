package com.teqlip.database;

import java.sql.*;
import java.util.ArrayList;

public class ExportData {

	public static void main(String[] args) {
		//	path has to be somewhere granted permission to write/create, for now, create a new folder (eg C:\\exchange) under and store it there
		String path = "/Users/Sherry/Desktop/testing1.csv";
//		String path2 = "C:\\\\exchange\\\\testingHeader.csv";
		// only work when there is no group by / order by for now. have to improve
		String query = "SELECT userID, username, active FROM user_login";
		ArrayList<String> header = 	new ArrayList<String>() {{
		    add("userID");
		    add("username");
		    add("active");
		}}; 
		exportCSV(query, path);
//		exportCSVwHeader(header, query, path);
	}
	
	/**
	 * Export a csv file that contain all the data of the given query.
	 * @param query that to be executed
	 * @param path with the new file name that will be written as
	 */
	public static void exportCSV(String query, String path) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/assignmentdb", "root", "");
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery(query + " INTO OUTFILE '" + path + "' FIELDS TERMINATED BY ',' ENCLOSED BY '\"' LINES TERMINATED BY '\\n'");
			System.out.print("File has been created.");
		} catch(Exception e){
			System.out.print(e);
		}
	}
	
	/**
	 * Export a csv file that contain all the data of the given query with corresponding headers.
	 * @param columns that corresponding to the data retrieved
	 * @param query that to be executed
	 * @param path with the new file name that will be written as
	 */
	public static void exportCSVwHeader(ArrayList columns, String query, String path) {
		String header = "(SELECT ";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/assignmentdb", "root", "");
			Statement stmt=con.createStatement();
			// get header query part
			for (int i=0; i<columns.size() ;i++) {
				header += "'" + columns.get(i) + "'";
				if (i != columns.size()-1) {
					header += ", ";
				} else {
					header += ")";
				}
			}
			
			ResultSet rs=stmt.executeQuery(header + " UNION " + query + " INTO OUTFILE '" + path + "' FIELDS TERMINATED BY ',' ENCLOSED BY '\"' LINES TERMINATED BY '\\n'");
			System.out.print("File has been created.");
		} catch(Exception e){
			System.out.print(e);
		}		
	}
}
