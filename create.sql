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
-- Adicionar colunas na tabela 'caixinha'
ALTER TABLE caixinha
ADD COLUMN data_insercao DATE NOT NULL DEFAULT CURRENT_DATE,  -- Adiciona a data de inserção como DATE com valor padrão de data atual
ADD COLUMN data_vencimento DATE,                             -- Adiciona a data de vencimento, opcional
ADD COLUMN vencimento_programado BOOLEAN NOT NULL DEFAULT FALSE; -- Adiciona o campo booleano de vencimento programado com valor padrão 'false'

-- Verificar se as alterações foram aplicadas corretamente
SELECT column_name, data_type, is_nullable, column_default
FROM information_schema.columns
WHERE table_name = 'caixinha';
-- Alterando a tabela para permitir valores maiores (máximo 1.000.000.000 com 2 casas decimais)
ALTER TABLE caixinha
    ALTER COLUMN valor_total TYPE NUMERIC(10, 2),
    ALTER COLUMN valor_arrecadado TYPE NUMERIC(10, 2),
    ALTER COLUMN investimento TYPE NUMERIC(10, 2);
