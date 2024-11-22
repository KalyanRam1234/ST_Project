package Test;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.PreparedStatement;

import org.junit.Test;

import com.outbreak.Case.Case;
import com.outbreak.Case.CaseDAO_JDBC;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Before;

public class CaseDao_JDBCTest {

    private Connection mockConnection;
    private Statement mockStatement;
    private PreparedStatement mockPreparedStatement;
    private ResultSet mockResultSet;

    @Before
    public void setUp() throws SQLException {
        mockConnection = mock(Connection.class);
        mockStatement = mock(Statement.class);
        mockPreparedStatement = mock(PreparedStatement.class);
        mockResultSet = mock(ResultSet.class);
    }

    @Test
    public void testEnterNewCase() throws Exception {
        // Mock the PreparedStatement
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        // Create the DAO and call the method
        CaseDAO_JDBC caseDAO = new CaseDAO_JDBC(mockConnection);
        caseDAO.enterNewCase("CASE123", "STUDENT123", "ROOM1", "TEST123", "2024-11-20");

        // Verify the PreparedStatement interactions
        verify(mockConnection).prepareStatement("insert into posCase(caseId, studentId, qroomNo, testId, diagnosisDate) values (?,?,?,?,?)");
        verify(mockPreparedStatement).setString(1, "CASE123");
        verify(mockPreparedStatement).setString(2, "STUDENT123");
        verify(mockPreparedStatement).setString(3, "ROOM1");
        verify(mockPreparedStatement).setString(4, "TEST123");
        verify(mockPreparedStatement).setString(5, "2024-11-20");
        verify(mockPreparedStatement).executeUpdate();
        verify(mockPreparedStatement).close();
    }

    @Test
    public void testGetTotalCases() throws Exception {
        // Mock the Statement and ResultSet
        Statement mockStatement = mock(Statement.class);
        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getString("max")).thenReturn("CASE123");

        // Create the DAO and call the method
        CaseDAO_JDBC caseDAO = new CaseDAO_JDBC(mockConnection);
        int totalCases = caseDAO.getTotalCases();

        // Verify interactions and assert result
        assertEquals(123, totalCases); // Assuming "CASE123" → 123
        verify(mockStatement).executeQuery("select max(caseId) as max from posCase");
        // verify(mockStatement).close();
    }

    @Test
    public void testDeleteCaseByCaseId() throws Exception {
        // Mock the PreparedStatement
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        // Create the DAO and call the method
        CaseDAO_JDBC caseDAO = new CaseDAO_JDBC(mockConnection);
        caseDAO.deleteCase("CASE123", null);

        // Verify interactions
        verify(mockConnection).prepareStatement("delete from posCase where caseId=?");
        verify(mockPreparedStatement).setString(1, "CASE123");
        verify(mockPreparedStatement).executeUpdate();
        verify(mockPreparedStatement).close();
    }

    @Test
    public void testDeleteCaseByStudentId() throws Exception {
        // Mock the PreparedStatement
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        // Create the DAO and call the method
        CaseDAO_JDBC caseDAO = new CaseDAO_JDBC(mockConnection);
        caseDAO.deleteCase(null, "STUDENT123");

        // Verify interactions
        verify(mockConnection).prepareStatement("delete from posCase where studentId=?");
        verify(mockPreparedStatement).setString(1, "STUDENT123");
        verify(mockPreparedStatement).executeUpdate();
        verify(mockPreparedStatement).close();
    }

    @Test
    public void testGetCaseByCaseId() throws Exception {
        // Mock the Statement and ResultSet
        Statement mockStatement = mock(Statement.class);
        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getString("caseId")).thenReturn("CASE123");
        when(mockResultSet.getString("studentId")).thenReturn("STUDENT123");
        when(mockResultSet.getString("qroomNo")).thenReturn("ROOM1");
        when(mockResultSet.getString("testId")).thenReturn("TEST123");
        when(mockResultSet.getString("diagnosisDate")).thenReturn("2024-11-20");

        // Create the DAO and call the method
        CaseDAO_JDBC caseDAO = new CaseDAO_JDBC(mockConnection);
        Case c = caseDAO.getCaseByCaseId("CASE123");

        // Verify the result
        assertNotNull(c);
        assertEquals("CASE123", c.getCaseId());
        assertEquals("STUDENT123", c.getStudentId());
        assertEquals("ROOM1", c.getQroom());
        assertEquals("TEST123", c.getTestId());
        assertEquals("2024-11-20", c.getDiagnosisdate());
        verify(mockStatement).executeQuery("select * from posCase where caseId = CASE123");
    }

    @Test
    public void testGetCaseByCaseId_DuplicateKey() throws Exception {
        // Mock the ResultSet
        when(mockResultSet.next()).thenReturn(true, true); // Simulate multiple rows being returned
        when(mockResultSet.getString("caseId")).thenReturn("CASE123");
        when(mockResultSet.getString("studentId")).thenReturn("STUDENT123");
        when(mockResultSet.getString("qroomNo")).thenReturn("QROOM1");
        when(mockResultSet.getString("testId")).thenReturn("TEST123");
        when(mockResultSet.getString("diagnosisDate")).thenReturn("2024-11-21");

        // Mock the Statement
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockConnection.createStatement()).thenReturn(mockStatement);

        // Redirect System.out to capture printed output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Create the DAO and call the method
        CaseDAO_JDBC caseDAO = new CaseDAO_JDBC(mockConnection);
        caseDAO.getCaseByCaseId("CASE123");

        // Verify interactions
        verify(mockStatement).executeQuery("select * from posCase where caseId = CASE123");
        verify(mockResultSet, times(2)).next(); // Verify that next() was called twice

        // Verify the printed output
        String output = outContent.toString();
        assertTrue(output.contains("Fatal Fault in table definition: Found Duplicate key in table."));

        // Reset System.out
        System.setOut(System.out);
    }

    // @Test
    // public void testGetCaseByCaseId_NoMatchingRecord() throws Exception {
    //     // Mock ResultSet to simulate no matching record
    //     when(mockConnection.createStatement()).thenReturn(mockStatement);
    //     when(mockStatement.executeQuery("select * from posCase where caseId = 'CASE123'"))
    //         .thenReturn(mockResultSet);
    //     when(mockResultSet.next()).thenReturn(false); // No matching record

    //     // Redirect System.out to capture the printed message
    //     ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    //     System.setOut(new PrintStream(outContent));

    //     // Create the DAO and call the method
    //     CaseDAO_JDBC caseDAO = new CaseDAO_JDBC(mockConnection);
    //     Case result = caseDAO.getCaseByCaseId("CASE123");

    //     // Verify and assert
    //     assertNull(result); // No Case object should be returned
    //     assertTrue(outContent.toString().contains("There is no matching entry in the student table."));

    //     // Reset System.out
    //     System.setOut(System.out);
    // }



    @Test
    public void testGetCaseByStudentId() throws Exception {
        // Mock the Statement and ResultSet
        Statement mockStatement = mock(Statement.class);
        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getString("caseId")).thenReturn("CASE123");
        when(mockResultSet.getString("studentId")).thenReturn("STUDENT123");
        when(mockResultSet.getString("qroomNo")).thenReturn("ROOM1");
        when(mockResultSet.getString("testId")).thenReturn("TEST123");
        when(mockResultSet.getString("diagnosisDate")).thenReturn("2024-11-20");

        // Create the DAO and call the method
        CaseDAO_JDBC caseDAO = new CaseDAO_JDBC(mockConnection);
        Case c = caseDAO.getCaseByStudentId("STUDENT123");

        // Verify the result
        assertNotNull(c);
        assertEquals("CASE123", c.getCaseId());
        assertEquals("STUDENT123", c.getStudentId());
        assertEquals("ROOM1", c.getQroom());
        assertEquals("TEST123", c.getTestId());
        assertEquals("2024-11-20", c.getDiagnosisdate());
        verify(mockStatement).executeQuery("select * from posCase where studentId = STUDENT123");
    }

    @Test
    public void testGetCaseByStudentId_DuplicateKey() throws Exception {
        // Mock the ResultSet
        when(mockResultSet.next()).thenReturn(true, true); // Simulate multiple rows being returned
        when(mockResultSet.getString("caseId")).thenReturn("CASE123");
        when(mockResultSet.getString("studentId")).thenReturn("STUDENT123");
        when(mockResultSet.getString("qroomNo")).thenReturn("QROOM1");
        when(mockResultSet.getString("testId")).thenReturn("TEST123");
        when(mockResultSet.getString("diagnosisDate")).thenReturn("2024-11-21");

        // Mock the Statement
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockConnection.createStatement()).thenReturn(mockStatement);

        // Redirect System.out to capture printed output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Create the DAO and call the method
        CaseDAO_JDBC caseDAO = new CaseDAO_JDBC(mockConnection);
        caseDAO.getCaseByStudentId("STUDENT123");

        // Verify interactions
        verify(mockStatement).executeQuery("select * from posCase where studentId = STUDENT123");
        verify(mockResultSet, times(2)).next(); // Verify that next() was called twice

        // Verify the printed output
        String output = outContent.toString();
        assertTrue(output.contains("Fatal Fault in table definition: Found Duplicate key in table."));

        // Reset System.out
        System.setOut(System.out);
    }

    // @Test
    // public void testGetCaseByStudentId_NoMatchingRecord() throws Exception {
    //     // Mock ResultSet to simulate no matching record
    //     when(mockConnection.createStatement()).thenReturn(mockStatement);
    //     when(mockStatement.executeQuery("select * from posCase where studentId = 'STUDENT123'"))
    //         .thenReturn(mockResultSet);
    //     when(mockResultSet.next()).thenReturn(false); // No matching record

    //     // Redirect System.out to capture the printed message
    //     ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    //     System.setOut(new PrintStream(outContent));

    //     // Create the DAO and call the method
    //     CaseDAO_JDBC caseDAO = new CaseDAO_JDBC(mockConnection);
    //     Case result = caseDAO.getCaseByStudentId("STUDENT123");

    //     // Verify and assert
    //     assertNull(result); // No Case object should be returned
    //     assertTrue(outContent.toString().contains("There is no matching entry in the student table."));

    //     // Reset System.out
    //     System.setOut(System.out);
    // }

}
