INSERT INTO GUNS VALUES (DEFAULT, random_uuid(), 'Assault Rifle', 'Ak-47', 10000, 'USSR', 'ak from ussr');
INSERT INTO GUNS_TTC VALUES ((SELECT UUID
                              FROM GUNS
                              WHERE ID = 1), 1000, 500, 30, '7.62x39mm', 600);
INSERT INTO GUNS VALUES (DEFAULT, random_uuid(), 'pistol', 'five seven', 5000, 'USA', 'simple five seven');
INSERT INTO GUNS_TTC VALUES ((SELECT UUID
                              FROM GUNS
                              WHERE ID = 2), 200, 50, 20, '5.7x28mm', NULL);
INSERT INTO BULLETS VALUES (DEFAULT, random_uuid(), '7.62x39mm', 'BEAR BROWN', NULL, 140, 300, NULL);
INSERT INTO BULLETS VALUES (DEFAULT, random_uuid(), '7.62x39mm', 'BEAR SILVER', NULL, 160, 300, NULL);
INSERT INTO BULLETS VALUES (DEFAULT, random_uuid(), '5.7x28mm', 'Federal', NULL, 24, 500, NULL);