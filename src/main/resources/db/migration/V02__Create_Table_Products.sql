CREATE TABLE product (
	id SERIAL PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	description VARCHAR(255),
	price DECIMAL NOT NULL,
	image VARCHAR(255) 
);

INSERT INTO product (name, description, price, image) values ('Hambúrguer', 'Descricão...', 7.00, 'url............');
INSERT INTO product (name, description, price, image) values ('Pizza', 'Descricão...', 30.00, 'url............');
INSERT INTO product (name, description, price, image) values ('Cola-Cola', 'Descricão...', 5.50, 'url............');