create table student(
    studentId varchar(10),
    fname varchar(20),
    lname varchar(20),
    dateOfBirth Date,
    emailId varchar(30) UNIQUE,
    gender varchar(1),
    branch varchar(10),
    roomNo varchar(10),
    constraint pk_student PRIMARY KEY(StudentId)
);

create table vaccination(
    studentId varchar(10),
    dosesTaken int,
    vaccinationStatus varchar(50),
    certif varchar(100),
    constraint pk_vaccination PRIMARY KEY(studentId)
);

create table rtpcr(
    testId varchar(10),
    studentId varchar(10),
    testDate Date,
    test_result TINYINT(1),
    certif varchar(100),
    constraint pk_rtpcr PRIMARY KEY(testId)
);

create table hostelRoom(
    roomNo varchar(10),
    roomType varchar(10),
    capacity int,
    vacancy int,
    hostelType varchar(10),
    constraint pk_hostelRoom PRIMARY KEY(roomNo)
);

create table quarantine(
    caseId varchar(10),
    startDate Date,
    endDate Date,
    healthStatus varchar(50),
    constraint pk_quarantine PRIMARY KEY(caseId)
);

create table posCase(
    caseId varchar(10),
    studentId varchar(10) UNIQUE,
    qroomNo varchar(10) UNIQUE,
    testId varchar(10) UNIQUE,
    diagnosisDate Date,
    constraint pk_case PRIMARY KEY(caseId)
);

create table dose(
    studentId varchar(10),
    doseNo int,
    dateTaken Date,
    vaccineName varchar(15),
    constraint pk_dose PRIMARY KEY(studentId,doseNo)
);

create table login_t(
    userid varchar(30),
    userpass varchar(50),
    constraint pk_userid PRIMARY KEY (userid)
);