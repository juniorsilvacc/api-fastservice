CREATE TABLE product (
	id SERIAL PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	description VARCHAR(255),
	price NUMERIC(255) NOT NULL,
	image VARCHAR(255)
);

INSERT INTO product (name, description, price, image) values ('Hambúrguer', 'Descricão...', 7.00, 'url............');
