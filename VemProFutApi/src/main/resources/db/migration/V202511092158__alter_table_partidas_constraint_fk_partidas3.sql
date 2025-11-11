ALTER TABLE Partidas ADD CONSTRAINT FK_Partidas_3
    FOREIGN KEY (fk_Cartoes_Peladeiro_id_cartoes_peladeiro)
    REFERENCES Cartoes_Peladeiro (id_cartoes_peladeiro)
    ON DELETE SET NULL;