CREATE TABLE tb_item_order (
	order_id SERIAL NOT NULL,
	product_id SERIAL NOT NULL,
	amount INTEGER NOT NULL,
	PRIMARY KEY (order_id, product_id),
	
	CONSTRAINT fk_product_id FOREIGN KEY (product_id) REFERENCES tb_product (id),
 	CONSTRAINT fk_order_id FOREIGN KEY (order_id) REFERENCES tb_order (id)
);

INSERT INTO tb_item_order (order_id, product_id, amount) VALUES (1, 2, 1);
INSERT INTO tb_item_order (order_id, product_id, amount) VALUES (1, 1, 2);
INSERT INTO tb_item_order (order_id, product_id, amount) VALUES (1, 3, 3);
INSERT INTO tb_item_order (order_id, product_id, amount) VALUES (2, 2, 1);