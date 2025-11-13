ALTER TABLE peladeiro ADD CONSTRAINT FK_Peladeiro_2
    FOREIGN KEY (fk_historico_peladeiro)
    REFERENCES historico_peladeiro (id_historico_peladeiro)
    ON DELETE RESTRICT;