ALTER TABLE ter_partidas_gols ADD CONSTRAINT FK_ter_partidas_gols_2
    FOREIGN KEY (fk_Gols_Partida_id_gols_partida)
    REFERENCES Gols_Partida (id_gols_partida)
    ON DELETE CASCADE;
