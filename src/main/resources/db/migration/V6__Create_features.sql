CREATE SEQUENCE features_sqs;

CREATE TABLE features
(
  id        bigint PRIMARY KEY DEFAULT NEXTVAL('features_sqs'),
  name varchar(256),
  value varchar(256)
);

ALTER TABLE requires
  ADD COLUMN feature_id bigint NOT NULL REFERENCES features(id) DEFAULT 0;

ALTER TABLE requires ALTER COLUMN feature_id DROP DEFAULT;