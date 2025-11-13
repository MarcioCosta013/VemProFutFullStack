CREATE TABLE participa_peladeiro_fut (
    fk_fut BIGINT,
    fk_peladeiro BIGINT,
    PRIMARY KEY (fk_fut, fk_peladeiro)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


CREATE TABLE esta_peladeiro_partidas (
    fk_partidas BIGINT,
    fk_peladeiro BIGINT,
    PRIMARY KEY (fk_partidas, fk_peladeiro)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


CREATE TABLE ter_partidas_gols (
    fk_partidas BIGINT,
    fk_gols_partida BIGINT,
    PRIMARY KEY (fk_partidas, fk_gols_partida)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


CREATE TABLE editores_fut (
    id_editor BIGINT AUTO_INCREMENT PRIMARY KEY,
    fk_fut BIGINT,
    fk_peladeiro BIGINT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
