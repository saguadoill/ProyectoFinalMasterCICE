
INSERT INTO tipos_modelos (id_tipomodelo,tipo)
VALUES (1,'Apartahotel'),
       (2,'Piso'),
       (3,'Casa Ruaral');

INSERT INTO propietarios (id_propietario,nombre,apellidos,telefono,email)
VALUES (1,'Hoteles Paco','','9182477930','hotelespaco@gmai.com'),
       (2,'Maira','Gomez Sanchezz','676493727','magomez97@hotmail.com'),
       (3,'toprural.com','','931283745','toprural@toprural.com');

INSERT INTO apartamentos (id_apartamento,id_tipomodelo,capacidad, direccion, piso, puerta, id_propietario,foto_url,disponible)
VALUES (1,1,4, 'Rio Hermosillo 24' ,'2', 'A',1,'src/main/resources/fotos/logo_apartamento.png',true),
       (2,2,2, 'Pobedilla 4' ,'1', 'Izquierda',2,'src/main/resources/fotos/logo_apartamento.png',false),
       (3,3,6, 'Calle Amaranto 6' ,'', '',3,'src/main/resources/fotos/logo_apartamento.png',true) ;