-- creating products tables
CREATE TABLE GUNS_TYPES
(
  type VARCHAR(50) UNIQUE                  NOT NULL
);
CREATE TABLE BULLETS_TYPES
(
  type VARCHAR(50) UNIQUE                  NOT NULL
);
CREATE TABLE GUNS
(
  id    BIGINT AUTO_INCREMENT PRIMARY KEY     NOT NULL,
  uuid  UUID UNIQUE                           NOT NULL,
  type  VARCHAR(40)                           NOT NULL,
  model VARCHAR(60) UNIQUE                    NOT NULL,
  price DOUBLE                                NOT NULL,
  origin      VARCHAR(80),
  description TEXT,
  FOREIGN KEY (type) REFERENCES GUNS_TYPES (type)
);
CREATE TABLE GUNS_TTC
(
  gun_uuid               UUID UNIQUE           NOT NULL,
  firing_range           INT,
  effective_firing_range INT,
  magazine_capacity      INT,
  caliber                VARCHAR(25),
  fire_rate              INT,
  FOREIGN KEY (gun_uuid) REFERENCES GUNS (uuid),
  UNIQUE (gun_uuid, firing_range, effective_firing_range, magazine_capacity, caliber, fire_rate)
);
CREATE TABLE BULLETS
(
  id          BIGINT AUTO_INCREMENT PRIMARY KEY    NOT NULL,
  uuid        UUID UNIQUE                          NOT NULL,
  caliber     VARCHAR(25)                          NOT NULL,
  name        VARCHAR(40)                          NOT NULL,
  bullet_type VARCHAR(50)                          NOT NULL,
  price       DOUBLE                               NOT NULL,
  Qty         INT,
  description TEXT,
  UNIQUE (caliber, name, bullet_type),
  FOREIGN KEY (bullet_type) REFERENCES BULLETS_TYPES (type)
);

-- creating users tables
CREATE TABLE ROLE (
  role VARCHAR(30) UNIQUE                  NOT NULL
);
CREATE TABLE USERS
(
  id       BIGINT AUTO_INCREMENT PRIMARY KEY   NOT NULL,
  uuid     UUID UNIQUE                         NOT NULL,
  login    VARCHAR(60) UNIQUE                  NOT NULL,
  email    VARCHAR(60) UNIQUE                  NOT NULL,
  role     VARCHAR(30)                         NOT NULL,
  password VARCHAR(60) NOT NULL,
  FOREIGN KEY (role) REFERENCES ROLE (role)
);