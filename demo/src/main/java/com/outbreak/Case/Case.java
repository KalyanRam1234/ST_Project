package com.outbreak.Case;

public class Case{
    private String caseId, studentId, qroomNo;
    private String testId, diagnosisDate;

    public Case() { }

    public Case(String caseId, String studentId, String qroomNo, String testId , String diagnosisDate){
        this.caseId=caseId;
        this.studentId=studentId;
        this.qroomNo=qroomNo;
        this.testId=testId;
        this.diagnosisDate=diagnosisDate;
    }

    public String getCaseId() { return caseId;}
    public String getStudentId() { return studentId; }
    public String getQroom() { return qroomNo;}
    public String getTestId() { return testId; }
    public String getDiagnosisdate() {
        return diagnosisDate;
    }
}