CREATE TABLE shows
(
  id          serial PRIMARY KEY,
  show_date   timestamp NOT NULL,
  premiere    boolean   NOT NULL,
  performance integer REFERENCES performance (id) ON DELETE CASCADE
);