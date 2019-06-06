insert into countries(name) values('Англия');
insert into countries(name) values('Российская империя');
insert into countries(name) values('Франция');
insert into countries(name) values('Россия');
insert into countries(name) values('Дания');
insert into countries(name) values('Литва');
--actors
insert into employees(fio, sex, birth_date,children_amount, salary, hire_date, origin) values('Михалков Никита Сергеевич', 'male', to_date('1945-10-21','YYYY-MM-dd'), 4, 100000, to_date('2010-04-28','YYYY-MM-dd'), 4);
insert into employees(fio, sex, birth_date,children_amount, salary, hire_date, origin) values('Ходченкова Светлана Викторовна', 'female',to_date('1983-01-21','YYYY-MM-dd'), 0, 200000, to_date('2010-07-13','YYYY-MM-dd'), 4);
insert into employees(fio, sex, birth_date,children_amount, salary, hire_date, origin) values('Ингеборга Эдмундовна Дапкунайте', 'female',to_date('1963-01-20','YYYY-MM-dd'), 1, 200000, to_date('2007-12-28','YYYY-MM-dd'), 5);
insert into employees(fio, sex, birth_date,children_amount, salary, hire_date, origin) values('Ургант Иван Андреевич', 'male', to_date('1978-04-16','YYYY-MM-dd'), 3, 300000, to_date('2015-02-01','YYYY-MM-dd'), 4);
insert into employees(fio, sex, birth_date,children_amount, salary, hire_date, origin) values('Вилкова Екатерина Николаевна', 'female', to_date('1984-06-11','YYYY-MM-dd'), 0, 200000, to_date('2015-08-26','YYYY-MM-dd'), 4);
insert into employees(fio, sex, birth_date,children_amount, salary, hire_date, origin) values('Паль Александр Владимирович', 'male', to_date('1988-12-16','YYYY-MM-dd'), 0, 300000, to_date('2016-07-13','YYYY-MM-dd'), 4);
insert into employees(fio, sex, birth_date,children_amount, salary, hire_date, origin) values('Хабенский Константин Юрьевич', 'male', to_date('1972-01-11','YYYY-MM-dd'), 2, 300000, to_date('2010-04-30','YYYY-MM-dd'), 4);
--producers
insert into employees(fio, sex, birth_date,children_amount, salary, hire_date, origin) values('Авдеев Владимир', 'male',to_date('1957-12-17','YYYY-MM-dd'), 0, 80000, to_date('2005-04-04','YYYY-MM-dd'), 4); --artist
insert into employees(fio, sex, birth_date,children_amount, salary, hire_date, origin) values('Губин Андрей Викторович', 'male',to_date('1974-04-30','YYYY-MM-dd'), 0, 70000, to_date('2011-08-15','YYYY-MM-dd'), 4); --composer
insert into employees(fio, sex, birth_date,children_amount, salary, hire_date, origin) values('Найшуллер Илья Викторович', 'male', to_date('1983-11-19','YYYY-MM-dd'), 0, 600000, to_date('2018-01-20','YYYY-MM-dd'), 4); --prod.
insert into employees(fio, sex, birth_date,children_amount, salary, hire_date, origin) values('Андреева Анна', 'female',to_date('1989-12-01','YYYY-MM-dd'), 0, 80000, to_date('2010-11-23','YYYY-MM-dd'), 4); --artist
insert into employees(fio, sex, birth_date,children_amount, salary, hire_date, origin) values('Белова Ирина', 'female',to_date('1975-05-22','YYYY-MM-dd'), 1, 70000, to_date('2015-03-07','YYYY-MM-dd'), 4); --composer
--musicians
insert into employees(fio, sex, birth_date,children_amount, salary, hire_date, origin) values('Башмет Юрий Анатольевич', 'male',to_date('1953-01-24','YYYY-MM-dd'), 2, 200000, to_date('2005-04-04','YYYY-MM-dd'), 4);
insert into employees(fio, sex, birth_date,children_amount, salary, hire_date, origin) values('Майкл Гордон Олдфилд', 'male',to_date('1953-05-15','YYYY-MM-dd'), 0, 300000, to_date('2015-08-15','YYYY-MM-dd'), 1);
insert into employees(fio, sex, birth_date,children_amount, salary, hire_date, origin) values('Дэвид Пьер Гетта', 'male', to_date('1967-11-07','YYYY-MM-dd'), 0, 600000, to_date('2018-02-25','YYYY-MM-dd'), 3);
--servants
insert into employees(fio, sex, birth_date,children_amount, salary, hire_date, origin) values('Киселёв Максим Игоревич', 'male',to_date('1989-09-22','YYYY-MM-dd'), 1, 50000, to_date('2016-11-30','YYYY-MM-dd'), 4);
insert into employees(fio, sex, birth_date,children_amount, salary, hire_date, origin) values('Погодина Анастасия Алексеевна', 'female',to_date('1990-08-14','YYYY-MM-dd'), 0, 50000, to_date('2017-08-15','YYYY-MM-dd'), 4);
insert into employees(fio, sex, birth_date,children_amount, salary, hire_date, origin) values('Фельзинг Наталья Фёдоровна', 'female', to_date('1992-10-03','YYYY-MM-dd'), 0, 40000, to_date('2014-03-20','YYYY-MM-dd'), 4);

insert into actors(employee_id, is_student) values(1, false);
insert into actors(employee_id, is_student) values(2, false);
insert into actors(employee_id, is_student) values(3, false);
insert into actors(employee_id, is_student) values(4, false);
insert into actors(employee_id, is_student) values(5, false);
insert into actors(employee_id, is_student) values(6, true);
insert into actors(employee_id, is_student) values(7, false);

insert into producers(employee_id, activity) values(8, 'Художник-постановщик');
insert into producers(employee_id, activity) values(9, 'Композитор-постановщик');
insert into producers(employee_id, activity) values(10, 'Режиссёр-постановщик');
insert into producers(employee_id, activity) values(11, 'Художник-постановщик');
insert into producers(employee_id, activity) values(12, 'Композитор-постановщик');

insert into musicians(employee_id, instrument) values(13, 'Скрипка');
insert into musicians(employee_id, instrument) values(14, 'Гитара');
insert into musicians(employee_id, instrument) values(15, 'Синтезатор');

insert into servants(employee_id, activity) values(16, 'Звукооператор');
insert into servants(employee_id, activity) values(17, 'Осветитель');
insert into servants(employee_id, activity) values(18, 'Билетёр');

insert into ranks(name, contest) values('Народный артист России', 'Указ президента РФ');
insert into ranks(name, contest) values('Заслуженный артист России', 'Указ президента РФ');
insert into ranks(name, contest) values('Золотой Орёл', 'Фестиваль Золотой Орёл');
insert into ranks(name, contest) values('ТЭФИ', 'Премия ТЭФИ');
insert into ranks(name, contest) values('Ника', 'Премия Ника');

insert into actors_ranks(actor_id, rank_id, date_of_giving) values(1, 3, to_date('2006-01-25','YYYY-MM-dd'));
insert into actors_ranks(actor_id, rank_id, date_of_giving) values(1, 4, to_date('2006-03-30','YYYY-MM-dd'));
insert into actors_ranks(actor_id, rank_id, date_of_giving) values(2, 3, to_date('2019-01-31','YYYY-MM-dd'));
insert into actors_ranks(actor_id, rank_id, date_of_giving) values(3, 5, to_date('1994-05-30','YYYY-MM-dd'));
insert into actors_ranks(actor_id, rank_id, date_of_giving) values(4, 5, to_date('2009-05-30','YYYY-MM-dd'));
insert into actors_ranks(actor_id, rank_id, date_of_giving) values(4, 4, to_date('2016-03-30','YYYY-MM-dd'));
insert into actors_ranks(actor_id, rank_id, date_of_giving) values(7, 5, to_date('2014-05-30','YYYY-MM-dd'));
insert into actors_ranks(actor_id, rank_id, date_of_giving) values(1, 1, to_date('1984-12-30','YYYY-MM-dd'));

insert into authors(surname, name, birth_date, death_date, country) values('Горький', 'Максим', to_date('1868-03-28','YYYY-MM-dd'), to_date('1936-06-18','YYYY-MM-dd'), 2);
insert into authors(surname, name, birth_date, death_date, country) values('Уайлд', 'Оскар', to_date('1854-10-16','YYYY-MM-dd'), to_date('1900-11-30','YYYY-MM-dd'), 1);
insert into authors(surname, name, birth_date, death_date, country) values('Шоу', 'Джоржд', to_date('1856-06-26','YYYY-MM-dd'), to_date('1950-11-02','YYYY-MM-dd'), 1);
insert into authors(surname, name, birth_date, death_date, country) values('Гюго', 'Виктор', to_date('1802-02-26','YYYY-MM-dd'), to_date('1885-05-22','YYYY-MM-dd'), 3);
insert into authors(surname, name, birth_date, death_date, country) values('Мольер', 'Жан-Батист', to_date('1622-01-15','YYYY-MM-dd'), to_date('1673-02-17','YYYY-MM-dd'), 3);
insert into authors(surname, name, birth_date, death_date, country) values('Перро', 'Шарль', to_date('1628-01-12','YYYY-MM-dd'), to_date('1703-05-16','YYYY-MM-dd'), 3);
insert into authors(surname, name, birth_date, death_date, country) values('Алексей', 'Попов', to_date('1974-05-31','YYYY-MM-dd'), null, 4);
insert into authors(surname, name, birth_date, death_date, country) values('Шекспир', 'Уильям', to_date('1564-04-01','YYYY-MM-dd'), to_date('1616-04-23','YYYY-MM-dd'), 1);

insert into genres(name) values( 'Комедия');
insert into genres(name) values( 'Трагедия');
insert into genres(name) values( 'Драма');
insert into genres(name) values( 'Мюзикл');
insert into genres(name) values( 'Водевиль');
insert into genres(name) values( 'Мелодрама');
insert into genres(name) values( 'Опера');
insert into genres(name) values( 'Романтическое приключение');

insert into spectacles(name, genre, age_category, author_id) values( 'На дне', 2, 14, 1);
insert into spectacles(name, genre, age_category, author_id) values( 'Портрет Дориана Грея', 3, 18, 2);
insert into spectacles(name, genre, age_category, author_id) values( 'Ромео и Джульетта', 2, 6, 8);
insert into spectacles(name, genre, age_category, author_id) values( 'Леди Макбет', 2, 12, 8);
insert into spectacles(name, genre, age_category, author_id) values( 'Красная Шапочка', 7, 0, 6);
insert into spectacles(name, genre, age_category, author_id) values( 'Стойкий оловянный солдатик', 8, 0, null);

insert into roles(name) values('Наташа'); 				--1
insert into roles(name) values('Клещ Андрей Митрич'); 	--2
insert into roles(name) values('Дориан Грей'); 			--3
insert into roles(name) values('Сибила Вэйн'); 			--4
insert into roles(name) values('Ромео'); 				--5
insert into roles(name) values('Джульетта'); 			--6
insert into roles(name) values('Красная Шапочка'); 		--7
insert into roles(name) values('Волк');					--8
insert into roles(name) values('Бабушка');				--9

insert into performances(production_designer, production_director, production_conductor, season, spectacle_id) values( 1, 3, 5, 1, 1);
insert into performances(production_designer, production_director, production_conductor, season, spectacle_id) values( 4, 3, 2, 2, 2);
insert into performances(production_designer, production_director, production_conductor, season, spectacle_id) values( 1, 3, 2, 2, 5);

insert into roles_performances(role_id, performance_id) values(1,1);
insert into roles_performances(role_id, performance_id) values(2,1);
insert into roles_performances(role_id, performance_id) values(3,2);
insert into roles_performances(role_id, performance_id) values(4,2);
insert into roles_performances(role_id, performance_id) values(7,3);
insert into roles_performances(role_id, performance_id) values(8,3);
insert into roles_performances(role_id, performance_id) values(9,3);

insert into shows(show_date, premiere, performance_id) values( to_date('2018-05-22','YYYY-MM-dd'), true, 2);
insert into shows(show_date, premiere, performance_id) values( to_date('2018-06-30','YYYY-MM-dd'), false, 2);
insert into shows(show_date, premiere, performance_id) values( to_date('2018-10-20','YYYY-MM-dd'), true, 1);
insert into shows(show_date, premiere, performance_id) values( to_date('2018-11-26','YYYY-MM-dd'), false, 1);
insert into shows(show_date, premiere, performance_id) values( to_date('2019-01-15','YYYY-MM-dd'), true, 3);
insert into shows(show_date, premiere, performance_id) values( to_date('2019-02-26','YYYY-MM-dd'), false, 3);

insert into tours(city, start_date, finish_date, performance_id ) values( 'Москва', to_date('2018-07-01','YYYY-MM-dd'), to_date('2019-07-12','YYYY-MM-dd'), 2);
insert into tours(city, start_date, finish_date, performance_id ) values( 'Санкт-Петербург', to_date('2019-07-13','YYYY-MM-dd'), to_date('2019-07-25','YYYY-MM-dd'), 2);
insert into tours(city, start_date, finish_date, performance_id ) values( 'Калининград', to_date('2019-04-01','YYYY-MM-dd'), to_date('2019-04-30','YYYY-MM-dd'), 3);

insert into features(name, value) values( 'Голос', 'Тенор');	--1
insert into features(name, value) values( 'Голос', 'Бас');		--2
insert into features(name, value) values( 'Голос', 'Фальцет');	--3
insert into features(name, value) values( 'Рост', 'Средний');	--4
insert into features(name, value) values( 'Рост', 'Высокий');	--5
insert into features(name, value) values( 'Рост', 'Низкий');	--6
insert into features(name, value) values( 'Возраст', 'Молодой');--7
insert into features(name, value) values( 'Возраст', 'Пожилой');--8
insert into features(name, value) values( 'Возраст', 'Средний');--9

insert into roles_features(role_id, feature_id) values(1,7); --Наташа молодая
insert into roles_features(role_id, feature_id) values(1,4); --Наташа среднего роста
insert into roles_features(role_id, feature_id) values(2,5); --Клещ высокий
insert into roles_features(role_id, feature_id) values(2,8); --Клещ пожилой
insert into roles_features(role_id, feature_id) values(3,5); --Дориан высокий
insert into roles_features(role_id, feature_id) values(3,7); --Дориан молодой
insert into roles_features(role_id, feature_id) values(4,7); --Сибила молодая
insert into roles_features(role_id, feature_id) values(5,7); --Ромео молодой
insert into roles_features(role_id, feature_id) values(6,7); --Джульетта молодая
insert into roles_features(role_id, feature_id) values(7,7); --Красная шапочка молодая
insert into roles_features(role_id, feature_id) values(8,2); --Волк бас
insert into roles_features(role_id, feature_id) values(8,5); --Волк высокий
insert into roles_features(role_id, feature_id) values(9,9); --Бабушка средний возраст
insert into roles_features(role_id, feature_id) values(9,6); --Бабушка низкая

insert into actors_features(actor_id, feature_id) values(1,8); --Михалков пожилой
insert into actors_features(actor_id, feature_id) values(2,7); --Ходченкова молодая
insert into actors_features(actor_id, feature_id) values(2,4); --Ходченкова среднего роста
insert into actors_features(actor_id, feature_id) values(3,9); --Дапкунайте средненго возраста
insert into actors_features(actor_id, feature_id) values(3,6); --Дапкунайте низкая
insert into actors_features(actor_id, feature_id) values(4,2); --Ургант бас
insert into actors_features(actor_id, feature_id) values(4,5); --Ургант высокий
insert into actors_features(actor_id, feature_id) values(5,7); --Вилкова молодая
insert into actors_features(actor_id, feature_id) values(5,4); --Вилкова среднего роста
insert into actors_features(actor_id, feature_id) values(6,7); --Паль молодой
insert into actors_features(actor_id, feature_id) values(6,5); --Паль высокий
insert into actors_features(actor_id, feature_id) values(7,2); --Хабенский бас
insert into actors_features(actor_id, feature_id) values(7,5); --Хабенский высокий

insert into actors_roles(actor_id, role_id, is_doubler) values(2,4,false); --Ходченкова Сибила
insert into actors_roles(actor_id, role_id, is_doubler) values(5,4,true); -- Вилкова Сибила
insert into actors_roles(actor_id, role_id, is_doubler) values(4,8,false); -- Ургант Волк
insert into actors_roles(actor_id, role_id, is_doubler) values(6,3,false); -- Паль Дориан
insert into actors_roles(actor_id, role_id, is_doubler) values(5,7,false); -- Вилкова Красная шапочка
insert into actors_roles(actor_id, role_id, is_doubler) values(7,8,true); -- Хабенский Волк
insert into actors_roles(actor_id, role_id, is_doubler) values(3,9,false); -- Дапкунайте бабушка
insert into actors_roles(actor_id, role_id, is_doubler) values(5,1,false); -- Вилкова Наташа
insert into actors_roles(actor_id, role_id, is_doubler) values(2,1,true); -- Ходченкова Наташа
insert into actors_roles(actor_id, role_id, is_doubler) values(1,2,false); -- Михалков Клещ

-- insert into tickets(row, seat, price, presence, previously, show_id) values()
