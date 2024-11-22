package Test;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.outbreak.UseCases.useCase6.useCase6;
import com.outbreak.UseCases.useCase6.useCase6_DAO_JDBC;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.contains;
import static org.mockito.Matchers.startsWith;
import static org.mockito.Mockito.*;

public class useCase6_DAO_JDBCTest {

    private Connection mockConnection;
    private PreparedStatement mockPreparedStatement;
    private ResultSet mockResultSet;
    private useCase6_DAO_JDBC dao;

    @Before
    public void setUp() {
        mockConnection = mock(Connection.class);
        mockPreparedStatement = mock(PreparedStatement.class);
        mockResultSet = mock(ResultSet.class);
        dao = new useCase6_DAO_JDBC(mockConnection);
    }

    @Test
    public void testGetInfo() throws SQLException {
        when(mockResultSet.getString("studentId")).thenReturn("S123");
        when(mockResultSet.getString("Full_Name")).thenReturn("John Doe");
        when(mockResultSet.getString("diagnosis_date")).thenReturn("2023-10-01");
        when(mockResultSet.getString("testId")).thenReturn("T456");
        when(mockResultSet.getString("qroomNo")).thenReturn("Q101");
        when(mockResultSet.getString("healthStatus")).thenReturn("Infected");

        useCase6 result = dao.getInfo(mockResultSet);

        assertNotNull(result);
        assertEquals("S123", result.getstudentId());
        assertEquals("John Doe", result.getName());
        assertEquals("2023-10-01",result.getDate());
        assertEquals("T456", result.getTestId());
        assertEquals("Q101", result.getqRoomNo());
        assertEquals("Infected", result.getHealthStatus());
        verify(mockResultSet, times(1)).getString("studentId");
    }

    @Test
    public void testUpdateInfectedDetails() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        dao.updateInfectedDetails("C123", "Q101", "2023-10-01", "2023-10-15", "Recovered");

        verify(mockConnection, times(3)).prepareStatement(anyString());
        verify(mockPreparedStatement, atLeast(1)).setString(anyInt(), anyString());
        verify(mockPreparedStatement, times(3)).executeUpdate();
    }

    @Test
    public void testRemoveInfectedDetails() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        dao.removeInfectedDetails("C123");

        verify(mockConnection, times(1)).prepareStatement("DELETE FROM posCase WHERE caseId=?");
        verify(mockPreparedStatement, times(1)).setString(1, "C123");
        verify(mockPreparedStatement, times(1)).executeUpdate();
        verify(mockPreparedStatement, times(1)).close();
    }

    @Test
    public void testUpdateInfectedDetails_InvalidInput() {
        dao.updateInfectedDetails(null, "null", "null", "null", "null");
        // Verify no exceptions occur
    }

    @Test
    public void testSQLExceptionHandling() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenThrow(new SQLException("Mocked SQL Exception"));

        dao.updateInfectedDetails("C123", "Q101", "null", "null", "null");
        // Verify exception handling without crashing
        
    }

    @Test
    public void testSQLExceptionHandling1() throws SQLException {

         // Simulating SQLException for statement and result set operations
        SQLException testException = new SQLException("Test SQL Exception", "TestState", 999);
        Mockito.when(mockConnection.createStatement()).thenThrow(testException);
        Mockito.when(mockResultSet.getString(Mockito.anyString())).thenThrow(testException);

         // Capture output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Expected output strings
        String expectedMessage = "SQLException: Test SQL Exception";
        String expectedState = "SQLState: TestState";
        String expectedErrorCode = "VendorError: 999";

        dao.getInfo(mockResultSet);
        assertTrue(outContent.toString().contains(expectedMessage));
        assertTrue(outContent.toString().contains(expectedState));
        assertTrue(outContent.toString().contains(expectedErrorCode));

        outContent.reset();

    }

    @Test
    public void testUpdateDatesAndHealthStatus() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        dao.updateInfectedDetails("C123", "null", "2023-10-01", "2023-10-15", "Recovered");

        verify(mockConnection).prepareStatement(contains("UPDATE quarantine SET"));
        verify(mockPreparedStatement, times(5)).setString(anyInt(), anyString());
        verify(mockPreparedStatement).executeUpdate();
        verify(mockPreparedStatement).close();
    }

    // Test updating all parameters
    @Test
    public void testUpdateAllParameters() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        dao.updateInfectedDetails("C123", "Q101", "2023-10-01", "2023-10-15", "Recovered");

        verify(mockConnection, times(3)).prepareStatement(anyString()); // 2 for qroomNo, 1 for quarantine
        verify(mockPreparedStatement, atLeast(6)).setString(anyInt(), anyString());
        verify(mockPreparedStatement, times(3)).executeUpdate();
        verify(mockPreparedStatement, times(2)).close();
    }

    // Test exception handling during qroomNo update
    @Test
    public void testSQLExceptionDuringQroomNoUpdate() throws SQLException {
        SQLException testException = new SQLException("Test Exception");
        when(mockConnection.prepareStatement(anyString())).thenThrow(testException);

        // Capture System.out
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        dao.updateInfectedDetails("C123", "Q101", "null", "null", "null");

        String output = outContent.toString();
        assertTrue(output.contains("Test Exception"));

        // Restore System.out
        System.setOut(System.out);
    }

    // Test exception handling during quarantine table update
    @Test
    public void testSQLExceptionDuringQuarantineUpdate() throws SQLException {
        when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        doThrow(new SQLException("Test Exception")).when(mockPreparedStatement).executeUpdate();

        // Capture System.out
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        dao.updateInfectedDetails("C123", "null", "2023-10-01", "2023-10-15", "Recovered");

        String output = outContent.toString();
        assertTrue(output.contains("Test Exception"));

        // Restore System.out
        System.setOut(System.out);
    }

    @Test
    public void testRemoveInfectedDetailsSQLException() throws SQLException {
        // Simulate SQLException during prepareStatement
        when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        doThrow(new SQLException("Test Exception")).when(mockPreparedStatement).executeUpdate();
        // Capture System.out to verify the output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Call the method
        dao.removeInfectedDetails("C123");

        // Verify exception handling without crashing
        String output = outContent.toString();
        assertTrue(output.contains("Test Exception"));

        // Restore System.out
        System.setOut(System.out);
    }

    @Test
    public void testRemoveInfectedDetailsCloseSQLException() throws SQLException {
        // Simulate normal execution for prepareStatement
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        // Simulate SQLException during preparedStatement.close()
        doThrow(new SQLException("Close Exception")).when(mockPreparedStatement).close();

        // Capture System.out to verify the output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Call the method
        dao.removeInfectedDetails("C123");

        // Verify exception handling for close()
        String output = outContent.toString();
        assertTrue(output.contains("Close Exception"));

        // Restore System.out
        System.setOut(System.out);
    }

    @Test
    public void testUpdateInfectedDetailsPreparedStatementUsage() throws SQLException {
        // Mock the connection to return the prepared statement
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        // Call the method with sample input
        dao.updateInfectedDetails("C123", "Q101", "2023-10-01", "2023-10-15", "Recovered");

        // Verify the correct SQL statements are prepared
        verify(mockConnection).prepareStatement("UPDATE posCase SET qroomNo=? WHERE caseId=?");
        verify(mockConnection).prepareStatement("UPDATE hostelRoom SET vacancy=0 WHERE roomNo=? and roomType='Quarantine'");
        verify(mockConnection).prepareStatement(startsWith("UPDATE quarantine SET caseId=?"));

        // Verify that parameters are set correctly
        verify(mockPreparedStatement, atLeastOnce()).setString(1, "Q101"); // qroomNo
        verify(mockPreparedStatement, atLeastOnce()).setString(2, "C123"); // caseId

        // Verify other query parameters for quarantine update
        verify(mockPreparedStatement, atLeastOnce()).setString(anyInt(), anyString());

        // Verify executeUpdate is called
        verify(mockPreparedStatement, atLeastOnce()).executeUpdate();

        // Verify that the prepared statement is closed
        verify(mockPreparedStatement, atLeastOnce()).close();
    }
}
