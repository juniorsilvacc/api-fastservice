CREATE TABLE product (
	id SERIAL PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	description VARCHAR(255),
	price DECIMAL NOT NULL,
	image VARCHAR(255)
);

INSERT INTO product (name, description, price, image) values ('Hambúrguer de Calabresa', 'Hambúrguer de 450g recheado de catupity com calabresa', 18.00, 'LINK FOTO');
INSERT INTO product (name, description, price, image) values ('Pizza de Carne de Sol', 'Pizza de carne de sol com as bordas de cheddar', 35.00, 'LINK FOTO');
INSERT INTO product (name, description, price, image) values ('Suco de Morango', 'Copo de Suco de Morango de 500ml', 10.00, 'LINK FOTO');