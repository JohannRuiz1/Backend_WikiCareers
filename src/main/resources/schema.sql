CREATE TABLE IF NOT EXISTS Careers (
    career_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    pay_range_low DECIMAL(10, 2),
    pay_range_high DECIMAL(10, 2),
    risk_level INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS Education (
    education_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    career_id INT,
    education_level VARCHAR(255) NOT NULL,
    years_of_schooling INT,
    education_description TEXT,
    FOREIGN KEY (career_id) REFERENCES Careers(career_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Risks (
    risk_id INT PRIMARY KEY AUTO_INCREMENT,
    career_id INT,
    risk_description TEXT,
    risk_level INT,
    FOREIGN KEY (career_id) REFERENCES Careers(career_id) ON DELETE CASCADE
);