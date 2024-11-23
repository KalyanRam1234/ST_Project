package com.outbreak.HostelRoom;
import java.sql.*;
import java.util.ArrayList;

public class HostelDAO_JDBC implements HostelDAO {

    Connection dbConnection;

    public HostelDAO_JDBC(Connection dbconn){

		dbConnection = dbconn;
	}

    @Override
    public HostelRoom getRoomDetails(String roomno) {
        HostelRoom hr=null;
        String sql;
		Statement stmt = null;
        
        try{
            stmt=dbConnection.createStatement();
            sql="select * from hostelRoom where roomNo= '" + roomno+"'";
            ResultSet rs= stmt.executeQuery(sql);

            while(rs.next()){

                String roomNo  = rs.getString("roomNo");
                String roomType = rs.getString("roomType");
                String capacity= rs.getString("capacity");
                String vacancy = rs.getString("vacancy");
                String hostelType = rs.getString("hostelType");

                hr = new HostelRoom(roomNo, roomType, Integer.parseInt(capacity), Integer.parseInt(vacancy), hostelType);
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

    @Override
    public int getHostelVacancy(String hostelType) {
        int total_vacancy=0;
        String sql;
		Statement stmt = null;
        
        try{
            stmt=dbConnection.createStatement();

            if(hostelType.equals("Hostel"))
                sql="select * from hostelRoom where roomType=\"Hostel\"";
            else
                sql="select * from hostelRoom where roomType=\"Quarantine\"";
            
            ResultSet rs= stmt.executeQuery(sql);
            while(rs.next()){
                String vacancy = rs.getString("vacancy");
                total_vacancy += Integer.parseInt(vacancy);
            }
        }
        catch ( SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
        }

        return total_vacancy;
    }

    @Override
    public int getHostelCapacity(String hostelType) {
        int total_capacity=0;
        String sql;
		Statement stmt = null;
        
        try{
            stmt=dbConnection.createStatement();

            if(hostelType.equals("Hostel"))
                sql="select * from hostelRoom where roomType=\"Hostel\"";
            else
                sql="select * from hostelRoom where roomType=\"Quarantine\"";
            
            ResultSet rs= stmt.executeQuery(sql);
            while(rs.next()){
                String capacity = rs.getString("capacity");
                total_capacity += Integer.parseInt(capacity);
            }
        }
        catch ( SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
        }

        return total_capacity;
    }

    @Override
    public ArrayList<HostelRoom> getallRooms() {
        ArrayList<HostelRoom> hr_list = new ArrayList<HostelRoom>();
        String sql;
		Statement stmt = null;
        
        try{
            stmt=dbConnection.createStatement();
            sql="select * from hostelRoom where roomType='Quarantine' order by vacancy";
            
            ResultSet rs= stmt.executeQuery(sql);
            while(rs.next()){
                String roomNo  = rs.getString("roomNo");
                String roomType = rs.getString("roomType");
                String capacity= rs.getString("capacity");
                String vacancy = rs.getString("vacancy");
                String hostelType1 = rs.getString("hostelType");

                HostelRoom hr = new HostelRoom(roomNo, roomType, Integer.parseInt(capacity), Integer.parseInt(vacancy), hostelType1);
                hr_list.add(hr);
            }
        }
        catch ( SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
        }

        return hr_list;
    }

    @Override
    public ArrayList<HostelRoom> getEmptyHRooms() {
        ArrayList<HostelRoom> hr_list = new ArrayList<HostelRoom>();
        String sql;
		Statement stmt = null;
        
        try{
            stmt=dbConnection.createStatement();

        
                sql="select * from hostelRoom where roomType=\"Quarantine\" AND vacancy>0";
           
            ResultSet rs= stmt.executeQuery(sql);
            while(rs.next()){
                String roomNo  = rs.getString("roomNo");
                String roomType = rs.getString("roomType");
                String capacity= rs.getString("capacity");
                String vacancy = rs.getString("vacancy");
                String hostelType1 = rs.getString("hostelType");

                HostelRoom hr = new HostelRoom(roomNo, roomType, Integer.parseInt(capacity), Integer.parseInt(vacancy), hostelType1);
                hr_list.add(hr);
            }
        }
        catch ( SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
        }

        return hr_list;
    }

    //implement functions in interface based on project requirements
    public void enterHostelRoom(String roomno, String roomType, int vacancy, int capacity, String hostelType)
    {
        PreparedStatement preparedStatement = null;																																																																																																																																													
		String sql;
		sql = "insert into hostelRoom(roomno, roomType, capacity, vacancy, hostelType) values (?,?,?,?,?)";

		try {
			preparedStatement = dbConnection.prepareStatement(sql);
 
			preparedStatement.setString(1, roomno);
			preparedStatement.setString(2, roomType);
            preparedStatement.setInt(3, capacity);
            preparedStatement.setInt(4, vacancy);
            preparedStatement.setString(5, hostelType);
 
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
    public void deleteHostelRoom(String roomNo) {
        PreparedStatement preparedStatement = null;																																																																																																																																													
		String sql;
		sql = "delete from hostelRoom where roomNo=?";

		try {
			preparedStatement = dbConnection.prepareStatement(sql);
			
			preparedStatement.setString(1, roomNo);
			// execute update SQL stetement
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
 			System.out.println(e.getMessage());
 		}

		try{
			// if (preparedStatement != null) {
			preparedStatement.close();
			// }
		} catch (SQLException e) {
 			System.out.println(e.getMessage());
 		}
    }
}
