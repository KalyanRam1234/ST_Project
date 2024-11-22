package Test;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.outbreak.HostelRoom.HostelDAO_JDBC;
import com.outbreak.HostelRoom.HostelRoom;

import java.sql.*;
import java.util.ArrayList;

import static org.junit.Assert.*;
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
}
