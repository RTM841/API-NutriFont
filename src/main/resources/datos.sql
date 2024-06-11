-- Verificar y luego insertar si no existe
INSERT INTO categoria (nombre, img_Path) VALUES ('Carne', 'carne') ON DUPLICATE KEY UPDATE nombre = nombre;
INSERT INTO categoria (nombre, img_Path) VALUES ('Pescado', 'pescado') ON DUPLICATE KEY UPDATE nombre = nombre;
INSERT INTO categoria (nombre, img_Path) VALUES ('Cereal', 'cereal') ON DUPLICATE KEY UPDATE nombre = nombre;
INSERT INTO categoria (nombre, img_Path) VALUES ('Fruta', 'fruta') ON DUPLICATE KEY UPDATE nombre = nombre;
INSERT INTO categoria (nombre, img_Path) VALUES ('Leche', 'leche') ON DUPLICATE KEY UPDATE nombre = nombre;
INSERT INTO categoria (nombre, img_Path) VALUES ('Verduras', 'verduras') ON DUPLICATE KEY UPDATE nombre = nombre;
INSERT INTO categoria (nombre, img_Path) VALUES ('Resposteria', 'pasteleszl') ON DUPLICATE KEY UPDATE nombre = nombre;

-- Insertar rol si no existe
INSERT INTO rol (name) VALUES ('ROLE_USER') ON DUPLICATE KEY UPDATE name = name;
INSERT INTO rol (name) VALUES ('ROLE_ADMINSUPERMERCADO') ON DUPLICATE KEY UPDATE name = name;

-- Insertar datos en la tabla supermercado
INSERT INTO supermercado (nombre, direccion, telefono) VALUES ('MarketCash', 'C/Gran Capitan', 639754546) ON DUPLICATE KEY UPDATE nombre = nombre;

-- Insertar productos
INSERT INTO producto (codigo_barras,nombre,img_path,descripcion,disponibilidad,precio,
                          calorias,grasa_saturada,hidratos,azucares,proteinas,sal,
                          gluten,crustaceo,huevo,pescado,cacahuetes,soja,leche,proteina_leche,
                          lactosa,frutos_cascara,apio,mostaza,sesamo,sulfitos,altramuces,moluscos,categoria)
VALUES (1,'Batido Avena Chocolate 250ml','batido','Ingredientes: Leche de Avena(80%),Cacao(20%)','Disponible',
        13.45,145,10,0.4,0.3,10,0.5,true,false,false,false,false,false,true,false,true,false,false,false,false,
        false,false,false,1);

INSERT INTO producto (codigo_barras,nombre,img_path,descripcion,disponibilidad,precio,
                      calorias,grasa_saturada,hidratos,azucares,proteinas,sal,
                      gluten,crustaceo,huevo,pescado,cacahuetes,soja,leche,proteina_leche,
                      lactosa,frutos_cascara,apio,mostaza,sesamo,sulfitos,altramuces,moluscos,categoria)
VALUES (2,'Balsa Churros','churros','Ingredientes: Masa(80%),Agua(20%)','Disponible',
        23.45,145,10,0.4,0.3,10,0.5,true,false,false,false,false,false,true,false,true,false,false,false,false,
        false,false,false,4);

INSERT INTO producto (codigo_barras,nombre,img_path,descripcion,disponibilidad,precio,
                      calorias,grasa_saturada,hidratos,azucares,proteinas,sal,
                      gluten,crustaceo,huevo,pescado,cacahuetes,soja,leche,proteina_leche,
                      lactosa,frutos_cascara,apio,mostaza,sesamo,sulfitos,altramuces,moluscos,categoria)
VALUES (3,'Yogur 250G','yogur','Ingredientes: Leche(80%),Azucar(20%)','No Disponible',
        4.45,145,10.5,0.4,0.3,10,0.5,true,false,false,false,false,false,true,false,true,false,false,false,false,
        false,false,false,3);