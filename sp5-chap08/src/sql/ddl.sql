create user 'spring5'@'localhost' identified by 'spring5';

create database sp5basic character set=utf8;
use sp5basic;

grant all privileges on sp5basic.* to 'spring5'@'localhost';	
alter user 'spring5'@'localhost' identified with mysql_native_password by 'spring5';

create table MEMBER(
	ID INT AUTO_INCREMENT PRIMARY KEY,
    EMAIL VARCHAR(255),
    PASSWORD VARCHAR(100),
    NAME VARCHAR(100),
    REGDATE DATETIME,
    UNIQUE KEY (EMAIL)
    ) ENGINE=InnoDB CHARACTER SET=UTF8;
    
    
SELECT *
	FROM MEMBER;
    
   
INSERT INTO MEMBER (EMAIL, PASSWORD, NAME, REGDATE)
	VALUES ('seungseob915@gmail.com', '1234', 'PSS', NOW());
    
UPDATE MEMBER
	SET PASSWORD='1234'
    WHERE ID=1;