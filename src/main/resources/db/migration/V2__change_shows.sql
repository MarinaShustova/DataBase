ALTER TABLE shows
  ADD CONSTRAINT performance_show_date_key UNIQUE (performance_id, show_date);
ALTER TABLE shows
  ALTER COLUMN performance_id SET NOT NULL;

ALTER TABLE tickets
  ALTER COLUMN show_id SET NOT NULL;

