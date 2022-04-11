create table if not exists profile
(
    id      varchar PRIMARY KEY,
    name    varchar NOT NULL,
    surname varchar NOT NULL,
    user_id varchar NOT NULL,
    FOREIGN KEY (user_id) REFERENCES table_user (id)
);