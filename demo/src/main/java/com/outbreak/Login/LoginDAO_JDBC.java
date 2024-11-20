package com.outbreak.Login;

import java.sql.*;
import java.util.*;
import java.security.MessageDigest;  
import java.security.NoSuchAlgorithmException; 
public class LoginDAO_JDBC implements LoginDAO {
    Connection dbConnection;

    public LoginDAO_JDBC(Connection dbconn){

		dbConnection = dbconn;
	}

    public int Login(String username, String password){
        String sql;
		Statement stmt = null;
        String encryptedpassword = null;  
        try{
            stmt=dbConnection.createStatement();
            MessageDigest m = MessageDigest.getInstance("MD5");  
            m.update(password.getBytes());  
            byte[] bytes = m.digest(); 
            StringBuilder s = new StringBuilder();  
            for(int i=0; i< bytes.length ;i++)  
            {  
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));  
            }  
               
            encryptedpassword = s.toString();  
            sql= "select userpass from login_t where userid='"+username+"'" ;
            ResultSet rs= stmt.executeQuery(sql);
            rs.next();
            int rows=rs.getRow();
            if(rows==0) return 2;
            String retrievedpass=rs.getString("userpass");
            if(retrievedpass.equals(encryptedpassword)) return 0;
        }
        catch ( SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
        }
        catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return 1;
        
    }

    public int Register(String username, String password){
        String sql;
		PreparedStatement preparedStatement = null;	
        String encryptedpassword = null;  
        try{
        
            MessageDigest m = MessageDigest.getInstance("MD5");  
            m.update(password.getBytes());  
            byte[] bytes = m.digest(); 
            StringBuilder s = new StringBuilder();  
            for(int i=0; i< bytes.length ;i++)  
            {  
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));  
            }  
               
            encryptedpassword = s.toString();  
            sql= "insert into login_t(userid, userpass) values(?,?)";
            preparedStatement = dbConnection.prepareStatement(sql);

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, encryptedpassword);

            preparedStatement.executeUpdate();
            
            return 0;
        }
        catch ( SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
        }
        catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return 1;
    }
}
