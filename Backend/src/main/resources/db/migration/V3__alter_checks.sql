alter table authors
add constraint chk_dates check (if death_date not null then (birth_date < death_date));

alter table employees
add constraint chk_dates check (hire_date < birth_date);

alter table employees
add constraint chk_sex check (sex in ('male', 'female'));

alter table employees
add constraint chk_child check (children_amount >= 0);

create trigger ticket_creator
on shows
after insert
as
declare @i int = 0
while @i < 100
BEGIN
if inserted.premiere = true
begin
	insert into tickets(row, seat, price, presence, previously, show_id) values(@i/10, @i%10, 500, true, true, inserted.id)
end
else
begin
	insert into tickets(row, seat, price, presence, previously, show_id) values(@i/10, @i%10, 1000, true, true, inserted.id)
end
    SET @i = @i + 1
END