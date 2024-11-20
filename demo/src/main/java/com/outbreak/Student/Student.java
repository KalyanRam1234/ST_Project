package com.outbreak.Student;

public class Student {
    private String id,fname,lname, email_id;
    private String gender;
    private String  date;
    private String branch, roomno;

    public Student(){}

    public Student(String id, String fname, String lname, String date, String email_id, String gender, String branch, String roomno){
        this.id=id;
        this.fname=fname;
        this.lname=lname;
        this.email_id=email_id;
        this.gender=gender;
        this.date=date;
        this.branch=branch;
        this.roomno=roomno;
    }

    public String getid(){ return id; }
    public String getfname(){ return fname;}
    public String getlname() { return lname; }
    public String getemail() { return email_id; }
    public String getDate() { return date; }
    public String getgender() { return gender; }
    public String getbranch() { return branch; }
    public String getroomno() { return roomno; }
}
