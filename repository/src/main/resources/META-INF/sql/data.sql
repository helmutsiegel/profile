INSERT INTO t_user(id, first_name, last_name, title, user_name, birth_date) VALUES (1, 'Max', 'Mustermann', 'Java Developper', 'maxmuster', '10/12/1995');
INSERT INTO t_user(id, first_name, last_name, title, user_name, birth_date) VALUES (2, 'John', 'Doe', 'Technical lead', 'johndoe', '02/06/1985');

INSERT INTO t_cv(id, about, userentity_id) VALUES (1, 'I am Max, and this is my about', 1);
INSERT INTO t_cv(id, about, userentity_id) VALUES (2, 'I am John, and this is my about', 2);

INSERT INTO t_experience(id, company, start_date, end_date, cv_id) VALUES (1, 'IBM', '11/11/2010', '11/11/2020', 1);
INSERT INTO t_experience(id, company, start_date, end_date, cv_id) VALUES (2, 'Facebook', '11/11/2007', '10/11/2010', 1);

INSERT INTO t_project(id, name, userentity_id) VALUES (1, 'Sample', 1);
INSERT INTO t_project(id, name, userentity_id) VALUES (2, 'Sample2', 1);
INSERT INTO t_project(id, name, userentity_id) VALUES (3, 'SampleSample', 1);