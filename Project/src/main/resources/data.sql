INSERT INTO project(customer_id, description, name, url) VALUES (1, 'Ma description de mon projet 1', 'Mon Projet 1', 'www.monprojet1.com');
INSERT INTO project(customer_id, description, name, url) VALUES (1, 'Ma description de mon projet 2', 'Mon Projet 2', 'www.monprojet2.com');
INSERT INTO project(customer_id, description, name, url) VALUES (2, 'Ma description de mon projet 3', 'Mon Projet 2', 'www.monprojet2.com');
INSERT INTO contributor(employee_id, project_id) VALUES (1, 1);
INSERT INTO contributor(employee_id, project_id) values (2, 2);
INSERT INTO contributor(employee_id, project_id) values (1, 3);