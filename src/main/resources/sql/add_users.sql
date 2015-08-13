INSERT INTO ROLE VALUES (DEFAULT, 'ADMIN');
INSERT INTO ROLE VALUES (DEFAULT, 'USER');
INSERT INTO ROLE VALUES (DEFAULT, 'GUEST');
INSERT INTO USERS VALUES (DEFAULT, random_uuid(), 'test', 'test@test', 'ADMIN', 'test');
INSERT INTO USERS VALUES (DEFAULT, random_uuid(), 'abc', 'abc@abc', 'USER', '123');
INSERT INTO USERS VALUES (DEFAULT, random_uuid(), 'sa', 'sa@sa', 'USER', 'sa');
INSERT INTO USERS VALUES (DEFAULT, random_uuid(), 'tester', 'tester@tester', 'USER', 'tester');