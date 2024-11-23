package com.outbreak.RTPCR;

import java.sql.*;
import java.util.ArrayList;
import com.outbreak.Case.*;
public class RTPCRDAO_JDBC implements RTPCRDAO{
    Connection dbConnection;
    CaseDAO dao;

    public RTPCRDAO_JDBC(Connection dbconn){
        dbConnection=dbconn;
        dao=new CaseDAO_JDBC(dbConnection);
    }

    public void setCaseDao(CaseDAO dao){
        this.dao=dao;
    }

    @Override
    public ArrayList<RTPCR> getRTPCRByStudentId(String id){
        
        ArrayList<RTPCR> rtpcr_list = new ArrayList<RTPCR>();
        String sql;
		Statement stmt = null;
        
        try{
            stmt=dbConnection.createStatement();
            sql="select * from rtpcr where studentId = " + id;
            ResultSet rs= stmt.executeQuery(sql);

            while(rs.next()){

                String studentId  = rs.getString("studentId");
                String testDate = rs.getString("testDate");
                String test_result = (rs.getString("test_result").equals("1"))?"Positive":"Negative";
                String certificate= rs.getString("certif");
                String testId=rs.getString("testId");

                RTPCR rtpcr = new RTPCR(testId, studentId, testDate, test_result, certificate);
                rtpcr_list.add(rtpcr);
            }

        }
        catch ( SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
        }


        return rtpcr_list;
    }
    
    @Override
    public RTPCR getRTPCRByStudentId_Date(String id, String tDate){
        
        RTPCR rtpcr = null; 
        String sql;
		Statement stmt = null;
        
        try{
            stmt=dbConnection.createStatement();
            sql="select * from rtpcr where studentId = " + id + " AND testDate = " + tDate;
            ResultSet rs= stmt.executeQuery(sql);

            while(rs.next()){

                String studentId  = rs.getString("studentId");
                String testDate = rs.getString("testDate");
                String test_result = (rs.getString("test_result").equals("1"))?"Positive":"Negative";
                String certificate= rs.getString("certif");
                String testId=rs.getString("testId");

                rtpcr = new RTPCR(testId, studentId, testDate, test_result, certificate);
                break;
            }

        }
        catch ( SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
        }


        return rtpcr;
    }
    
    @Override
    public ArrayList<RTPCR> getRTPCR_postive(){
        
        ArrayList<RTPCR> rtpcr_list = new ArrayList<RTPCR>();
        String sql;
		Statement stmt = null;
        
        try{
            stmt=dbConnection.createStatement();
            sql="select * from rtpcr where test_result=1";
            ResultSet rs= stmt.executeQuery(sql);

            while(rs.next()){

                String studentId  = rs.getString("studentId");
                String testDate = rs.getString("testDate");
                String test_result = (rs.getString("test_result").equals("1"))?"Positive":"Negative";
                String certificate= rs.getString("certif");
                String testId=rs.getString("testId");

                RTPCR rtpcr = new RTPCR(testId, studentId, testDate, test_result, certificate);
                rtpcr_list.add(rtpcr);
            }

        }
        catch ( SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
        }

        return rtpcr_list;
    }

    @Override
    public ArrayList<RTPCR> getRTPCR_negative(){

        ArrayList<RTPCR> rtpcr_list = new ArrayList<RTPCR>();
        String sql;
		Statement stmt = null;
        
        try{
            stmt=dbConnection.createStatement();
            sql="select * from rtpcr where test_result=0";
            ResultSet rs= stmt.executeQuery(sql);

            while(rs.next()){

                String studentId  = rs.getString("studentId");
                String testDate = rs.getString("testDate");
                String test_result = (rs.getString("test_result").equals("1"))?"Positive":"Negative";
                String certificate= rs.getString("certif");
                String testId=rs.getString("testId");

                RTPCR rtpcr = new RTPCR(testId, studentId, testDate, test_result, certificate);
                rtpcr_list.add(rtpcr);
            }

        }
        catch ( SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
        }

        return rtpcr_list;
    }

    @Override
    public RTPCR getRTPCRByStudentId_LastDate(String id){
        RTPCR rtpcr = null; 
        String sql;
		Statement stmt = null;
        
        try{
            stmt=dbConnection.createStatement();
            sql="select * from rtpcr where studentId = " + id + " AND testDate = (select max(testDate) from rtpcr where studentId= " + id + ")";
            ResultSet rs= stmt.executeQuery(sql);

            while(rs.next()){

                String studentId  = rs.getString("studentId");
                String testDate = rs.getString("testDate");
                String test_result = (rs.getString("test_result").equals("1"))?"Positive":"Negative";
                String certificate= rs.getString("certif");
                String testId=rs.getString("testId");

                rtpcr = new RTPCR(testId, studentId, testDate, test_result, certificate);
                break;
            }

        }
        catch ( SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
        }

        return rtpcr;
    }

    @Override
    public int enterRTPCR(RTPCR test)
    {
        PreparedStatement preparedStatement = null;													
        Statement stmt = null;																		
		String sql,retrieve, insert, check, delete;
		sql = "insert into rtpcr(testId, studentId, testDate, test_result, certif) values (?,?,?,?,?)";

        retrieve="select count(*) as count from rtpcr";

        check="select studentId from posCase where studentId='"+ test.getstudentId() +"'";

        insert="insert into posCase(caseId,studentId,qroomNo,testId,diagnosisDate) values(?,?,?,?,?)";
		try {
            stmt=dbConnection.createStatement();
            ResultSet rs= stmt.executeQuery(retrieve);
            rs.next();
            int count=rs.getInt("count")+1;
            String testid="RT"+count;
            rs=stmt.executeQuery(check);
            rs.last();
            int val=rs.getRow();

            rs=stmt.executeQuery("select studentId from student where studentId='"+test.getstudentId()+"'");
            rs.last();
            if(rs.getRow()==0){
                System.out.println("Invalid student ID");
                return 0;
            }
			preparedStatement = dbConnection.prepareStatement(sql);
 
			preparedStatement.setString(1, testid);
			preparedStatement.setString(2, test.getstudentId());
            preparedStatement.setString(3, test.gettestDate());
            if(test.gettest_Result().equals("Positive")) preparedStatement.setInt(4, 1);
            else if(test.gettest_Result().equals("Negative")) preparedStatement.setInt(4, 0);
            preparedStatement.setString(5, "NULL");
 
			// execute insert SQL stetement
			preparedStatement.executeUpdate();

            if(test.gettest_Result().equals("Positive")){
                int caseid=dao.getTotalCases()+1;
                String id="case"+caseid;

                if(val==1){
                    delete="delete from posCase where studentId='"+test.getstudentId()+"'";
                    preparedStatement=dbConnection.prepareStatement(delete);
                    preparedStatement.executeUpdate();
                }

                preparedStatement = dbConnection.prepareStatement(insert);
                preparedStatement.setString(1, id);
                preparedStatement.setString(2, test.getstudentId());
                preparedStatement.setString(3,null);
                preparedStatement.setString(4, testid);

                preparedStatement.setString(5, test.gettestDate());
                preparedStatement.executeUpdate();
            }
            System.out.println("Data was entered successfully.\n");
            return 0;

		} catch (SQLException e) {
 			System.out.println(e.getMessage());
 		}

		try{
			preparedStatement.close();
		} catch (SQLException e) {
 			System.out.println(e.getMessage());
 		}
        return 1;
    }

    @Override
    public void deleteRTPCR(String testId, String studentId) {
        PreparedStatement preparedStatement = null;																																																																																																																																													
		String sql;
        
        if (testId != null) {
            sql = "delete from rtpcr where testId=?";
        } else if (studentId != null) {
            sql = "delete from rtpcr where studentId=?";
        } else {
            return;
        }
        

		try {
			preparedStatement = dbConnection.prepareStatement(sql);
		
            if(testId!=null)
                preparedStatement.setString(1, testId);
            else if(studentId!=null)
                preparedStatement.setString(1, studentId);
			
			// execute update SQL stetement
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
 			System.out.println(e.getMessage());
 		}

		try{
			preparedStatement.close();
			
		} catch (SQLException e) {
 			System.out.println(e.getMessage());
 		}
    }

    
}
