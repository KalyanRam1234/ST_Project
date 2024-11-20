package com.outbreak.UseCases.useCase7;

import java.util.ArrayList;

public interface useCase7_DAO {
    public ArrayList<useCase7a> getInfectedStudentsList();
    public ArrayList<ArrayList<useCase7b>> getRooomatesOfInfectedStudents(ArrayList<useCase7a> uc7a); 
}
