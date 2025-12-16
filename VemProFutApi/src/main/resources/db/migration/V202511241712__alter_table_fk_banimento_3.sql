ALTER TABLE banimento ADD CONSTRAINT FK_banimento_3
    FOREIGN KEY (fk_fut)
    REFERENCES fut (id_fut)
    ON DELETE SET NULL;