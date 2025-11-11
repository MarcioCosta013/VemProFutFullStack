CREATE TABLE participa_peladeiro_fut (
    fk_Fut_id_fut BIGINT,
    fk_Peladeiro_id_peladeiro BIGINT,
    PRIMARY KEY (fk_Fut_id_fut, fk_Peladeiro_id_peladeiro)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


CREATE TABLE esta_peladeiro_partidas (
    fk_Partidas_id_partida BIGINT,
    fk_Peladeiro_id_peladeiro BIGINT,
    PRIMARY KEY (fk_Partidas_id_partida, fk_Peladeiro_id_peladeiro)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


CREATE TABLE ter_partidas_gols (
    fk_Partidas_id_partida BIGINT,
    fk_Gols_Partida_id_gols_partida BIGINT,
    PRIMARY KEY (fk_Partidas_id_partida, fk_Gols_Partida_id_gols_partida)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


CREATE TABLE Editor (
    id_editor BIGINT AUTO_INCREMENT PRIMARY KEY,
    fk_Fut_id_fut BIGINT,
    fk_Peladeiro_id_peladeiro BIGINT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
