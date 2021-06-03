INSERT INTO t_user(id, first_name, last_name, title, user_name, birth_date, seniority) VALUES (10001, 'Max', 'Mustermann', 'Java Developper', 'maxmuster', '10/12/1995',1);
INSERT INTO t_user(id, first_name, last_name, title, user_name, birth_date, seniority) VALUES (10002, 'John', 'Doe', 'Technical lead', 'johndoe', '02/06/1985',2);
INSERT INTO t_user(id, first_name, last_name, title, user_name, birth_date, seniority) VALUES (10003, 'Peter', 'Pan', '.Net Developper', 'peterpan', '10/10/1995',0);
INSERT INTO t_user(id, first_name, last_name, title, user_name, birth_date, seniority) VALUES (10004, 'James', 'Bond', 'Angular Technical Coach', 'jamesbond', '01/10/1988',1);
INSERT INTO t_user(id, first_name, last_name, title, user_name, birth_date, seniority) VALUES (10005, 'John', 'Rambo', 'System Admin', 'johnrambo', '16/05/1968',1);

INSERT INTO t_cv(id, about, userentity_id) VALUES (10001, 'I am Max, and this is my about', 10001);
INSERT INTO t_cv(id, about, userentity_id) VALUES (10002, 'I am John, and this is my about', 10002);
INSERT INTO t_cv(id, about, userentity_id) VALUES (10003, 'I am Peter Pan, and this is my about', 10003);
INSERT INTO t_cv(id, about, userentity_id) VALUES (10004, 'My name is Bond, James Bond and this is my about', 10004);
INSERT INTO t_cv(id, about, userentity_id) VALUES (10005, 'Hey guys, this is Rambo and this is my about', 10004);

INSERT INTO t_experience(id, company, start_date, end_date, position, cv_id) VALUES (10001, 'IBM', '11/11/2010', '11/11/2020', 'Java Developper', 10001);
INSERT INTO t_experience(id, company, start_date, end_date, position, cv_id) VALUES (10002, 'Facebook', '11/11/2007', '10/11/2010', 'Frontend Technical Lead', 10001);

INSERT INTO t_project(id, name, userentity_id) VALUES (10001, 'Sample', 10001);
INSERT INTO t_project(id, name, userentity_id) VALUES (10002, 'Sample2', 10001);
INSERT INTO t_project(id, name, userentity_id) VALUES (10003, 'SampleSample', 10001);

INSERT INTO t_language(id, language, level, cv_id) VALUES (10001, 'English', '5', 10001);
INSERT INTO t_language(id, language, level, cv_id) VALUES (10002, 'German', '0', 10001);

INSERT INTO t_certification(id, date, expiration_date, issued_by, name, cv_id) VALUES (10001, '11/11/2010', null , 'Oracle', 'OCA', 10001);
INSERT INTO t_certification(id, date, expiration_date, issued_by, name, cv_id) VALUES (10002, '01/01/2015', '01/01/2025' , 'AWS', 'Associate AWS Developper', 10001);