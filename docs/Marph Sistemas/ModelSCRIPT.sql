CREATE TABLE endereco (
  idEndereco INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  pessoa_idPessoa INTEGER UNSIGNED NOT NULL,
  rua VARCHAR(255) NULL,
  bairro VARCHAR(45) NULL,
  telefone VARCHAR(20) NULL,
  PRIMARY KEY(idEndereco),
  INDEX FKIndexEnderecoPessoa(pessoa_idPessoa)
);

CREATE TABLE pessoa (
  idPessoa INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  nome VARCHAR(45) NULL,
  cpf NUMERIC(10,2) NULL DEFAULT 1.00,
  nascimento DATE NULL,
  PRIMARY KEY(idPessoa)
);

CREATE TABLE usuario (
  pessoa_idPessoa INTEGER UNSIGNED NOT NULL,
  login VARCHAR(255) NULL,
  senha VARCHAR(255) NULL,
  status_2 BOOL NULL DEFAULT FALSE,
  PRIMARY KEY(pessoa_idPessoa),
  INDEX FKIndexUsuarioPessoa(pessoa_idPessoa)
);


