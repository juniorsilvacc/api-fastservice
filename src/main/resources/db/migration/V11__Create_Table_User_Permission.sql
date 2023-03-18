CREATE TABLE tb_user_permission (
  id_user SERIAL NOT NULL,
  id_permission SERIAL NOT NULL,
  
  PRIMARY KEY (id_user, id_permission),
  
  FOREIGN KEY (id_user) REFERENCES tb_user (id), 
  FOREIGN KEY (id_permission) REFERENCES tb_permission (id)
);