START TRANSACTION;
CREATE DATABASE IF NOT EXISTS acme;
USE acme;
CREATE TABLE IF NOT EXISTS `usuarios`(
	`ID` INT PRIMARY KEY AUTO_INCREMENT,
    `username` VARCHAR(255) NOT NULL,
    `password`VARCHAR(255) NOT NULL
);
INSERT INTO `usuarios`(`username`, `password`)
VALUES('rdghenrique@gmail.com', '12345678');
INSERT INTO `usuarios`(`username`, `password`)
VALUES('rodrigohrq@gmail.com', '12345678');
INSERT INTO `usuarios`(`username`, `password`)
VALUES('rodrigo@gmail.com', '12345678');
COMMIT;
