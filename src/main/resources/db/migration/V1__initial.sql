create table countries
(
    id   serial primary key,
    name varchar(50) not null unique
);

create table employees
(
    id serial primary key,
    fio             varchar(255),
    sex             varchar(6),
    birth_date      date,
    children_amount integer,
    salary          integer,
    hire_date       date,
    origin          integer references countries(id)
);

create table actors
(
    id  serial primary key,
    employee_id integer
            references employees(id)
            on delete cascade,
    is_student  boolean
);

create table producers
(
    id   serial primary key,
    employee_id integer
            references employees(id)
            on delete cascade,
    activity    varchar(255)
);

create table servants
(
    id     serial primary key,
    employee_id integer
            references employees(id)
            on delete cascade,
    activity    varchar(255)
);

create table musicians
(
    id  serial   primary key,
    employee_id integer
            references employees(id)
            on delete cascade,
    instrument  varchar(255)
);

create table authors
(
    id         serial  primary key,
    surname    varchar(50) not null,
    name       varchar(50) not null,
    birth_date date        not null,
    death_date date,
    country    integer    references countries(id) not null
);

create table genres
(
    id   serial  primary key,
    name varchar(50) not null unique
);

create table spectacles
(
    id           serial     primary key,
    name         varchar(50) not null unique,
    genre        integer     not null
            references genres(id),
    age_category integer    not null,
    author_id    integer
            references authors(id)
);

create table performances
(
    id  serial primary key,
    production_designer  integer references producers(id),
    production_director  integer references producers(id),
    production_conductor integer references producers(id),
    season               integer,
    spectacle_id         integer references spectacles(id) on delete cascade
);

create table shows
(
    id      serial  primary key,
    show_date      date    not null,
    premiere       boolean not null,
    performance_id integer references performances(id) on delete cascade
);

create table tickets
(
    id         serial  primary key,
    row        integer not null,
    seat       integer not null,
    price      integer not null,
    presence   boolean not null,
    previously boolean not null,
    show_id    integer references shows(id) on delete cascade,
    constraint uniq_ticket_params unique (row, seat, price, show_id)
);

create table roles
(
    id serial primary key,
    name varchar(256)
);

create table actors_roles
(
    actor_id   integer references actors(id) not null,
    role_id    integer references roles(id) not null,
    is_doubler boolean
);

create table features
(
    id    serial primary key,
    name  varchar(256),
    value varchar(256)
);

create table roles_features
(
    role_id int not null
            references roles(id) on delete cascade,
    feature_id int not null
            references features(id) on delete cascade
);

create table roles_performances
(
    role_id        integer references roles(id) not null,
    performance_id integer references performances(id) not null
);

create table tours
(
    id    serial primary key,
    city        varchar(256) not null,
    start_date  date not null,
    finish_date date not null,
    performance_id int references performances(id) not null
);

create table tours_performances
(
    performance_id int references performances(id),
    tour_id        int references tours(id)
);


CREATE TABLE ranks
(
    id serial PRIMARY KEY,
    name varchar(256),
    contest varchar(256)
);

CREATE TABLE actors_ranks
(
    actor_id integer REFERENCES actors(id) NOT NULL,
    rank_id integer REFERENCES ranks(id) NOT NULL,
    date_of_giving date
);

CREATE TABLE actors_features (
                     actor_id int NOT NULL REFERENCES Actors(id),
                     feature_id int NOT NULL REFERENCES features(id)
);