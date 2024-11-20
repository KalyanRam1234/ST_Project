package com.outbreak.UseCases.useCase8;

import java.sql.*;
import java.util.ArrayList;

public class useCase8DAO_JDBC implements useCase8DAO {
    Connection dbConnection;

    public useCase8DAO_JDBC(Connection dbconn){

		dbConnection = dbconn;
	}

    @Override
    public useCase8 getRoomDetails(String roomNo){
        useCase8 hr=null;
        String sql;
		Statement stmt = null;

        try{
            stmt=dbConnection.createStatement();
            sql="select s.studentId as 'sid', concat(s.fname,s.lname) as 'Name',r.roomNo as 'rno',r.capacity as 'rc',r.vacancy as 'rv', r.hostelType as 'rh' from student as s join hostelRoom as r where s.roomNo=r.roomNo and r.roomNo= '" + roomNo+"'";
            ResultSet rs= stmt.executeQuery(sql);
            while(rs.next()){

                String studentId = rs.getString("sid");
                String capacity= rs.getString("rc");
                String vacancy = rs.getString("rv");
                String Name = rs.getString("Name");
                String hostelType=rs.getString("rh");
                hr = new useCase8(studentId, Name, roomNo, Integer.parseInt(capacity), Integer.parseInt(vacancy), hostelType);
                break;
            }
        }
        catch ( SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
        }

        return hr;
    }

    public ArrayList<useCase8> getAllRoomDetails(String hostelType){
        ArrayList<useCase8> hr=new ArrayList<useCase8>(0);
        String sql="";
		Statement stmt = null;

        try{
            stmt=dbConnection.createStatement();
            if(hostelType.equals("Hostel"))
            sql="select s.studentId as 'sid', concat(s.fname,' ',s.lname) as 'Name',r.roomNo as 'rno',r.capacity as 'rc',r.vacancy as 'rv', r.hostelType as 'rh' from student as s join hostelRoom as r where s.roomNo=r.roomNo and r.roomType='"+hostelType +"' order by rv";

            ResultSet rs= stmt.executeQuery(sql);
            while(rs.next()){
                String roomNo=rs.getString("rno");
                String studentId = rs.getString("sid");
                String capacity= rs.getString("rc");
                String vacancy = rs.getString("rv");
                String Name = rs.getString("Name");
                String hostelTyp=rs.getString("rh");
                useCase8 p=new useCase8(studentId, Name, roomNo, Integer.parseInt(capacity), Integer.parseInt(vacancy),hostelTyp);
                hr.add(p);
            }
        }
        catch ( SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
        }

        return hr;
    }
}
