package com.outbreak.Case;

public interface CaseDAO {
    public void enterNewCase(String caseId, String studentId, String qroomNo, String testId , String diagnosisDate);
    public void deleteCase(String caseId, String studentId);
    public Case getCaseByCaseId(String caseId);
    public Case getCaseByStudentId(String studentId); 
    public int getTotalCases();
}
