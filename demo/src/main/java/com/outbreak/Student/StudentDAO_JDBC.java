package com.outbreak.Student;

import java.sql.*;
import java.util.ArrayList;
public class StudentDAO_JDBC implements StudentDAO{
    Connection dbConnection;

    public StudentDAO_JDBC(Connection dbconn){

		dbConnection = dbconn;
	}

    @Override
    public Student getStudentByKey(String rollNo){
        Student s=new Student();
        String sql;
		Statement stmt = null;
        
        try{
            stmt=dbConnection.createStatement();
            sql="select * from student where studentId = '" + rollNo +"'";
            ResultSet rs= stmt.executeQuery(sql);

				//Retrieve by column name
                while(rs.next()){
                    String rollno  = rs.getString("studentId");
                    String fname = rs.getString("fname");
                    String lname = rs.getString("lname");
                    String email= rs.getString("emailId");
                    String gender=rs.getString("gender");
                    String branch=rs.getString("branch");
                    String roomNo=rs.getString("roomNo");
                    String date=rs.getString("dateOfBirth");

                    s=new Student(rollno,fname,lname,date,email,gender,branch,roomNo);
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
    public ArrayList<Student> getStudentsByBatch(String batch){
        ArrayList<Student> list=new ArrayList<Student>(0);
        String sql;
		Statement stmt = null;

        try{
            stmt=dbConnection.createStatement();
            sql="select * from student where studentId like '%" + batch + "%'" ;
            ResultSet rs= stmt.executeQuery(sql);
            
            while(rs.next()){
                String rollno  = rs.getString("studentId");
                String fname = rs.getString("fname");
                String lname = rs.getString("lname");
                String email= rs.getString("emailId");
                String gender=rs.getString("gender");
                String branch=rs.getString("branch");
                String roomNo=rs.getString("roomNo");

                String date=rs.getString("dateOfBirth");

                Student s=new Student(rollno,fname,lname,date,email,gender,branch,roomNo);

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
    public int getStudentCount(){
        String sql;
		Statement stmt = null;
        int count=0;
        try{
            stmt=dbConnection.createStatement();
            sql="select count(*) as count from student" ;
            ResultSet rs= stmt.executeQuery(sql);
            rs.next();
            count=rs.getInt("count");
        }
        catch ( SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
        }
        return count;
    }

    @Override
    public int getStudentCountByBatch(String batch){
        String sql;
		Statement stmt = null;
        int count=0;
        try{
            stmt=dbConnection.createStatement();
            sql="select count(*) as count from student where studentId like '%" + batch + "%'" ;
            ResultSet rs= stmt.executeQuery(sql);
            rs.next();
            count=rs.getInt("count");
        }
        catch ( SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
        }
        return count;
    }

    @Override
    public ArrayList<Student> getStudentByRoomNo(String roomno) {
        ArrayList<Student> slist=new ArrayList<Student>();
        String sql;
		Statement stmt = null;
        
        try{
            stmt=dbConnection.createStatement();
            sql="select * from student where roomNo = " + roomno;
            ResultSet rs= stmt.executeQuery(sql);

				//Retrieve by column name
                while(rs.next()){
                    String rollno  = rs.getString("studentId");
                    String fname = rs.getString("fname");
                    String lname = rs.getString("lname");
                    String email= rs.getString("emailId");
                    String gender=rs.getString("gender");
                    String branch=rs.getString("branch");
                    String roomNo=rs.getString("roomNo");
                    String date=rs.getString("dateOfBirth");

                    Student s=new Student(rollno,fname,lname,date,email,gender,branch,roomNo);
                    slist.add(s);
                }

        }
        catch ( SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
        }
        return slist;
        
    }

    @Override
    public void enterStudent(String id, String fname, String lname, String date, String email_id, String gender,
            String branch, String roomno) {
            
            PreparedStatement preparedStatement = null;																																																																																																																																													
            String sql;
            sql = "insert into Doses(studentId, fname, lname, dateOfBirth, emailId, gender, branch, roomNo) values (?,?,?,?,?,?,?,?)";
        
            try {
                preparedStatement = dbConnection.prepareStatement(sql);
         
                preparedStatement.setString(1, id);
                preparedStatement.setString(2, fname);
                preparedStatement.setString(3, lname);
                preparedStatement.setString(4, date);
                preparedStatement.setString(5, email_id);
                preparedStatement.setString(6, gender);
                preparedStatement.setString(7, branch);
                preparedStatement.setString(8, roomno);
                
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
    public void deleteStudentById(String id) {
        PreparedStatement preparedStatement = null;																																																																																																																																													
		String sql;
		sql = "delete from student where studentId=?";

		try {
			preparedStatement = dbConnection.prepareStatement(sql);
			
			preparedStatement.setString(1, id);

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
    public void updateStudentById(String id, Student s) {
        PreparedStatement preparedStatement = null;																																																																																																																																													
        String sql;
        sql = "UPDATE student SET studentId=?, fname=?, lname=?, dateOfBirth=?, emailId=?, gender=?, branch=?, roomNo=? WHERE studentId=?";
  
        try {
          preparedStatement = dbConnection.prepareStatement(sql);
        
          preparedStatement.setString(1, s.getid());
          preparedStatement.setString(2, s.getfname());
          preparedStatement.setString(3, s.getlname());
          preparedStatement.setString(4, s.getDate());
          preparedStatement.setString(5, s.getemail());
          preparedStatement.setString(6, s.getgender());
          preparedStatement.setString(7, s.getbranch());
          preparedStatement.setString(8, s.getroomno());
          preparedStatement.setString(9, id);
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
