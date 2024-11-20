alter table student
    add constraint fk_student_roomNo FOREIGN KEY (roomNo) REFERENCES hostelRoom(roomNo);
alter table vaccination
    add constraint fk_vaccination_studentId FOREIGN KEY (studentId) REFERENCES student(studentId) ON DELETE CASCADE;
alter table rtpcr
    add constraint fk_rtpcr_studentId FOREIGN KEY (studentId) REFERENCES student(studentId);
alter table posCase
    add constraint fk_case_studenId FOREIGN KEY (studentId) REFERENCES student(studentId);
alter table posCase
    add constraint fk_case_qroomNo FOREIGN KEY (qroomNo) REFERENCES hostelRoom(roomNo);
alter table posCase
    add constraint fk_case_testId FOREIGN KEY (testId) REFERENCES rtpcr(testId);
alter table quarantine
    add constraint fk_quarantine_caseId FOREIGN KEY (caseId) REFERENCES posCase(caseId) ON DELETE CASCADE;
alter table dose
    add constraint fk_dose_studentId FOREIGN KEY (studentId) REFERENCES student(studentId) ON DELETE CASCADE;

-- alter table login_t
--    add constraint fk_login_userid FOREIGN KEY (userid) REFERENCES student(StudentId);