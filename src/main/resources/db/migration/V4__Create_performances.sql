CREATE SEQUENCE performances_sqs;

CREATE TABLE performances
(
  id        bigint PRIMARY KEY DEFAULT NEXTVAL('performances_sqs'),
  production_designer varchar(256),
  production_director varchar(256),
  production_conductor varchar(256),
  season int
);

ALTER TABLE goes_to
  ADD COLUMN performance_id bigint NOT NULL REFERENCES performances(id) DEFAULT 0;

ALTER TABLE goes_to ALTER COLUMN performance_id DROP DEFAULT;

ALTER TABLE contains
  ADD COLUMN performance_id bigint NOT NULL REFERENCES performances(id) DEFAULT 0;

ALTER TABLE contains ALTER COLUMN performance_id DROP DEFAULT;