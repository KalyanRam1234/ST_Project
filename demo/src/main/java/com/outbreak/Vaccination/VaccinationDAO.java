package com.outbreak.Vaccination;

public interface VaccinationDAO {
    public void enterVaccinationStatus(String id, int doses, String status, String certif);
    public void deleteVaccinationStatusOfStudentId(String studentId);
    public Vaccination getVaccinationStatusByStudentId(String studentId);
    public void updateVaccinationStatusOfStudentId(String studentId, String status);
    public void updateVaccinationDosesOfStudentId(String studentId, int doses);
}
