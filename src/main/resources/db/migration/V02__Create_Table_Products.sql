CREATE TABLE tb_product (
	id SERIAL PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	description VARCHAR(255),
	price DECIMAL NOT NULL,
	image VARCHAR(255)
);

INSERT INTO tb_product (name, description, price, image) VALUES ('Hambúrguer de Calabresa', 'Hambúrguer de 450g recheado de catupity com calabresa', 18.00, 'LINK FOTO');
INSERT INTO tb_product (name, description, price, image) VALUES ('Pizza de Carne de Sol', 'Pizza de carne de sol com as bordas de cheddar', 35.00, 'LINK FOTO');
INSERT INTO tb_product (name, description, price, image) VALUES ('Suco de Morango', 'Copo de Suco de Morango de 500ml', 10.00, 'LINK FOTO');