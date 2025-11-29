ALTER TABLE esta_peladeiro_partidas ADD CONSTRAINT FK_esta_peladeiro_partidas_2
    FOREIGN KEY (fk_peladeiro)
    REFERENCES peladeiro (id_peladeiro)
    ON DELETE CASCADE;
