package com.outbreak.Vaccination;

import java.sql.*;

public class VaccinationDAO_JDBC implements VaccinationDAO {
    Connection dbConnection;

    public VaccinationDAO_JDBC(Connection dbconn){

		dbConnection = dbconn;
	}

    @Override
    public void enterVaccinationStatus(String id, int doses, String status, String certif) {
      PreparedStatement preparedStatement = null;																																																																																																																																													
		  String sql;
		  sql = "insert into vaccination(studentId, doses, status, certif) values (?,?,?,?)";

		  try {
			      preparedStatement = dbConnection.prepareStatement(sql);
 
  			    preparedStatement.setString(1, id);
	  	  	  preparedStatement.setInt(2, doses);
            preparedStatement.setString(3, status);
            preparedStatement.setString(4, certif);
 
			      // execute insert SQL stetement
			      preparedStatement.executeUpdate();

    		} catch (SQLException e) {
 		      	System.out.println(e.getMessage());
 		    }

		  try{
			      if (preparedStatement != null) {
				        preparedStatement.close();
		    	  }
		  } catch (SQLException e) {
 			      System.out.println(e.getMessage());
 		  }
    }

    @Override
    public void deleteVaccinationStatusOfStudentId(String studentId) {
        PreparedStatement preparedStatement = null;																																																																																																																																													
        String sql;
        
        if(!studentId.equals(null))
        sql = "delete from rtpcr where studentId=?";
        else return ;
  
        try {
            preparedStatement = dbConnection.prepareStatement(sql);
      
            if(!studentId.equals(null))
              preparedStatement.setString(1, studentId);
            else return;
        
            // execute update SQL stetement
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
  
        try{
            if (preparedStatement != null) {
              preparedStatement.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Vaccination getVaccinationStatusByStudentId(String studentId) {
        Vaccination v = null; 
        String sql;
		    Statement stmt = null;
        
        try{
            stmt=dbConnection.createStatement();
            sql="select * from vaccination where studentId = " + studentId;
            ResultSet rs= stmt.executeQuery(sql);

            while(rs.next()){

                String studentId1  = rs.getString("studentId");
                String doses = rs.getString("dosesTaken");
                String status = rs.getString("vaccinationStatus");
                String certificate= rs.getString("certif");

                v = new Vaccination(studentId1, Integer.parseInt(doses), status, certificate);
                break;
            }

        }
        catch ( SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
		        System.out.println("SQLState: " + ex.getSQLState());
		        System.out.println("VendorError: " + ex.getErrorCode());
        }

        return v;
    }

    @Override
    public void updateVaccinationStatusOfStudentId(String studentId, String status) {
        PreparedStatement preparedStatement = null;																																																																																																																																													
        String sql;
        sql = "UPDATE vaccination SET vaccinationStatus=? WHERE studentId=?";
  
        try {
          preparedStatement = dbConnection.prepareStatement(sql);
        
          preparedStatement.setString(1, studentId);
          preparedStatement.setString(2, status);
          // execute update SQL stetement
          preparedStatement.executeUpdate();
        } catch (SQLException e) {
          System.out.println(e.getMessage());
        }
  
        try{
          if (preparedStatement != null) {
            preparedStatement.close();
          }
        } catch (SQLException e) {
          System.out.println(e.getMessage());
        } 
    }

    @Override
    public void updateVaccinationDosesOfStudentId(String studentId, int doses) {
        PreparedStatement preparedStatement = null;																																																																																																																																													
        String sql;
        sql = "UPDATE vaccination SET doses=? WHERE studentId=?";

        try {
          preparedStatement = dbConnection.prepareStatement(sql);
      
          preparedStatement.setString(2, studentId);
          preparedStatement.setInt( 1, doses);
          // execute update SQL stetement
          preparedStatement.executeUpdate();
        } catch (SQLException e) {
          System.out.println(e.getMessage());
        }

        try{
          if (preparedStatement != null) {
            preparedStatement.close();
          }
        } catch (SQLException e) {
          System.out.println(e.getMessage());
        } 
    }
}
