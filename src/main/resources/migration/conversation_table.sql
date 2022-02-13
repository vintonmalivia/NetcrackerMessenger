create table if not exists conversations
(
    name       varchar,
    creator_id varchar,             -- TODO: Будет внешним ключом. Пример смотри в abstractmessage_table.sql
    id         varchar PRIMARY KEY
);