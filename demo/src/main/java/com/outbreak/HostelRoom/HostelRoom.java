package com.outbreak.HostelRoom;

public class HostelRoom {
    private String roomNo, roomType;
    private int capacity,vacancy;
    private String hostelType;

    public HostelRoom(){}

    public HostelRoom(String roomNo, String roomType, int capacity, int vacancy, String hostelType){
        this.roomNo=roomNo;
        this.roomType=roomType;
        this.capacity=capacity;
        this.vacancy=vacancy;
        this.hostelType=hostelType;
    }
    
    public String getroomNo() { return roomNo; }
    public String getroomType() { return roomType; }
    public int getCapacity() { return capacity; }
    public int getVacancy() { return vacancy; } 
    public String gethostelType() { return hostelType;}
}
