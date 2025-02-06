begin transaction;
update caixinha set valor_Arrecadado = valor_arrecadado - 5 where id_usuario=1;
select * from caixinha where id_usuario =1 order by 4 desc;
rollback;

update caixinha set valor_arrecadado =412.23 where id =4;
update caixinha set valor_arrecadado =262.88 where id =8;
update caixinha set valor_arrecadado =261.87 where id =2;
update caixinha set valor_arrecadado =251.68 where id =16;
update caixinha set valor_arrecadado =206.03 where id =5;
update caixinha set valor_arrecadado =153.31 where id =1;
update caixinha set valor_arrecadado =133.47 where id =9;
update caixinha set valor_arrecadado =117.56 where id =13;
update caixinha set valor_arrecadado =106.05 where id =6;
update caixinha set valor_arrecadado =106.04 where id =7;
update caixinha set valor_arrecadado =90.85 where id =12;
update caixinha set valor_arrecadado =86.30 where id =15;
update caixinha set valor_arrecadado =81.57 where id =10;
update caixinha set valor_arrecadado =71.36 where id =17;
update caixinha set valor_arrecadado =69.46 where id =18;
update caixinha set valor_arrecadado =50.67 where id =23;
update caixinha set valor_arrecadado =47.31 where id =24;
update caixinha set valor_arrecadado =42.34 where id =11;
update caixinha set valor_arrecadado =42.34 where id =14;
update caixinha set valor_arrecadado =32.39 where id =22;
update caixinha set valor_arrecadado =11.13 where id =3;
update caixinha set valor_arrecadado =11.07 where id =20;
update caixinha set valor_arrecadado =11.07 where id =19;
update caixinha set valor_arrecadado =11.07 where id =21;
update caixinha set valor_arrecadado =11.06 where id =25;
update caixinha set valor_arrecadado =6.02 where id =26;
update caixinha set valor_arrecadado =6.02 where id =27;
update caixinha set valor_arrecadado =6.01 where id =30;


select sum(valor_arrecadado) from caixinha where id_usuario = 1;


select sum(valor_arrecadado) from caixinha where id_usuario = 1;
select * from utilidade;
select * from classificacao;
update caixinha set id_classificacao=8 where id=15;
update caixinha set id_utilidade=3 where id=22;
update classificacao set valor = 4 where id=6;
update classificacao set valor = 2 where id=7;
update classificacao set valor = 1 where id=8;

update caixinha set id_classificacao=34 where id=4;
update caixinha set id_classificacao=34 where id=8;
update caixinha set id_classificacao=1 where id=2;
update caixinha set id_classificacao=6 where id=16;
update caixinha set id_classificacao=34 where id=5;
update caixinha set id_classificacao=1 where id=1;
update caixinha set id_classificacao=34 where id=9;
update caixinha set id_classificacao=3 where id=13;
update caixinha set id_classificacao=34 where id=6;
update caixinha set id_classificacao=34 where id=7;
update caixinha set id_classificacao=4 where id=12;
update caixinha set id_classificacao=8 where id=15;
update caixinha set id_classificacao=34 where id=10;
update caixinha set id_classificacao=6 where id=17;
update caixinha set id_classificacao=7 where id=18;
update caixinha set id_classificacao=8 where id=23;
update caixinha set id_classificacao=8 where id=24;
update caixinha set id_classificacao=2 where id=11;
update caixinha set id_classificacao=2 where id=14;
update caixinha set id_classificacao=8 where id=22;
update caixinha set id_classificacao=1 where id=3;
update caixinha set id_classificacao=7 where id=20;
update caixinha set id_classificacao=7 where id=19;
update caixinha set id_classificacao=7 where id=21;
update caixinha set id_classificacao=2 where id=25;
update caixinha set id_classificacao=5 where id=27;
update caixinha set id_classificacao=67 where id=26;
update caixinha set id_classificacao=5 where id=30;

update caixinha set valor_arrecadado =652.05 where id =4;--forno
update caixinha set valor_arrecadado =414.78 where id =2;--pai
update caixinha set valor_arrecadado =354.64 where id =8;--aspirador
update caixinha set valor_arrecadado =256.59 where id =5;--fogão
update caixinha set valor_arrecadado =252.65 where id =16;--viajar com mamis
update caixinha set valor_arrecadado =206.23 where id =1;--apartamento entrada
update caixinha set valor_arrecadado =168.85 where id =10;--tramontina
update caixinha set valor_arrecadado =166.98 where id =9;--lava louças
update caixinha set valor_arrecadado =143.92 where id =13;--presente amoreco
update caixinha set valor_arrecadado =125.98 where id =6;--maquina lavar roupa
update caixinha set valor_arrecadado =125.96 where id =7;--geladeira
update caixinha set valor_arrecadado =91.19 where id =12;--sushi
update caixinha set valor_arrecadado =86.58 where id =15;--the sims
update caixinha set valor_arrecadado =71.58 where id =17;--urubici
update caixinha set valor_arrecadado =69.72 where id =18;--ipad
update caixinha set valor_arrecadado =50.85 where id =23;--quebra cabeça
update caixinha set valor_arrecadado =47.48 where id =24;--rummikub
update caixinha set valor_arrecadado =42.50 where id =11;--casamento
update caixinha set valor_arrecadado =42.50 where id =14;--studio
update caixinha set valor_arrecadado =36.61 where id =26;--emergencia carro
update caixinha set valor_arrecadado =32.51 where id =22;--kit elemneto quimico
update caixinha set valor_arrecadado =29.16 where id =30;--short decathlon
update caixinha set valor_arrecadado =11.16 where id =3;--apartamento quitar
update caixinha set valor_arrecadado =11.10 where id =20;--apple watch
update caixinha set valor_arrecadado =11.10 where id =19;--monitor
update caixinha set valor_arrecadado =11.09 where id =21;--zfold
update caixinha set valor_arrecadado =11.09 where id =25;--faxineira
update caixinha set valor_arrecadado =6.03 where id =27;--melissa

select * from caixinha where id_usuario=1 and ativo

update caixinha set data_vencimento = '2025-06-12', vencimento_programado=true where id = 34;
update caixinha set data_vencimento = '2055-05-20', vencimento_programado=true where id = 3;
update caixinha set data_vencimento = '2025-06-12', vencimento_programado=true where id = 36;
update caixinha set data_vencimento = '2025-11-30', vencimento_programado=true where id = 37;
update caixinha set data_vencimento = '2024-12-24', vencimento_programado=true where id = 39;
update caixinha set data_vencimento = '2024-12-30', vencimento_programado=true where id = 40;
update caixinha set data_vencimento = '2025-12-24', vencimento_programado=true where id = 43;
update caixinha set data_vencimento = '2024-12-24', vencimento_programado=true where id = 13;
update caixinha set data_vencimento = '2025-07-24', vencimento_programado=true where id = 15;

update caixinha set data_vencimento = '2026-01-01', vencimento_programado=true where id = 35;
select * from caixinha where id_usuario = 1 and ativo and not vencimento_programado order by valor_arrecadado desc;

update caixinha set valor_arrecadado =656.70 where id =4;--microondas
update caixinha set valor_arrecadado =417.46 where id =2;--pai
update caixinha set valor_arrecadado =356.95 where id =8;--aspirador
update caixinha set valor_arrecadado =258.32 where id =5;--fogão
update caixinha set valor_arrecadado =254.38 where id =16;--viajar com mamis
update caixinha set valor_arrecadado =207.53 where id =1;--apartamento entrada
update caixinha set valor_arrecadado =168.11 where id =9;--lava louças
update caixinha set valor_arrecadado =145.86 where id =13;--presente amoreco
update caixinha set valor_arrecadado =126.85 where id =6;--maquina lavar roupa
update caixinha set valor_arrecadado =126.81 where id =7;--geladeira
update caixinha set valor_arrecadado =91.80 where id =12;--sushi
update caixinha set valor_arrecadado =87.61 where id =10;--vans
update caixinha set valor_arrecadado =87.14 where id =15;--the sims
update caixinha set valor_arrecadado =72.02 where id =17;--urubici
update caixinha set valor_arrecadado =70.18 where id =18;--ipad
update caixinha set valor_arrecadado =51.18 where id =23;--quebra cabeça
update caixinha set valor_arrecadado =47.79 where id =24;--rummikub
update caixinha set valor_arrecadado =42.78 where id =11;--casamento
update caixinha set valor_arrecadado =42.77 where id =14;--studio
update caixinha set valor_arrecadado =36.85 where id =26;--emergencia carro
update caixinha set valor_arrecadado =32.73 where id =22;--kit elemneto quimico
update caixinha set valor_arrecadado =29.35 where id =30;--short decathlon
update caixinha set valor_arrecadado =11.25 where id =3;--apartamento quitar
update caixinha set valor_arrecadado =11.18 where id =20;--apple watch
update caixinha set valor_arrecadado =11.18, valor_total=2000 where id =19;--monitor
update caixinha set valor_arrecadado =11.18 where id =21;--zfold
update caixinha set valor_arrecadado =11.18 where id =25;--faxineira
update caixinha set valor_arrecadado =6.08 where id =27;--melissa
update caixinha set valor_arrecadado =1.02 where id =46;--panela pressao eletrica
update caixinha set valor_arrecadado =1.02 where id =47;--air fryer
update caixinha set valor_arrecadado =1.02 where id =45;--colocar na poupança
update caixinha set valor_arrecadado =1.02 where id =34;--dia dos namorados
update caixinha set valor_arrecadado =1.02 where id =35;--secadora roupa
update caixinha set valor_arrecadado =1.02 where id =36;--vinho
update caixinha set valor_arrecadado =1.02 where id =37;--dia dos amorecos
update caixinha set valor_arrecadado =1.02 where id =38;--calça
update caixinha set valor_arrecadado =1.02 where id =39;--roupa natal
update caixinha set valor_arrecadado =1.02 where id =40;--roupa ano novo
update caixinha set valor_arrecadado =1.02 where id =42;--lupo
update caixinha set valor_arrecadado =1.02 where id =43;--presentes 2025
update caixinha set valor_arrecadado =1.02 where id =44;--coreia
update caixinha set valor_arrecadado =1.01 where id =67;--show amoreco
update caixinha set valor_arrecadado =1.01 where id =68;--fantasia

update caixinha set valor_arrecadado = valor_arrecadado + 5 where id_usuario = 1 and ativo and not quitada


update caixinha set valor_arrecadado =661.74 where id =4;--microondas
update caixinha set valor_arrecadado =481.19 where id =2;--pai
update caixinha set valor_arrecadado =403.81 where id =8;--aspirador
update caixinha set valor_arrecadado =347.85 where id =5;--fogão
update caixinha set valor_arrecadado =312.10 where id =6;--maquina lavar roupa
update caixinha set valor_arrecadado =312.07 where id =7;--geladeira
update caixinha set valor_arrecadado =278.99 where id =9;--lava louças
update caixinha set valor_arrecadado =259.38 where id =16;--viajar com mamis
update caixinha set valor_arrecadado =251.95 where id =13;--presente amoreco
update caixinha set valor_arrecadado =228.22 where id =1;--apartamento entrada
update caixinha set valor_arrecadado =105.91 where id =34;--dia dos namorados
update caixinha set valor_arrecadado =101.32 where id =39;--roupa natal
update caixinha set valor_arrecadado =101.32 where id =40;--roupa ano novo
update caixinha set valor_arrecadado =100.54 where id =15;--the sims
update caixinha set valor_arrecadado =96.80 where id =12;--sushi
update caixinha set valor_arrecadado =92.61 where id =10;--vans
update caixinha set valor_arrecadado =78.68 where id =37;--dia dos amorecos
update caixinha set valor_arrecadado =77.04 where id =17;--urubici
update caixinha set valor_arrecadado =75.18 where id =18;--ipad
update caixinha set valor_arrecadado =61.73 where id =45;--colocar na poupança
update caixinha set valor_arrecadado =56.18 where id =23;--quebra cabeça
update caixinha set valor_arrecadado =52.79 where id =24;--rummikub
update caixinha set valor_arrecadado =51.76 where id =47;--air fryer
update caixinha set valor_arrecadado =47.78 where id =11;--casamento
update caixinha set valor_arrecadado =47.77 where id =14;--studio
update caixinha set valor_arrecadado =41.88 where id =26;--emergencia carro
update caixinha set valor_arrecadado =41.04 where id =46;--panela pressao eletrica
update caixinha set valor_arrecadado =37.73 where id =22;--kit elemneto quimico
update caixinha set valor_arrecadado =34.37 where id =30;--short decathlon
update caixinha set valor_arrecadado =16.26 where id =3;--apartamento quitar
update caixinha set valor_arrecadado =16.19 where id =25;--faxineira
update caixinha set valor_arrecadado =16.19 where id =21;--zfold
update caixinha set valor_arrecadado =16.19 where id =19;--monitor
update caixinha set valor_arrecadado =16.19 where id =20;--apple watch
update caixinha set valor_arrecadado =14.20 where id =36;--vinho
update caixinha set valor_arrecadado =11.09 where id =27;--melissa
update caixinha set valor_arrecadado =6.02 where id =35;--secadora roupa
update caixinha set valor_arrecadado =6.02 where id =38;--calça
update caixinha set valor_arrecadado =6.02 where id =42;--lupo
update caixinha set valor_arrecadado =6.02 where id =43;--presentes 2025
update caixinha set valor_arrecadado =6.02 where id =44;--coreia
update caixinha set valor_arrecadado =6.01 where id =67;--show amoreco
update caixinha set valor_arrecadado =6.01 where id =68;--fantasia

