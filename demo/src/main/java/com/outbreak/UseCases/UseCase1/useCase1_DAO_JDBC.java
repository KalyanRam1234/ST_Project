package com.outbreak.UseCases.UseCase1;

import java.sql.*;
import java.util.ArrayList;

public class useCase1_DAO_JDBC implements useCase1_DAO{
    Connection dbConnection;

    public useCase1_DAO_JDBC(Connection dbconn){

		dbConnection = dbconn;
	}

    public useCase1 getInfo(ResultSet rs){
        useCase1 s=new useCase1();
        try{
            String rollno  = rs.getString("studentId");
            String name = rs.getString("Full_Name");
            String email= rs.getString("emailId");
            String gender=rs.getString("gender");
            String roomNo=rs.getString("roomNo");
            String testId=rs.getString("testId");
            String date=rs.getString("testDate");
            int result=rs.getInt("test_result");
            if(result==0)
            
            s=new useCase1(rollno,name,roomNo,gender,date,testId,"Negative" ,email);

            else s=new useCase1(rollno,name,roomNo,gender,date,testId,"Positive" ,email);
        }
        catch ( SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
        }
        return s;
    }

    @Override
    public useCase1 getStudentRTPCR_Status(String rollNo, String Date){
        useCase1 s=new useCase1();
        String sql;
		Statement stmt = null;

        try{
            stmt=dbConnection.createStatement();
            sql="select s.studentId, concat(fname,' ',lname) as " + "Full_Name" + ", s.gender, s.roomNo, s.emailId, r.testId,r.testDate, r.test_result from student as s join rtpcr as r where s.studentId='" + rollNo+ "' and s.studentId=r.studentId and r.testDate='"+ Date +"'";
            ResultSet rs= stmt.executeQuery(sql);
            while(rs.next()){
                s=getInfo(rs);
                break;
            }
        }
        catch ( SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
        }
        return s;
    }

    @Override
    public ArrayList<useCase1> getStudentRTPCR_Status(String rollNo){
        ArrayList<useCase1> list=new ArrayList<useCase1>();
        String sql;
		Statement stmt = null;

        try{
            stmt=dbConnection.createStatement();
            sql="select s.studentId, concat(fname,' ',lname) as " + "Full_Name" + ", s.gender, s.roomNo, s.emailId, r.testId,r.testDate, r.test_result from student as s join rtpcr as r where s.studentId='" + rollNo+ "' and s.studentId=r.studentId order by r.testDate";// and r.testDate='"//+ Date +"'";
            ResultSet rs= stmt.executeQuery(sql);
				//Retrieve by column name
            while(rs.next()){
                useCase1 s =new useCase1();
                s=getInfo(rs);
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

    @Override
    public ArrayList<useCase1> getBatchRTPCR_Status(String batch){
        ArrayList<useCase1> list=new ArrayList<useCase1>(0);
        String sql;
		Statement stmt = null;

        try{
            stmt=dbConnection.createStatement();
            sql="select s.studentId, concat(fname,' ',lname) as " + "Full_Name" + ", s.gender, s.roomNo, s.emailId, r.testId,r.testDate, r.test_result from student as s join rtpcr as r where s.studentId like '%" + batch+ "%' and s.studentId=r.studentId order by r.testDate";// and r.testDate='"+ Date +"'" ;

            ResultSet rs= stmt.executeQuery(sql);
            
            while(rs.next()){
                useCase1 s=getInfo(rs);
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

    @Override
    public ArrayList<useCase1> getCollegeRTPCR_Status(String Date){
        ArrayList<useCase1> list=new ArrayList<useCase1>(0);
        String sql;
		Statement stmt = null;

        try{
            stmt=dbConnection.createStatement();
            sql="select s.studentId, concat(fname,' ',lname) as " + "Full_Name" + ", s.gender, s.roomNo, s.emailId, r.testId,r.testDate, r.test_result from student as s join rtpcr as r where s.studentId=r.studentId and r.testDate='"+ Date +"'" ;

            ResultSet rs= stmt.executeQuery(sql);
            
            while(rs.next()){
                useCase1 s=getInfo(rs);
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

    
}