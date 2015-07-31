# creating products tables
CREATE TABLE GUNS
(
  id          INT AUTO_INCREMENT PRIMARY KEY    NOT NULL,
  type        VARCHAR(40)                       NOT NULL,
  model       VARCHAR(60) UNIQUE                NOT NULL,
  price       DOUBLE                            NOT NULL,
  origin      VARCHAR(80),
  description TEXT
);
CREATE TABLE GUNS_TTC
(
  gun_id                 INT UNIQUE  NOT NULL,
  firing_range           INT,
  effective_firing_range INT,
  magazine_capacity      INT,
  caliber                VARCHAR(25),
  fire_rate              VARCHAR(15),
  FOREIGN KEY (gun_id) REFERENCES GUNS (id)
);
CREATE TABLE BULLETS
(
  id          INT AUTO_INCREMENT PRIMARY KEY    NOT NULL,
  caliber     VARCHAR(25)                       NOT NULL,
  name        VARCHAR(40),
  bullet_type VARCHAR(50)                       NOT NULL,
  price       DOUBLE                            NOT NULL,
  description TEXT,
  UNIQUE (caliber, name)
);

# creating users tables
# TODO make tables for users and admins