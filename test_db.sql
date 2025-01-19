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
VALUES(
	'123e4567-e89b-12d3-a456-426655440000', 'test_name', 'test_surname', 'user@test', 'user'
);

SELECT * FROM "users";

SELECT * FROM "users" WHERE user_email='user@test';