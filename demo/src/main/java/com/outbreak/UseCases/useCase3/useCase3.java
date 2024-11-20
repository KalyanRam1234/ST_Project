package com.outbreak.UseCases.useCase3;

public class useCase3{
    private String studentId;
    private int doseNo;
    private String dateTaken, vaccineName, vaccinationStatus; 
    

    public useCase3(){}

    public useCase3(String studentId, int doseNo,String dateTaken, String vaccineName, String vaccinationStatus){
        this.studentId=studentId;
        this.doseNo=doseNo;
        this.dateTaken=dateTaken;
        this.vaccineName=vaccineName;
        this.vaccinationStatus=vaccinationStatus;
    }

    public String getStudentId() {return studentId;}
    public String getDate() {return dateTaken;}
    public String getvStatus() {return vaccinationStatus;}
    public String getvName() {return vaccineName;}
    public int getDoseNo() {return doseNo;}
}
