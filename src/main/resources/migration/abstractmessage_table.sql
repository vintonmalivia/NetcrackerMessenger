create table if not exists abstract_messages
(
    date            date,
    id              varchar PRIMARY KEY,

    id_conversation varchar,
    FOREIGN KEY (id_conversation) REFERENCES conversations (id),

    sender_id       varchar,
    FOREIGN KEY (sender_id) REFERENCES profile (id)
);