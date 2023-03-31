CREATE TABLE tb_user (
	id SERIAL PRIMARY KEY,
	user_name VARCHAR(100),
	email  VARCHAR(100) UNIQUE,
	cpf  VARCHAR(11) UNIQUE,
	avatar  VARCHAR(255),
	password  VARCHAR(255),
	account_non_expired BOOLEAN DEFAULT TRUE NULL,
	account_non_locked BOOLEAN DEFAULT TRUE NULL,
	credentials_non_expired BOOLEAN DEFAULT TRUE NULL,
	enabled BOOLEAN DEFAULT TRUE NULL
);