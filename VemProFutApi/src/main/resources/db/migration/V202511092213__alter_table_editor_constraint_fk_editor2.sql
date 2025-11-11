ALTER TABLE Editor ADD CONSTRAINT FK_Editor_2
    FOREIGN KEY (fk_Peladeiro_id_peladeiro)
    REFERENCES Peladeiro (id_peladeiro)
    ON DELETE CASCADE;