ALTER TABLE editores_fut ADD CONSTRAINT FK_Editor_1
    FOREIGN KEY (fk_fut)
    REFERENCES fut (id_fut)
    ON DELETE CASCADE;