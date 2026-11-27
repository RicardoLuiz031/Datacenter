CREATE DATABASE Datacenter;
USE Datacenter;

CREATE TABLE Usuarios(
    id_Usuarios int AUTO_INCREMENT PRIMARY KEY,
    tipo_usuario enum("Administrador" , "Tecnico" , "Visitante") not null,
    nome varchar(200) not null,
    cpf varchar(14) not null,
    telefone varchar(18) not null,
    endereco varchar(170) not null,
    email varchar(220) not null,
    login varchar(100) not null,
    senha varchar(12) not null,
    tentativas_falhas int default 0,
    bloqueado_ate datetime null
);

CREATE TABLE Acesso(
    id_Acesso int AUTO_INCREMENT PRIMARY KEY,
    data_acesso datetime not null,
    area_acessada enum("sala de servidores" , "sala de rede" , "sala de energia"),
    tipo_movimento enum("entrada" , "saida"),
    resultado enum("permitido" , "negado"),
    motivo_negacao text,
    id_Usuarios int not null,
    FOREIGN KEY(id_Usuarios) REFERENCES Usuarios (id_Usuarios)
);

CREATE TABLE Autorizacoes_visitantes(
    id_Autorizacoes_visitantes int AUTO_INCREMENT PRIMARY KEY,
    Data_inicio date not null,
    Data_validade datetime not null,
    Ativa boolean default true,
    id_Usuarios int not null,
    FOREIGN KEY(id_Usuarios) REFERENCES Usuarios (id_Usuarios)
);