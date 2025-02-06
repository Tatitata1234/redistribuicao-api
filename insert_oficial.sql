INSERT INTO usuario (nome, data_nascimento, nome_usuario, senha, ativo)
VALUES ('Thaís Landfeldt', '2000-06-18', 'Tatitata1234', '123', true);

INSERT INTO classificacao (nome, valor, ativo, id_usuario) VALUES
    ('Dívida', 256, true, 1),
    ('Eletrodomésticos apartamento', 128, true, 1),
    ('Planos pro futuro', 64, true, 1),
    ('Presentes', 32, true, 1),
    ('Viagem', 16, true, 1),
    ('Restaurante', 8, true, 1),
    ('Roupa', 4, true, 1),
    ('Tecnologia', 2, true, 1),
    ('Bugiganga', 1, true, 1);

INSERT INTO utilidade (nome, valor, ativo, id_usuario) VALUES
    ('Dívida', 4, true, 6),
    ('Útil', 2, true, 6),
    ('Inútil', 1, true, 6);

-- Inserir nova classificação com id 27
INSERT INTO classificacao ( nome, valor, ativo, id_usuario)
VALUES ( 'emergencia', 256, true, 6);

-- Atualizar o valor da classificação com id = 18, multiplicando o valor atual por 2
UPDATE classificacao
SET valor = valor * 2
WHERE id = 18;

-- Inserir nova utilidade
INSERT INTO utilidade (nome, valor, ativo, id_usuario)
VALUES ('emergencia', 4, true, 6);

-- Atualizar o valor da utilidade com id = 4, multiplicando o valor atual por 2
UPDATE utilidade
SET valor = valor * 2
WHERE id = 4;

INSERT INTO caixinha (
    nome, valor_total, valor_arrecadado, id_classificacao, id_utilidade, quitada, id_usuario, ativo, data_vencimento, vencimento_programado
) VALUES
    ('Microondas', 				700.00, 	675.85, 19, 5, false, 6, true,	'2026-01-01', 	true),
    ('Aspirador de pó', 		1000.00, 	413.91, 19, 5, false, 6, true, 	'2026-01-01',	true),
    ('Fogão cooktop', 			1500.00, 	357.37, 19, 5, false, 6, true, 	'2026-01-01', 	true),
    ('Máquina de lavar roupa', 	3000.00, 	321.12, 19, 5, false, 6, true, 	'2026-01-01', 	true),
    ('Geladeira', 				3000.00, 	321.09, 19, 5, false, 6, true, 	'2026-01-01', 	true),
    ('Lava louças', 			2000.00, 	287.59, 19, 5, false, 6, true, 	'2026-01-01', 	true),
    ('Viagem com mamis', 		20000.00, 	267.83, 22, 6, false, 6, true, 	NULL, 			false),
    ('Dia dos namorados', 		1000.00, 	112.28, 20, 6, false, 6, true, 	'2025-06-18', 	true),
    ('The sims', 				200.00, 	106.83, 26, 6, false, 6, true, 	NULL,			false),
    ('Sushi by Cleber', 		600.00, 	103.06, 23, 6, false, 6, true, 	NULL, 			false),
    ('Vans', 					550.00, 	98.81, 	24, 6, false, 6, true, 	NULL, 			false),
    ('Dia dos amorecos', 		1000.00, 	84.71, 	20, 6, false, 6, true, 	'2025-11-30', 	true),
    ('iPad', 					3500.00, 	81.16, 	25, 5, false, 6, true, 	NULL, 			false),
	('Viajar Urubici', 			6000.00, 	77.48, 	22, 6, false, 6, true, 	NULL, 			false),
    ('Colocar na poupança', 	600.00, 	67.53, 	18, 4, false, 6, true, 	NULL, 			false),
    ('Quebra cabeça', 			150.00, 	61.91, 	26, 6, false, 6, true, 	NULL, 			false),
    ('Rummickup', 				200.00, 	58.48, 	26, 6, false, 6, true, 	NULL, 			false),
    ('Air fryes 2 andar', 		700.00, 	57.44, 	19, 5, false, 6, true, 	'2026-01-01', 	true),
    ('Studio Gabriel', 			50000.00, 	53.40, 	20, 5, false, 6, true, 	NULL, 			false),
    ('Casamento', 				30000.00, 	53.40, 	20, 6, false, 6, true, 	NULL, 			false),
    ('Emergencia carro', 		4000.00, 	47.42, 	27, 7, false, 6, true, 	NULL, 			false),--adicionar emergencia=27 e emergencia=7 ambos depois de divida
    ('Panela pressão eletrica', 550.00, 	46.59, 	19, 5, false, 6, true, 	'2026-01-01', 	true),
    ('Kit elementos químicos', 	200.00, 	43.25, 	26, 6, false, 6, true, 	NULL, 			false),
    ('Short decathlon', 		80.00, 		39.83, 	24, 5, false, 6, true, 	NULL, 			false),
    ('Apartamento Quitar', 		168000.00, 	21.48, 	18, 4, false, 6, true, 	'2056-07-01', 	true),
    ('Monitor curvo', 			2000.00, 	21.42, 	25, 6, false, 6, true, 	NULL, 			false),
	('Apple watch', 			4500.00, 	21.42, 	25, 5, false, 6, true, 	NULL, 			false),
    ('Faxineira', 				92000.00, 	21.41, 	20, 5, false, 6, true, 	NULL, 			false),
    ('Z fold', 					11000.00, 	21.41, 	25, 5, false, 6, true, 	NULL, 			false),
    ('Vinho', 					100.00, 	19.40, 	21, 6, false, 6, true, 	'2025-06-12', 	true),
    ('Melissa', 				370.00, 	16.25, 	24, 6, false, 6, true, 	NULL, 			false),
    ('Show com o amoreco', 		3000.00, 	11.12, 	22, 6, false, 6, true, 	NULL, 			false),
    ('Fantasia', 				300.00, 	11.12, 	26, 6, false, 6, true, 	'2025-10-31', 	true),
    ('Scadora de roupa', 		3700.00, 	11.12, 	19, 5, false, 6, true, 	'2026-01-01',	true),
    ('Lupo', 					200.00, 	11.12, 	24, 5, false, 6, true, 	NULL, 			false),
    ('Presentes 2025', 			1500.00, 	11.12, 	21, 6, false, 6, true, 	'2025-12-01', 	true),
    ('Coreia', 					30000.00, 	11.12, 	22, 6, false, 6, true, 	NULL, 			false),
    ('Chaleira elétrica trans', 380.00, 	6.04, 	19, 5, false, 6, true, 	'2026-01-01', 	true),
    ('Pagar pai 2025', 			8000.00, 	6.04, 	18, 4, false, 6, true, 	'2025-12-01', 	true),
	('Formatura Gabriel', 		30000.00, 	1.00, 	20, 6, false, 6, true, 	NULL, 			false),
    ('Filho 2', 				400000.00, 	1.00, 	20, 5, false, 6, true, 	'2047-01-01',	true),
    ('Filho 1', 				400000.00, 	1.00, 	20, 5, false, 6, true, 	'2045-01-01', 	true),
    ('Casa', 					1000000.00, 1.00, 	20, 5, false, 6, true, 	NULL, 			false),
    ('Aniversário 2025', 		3000.00, 	1.00, 	20, 6, false, 6, true, 	'2025-06-18',	true),
    ('Formatura', 				30000.00, 	1.00, 	20, 6, false, 6, true, 	NULL, 			false),
    ('Pagar pai 2026', 			8000.00, 	1.00, 	18, 4, false, 6, true, 	'2025-12-01',	true),
    ('15 anos Duda', 			3000.00, 	1.00, 	21, 6, false, 6, true, 	'2027-01-01', 	true),
    ('Presente amoreco 2025', 	750, 		1.00, 	21, 6, false, 6, true, 	'2025-12-01', 	true),
    ('Amigo secreto 2025', 		70.00, 		1.00, 	21, 6, false, 6, true, 	'2025-12-01', 	true),
    ('Roupa NatalAnoNovo', 		400.00, 	0.00, 	24, 6, false, 6, true, 	'2025-12-01', 	true);