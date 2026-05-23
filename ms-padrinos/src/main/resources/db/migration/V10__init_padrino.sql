CREATE TABLE IF NOT EXISTS padrinos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha_inicio DATE,
    monto_cuota DOUBLE NOT NULL,
    id_animal BIGINT NOT NULL,
    id_usuario BIGINT NOT NULL,
    estado VARCHAR(255) NOT NULL
    );