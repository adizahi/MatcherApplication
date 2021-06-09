DROP TABLE IF EXISTS skill;
--DROP TABLE IF EXISTS candidate;

CREATE TABLE SKILL (
  id INT auto_increment PRIMARY KEY,
  name VARCHAR(250) NOT NULL
);

--CREATE TABLE CANDIDATE (
--  id INT PRIMARY KEY,
--  title VARCHAR(250) NOT NULL,
--  image_url VARCHAR(250) NOT NULL,
--  skill_id INT,
--  foreign key (skill_id) references skill
--);