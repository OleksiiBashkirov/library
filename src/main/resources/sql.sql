create table person
(
    id        int generated by default as identity primary key,
    name      varchar(32)                                      not null,
    lastname  varchar(32)                                      not null,
    inn       varchar(10) check ( inn ~ '^[0-9]{10}$' ) unique not null,
    birthdate varchar(10) check ( birthdate ~ '^\\d{4}-\\d{2}-\\d{2}$')
);