package com.outbreak.Quarantine;

import java.util.ArrayList;

public interface QuarantineDAO {
    public Quarantine getCaseDetails(String caseid);
    public ArrayList<Quarantine> getQuarantineByStartDate(String startDate);
    public ArrayList<Quarantine> getQuarantineByEndDate(String endDate);  
    public void enterQuarantineCase(String caseId, String startDate, String healthStatus, String duration, String endDate); 
    public void removeQuarantineCase(String caseId);
    public void updateQuarantineHealthStatus(String caseId, String status);
    public void updateQuarantineEndDate(String caseId, String endDate);
}
