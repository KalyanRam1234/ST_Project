package com.outbreak.Quarantine;

public class Quarantine {
    private String caseId;
    private String startDate, healthStatus;
    private String endDate;
    private String duration;
    
    public Quarantine( ) {}

    public Quarantine(String caseId, String startDate, String healthStatus, String duration, String endDate){
        this.caseId=caseId;
        this.startDate=startDate;
        this.healthStatus=healthStatus;
        this.duration=duration;
        this.endDate=endDate;
    }

    public String getcaseId() { return caseId; }
    public String getStartDate() { return startDate; }

    public String getStatus() { return healthStatus; }

    public String getEndDate() {return endDate;}

    public String getDuration() { return duration;}

}
