-- =========================================================
-- SMART STUDENT ATTENDANCE MANAGEMENT SYSTEM
-- DATABASE SETUP SCRIPT
-- =========================================================

CREATE DATABASE IF NOT EXISTS smart_student_attendance;

USE smart_student_attendance;


-- =========================================================
-- USERS TABLE
-- =========================================================

CREATE TABLE IF NOT EXISTS users (

    id INT PRIMARY KEY AUTO_INCREMENT,

    username VARCHAR(50) NOT NULL UNIQUE,

    password_hash VARCHAR(255) NOT NULL,

    role ENUM('ADMIN', 'STAFF')
        NOT NULL DEFAULT 'STAFF',

    created_at TIMESTAMP
        DEFAULT CURRENT_TIMESTAMP
);


-- =========================================================
-- STUDENTS TABLE
-- =========================================================

CREATE TABLE IF NOT EXISTS students (

    id INT PRIMARY KEY AUTO_INCREMENT,

    registration_no VARCHAR(50)
        NOT NULL UNIQUE,

    name VARCHAR(100)
        NOT NULL,

    email VARCHAR(150),

    phone VARCHAR(20),

    gender VARCHAR(20),

    department VARCHAR(100),

    year INT,

    section VARCHAR(20),

    qr_code VARCHAR(255)
        NOT NULL UNIQUE,

    created_at TIMESTAMP
        DEFAULT CURRENT_TIMESTAMP
);


-- =========================================================
-- ATTENDANCE TABLE
-- =========================================================

CREATE TABLE IF NOT EXISTS attendance (

    id INT PRIMARY KEY AUTO_INCREMENT,

    student_id INT
        NOT NULL,

    attendance_date DATE
        NOT NULL,

    check_in DATETIME,

    check_out DATETIME,

    CONSTRAINT fk_attendance_student

        FOREIGN KEY (student_id)

        REFERENCES students(id)

        ON DELETE CASCADE,

    CONSTRAINT unique_student_date

        UNIQUE (
            student_id,
            attendance_date
        )
);


-- =========================================================
-- ACADEMIC CALENDAR TABLE
-- =========================================================

CREATE TABLE IF NOT EXISTS academic_calendar (

    id INT PRIMARY KEY AUTO_INCREMENT,

    calendar_date DATE
        NOT NULL UNIQUE,

    is_working_day BOOLEAN
        NOT NULL DEFAULT TRUE,

    description VARCHAR(255)
);


-- =========================================================
-- OPTIONAL VERIFICATION
-- =========================================================

SELECT
    'Database setup completed successfully.'
    AS message;