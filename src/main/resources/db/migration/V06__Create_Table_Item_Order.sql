CREATE TABLE tb_item_order (
	order_id SERIAL NOT NULL,
	product_id SERIAL NOT NULL,
	amount INTEGER NOT NULL,
	PRIMARY KEY (order_id, product_id),
	
	CONSTRAINT fk_product_id FOREIGN KEY (product_id) REFERENCES tb_product (id),
 	CONSTRAINT fk_order_id FOREIGN KEY (order_id) REFERENCES tb_order (id)
);