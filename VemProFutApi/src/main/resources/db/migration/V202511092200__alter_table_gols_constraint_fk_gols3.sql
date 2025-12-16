ALTER TABLE gols_partida ADD CONSTRAINT FK_Gols_Partida_3
    FOREIGN KEY (fk_partida)
    REFERENCES partidas (id_partida)
    ON DELETE CASCADE;