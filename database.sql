CREATE TABLE Administrador (
    ID VARCHAR(36) PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL
);

CREATE TABLE Configuracoes (
    Horario_funcionamento VARCHAR(255)
);

CREATE TABLE Categoria (
    ID VARCHAR(36) PRIMARY KEY,
    Nome VARCHAR(255) NOT NULL
);

CREATE TABLE Produtos (
    ID VARCHAR(36) PRIMARY KEY,
    Nome VARCHAR(255) NOT NULL,
    Descricao TEXT,
    Preco DECIMAL(10,2) NOT NULL,
    Categoria VARCHAR(36) NOT NULL,
    Imagem VARCHAR(255),
    FOREIGN KEY (Categoria) REFERENCES Categoria(ID)
);

CREATE TABLE Cliente (
    ID VARCHAR(36) PRIMARY KEY,
    CPF VARCHAR(14) NOT NULL UNIQUE,
    Nome VARCHAR(100) NOT NULL,
    Sobrenome VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL
);

CREATE TABLE Cliente_Endereco (
    ID VARCHAR(36) PRIMARY KEY,
    IDCliente VARCHAR(36) NOT NULL,
    Rua VARCHAR(255) NOT NULL,
    Cidade VARCHAR(100) NOT NULL,
    Estado VARCHAR(50) NOT NULL,
    Bairro VARCHAR(100) NOT NULL,
    CEP VARCHAR(20) NOT NULL,
    Numero VARCHAR(20) NOT NULL,
    Tipo VARCHAR(50) NOT NULL,
    FOREIGN KEY (IDCliente) REFERENCES Cliente(ID)
);

CREATE TABLE Cliente_Telefone (
    ID VARCHAR(36) PRIMARY KEY,
    IDCliente VARCHAR(36) NOT NULL,
    Telefone VARCHAR(20) NOT NULL,
    FOREIGN KEY (IDCliente) REFERENCES Cliente(ID)
);

CREATE TABLE Pedido (
    ID VARCHAR(36) PRIMARY KEY,
    IDCliente VARCHAR(36) NOT NULL,
    PrecoTotal DECIMAL(10,2) NOT NULL,
    TipoEntrega VARCHAR(50) NOT NULL,
    Endereco TEXT,
    Forma_de_pagamento VARCHAR(50) NOT NULL,
    HorarioCriacao DATETIME NOT NULL,
    HorarioSaida DATETIME,
    HorarioFinalizacao DATETIME,
    Telefone VARCHAR(20) NOT NULL,
    Status VARCHAR(50) NOT NULL,
    Motivo_Cancelamento TEXT,
    FOREIGN KEY (IDCliente) REFERENCES Cliente(ID)
);

CREATE TABLE Produtos_Pedido (
    ID VARCHAR(36) PRIMARY KEY,
    IDPedido VARCHAR(36) NOT NULL,
    IDProduto VARCHAR(36) NOT NULL,
    Preco DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (IDPedido) REFERENCES Pedido(ID),
    FOREIGN KEY (IDProduto) REFERENCES Produtos(ID)
);

CREATE TABLE Reserva (
    ID VARCHAR(36) PRIMARY KEY,
    IDCliente VARCHAR(36) NOT NULL,
    Horario DATETIME NOT NULL,
    Quantidade_de_pessoas INT NOT NULL,
    Tipo_Evento VARCHAR(100),
    Motivo_cancelamento TEXT,
    FOREIGN KEY (IDCliente) REFERENCES Cliente(ID)
);

-- Dados de Exemplo
INSERT INTO Administrador (ID, email, senha) VALUES ('e24bd64a-38bb-4b68-b772-5b943265522e', 'admin@cantinanonna.com.br', 'senha123');

INSERT INTO Configuracoes (Horario_funcionamento) VALUES ('Seg a Sex - 18h as 23h');

INSERT INTO Categoria (ID, Nome) VALUES ('2d02c4f1-39fa-4cc9-b7b2-a42e7bde93e4', 'Massas');
INSERT INTO Categoria (ID, Nome) VALUES ('56b9c9f2-fbcf-46c5-92db-a5171df5d15a', 'Bebidas');

INSERT INTO Produtos (ID, Nome, Descricao, Preco, Categoria, Imagem) VALUES ('1b80dbfa-30a2-4a00-af15-585bb049e6aa', 'Espaguete a Bolonhesa', 'Massa fresca com molho de tomate e carne moída', 45.90, '2d02c4f1-39fa-4cc9-b7b2-a42e7bde93e4', 'http://imagem.com/espaguete.png');

INSERT INTO Cliente (ID, CPF, Nome, Sobrenome, email, senha) VALUES ('884d5d4d-cc83-4a11-a8cf-81ff0a184e9b', '12345678901', 'Joao', 'Silva', 'joao.silva@email.com', '123456');

