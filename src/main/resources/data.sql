
-- Вставка начальных данных в таблицу Employee
INSERT INTO employee (employee_name) VALUES ('Bob1');
INSERT INTO employee (employee_name) VALUES ('Bob2');
INSERT INTO employee (employee_name) VALUES ('Bob3');

-- Вставка начальных данных в таблицу Project
INSERT INTO project (project_name) VALUES ('Spring1');
INSERT INTO project (project_name) VALUES ('Spring2');
INSERT INTO project (project_name) VALUES ('Spring3');

-- Вставка начальных данных в таблицу Employee_Project
INSERT INTO employee_project (employee_id, project_id) VALUES (1, 1);
INSERT INTO employee_project (employee_id, project_id) VALUES (1, 2);
INSERT INTO employee_project (employee_id, project_id) VALUES (1, 3);
INSERT INTO employee_project (employee_id, project_id) VALUES (2, 2);
INSERT INTO employee_project (employee_id, project_id) VALUES (3, 3);

-- Вставка начальных данных в таблицу Timesheet
INSERT INTO timesheet (timesheet_project_id, timesheet_employee_id, minutes, created_at) VALUES (1, 1, 800, '2024-01-01');
INSERT INTO timesheet (timesheet_project_id, timesheet_employee_id, minutes, created_at) VALUES (2, 1, 200, '2024-01-02');
INSERT INTO timesheet (timesheet_project_id, timesheet_employee_id, minutes, created_at) VALUES (2, 2, 300, '2024-01-03');
INSERT INTO timesheet (timesheet_project_id, timesheet_employee_id, minutes, created_at) VALUES (3, 2, 500, '2024-01-04');
INSERT INTO timesheet (timesheet_project_id, timesheet_employee_id, minutes, created_at) VALUES (3, 3, 70, '2024-01-05');

-- Вставка начальных данных в таблицу roles
INSERT INTO roles (role_name) VALUES ('ADMIN');
INSERT INTO roles (role_name) VALUES ('USER');
INSERT INTO roles (role_name) VALUES ('REST');

-- Вставка начальных данных в таблицу users
INSERT INTO users (login, password) VALUES ('admin', 'admin'); -- password: admin
INSERT INTO users (login, password) VALUES ('user', 'user'); -- password: user
INSERT INTO users (login, password) VALUES ('rest', 'rest');

-- Вставка начальных данных в таблицу user_roles
INSERT INTO user_roles (user_id, role_id) VALUES (1, 1); -- admin -> ADMIN
INSERT INTO user_roles (user_id, role_id) VALUES (2, 2); -- user -> USER
INSERT INTO user_roles (user_id, role_id) VALUES (3, 3); -- rest -> REST
