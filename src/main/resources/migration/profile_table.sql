create table if not exists profiles
(
    id      varchar PRIMARY KEY,
    name    varchar NOT NULL,
    surname varchar NOT NULL
);