package com.outbreak.UseCases.useCase7;

import java.sql.*;
import java.util.ArrayList;

public class useCase7_DAO_JDBC implements useCase7_DAO{
    Connection dbConnection;

    public useCase7_DAO_JDBC(Connection dbconn){

		dbConnection = dbconn;
	}

    public useCase7a getInfoa(ResultSet rs){
        useCase7a s=new useCase7a();
        try{
            String id  = rs.getString("studentId");
            String name = rs.getString("Name");
            String caseId = rs.getString("caseId");
            String hroomNo=rs.getString("hroomNo");
            String qroomNo=rs.getString("qroomNo");
            
            s=new useCase7a(id, name, caseId, hroomNo, qroomNo, "null");
        }
        catch ( SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
        }
        return s;
    }

    public useCase7b getInfob(ResultSet rs){
        useCase7b s=new useCase7b();
        try{
            String id  = rs.getString("studentId");
            String name = rs.getString("name");
            String vaccination_status = rs.getString("vaccination_status");
            String rtpcr_recent_result=rs.getString("rtpcr_recent_result");
            String rtpcr_date=rs.getString("rtpcr_date");

            
            s=new useCase7b(id, name, vaccination_status, rtpcr_recent_result, rtpcr_date);
        }
        catch ( SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
        }
        return s;
    }

    public useCase7b getInfob1(ResultSet rs, useCase7b s)
    {
        try{
            String rtpcr_recent_result=rs.getString("rtpcr_recent_result");
            String rtpcr_date=rs.getString("rtpcr_date");
            s.setRTPCRResult(rtpcr_recent_result);
            s.setRTPCRDate(rtpcr_date);
        }
        catch ( SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
        }
        return s;
    }

    @Override
    public ArrayList<useCase7a> getInfectedStudentsList() {
        ArrayList<useCase7a> list=new ArrayList<useCase7a>(0);
        String sql;
		Statement stmt = null;

        try{
            stmt=dbConnection.createStatement();
            //studentId, name, caseId, hroomNo, qroomNo, healthStatus
            sql="select s.studentId as studentId, concat(fname,' ',lname) as " + "Name" + ", p.caseId as caseId, s.roomNo as hroomNo, p.qroomNo as qroomNo from student as s join posCase as p on s.studentId=p.studentId";

            ResultSet rs= stmt.executeQuery(sql);
            
            while(rs.next()){
                useCase7a s=getInfoa(rs);
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
    public ArrayList<ArrayList<useCase7b>> getRooomatesOfInfectedStudents(ArrayList<useCase7a> uc7a) 
    {
        ArrayList<ArrayList<useCase7b>> list=new ArrayList<ArrayList<useCase7b>>();
        String sql;
		Statement stmt = null;

        for(int i=0; i<uc7a.size(); i++)
        {
            try{
                stmt=dbConnection.createStatement();
                //studentId, name, caseId, hroomNo, qroomNo, healthStatus
                sql="select s.studentId as studentId, concat(s.fname,' ',s.lname) as name, v.vaccinationStatus as vaccination_status, r.test_result as rtpcr_recent_result, r.testDate as rtpcr_date from student as s join vaccination as v on v.studentId=s.studentId join rtpcr r on r.studentId=s.studentId where r.testDate=(select max(r1.testDate) from rtpcr r1 where r1.studentId=s.studentId) and s.roomNo=\""+uc7a.get(i).gethroomNo()+"\" and s.studentId!=\"" + uc7a.get(i).getstudentId() + "\"";
                ResultSet rs= stmt.executeQuery(sql);
               
                ArrayList<useCase7b> rlist = new ArrayList<useCase7b>();
              
                while(rs.next()){
                    
                    useCase7b s=getInfob(rs);
                    rlist.add(s);
                }
                list.add(rlist);
            }
            catch ( SQLException ex){
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }
        }

        
        return list;
    }
}