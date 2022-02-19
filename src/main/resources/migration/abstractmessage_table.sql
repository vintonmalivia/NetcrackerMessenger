create table if not exists abstract_messages
(
    date            date,
    id              varchar PRIMARY KEY,

    id_conversation varchar,
    FOREIGN KEY (id_conversation) REFERENCES conversations (id),

    sender_id       varchar,
    FOREIGN KEY (sender_id) REFERENCES profile (id)

    -- TODO: Посмотри про внешние ключи. У нас это поле должно ЯВНО ссылаться на чат
    -- TODO: Я напишу примерный синтаксис (строка ниже), а ты его адаптируй под нашу ситуацию
    -- FOREIGN KEY (CustomerId) REFERENCES Customers (Id) ON DELETE CASCADE
    -- TODO: Глянуть про ON DELETE CASCADE (просто чтоб знать, для чего оно нужно)
);