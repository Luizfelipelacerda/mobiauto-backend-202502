
CREATE DATABASE IF NOT EXISTS mobiauto;
USE mobiauto;

-- Apagar tabelas se existirem
DROP TABLE IF EXISTS oportunidades;
DROP TABLE IF EXISTS veiculos;
DROP TABLE IF EXISTS clientes;
DROP TABLE IF EXISTS usuario_oportunidade;
DROP TABLE IF EXISTS mobiautousers;
DROP TABLE IF EXISTS revenda;

-- Tabela Revenda
CREATE TABLE revenda (
    revenda_id CHAR(36) PRIMARY KEY DEFAULT (UUID()),
    cnpj VARCHAR(18) NOT NULL UNIQUE,
    nome_social VARCHAR(255) NOT NULL
);

-- Tabela Usuário (mobiautoUsers)
    CREATE TABLE mobiautousers (
        user_id CHAR(36) PRIMARY KEY DEFAULT (UUID()),
        user_name VARCHAR(255) NOT NULL,
        email VARCHAR(255) NOT NULL UNIQUE,
        password VARCHAR(255) NOT NULL,
        cargo ENUM('ADMIN', 'PROPRIETARIO', 'GERENTE', 'ASSISTENTE') NOT NULL,
        revenda_id CHAR(36) NULL,
        FOREIGN KEY (revenda_id) REFERENCES revenda(revenda_id) ON DELETE SET NULL
    );

-- Tabela Cliente
CREATE TABLE clientes (
    cliente_id CHAR(36) PRIMARY KEY DEFAULT (UUID()),
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    telefone VARCHAR(20) NOT NULL
);

-- Tabela Veículo
CREATE TABLE veiculos (
    veiculo_id CHAR(36) PRIMARY KEY DEFAULT (UUID()),
    marca VARCHAR(100) NOT NULL,
    modelo VARCHAR(100) NOT NULL,
    versao VARCHAR(100),
    ano VARCHAR(10)
);

-- Tabela Oportunidade
CREATE TABLE oportunidades (
    oportunidade_id CHAR(36) PRIMARY KEY DEFAULT (UUID()),
    status ENUM('NOVO', 'EM_ATENDIMENTO', 'CONCLUIDO') NOT NULL,
    motivo_conclusao TEXT NULL,
    revenda_id CHAR(36),
    user_id CHAR(36),
    cliente_id CHAR(36),
    veiculo_id CHAR(36),
    data_insersao DATETIME NOT NULL DEFAULT NOW(),
    data_alteracao DATETIME NOT NULL DEFAULT NOW(),
    FOREIGN KEY (revenda_id) REFERENCES revenda(revenda_id) ON DELETE SET NULL,
    FOREIGN KEY (user_id) REFERENCES mobiautoUsers(user_id) ON DELETE SET NULL,
    FOREIGN KEY (cliente_id) REFERENCES clientes(cliente_id) ON DELETE SET NULL,
    FOREIGN KEY (veiculo_id) REFERENCES veiculos(veiculo_id) ON DELETE SET NULL
);


-- Tabela usuario Oportunidades
CREATE TABLE usuario_oportunidade (
    id CHAR(36) PRIMARY KEY DEFAULT (UUID()),
	oportunidades_em_andamento INT DEFAULT 0,
    oportunidades_concluidas INT DEFAULT 0,
    ultima_oportunidade datetime,
	assistente CHAR(36),
	revenda CHAR(36),
    FOREIGN KEY (assistente) REFERENCES mobiautousers(user_id) ON DELETE SET NULL,
    FOREIGN KEY (revenda) REFERENCES revenda(revenda_id) ON DELETE SET NULL
);




