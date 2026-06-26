CREATE TABLE notificaciones (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_usuario BIGINT NOT NULL,
    mensaje VARCHAR(255) NOT NULL,
    tipo_notificacion VARCHAR(50) NOT NULL,
    fecha_notificacion DATE NOT NULL
);