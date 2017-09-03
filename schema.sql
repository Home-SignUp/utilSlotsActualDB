DROP TABLE IF EXISTS account;
CREATE TABLE account(
  id SERIAL,
  owner VARCHAR(50) NOT NULL,
  balance DOUBLE PRECISION NOT NULL,
  PRIMARY KEY (id)
);