package com.outbreak.HostelRoom;

import java.util.ArrayList;

public interface HostelDAO {
    public HostelRoom getRoomDetails(String roomno);  // retrieve
    public int getHostelVacancy(String hostelType);
    public int getHostelCapacity(String hostelType);
    public ArrayList<HostelRoom> getEmptyHRooms();
    public void enterHostelRoom(String roomno, String roomType, int vacancy, int capacity, String hostelType); // insert
    public void deleteHostelRoom(String roomNo);   // delete
    public ArrayList<HostelRoom> getallRooms();
}
