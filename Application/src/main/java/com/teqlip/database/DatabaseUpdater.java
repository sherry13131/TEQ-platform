package com.teqlip.database;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Class to handle all updates to the database for the B07 application.
 * @author Joe
 *
 */
public class DatabaseUpdater {
  
  /**
   * Update the role name of a given role in the role table.
   * @param name the new name of the role.
   * @param id the current ID of the role.
   * @param connection the database connection.
   * @return true if successful, false otherwise.
   */
  protected static boolean updateRoleName(String name, int id, Connection con) {
    String sql = "UPDATE roles SET role = ? WHERE roleID = ?;";
    try {
      PreparedStatement preparedStatement = con.prepareStatement(sql);
      preparedStatement.setString(1, name);
      preparedStatement.setInt(2, id);
      preparedStatement.executeUpdate();
      return true;
        
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  	public static boolean updateUserRole(int roleId, int userId, Connection con) {
  		String sql = "UPDATE users SET roleID = ? WHERE userID = ?;";
  		try {
  			PreparedStatement ps = con.prepareStatement(sql);
  			ps.setInt(1, roleId);
  			ps.setInt(2, userId);
  			ps.executeUpdate();
  			return true;
  		} catch (Exception e) {
  			e.printStackTrace();
  		}
  		return false;
  	}
  
}