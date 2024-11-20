package com.outbreak.UseCases.useCase2;

public class useCase2 {
    private String studentId;
    private String Name, testDate, testResult,emailId, test_id;

    public useCase2(){}

    public useCase2(String studentId, String Name, String testDate,String test_id, String testResult, String emailId){
        this.studentId=studentId;
        this.Name=Name;
        this.testDate=testDate;
        this.test_id=test_id;
        this.testResult=testResult;
        this.emailId=emailId;
    }

    public String getstudentId() { return studentId;}
    public String getName() { return Name; }
    public String getTestDate() {return testDate;}
    public String getTestId() { return test_id;}
    public String getTestResult() { return testResult; }
    public String getEmailId() { return emailId; }
}
