
CREATE TABLE IF NOT EXISTS tipo_documento (
    id_tipo_doc INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(50) NOT NULL
);


CREATE TABLE IF NOT EXISTS estado_civil (
    id_estado_civil INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(50) NOT NULL
);


CREATE TABLE IF NOT EXISTS funcionario (
    id_funcionario INT AUTO_INCREMENT PRIMARY KEY,
    id_tipo_doc INT NOT NULL,
    id_estado_civil INT NOT NULL,
    numero_documento VARCHAR(20) NOT NULL,
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    direccion VARCHAR(150),
    telefono VARCHAR(20),
    sexo CHAR(1),
    FOREIGN KEY (id_tipo_doc) REFERENCES tipo_documento(id_tipo_doc),
    FOREIGN KEY (id_estado_civil) REFERENCES estado_civil(id_estado_civil)
);


INSERT INTO tipo_documento (descripcion) VALUES
('DNI'),
('Pasaporte'),
('Carnet de Extranjería');


INSERT INTO estado_civil (descripcion) VALUES
('Soltero'),
('Casado'),
('Divorciado'),
('Viudo');


INSERT INTO funcionario (
    id_tipo_doc, id_estado_civil, numero_documento, nombres, apellidos, direccion, telefono, sexo
) VALUES
(1, 2, '12345678', 'Juan', 'Pérez', 'Av. Siempre Viva 123', '999888777', 'M'),
(2, 1, 'A987654', 'María', 'Gómez', 'Calle Falsa 456', '888777666', 'F');
