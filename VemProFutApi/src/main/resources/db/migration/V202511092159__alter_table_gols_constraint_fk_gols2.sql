ALTER TABLE Gols_Partida ADD CONSTRAINT FK_Gols_Partida_2
    FOREIGN KEY (fk_Peladeiro_id_peladeiro)
    REFERENCES Peladeiro (id_peladeiro)
    ON DELETE CASCADE;