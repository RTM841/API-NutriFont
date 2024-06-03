-- Verificar y luego insertar si no existe
INSERT INTO categoria (nombre, img_Path) VALUES ('Carne', 'carne') ON DUPLICATE KEY UPDATE nombre = nombre;
INSERT INTO categoria (nombre, img_Path) VALUES ('Pescado', 'pescado') ON DUPLICATE KEY UPDATE nombre = nombre;

-- Insertar rol si no existe
INSERT INTO rol (name) VALUES ('ROLE_USER') ON DUPLICATE KEY UPDATE name = name;
INSERT INTO rol (name) VALUES ('ROLE_ADMINSUPERMERCADO') ON DUPLICATE KEY UPDATE name = name;


-- Insertar datos en la tabla supermercado
INSERT INTO supermercado (nombre, direccion, telefono) VALUES ('MarketCash', 'C/Gran Capitan', 639754546) ON DUPLICATE KEY UPDATE nombre = nombre;