package com.outbreak.UseCases.useCase6;

public class useCase6 {
    private String studentId;
    private String name, date, testId;
    private String qroomNo, healthStatus;

    public useCase6(){}

    public useCase6(String studentId, String name, String date, String testId, String qroomNo, String healthStatus){
        this.studentId=studentId;
        this.name=name;
        this.date=date;
        this.testId=testId;
        this.qroomNo=qroomNo;
        this.healthStatus=healthStatus;
    }

    public String getstudentId() { return studentId;}
    public String getName() { return name; }
    public String getDate() { return date; }
    public String getTestId() { return testId;}
    public String getqRoomNo() { return qroomNo;}
    public String getHealthStatus() { return healthStatus;}
}
