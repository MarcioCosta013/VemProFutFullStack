ALTER TABLE Fut ADD CONSTRAINT FK_Fut_4
    FOREIGN KEY (fk_Cartoes_Peladeiro_id_cartoes_peladeiro)
    REFERENCES Cartoes_Peladeiro (id_cartoes_peladeiro)
    ON DELETE SET NULL;