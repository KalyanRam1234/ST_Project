package com.outbreak.UseCases.useCase4;

import java.util.ArrayList;

public interface useCase4_DAO {
    public ArrayList<useCase4> getStudentDoses(String rollNo);
    public ArrayList<useCase4> getAllStudentDoses();
    public ArrayList<useCase4b> getVaccinationDetails();
    public ArrayList<useCase4b> getVaccinationDetailsBatch(String batch);
}
