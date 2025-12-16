CREATE TABLE banimento (
    id_banimento BIGINT AUTO_INCREMENT PRIMARY KEY,
    motivo_banimento varchar(100),
    data_banimento Date,
    data_fim_banimento Date,
    fk_peladeiro BIGINT,
    fk_fut BIGINT
);