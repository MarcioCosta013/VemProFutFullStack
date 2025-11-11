ALTER TABLE fut
ADD CONSTRAINT FK_Fut_3
FOREIGN KEY (fk_Peladeiro_id_peladeiro)
REFERENCES peladeiro (id_peladeiro)
ON DELETE CASCADE;
