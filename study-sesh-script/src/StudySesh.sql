DROP DATABASE if exists StudySesh;
CREATE DATABASE StudySesh;
USE StudySesh;

CREATE TABLE User (
	id int(11) primary key not null auto_increment,
	username varchar(30) not null,
	password varchar(30) not null,
    firstname varchar(20) not null,
    lastname varchar(20) not null,
    major varchar(25) not null,
    bio varchar(400) not null
);

CREATE TABLE Classroom (
	id int(11) primary key not null auto_increment,
	building int(11) not null,
    roomNumber varchar(30) not null
);

CREATE TABLE Class (
	id int(11) primary key not null auto_increment,
	name varchar(100) not null,
    code varchar(10) not null
);

CREATE TABLE StudySession (
	id int(11) primary key not null auto_increment,
	class int(11) not null,
    classroom int(11) not null,
    startTime varchar(6) not null,
    endTime varchar(6) not null
);

CREATE TABLE UserClass (
	id int(11) primary key not null auto_increment,
    user int(11) not null,
    class int(11) not null
);

CREATE Table UserSession (
	id int(11) primary key not null auto_increment,
    student int(11) not null,
    studySession int(11) not null
);

CREATE Table ClassSession (
	id int(11) primary key not null auto_increment,
	startTime varchar(6) not null,
    endTime varchar(6) not null,
    room int(11) not null
);

CREATE Table Building (
	id int(11) primary key not null auto_increment,
    code varchar(10) not null
);