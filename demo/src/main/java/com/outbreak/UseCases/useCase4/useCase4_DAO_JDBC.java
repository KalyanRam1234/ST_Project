package com.outbreak.UseCases.useCase4;

import java.sql.*;
import java.util.ArrayList;

public class useCase4_DAO_JDBC implements useCase4_DAO{
    Connection dbConnection;

    public useCase4_DAO_JDBC(Connection dbconn){

		dbConnection = dbconn;
	}

    public useCase4 getInfo(ResultSet rs){
        useCase4 s=new useCase4();
        try{
            String id  = rs.getString("studentId");
            String name = rs.getString("Full_Name");
            int doseNo = rs.getInt("doseNo");
            String dateTaken=rs.getString("dateTaken");
            String vaccineName=rs.getString("vaccineName");
            
            s=new useCase4(id, name, doseNo, dateTaken, vaccineName);
        }
        catch ( SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
        }
        return s;
    }

    public useCase4b getInfo1(ResultSet rs){
        useCase4b s=new useCase4b();
        try{
            String id  = rs.getString("studentId");
            String name = rs.getString("Full_Name");
            String vaccination_status = rs.getString("vaccination_status");
            String dosesTaken=rs.getString("dosesTaken");
            
            s=new useCase4b(id, name, dosesTaken, vaccination_status);
        }
        catch ( SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
        }
        return s;
    }

    @Override
    public ArrayList<useCase4> getStudentDoses(String rollNo) {
        ArrayList<useCase4> list=new ArrayList<useCase4>(0);
        String sql;
		Statement stmt = null;

        try{
            stmt=dbConnection.createStatement();
            sql="select s.studentId as studentId, concat(fname,' ',lname) as " + "Full_Name" + ", d.doseNo, d.dateTaken, d.vaccineName from student as s join dose as d where s.studentId=d.studentId and s.studentId=\"" + rollNo + "\"";

            ResultSet rs= stmt.executeQuery(sql);
            
            while(rs.next()){
                useCase4 s=getInfo(rs);
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
    public ArrayList<useCase4> getAllStudentDoses() {
        ArrayList<useCase4> list=new ArrayList<useCase4>(0);
        String sql;
		Statement stmt = null;

        try{
            stmt=dbConnection.createStatement();
            sql="select s.studentId as studentId, concat(fname,' ',lname) as " + "Full_Name" + ", d.doseNo, d.dateTaken, d.vaccineName from student as s join dose as d where s.studentId=d.studentId";

            ResultSet rs= stmt.executeQuery(sql);
            
            while(rs.next()){
                useCase4 s=getInfo(rs);
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
    public ArrayList<useCase4b> getVaccinationDetails() {
        ArrayList<useCase4b> list=new ArrayList<useCase4b>(0);
        String sql;
		Statement stmt = null;

        try{
            stmt=dbConnection.createStatement();
            sql="select s.studentId as studentId, concat(fname,' ',lname) as " + "Full_Name" + ", v.vaccinationStatus as vaccination_status, v.dosesTaken as dosesTaken from student as s join vaccination as v on s.studentId=v.studentId";

            ResultSet rs= stmt.executeQuery(sql);
            
            while(rs.next()){
                useCase4b s=getInfo1(rs);
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
    public ArrayList<useCase4b> getVaccinationDetailsBatch(String batch) {
        ArrayList<useCase4b> list=new ArrayList<useCase4b>(0);
        String sql;
		Statement stmt = null;

        try{
            stmt=dbConnection.createStatement();
            sql="select s.studentId as studentId, concat(fname,' ',lname) as " + "Full_Name" + ", v.vaccinationStatus as vaccination_status, v.dosesTaken as dosesTaken from student as s join vaccination as v on s.studentId=v.studentId where s.studentId like \"" + batch + "%\"";

            ResultSet rs= stmt.executeQuery(sql);
            
            while(rs.next()){
                useCase4b s=getInfo1(rs);
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