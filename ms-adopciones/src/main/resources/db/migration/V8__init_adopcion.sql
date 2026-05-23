CREATE TABLE IF NOT EXISTS adopcion (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    estado VARCHAR(255),
    fecha_adopcion DATE,
    id_animal BIGINT,
    id_usuario BIGINT
    );