INSERT INTO ROLE VALUES ('ADMIN');
INSERT INTO ROLE VALUES ('USER');
INSERT INTO USERS VALUES (DEFAULT, random_uuid(), 'test', 'test@test', 'ADMIN',
                          '1000:5b42403565643839613366:5950d28d38b951a477c396a3ba33de1e054f628c331b70645647416d626a3675f7862e3455e065c312330802b8a9208d704757d89fa62236e14a5b4057fa9d22');
-- INSERT INTO USERS VALUES (DEFAULT, random_uuid(), 'abc', 'abc@abc', 'USER', '123');
-- INSERT INTO USERS VALUES (DEFAULT, random_uuid(), 'sa', 'sa@sa', 'USER', 'sa');
-- INSERT INTO USERS VALUES (DEFAULT, random_uuid(), 'tester', 'tester@tester', 'USER', 'tester');