CREATE TABLE historico_fut (
    id_historico_fut BIGINT AUTO_INCREMENT PRIMARY KEY,
    gols_total_historico_fut INT,
    partidas_jogadas_historico_fut INT,
    time_mais_vitorias_historico VARCHAR(100)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
