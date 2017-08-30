DROP TABLE IF EXISTS CLIENT  CASCADE;
CREATE TABLE CLIENT (
  ID            SERIAL,
  FIRSTNAME     VARCHAR(40) NOT NULL,
  LASTNAME      VARCHAR(40) NOT NULL,
  PRIMARY KEY (ID)
);

DROP TABLE IF EXISTS PRODUCT CASCADE;
CREATE TABLE PRODUCT (
  ID            SERIAL,
  NAME          VARCHAR(50) NOT NULL,
  PRICE         FLOAT       NOT NULL,
  CURRENCY      MONEY       NOT NULL,
  PRIMARY KEY (ID)
);

DROP TABLE IF EXISTS ORDERS;
CREATE TABLE ORDERS (
  ID            SERIAL,
  ID_PRODUCT    INT         REFERENCES  PRODUCT(ID),
  QUANTITY      INT         NOT NULL,
  ID_CLIENT     INT         REFERENCES  CLIENT(ID),
  DATEBUY       DATE        NOT NULL,
  PRIMARY KEY (ID)
);