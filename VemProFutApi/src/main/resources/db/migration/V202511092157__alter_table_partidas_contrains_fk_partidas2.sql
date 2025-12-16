ALTER TABLE partidas ADD CONSTRAINT FK_Partidas_2
    FOREIGN KEY (fk_fut)
    REFERENCES fut (id_fut)
    ON DELETE CASCADE;