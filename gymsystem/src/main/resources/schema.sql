DROP TABLE UserInfo CASCADE CONSTRAINTS;
DROP TABLE Review CASCADE CONSTRAINTS;
DROP TABLE Trainer CASCADE CONSTRAINTS;
DROP TABLE Workout CASCADE CONSTRAINTS;
DROP SEQUENCE reviewId_seq;

CREATE TABLE UserInfo ( 
	userId      VARCHAR2(12)	PRIMARY KEY, 
	password	VARCHAR2(12)	NOT NULL,
	email		VARCHAR2(50)	NOT NULL
);


CREATE TABLE Review (
	reviewId 		NUMBER(4)		PRIMARY KEY,
	userId 		VARCHAR2(12) 	NOT NULL,
	workoutId	NUMBER(4)		NOT NULL,
	trainerId	NUMBER(4)	NOT NULL,
	title		VARCHAR2(50),
	content		VARCHAR2(300),
	score		NUMBER(1),
	likeCount	NUMBER(4)
);

create table Trainer (
	trainerId		NUMBER(4) primary key,
	name		varchar2(12)
);

create table Workout (
	workoutId		number(4) primary key,
	name 	varchar2(12)
);

create table Likey (
    userId     varchar(12) not null,
    reviewId     number(4) not null
);

create table Lesson (
	lessonId	number(4) primary key,
	trainerId	NUMBER(4),
	workoutId 	NUMBER(4),
	day			char(2),
	time		number(2),
);

create table WishList (
	 userId     varchar(12),
	 lessonId	number(4)
);

ALTER TABLE Likey ADD CONSTRAINT Likey_pk PRIMARY KEY (userid, reviewId);


ALTER TABLE Review ADD FOREIGN KEY (userId) REFERENCES UserInfo (userId);
ALTER TABLE Review ADD FOREIGN KEY (trainerId) REFERENCES Trainer (trainerId);
ALTER TABLE Review ADD FOREIGN KEY (workoutId) REFERENCES Workout (workoutId);

ALTER TABLE Trainer ADD FOREIGN KEY (workoutId) REFERENCES Workout (workoutId);

ALTER TABLE Likey ADD FOREIGN KEY (reviewId) REFERENCES Review (reviewId);
ALTER TABLE Likey ADD FOREIGN KEY (userId) REFERENCES UserInfo (userId);

CREATE SEQUENCE reviewId_seq
START WITH 1
INCREMENT BY 1;

INSERT INTO UserInfo VALUES ('admin', 'admin', 'admin@dongduk.ac.kr');
INSERT INTO UserInfo VALUES ('movieMan', 'movieman', 'young99@gmail.com');
INSERT INTO UserInfo VALUES ('mina', 'mina123', 'mnkim@naver.com');
INSERT INTO UserInfo VALUES ('rizzi', 'rizzi123', 'james@gmail.com');
INSERT INTO UserInfo VALUES ('barnes', 'barnes123', 'barnes@hotmail.com');

COMMIT;
