CREATE SEQUENCE goes_to_sqs START 1;
CREATE TABLE goes_to(
id        bigint PRIMARY KEY DEFAULT NEXTVAL('goes_to_sqs')
);