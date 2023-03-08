CREATE TABLE product_category (
  product_id SERIAL NOT NULL,
  category_id SERIAL NOT NULL,
  PRIMARY KEY (product_id, category_id),
  
  CONSTRAINT fk_product_id FOREIGN KEY (product_id) REFERENCES product (id),
  CONSTRAINT fk_category_id FOREIGN KEY (category_id) REFERENCES category (id)
);