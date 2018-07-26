
CREATE DATABASE HIGHSCHOOL;

USE HIGHSCHOOL;

CREATE TABLE STUDENT(
    registration integer not null,
    firstname char(20) not null,
    lastname char(20) not null, 
    birth date not null,
    CONSTRAINT pk_stundent PRIMARY KEY (registration)
);
CREATE TABLE TEACHER(
    matriculation char(10) not null,
    firstname char(20) not null,
    lastname char(20) not null, 
    birth date not null,
    CONSTRAINT pk_teacher PRIMARY KEY (matriculation)
);
CREATE TABLE COURSE(
    name char(30) not null,
    teacher char(10) not null,
    hoursweek real not null,
    CONSTRAINT pk_course PRIMARY KEY (name),
    CONSTRAINT fk_course_teacher FOREIGN KEY (teacher) REFERENCES TEACHER(matriculation)
);
CREATE TABLE STUDY(
    course char(30) not null,
    student integer not null,
    CONSTRAINT pk_study PRIMARY KEY (course, student),
    CONSTRAINT fk_study_course FOREIGN KEY (course) REFERENCES COURSE(name),
    CONSTRAINT fk_study_student FOREIGN KEY (student) REFERENCES STUDENT(registration)
);
CREATE TABLE SCHEDULE(
    course char(30) not null,
    day char(10) not null,
    begin time not null, 
    end time not null,
    CONSTRAINT pk_schedule PRIMARY KEY (course, day, begin),
    CONSTRAINT fk_schedule_course FOREIGN KEY (course) REFERENCES COURSE(name)
);

CREATE TABLE HISTORICAL(
    student integer not null,
    course char(10) not null,
    datec date not null,
    partial1 float default 0,
    partial2 float default 0,
    partial3 float default 0,
    final float default 0,
    CONSTRAINT pk_history PRIMARY KEY (student, course, datec),
    CONSTRAINT fk_history_student FOREIGN KEY (student) REFERENCES STUDENT(registration),
    CONSTRAINT fk_history_course FOREIGN KEY (course) REFERENCES COURSE(name)
);

INSERT INTO STUDENT(registration, firstname, lastname, birth) VALUES (1, 'Maria', 'Lucero','1995-07-16');
INSERT INTO STUDENT(registration, firstname, lastname, birth) VALUES (2, 'Marta', 'Brocca','1993-08-07');
INSERT INTO STUDENT(registration, firstname, lastname, birth) VALUES (3, 'Pedro', 'Perez','1995-10-16');
INSERT INTO STUDENT(registration, firstname, lastname, birth) VALUES (4, 'Esteban', 'Hordan','1994-01-05');
INSERT INTO STUDENT(registration, firstname, lastname, birth) VALUES (5, 'Juan', 'Gonzalez','1996-05-25');
INSERT INTO STUDENT(registration, firstname, lastname, birth) VALUES (6, 'Lucio', 'Peralta','1995-05-17');
INSERT INTO STUDENT(registration, firstname, lastname, birth) VALUES (7, 'Roberto', 'Romero','1995-12-14');
INSERT INTO STUDENT(registration, firstname, lastname, birth) VALUES (8, 'Soledad', 'Canditta','1993-04-01');
INSERT INTO STUDENT(registration, firstname, lastname, birth) VALUES (9, 'Victoria', 'Echevere','1993-09-23');
INSERT INTO STUDENT(registration, firstname, lastname, birth) VALUES (10, 'Nicolas', 'Merdan','1993-08-14');

INSERT INTO TEACHER(matriculation, firstname, lastname, birth) VALUES ('MN001', 'Julia', 'Rodriguez','1985-07-03');
INSERT INTO TEACHER(matriculation, firstname, lastname, birth) VALUES ('MN002', 'Juan Luis', 'Diaz','1990-08-21');
INSERT INTO TEACHER(matriculation, firstname, lastname, birth) VALUES ('MN003', 'Marcela', 'Pedersen','1988-08-17');

INSERT INTO COURSE(name, teacher, hoursweek) VALUES ('Algebra', 'MN001', 20);
INSERT INTO COURSE(name, teacher, hoursweek) VALUES ('Philosophy', 'MN003', 18);
INSERT INTO COURSE(name, teacher, hoursweek) VALUES ('Literature', 'MN002', 14);

INSERT INTO STUDY(course, student) VALUES ('Algebra', 1);
INSERT INTO STUDY(course, student) VALUES ('Algebra', 2);
INSERT INTO STUDY(course, student) VALUES ('Algebra', 3);
INSERT INTO STUDY(course, student) VALUES ('Algebra', 4);
INSERT INTO STUDY(course, student) VALUES ('Algebra', 5);
INSERT INTO STUDY(course, student) VALUES ('Algebra', 6);
INSERT INTO STUDY(course, student) VALUES ('Algebra', 7);
INSERT INTO STUDY(course, student) VALUES ('Algebra', 8);
INSERT INTO STUDY(course, student) VALUES ('Algebra', 9);
INSERT INTO STUDY(course, student) VALUES ('Algebra', 10);
INSERT INTO STUDY(course, student) VALUES ('Literature', 1);
INSERT INTO STUDY(course, student) VALUES ('Literature', 2);
INSERT INTO STUDY(course, student) VALUES ('Literature', 3);
INSERT INTO STUDY(course, student) VALUES ('Literature', 4);
INSERT INTO STUDY(course, student) VALUES ('Literature', 5);
INSERT INTO STUDY(course, student) VALUES ('Literature', 6);
INSERT INTO STUDY(course, student) VALUES ('Literature', 7);
INSERT INTO STUDY(course, student) VALUES ('Literature', 8);
INSERT INTO STUDY(course, student) VALUES ('Literature', 9);
INSERT INTO STUDY(course, student) VALUES ('Literature', 10);
INSERT INTO STUDY(course, student) VALUES ('Philosophy', 1);
INSERT INTO STUDY(course, student) VALUES ('Philosophy', 2);
INSERT INTO STUDY(course, student) VALUES ('Philosophy', 3);
INSERT INTO STUDY(course, student) VALUES ('Philosophy', 4);
INSERT INTO STUDY(course, student) VALUES ('Philosophy', 5);
INSERT INTO STUDY(course, student) VALUES ('Philosophy', 6);
INSERT INTO STUDY(course, student) VALUES ('Philosophy', 7);
INSERT INTO STUDY(course, student) VALUES ('Philosophy', 8);
INSERT INTO STUDY(course, student) VALUES ('Philosophy', 9);
INSERT INTO STUDY(course, student) VALUES ('Philosophy', 10);

INSERT INTO SCHEDULE(course, day, begin, end) VALUES ('Algebra','Monday','08:00','11:00');
INSERT INTO SCHEDULE(course, day, begin, end) VALUES ('Algebra','Wednesday','08:00','11:00');
INSERT INTO SCHEDULE(course, day, begin, end) VALUES ('Algebra','Friday','08:00','11:00');
INSERT INTO SCHEDULE(course, day, begin, end) VALUES ('Literature','Tuesday','14:30','17:30');
INSERT INTO SCHEDULE(course, day, begin, end) VALUES ('Literature','Friday','17:30','20:30');
INSERT INTO SCHEDULE(course, day, begin, end) VALUES ('Philosophy','Thursday ','08:00','10:00');
INSERT INTO SCHEDULE(course, day, begin, end) VALUES ('Philosophy','Thursday ','14:00','17:00');
INSERT INTO SCHEDULE(course, day, begin, end) VALUES ('Literature','Thursday ','08:00','10:00');
INSERT INTO SCHEDULE(course, day, begin, end) VALUES ('Literature','Thursday ','14:00','17:00');

INSERT INTO HISTORICAL(student, course, datec, final) VALUES (2, 'Algebra','2018-07-22', 7);
INSERT INTO HISTORICAL(student, course, datec, final) VALUES (1, 'Algebra','2018-07-22', 2);
INSERT INTO HISTORICAL(student, course, datec, final) VALUES (3, 'Algebra','2018-07-22', 2);
INSERT INTO HISTORICAL(student, course, datec, final) VALUES (4, 'Algebra','2018-07-22', 7);
INSERT INTO HISTORICAL(student, course, datec, final) VALUES (5, 'Algebra','2018-07-22', 7);
INSERT INTO HISTORICAL(student, course, datec, final) VALUES (6, 'Algebra','2018-07-22', 2);
INSERT INTO HISTORICAL(student, course, datec, final) VALUES (7, 'Algebra','2018-07-22', 1);
INSERT INTO HISTORICAL(student, course, datec, final) VALUES (8, 'Algebra','2018-07-22', 2);
INSERT INTO HISTORICAL(student, course, datec, final) VALUES (9, 'Algebra','2018-07-22', 2);
INSERT INTO HISTORICAL(student, course, datec, final) VALUES (10, 'Algebra','2018-07-22', 7);
INSERT INTO HISTORICAL(student, course, datec, final) VALUES (1, 'Philosophy','2018-07-22', 2);
INSERT INTO HISTORICAL(student, course, datec, final) VALUES (2, 'Philosophy','2018-07-22', 2);
INSERT INTO HISTORICAL(student, course, datec, final) VALUES (3, 'Philosophy','2018-07-22', 7);
INSERT INTO HISTORICAL(student, course, datec, final) VALUES (4, 'Philosophy','2018-07-22', 7);