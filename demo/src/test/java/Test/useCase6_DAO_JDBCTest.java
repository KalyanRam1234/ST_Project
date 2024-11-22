package Test;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.outbreak.UseCases.useCase6.useCase6;
import com.outbreak.UseCases.useCase6.useCase6_DAO_JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
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

    
}
