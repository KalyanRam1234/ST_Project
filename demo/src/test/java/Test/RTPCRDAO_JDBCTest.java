package Test;

import java.sql.*;

import org.junit.Before;
import org.junit.Test;

import com.outbreak.Case.CaseDAO;
import com.outbreak.RTPCR.RTPCR;
import com.outbreak.RTPCR.RTPCRDAO_JDBC;

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

    // @Test
    // public void testEnterRTPCR_ValidStudent_NegativeResult() throws SQLException {
    //     RTPCR test = new RTPCR(null, "S123", "2024-11-20", "Negative", null);

    //     when(mockStatement.executeQuery("select count(*) as count from rtpcr")).thenReturn(mockResultSet);
    //     when(mockResultSet.next()).thenReturn(true);
    //     when(mockResultSet.getInt("count")).thenReturn(5);

    //     when(mockStatement.executeQuery("select studentId from posCase where studentId='S123'")).thenReturn(mockResultSet);
    //     when(mockResultSet.last()).thenReturn(false);

    //     when(mockStatement.executeQuery("select studentId from student where studentId='S123'")).thenReturn(mockResultSet);
    //     when(mockResultSet.last()).thenReturn(true);

    //     when(mockPreparedStatement.executeUpdate()).thenReturn(1);

    //     int result = dao.enterRTPCR(test);

    //     assertEquals(0, result);
    //     verify(mockPreparedStatement, times(1)).setString(1, "RT6");
    //     verify(mockPreparedStatement, times(1)).setString(2, "S123");
    //     verify(mockPreparedStatement, times(1)).setString(3, "2024-11-20");
    //     verify(mockPreparedStatement, times(1)).setInt(4, 0);
    //     verify(mockPreparedStatement, times(1)).setString(5, "NULL");
    // }

    // @Test
    // public void testEnterRTPCR_ValidStudent_PositiveResult() throws SQLException {
    //     RTPCR test = new RTPCR(null, "S123", "2024-11-20", "Positive", null);

    //     when(mockStatement.executeQuery("select count(*) as count from rtpcr")).thenReturn(mockResultSet);
    //     when(mockResultSet.next()).thenReturn(true);
    //     when(mockResultSet.getInt("count")).thenReturn(5);

    //     when(mockStatement.executeQuery("select studentId from posCase where studentId='S123'")).thenReturn(mockResultSet);
    //     when(mockResultSet.last()).thenReturn(false);

    //     when(mockStatement.executeQuery("select studentId from student where studentId='S123'")).thenReturn(mockResultSet);
    //     when(mockResultSet.last()).thenReturn(true);

    //     when(mockCaseDAO.getTotalCases()).thenReturn(10);
    //     when(mockPreparedStatement.executeUpdate()).thenReturn(1);

    //     int result = dao.enterRTPCR(test);

    //     assertEquals(0, result);
    //     verify(mockPreparedStatement, times(2)).executeUpdate();
    // }

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


    // @Test
    // public void testEnterRTPCR_SQLException() throws SQLException {
    //     RTPCR test = new RTPCR(null, "S123", "2024-11-20", "Positive", null);

    //     when(mockPreparedStatement.executeUpdate()).thenThrow(new SQLException("Database error"));

    //     int result = dao.enterRTPCR(test);

    //     assertEquals(1, result);
    // }
    
    @Test
    public void testDeleteRTPCR() throws Exception {
        String testId = "RT123";
    
        dao.deleteRTPCR(testId, null);
    
        verify(mockPreparedStatement, times(1)).setString(1, testId);
        verify(mockPreparedStatement, times(1)).executeUpdate();
    }
    
}
