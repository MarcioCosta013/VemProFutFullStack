/* Lógico_1: */

CREATE TABLE Historico_Peladeiro (
    id_historico_peladeiro INTEGER PRIMARY KEY,
    gols_historico_peladeiro int,
    nota_historico_peladeiro decimal(5,2),
    partidas_jogadas_peladeiro int,
    partidas_ganhas_peladeiro int
);

CREATE TABLE Historico_Fut (
    gols_total_historico_fut int,
    partidas_jogadas_historico_fut int,
    time_mais_vitorias_historico varchar(100),
    id_historico_fut INTEGER PRIMARY KEY
);

CREATE TABLE Fut (
    id_fut INTEGER PRIMARY KEY,
    nome_fut varchar(50),
    jogadores_por_time_fut int,
    tempo_max_partida_fut Time,
    max_gols_vitoria_fut int,
    fk_Historico_Fut_id_historico_fut INTEGER,
    fk_Peladeiro_id_peladeiro INTEGER
);

CREATE TABLE Partidas (
    id_partida INTEGER PRIMARY KEY,
    reservas_partida boolean,
    fk_Fut_id_fut INTEGER
);

CREATE TABLE Gols_Partida (
    id_gols_partida INTEGER PRIMARY KEY,
    fk_Peladeiro_id_peladeiro INTEGER
);

CREATE TABLE Cartoes_Peladeiro (
    azul_cartoes INTEGER,
    amarelo_cartoes INTEGER,
    vermelho_cartoes INTEGER,
    id_cartoes_peladeiro INTEGER PRIMARY KEY
);

CREATE TABLE Peladeiro (
    id_peladeiro INTEGER PRIMARY KEY,
    nome_usuario varchar(50),
    apelido_usuario varchar(30),
    descricao_peladeiro varchar(100),
    pe_dominante varchar(10),
    whatsapp varchar(15),
    fk_Historico_Peladeiro_id_historico_peladeiro INTEGER
);

CREATE TABLE criar_fut_peladeiro (
    fk_Fut_id_fut INTEGER,
    fk_Peladeiro_id_peladeiro INTEGER
);

CREATE TABLE esta_peladeiro_partidas (
    fk_Partidas_id_partida INTEGER,
    fk_Peladeiro_id_peladeiro INTEGER
);

CREATE TABLE tem_peladeiros_cartoes (
    fk_Peladeiro_id_peladeiro INTEGER,
    fk_Cartoes_Peladeiro_id_cartoes_peladeiro INTEGER
);

CREATE TABLE ter_partidas_gols (
    fk_Partidas_id_partida INTEGER,
    fk_Gols_Partida_id_gols_partida INTEGER
);

CREATE TABLE tem_partidas_cartoes (
    fk_Partidas_id_partida INTEGER,
    fk_Cartoes_Peladeiro_id_cartoes_peladeiro INTEGER
);

CREATE TABLE Editor (
    fk_Fut_id_fut INTEGER,
    fk_Peladeiro_id_peladeiro INTEGER
);
 
ALTER TABLE Fut ADD CONSTRAINT FK_Fut_2
    FOREIGN KEY (fk_Historico_Fut_id_historico_fut)
    REFERENCES Historico_Fut (id_historico_fut)
    ON DELETE RESTRICT;
 
ALTER TABLE Fut ADD CONSTRAINT FK_Fut_3
    FOREIGN KEY (fk_Peladeiro_id_peladeiro)
    REFERENCES Peladeiro (id_peladeiro)
    ON DELETE CASCADE;
 
ALTER TABLE Partidas ADD CONSTRAINT FK_Partidas_2
    FOREIGN KEY (fk_Fut_id_fut)
    REFERENCES Fut (id_fut)
    ON DELETE CASCADE;
 
ALTER TABLE Gols_Partida ADD CONSTRAINT FK_Gols_Partida_2
    FOREIGN KEY (fk_Peladeiro_id_peladeiro)
    REFERENCES Peladeiro (id_peladeiro)
    ON DELETE CASCADE;
 
ALTER TABLE Peladeiro ADD CONSTRAINT FK_Peladeiro_2
    FOREIGN KEY (fk_Historico_Peladeiro_id_historico_peladeiro)
    REFERENCES Historico_Peladeiro (id_historico_peladeiro)
    ON DELETE RESTRICT;
 
ALTER TABLE criar_fut_peladeiro ADD CONSTRAINT FK_criar_fut_peladeiro_1
    FOREIGN KEY (fk_Fut_id_fut)
    REFERENCES Fut (id_fut)
    ON DELETE SET NULL;
 
ALTER TABLE criar_fut_peladeiro ADD CONSTRAINT FK_criar_fut_peladeiro_2
    FOREIGN KEY (fk_Peladeiro_id_peladeiro)
    REFERENCES Peladeiro (id_peladeiro)
    ON DELETE SET NULL;
 
ALTER TABLE esta_peladeiro_partidas ADD CONSTRAINT FK_esta_peladeiro_partidas_1
    FOREIGN KEY (fk_Partidas_id_partida)
    REFERENCES Partidas (id_partida)
    ON DELETE SET NULL;
 
ALTER TABLE esta_peladeiro_partidas ADD CONSTRAINT FK_esta_peladeiro_partidas_2
    FOREIGN KEY (fk_Peladeiro_id_peladeiro)
    REFERENCES Peladeiro (id_peladeiro)
    ON DELETE SET NULL;
 
ALTER TABLE tem_peladeiros_cartoes ADD CONSTRAINT FK_tem_peladeiros_cartoes_1
    FOREIGN KEY (fk_Peladeiro_id_peladeiro)
    REFERENCES Peladeiro (id_peladeiro)
    ON DELETE SET NULL;
 
ALTER TABLE tem_peladeiros_cartoes ADD CONSTRAINT FK_tem_peladeiros_cartoes_2
    FOREIGN KEY (fk_Cartoes_Peladeiro_id_cartoes_peladeiro)
    REFERENCES Cartoes_Peladeiro (id_cartoes_peladeiro)
    ON DELETE SET NULL;
 
ALTER TABLE ter_partidas_gols ADD CONSTRAINT FK_ter_partidas_gols_1
    FOREIGN KEY (fk_Partidas_id_partida)
    REFERENCES Partidas (id_partida)
    ON DELETE RESTRICT;
 
ALTER TABLE ter_partidas_gols ADD CONSTRAINT FK_ter_partidas_gols_2
    FOREIGN KEY (fk_Gols_Partida_id_gols_partida)
    REFERENCES Gols_Partida (id_gols_partida)
    ON DELETE SET NULL;
 
ALTER TABLE tem_partidas_cartoes ADD CONSTRAINT FK_tem_partidas_cartoes_1
    FOREIGN KEY (fk_Partidas_id_partida)
    REFERENCES Partidas (id_partida)
    ON DELETE RESTRICT;
 
ALTER TABLE tem_partidas_cartoes ADD CONSTRAINT FK_tem_partidas_cartoes_2
    FOREIGN KEY (fk_Cartoes_Peladeiro_id_cartoes_peladeiro)
    REFERENCES Cartoes_Peladeiro (id_cartoes_peladeiro)
    ON DELETE SET NULL;
 
ALTER TABLE Editor ADD CONSTRAINT FK_Editor_1
    FOREIGN KEY (fk_Fut_id_fut)
    REFERENCES Fut (id_fut)
    ON DELETE RESTRICT;
 
ALTER TABLE Editor ADD CONSTRAINT FK_Editor_2
    FOREIGN KEY (fk_Peladeiro_id_peladeiro)
    REFERENCES Peladeiro (id_peladeiro)
    ON DELETE SET NULL;/* Lógico_1: */

CREATE TABLE Historico_Peladeiro (
    id_historico_peladeiro INTEGER PRIMARY KEY,
    gols_historico_peladeiro int,
    nota_historico_peladeiro double,
    partidas_jogadas_peladeiro int,
    partidas_ganhas_peladeiro int
);

CREATE TABLE Historico_Fut (
    gols_total_historico_fut int,
    partidas_jogadas_historico_fut int,
    time_mais_vitorias_historico varchar(100),
    id_historico_fut INTEGER PRIMARY KEY
);

CREATE TABLE Fut (
    id_fut INTEGER PRIMARY KEY,
    nome_fut varchar(50),
    jogadores_por_time_fut int,
    tempo_max_partida_fut Time,
    max_gols_vitoria_fut int,
    fk_Historico_Fut_id_historico_fut INTEGER,
    fk_Peladeiro_id_peladeiro INTEGER
);

CREATE TABLE Partidas (
    id_partida INTEGER PRIMARY KEY,
    reservas_partida boolean,
    fk_Fut_id_fut INTEGER
);

CREATE TABLE Gols_Partida (
    id_gols_partida INTEGER PRIMARY KEY,
    fk_Peladeiro_id_peladeiro INTEGER
);

CREATE TABLE Cartoes_Peladeiro (
    azul_cartoes INTEGER,
    amarelo_cartoes INTEGER,
    vermelho_cartoes INTEGER,
    id_cartoes_peladeiro INTEGER PRIMARY KEY
);

CREATE TABLE Peladeiro (
    id_peladeiro INTEGER PRIMARY KEY,
    nome_usuario varchar(50),
    apelido_usuario varchar(30),
    descricao_peladeiro varchar(100),
    pe_dominante varchar(10),
    whatsapp varchar(15),
    fk_Historico_Peladeiro_id_historico_peladeiro INTEGER
);

CREATE TABLE criar_fut_peladeiro (
    fk_Fut_id_fut INTEGER,
    fk_Peladeiro_id_peladeiro INTEGER
);

CREATE TABLE esta_peladeiro_partidas (
    fk_Partidas_id_partida INTEGER,
    fk_Peladeiro_id_peladeiro INTEGER
);

CREATE TABLE tem_peladeiros_cartoes (
    fk_Peladeiro_id_peladeiro INTEGER,
    fk_Cartoes_Peladeiro_id_cartoes_peladeiro INTEGER
);

CREATE TABLE ter_partidas_gols (
    fk_Partidas_id_partida INTEGER,
    fk_Gols_Partida_id_gols_partida INTEGER
);

CREATE TABLE tem_partidas_cartoes (
    fk_Partidas_id_partida INTEGER,
    fk_Cartoes_Peladeiro_id_cartoes_peladeiro INTEGER
);

CREATE TABLE Editor (
    fk_Fut_id_fut INTEGER,
    fk_Peladeiro_id_peladeiro INTEGER
);
 
ALTER TABLE Fut ADD CONSTRAINT FK_Fut_2
    FOREIGN KEY (fk_Historico_Fut_id_historico_fut)
    REFERENCES Historico_Fut (id_historico_fut)
    ON DELETE RESTRICT;
 
ALTER TABLE Fut ADD CONSTRAINT FK_Fut_3
    FOREIGN KEY (fk_Peladeiro_id_peladeiro)
    REFERENCES Peladeiro (id_peladeiro)
    ON DELETE CASCADE;
 
ALTER TABLE Partidas ADD CONSTRAINT FK_Partidas_2
    FOREIGN KEY (fk_Fut_id_fut)
    REFERENCES Fut (id_fut)
    ON DELETE CASCADE;
 
ALTER TABLE Gols_Partida ADD CONSTRAINT FK_Gols_Partida_2
    FOREIGN KEY (fk_Peladeiro_id_peladeiro)
    REFERENCES Peladeiro (id_peladeiro)
    ON DELETE CASCADE;
 
ALTER TABLE Peladeiro ADD CONSTRAINT FK_Peladeiro_2
    FOREIGN KEY (fk_Historico_Peladeiro_id_historico_peladeiro)
    REFERENCES Historico_Peladeiro (id_historico_peladeiro)
    ON DELETE RESTRICT;
 
ALTER TABLE criar_fut_peladeiro ADD CONSTRAINT FK_criar_fut_peladeiro_1
    FOREIGN KEY (fk_Fut_id_fut)
    REFERENCES Fut (id_fut)
    ON DELETE SET NULL;
 
ALTER TABLE criar_fut_peladeiro ADD CONSTRAINT FK_criar_fut_peladeiro_2
    FOREIGN KEY (fk_Peladeiro_id_peladeiro)
    REFERENCES Peladeiro (id_peladeiro)
    ON DELETE SET NULL;
 
ALTER TABLE esta_peladeiro_partidas ADD CONSTRAINT FK_esta_peladeiro_partidas_1
    FOREIGN KEY (fk_Partidas_id_partida)
    REFERENCES Partidas (id_partida)
    ON DELETE SET NULL;
 
ALTER TABLE esta_peladeiro_partidas ADD CONSTRAINT FK_esta_peladeiro_partidas_2
    FOREIGN KEY (fk_Peladeiro_id_peladeiro)
    REFERENCES Peladeiro (id_peladeiro)
    ON DELETE SET NULL;
 
ALTER TABLE tem_peladeiros_cartoes ADD CONSTRAINT FK_tem_peladeiros_cartoes_1
    FOREIGN KEY (fk_Peladeiro_id_peladeiro)
    REFERENCES Peladeiro (id_peladeiro)
    ON DELETE SET NULL;
 
ALTER TABLE tem_peladeiros_cartoes ADD CONSTRAINT FK_tem_peladeiros_cartoes_2
    FOREIGN KEY (fk_Cartoes_Peladeiro_id_cartoes_peladeiro)
    REFERENCES Cartoes_Peladeiro (id_cartoes_peladeiro)
    ON DELETE SET NULL;
 
ALTER TABLE ter_partidas_gols ADD CONSTRAINT FK_ter_partidas_gols_1
    FOREIGN KEY (fk_Partidas_id_partida)
    REFERENCES Partidas (id_partida)
    ON DELETE RESTRICT;
 
ALTER TABLE ter_partidas_gols ADD CONSTRAINT FK_ter_partidas_gols_2
    FOREIGN KEY (fk_Gols_Partida_id_gols_partida)
    REFERENCES Gols_Partida (id_gols_partida)
    ON DELETE SET NULL;
 
ALTER TABLE tem_partidas_cartoes ADD CONSTRAINT FK_tem_partidas_cartoes_1
    FOREIGN KEY (fk_Partidas_id_partida)
    REFERENCES Partidas (id_partida)
    ON DELETE RESTRICT;
 
ALTER TABLE tem_partidas_cartoes ADD CONSTRAINT FK_tem_partidas_cartoes_2
    FOREIGN KEY (fk_Cartoes_Peladeiro_id_cartoes_peladeiro)
    REFERENCES Cartoes_Peladeiro (id_cartoes_peladeiro)
    ON DELETE SET NULL;
 
ALTER TABLE Editor ADD CONSTRAINT FK_Editor_1
    FOREIGN KEY (fk_Fut_id_fut)
    REFERENCES Fut (id_fut)
    ON DELETE CASCADE;
 
ALTER TABLE Editor ADD CONSTRAINT FK_Editor_2
    FOREIGN KEY (fk_Peladeiro_id_peladeiro)
    REFERENCES Peladeiro (id_peladeiro)
    ON DELETE CASCADE;