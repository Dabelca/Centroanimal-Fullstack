CREATE TABLE IF NOT EXISTS voluntariado (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_usuario BIGINT NOT NULL,
    fecha_postulacion DATE NOT NULL,
    fecha_inscripcion DATE,
    horario VARCHAR(255) NOT NULL,
    estado VARCHAR(50) NOT NULL
);