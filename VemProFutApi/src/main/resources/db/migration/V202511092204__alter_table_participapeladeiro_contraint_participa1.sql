ALTER TABLE participa_peladeiro_fut ADD CONSTRAINT FK_participa_peladeiro_fut_1
    FOREIGN KEY (fk_Fut_id_fut)
    REFERENCES Fut (id_fut)
    ON DELETE CASCADE;
