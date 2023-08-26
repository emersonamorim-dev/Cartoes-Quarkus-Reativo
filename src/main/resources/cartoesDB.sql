CREATE TABLE cartoes (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    numero VARCHAR(16) NOT NULL UNIQUE,
    validade DATE NOT NULL,
    codigoSeguranca VARCHAR(4) NOT NULL,
    tipo VARCHAR(50) CHECK (tipo IN ('debito', 'credito')) NOT NULL
);
