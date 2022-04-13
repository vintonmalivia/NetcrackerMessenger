create table if not exists profile
(
    id      varchar PRIMARY KEY,
    name    varchar NOT NULL,
    surname varchar NOT NULL
);