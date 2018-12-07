
INSERT INTO tipos_modelos (id_capacidad,capacidad)
VALUES (1,'2PAX'),
       (2,'3PAX'),
       (3,'4PAX'),
       (4,'5PAX'),
       (5,'6PAX'),
       (6,'7PAX');

INSERT INTO propietarios (id_propietario,nombre,apellidos,telefono,email)
VALUES (1,'MarinaDor','','9182477930','marinador@gmai.com'),
       (2,'Maira','Gomez Sanchez','676493727','magomez97@hotmail.com'),
       (3,'toprural.com','','931283745','toprural@toprural.com');

INSERT INTO apartamentos (id_apartamento,capacidad, direccion, piso, puerta, id_propietario,foto_url,disponible)
VALUES (1,3, 'Edificio Esmeralda I, Calle Ronda 24' ,'2', 'A',1,'src/main/resources/fotos/logo_apartamento.png',true),
       (2,2, 'Edificio Esmeralda I, Calle Ronda 24' ,'4', 'D',1,'src/main/resources/fotos/logo_apartamento.png',false),
       (3,1, 'Edificio Esmeralda II, Calle Cicuta 2' ,'8', 'B',1,'src/main/resources/fotos/logo_apartamento.png',true),
       (4,6, 'Calle Amaranto 6' ,'', '',2,'src/main/resources/fotos/logo_apartamento.png',true) ,
       (5,3, 'Rio Hermosillo 24' ,'2', 'A',3,'src/main/resources/fotos/logo_apartamento.png',true),
       (6,2, 'Pobedilla 4' ,'1', 'Izquierda',3,'src/main/resources/fotos/logo_apartamento.png',false),
       (7,6, 'Calle Amaranto 6' ,'', '',3,'src/main/resources/fotos/logo_apartamento.png',true) ,
       (8,4, 'Edificio Rubi I, Calle Francisco I' ,'2', 'A',1,'src/main/resources/fotos/logo_apartamento.png',true),
       (9,2, 'Edificio Rubi I, Calle Francisco I' ,'5', 'C',1,'src/main/resources/fotos/logo_apartamento.png',false),
       (10,1, 'Calle Amaranto 6' ,'', '',3,'src/main/resources/fotos/logo_apartamento.png',true) ;