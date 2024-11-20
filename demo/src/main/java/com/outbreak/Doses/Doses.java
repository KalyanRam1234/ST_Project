package com.outbreak.Doses;

public class Doses {
    private String studentId;
    private int doseNo;
    private String dateTaken, vaccineName;

    public Doses(){}

    public Doses(String studentId, int doseNo, String dateTaken, String vaccineName ){
        this.studentId=studentId;
        this.doseNo=doseNo;
        this.dateTaken=dateTaken;
        this.vaccineName=vaccineName;
    }

    public String getStudentId() {return studentId;}

    public int getdoseNo() { return doseNo; }
    public String getdateTaken(){ return dateTaken; }
    public String getvaccineName() { return vaccineName;}
}
