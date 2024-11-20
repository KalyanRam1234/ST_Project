package com.outbreak.RTPCR;

public class RTPCR {
    private String testId, studentId, testDate;
    private String test_result, certificate;

    public RTPCR(){ }

    public RTPCR(String testId, String studentId, String testDate, String test_result, String certificate){
        this.testId=testId;
        this.studentId=studentId;
        this.testDate=testDate;
        this.test_result=test_result;
        this.certificate=certificate;
    } 

    public String gettestId() { return testId; }
    public String getstudentId() { return studentId; }
    public String gettestDate() { return testDate; }
    public String gettest_Result() { return test_result; }
    public String getCertificate() { return certificate; }
}
