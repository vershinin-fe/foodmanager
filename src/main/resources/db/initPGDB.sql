DROP TABLE IF EXISTS items;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS families;
DROP SEQUENCE IF EXISTS GLOBAL_SEQ;

CREATE SEQUENCE GLOBAL_SEQ AS INTEGER START WITH 100;

CREATE TABLE families (
  id               INTEGER PRIMARY KEY DEFAULT nextval('GLOBAL_SEQ'),
  name             VARCHAR(110)             NOT NULL
);

CREATE TABLE items (
  id               INTEGER PRIMARY KEY DEFAULT nextval('GLOBAL_SEQ'),
  name             VARCHAR(110)             NOT NULL,
  quantity         INTEGER                  NOT NULL,
  mu               VARCHAR(110)             NOT NULL,
  description      VARCHAR(255)                    ,
  price            INTEGER                         ,
  closed           BOOLEAN DEFAULT FALSE   NOT NULL,
  createdate       TIMESTAMP DEFAULT now() NOT NULL,
  closedate        TIMESTAMP,
  family_id        INTEGER                 NOT NULL,
  FOREIGN KEY (family_id) REFERENCES families(id) ON DELETE CASCADE
);

CREATE TABLE users
(
  id               INTEGER PRIMARY KEY DEFAULT nextval('GLOBAL_SEQ'),
  name             VARCHAR(110)            NOT NULL,
  email            VARCHAR(110)            NOT NULL,
  password         VARCHAR(110)            NOT NULL,
  enabled          BOOLEAN DEFAULT TRUE    NOT NULL,
  role             VARCHAR(110),
  family_id        INTEGER,
  FOREIGN KEY (family_id) REFERENCES families(id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX users_unique_email_idx ON USERS (email);