package com.outbreak.UseCases.useCase5;

import java.util.ArrayList;

public interface useCase5_DAO {
    public ArrayList<useCase5> getInfectedList();
    public ArrayList<useCase5> getInfectedListByBatch(String batch);
    public void enterInfectedDetails(String studentId, String caseId, String name, String date, String testId, String qroomNo, String sdate, String edate, String healthStatus);
}
