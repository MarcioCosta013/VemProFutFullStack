CREATE TABLE cartoes_peladeiro (
    id_cartoes_peladeiro BIGINT AUTO_INCREMENT PRIMARY KEY,
    fk_peladeiro BIGINT,
    fk_fut BIGINT,
    fk_partida BIGINT,
    tipo_cartao VARCHAR(8)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
