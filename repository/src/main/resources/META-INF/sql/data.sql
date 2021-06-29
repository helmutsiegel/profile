INSERT INTO t_user(id, first_name, last_name, title, email, birth_date, seniority, password) VALUES (10001, 'Max', 'Mustermann', 'Java Developer', 'maxmuster@dummy.mail', '10/12/1995',1, 'd8lT6FWVtqT9wWCXj9rCfKQbsGEm3UI24Ca9xqBMosE=');
INSERT INTO t_user(id, first_name, last_name, title, email, birth_date, seniority, password) VALUES (10002, 'John', 'Doe', 'Technical lead', 'johndoe@dummy.mail', '02/06/1985',2,'wnE7YskDeRve/Fpqmd8E1DMN5JG7x6DKalAHM35KYCg=');
INSERT INTO t_user(id, first_name, last_name, title, email, birth_date, seniority, password) VALUES (10003, 'Peter', 'Pan', '.Net Developer', 'peterpan@dummy.mail', '10/10/1995',0, '51Om08Dp0itGKyRmX8m17je1RFTiGZQ8c0F76RvAdGU=');
INSERT INTO t_user(id, first_name, last_name, title, email, birth_date, seniority, password) VALUES (10004, 'James', 'Bond', 'Angular Technical Coach', 'jamesbond@dummy.mail', '01/10/1988',1, 'i112SdRDM046mx3YBwTUg422V4rOHT1yzQVB3Sie4Gk=');
INSERT INTO t_user(id, first_name, last_name, title, email, birth_date, seniority, password) VALUES (10005, 'John', 'Rambo', 'System Admin', 'johnrambo@dummy.mail', '05/15/1968',1, 'qz8nMpa9DEG0tjX/zlnMqCYTseB4UKUjArn1iEocXLo=');
INSERT INTO t_user(id, first_name, last_name, title, email, birth_date, seniority, password) VALUES (10006, 'Barney', 'Stinson', 'CEO', 'barney@dummy.mail', '05/18/1968',2, 'JqBTcGE8z1bBDdwkOeMZ3ICKqc0U6o4bZL3g/GAtPJ0=');

INSERT INTO t_cv(id, short_about, long_about, userentity_id) VALUES (10001, 'I am Max, and this is my about', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry''s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum', 10001);
INSERT INTO t_cv(id, short_about, long_about, userentity_id) VALUES (10002, 'I am John, and this is my about', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry''s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum',10002);
INSERT INTO t_cv(id, short_about, long_about, userentity_id) VALUES (10003, 'I am Peter Pan, and this is my about', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry''s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum',10003);
INSERT INTO t_cv(id, short_about, long_about, userentity_id) VALUES (10004, 'My name is Bond, James Bond and this is my about', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry''s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum',10004);
INSERT INTO t_cv(id, short_about, long_about, userentity_id) VALUES (10005, 'Hey guys, this is Rambo and this is my about', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry''s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum',10005);
INSERT INTO t_cv(id, short_about, long_about, userentity_id) VALUES (10006, 'Hey guys, this is Barney and my about is legen, wait for it .... dary','Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry''s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum', 10006);

INSERT INTO t_experience(id, company, start_date, end_date, position, cv_id) VALUES (10001, 'IBM', '11/11/2010', '11/11/2020', 'Java Developper', 10001);
INSERT INTO t_experience(id, company, start_date, end_date, position, cv_id) VALUES (10002, 'Facebook', '11/11/2007', '10/11/2010', 'Frontend Technical Lead', 10001);

INSERT INTO t_project(id, name, userentity_id, description) VALUES (10001, 'Sample', 10001, 'Short description of the project Sample');
INSERT INTO t_project(id, name, userentity_id, description) VALUES (10002, 'Sample2', 10001, 'Short description of the project Sample2');
INSERT INTO t_project(id, name, userentity_id, description) VALUES (10003, 'SampleSample', 10001, 'Short description of the project SampleSample');

INSERT INTO t_language(id, language, level, cv_id) VALUES (10001, 'English', '5', 10001);
INSERT INTO t_language(id, language, level, cv_id) VALUES (10002, 'German', '0', 10001);

INSERT INTO t_certification(id, date, expiration_date, issued_by, name, cv_id) VALUES (10001, '11/11/2010', null , 'Oracle', 'OCA', 10001);
INSERT INTO t_certification(id, date, expiration_date, issued_by, name, cv_id) VALUES (10002, '01/01/2015', '01/01/2025' , 'AWS', 'Associate AWS Developper', 10001);

INSERT INTO t_chapter(id, title, project_id) VALUES (10001, 'Project Overview', 10001);
INSERT INTO t_chapter(id, title, project_id) VALUES (10002, 'Architecture', 10001);
INSERT INTO t_chapter(id, title, project_id) VALUES (10003, 'Conclusion', 10001);

INSERT INTO t_section(id, description, title, chapter_id) VALUES (10001, 'Here will be the description of the purpose of the project', 'Purpose', 10001);

INSERT INTO t_section(id, description, title, chapter_id) VALUES (10002, 'Here will be the description of the architecture of the project', 'Overall architecture', 10002);
INSERT INTO t_section(id, description, title, chapter_id) VALUES (10003, 'Here will be the description of the database ', 'Database', 10002);
INSERT INTO t_section(id, description, title, chapter_id) VALUES (10004, 'Long long description of business', 'Business layer', 10002);
INSERT INTO t_section(id, description, title, chapter_id) VALUES (10005, 'Long long description of the UI', 'Presentation layer', 10002);

INSERT INTO t_section(id, description, title, chapter_id) VALUES (10006, 'Long long long long conclusion ', 'Conclusion', 10003);


-- Inserts for Helmut Siegel ----
INSERT INTO t_user(id, first_name, last_name, title, email, birth_date, seniority, password) VALUES (20001, 'Helmut', 'Siegel', 'Full-Stack Developer', 'helmutsiegel17@gmail.com', '07/30/1995',2, 'd8lT6FWVtqT9wWCXj9rCfKQbsGEm3UI24Ca9xqBMosE=');
INSERT INTO t_cv(id, short_about, long_about, userentity_id) VALUES (20001, 'My short about', 'My long about', 20001);
INSERT INTO t_experience(id, company, start_date, end_date, position, cv_id) VALUES (20001, 'msg systems Romania', '07/04/2016', '08/12/2016', 'Intern', 20001);
INSERT INTO t_experience(id, company, start_date, end_date, position, cv_id) VALUES (20002, 'msg systems Romania', '09/19/2016', '11/14/2020', 'Full-stack developer', 20001);
INSERT INTO t_experience(id, company, start_date, position, cv_id) VALUES (20003, 'Accesa', '11/16/2016', 'Full-stack developer', 20001);
INSERT INTO t_project(id, name, userentity_id, description) VALUES (20001, 'PROFile', 20001, 'Short description of the project Profile');
INSERT INTO t_certification(id, date, expiration_date, issued_by, name, cv_id) VALUES (20001, '11/01/2019', null , 'Oracle', 'OCA', 20001);
INSERT INTO t_language(id, language, level, cv_id) VALUES (20001, 'English', '3', 20001);
INSERT INTO t_language(id, language, level, cv_id) VALUES (20002, 'German', '3', 20001);
INSERT INTO t_language(id, language, level, cv_id) VALUES (20003, 'Romanian', '4', 20001);
INSERT INTO t_language(id, language, level, cv_id) VALUES (20004, 'Hungarian', '5', 20001);
INSERT INTO t_chapter(id, title, project_id) VALUES (20001, 'Project Overview', 20001);
INSERT INTO t_section(id, description, title, chapter_id) VALUES (20001, 'Coming soon..', 'Purpose', 20001);
-- End of Inserts for Helmut Siegel ----