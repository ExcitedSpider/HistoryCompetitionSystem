/**
server:localhost:3306
database: HC

user:root
password:1802085
 */
CREATE DATABASE HC;
CREATE TABLE users (
  id VARCHAR(10) NOT NULL PRIMARY KEY,
  password VARCHAR(20) NOT NULL,
  grade INT DEFAULT 0
);


CREATE TABLE admin (
  id VARCHAR(10) NOT NULL PRIMARY KEY,
  password VARCHAR(20) NOT NULL
);

INSERT INTO users (id,password)VALUES ("71117233","123456");
UPDATE users SET id = "123456" WHERE id = "71117233";
INSERT INTO admin (id,password)VALUES ("71117233","123456");