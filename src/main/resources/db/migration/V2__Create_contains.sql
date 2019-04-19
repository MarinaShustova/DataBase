CREATE SEQUENCE contains_sqs START 1;
CREATE TABLE contains(
id        bigint PRIMARY KEY DEFAULT NEXTVAL('contains_sqs')
);