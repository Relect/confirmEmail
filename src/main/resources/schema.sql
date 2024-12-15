
    CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_name VARCHAR(200),
    email VARCHAR(200) UNIQUE,
    password VARCHAR(200),
    is_enabled boolean DEFAULT false);

    CREATE TABLE confirm (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    token VARCHAR(255),
    created TIMESTAMP,
    user_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES users(id));