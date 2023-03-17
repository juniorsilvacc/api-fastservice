CREATE TABLE tb_item_order (
	id SERIAL PRIMARY KEY,
	amount INTEGER NOT NULL,
	order_id INTEGER NOT NULL,
	product_id INTEGER NOT NULL,
	 
	
	FOREIGN KEY (product_id) REFERENCES tb_product (id),
 	FOREIGN KEY (order_id) REFERENCES tb_order (id)
);

INSERT INTO tb_item_order (amount, order_id, product_id) VALUES (1, 1, 1);
INSERT INTO tb_item_order (amount, order_id, product_id) VALUES (2, 1, 3);
INSERT INTO tb_item_order (amount, order_id, product_id) VALUES (1, 2, 1);
INSERT INTO tb_item_order (amount, order_id, product_id) VALUES (1, 2, 3);