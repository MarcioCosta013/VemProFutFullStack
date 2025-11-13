CREATE TABLE gols_partida (
    id_gols_partida BIGINT AUTO_INCREMENT PRIMARY KEY,
    fk_peladeiro BIGINT,
    fk_partida BIGINT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
