create user 'scott'@'locahost' identified by 'tiger';
create user 'scott'@'%' identified by 'tiger';
GRANT ALL privileges ON *.* TO 'scott'@'locahost';
GRANT ALL privileges ON *.* TO 'scott'@'%';
flush privileges;

create database 12kchess;
USE 12kchess;

CREATE TABLE USER(
ID VARCHAR(100) PRIMARY KEY,
PW VARCHAR(100) NOT NULL,
NAME VARCHAR(200) NOT NULL,
BIRTH DATE NOT NULL,
TELL VARCHAR(15) NOT NULL,
AUTHORITY VARCHAR(2) NOT NULL
);
ALTER TABLE USER ADD CONSTRAINT CK_ID CHECK (LENGTH(ID) > 3);
ALTER TABLE USER ADD CONSTRAINT CK_PW CHECK (LENGTH(PW) > 3);

INSERT INTO user VALUES('user01','1111','US3',NOW(),'0-0-0','C');
INSERT INTO user VALUES('user02','1111','US2',NOW(),'0-0-0','C');
INSERT INTO user VALUES('user03','1111','US3',NOW(),'0-0-0','C');
INSERT INTO user VALUES('user04','1111','US3',NOW(),'0-0-0','C');
INSERT INTO user VALUES('user05','1111','US3',NOW(),'0-0-0','C');




CREATE TABLE COMMANT(
CKEY VARCHAR(100) PRIMARY KEY,
CONTENT TEXT(20000),
REF INT NOT NULL DEFAULT 0,
STEP INT NOT NULL DEFAULT 0,
LEV INT NOT NULL DEFAULT 0,
GOOD INT NOT NULL DEFAULT 0,
BAD  INT NOT NULL DEFAULT 0,
ID VARCHAR(100) NOT NULL,
PBOARD VARCHAR(100) NOT NULL,
wDate DATETIME NOT NULL,
FOREIGN KEY (ID) REFERENCES USER(ID)
);


