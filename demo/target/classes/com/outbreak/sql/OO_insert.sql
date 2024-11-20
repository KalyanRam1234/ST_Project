-- MEN's HOSTEL ENTRIES
INSERT INTO hostelRoom(roomNo, roomType, capacity, vacancy, hostelType ) VALUES("B205","Quarantine", 1, 1, "Mens");
INSERT INTO hostelRoom(roomNo, roomType, capacity, vacancy, hostelType ) VALUES("B204","Quarantine", 1, 1, "Mens");
INSERT INTO hostelRoom(roomNo, roomType, capacity, vacancy, hostelType ) VALUES("B203","Quarantine", 1, 0, "Mens");
INSERT INTO hostelRoom(roomNo, roomType, capacity, vacancy, hostelType ) VALUES("B202","Quarantine", 1, 0, "Mens");
INSERT INTO hostelRoom(roomNo, roomType, capacity, vacancy, hostelType ) VALUES("B201","Quarantine", 1, 0, "Mens");

INSERT INTO hostelRoom(roomNo, roomType, capacity, vacancy, hostelType ) VALUES("B105","Hostel", 1, 1, "Mens");
INSERT INTO hostelRoom(roomNo, roomType, capacity, vacancy, hostelType ) VALUES("B104","Hostel", 1, 0, "Mens");
INSERT INTO hostelRoom(roomNo, roomType, capacity, vacancy, hostelType ) VALUES("B103","Hostel", 1, 0, "Mens");
INSERT INTO hostelRoom(roomNo, roomType, capacity, vacancy, hostelType ) VALUES("B102","Hostel", 3, 1, "Mens");
INSERT INTO hostelRoom(roomNo, roomType, capacity, vacancy, hostelType ) VALUES("B101","Hostel", 3, 0, "Mens");

-- WOMEN'S HOSTEL ENTRIES
INSERT INTO hostelRoom(roomNo, roomType, capacity, vacancy, hostelType ) VALUES("L205","Quarantine", 1, 1, "Womens");
INSERT INTO hostelRoom(roomNo, roomType, capacity, vacancy, hostelType ) VALUES("L204","Quarantine", 1, 1, "Womens");
INSERT INTO hostelRoom(roomNo, roomType, capacity, vacancy, hostelType ) VALUES("L203","Quarantine", 1, 1, "Womens");
INSERT INTO hostelRoom(roomNo, roomType, capacity, vacancy, hostelType ) VALUES("L202","Quarantine", 1, 0, "Womens");
INSERT INTO hostelRoom(roomNo, roomType, capacity, vacancy, hostelType ) VALUES("L201","Quarantine", 1, 0, "Womens");


INSERT INTO hostelRoom(roomNo, roomType, capacity, vacancy, hostelType ) VALUES("L105","Hostel", 1, 1, "Womens");
INSERT INTO hostelRoom(roomNo, roomType, capacity, vacancy, hostelType ) VALUES("L104","Hostel", 1, 0, "Womens");
INSERT INTO hostelRoom(roomNo, roomType, capacity, vacancy, hostelType ) VALUES("L103","Hostel", 1, 0, "Womens");
INSERT INTO hostelRoom(roomNo, roomType, capacity, vacancy, hostelType ) VALUES("L102","Hostel", 1, 0, "Womens");
INSERT INTO hostelRoom(roomNo, roomType, capacity, vacancy, hostelType ) VALUES("L101","Hostel", 3, 1, "Womens");


INSERT INTO student(studentId, fname, lname, dateOfBirth, emailId, gender, branch, roomNo) VALUES("IMT2022001","Vishal", "Parthani", "2001-02-27", "Vishal.Parthani@iiitb.ac.in", "M", "CSE", "B101");
INSERT INTO student(studentId, fname, lname, dateOfBirth, emailId, gender, branch, roomNo) VALUES("IMT2022002", "Kushal", "Singh", "2003-11-03", "Kushal.Singh@iiitb.ac.in", "M", "CSE", "B102");
INSERT INTO student(studentId, fname, lname, dateOfBirth, emailId, gender, branch, roomNo) VALUES("IMT2022003", "Krushkar Reddy", "Yerla", "2003-7-26", "Krushkar.Reddy@iiitb.ac.in", "M", "CSE", "B102");
INSERT INTO student(studentId, fname, lname, dateOfBirth, emailId, gender, branch, roomNo) VALUES("IMT2022004", "Swapna", "Kumari", "2003-04-21", "Swapna.Kumari@iiitb.ac.in", "F", "ECE", "L101");
INSERT INTO student(studentId, fname, lname, dateOfBirth, emailId, gender, branch, roomNo) VALUES("IMT2021001", "Rahul", "Joshi", "2002-07-25", "Rahul.Joshi@iiitb.ac.in", "M", "CSE", "B101");
INSERT INTO student(studentId, fname, lname, dateOfBirth, emailId, gender, branch, roomNo) VALUES("IMT2021002", "Ganesh", "Yadav", "2002-07-07", "Ganesh.Yadav@iiitb.ac.in", "M", "CSE", "B104");
INSERT INTO student(studentId, fname, lname, dateOfBirth, emailId, gender, branch, roomNo) VALUES("IMT2021003", "Aryan", "Khatri", "2002-04-01", "Aryan.Khatri@iiitb.ac.in", "M", "CSE", "B101");
INSERT INTO student(studentId, fname, lname, dateOfBirth, emailId, gender, branch, roomNo) VALUES("MT2021001", "Vaibhav", "Acharya", "1999-12-15", "Vaibhav.Acharya@iiitb.ac.in", "M", "CSE", "B103");
INSERT INTO student(studentId, fname, lname, dateOfBirth, emailId, gender, branch, roomNo) VALUES("MT2021002", "Mehana", "Bhatt", "2000-06-21", "Mehana.Bhatt@iiitb.ac.in", "F", "ECE", "L104");
INSERT INTO student(studentId, fname, lname, dateOfBirth, emailId, gender, branch, roomNo) VALUES("MT2022001", "Keerthi", "Verma", "2000-08-19", "Keerthi.Verma@iiitb.ac.in", "F", "CSE", "L103");
INSERT INTO student(studentId, fname, lname, dateOfBirth, emailId, gender, branch, roomNo) VALUES("MT2022002", "Supriya", "Singh", "2000-09-21", "Supriya.Singh@iiitb.ac.in", "F", "ECE", "L102");
INSERT INTO student(studentId, fname, lname, dateOfBirth, emailId, gender, branch, roomNo) VALUES("IMT2021004", "Harshini", "Joshi", "2002-10-30", "Harshini.Joshi@iiitb.ac.in", "F", "ECE", "L101");


INSERT INTO vaccination(studentId,dosesTaken,vaccinationStatus,certif) VALUES("IMT2022001",2,"Done",NULL);
INSERT INTO vaccination(studentId,dosesTaken,vaccinationStatus,certif) VALUES("IMT2022002",3,"Done",NULL);
INSERT INTO vaccination(studentId,dosesTaken,vaccinationStatus,certif) VALUES("IMT2022003",2,"Done",NULL);
INSERT INTO vaccination(studentId,dosesTaken,vaccinationStatus,certif) VALUES("IMT2022004",3,NULL,NULL);
INSERT INTO vaccination(studentId,dosesTaken,vaccinationStatus,certif) VALUES("IMT2021001",2,NULL,NULL);
INSERT INTO vaccination(studentId,dosesTaken,vaccinationStatus,certif) VALUES("IMT2021002",1,"Incomplete",NULL);
INSERT INTO vaccination(studentId,dosesTaken,vaccinationStatus,certif) VALUES("IMT2021003",2,"Done",NULL);
INSERT INTO vaccination(studentId,dosesTaken,vaccinationStatus,certif) VALUES("IMT2021004",2,NULL,NULL);
INSERT INTO vaccination(studentId,dosesTaken,vaccinationStatus,certif) VALUES("MT2022001",2,NULL,NULL);
INSERT INTO vaccination(studentId,dosesTaken,vaccinationStatus,certif) VALUES("MT2022002",1,NULL,NULL);
INSERT INTO vaccination(studentId,dosesTaken,vaccinationStatus,certif) VALUES("MT2021001",1,NULL,NULL);
INSERT INTO vaccination(studentId,dosesTaken,vaccinationStatus,certif) VALUES("MT2021002",1,NULL,NULL);


INSERT INTO dose(studentId, doseNo, dateTaken, vaccineName) VALUES("IMT2022001",1,"2023-01-02","COVISHIELD");
INSERT INTO dose(studentId, doseNo, dateTaken, vaccineName) VALUES("IMT2022001",2,"2023-01-29","COVISHIELD");
INSERT INTO dose(studentId, doseNo, dateTaken, vaccineName) VALUES("IMT2022002",1,"2023-01-04","COVISHIELD");
INSERT INTO dose(studentId, doseNo, dateTaken, vaccineName) VALUES("IMT2022002",2,"2023-02-07","COVISHIELD");
INSERT INTO dose(studentId, doseNo, dateTaken, vaccineName) VALUES("IMT2022002",3,"2023-02-26","COVISHIELD");
INSERT INTO dose(studentId, doseNo, dateTaken, vaccineName) VALUES("IMT2022003",1,"2023-01-03","COVISHIELD");
INSERT INTO dose(studentId, doseNo, dateTaken, vaccineName) VALUES("IMT2022003",2,"2023-01-21","COVISHIELD");
INSERT INTO dose(studentId, doseNo, dateTaken, vaccineName) VALUES("IMT2022004",1,"2023-01-09","COVISHIELD");
INSERT INTO dose(studentId, doseNo, dateTaken, vaccineName) VALUES("IMT2022004",2,"2023-02-12","COVISHIELD");
INSERT INTO dose(studentId, doseNo, dateTaken, vaccineName) VALUES("IMT2022004",3,"2023-03-03","COVISHIELD");
INSERT INTO dose(studentId, doseNo, dateTaken, vaccineName) VALUES("IMT2021001",1,"2023-01-21","COVISHIELD");
INSERT INTO dose(studentId, doseNo, dateTaken, vaccineName) VALUES("IMT2021001",2,"2023-02-20","COVISHIELD");
INSERT INTO dose(studentId, doseNo, dateTaken, vaccineName) VALUES("IMT2021002",1,"2023-01-18","COVISHIELD");
INSERT INTO dose(studentId, doseNo, dateTaken, vaccineName) VALUES("IMT2021003",1,"2023-02-16","COVISHIELD");
INSERT INTO dose(studentId, doseNo, dateTaken, vaccineName) VALUES("IMT2021003",2,"2023-03-11","COVISHIELD");
INSERT INTO dose(studentId, doseNo, dateTaken, vaccineName) VALUES("IMT2021004",1,"2023-02-12","COVISHIELD");
INSERT INTO dose(studentId, doseNo, dateTaken, vaccineName) VALUES("IMT2021004",2,"2023-03-14","COVISHIELD");
INSERT INTO dose(studentId, doseNo, dateTaken, vaccineName) VALUES("MT2022001",1,"2023-01-12","COVISHIELD");
INSERT INTO dose(studentId, doseNo, dateTaken, vaccineName) VALUES("MT2022001",2,"2023-02-22","COVISHIELD");
INSERT INTO dose(studentId, doseNo, dateTaken, vaccineName) VALUES("MT2022002",1,"2023-02-17","COVISHIELD");
INSERT INTO dose(studentId, doseNo, dateTaken, vaccineName) VALUES("MT2021001",1,"2023-01-22","COVISHIELD");
INSERT INTO dose(studentId, doseNo, dateTaken, vaccineName) VALUES("MT2021002",1,"2023-01-27","COVISHIELD");

INSERT INTO rtpcr(testId, studentId, testDate, test_result, certif) VALUES("RT001","IMT2022001","2023-02-15",0,NULL);
INSERT INTO rtpcr(testId, studentId, testDate, test_result, certif) VALUES("RT002","MT2022001","2023-02-15",0,NULL);
INSERT INTO rtpcr(testId, studentId, testDate, test_result, certif) VALUES("RT003","MT2022002","2023-02-15",0,NULL);
INSERT INTO rtpcr(testId, studentId, testDate, test_result, certif) VALUES("RT004","MT2021001","2023-02-15",0,NULL);
INSERT INTO rtpcr(testId, studentId, testDate, test_result, certif) VALUES("RT005","IMT2022002","2023-02-15",0,NULL);
INSERT INTO rtpcr(testId, studentId, testDate, test_result, certif) VALUES("RT006","IMT2022003","2023-02-15",0,NULL);
INSERT INTO rtpcr(testId, studentId, testDate, test_result, certif) VALUES("RT007","IMT2022004","2023-02-15",0,NULL);
INSERT INTO rtpcr(testId, studentId, testDate, test_result, certif) VALUES("RT008","IMT2021001","2023-02-15",0,NULL);
INSERT INTO rtpcr(testId, studentId, testDate, test_result, certif) VALUES("RT009","IMT2021002","2023-02-15",0,NULL);
INSERT INTO rtpcr(testId, studentId, testDate, test_result, certif) VALUES("RT010","IMT2021003","2023-02-15",0,NULL);
INSERT INTO rtpcr(testId, studentId, testDate, test_result, certif) VALUES("RT011","IMT2021004","2023-02-15",0,NULL);
INSERT INTO rtpcr(testId, studentId, testDate, test_result, certif) VALUES("RT012","MT2021002","2023-02-15",0,NULL);

INSERT INTO rtpcr(testId, studentId, testDate, test_result, certif) VALUES("RT013","IMT2022002","2023-02-22",0,NULL);
INSERT INTO rtpcr(testId, studentId, testDate, test_result, certif) VALUES("RT014","IMT2022001","2023-02-22",0,NULL);
INSERT INTO rtpcr(testId, studentId, testDate, test_result, certif) VALUES("RT015","MT2021001","2023-02-22",1,NULL);
INSERT INTO rtpcr(testId, studentId, testDate, test_result, certif) VALUES("RT016","IMT2022003","2023-02-22",0,NULL);
INSERT INTO rtpcr(testId, studentId, testDate, test_result, certif) VALUES("RT017","MT2022002","2023-02-22",0,NULL);
INSERT INTO rtpcr(testId, studentId, testDate, test_result, certif) VALUES("RT018","IMT2022004","2023-02-22",0,NULL);
INSERT INTO rtpcr(testId, studentId, testDate, test_result, certif) VALUES("RT019","IMT2021002","2023-02-22",0,NULL);
INSERT INTO rtpcr(testId, studentId, testDate, test_result, certif) VALUES("RT020","IMT2021004","2023-02-22",0,NULL);
INSERT INTO rtpcr(testId, studentId, testDate, test_result, certif) VALUES("RT021","IMT2021001","2023-02-22",0,NULL);
INSERT INTO rtpcr(testId, studentId, testDate, test_result, certif) VALUES("RT022","MT2021002","2023-02-22",0,NULL);
INSERT INTO rtpcr(testId, studentId, testDate, test_result, certif) VALUES("RT023","IMT2021003","2023-02-22",0,NULL);
INSERT INTO rtpcr(testId, studentId, testDate, test_result, certif) VALUES("RT024","MT2022001","2023-02-22",0,NULL);

INSERT INTO rtpcr(testId, studentId, testDate, test_result, certif) VALUES("RT025","IMT2022001","2023-03-29",1,NULL);
INSERT INTO rtpcr(testId, studentId, testDate, test_result, certif) VALUES("RT026","MT2022001","2023-03-29",0,NULL);
INSERT INTO rtpcr(testId, studentId, testDate, test_result, certif) VALUES("RT027","MT2022002","2023-03-29",0,NULL);
INSERT INTO rtpcr(testId, studentId, testDate, test_result, certif) VALUES("RT028","MT2021001","2023-03-29",0,NULL);
INSERT INTO rtpcr(testId, studentId, testDate, test_result, certif) VALUES("RT029","IMT2022002","2023-03-29",0,NULL);
INSERT INTO rtpcr(testId, studentId, testDate, test_result, certif) VALUES("RT030","IMT2022003","2023-03-29",0,NULL);
INSERT INTO rtpcr(testId, studentId, testDate, test_result, certif) VALUES("RT031","IMT2022004","2023-03-29",0,NULL);
INSERT INTO rtpcr(testId, studentId, testDate, test_result, certif) VALUES("RT032","IMT2021001","2023-03-29",0,NULL);
INSERT INTO rtpcr(testId, studentId, testDate, test_result, certif) VALUES("RT033","IMT2021002","2023-03-29",0,NULL);
INSERT INTO rtpcr(testId, studentId, testDate, test_result, certif) VALUES("RT034","IMT2021003","2023-03-29",0,NULL);
INSERT INTO rtpcr(testId, studentId, testDate, test_result, certif) VALUES("RT035","IMT2021004","2023-03-29",1,NULL);
INSERT INTO rtpcr(testId, studentId, testDate, test_result, certif) VALUES("RT036","MT2021002","2023-03-29",0,NULL);

INSERT INTO rtpcr(testId, studentId, testDate, test_result, certif) VALUES("RT037","IMT2022002","2023-04-05",0,NULL);
INSERT INTO rtpcr(testId, studentId, testDate, test_result, certif) VALUES("RT038","IMT2022001","2023-04-05",0,NULL);
INSERT INTO rtpcr(testId, studentId, testDate, test_result, certif) VALUES("RT039","MT2021001","2023-04-05",0,NULL);
INSERT INTO rtpcr(testId, studentId, testDate, test_result, certif) VALUES("RT040","IMT2022003","2023-04-05",0,NULL);
INSERT INTO rtpcr(testId, studentId, testDate, test_result, certif) VALUES("RT041","MT2022002","2023-04-05",0,NULL);
INSERT INTO rtpcr(testId, studentId, testDate, test_result, certif) VALUES("RT042","IMT2022004","2023-04-05",0,NULL);
INSERT INTO rtpcr(testId, studentId, testDate, test_result, certif) VALUES("RT043","IMT2021002","2023-04-05",0,NULL);
INSERT INTO rtpcr(testId, studentId, testDate, test_result, certif) VALUES("RT044","IMT2021004","2023-04-05",0,NULL);
INSERT INTO rtpcr(testId, studentId, testDate, test_result, certif) VALUES("RT045","IMT2021001","2023-04-05",0,NULL);
INSERT INTO rtpcr(testId, studentId, testDate, test_result, certif) VALUES("RT046","MT2021002","2023-04-05",0,NULL);
INSERT INTO rtpcr(testId, studentId, testDate, test_result, certif) VALUES("RT047","IMT2021003","2023-04-05",0,NULL);
INSERT INTO rtpcr(testId, studentId, testDate, test_result, certif) VALUES("RT048","MT2022001","2023-04-05",0,NULL);

INSERT INTO rtpcr(testId, studentId, testDate, test_result, certif) VALUES("RT049","IMT2021002","2023-04-12",0,NULL);
INSERT INTO rtpcr(testId, studentId, testDate, test_result, certif) VALUES("RT050","IMT2021004","2023-04-12",0,NULL);
INSERT INTO rtpcr(testId, studentId, testDate, test_result, certif) VALUES("RT051","IMT2022002","2023-04-12",0,NULL);
INSERT INTO rtpcr(testId, studentId, testDate, test_result, certif) VALUES("RT052","MT2021001","2023-04-12",0,NULL);
INSERT INTO rtpcr(testId, studentId, testDate, test_result, certif) VALUES("RT053","IMT2021001","2023-04-12",1,NULL);
INSERT INTO rtpcr(testId, studentId, testDate, test_result, certif) VALUES("RT054","IMT2022001","2023-04-12",0,NULL);
INSERT INTO rtpcr(testId, studentId, testDate, test_result, certif) VALUES("RT055","MT2021002","2023-04-12",0,NULL);
INSERT INTO rtpcr(testId, studentId, testDate, test_result, certif) VALUES("RT056","IMT2022003","2023-04-12",0,NULL);
INSERT INTO rtpcr(testId, studentId, testDate, test_result, certif) VALUES("RT057","MT2022002","2023-04-12",0,NULL);
INSERT INTO rtpcr(testId, studentId, testDate, test_result, certif) VALUES("RT058","IMT2022004","2023-04-12",0,NULL);
INSERT INTO rtpcr(testId, studentId, testDate, test_result, certif) VALUES("RT059","IMT2021003","2023-04-12",0,NULL);
INSERT INTO rtpcr(testId, studentId, testDate, test_result, certif) VALUES("RT060","MT2022001","2023-04-12",1,NULL);


INSERT INTO posCase(caseId, studentId, qroomNo, testId, diagnosisDate) VALUES("case001", "MT2021001", "B203", "RT015", "2023-02-22");
INSERT INTO posCase(caseId, studentId, qroomNo, testId, diagnosisDate) VALUES("case002", "IMT2022001", "B201", "RT025", "2023-03-29");
INSERT INTO posCase(caseId, studentId, qroomNo, testId, diagnosisDate) VALUES("case003", "IMT2021001", "B202", "RT035", "2023-03-29");
INSERT INTO posCase(caseId, studentId, qroomNo, testId, diagnosisDate) VALUES("case004", "IMT2021004", "L201", "RT053", "2023-04-12");
INSERT INTO posCase(caseId, studentId, qroomNo, testId, diagnosisDate) VALUES("case005", "MT2022001", "L202", "RT060", "2023-04-12");

INSERT INTO quarantine(caseId, startDate, endDate, healthStatus) VALUES("case001", "2023-02-22", "2023-03-06", "Good");
INSERT INTO quarantine(caseId, startDate, endDate, healthStatus) VALUES("case002", "2023-03-29", "2023-04-13","Good");
INSERT INTO quarantine(caseId, startDate, endDate, healthStatus) VALUES("case003", "2023-03-29", "2023-04-13","Decent");
INSERT INTO quarantine(caseId, startDate, endDate, healthStatus) VALUES("case004", "2023-04-12", "2023-04-27","Bad");
INSERT INTO quarantine(caseId, startDate, endDate, healthStatus) VALUES("case005", "2023-04-12", "2023-04-27","Critical");


INSERT INTO login_t(userid, userpass) VALUES("IMT2022001", MD5("IMT2022001"));
INSERT INTO login_t(userid, userpass) VALUES("IMT2022002", MD5("IMT2022002"));
INSERT INTO login_t(userid, userpass) VALUES("IMT2022003", MD5("IMT2022003"));
INSERT INTO login_t(userid, userpass) VALUES("IMT2022004", MD5("IMT2022004"));
INSERT INTO login_t(userid, userpass) VALUES("IMT2021001", MD5("IMT2021001"));
INSERT INTO login_t(userid, userpass) VALUES("IMT2021002", MD5("IMT2021002"));
INSERT INTO login_t(userid, userpass) VALUES("IMT2021003", MD5("IMT2021003"));
INSERT INTO login_t(userid, userpass) VALUES("MT2021001", MD5("MT2021001"));
INSERT INTO login_t(userid, userpass) VALUES("MT2021002", MD5("RedRose@91"));
INSERT INTO login_t(userid, userpass) VALUES("MT2022001", MD5("BlueSea!65"));
INSERT INTO login_t(userid, userpass) VALUES("MT2022002", MD5("PinkFlower#73"));
INSERT INTO login_t(userid, userpass) VALUES("IMT2021004", MD5("YellowSun#98"));
INSERT INTO login_t(userid, userpass) VALUES("Admin", MD5("Admin"));














