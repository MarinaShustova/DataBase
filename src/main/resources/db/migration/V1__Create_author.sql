CREATE TABLE countries
(
  id   SERIAL PRIMARY KEY,
  name varchar(50) NOT NULL UNIQUE
);

CREATE TABLE authors
(
  id         SERIAL PRIMARY KEY,
  surname    varchar(50)                       NOT NULL,
  name       varchar(50)                       NOT NULL,
  birth_date date                              NOT NULL,
  death_date date,
  country    integer REFERENCES countries (id) NOT NULL
);