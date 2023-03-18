CREATE TABLE tb_user (
	id SERIAL PRIMARY KEY,
	name VARCHAR(100) NOT NULL,
	email  VARCHAR(100) NOT NULL UNIQUE,
	cpf  VARCHAR(11) NOT NULL UNIQUE,
	avatar  VARCHAR(255),
	password  VARCHAR(255) NOT NULL,
	account_non_expired bit(1) DEFAULT NULL,
	account_non_locked bit(1) DEFAULT NULL,
	credentials_non_expired bit(1) DEFAULT NULL,
	enabled bit(1) DEFAULT NULL,
	createdAt DATE
);