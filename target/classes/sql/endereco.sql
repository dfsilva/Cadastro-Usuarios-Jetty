DROP TABLE IF EXISTS `cadastro`.`endereco`;
CREATE TABLE  `cadastro`.`endereco` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `apelido` varchar(255) NOT NULL DEFAULT '',
  `cep` varchar(8) NOT NULL DEFAULT '',
  `logradouro` varchar(255) NOT NULL DEFAULT '',
  `complemento` varchar(255) DEFAULT NULL,
  `bairro` varchar(100) DEFAULT NULL,
  `localidade` varchar(150) NOT NULL DEFAULT '',
  `uf` char(2) NOT NULL DEFAULT '',
  `idUsuario` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_usuario` (`idUsuario`),
  CONSTRAINT `fk_usuario` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;