CREATE TABLE tb_item_order (
	id SERIAL PRIMARY KEY,
	amount INTEGER NOT NULL,
	order_id INTEGER NOT NULL,
	product_id INTEGER NOT NULL,
	 
	
	FOREIGN KEY (product_id) REFERENCES tb_product (id),
 	FOREIGN KEY (order_id) REFERENCES tb_order (id)
);
