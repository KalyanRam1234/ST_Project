package Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.outbreak.Case.CaseDAO;
import com.outbreak.Case.CaseDAO_JDBC;
import com.outbreak.RTPCR.RTPCR;
import com.outbreak.RTPCR.RTPCRDAO_JDBC;
import com.outbreak.UseCases.useCase6.useCase6;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class RTPCRDAO_JDBCTest {
    private Connection mockConnection;
    private Statement mockStatement;
    private PreparedStatement mockPreparedStatement;
    private ResultSet mockResultSet;
    private RTPCRDAO_JDBC dao;
    private CaseDAO mockCaseDAO;

    @Before
    public void setup() throws Exception {
        mockConnection = mock(Connection.class);
        mockStatement = mock(Statement.class);
        mockPreparedStatement = mock(PreparedStatement.class);
        mockResultSet = mock(ResultSet.class);
        mockCaseDAO = mock(CaseDAO.class);

        dao = new RTPCRDAO_JDBC(mockConnection);

        dao.setCaseDao(mockCaseDAO);
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
    }

    @Test
    public void testGetRTPCRByStudentId() throws Exception {
        String studentId = "S123";
        String sql = "select * from rtpcr where studentId = " + studentId;
        when(mockStatement.executeQuery(sql)).thenReturn(mockResultSet);

        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getString("studentId")).thenReturn(studentId);
        when(mockResultSet.getString("testDate")).thenReturn("2024-11-20");
        when(mockResultSet.getString("test_result")).thenReturn("1");
        when(mockResultSet.getString("certif")).thenReturn("cert123");
        when(mockResultSet.getString("testId")).thenReturn("RT1");

        ArrayList<RTPCR> results = dao.getRTPCRByStudentId(studentId);

        assertEquals(1, results.size());
        RTPCR rtpcr = results.get(0);
        assertEquals("S123", rtpcr.getstudentId());
        assertEquals("Positive", rtpcr.gettest_Result());
    }
    @Test
    public void testGetRTPCRByStudentId_Date() throws Exception {
        String studentId = "S123";
        String testDate = "2024-11-20";
        String sql = "select * from rtpcr where studentId = " + studentId + " AND testDate = " + testDate;

        when(mockStatement.executeQuery(sql)).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getString("studentId")).thenReturn(studentId);
        when(mockResultSet.getString("testDate")).thenReturn(testDate);
        when(mockResultSet.getString("test_result")).thenReturn("0");
        when(mockResultSet.getString("certif")).thenReturn("cert123");
        when(mockResultSet.getString("testId")).thenReturn("RT2");

        RTPCR result = dao.getRTPCRByStudentId_Date(studentId, testDate);

        assertNotNull(result);
        assertEquals("S123", result.getstudentId());
        assertEquals("Negative", result.gettest_Result());
    }

    @Test
    public void testGetRTPCR_positive() throws Exception {
        String sql = "select * from rtpcr where test_result=1";

        when(mockStatement.executeQuery(sql)).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getString("studentId")).thenReturn("S123");
        when(mockResultSet.getString("testDate")).thenReturn("2024-11-20");
        when(mockResultSet.getString("test_result")).thenReturn("1");
        when(mockResultSet.getString("certif")).thenReturn("cert123");
        when(mockResultSet.getString("testId")).thenReturn("RT3");

        ArrayList<RTPCR> results = dao.getRTPCR_postive();

        assertEquals(1, results.size());
        assertEquals("Positive", results.get(0).gettest_Result());
    }


    @Test
    public void testGetRTPCR_negative() throws Exception {
        String sql = "select * from rtpcr where test_result=0";

        when(mockStatement.executeQuery(sql)).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getString("studentId")).thenReturn("S123");
        when(mockResultSet.getString("testDate")).thenReturn("2024-11-20");
        when(mockResultSet.getString("test_result")).thenReturn("0");
        when(mockResultSet.getString("certif")).thenReturn("cert123");
        when(mockResultSet.getString("testId")).thenReturn("RT3");

        ArrayList<RTPCR> results = dao.getRTPCR_negative();

        assertEquals(1, results.size());
        assertEquals("Negative", results.get(0).gettest_Result());
    }

    @Test
    public void testGetRTPCRByStudentId_LastDate() throws Exception {

        String id = "S123";
        String sql = "select * from rtpcr where studentId = " + id + " AND testDate = (select max(testDate) from rtpcr where studentId= " + id + ")";

        when(mockStatement.executeQuery(sql)).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getString("studentId")).thenReturn("S123");
        when(mockResultSet.getString("testDate")).thenReturn("2024-11-20");
        when(mockResultSet.getString("test_result")).thenReturn("0");
        when(mockResultSet.getString("certif")).thenReturn("cert123");
        when(mockResultSet.getString("testId")).thenReturn("RT3");

        RTPCR results = dao.getRTPCRByStudentId_LastDate(id);

        assertEquals("Negative", results.gettest_Result());
    }


    @Test
    public void testEnterRTPCR_AlreadyInPositiveCases() throws SQLException {
        RTPCR test = new RTPCR(null, "S123", "2024-11-20", "Positive", null);

        when(mockStatement.executeQuery("select count(*) as count from rtpcr")).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("count")).thenReturn(5);

        when(mockStatement.executeQuery("select studentId from posCase where studentId='S123'")).thenReturn(mockResultSet);
        when(mockResultSet.last()).thenReturn(true);

        when(mockStatement.executeQuery("select studentId from student where studentId='S123'")).thenReturn(mockResultSet);
        when(mockResultSet.last()).thenReturn(true);

        when(mockCaseDAO.getTotalCases()).thenReturn(10);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        int result = dao.enterRTPCR(test);

        assertEquals(0, result);
    }


    @Test
public void testEnterRTPCR_PositiveResult() throws SQLException {
    // Mock database connections and statements
    Connection mockConnection = mock(Connection.class);
    Statement mockStatement = mock(Statement.class);
    PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
    ResultSet mockResultSet = mock(ResultSet.class);
    ResultSet mockCaseResultSet = mock(ResultSet.class); // Mocked ResultSet for CaseDAO

    // Create instances of DAO with mocks
    RTPCRDAO_JDBC newdao = new RTPCRDAO_JDBC(mockConnection);
    CaseDAO_JDBC newCaseDao = mock(CaseDAO_JDBC.class); // Mock CaseDAO
    newdao.setCaseDao(newCaseDao);

    // Mock behavior for RTPCRDAO queries
    when(mockConnection.createStatement()).thenReturn(mockStatement);
    when(mockStatement.executeQuery("select count(*) as count from rtpcr")).thenReturn(mockResultSet);
    when(mockResultSet.next()).thenReturn(true);
    when(mockResultSet.getInt("count")).thenReturn(5); // Existing count

    when(mockStatement.executeQuery("select studentId from posCase where studentId='S123'")).thenReturn(mockResultSet);
    when(mockResultSet.last()).thenReturn(true);
    when(mockResultSet.getRow()).thenReturn(1); // Student exists in posCase

    when(mockStatement.executeQuery("select studentId from student where studentId='S123'")).thenReturn(mockResultSet);
    when(mockResultSet.getRow()).thenReturn(1); // Valid student

    // Mock behavior for CaseDAO methods
    when(newCaseDao.getTotalCases()).thenReturn(5); // Simulate 5 total cases

    // Mock prepared statement behavior
    when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

    // Create RTPCR test input
    RTPCR test = new RTPCR("RT6", "S123", "2023-11-15", "Positive", "NULL");

    // Call the method under test
    int result = newdao.enterRTPCR(test);

    // Verify the results
    assertEquals(result, 0);

    // Verify SQL execution for prepared statements
    verify(mockPreparedStatement, times(3)).executeUpdate();
    verify(mockPreparedStatement, atLeastOnce()).setString(1, "RT6");
    verify(mockPreparedStatement, atLeastOnce()).setString(2, "S123");
    verify(mockPreparedStatement, atLeastOnce()).setString(3, "2023-11-15");
    verify(mockPreparedStatement, atLeastOnce()).setInt(4, 1);
    verify(mockPreparedStatement, atLeastOnce()).setString(5, "NULL");

    // Verify interaction with CaseDAO
    verify(newCaseDao, times(1)).getTotalCases();
}


@Test
public void testEnterRTPCR_CountIsZero() throws SQLException {
    // Mock database connections and statements
    Connection mockConnection = mock(Connection.class);
    Statement mockStatement = mock(Statement.class);
    PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
    ResultSet mockResultSet = mock(ResultSet.class);
    CaseDAO_JDBC newCaseDao = mock(CaseDAO_JDBC.class); // Mock CaseDAO

    RTPCRDAO_JDBC newdao = new RTPCRDAO_JDBC(mockConnection);
    newdao.setCaseDao(newCaseDao);

    // Mock behavior for RTPCRDAO queries
    when(mockConnection.createStatement()).thenReturn(mockStatement);
    when(mockStatement.executeQuery("select count(*) as count from rtpcr")).thenReturn(mockResultSet);
    when(mockResultSet.next()).thenReturn(true);
    when(mockResultSet.getInt("count")).thenReturn(0); // No records in RTPCR table

    when(mockStatement.executeQuery("select studentId from posCase where studentId='S123'")).thenReturn(mockResultSet);
    when(mockResultSet.last()).thenReturn(false); // Student not in posCase

    when(mockStatement.executeQuery("select studentId from student where studentId='S123'")).thenReturn(mockResultSet);
    when(mockResultSet.getRow()).thenReturn(1); // Valid student

    // Mock prepared statement behavior
    when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

    // Create RTPCR test input
    RTPCR test = new RTPCR("RT1", "S123", "2023-11-15", "Positive", "NULL");

    // Call the method under test
    int result = newdao.enterRTPCR(test);

    // Verify the results
    assertEquals(result, 0); // Assuming the method returns 1 for success

    // Verify SQL execution for prepared statements
    verify(mockPreparedStatement, times(3)).executeUpdate(); // insert into rtpcr and posCase
    verify(mockPreparedStatement, atLeastOnce()).setString(1, "RT1");
    verify(mockPreparedStatement, atLeastOnce()).setString(2, "S123");
    verify(mockPreparedStatement, atLeastOnce()).setString(3, "2023-11-15");

}

@Test
public void testEnterRTPCR_CountIsOne() throws SQLException {
    // Mock database connections and statements
    Connection mockConnection = mock(Connection.class);
    Statement mockStatement = mock(Statement.class);
    PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
    ResultSet mockResultSet = mock(ResultSet.class);
    CaseDAO_JDBC newCaseDao = mock(CaseDAO_JDBC.class); // Mock CaseDAO

    RTPCRDAO_JDBC newdao = new RTPCRDAO_JDBC(mockConnection);
    newdao.setCaseDao(newCaseDao);

    // Mock behavior for RTPCRDAO queries
    when(mockConnection.createStatement()).thenReturn(mockStatement);
    when(mockStatement.executeQuery("select count(*) as count from rtpcr")).thenReturn(mockResultSet);
    when(mockResultSet.next()).thenReturn(true);
    when(mockResultSet.getInt("count")).thenReturn(1); // One existing record in RTPCR table

    when(mockStatement.executeQuery("select studentId from posCase where studentId='S123'")).thenReturn(mockResultSet);
    when(mockResultSet.last()).thenReturn(false); // Student not in posCase

    when(mockStatement.executeQuery("select studentId from student where studentId='S123'")).thenReturn(mockResultSet);
    when(mockResultSet.getRow()).thenReturn(1); // Valid student

    // Mock prepared statement behavior
    when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

    // Mock behavior for CaseDAO
    when(newCaseDao.getTotalCases()).thenReturn(1); // Simulate 1 total case

    // Create RTPCR test input
    RTPCR test = new RTPCR("RT2", "S123", "2023-11-16", "Positive", "NULL");

    // Call the method under test
    int result = newdao.enterRTPCR(test);

    // Verify the results
    assertEquals(result, 0); // Assuming the method returns 1 for success

    // Verify SQL execution for prepared statements
    verify(mockPreparedStatement, times(3)).executeUpdate(); // insert into rtpcr and posCase
    verify(mockPreparedStatement, atLeastOnce()).setString(1, "RT2");
    verify(mockPreparedStatement, atLeastOnce()).setString(2, "S123");
    verify(mockPreparedStatement, atLeastOnce()).setString(3, "2023-11-16");
    verify(mockPreparedStatement, atLeastOnce()).setString(5, "NULL");

    // Verify interaction with CaseDAO
    verify(newCaseDao, times(1)).getTotalCases();
}


@Test
public void testEnterRTPCR_InvalidStudentId() throws SQLException {
    // Mock database connections and statements
    Connection mockConnection = mock(Connection.class);
    Statement mockStatement = mock(Statement.class);
    PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
    ResultSet mockResultSet = mock(ResultSet.class);
    ResultSet mockCaseResultSet = mock(ResultSet.class); // Mocked ResultSet for CaseDAO

    // Create instances of DAO with mocks
    RTPCRDAO_JDBC newdao = new RTPCRDAO_JDBC(mockConnection);
    CaseDAO_JDBC newCaseDao = mock(CaseDAO_JDBC.class); // Mock CaseDAO
    newdao.setCaseDao(newCaseDao);

    // Mock behavior for RTPCRDAO queries
    when(mockConnection.createStatement()).thenReturn(mockStatement);
    when(mockStatement.executeQuery("select count(*) as count from rtpcr")).thenReturn(mockResultSet);
    when(mockResultSet.next()).thenReturn(true);
    when(mockResultSet.getInt("count")).thenReturn(5); // Existing count

    when(mockStatement.executeQuery("select studentId from posCase where studentId='S999'")).thenReturn(mockResultSet);
    when(mockResultSet.last()).thenReturn(true);
    when(mockResultSet.getRow()).thenReturn(1); // Student exists in posCase

    // Mock behavior for invalid student ID check
    when(mockStatement.executeQuery("select studentId from student where studentId='S999'")).thenReturn(mockResultSet);
    when(mockResultSet.last()).thenReturn(true);
    when(mockResultSet.getRow()).thenReturn(0); 

    // Mock behavior for CaseDAO methods
    when(newCaseDao.getTotalCases()).thenReturn(5); // Simulate 5 total cases

    // Mock prepared statement behavior
    when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

    // Create RTPCR test input
    RTPCR test = new RTPCR("RT6", "S999", "2023-11-15", "Negative", "NULL");

    // Call the method under test
    int result = newdao.enterRTPCR(test);

    // Verify the results
    assertEquals(result, 0);

    // Verify SQL execution
    verify(mockPreparedStatement, never()).executeUpdate(); // Ensure no insert is executed
    verify(mockStatement, times(1)).executeQuery("select studentId from student where studentId='S999'");
    verify(mockResultSet, atLeastOnce()).last(); // Verify ResultSet navigation
    verify(mockResultSet, atLeastOnce()).getRow(); // Verify row count retrieval
}



    
    @Test
    public void testDeleteRTPCR() throws Exception {
        String testId = "RT123";
    
        dao.deleteRTPCR(testId, null);
    
        verify(mockPreparedStatement, times(1)).setString(1, testId);
        verify(mockPreparedStatement, times(1)).executeUpdate();
    }
    
    @Test
    public void testSQLExceptionHandlingRTPCRTEST() throws SQLException {

         // Simulating SQLException for statement and result set operations
        SQLException testException = new SQLException("Test SQL Exception", "TestState", 999);
        Mockito.when(mockConnection.createStatement()).thenThrow(testException);
        // Mockito.when(mockResultSet.getString(Mockito.anyString())).thenThrow(testException);

         // Capture output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Expected output strings
        String expectedMessage = "SQLException: Test SQL Exception";
        String expectedState = "SQLState: TestState";
        String expectedErrorCode = "VendorError: 999";

        dao.getRTPCRByStudentId("S1");
        assertTrue(outContent.toString().contains(expectedMessage));
        assertTrue(outContent.toString().contains(expectedState));
        assertTrue(outContent.toString().contains(expectedErrorCode));

        outContent.reset();

        dao.getRTPCRByStudentId_Date("S1","2023-10-01");
        assertTrue(outContent.toString().contains(expectedMessage));
        assertTrue(outContent.toString().contains(expectedState));
        assertTrue(outContent.toString().contains(expectedErrorCode));

        outContent.reset();

        dao.getRTPCR_postive();
        assertTrue(outContent.toString().contains(expectedMessage));
        assertTrue(outContent.toString().contains(expectedState));
        assertTrue(outContent.toString().contains(expectedErrorCode));

        outContent.reset();

        dao.getRTPCR_negative();
        assertTrue(outContent.toString().contains(expectedMessage));
        assertTrue(outContent.toString().contains(expectedState));
        assertTrue(outContent.toString().contains(expectedErrorCode));

        outContent.reset();

        dao.getRTPCRByStudentId_LastDate("S1");
        assertTrue(outContent.toString().contains(expectedMessage));
        assertTrue(outContent.toString().contains(expectedState));
        assertTrue(outContent.toString().contains(expectedErrorCode));

        outContent.reset();
    }

    @Test
    public void testGetRTPCRByStudentId_ValidId() throws SQLException {
        // Mock the database connection and related objects
        Connection mockConnection = mock(Connection.class);
        Statement mockStatement = mock(Statement.class);
        ResultSet mockResultSet = mock(ResultSet.class);

        // Mock the DAO with the mocked connection
        RTPCRDAO_JDBC daonew = new RTPCRDAO_JDBC(mockConnection);

        // Define behavior for the mocked objects
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery("select * from rtpcr where studentId = 123")).thenReturn(mockResultSet);

        // Simulate rows in the result set
        when(mockResultSet.next()).thenReturn(true, true, false); // 2 rows, then no more rows
        when(mockResultSet.getString("studentId")).thenReturn("123", "123");
        when(mockResultSet.getString("testDate")).thenReturn("2023-11-01", "2023-11-15");
        when(mockResultSet.getString("test_result")).thenReturn("1", "0"); // Positive, Negative
        when(mockResultSet.getString("certif")).thenReturn("Cert1", "Cert2");
        when(mockResultSet.getString("testId")).thenReturn("RT1", "RT2");

        // Call the method under test
        ArrayList<RTPCR> result = daonew.getRTPCRByStudentId("123");

        // Assertions to verify the correctness of the method
        assertNotNull(result);
        assertEquals(2, result.size()); // Expecting 2 tests in the list

        // Verify the first RTPCR entry
        RTPCR rtpcr1 = result.get(0);
        assertEquals("123", rtpcr1.getstudentId());
        assertEquals("2023-11-01", rtpcr1.gettestDate());
        assertEquals("Positive", rtpcr1.gettest_Result());
        assertEquals("Cert1", rtpcr1.getCertificate());
        assertEquals("RT1", rtpcr1.gettestId());

        // Verify the second RTPCR entry
        RTPCR rtpcr2 = result.get(1);
        assertEquals("123", rtpcr2.getstudentId());
        assertEquals("2023-11-15", rtpcr2.gettestDate());
        assertEquals("Negative", rtpcr2.gettest_Result());
        assertEquals("Cert2", rtpcr2.getCertificate());
        assertEquals("RT2", rtpcr2.gettestId());

        // Verify interactions with the mocks
        verify(mockConnection, times(1)).createStatement();
        verify(mockStatement, times(1)).executeQuery("select * from rtpcr where studentId = 123");
        verify(mockResultSet, times(3)).next(); // Called 3 times (2 rows + 1 for false)
        verify(mockResultSet, atLeastOnce()).getString("studentId");
        verify(mockResultSet, atLeastOnce()).getString("testDate");
        verify(mockResultSet, atLeastOnce()).getString("test_result");
        verify(mockResultSet, atLeastOnce()).getString("certif");
        verify(mockResultSet, atLeastOnce()).getString("testId");

    }


    @Test
public void testDeleteRTPCR_ByTestId() throws SQLException {
    // Mock the database connection and prepared statement
    Connection mockConnection = mock(Connection.class);
    PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);

    // Create instance of the DAO
    RTPCRDAO_JDBC dao = new RTPCRDAO_JDBC(mockConnection);

    // Define mock behavior for the prepared statement
    when(mockConnection.prepareStatement("delete from rtpcr where testId=?")).thenReturn(mockPreparedStatement);

    // Call the method under test
    dao.deleteRTPCR("RT1", null);

    // Verify that the correct query was prepared
    verify(mockConnection, times(1)).prepareStatement("delete from rtpcr where testId=?");

    // Verify that the correct parameter was set
    verify(mockPreparedStatement, times(1)).setString(1, "RT1");

    // Verify that executeUpdate was called
    verify(mockPreparedStatement, times(1)).executeUpdate();

    // Verify that the PreparedStatement was closed
    verify(mockPreparedStatement, times(1)).close();
}

@Test
public void testDeleteRTPCR_ByStudentId() throws SQLException {
    // Mock the database connection and prepared statement
    Connection mockConnection = mock(Connection.class);
    PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);

    // // Create instance of the DAO
    RTPCRDAO_JDBC newdao = new RTPCRDAO_JDBC(mockConnection);

    // Define mock behavior for the prepared statement
    when(mockConnection.prepareStatement("delete from rtpcr where studentId=?")).thenReturn(mockPreparedStatement);

    // Call the method under test
    newdao.deleteRTPCR(null, "S123");

    // Verify that the correct query was prepared
    verify(mockConnection, times(1)).prepareStatement("delete from rtpcr where studentId=?");

    // Verify that the correct parameter was set
    verify(mockPreparedStatement, times(1)).setString(1, "S123");

    // Verify that executeUpdate was called
    verify(mockPreparedStatement, times(1)).executeUpdate();

    // Verify that the PreparedStatement was closed
    verify(mockPreparedStatement, times(1)).close();
}

@Test
public void testDeleteRTPCR_InvalidInputs() throws SQLException {
    // Mock the database connection and prepared statement
    Connection mockConnection = mock(Connection.class);

    // Create instance of the DAO
    RTPCRDAO_JDBC dao = new RTPCRDAO_JDBC(mockConnection);

    // Call the method under test with both null testId and studentId
    dao.deleteRTPCR(null, null);

    // Verify that no PreparedStatement was created or executed
    verify(mockConnection, never()).prepareStatement(anyString());
}

}
