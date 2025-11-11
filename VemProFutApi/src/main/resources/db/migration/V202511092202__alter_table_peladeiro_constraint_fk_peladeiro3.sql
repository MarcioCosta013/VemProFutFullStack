ALTER TABLE Peladeiro ADD CONSTRAINT FK_Peladeiro_3
    FOREIGN KEY (fk_Cartoes_Peladeiro_id_cartoes_peladeiro)
    REFERENCES cartoes_peladeiro (id_cartoes_peladeiro)
    ON DELETE SET NULL;