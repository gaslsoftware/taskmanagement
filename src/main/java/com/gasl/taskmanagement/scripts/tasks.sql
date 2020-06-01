CREATE TABLE tasks (
    task_id INT AUTO_INCREMENT PRIMARY KEY,
    task_name VARCHAR(50),
    task_description VARCHAR(1000),
    created_time DATETIME,
    updated_time DATETIME,
    due_time DATETIME,
    status VARCHAR(50),
    label VARCHAR(255),
    completed_date DATETIME,
    user_id INT,
    priority INT
    
);