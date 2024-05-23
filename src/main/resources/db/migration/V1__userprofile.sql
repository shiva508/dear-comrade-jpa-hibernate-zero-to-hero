CREATE TABLE users_migration (
  user_id INTEGER PRIMARY KEY,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL
);
ALTER TABLE users_migration ADD phone_number VARCHAR(13);