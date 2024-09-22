-- Dropar tabelas se já existirem
DROP TABLE IF EXISTS caixinha;
DROP TABLE IF EXISTS utilidade;
DROP TABLE IF EXISTS classificacao;
DROP TABLE IF EXISTS usuario;

-- Tabela de Usuários
CREATE TABLE usuario (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    data_nascimento DATE NOT NULL,
    nome_usuario VARCHAR(100) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    ativo BOOLEAN NOT NULL DEFAULT TRUE
);

-- Tabela de Classificações
CREATE TABLE classificacao (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    valor BIGINT NOT NULL,
    ativo BOOLEAN NOT NULL DEFAULT TRUE,
    id_usuario BIGINT REFERENCES usuario(id) ON DELETE CASCADE -- Adiciona o relacionamento com usuário
);

-- Tabela de Utilidades
CREATE TABLE utilidade (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    valor BIGINT NOT NULL,
    ativo BOOLEAN NOT NULL DEFAULT TRUE,
    id_usuario BIGINT REFERENCES usuario(id) ON DELETE CASCADE -- Adiciona o relacionamento com usuário
);

-- Tabela de Caixinhas
CREATE TABLE caixinha (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    valor_total NUMERIC(8, 2) NOT NULL,      -- Valor máximo 1.000.000 com 2 casas decimais
    valor_arrecadado NUMERIC(8, 2) NOT NULL, -- Valor máximo 1.000.000 com 2 casas decimais
    investimento NUMERIC(8, 2) default 0,     -- Valor máximo 1.000.000 com 2 casas decimais
    id_classificacao BIGINT REFERENCES classificacao(id) ON DELETE SET NULL,
    id_utilidade BIGINT REFERENCES utilidade(id) ON DELETE SET NULL,
    mensagem VARCHAR(500),  -- Campo de mensagem alterado para VARCHAR
    quitada BOOLEAN NOT NULL DEFAULT FALSE,
    id_usuario BIGINT REFERENCES usuario(id) ON DELETE CASCADE,
    ativo BOOLEAN NOT NULL DEFAULT TRUE
);
