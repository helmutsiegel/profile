INSERT INTO t_user(id, first_name, last_name, title, user_name) VALUES (1, 'Max', 'Mustermann', 'Java Developper', 'maxmuster');
INSERT INTO t_user(id, first_name, last_name, title, user_name) VALUES (2, 'John', 'Doe', 'Technical lead', 'johndoe');

INSERT INTO t_cv(id, about, userentity_id) VALUES (1, 'I am Max, and this is my about', 1);
INSERT INTO t_cv(id, about, userentity_id) VALUES (2, 'I am John, and this is my about', 2);

INSERT INTO t_experience(id, company, start_date, end_date, cv_id) VALUES (1, 'IBM', '11/11/2010', '11/11/2020', 1);
INSERT INTO t_experience(id, company, start_date, end_date, cv_id) VALUES (2, 'Facebook', '11/11/2007', '10/11/2010', 1);