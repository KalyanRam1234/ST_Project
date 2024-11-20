package com.outbreak.UseCases.useCase7;

public class useCase7b 
{
    private String studentId, name, vaccination_status, rtpcr_recent_result, rtpcr_date;

    public useCase7b(){}

    public useCase7b(String studentId, String name, String vaccination_status, String rtpcr_recent_result, String rtpcr_date)
    {
        this.studentId=studentId;
        this.name=name;
        this.vaccination_status=vaccination_status;
        this.rtpcr_recent_result=rtpcr_recent_result;
        this.rtpcr_date=rtpcr_date;
    }

    public String getstudentId() { return studentId;}
    public String getName() { return name; }
    public String getVaccinationStatus() { return vaccination_status; }
    public String getRTPCRResult() { return rtpcr_recent_result;}
    public String getRTPCRDate() { return rtpcr_date;}

    public void setstudentId(String studentId) { this.studentId=studentId;}
    public void setName(String name) { this.name=name; }
    public void setVaccinationStatus(String vaccination_status) { this.vaccination_status=vaccination_status; }
    public void setRTPCRResult(String rtpcr_recent_result) { this.rtpcr_recent_result=rtpcr_recent_result;}
    public void setRTPCRDate(String rtpcr_date) { this.rtpcr_date=rtpcr_date;}

}



