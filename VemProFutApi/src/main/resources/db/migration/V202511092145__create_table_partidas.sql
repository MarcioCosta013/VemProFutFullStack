CREATE TABLE partidas (
    id_partida BIGINT AUTO_INCREMENT PRIMARY KEY,
    reservas_partida BOOLEAN,
    fk_fut BIGINT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
