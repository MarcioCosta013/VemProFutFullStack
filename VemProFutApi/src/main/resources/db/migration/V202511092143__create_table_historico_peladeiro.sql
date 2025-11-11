CREATE TABLE historico_peladeiro (
    id_historico_peladeiro BIGINT AUTO_INCREMENT PRIMARY KEY,
    gols_historico_peladeiro INT,
    nota_historico_peladeiro DOUBLE,
    partidas_jogadas_peladeiro INT,
    partidas_ganhas_peladeiro INT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
