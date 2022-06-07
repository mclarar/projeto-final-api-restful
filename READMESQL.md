# projeto-final-api-restful
Repositório destinado a entrega do projeto final da disciplina Desenvolvimento de API Restful da Residência em TIC Software do Serratec 2022.1 - Turma 01, grupo 1

comandos SQL

CREATE TABLE endereco (
id_endereco SERIAL PRIMARY KEY, 
cep varchar(9) NOT NULL, 
rua varchar(100) NOT NULL,
bairro varchar(50) NOT NULL,
cidade varchar(30),
numero INTEGER NOT NULL,
complemento varchar(20),
uf varchar(2))
;

CREATE TABLE cliente (
id_cliente SERIAL PRIMARY KEY,
email varchar(100) NOT NULL,
nome_completo varchar(100) NOT NULL,
cpf varchar(14) NOT NULL,
telefone varchar(11),
data_nascimento DATE, 
id_endereco INTEGER, 
FOREIGN KEY(id_endereco) REFERENCES endereco(id_endereco)
);

CREATE TABLE pedido (
id_pedido SERIAL PRIMARY KEY,
data_pedido DATE NOT NULL,
data_entrega DATE, 
data_envio DATE,
status boolean, --ativo/inativo,
id_cliente INTEGER, 
FOREIGN KEY(id_cliente) REFERENCES cliente(id_cliente)
);

CREATE TABLE categoria (
id_categoria SERIAL PRIMARY KEY, 
nome varchar(30) NOT NULL, 
descricao varchar(150)
);

CREATE TABLE produto (
id_produto SERIAL PRIMARY KEY,
nome varchar(30) NOT NULL,
descricao varchar(100),
qtd_estoque INTEGER NOT NULL,
data_cadastro DATE,
valor_unitario FLOAT NOT NULL, 
imagem varchar(100),
id_categoria INTEGER, 
FOREIGN KEY(id_categoria) REFERENCES categoria(id_categoria)
);


CREATE TABLE item_pedido (
id_item_pedido SERIAL PRIMARY KEY,
quantidade INTEGER NOT NULL,
preco_venda INTEGER NOT NULL,
percentual_desconto NUMERIC(3,2),
valor_bruto NUMERIC(10,2),
valor_liquido NUMERIC(10,2),
id_pedido INTEGER, 
FOREIGN KEY (id_pedido) REFERENCES pedido(id_pedido),
id_produto INTEGER,  
FOREIGN KEY (id_produto) REFERENCES produto(id_produto)
);

ALTER TABLE item_pedido ALTER COLUMN percentual_desconto TYPE NUMERIC(10,2);

INSERT INTO endereco (cep, rua, bairro, cidade, numero, complemento, UF) VALUES 
('25620120', 'Rua Casimiro de Abreu', 'Centro', 'Petrópolis', '39','Apto', 'RJ');

INSERT INTO cliente (email, nome_completo, cpf, telefone, data_nascimento, id_endereco) VALUES
('maria@gmail.com', 'Maria Clara Ribeiro de Paula', '14144647706', '24992077250', '1995-11-27',1);

INSERT INTO pedido (data_pedido, status, id_cliente) values 
('2022-06-06', false, 1);

INSERT INTO categoria (nome, descricao) VALUES
('Eletronicos','aparelho eletronico');

INSERT INTO produto (nome, descricao, qtd_estoque, data_cadastro, valor_unitario, id_categoria) VALUES
('Playstation 5', 'console de videogame', 50, '2021-11-19', 5000, 1); 

INSERT INTO item_pedido (quantidade, preco_venda, percentual_desconto, id_pedido, id_produto) VALUES
(1, 5000, 0, 1, 1);
