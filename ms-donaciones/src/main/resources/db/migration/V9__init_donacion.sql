CREATE TABLE IF NOT EXISTS `donación` (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha DATE,
    monto DOUBLE NOT NULL,
    id_usuario BIGINT NULL,
    mensaje VARCHAR(500) NULL
);