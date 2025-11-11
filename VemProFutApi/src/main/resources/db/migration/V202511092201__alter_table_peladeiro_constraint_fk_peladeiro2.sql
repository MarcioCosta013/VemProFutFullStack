ALTER TABLE Peladeiro ADD CONSTRAINT FK_Peladeiro_2
    FOREIGN KEY (fk_Historico_Peladeiro_id_historico_peladeiro)
    REFERENCES Historico_Peladeiro (id_historico_peladeiro)
    ON DELETE RESTRICT;