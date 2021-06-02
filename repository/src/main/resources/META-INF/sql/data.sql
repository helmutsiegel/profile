INSERT INTO t_user(id, first_name, last_name, title, user_name, birth_date) VALUES (10001, 'Max', 'Mustermann', 'Java Developper', 'maxmuster', '10/12/1995');
INSERT INTO t_user(id, first_name, last_name, title, user_name, birth_date) VALUES (10002, 'John', 'Doe', 'Technical lead', 'johndoe', '02/06/1985');

INSERT INTO t_cv(id, about, userentity_id) VALUES (10001, 'I am Max, and this is my about', 10001);
INSERT INTO t_cv(id, about, userentity_id) VALUES (10002, 'I am John, and this is my about', 10002);

INSERT INTO t_experience(id, company, start_date, end_date, position, cv_id) VALUES (10001, 'IBM', '11/11/2010', '11/11/2020', 'Java Developper', 10001);
INSERT INTO t_experience(id, company, start_date, end_date, position, cv_id) VALUES (10002, 'Facebook', '11/11/2007', '10/11/2010', 'Frontend Technical Lead', 10001);

INSERT INTO t_project(id, name, userentity_id) VALUES (10001, 'Sample', 10001);
INSERT INTO t_project(id, name, userentity_id) VALUES (10002, 'Sample2', 10001);
INSERT INTO t_project(id, name, userentity_id) VALUES (10003, 'SampleSample', 10001);

INSERT INTO t_language(id, language, level, cv_id) VALUES (10001, 'English', '5', 10001);
INSERT INTO t_language(id, language, level, cv_id) VALUES (10002, 'German', '0', 10001);

INSERT INTO t_certification(id, date, expiration_date, issued_by, name, cv_id) VALUES (10001, '11/11/2010', null , 'Oracle', 'OCA', 10001);
INSERT INTO t_certification(id, date, expiration_date, issued_by, name, cv_id) VALUES (10002, '01/01/2015', '01/01/2025' , 'AWS', 'Associate AWS Developper', 10001);