-- Crear base de datos
CREATE DATABASE gestion_funcionarios;
USE gestion_funcionarios;

-- Tabla estado_civil
CREATE TABLE estado_civil (
    id_estado INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(50)
);

-- Tabla tipo_documento
CREATE TABLE tipo_documento (
    id_tipo INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(50)
);

-- Tabla funcionarios
CREATE TABLE funcionarios (
    id_funcionario INT AUTO_INCREMENT PRIMARY KEY,
    id_tipo INT,
    id_estado INT,
    nombres VARCHAR(100),
    apellidos VARCHAR(100),
    direccion VARCHAR(100),
    telefono VARCHAR(20),
    documento VARCHAR(30),
    sexo CHAR(1),
    FOREIGN KEY (id_tipo) REFERENCES tipo_documento(id_tipo),
    FOREIGN KEY (id_estado) REFERENCES estado_civil(id_estado)
);

-- Tabla grupo_familiar
CREATE TABLE grupo_familiar (
    id_familiar INT AUTO_INCREMENT PRIMARY KEY,
    id_funcionario INT,
    nombre VARCHAR(100),
    parentesco VARCHAR(50),
    FOREIGN KEY (id_funcionario) REFERENCES funcionarios(id_funcionario)
);

-- Tabla formacion_academica
CREATE TABLE formacion_academica (
    id_formacion INT AUTO_INCREMENT PRIMARY KEY,
    id_funcionario INT,
    universidad VARCHAR(100),
    nivel_estudio VARCHAR(50),
    titulo_obtenido VARCHAR(100),
    FOREIGN KEY (id_funcionario) REFERENCES funcionarios(id_funcionario)
);

-- Poblado inicial
INSERT INTO estado_civil (descripcion) VALUES ('Soltero'), ('Casado'), ('Unión libre'), ('Divorciado');

INSERT INTO tipo_documento (descripcion) VALUES ('DNI'), ('Cédula'), ('Pasaporte'), ('Tarjeta de identidad');