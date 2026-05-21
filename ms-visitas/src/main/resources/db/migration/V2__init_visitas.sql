CREATE TABLE visitas (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         id_usuario BIGINT NULL,
                         id_animal BIGINT NULL,
                         tipo_visita VARCHAR(50) NOT NULL,
                         fecha_visita DATE NOT NULL,
                         estado VARCHAR(20) NOT NULL
);