package com.outbreak.UseCases.useCase3;

import java.sql.*;
import java.util.ArrayList;

public class useCase3_DAO_JDBC implements useCase3_DAO {

    Connection dbConnection;

    public useCase3_DAO_JDBC(Connection dbconn){

		dbConnection = dbconn;
	}

    public useCase3 getInfo(ResultSet rs){
        useCase3 s=new useCase3();
        try{
            String studentId = rs.getString("studentId");
            int doseNo = rs.getInt("doseNo");           
            String dateTaken = rs.getString("dateTaken");
            String vaccineName = rs.getString("vaccineName");
            String vaccinationStatus = rs.getString("vaccinationStatus");
            s=new useCase3(studentId, doseNo, dateTaken, vaccineName, vaccinationStatus);
        }
        catch ( SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
        }
        return s;
    }

    @Override
    public void enterIntoDosesTable(useCase3 uc3) {
        PreparedStatement preparedStatement = null;																				
		String sql;
		sql = "insert into dose(studentId, doseNo, dateTaken, vaccineName) values (?,?,?,?)";

		try {
			preparedStatement = dbConnection.prepareStatement(sql);
 
			preparedStatement.setString(1, uc3.getStudentId());
            preparedStatement.setInt(2, uc3.getDoseNo());
			preparedStatement.setString(3, uc3.getDate());
            preparedStatement.setString(4, uc3.getvName());
 
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

        preparedStatement = null;																										
        sql = "UPDATE vaccination SET dosesTaken=? WHERE studentId=?";
 
        try {
            preparedStatement = dbConnection.prepareStatement(sql);

            preparedStatement.setInt(1, uc3.getDoseNo());
            preparedStatement.setString(2, uc3.getStudentId());
            
            // execute update SQL stetement
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
             System.out.println(e.getMessage());
        }
        
        try{
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        } 
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Data entered successfully.\n");
    }

    @Override
    public void updateVaccinationStatus(useCase3 uc3) {
        PreparedStatement preparedStatement = null;																																															
		String sql;
		sql = "UPDATE vaccination SET vaccinationStatus=? WHERE studentId=?";

		try {
			preparedStatement = dbConnection.prepareStatement(sql);
			
            preparedStatement.setString(1, uc3.getvStatus());
			preparedStatement.setString(2, uc3.getStudentId());
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

         System.out.println("Vaccination status successfully.\n");
    }

    @Override
    public int getCurrentDoseNo(String studentId) {
		int doseNo=-2;
        String sql;
		Statement stmt = null;
		
		try{
			stmt = dbConnection.createStatement();
			sql = "select dosesTaken from vaccination where studentId = \"" + studentId + "\"";
			ResultSet rs = stmt.executeQuery(sql);
					
			//STEP 5: Extract data from result set
			while(rs.next()){
				//Retrieve by column name
				doseNo = rs.getInt("dosesTaken");
				// Add exception handling here if more than one row is returned
				if(rs.next()){
					System.out.println("Fatal Fault in table definition: Found Duplicate key in table. key value is "+studentId);
				}
				break;
			}
		} catch (SQLException ex) {
			System.out.println("hi");
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		// Add exception handling when there is no matching record
        return (doseNo+1);
    }
       
}
