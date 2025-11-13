ALTER TABLE participa_peladeiro_fut ADD CONSTRAINT FK_participa_peladeiro_fut_2
    FOREIGN KEY (fk_peladeiro)
    REFERENCES Peladeiro (id_peladeiro)
    ON DELETE CASCADE;
