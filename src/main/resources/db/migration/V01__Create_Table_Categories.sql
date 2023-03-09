CREATE TABLE tb_category (
	id SERIAL PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	description VARCHAR(255)
);

INSERT INTO tb_category (name, description) VALUES ('Hambúrguers', 'Falando de um prato específico, o hambúrguer foi o mais pedido no país durante o último ano, chegando a uma média de 7 milhões a cada mês.');
INSERT INTO tb_category (name, description) VALUES ('Sucos', 'Apresentam forte crescimento, com maior destaque para os estados de São Paulo e Mato Grosso.');
INSERT INTO tb_category (name, description) VALUES ('Pizzas', 'A venda de pizzas pelo app representa quase 24% dos pedidos feitos, sendo uma ótima iguaria para se vender pela plataforma.');
