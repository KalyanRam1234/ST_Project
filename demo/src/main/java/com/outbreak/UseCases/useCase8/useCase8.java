package com.outbreak.UseCases.useCase8;

public class useCase8 {
    private String studentId, name, roomNo, hostelType;
    private int Capacity, Vacancy;

    public useCase8(){}

    public useCase8(String studentId, String name, String roomNo, int Capacity, int Vacancy, String hostelType){
        this.studentId=studentId;
        this.name=name;
        this.roomNo=roomNo;
        this.Capacity=Capacity;
        this.Vacancy=Vacancy;
        this.hostelType=hostelType;
    }

    public String getstudentId() { return studentId;}
    public String getName() { return name; }
    public String getRoomNo() {return roomNo; }
    public int getCapacity() { return Capacity;}
    public int getVacancy() {return Vacancy;}
    public String getType() { return hostelType;}
}
