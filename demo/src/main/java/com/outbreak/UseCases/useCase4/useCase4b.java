package com.outbreak.UseCases.useCase4;

public class useCase4b {
    private String studentId;
    private String name, dosesTaken, vaccination_status;

    public useCase4b(){}

    public useCase4b(String studentId, String name, String dosesTaken, String vaccination_status){
        this.studentId=studentId;
        this.name=name;
        this.dosesTaken=dosesTaken;
        this.vaccination_status=vaccination_status;
    }

    public String getstudentId() { return studentId;}
    public String getName() { return name; }
    public String getDosesTaken() { return dosesTaken; }
    public String getVaccinationStatus() { return vaccination_status;}    
}
