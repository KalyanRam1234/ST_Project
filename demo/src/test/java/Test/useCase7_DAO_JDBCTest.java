package Test;

import com.outbreak.UseCases.useCase7.useCase7_DAO_JDBC;
import com.outbreak.UseCases.useCase7.useCase7a;
import com.outbreak.UseCases.useCase7.useCase7b;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class useCase7_DAO_JDBCTest {
    private useCase7_DAO_JDBC dao;
    private Connection mockConnection;
    private Statement mockStatement;
    private ResultSet mockResultSet;

    @Before
    public void setUp() throws SQLException {
        mockConnection = mock(Connection.class);
        mockStatement = mock(Statement.class);
        mockResultSet = mock(ResultSet.class);
        dao = new useCase7_DAO_JDBC(mockConnection);
    }

    @Test
    public void testGetInfectedStudentsList() throws SQLException {
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        when(mockResultSet.getString("studentId")).thenReturn("1");
        when(mockResultSet.getString("Name")).thenReturn("John Doe");
        when(mockResultSet.getString("caseId")).thenReturn("C1");
        when(mockResultSet.getString("hroomNo")).thenReturn("101");
        when(mockResultSet.getString("qroomNo")).thenReturn("201");

        ArrayList<useCase7a> result = dao.getInfectedStudentsList();
        assertEquals(1, result.size());
        assertEquals("1", result.get(0).getstudentId());
        assertEquals("John Doe", result.get(0).getName());
    }

    @Test
    public void testGetRooomatesOfInfectedStudents() throws SQLException {
        ArrayList<useCase7a> infectedStudents = new ArrayList<useCase7a>();
        infectedStudents.add(new useCase7a("1", "John Doe", "C1", "101", "201", "Healthy"));

        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        when(mockResultSet.getString("studentId")).thenReturn("2");
        when(mockResultSet.getString("name")).thenReturn("Jane Doe");
        when(mockResultSet.getString("vaccination_status")).thenReturn("Vaccinated");
        when(mockResultSet.getString("rtpcr_recent_result")).thenReturn("Negative");
        when(mockResultSet.getString("rtpcr_date")).thenReturn("2023-10-01");

        ArrayList<ArrayList<useCase7b>> result = dao.getRooomatesOfInfectedStudents(infectedStudents);
        assertEquals(1, result.size());
        assertEquals(1, result.get(0).size());
        assertEquals("2", result.get(0).get(0).getstudentId());
        assertEquals("Jane Doe", result.get(0).get(0).getName());
    }

    @Test
    public void testGetInfob() throws SQLException {
        when(mockResultSet.getString("studentId")).thenReturn("2");
        when(mockResultSet.getString("name")).thenReturn("Jane Doe");
        when(mockResultSet.getString("vaccination_status")).thenReturn("Vaccinated");
        when(mockResultSet.getString("rtpcr_recent_result")).thenReturn("Negative");
        when(mockResultSet.getString("rtpcr_date")).thenReturn("2023-10-01");

        useCase7b result = dao.getInfob(mockResultSet);
        assertEquals("2", result.getstudentId());
        assertEquals("Jane Doe", result.getName());
    }

    @Test
    public void testGetInfob1() throws SQLException {
        useCase7b student = new useCase7b();
        when(mockResultSet.getString("rtpcr_recent_result")).thenReturn("Negative");
        when(mockResultSet.getString("rtpcr_date")).thenReturn("2023-10-01");

        useCase7b result = dao.getInfob1(mockResultSet, student);
        assertEquals("Negative", result.getRTPCRResult());
        assertEquals("2023-10-01", result.getRTPCRDate());
    }

    @Test
    public void testSQLExceptionHandlingDetailed() throws SQLException {
        // Mocking Connection and Statement objects
        Connection mockConnection = Mockito.mock(Connection.class);
        ResultSet mockResultSet = Mockito.mock(ResultSet.class);

        // Simulating SQLException for statement and result set operations
        SQLException testException = new SQLException("Test SQL Exception", "TestState", 999);
        Mockito.when(mockConnection.createStatement()).thenThrow(testException);
        Mockito.when(mockResultSet.getString(Mockito.anyString())).thenThrow(testException);

        // Instantiate DAO with mocked connection
        useCase7_DAO_JDBC dao = new useCase7_DAO_JDBC(mockConnection);

        // Capture output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Expected output strings
        String expectedMessage = "SQLException: Test SQL Exception";
        String expectedState = "SQLState: TestState";
        String expectedErrorCode = "VendorError: 999";

        // Test getInfectedStudentsList
        dao.getInfectedStudentsList();
        assertTrue(outContent.toString().contains(expectedMessage));
        assertTrue(outContent.toString().contains(expectedState));
        assertTrue(outContent.toString().contains(expectedErrorCode));

        // Clear output for the next test
        outContent.reset();

        // Test getRooomatesOfInfectedStudents
        ArrayList<useCase7a> dummyInfectedList = new ArrayList<useCase7a>();
        dummyInfectedList.add(new useCase7a("1", "Dummy", "case1", "H101", "Q101", "null"));
        dao.getRooomatesOfInfectedStudents(dummyInfectedList);
        assertTrue(outContent.toString().contains(expectedMessage));
        assertTrue(outContent.toString().contains(expectedState));
        assertTrue(outContent.toString().contains(expectedErrorCode));

        // Clear output for the next test
        outContent.reset();

        // Test getInfoa
        dao.getInfoa(mockResultSet);
        assertTrue(outContent.toString().contains(expectedMessage));
        assertTrue(outContent.toString().contains(expectedState));
        assertTrue(outContent.toString().contains(expectedErrorCode));

        outContent.reset();

        // Test getInfob
        dao.getInfob(mockResultSet);
        assertTrue(outContent.toString().contains(expectedMessage));
        assertTrue(outContent.toString().contains(expectedState));
        assertTrue(outContent.toString().contains(expectedErrorCode));

        outContent.reset();

        // Test getInfob1
        dao.getInfob1(mockResultSet, new useCase7b());
        assertTrue(outContent.toString().contains(expectedMessage));
        assertTrue(outContent.toString().contains(expectedState));
        assertTrue(outContent.toString().contains(expectedErrorCode));

        // Reset the system output
        System.setOut(System.out);
    }
}