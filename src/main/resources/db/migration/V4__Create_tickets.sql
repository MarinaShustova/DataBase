CREATE TABLE tickets
(
  id         serial PRIMARY KEY,
  row        integer NOT NULL,
  seat       integer NOT NULL,
  price      integer NOT NULL,
  presence   boolean NOT NULL,
  previously boolean NOT NULL,
  show_id    integer REFERENCES shows (id) ON DELETE CASCADE,
  CONSTRAINT unq_ticket_params UNIQUE (row, seat, price, show_id)
);