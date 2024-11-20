package com.outbreak.UseCases.useCase7;

public class useCase7a {
    private String studentId, name, caseId, hroomNo, qroomNo, healthStatus;

    public useCase7a(){}

    public useCase7a(String studentId, String name, String caseId, String hroomNo, String qroomNo, String healthStatus){
        this.studentId=studentId;
        this.name=name;
        this.caseId=caseId;
        this.hroomNo=hroomNo;
        this.qroomNo=qroomNo;
        this.healthStatus=healthStatus;
    }

    public String getstudentId() { return studentId;}
    public String getName() { return name; }
    public String getCaseId() { return caseId; }
    public String gethroomNo() { return hroomNo;}
    public String getqRoomNo() { return qroomNo;}
    public String getHealthStatus() { return healthStatus;}
}
