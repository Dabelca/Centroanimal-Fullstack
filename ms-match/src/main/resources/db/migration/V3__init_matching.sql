CREATE TABLE matchings (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_usuario BIGINT NOT NULL,
    especie_preferida VARCHAR(50) NULL,
    tamano_preferido VARCHAR(50) NULL,
    edad_preferida INT NULL,
    resultado VARCHAR(255) NOT NULL
);