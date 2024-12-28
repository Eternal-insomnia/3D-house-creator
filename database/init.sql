DROP TABLE IF EXISTS "users";

CREATE TABLE IF NOT EXISTS "users"
(
    user_id serial NOT NULL UNIQUE PRIMARY KEY,
    user_name text,
    user_email text,
    user_password text
);

INSERT INTO "users" (user_name, user_email, user_password)
VALUES ('test_user', 'user', 'user');