-- Create a development/admin account after the schema is installed.
-- Change the placeholder password before executing.

USE smart_student_attendance;

SET @admin_password = 'CHANGE_ME_BEFORE_RUN';

INSERT INTO users (username, password_hash, role)
VALUES ('admin', SHA2(@admin_password, 256), 'ADMIN')
ON DUPLICATE KEY UPDATE
    password_hash = VALUES(password_hash),
    role = VALUES(role);
