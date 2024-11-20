package com.outbreak.Doses;

public interface DosesDAO {
    public void enterDose(String studentId, int doseNo, String dateTaken, String vaccineName);
    public void deleteDose(String studentId, int doseNo);
    public Doses getDose(String studentId, int doseNo); 
}
