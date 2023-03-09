CREATE TABLE tb_order (
	id SERIAL PRIMARY KEY,
	name VARCHAR(80),
	position INTEGER NOT NULL,
	draft BOOLEAN DEFAULT TRUE,
	status INTEGER,
	moment DATE
);

INSERT INTO tb_order (name, position, draft, status, moment) VALUES ('Junior Silva', 1, false, 2, '2023-03-08T19:30:50');
INSERT INTO tb_order (name, position, draft, status, moment) VALUES ('Cicero Junior', 3, false, 2, '2023-03-08T19:40:00');
INSERT INTO tb_order (name, position, draft, status, moment) VALUES ('Leontina Dias', 5, true, 1, '2023-03-08T19:45:30');