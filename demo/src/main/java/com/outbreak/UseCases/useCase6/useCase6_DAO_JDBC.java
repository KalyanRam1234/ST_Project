package com.outbreak.UseCases.useCase6;

import java.sql.*;
import java.util.ArrayList;

public class useCase6_DAO_JDBC implements useCase6_DAO{

    class DS{
        int i;
        String s;

        public DS(int i, String s) { this.i=i; this.s=s;}
        public int getInt() {return this.i;}
        public String getString() {return this.s;}
    }

    Connection dbConnection;

    public useCase6_DAO_JDBC(Connection dbconn){

		dbConnection = dbconn;
	}

    public useCase6 getInfo(ResultSet rs){
        useCase6 s=new useCase6();
        try{
            String id  = rs.getString("studentId");
            String name = rs.getString("Full_Name");
            String date = rs.getString("diagnosis_date");
            String testId=rs.getString("testId");
            String qroomNo=rs.getString("qroomNo");
            String healthStatus=rs.getString("healthStatus");
            
            s=new useCase6(id, name, date, testId, qroomNo, healthStatus);
        }
        catch ( SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
        }
        return s;
    }

    @Override
    public void updateInfectedDetails(String caseid, String qroomNo, String sdate, String edate, String healthStatus) {
                PreparedStatement preparedStatement = null;																																																																																																																																													
                String sql;
                
                if(!qroomNo.equals("null"))
                {
                    sql = "UPDATE posCase SET qroomNo=? WHERE caseId=?";
        
                    try {
                        preparedStatement = dbConnection.prepareStatement(sql);
                    
                        preparedStatement.setString(1,qroomNo);
                        preparedStatement.setString(2,caseid);
                        // execute update SQL stetement
                        preparedStatement.executeUpdate();
                    } catch (SQLException e) {
                         System.out.println(e.getMessage());
                    }
        
                    try{
                         if (preparedStatement != null) {
                            sql = "UPDATE hostelRoom SET vacancy=0 WHERE roomNo=? and roomType='Quarantine'";
        
                            try {
                                preparedStatement = dbConnection.prepareStatement(sql);
                            
                                preparedStatement.setString(1,qroomNo);
                                // execute update SQL stetement
                                preparedStatement.executeUpdate();
                            } catch (SQLException e) {
                                System.out.println(e.getMessage());
                            }

                            preparedStatement.close();
                        }
                    } catch (SQLException e) {
                         System.out.println(e.getMessage());
                    }
        
    
                }
                
                if(!sdate.equals("null") || !edate.equals("null") || !healthStatus.equals("null"))
                {
                    ArrayList<DS> l = new ArrayList<DS>();

                    sql = "UPDATE quarantine SET caseId=? "; 
                    l.add(new DS(1,caseid));
                    int i=2;
                    if(!sdate.equals("null"))
                        {
                            sql += " ,startDate=? "; l.add(new DS(i,sdate)); i++;
                        }
                    if(!edate.equals("null"))
                        {
                            sql += " ,endDate=? "; l.add(new DS(i,edate)); i++;
                        }
                    if(!healthStatus.equals("null"))
                        {
                            sql += " ,healthStatus=? "; l.add(new DS(i,healthStatus)); i++;
                        }
                    sql += "WHERE caseId=?";
                    l.add(new DS(i,caseid));
                    //sql = "UPDATE quarantine SET caseId=?, startDate=?, endDate=?, healthStatus=? WHERE caseId=?";

                    try {
                        preparedStatement = dbConnection.prepareStatement(sql);

                        for(int j=0; j<l.size(); j++)
                        {
                            preparedStatement.setString(l.get(j).getInt(),l.get(j).getString());
                        }
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

    @Override
    public void removeInfectedDetails(String caseId) {
        PreparedStatement preparedStatement = null;																																																																																																																																													
		String sql;
		sql = "DELETE FROM posCase WHERE caseId=?";

		try {
			preparedStatement = dbConnection.prepareStatement(sql);
			
			preparedStatement.setString(1, caseId);
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