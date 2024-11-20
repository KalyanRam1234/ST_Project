package com.outbreak.UseCases.UseCase1;

public class useCase1 {
    private String studentId, roomNo, gender;
    private String Name, testDate, testResult,emailId, test_id;

    public useCase1(){}

    public useCase1(String studentId, String Name, String roomNo, String gender, String testDate,String test_id, String testResult, String emailId){
        this.studentId=studentId;
        this.Name=Name;
        this.roomNo=roomNo;
        this.gender=gender;
        this.testDate=testDate;
        this.test_id=test_id;
        this.testResult=testResult;
        this.emailId=emailId;
    }

    public String getstudentId() { return studentId;}
    public String getName() { return Name; }
    public String getDate() { return testDate; }
    public String getRoomNo() { return roomNo;}
    public String getTestId() { return test_id;}
    public String getTestResult() { return testResult; }
    public String getEmailId() { return emailId; }
}
