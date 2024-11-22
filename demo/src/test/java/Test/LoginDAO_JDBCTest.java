package Test;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Before;
import org.junit.Test;
import java.io.*;

import com.outbreak.Login.*;

public class LoginDAO_JDBCTest {

    private Connection mockConnection;
    private Statement mockStatement;
    private PreparedStatement mockPreparedStatement;
    private ResultSet mockResultSet;
    private LoginDAO_JDBC loginDAO;

    @Before
    public void setUp() throws SQLException {
        mockConnection = mock(Connection.class);
        mockStatement = mock(Statement.class);
        mockPreparedStatement = mock(PreparedStatement.class);
        mockResultSet = mock(ResultSet.class);
        loginDAO = new LoginDAO_JDBC(mockConnection);
    }

    @Test
    public void testValidLogin() throws Exception {
        // Mock the database behavior
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true); // User exists
        when(mockResultSet.getRow()).thenReturn(1); // Simulate a valid row
        when(mockResultSet.getString("userpass")).thenReturn("5f4dcc3b5aa765d61d8327deb882cf99"); // "password" in MD5

        // Execute the login method
        int result = loginDAO.Login("testUser", "password");

        // Verify and assert
        assertEquals(0, result); // Login success
        verify(mockStatement).executeQuery(anyString());
    }

    @Test
    public void testInvalidLoginWrongPassword() throws Exception {
        // Mock the database behavior
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true); // User exists
        when(mockResultSet.getRow()).thenReturn(1); // Simulate a valid row
        when(mockResultSet.getString("userpass")).thenReturn("5f4dcc3b5aa765d61d8327deb882cf99"); // "password" in MD5

        // Execute the login method
        int result = loginDAO.Login("testUser", "wrongPassword");

        // Verify and assert
        assertEquals(1, result); // Login failure due to incorrect password
        verify(mockStatement).executeQuery(anyString());
    }

    @Test
    public void testLoginUserNotFound() throws Exception {
        // Mock the database behavior
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(false); // User does not exist

        // Execute the login method
        int result = loginDAO.Login("nonexistentUser", "password");

        // Verify and assert
        assertEquals(2, result); // User not found
        verify(mockStatement).executeQuery(anyString());
    }

    @Test
    public void testSQLExceptionInLogin() throws Exception {
        // Mock the database behavior
        when(mockConnection.createStatement()).thenThrow(new SQLException("Test SQL Exception"));

        // Capture output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Execute the login method
        int result = loginDAO.Login("testUser", "password");

        // Verify output
        assertEquals(1, result); // SQLException should return 1
        String output = outContent.toString();
        assertTrue(output.contains("SQLException: Test SQL Exception"));
        System.setOut(System.out);
    }

    @Test
    public void testValidRegister() throws Exception {
        // Mock the database behavior
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        // Execute the register method
        int result = loginDAO.Register("newUser", "newPassword");

        // Verify and assert
        assertEquals(0, result); // Registration success
        verify(mockPreparedStatement).setString(1, "newUser");
        verify(mockPreparedStatement).setString(2, "14a88b9d2f52c55b5fbcf9c5d9c11875"); // Match the actual generated hash
        verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    public void testDuplicateUsernameRegister() throws Exception {
        // Mock the database behavior
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        doThrow(new SQLException("Duplicate entry")).when(mockPreparedStatement).executeUpdate();

        // Capture output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Execute the register method
        int result = loginDAO.Register("existingUser", "password");

        // Verify output
        assertEquals(1, result); // Registration fails due to duplicate username
        String output = outContent.toString();
        assertTrue(output.contains("SQLException: Duplicate entry"));
        System.setOut(System.out);
    }

    @Test
    public void testLoginSQLExceptionHandling() throws Exception {
        // Redirect System.out
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Mock the database behavior to throw an SQLException
        SQLException mockSQLException = new SQLException("Test SQL Exception", "42000", 1049);
        when(mockConnection.createStatement()).thenThrow(mockSQLException);

        // Execute the Login method
        loginDAO.Login("testUser", "testPassword");

        // Verify the output
        String output = outContent.toString();
        assertTrue(output.contains("SQLException: Test SQL Exception"));
        assertTrue(output.contains("SQLState: 42000"));
        assertTrue(output.contains("VendorError: 1049"));

        // Reset System.out
        System.setOut(System.out);
    }

    @Test
    public void testRegisterSQLExceptionPrinting() throws Exception {
        // Redirect System.out
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Simulate SQLException
        SQLException mockSQLException = new SQLException("Register SQL Exception", "42000", 5678);
        when(mockConnection.prepareStatement(anyString())).thenThrow(mockSQLException);

        // Execute the Register method
        loginDAO.Register("newUser", "newPassword");

        // Verify the printed output
        String output = outContent.toString();
        assertTrue(output.contains("SQLException: Register SQL Exception"));
        assertTrue(output.contains("SQLState: 42000"));
        assertTrue(output.contains("VendorError: 5678"));

        // Reset System.out
        System.setOut(System.out);
    }

    // @Test
    // public void testLoginNoSuchAlgorithmExceptionHandling() throws Exception {
    //     // Redirect System.err to capture stack trace
    //     ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    //     System.setErr(new PrintStream(errContent));

    //     // Create a LoginDAO that triggers NoSuchAlgorithmException
    //     LoginDAO_JDBC faultyLoginDAO = new LoginDAO_JDBC(mockConnection) {
    //         @Override
    //         public int Login(String username, String password) {
    //             try {
    //                 throw new NoSuchAlgorithmException("Test NoSuchAlgorithmException");
    //             } catch (NoSuchAlgorithmException e) {
    //                 e.printStackTrace(); // This is what we want to capture
    //                 return 1;
    //             }
    //         }
    //     };

    //     // Execute the Login method
    //     faultyLoginDAO.Login("testUser", "testPassword");

    //     // Verify the output
    //     String output = errContent.toString();
    //     assertTrue(output.contains("java.security.NoSuchAlgorithmException: Test NoSuchAlgorithmException"));

    //     // Reset System.err
    //     System.setErr(System.err);
    // }

    // @Test
    // public void testRegisterNoSuchAlgorithmExceptionHandling() throws Exception {
    //     // Redirect System.err to capture stack trace
    //     ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    //     System.setErr(new PrintStream(errContent));

    //     // Create a LoginDAO that triggers NoSuchAlgorithmException in Register method
    //     LoginDAO_JDBC faultyRegisterDAO = new LoginDAO_JDBC(mockConnection) {
    //         @Override
    //         public int Register(String username, String password) {
    //             try {
    //                 throw new NoSuchAlgorithmException("Test NoSuchAlgorithmException in Register");
    //             } catch (NoSuchAlgorithmException e) {
    //                 e.printStackTrace(); // This is what we want to capture
    //                 return 1;
    //             }
    //         }
    //     };

    //     // Execute the Register method
    //     faultyRegisterDAO.Register("newUser", "newPassword");

    //     // Verify the output contains the expected stack trace message
    //     String output = errContent.toString();
    //     assertTrue(output.contains("java.security.NoSuchAlgorithmException: Test NoSuchAlgorithmException in Register"));

    //     // Reset System.err
    //     System.setErr(System.err);
    // }


}
