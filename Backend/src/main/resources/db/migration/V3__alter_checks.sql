alter table authors
    add constraint chk_datesA check (death_date is null or (birth_date <= death_date));

alter table employees
    add constraint chk_datesE check (hire_date >= birth_date);

alter table employees
    add constraint chk_sex check (sex in ('male', 'female'));

alter table employees
    add constraint chk_child check (children_amount >= 0);

CREATE FUNCTION trigger_function() RETURNS trigger
AS
$BODY$
declare
    i integer = 0;
begin
    while i < 100
        loop
            if new.premiere = true then
                insert into tickets(row, seat, price, presence, previously, show_id)
                values (i / 10, i % 10, 1000, true, true, new.id);
            else
                insert into tickets(row, seat, price, presence, previously, show_id)
                values (i / 10, i % 10, 500, true, true, new.id);
            end if;
            i = i + 1;
        END loop;
    return new;
end;
$BODY$
    LANGUAGE plpgsql;

create trigger ticket_creator
    after insert
    on shows
    for each row
execute procedure trigger_function();