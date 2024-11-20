package com.outbreak.Case;

import java.sql.*;
public class CaseDAO_JDBC implements CaseDAO{
    Connection dbConnection;

    public CaseDAO_JDBC(Connection dbconn){
        dbConnection=dbconn;
    }

    @Override
    public void enterNewCase(String caseId, String studentId, String qroomNo, String testId, String diagnosisDate) {
        PreparedStatement preparedStatement = null;																																																																																																																																													
		String sql;
		sql = "insert into posCase(caseId, studentId, qroomNo, testId, diagnosisDate) values (?,?,?,?,?)";

		try {
			preparedStatement = dbConnection.prepareStatement(sql);
 
			preparedStatement.setString(1, caseId);
			preparedStatement.setString(2, studentId);
            preparedStatement.setString(3, qroomNo);
            preparedStatement.setString(4, testId);
            preparedStatement.setString(5, diagnosisDate);
 
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
	public int getTotalCases(){
		Statement stmt = null;
		String sql="select max(caseId) as max from posCase";
		try{
			stmt=dbConnection.createStatement();
			ResultSet rs= stmt.executeQuery(sql);
            rs.next();
            String count=rs.getString("max");
			String num=count.substring(4);
			return Integer.parseInt(num);
		}
		catch ( SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
        }
		return 0;
	}

    @Override
    public void deleteCase(String caseId, String studentId) {
        PreparedStatement preparedStatement = null;																																																																																																																																													
		String sql;
        
        if(!caseId.equals(null))
		sql = "delete from posCase where caseId=?";
        else if(!studentId.equals(null))
        sql = "delete from posCase where studentId=?";
        else return;

		try {
			preparedStatement = dbConnection.prepareStatement(sql);
		
            if(!caseId.equals(null))
                preparedStatement.setString(1, caseId);
            else if(!studentId.equals(null))
                preparedStatement.setString(1, studentId);
			
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
    public Case getCaseByCaseId(String caseId) {
        Case c = null;
		String sql;
		Statement stmt = null;
		
		try{
			stmt = dbConnection.createStatement();
			sql = "select * from posCase where caseId = " + caseId;
			ResultSet rs = stmt.executeQuery(sql);
																																																																																																																																																																																			
			//STEP 5: Extract data from result set
			while(rs.next()){
				//Retrieve by column name
				String caseId1  = rs.getString("caseId");
				String studentId = rs.getString("studentId");
                String qroomNo  = rs.getString("qroomNo");
                String testId  = rs.getString("testId");
				String diagnosisDate = rs.getString("diagnosisDate");
				// Add exception handling here if more than one row is returned
				if(rs.next()){
					System.out.println("Fatal Fault in table definition: Found Duplicate key in table.");
				}
                c = new Case(caseId1, studentId, qroomNo, testId, diagnosisDate);
				break;
			}
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		// Add exception handling when there is no matching record
		if(c.getStudentId()==null && c.getCaseId()==null){
			System.out.println("There is no matching entry in the student table.");
		}
	
        return c;        
    }

    @Override
    public Case getCaseByStudentId(String studentId) {
        Case c = null;
		String sql;
		Statement stmt = null;
		
		try{
			stmt = dbConnection.createStatement();
			sql = "select * from posCase where studentId = " + studentId;
			ResultSet rs = stmt.executeQuery(sql);
																																																																																																																																																																																			
			//STEP 5: Extract data from result set
			while(rs.next()){
				//Retrieve by column name
				String caseId1  = rs.getString("caseId");
				String studentId1 = rs.getString("studentId");
                String qroomNo  = rs.getString("qroomNo");
                String testId  = rs.getString("testId");
				String diagnosisDate = rs.getString("diagnosisDate");
				// Add exception handling here if more than one row is returned
				if(rs.next()){
					System.out.println("Fatal Fault in table definition: Found Duplicate key in table.");
				}
                c = new Case(caseId1, studentId1, qroomNo, testId, diagnosisDate);
				break;
			}
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		// Add exception handling when there is no matching record
		if(c.getStudentId()==null && c.getCaseId()==null){
			System.out.println("There is no matching entry in the student table.");
		}
	
        return c;        
    }
}
