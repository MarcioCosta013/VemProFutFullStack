ALTER TABLE gols_partida ADD CONSTRAINT FK_Gols_Partida_2
    FOREIGN KEY (fk_peladeiro)
    REFERENCES peladeiro (id_peladeiro)
    ON DELETE CASCADE;