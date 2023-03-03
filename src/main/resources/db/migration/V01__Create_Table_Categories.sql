CREATE TABLE category (
	id SERIAL PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	description VARCHAR(255)
);

INSERT INTO category (name) values ('Hambúrguer');
INSERT INTO category (name, description) values ('Pizzas', 'A venda de pizzas pelo app representa quase 24% dos pedidos feitos, sendo uma ótima iguaria para se vender pela plataforma.');
INSERT INTO category (name, description) values ('Comida Brasileira', 'Mesmo sendo um prato mais tradicional que passa longe dos fasts-foods, a comida brasileira também está na lista dos 10 mais consumidos no país, o que representa 12,4% dos pedidos.');