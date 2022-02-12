create table if not exists abstract_messages
(
    date            date,
    id              varchar PRIMARY KEY,
    id_conversation varchar
--     sender_id       varchar TODO: check
);