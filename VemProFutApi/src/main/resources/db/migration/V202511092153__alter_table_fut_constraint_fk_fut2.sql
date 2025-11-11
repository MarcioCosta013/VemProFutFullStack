ALTER TABLE fut
ADD CONSTRAINT FK_Fut_2
FOREIGN KEY (fk_Historico_Fut_id_historico_fut)
REFERENCES historico_fut (id_historico_fut)
ON DELETE RESTRICT;