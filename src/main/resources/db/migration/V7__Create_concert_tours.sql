CREATE SEQUENCE concert_tours_sqs;

CREATE TABLE concert_tours
(
  id        bigint PRIMARY KEY DEFAULT NEXTVAL('concert_tours_sqs'),
  city varchar(256),
  start_date date,
  finish_date date
);


ALTER TABLE goes_to
  ADD COLUMN tour_id bigint NOT NULL REFERENCES concert_tours(id) DEFAULT 0;

ALTER TABLE goes_to ALTER COLUMN tour_id DROP DEFAULT;
