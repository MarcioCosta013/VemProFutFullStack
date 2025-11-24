CREATE TABLE peladeiro (
    id_peladeiro BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome_peladeiro varchar(50) NOT NULL,
    apelido_peladeiro varchar(30),
    descricao_peladeiro varchar(100),
    pe_dominante_peladeiro varchar(10),
    whatsapp_peladeiro varchar(15),
    email varchar(80) NOT NULL UNIQUE,
    auth_provider varchar(50),
    foto_url varchar(255),
    fk_historico_peladeiro BIGINT
    -- As colunas de chave estrangeira (FK) foram removidas.
    -- É melhor adicioná-las em uma migration separada,
    -- após a criação das tabelas que elas referenciam.
    -- Exemplo de como seria em uma nova migration:
    -- ALTER TABLE peladeiro ADD COLUMN historico_id BIGINT;
    -- ALTER TABLE peladeiro ADD CONSTRAINT fk_peladeiro_historico FOREIGN KEY (historico_id) REFERENCES historico_peladeiro (id);
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;