ALTER TABLE student
    DROP FOREIGN KEY fk_student_roomNo;

ALTER TABLE vaccination
    DROP FOREIGN KEY fk_vaccination_studentId;

ALTER TABLE rtpcr
    DROP FOREIGN KEY fk_rtpcr_studentId;

ALTER TABLE posCase
    DROP FOREIGN KEY fk_case_studenId;

ALTER TABLE posCase
    DROP FOREIGN KEY fk_case_qroomNo;

ALTER TABLE posCase
    DROP FOREIGN KEY fk_case_testId; 

ALTER TABLE posCase
    DROP FOREIGN KEY fk_quarantine_caseId; 

ALTER TABLE posCase
    DROP FOREIGN KEY fk_dose_studentId; 

-- ALTER TABLE login_t
--    DROP FOREIGN KEY fk_login_userid; 

DROP TABLE student;
DROP TABLE vaccination;
DROP TABLE rtpcr;
DROP TABLE hostelRoom;
DROP TABLE quarantine;
DROP TABLE posCase;
DROP TABLE dose;
DROP TABLE login_t;

    