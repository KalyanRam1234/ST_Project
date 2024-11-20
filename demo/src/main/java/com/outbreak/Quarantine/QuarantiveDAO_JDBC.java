package com.outbreak.Quarantine;

import java.sql.*;
import java.util.ArrayList;
public class QuarantiveDAO_JDBC implements QuarantineDAO {
    Connection dbConnection;

    public QuarantiveDAO_JDBC( Connection dbconn){ dbConnection=dbconn;}

    @Override
    public Quarantine getCaseDetails(String caseid) {
        Quarantine q=null;
        String sql;
		Statement stmt = null;
        
        try{
            stmt=dbConnection.createStatement();
            sql="select * from quarantine where caseId = " + caseid;
            ResultSet rs= stmt.executeQuery(sql);

            while(rs.next()){

                String caseId  = rs.getString("caseId");
                String startDate = rs.getString("startDate");
                String endDate= rs.getString("endDate");
                String healthStatus = rs.getString("healthStatus");

                q = new Quarantine(caseId, startDate, healthStatus, "undefined", endDate);
                break;
            }
        }
        catch ( SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
        }

        return q;
    }

    @Override
    public ArrayList<Quarantine> getQuarantineByStartDate(String startDate) {
        ArrayList<Quarantine> qList = new ArrayList<Quarantine>();
        String sql;
		Statement stmt = null;
        
        try{
            stmt=dbConnection.createStatement();
            sql="select * from quarantine where startDate = " + startDate;
            ResultSet rs= stmt.executeQuery(sql);

            while(rs.next()){

                String caseId  = rs.getString("caseId");
                String startDate1 = rs.getString("startDate");
                String endDate= rs.getString("endDate");
                String healthStatus = rs.getString("healthStatus");

                Quarantine q = new Quarantine(caseId, startDate1, healthStatus, "undefined", endDate);
                qList.add(q);
            }
        }
        catch ( SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
        }

        return qList;
    }

    @Override
    public ArrayList<Quarantine> getQuarantineByEndDate(String endDate) {
        ArrayList<Quarantine> qList = new ArrayList<Quarantine>();
        String sql;
		Statement stmt = null;
        
        try{
            stmt=dbConnection.createStatement();
            sql="select * from quarantine where endDate = " + endDate;
            ResultSet rs= stmt.executeQuery(sql);

            while(rs.next()){

                String caseId  = rs.getString("caseId");
                String startDate1 = rs.getString("startDate");
                String endDate1 = rs.getString("endDate");
                String healthStatus = rs.getString("healthStatus");

                Quarantine q = new Quarantine(caseId, startDate1, healthStatus, "undefined", endDate1);
                qList.add(q);
            }
        }
        catch ( SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
        }

        return qList;
    }

    @Override
    public void enterQuarantineCase(String caseId, String startDate, String healthStatus, String duration, String endDate)
    {
        PreparedStatement preparedStatement = null;																																																																																																																																													
		String sql;
		sql = "insert into quarantine(caseId, startDate, endDate, healthStatus) values (?,?,?,?)";

		try {
			preparedStatement = dbConnection.prepareStatement(sql);
 
			preparedStatement.setString(1, caseId);
			preparedStatement.setString(2, startDate);
            preparedStatement.setString(3, healthStatus);
            preparedStatement.setString(4, endDate);

 
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

    public void removeQuarantineCase(String caseId)
    {
        PreparedStatement preparedStatement = null;																																																																																																																																													
		String sql;
		sql = "delete from quarantine where caseId=?";

		try {
			preparedStatement = dbConnection.prepareStatement(sql);
			
			preparedStatement.setString(1, caseId);
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
    public void updateQuarantineHealthStatus(String caseId, String status) {
        PreparedStatement preparedStatement = null;																																																																																																																																													
        String sql;
        sql = "UPDATE vaccination SET healthStatus=? WHERE caseId=?";
  
        try {
          preparedStatement = dbConnection.prepareStatement(sql);
        
          preparedStatement.setString(1, status);
          preparedStatement.setString(2, caseId);
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
    public void updateQuarantineEndDate(String caseId, String endDate) {
        PreparedStatement preparedStatement = null;																																																																																																																																													
        String sql;
        sql = "UPDATE vaccination SET endDate=? WHERE caseId=?";
  
        try {
          preparedStatement = dbConnection.prepareStatement(sql);
        
          preparedStatement.setString(1, endDate);
          preparedStatement.setString(2, caseId);
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
