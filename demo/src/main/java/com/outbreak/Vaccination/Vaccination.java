package com.outbreak.Vaccination;

public class Vaccination {
    private String studentId;
    private int dosesTaken;
    private String vaccineStatus;
    private String certificate;

    public Vaccination(){}

    public Vaccination(String id, int doses, String status, String certif){
        studentId=id;
        dosesTaken=doses;
        vaccineStatus=status;
        certificate=certif;
    }

    public String getstudentId() { return studentId; }
    public int getdosesTaken(){ return dosesTaken; }
    public String getvaccineStatus() { return vaccineStatus; }
    public String getCertificate() { return certificate; }

}
