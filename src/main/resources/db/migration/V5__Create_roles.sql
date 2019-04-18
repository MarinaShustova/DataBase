CREATE SEQUENCE roles_sqs;

CREATE TABLE roles
(
  id        bigint PRIMARY KEY DEFAULT NEXTVAL('roles_sqs'),
  name varchar(256)
);

ALTER TABLE contains
  ADD COLUMN role_id bigint NOT NULL REFERENCES roles(id) DEFAULT 0;

ALTER TABLE contains ALTER COLUMN staging_id DROP DEFAULT;