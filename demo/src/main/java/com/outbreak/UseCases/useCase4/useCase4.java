package com.outbreak.UseCases.useCase4;

public class useCase4 {
    private String studentId;
    private String name, dateTaken, vaccineName;
    private int doseNo;

    public useCase4(){}

    public useCase4(String studentId, String name, int doseNo, String dateTaken, String vaccineName){
        this.studentId=studentId;
        this.name=name;
        this.doseNo=doseNo;
        this.dateTaken=dateTaken;
        this.vaccineName=vaccineName;
    }

    public String getstudentId() { return studentId;}
    public String getName() { return name; }
    public String getDate() { return dateTaken; }
    public int getdoseNo() { return doseNo;}
    public String getvaccineName() { return vaccineName;}
}
