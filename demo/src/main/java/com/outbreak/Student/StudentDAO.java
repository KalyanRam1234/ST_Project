package com.outbreak.Student;
import java.util.ArrayList;
public interface StudentDAO {
    public Student getStudentByKey(String rollNo);
    public ArrayList<Student> getStudentsByBatch(String batch);
    public int getStudentCount();
    public int getStudentCountByBatch(String batch);
    public ArrayList<Student> getStudentByRoomNo(String roomno);
    public void enterStudent(String id, String fname, String lname, String date, String email_id, String gender, String branch, String roomno);
    public void deleteStudentById(String id);
    public void updateStudentById(String id, Student s);
}
