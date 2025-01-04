DROP TABLE IF EXISTS "users";

CREATE TABLE IF NOT EXISTS "users"
(
    user_id UUID NOT NULL UNIQUE PRIMARY KEY,
    user_name text NOT NULL,
    user_surname text NOT NULL,
    user_email text NOT NULL UNIQUE,
    user_password text NOT NULL
);

INSERT INTO "users"
VALUES ('80a98094-ccf7-49ab-a090-9394be7f9d1b', 'test_name', 'test_surname', 'user@test', 'user');