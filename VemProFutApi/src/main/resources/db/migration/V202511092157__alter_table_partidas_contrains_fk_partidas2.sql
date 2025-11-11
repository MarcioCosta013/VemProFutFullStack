ALTER TABLE Partidas ADD CONSTRAINT FK_Partidas_2
    FOREIGN KEY (fk_Fut_id_fut)
    REFERENCES Fut (id_fut)
    ON DELETE CASCADE;