package com.outbreak.UseCases.useCase5;

import java.sql.*;
import java.util.ArrayList;

public class useCase5_DAO_JDBC implements useCase5_DAO{
    Connection dbConnection;

    public useCase5_DAO_JDBC(Connection dbconn){

		dbConnection = dbconn;
	}

    public useCase5 getInfo(ResultSet rs){
        useCase5 s=new useCase5();
        try{
            String id  = rs.getString("studentId");
            String name = rs.getString("Full_Name");
            String date = rs.getString("diagnosis_date");
            String testId=rs.getString("testId");
            String qroomNo=rs.getString("qroomNo");
            String healthStatus=rs.getString("healthStatus");
            
            s=new useCase5(id, name, date, testId, qroomNo, healthStatus);
        }
        catch ( SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
        }
        return s;
    }

    @Override
    public ArrayList<useCase5> getInfectedList() {
        ArrayList<useCase5> list=new ArrayList<useCase5>(0);
        String sql;
		Statement stmt = null;

        try{
            stmt=dbConnection.createStatement();
            sql="select s.studentId as studentId, concat(fname,' ',lname) as " + "Full_Name" + ", p.diagnosisDate as diagnosis_date, p.testId as testId, p.qroomNo as qroomNo, q.healthStatus as healthStatus from student as s join posCase as p on s.studentId=p.studentId left join quarantine as q on p.caseId=q.caseId";

            ResultSet rs= stmt.executeQuery(sql);
            
            while(rs.next()){
                useCase5 s=getInfo(rs);
                list.add(s);
            }

        }
        catch ( SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
        }
        return list;
    }

    public ArrayList<useCase5> getInfectedListByBatch(String batch) {
        ArrayList<useCase5> list=new ArrayList<useCase5>(0);
        String sql;
		Statement stmt = null;

        try{
            stmt=dbConnection.createStatement();
            sql="select s.studentId as studentId, concat(fname,' ',lname) as " + "Full_Name" + ", p.diagnosisDate as diagnosis_date, p.testId as testId, p.qroomNo as qroomNo, q.healthStatus as healthStatus from student as s join posCase as p on s.studentId=p.studentId left join quarantine as q on p.caseId=q.caseId where s.studentId like \"" + batch + "%\"";

            ResultSet rs= stmt.executeQuery(sql);
            
            while(rs.next()){
                useCase5 s=getInfo(rs);
                list.add(s);
            }

        }
        catch ( SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
        }
        return list;
    }

    public void enterInfectedDetails(String studentId, String caseId, String name, String date, String testId, String qroomNo, String sdate, String edate, String healthStatus)
    {
        PreparedStatement preparedStatement = null;																																	
		String sql;
		sql = "insert into posCase(caseId, studentId, qroomNo, testId, diagnosisDate) values (?,?,?,?,?)";

		try {
			preparedStatement = dbConnection.prepareStatement(sql);
 
			preparedStatement.setString(1, studentId);
			preparedStatement.setString(2, caseId);
            preparedStatement.setString(3, date);
            preparedStatement.setString(4, testId);
            preparedStatement.setString(5, qroomNo);
 
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

         sql = "insert into quarantine(caseId, startDate, endDate, healthStatus) values (?,?,?,?)";
 
         try {
             preparedStatement = dbConnection.prepareStatement(sql);
  
             preparedStatement.setString(1, caseId);
             preparedStatement.setString(2, sdate);
             preparedStatement.setString(3, edate);
             preparedStatement.setString(4, healthStatus);
  
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
}