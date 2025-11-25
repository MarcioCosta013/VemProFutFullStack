ALTER TABLE banimento ADD CONSTRAINT FK_banimento_2
    FOREIGN KEY (fk_peladeiro)
    REFERENCES Peladeiro (id_peladeiro)
    ON DELETE SET NULL;