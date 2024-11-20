package com.outbreak.UseCases.UseCase1;

import java.util.ArrayList;

public interface useCase1_DAO {
    public useCase1 getStudentRTPCR_Status(String rollNo, String Date);
    public ArrayList<useCase1> getStudentRTPCR_Status(String rollNo);
    public ArrayList<useCase1> getBatchRTPCR_Status(String batch);
    public ArrayList<useCase1> getCollegeRTPCR_Status(String Date);
}
