package com.outbreak.UseCases.useCase6;


public interface useCase6_DAO {
    public void updateInfectedDetails(String caseid, String qroomNo, String sdate, String edate, String healthStatus);
    public void removeInfectedDetails( String caseId );
}
