-- DROP TABLE IF EXISTS CLIENT CASCADE;
CREATE TABLE  IF NOT EXISTS CLIENT (
  ID        SERIAL,
  FIRSTNAME VARCHAR(40) NOT NULL,
  LASTNAME  VARCHAR(40) NOT NULL,
  PRIMARY KEY (ID)
);

-- DROP TABLE IF EXISTS PRODUCT CASCADE;
CREATE TABLE  IF NOT EXISTS PRODUCT(
  ID       SERIAL,
  NAME     VARCHAR(50) NOT NULL,
  PRICE    FLOAT       NOT NULL,
  CURRENCY VARCHAR(3)  NOT NULL,
  PRIMARY KEY (ID)
);

-- DROP TABLE IF EXISTS ORDERS;
CREATE TABLE  IF NOT EXISTS ORDERS (
  ID         SERIAL,
  PRODUCT_ID INT REFERENCES PRODUCT (ID),
  QUANTITY   INT       NOT NULL,
  CLIENT_ID  INT REFERENCES CLIENT (ID),
  DATEBUY    TIMESTAMP NOT NULL,
  PRIMARY KEY (ID)
);