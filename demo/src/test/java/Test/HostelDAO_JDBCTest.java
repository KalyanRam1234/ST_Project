package Test;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.outbreak.HostelRoom.HostelDAO_JDBC;
import com.outbreak.HostelRoom.HostelRoom;
import com.outbreak.UseCases.useCase7.useCase7a;
import com.outbreak.UseCases.useCase7.useCase7b;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.*;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class HostelDAO_JDBCTest {

    private Connection mockConnection;
    private Statement mockStatement;
    private PreparedStatement mockPreparedStatement;
    private ResultSet mockResultSet;
    private HostelDAO_JDBC hostelDAO;

    @Before
    public void setUp() {
        mockConnection = mock(Connection.class);
        mockStatement = mock(Statement.class);
        mockPreparedStatement = mock(PreparedStatement.class);
        mockResultSet = mock(ResultSet.class);
        hostelDAO = new HostelDAO_JDBC(mockConnection);
    }

    @Test
    public void testGetRoomDetails() throws SQLException {
        String roomNo = "R101";

        // Mock SQL behavior
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        when(mockResultSet.getString("roomNo")).thenReturn(roomNo);
        when(mockResultSet.getString("roomType")).thenReturn("Quarantine");
        when(mockResultSet.getString("capacity")).thenReturn("4");
        when(mockResultSet.getString("vacancy")).thenReturn("2");
        when(mockResultSet.getString("hostelType")).thenReturn("Hostel");

        HostelRoom room = hostelDAO.getRoomDetails(roomNo);

        // Verify results
        assertNotNull(room);
        assertEquals("R101", room.getroomNo());
        assertEquals("Quarantine", room.getroomType());
        assertEquals(4, room.getCapacity());
        assertEquals(2, room.getVacancy());
        assertEquals("Hostel", room.gethostelType());

        // Verify SQL execution
        verify(mockStatement).executeQuery("select * from hostelRoom where roomNo= 'R101'");
    }

    @Test
    public void testGetRoomDetailsSQLException() throws SQLException {
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenThrow(new SQLException("Test SQL Exception"));

        HostelRoom room = hostelDAO.getRoomDetails("R101");

        // Verify behavior on exception
        assertNull(room);
    }

    @Test
    public void testGetHostelVacancy() throws SQLException {
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, true, false);
        when(mockResultSet.getString("vacancy")).thenReturn("2", "3");

        int vacancy = hostelDAO.getHostelVacancy("Hostel");

        // Verify results
        assertEquals(5, vacancy);

        // Verify SQL execution
        verify(mockStatement).executeQuery("select * from hostelRoom where roomType=\"Hostel\"");
    }

    @Test
    public void testGetHostelCapacity() throws SQLException {
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getString("capacity")).thenReturn("5");

        int capacity = hostelDAO.getHostelCapacity("Hostel");

        // Verify results
        assertEquals(5, capacity);

        // Verify SQL execution
        verify(mockStatement).executeQuery("select * from hostelRoom where roomType=\"Hostel\"");
    }

    @Test
    public void testGetAllRooms() throws SQLException {
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, true, false);
        when(mockResultSet.getString("roomNo")).thenReturn("R101", "R102");
        when(mockResultSet.getString("roomType")).thenReturn("Quarantine", "Quarantine");
        when(mockResultSet.getString("capacity")).thenReturn("4", "3");
        when(mockResultSet.getString("vacancy")).thenReturn("2", "1");
        when(mockResultSet.getString("hostelType")).thenReturn("Hostel", "Hostel");

        ArrayList<HostelRoom> rooms = hostelDAO.getallRooms();

        // Verify results
        assertEquals(2, rooms.size());
        assertEquals("R101", rooms.get(0).getroomNo());
        assertEquals(4, rooms.get(0).getCapacity());
    }

    @Test
    public void testEnterHostelRoom() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        hostelDAO.enterHostelRoom("R103", "Hostel", 2, 4, "Hostel");

        // Verify SQL and parameters
        verify(mockConnection).prepareStatement("insert into hostelRoom(roomno, roomType, capacity, vacancy, hostelType) values (?,?,?,?,?)");
        verify(mockPreparedStatement).setString(1, "R103");
        verify(mockPreparedStatement).setString(2, "Hostel");
        verify(mockPreparedStatement).setInt(3, 4);
        verify(mockPreparedStatement).setInt(4, 2);
        verify(mockPreparedStatement).setString(5, "Hostel");
        verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    public void testDeleteHostelRoom() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        hostelDAO.deleteHostelRoom("R103");

        // Verify SQL and parameters
        verify(mockConnection).prepareStatement("delete from hostelRoom where roomNo=?");
        verify(mockPreparedStatement).setString(1, "R103");
        verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    public void testSQLExceptionHandlingRoom() throws SQLException {

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

        hostelDAO.getRoomDetails("R103");
        assertTrue(outContent.toString().contains(expectedMessage));
        assertTrue(outContent.toString().contains(expectedState));
        assertTrue(outContent.toString().contains(expectedErrorCode));

        outContent.reset();

    }

    @Test
    public void testSQLExceptionHandlingVacancy() throws SQLException {

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

        hostelDAO.getHostelVacancy("Hostel");
        assertTrue(outContent.toString().contains(expectedMessage));
        assertTrue(outContent.toString().contains(expectedState));
        assertTrue(outContent.toString().contains(expectedErrorCode));

        outContent.reset();

    }

    @Test
    public void testSQLExceptionHandlingCapacity() throws SQLException {

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

        hostelDAO.getHostelCapacity("Hostel");
        assertTrue(outContent.toString().contains(expectedMessage));
        assertTrue(outContent.toString().contains(expectedState));
        assertTrue(outContent.toString().contains(expectedErrorCode));

        outContent.reset();

    }

    @Test
    public void testSQLExceptionHandlingAllRooms() throws SQLException {

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

        hostelDAO.getallRooms();
        assertTrue(outContent.toString().contains(expectedMessage));
        assertTrue(outContent.toString().contains(expectedState));
        assertTrue(outContent.toString().contains(expectedErrorCode));

        outContent.reset();

        hostelDAO.getEmptyHRooms();

        assertTrue(outContent.toString().contains(expectedMessage));
        assertTrue(outContent.toString().contains(expectedState));
        assertTrue(outContent.toString().contains(expectedErrorCode));
    }

    @Test
    public void testGetEmptyHRooms() throws SQLException {
        // Mock the database connection and related objects
        Connection mockConnection = mock(Connection.class);
        Statement mockStatement = mock(Statement.class);
        ResultSet mockResultSet = mock(ResultSet.class);

        // Define behavior for the mocked objects
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery("select * from hostelRoom where roomType=\"Quarantine\" AND vacancy>0")).thenReturn(mockResultSet);

        // Simulate rows in the result set
        when(mockResultSet.next()).thenReturn(true, true, false); // 2 rows, then no more rows
        when(mockResultSet.getString("roomNo")).thenReturn("R101", "R102");
        when(mockResultSet.getString("roomType")).thenReturn("Quarantine", "Quarantine");
        when(mockResultSet.getString("capacity")).thenReturn("4", "6");
        when(mockResultSet.getString("vacancy")).thenReturn("2", "1");
        when(mockResultSet.getString("hostelType")).thenReturn("TypeA", "TypeB");

        // Call the method under test
        HostelDAO_JDBC newHostelDAO= new HostelDAO_JDBC(mockConnection);
        ArrayList<HostelRoom> result = newHostelDAO.getEmptyHRooms();

        // Assertions to verify the correctness of the method
        assertNotNull(result);
        assertEquals(2, result.size()); // Expecting 2 rooms in the list

        // Verify the first room
        HostelRoom room1 = result.get(0);
        assertEquals("R101", room1.getroomNo());
        assertEquals("Quarantine", room1.getroomType());
        assertEquals(4, room1.getCapacity());
        assertEquals(2, room1.getVacancy());
        assertEquals("TypeA", room1.gethostelType());

        // Verify the second room
        HostelRoom room2 = result.get(1);
        assertEquals("R102", room2.getroomNo());
        assertEquals("Quarantine", room2.getroomType());
        assertEquals(6, room2.getCapacity());
        assertEquals(1, room2.getVacancy());
        assertEquals("TypeB", room2.gethostelType());

        // Verify interactions with the mocks
        verify(mockConnection, times(1)).createStatement();
        verify(mockStatement, times(1)).executeQuery("select * from hostelRoom where roomType=\"Quarantine\" AND vacancy>0");
        verify(mockResultSet, times(3)).next(); // Called 3 times (2 rows + 1 for false)
        verify(mockResultSet, atLeastOnce()).getString("roomNo");
        verify(mockResultSet, atLeastOnce()).getString("roomType");
        verify(mockResultSet, atLeastOnce()).getString("capacity");
        verify(mockResultSet, atLeastOnce()).getString("vacancy");
        verify(mockResultSet, atLeastOnce()).getString("hostelType");

    }

}
