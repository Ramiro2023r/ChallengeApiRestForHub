CREATE TABLE topicos (
    id          BIGINT NOT NULL AUTO_INCREMENT,
    titulo      VARCHAR(200) NOT NULL,
    mensaje     TEXT NOT NULL,
    fecha_creacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    status      VARCHAR(50) NOT NULL DEFAULT 'ABIERTO',
    autor       VARCHAR(100) NOT NULL,
    curso       VARCHAR(100) NOT NULL,
    activo      TINYINT NOT NULL DEFAULT 1,

    PRIMARY KEY (id),
    UNIQUE KEY uk_titulo_mensaje (titulo, mensaje)
);