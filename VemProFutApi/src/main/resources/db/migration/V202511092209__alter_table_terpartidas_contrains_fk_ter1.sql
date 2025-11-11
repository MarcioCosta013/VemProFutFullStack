ALTER TABLE ter_partidas_gols ADD CONSTRAINT FK_ter_partidas_gols_1
    FOREIGN KEY (fk_Partidas_id_partida)
    REFERENCES Partidas (id_partida)
    ON DELETE RESTRICT;