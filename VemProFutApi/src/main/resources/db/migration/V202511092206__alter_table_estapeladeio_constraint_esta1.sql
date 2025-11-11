ALTER TABLE esta_peladeiro_partidas ADD CONSTRAINT FK_esta_peladeiro_partidas_1
    FOREIGN KEY (fk_Partidas_id_partida)
    REFERENCES Partidas (id_partida)
    ON DELETE CASCADE;
