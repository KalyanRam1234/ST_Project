package com.outbreak.Doses;

import java.sql.*;
public class DosesDAO_JDBC implements DosesDAO{
    Connection dbConnection;

    public DosesDAO_JDBC(Connection dbconn){
        dbConnection=dbconn;
    }

    @Override
    public void enterDose(String studentId, int doseNo, String dateTaken, String vaccineName) {
        PreparedStatement preparedStatement = null;																																																																																																																																													
		String sql;
		sql = "insert into Doses(studentId, doseNo, dateTaken, vaccineName) values (?,?,?,?)";

		try {
			preparedStatement = dbConnection.prepareStatement(sql);
 
			preparedStatement.setString(1, studentId);
			preparedStatement.setInt(2, doseNo);
            preparedStatement.setString(3, dateTaken);
            preparedStatement.setString(4, vaccineName);
 
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
    public void deleteDose(String studentId, int doseNo) {
        PreparedStatement preparedStatement = null;																																																																																																																																													
		String sql;
		sql = "delete from dose where studentId=? and doseNo=?";

		try {
			preparedStatement = dbConnection.prepareStatement(sql);
			
			preparedStatement.setString(1, studentId);
            preparedStatement.setInt(2, doseNo);
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
    public Doses getDose(String studentId, int doseNo) {
        Doses d=null;
		String sql;
		Statement stmt = null;
		
		try{
			stmt = dbConnection.createStatement();
			sql = "select * from student where studentId = " + studentId + " doseNo = " + doseNo;
			ResultSet rs = stmt.executeQuery(sql);
																																																																																																																																																																																			
			//STEP 5: Extract data from result set
			while(rs.next()){
				//Retrieve by column name
				String studentId1  = rs.getString("studentId");
				int doseNo1 = rs.getInt("doseNo");
                String dateTaken  = rs.getString("dosesTaken");
                String vaccineName  = rs.getString("vaccineName");
				
				// Add exception handling here if more than one row is returned
				if(rs.next()){
					System.out.println("Fatal Fault in table definition: Found Duplicate key in table.");
				}
                d = new Doses(studentId1, doseNo1, dateTaken, vaccineName);
				break;
			}
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		// Add exception handling when there is no matching record
		if(d.getStudentId()==null && d.getdoseNo()==0){
			System.out.println("There is no matching entry in the student table.");
		}
		return d;
    }
}
