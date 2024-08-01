CREATE database cadastro_veiculos;

USE cadastro_veiculos;

CREATE TABLE cadastro(

codigo	 		INT 				AUTO_INCREMENT PRIMARY KEY,
chassi 	 		VARCHAR(17)			NOT NULL,
modelo 			VARCHAR(100)   		NOT NULL,
ano		 	 	INT(4)				NOT NULL,
fabricante 		VARCHAR(100)		NOT NULL,
kmAtual 		INT(10)				NOT NULL,
opcionais 		VARCHAR(100)		NOT NULL,
descricao 		VARCHAR(100)		NOT NULL,
motorizacao 	VARCHAR(50)			NOT NULL,
combustivel 	VARCHAR(50)			NOT NULL,
cor 			VARCHAR(100)		NOT NULL,
fimPlaca		INT 				NOT NULL,
carroceria 		VARCHAR(100)		NOT NULL,
cidade 			VARCHAR(100)		NOT NULL,
valor 			INT(11)				NOT NULL
);

select * from cadastro;

drop table cadastro;


