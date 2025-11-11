CREATE TABLE cartoes_peladeiro (
    id_cartoes_peladeiro BIGINT AUTO_INCREMENT PRIMARY KEY,
    peladeiroId BIGINT,
    futId BIGINT,
    partidaId BIGINT,
    tipoCartao VARCHAR(8)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
