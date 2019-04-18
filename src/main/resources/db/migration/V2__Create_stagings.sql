CREATE SEQUENCE stagings_sqs;

CREATE TABLE stagings
(
  id        bigint PRIMARY KEY DEFAULT NEXTVAL('stagings_sqs'),
  production_designer varchar(256),
  production_director varchar(256),
  production_conductor varchar(256),
  season int
);

ALTER TABLE goes_to
  ADD COLUMN staging_id bigint NOT NULL REFERENCES stagings(id) DEFAULT 0;

ALTER TABLE goes_to ALTER COLUMN staging_id DROP DEFAULT;