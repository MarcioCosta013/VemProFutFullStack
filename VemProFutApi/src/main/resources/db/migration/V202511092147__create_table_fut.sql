CREATE TABLE fut (
    id_fut BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome_fut VARCHAR(50),
    jogadores_por_time_fut INT,
    tempo_max_partida_fut INT,
    max_gols_vitoria_fut INT,
    foto_url varchar(255),
    fk_historico_fut BIGINT,
    fk_peladeiro BIGINT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
