CREATE TABLE IF NOT EXISTS "users"
(
    user_id UUID NOT NULL UNIQUE PRIMARY KEY,
    user_name text NOT NULL,
    user_surname text NOT NULL,
    user_email text NOT NULL UNIQUE,
    user_password text NOT NULL
);