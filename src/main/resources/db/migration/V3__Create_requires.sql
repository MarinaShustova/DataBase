CREATE SEQUENCE requires_sqs START 1;
CREATE TABLE requires(
id        bigint PRIMARY KEY DEFAULT NEXTVAL('requires_sqs')
);