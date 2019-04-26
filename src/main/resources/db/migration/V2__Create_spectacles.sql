CREATE TABLE genres
(
  id   SERIAL PRIMARY KEY,
  name varchar(50) NOT NULL UNIQUE
);

CREATE TABLE spectacles
(
  id           SERIAL PRIMARY KEY,
  name         varchar(50)                    NOT NULL UNIQUE,
  genre        integer REFERENCES genres (id) NOT NULL,
  age_category integer                        NOT NULL,
  author_id    integer REFERENCES authors (id) ON DELETE CASCADE
);