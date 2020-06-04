CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    user_name VARCHAR(50) UNIQUE,
    password VARCHAR(1000),
    email_address VARCHAR(500),
    mobile_number INT
);