CREATE TABLE usuario (
  id SERIAL PRIMARY KEY,
  username VARCHAR(100),
  password VARCHAR(255),
  nombre VARCHAR(255),
  email VARCHAR(255),
  role VARCHAR(50)
);

CREATE TABLE categoria (
  id SERIAL PRIMARY KEY,
  nombre VARCHAR(255),
  descripcion TEXT
);

CREATE TABLE post (
  id SERIAL PRIMARY KEY,
  titulo VARCHAR(255),
  contenido TEXT,
  creado_en TIMESTAMP,
  autor_id INTEGER REFERENCES usuario(id),
  categoria_id INTEGER REFERENCES categoria(id)
);

CREATE TABLE etiqueta (
  id SERIAL PRIMARY KEY,
  nombre VARCHAR(255)
);

CREATE TABLE post_etiqueta (
  post_id INTEGER REFERENCES post(id),
  etiqueta_id INTEGER REFERENCES etiqueta(id),
  PRIMARY KEY (post_id, etiqueta_id)
);

CREATE TABLE comentario (
  id SERIAL PRIMARY KEY,
  texto VARCHAR(2000),
  creado_en TIMESTAMP,
  autor_id INTEGER REFERENCES usuario(id),
  post_id INTEGER REFERENCES post(id)
);

CREATE TABLE multimedia (
  id SERIAL PRIMARY KEY,
  url VARCHAR(255),
  tipo VARCHAR(50),
  post_id INTEGER REFERENCES post(id)
);

CREATE TABLE post_like (
  id SERIAL PRIMARY KEY,
  usuario_id INTEGER REFERENCES usuario(id),
  post_id INTEGER REFERENCES post(id)
);
