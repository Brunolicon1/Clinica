-- Cria a sequência para gerar os IDs
CREATE SEQUENCE if not exists pessoa_seq START WITH 1 INCREMENT BY 1;

-- Insere um paciente
INSERT INTO paciente (id, nome, telefone) VALUES (nextval('pessoa_seq'), 'João da Silva', '(12) 12345-6789');
INSERT INTO paciente (id, nome, telefone) VALUES (nextval('pessoa_seq'), 'Bruno Licon', '(11) 11111-1111');
INSERT INTO paciente (id, nome, telefone) VALUES (nextval('pessoa_seq'), 'Marcos Victor', '(22) 22222-2222');


-- Insere um médico
INSERT INTO medico (id, nome, crm) VALUES (nextval('pessoa_seq'), 'Dra. Ana Beatriz', 'CRM123456');
INSERT INTO medico (id, nome, crm) VALUES (nextval('pessoa_seq'), 'Dra. Luiza ', 'CRM654321');
INSERT INTO medico (id, nome, crm) VALUES (nextval('pessoa_seq'), 'Dra. Maria', 'CRM111222');

insert into TB_CONSULTA (DATA, OBSERVACAO, VALOR, ID_MEDICO, ID_PACIENTE) VALUES ('2025-05-10','dor de dente',500,4,1 );
insert into TB_CONSULTA (DATA, OBSERVACAO, VALOR, ID_MEDICO, ID_PACIENTE) VALUES ('2025-08-12','Virose',150,5,2 );
insert into TB_CONSULTA (DATA, OBSERVACAO, VALOR, ID_MEDICO, ID_PACIENTE) VALUES ('2025-05-10','dor de cabeça',300,6,3 );