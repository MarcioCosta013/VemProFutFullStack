ALTER TABLE ter_partidas_gols ADD CONSTRAINT FK_ter_partidas_gols_1
    FOREIGN KEY (fk_partidas)
    REFERENCES Partidas (id_partida)
    ON DELETE RESTRICT;