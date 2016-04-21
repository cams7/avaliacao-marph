CREATE DATABASE  IF NOT EXISTS `crud_sys`;
USE `crud_sys`;

DROP TABLE IF EXISTS `usuario`;
DROP TABLE IF EXISTS `endereco`;
DROP TABLE IF EXISTS `pessoa`;

CREATE TABLE `pessoa` (
  `id_pessoa` bigint(20) NOT NULL AUTO_INCREMENT,
  `cpf` varchar(11) NOT NULL,
  `nascimento` datetime DEFAULT NULL,
  `nome` varchar(45) NOT NULL,
  PRIMARY KEY (`id_pessoa`),
  UNIQUE KEY `UK_pessoa_cpf` (`cpf`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

CREATE TABLE `usuario` (
  `id_usuario` bigint(20) NOT NULL AUTO_INCREMENT,
  `status` bit(1) NOT NULL,
  `login` varchar(30) NOT NULL,
  `senha` varchar(80) NOT NULL,
  `autorizacoes` varchar(50) DEFAULT NULL,
  `id_pessoa` bigint(20) NOT NULL,
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `UK_usuario_login` (`login`),
  KEY `FK_usuario_pessoa` (`id_pessoa`),
  CONSTRAINT `FK_usuario_pessoa` FOREIGN KEY (`id_pessoa`) REFERENCES `pessoa` (`id_pessoa`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

CREATE TABLE `endereco` (
  `id_endereco` bigint(20) NOT NULL AUTO_INCREMENT,
  `bairro` varchar(50) NOT NULL,
  `rua` varchar(100) NOT NULL,
  `telefone` varchar(11) NOT NULL,
  `id_pessoa` bigint(20) NOT NULL,
  PRIMARY KEY (`id_endereco`),
  KEY `FK_endereco_pessoa` (`id_pessoa`),
  CONSTRAINT `FK_endereco_pessoa` FOREIGN KEY (`id_pessoa`) REFERENCES `pessoa` (`id_pessoa`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
