-- Inserindo dados na tabela de Usuario
INSERT INTO usuario (nome, data_nascimento, nome_usuario, senha, ativo) VALUES
('Thaís Santos Landfeldt', '2000-06-18', 'tatitata', 'senha123', true),
('Gabriel Moraes de Freitas', '2000-12-13', 'peido13', 'senha456', true),
('Pedro Antônio Antunes Landfeldt', '1972-05-24', 'paal', 'senha789', true),
('Felipe Santos Landfeldt', '2006-08-29', 'felipe.landfeldt', 'senha321', true),
('Cristiane Santos Landfeldt', '1972-08-07', 'cris', 'senha654', false);

-- Inserindo dados na tabela de Classificacao
INSERT INTO classificacao (nome, valor, ativo,id_usuario) VALUES
('DIVIDA', 128, true,1),
('PRECISO_PARA_AGORA', 64, true,1),
('PRECISO', 32, true,1),
('QUERO_MUITO', 16, true,1),
('QUERO', 8, true,1),
('VIAGEM', 8, true,1),
('TECNOLOGIA', 8, true,1),
('BUGIGANGA', 8, true,1);

-- Inserindo dados na tabela de Utilidade
INSERT INTO utilidade (nome, valor, ativo,id_usuario) VALUES
('DIVIDA', 4, true,1),
('UTIL', 2, true,1),
('INUTIL', 1, true,1);

-- Inserindo dados na tabela de Caixinha
INSERT INTO caixinha (nome, valor_total, valor_arrecadado, id_classificacao, id_utilidade, quitada, id_usuario)
VALUES
('Apartamento ENTRADA', 10052.87, 147.35, (SELECT id FROM classificacao WHERE nome = 'DIVIDA'), (SELECT id FROM utilidade WHERE nome = 'DIVIDA'), false, 1),
('Pagar pai', 4000, 255.42, (SELECT id FROM classificacao WHERE nome = 'DIVIDA'), (SELECT id FROM utilidade WHERE nome = 'DIVIDA'), false, 1),
('Apartamento QUITAR', 168000, 6.08, (SELECT id FROM classificacao WHERE nome = 'DIVIDA'), (SELECT id FROM utilidade WHERE nome = 'DIVIDA'), false, 1),
('Forno elétrico', 700, 404.57, (SELECT id FROM classificacao WHERE nome = 'PRECISO_PARA_AGORA'), (SELECT id FROM utilidade WHERE nome = 'UTIL'), false, 1),
('Fogão', 1500, 199.86, (SELECT id FROM classificacao WHERE nome = 'PRECISO_PARA_AGORA'), (SELECT id FROM utilidade WHERE nome = 'UTIL'), false, 1),
('Máquina de lavar de roupa', 3000, 100.45, (SELECT id FROM classificacao WHERE nome = 'PRECISO_PARA_AGORA'), (SELECT id FROM utilidade WHERE nome = 'UTIL'), false, 1),
('Geladeira', 3000, 100.45, (SELECT id FROM classificacao WHERE nome = 'PRECISO_PARA_AGORA'), (SELECT id FROM utilidade WHERE nome = 'UTIL'), false, 1),
('Aspirador', 1000, 256.44, (SELECT id FROM classificacao WHERE nome = 'PRECISO_PARA_AGORA'), (SELECT id FROM utilidade WHERE nome = 'UTIL'), false, 1),
('Lava louça', 2000, 127.71, (SELECT id FROM classificacao WHERE nome = 'PRECISO_PARA_AGORA'), (SELECT id FROM utilidade WHERE nome = 'UTIL'), false, 1),
('Tramontina itria', 850, 76.13, (SELECT id FROM classificacao WHERE nome = 'QUERO_MUITO'), (SELECT id FROM utilidade WHERE nome = 'UTIL'), false, 1),
('Casamento', 30000, 37.12, (SELECT id FROM classificacao WHERE nome = 'QUERO_MUITO'), (SELECT id FROM utilidade WHERE nome = 'INUTIL'), false, 1),
('Sushi Cléber', 600, 85.34, (SELECT id FROM classificacao WHERE nome = 'QUERO_MUITO'), (SELECT id FROM utilidade WHERE nome = 'INUTIL'), false, 1),
('Presente de Natal amoreco', 400, 111.91, (SELECT id FROM classificacao WHERE nome = 'QUERO_MUITO'), (SELECT id FROM utilidade WHERE nome = 'INUTIL'), false, 1),
('Studio Gabriel', 50000, 37.11, (SELECT id FROM classificacao WHERE nome = 'QUERO'), (SELECT id FROM utilidade WHERE nome = 'UTIL'), false, 1),
('The sims', 200, 80.84, (SELECT id FROM classificacao WHERE nome = 'QUERO'), (SELECT id FROM utilidade WHERE nome = 'INUTIL'), false, 1),
('Viagem com mamis', 20000, 245.21, (SELECT id FROM classificacao WHERE nome = 'VIAGEM'), (SELECT id FROM utilidade WHERE nome = 'INUTIL'), false, 1),
('Urubici', 2000, 65.94, (SELECT id FROM classificacao WHERE nome = 'VIAGEM'), (SELECT id FROM utilidade WHERE nome = 'INUTIL'), false, 1),
('iPad', 3500, 64.08, (SELECT id FROM classificacao WHERE nome = 'TECNOLOGIA'), (SELECT id FROM utilidade WHERE nome = 'UTIL'), false, 1),
('Monitor', 12000, 6.03, (SELECT id FROM classificacao WHERE nome = 'TECNOLOGIA'), (SELECT id FROM utilidade WHERE nome = 'UTIL'), false, 1),
('Apple Watch', 4500, 6.03, (SELECT id FROM classificacao WHERE nome = 'TECNOLOGIA'), (SELECT id FROM utilidade WHERE nome = 'UTIL'), false, 1),
('ZFOLD', 11000, 6.02, (SELECT id FROM classificacao WHERE nome = 'TECNOLOGIA'), (SELECT id FROM utilidade WHERE nome = 'UTIL'), false, 1),
('Kit elemento químico', 200, 27.23, (SELECT id FROM classificacao WHERE nome = 'BUGIGANGA'), (SELECT id FROM utilidade WHERE nome = 'UTIL'), false, 1),
('Quebra-cabeça', 150, 45.39, (SELECT id FROM classificacao WHERE nome = 'BUGIGANGA'), (SELECT id FROM utilidade WHERE nome = 'INUTIL'), false, 1),
('Rummikub', 200, 42.05, (SELECT id FROM classificacao WHERE nome = 'BUGIGANGA'), (SELECT id FROM utilidade WHERE nome = 'INUTIL'), false, 1),
('Faxineira', 20000, 6.01, (SELECT id FROM classificacao WHERE nome = 'QUERO_MUITO'), (SELECT id FROM utilidade WHERE nome = 'UTIL'), false, 1),
('Emergência carro', 4000, 1, (SELECT id FROM classificacao WHERE nome = 'PRECISO'), (SELECT id FROM utilidade WHERE nome = 'UTIL'), false, 1),
('Melissa', 370, 1, (SELECT id FROM classificacao WHERE nome = 'QUERO'), (SELECT id FROM utilidade WHERE nome = 'INUTIL'), false, 1);
