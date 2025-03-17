USE mobiauto;

-- Criar a revendas
INSERT INTO revenda (cnpj, nome_social)
VALUES ('12.345.678/0001-99', 'Revenda 01'),
       ('61.840.456/0001-54', 'Revenda 02');

-- Criar o usuário Admin
INSERT INTO mobiautousers (user_name, email, password, cargo, revenda_id)
VALUES ('admin', 'admin@revenda.com', '$2a$10$St0LB5JTrsi9YqoByks3d.fUY0igyjWWPb3skaJNhEYMOw9lNaAsq', 'ADMIN', (SELECT revenda_id FROM revenda WHERE nome_social = 'Revenda 01'));

-- Criar o usuário Proprietário
INSERT INTO mobiautousers (user_name, email, password, cargo, revenda_id)
VALUES ('proprietario', 'proprietario@revenda.com', '$2a$10$St0LB5JTrsi9YqoByks3d.fUY0igyjWWPb3skaJNhEYMOw9lNaAsq', 'PROPRIETARIO', (SELECT revenda_id FROM revenda WHERE nome_social = 'Revenda 01'));

-- Criar os usuários Gerentes
INSERT INTO mobiautousers (user_name, email, password, cargo, revenda_id)
VALUES ('gerente1', 'gerente1@revenda.com', '$2a$10$St0LB5JTrsi9YqoByks3d.fUY0igyjWWPb3skaJNhEYMOw9lNaAsq', 'GERENTE', (SELECT revenda_id FROM revenda WHERE nome_social = 'Revenda 01'));

INSERT INTO mobiautousers (user_name, email, password, cargo, revenda_id)
VALUES ('gerente2', 'gerente2@revenda.com', '$2a$10$St0LB5JTrsi9YqoByks3d.fUY0igyjWWPb3skaJNhEYMOw9lNaAsq', 'GERENTE', (SELECT revenda_id FROM revenda WHERE nome_social = 'Revenda 01'));

-- Criar os usuários Assistentes
INSERT INTO mobiautousers (user_name, email, password, cargo, revenda_id)
VALUES ('assistente1', 'assistente1@revenda.com', '$2a$10$St0LB5JTrsi9YqoByks3d.fUY0igyjWWPb3skaJNhEYMOw9lNaAsq', 'ASSISTENTE', (SELECT revenda_id FROM revenda WHERE nome_social = 'Revenda 01')),
       ('assistente2', 'assistente2@revenda.com', '$2a$10$St0LB5JTrsi9YqoByks3d.fUY0igyjWWPb3skaJNhEYMOw9lNaAsq', 'ASSISTENTE', (SELECT revenda_id FROM revenda WHERE nome_social = 'Revenda 01')),
       ('assistente3', 'assistente3@revenda.com', '$2a$10$St0LB5JTrsi9YqoByks3d.fUY0igyjWWPb3skaJNhEYMOw9lNaAsq', 'ASSISTENTE', (SELECT revenda_id FROM revenda WHERE nome_social = 'Revenda 01'));

-- Criar Clientes
INSERT INTO clientes (nome, email, telefone)
VALUES ('Cliente 1', 'cliente1@revenda.com', '12345678901'),
       ('Cliente 2', 'cliente2@revenda.com', '12345678902'),
       ('Cliente 3', 'cliente3@revenda.com', '12345678903'),
       ('Cliente 4', 'cliente4@revenda.com', '12345678904'),
       ('Cliente 5', 'cliente5@revenda.com', '12345678905');

-- Criar Veículos
INSERT INTO veiculos (veiculo_id, marca, modelo, versao, ano)
VALUES
(UUID(), 'Toyota', 'Corolla', 'XEI 2.0', '2022'),
(UUID(), 'Honda', 'Civic', 'EXL 1.5 Turbo', '2023'),
(UUID(), 'Ford', 'Focus', 'Titanium 2.0', '2020'),
(UUID(), 'Volkswagen', 'Jetta', 'R-Line 1.4 TSI', '2021'),
(UUID(), 'Chevrolet', 'Cruze', 'Premier 1.4 Turbo', '2019');

-- Criar Oportunidades
INSERT INTO oportunidades (status, motivo_conclusao, revenda_id, user_id, cliente_id, veiculo_id)
VALUES
  ('NOVO', NULL, (SELECT revenda_id FROM revenda WHERE nome_social = 'Revenda 01'),
    (SELECT user_id FROM mobiautoUsers WHERE user_name = 'assistente1'),
    (SELECT cliente_id FROM clientes WHERE nome = 'Cliente 1'),
    (SELECT veiculo_id FROM veiculos WHERE marca = 'Toyota')),
  ('NOVO', NULL, (SELECT revenda_id FROM revenda WHERE nome_social = 'Revenda 01'),
    (SELECT user_id FROM mobiautoUsers WHERE user_name = 'assistente1'),
    (SELECT cliente_id FROM clientes WHERE nome = 'Cliente 2'),
    (SELECT veiculo_id FROM veiculos WHERE marca = 'Honda')),
  ('NOVO', NULL, (SELECT revenda_id FROM revenda WHERE nome_social = 'Revenda 01'),
    (SELECT user_id FROM mobiautoUsers WHERE user_name = 'assistente2'),
    (SELECT cliente_id FROM clientes WHERE nome = 'Cliente 3'),
    (SELECT veiculo_id FROM veiculos WHERE marca = 'Ford')),
  ('NOVO', NULL, (SELECT revenda_id FROM revenda WHERE nome_social = 'Revenda 01'),
    (SELECT user_id FROM mobiautoUsers WHERE user_name = 'assistente3'),
    (SELECT cliente_id FROM clientes WHERE nome = 'Cliente 4'),
    (SELECT veiculo_id FROM veiculos WHERE marca = 'Volkswagen')),
  ('EM_ATENDIMENTO', NULL, (SELECT revenda_id FROM revenda WHERE nome_social = 'Revenda 01'),
    (SELECT user_id FROM mobiautoUsers WHERE user_name = 'assistente3'),
    (SELECT cliente_id FROM clientes WHERE nome = 'Cliente 5'),
    (SELECT veiculo_id FROM veiculos WHERE marca = 'Chevrolet'));