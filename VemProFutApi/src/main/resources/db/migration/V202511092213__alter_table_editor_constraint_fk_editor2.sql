ALTER TABLE editores_fut ADD CONSTRAINT FK_Editor_2
    FOREIGN KEY (fk_peladeiro)
    REFERENCES Peladeiro (id_peladeiro)
    ON DELETE CASCADE;