CREATE SCHEMA CareerInfo;

CREATE TABLE CareerInfo.Career (
    career_id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    pay_range_low DECIMAL(10, 2),
    pay_range_high DECIMAL(10, 2),
    risk_level INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE CareerInfo.Education (
    education_id INT PRIMARY KEY AUTO_INCREMENT,
    career_id INT,
    education_level VARCHAR(255) NOT NULL,
    years_of_schooling INT,
    education_description TEXT,
    FOREIGN KEY (career_id) REFERENCES Career(career_id)
);

CREATE TABLE CareerInfo.Risks (
    risk_id INT PRIMARY KEY AUTO_INCREMENT,
    career_id INT,
    risk_description TEXT,
    risk_level INT,
    FOREIGN KEY (career_id) REFERENCES Career(career_id)
);
