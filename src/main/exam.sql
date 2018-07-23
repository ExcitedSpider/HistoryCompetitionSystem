/**
server:localhost:3306
database: HC

user:root
password:1802085
 */
CREATE TABLE TFQUESTION(
  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  question VARCHAR(500) NOT NULL ,
  answer INT NOT NULL
);

INSERT INTO TFQUESTION (question, answer) VALUES ('问题1',0);

CREATE TABLE CQUESTION(
  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  question VARCHAR(500) NOT NULL,
  choices VARCHAR(400),
  answer INT NOT NULL
);

INSERT INTO CQUESTION (question, choices, answer) VALUES ('问题5','选项1,选项2,选项3,选项4',1);


SELECT count(1) FROM CQUESTION