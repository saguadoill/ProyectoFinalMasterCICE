
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

INSERT INTO huespedes (id_huesped,nombre,apellidos,telefono,email,dni)
VALUES (0,'Admin','Admin','','','0'),
       (1,'Josefa','Gallardo','9182477930','josefa@gmai.com','35483671F'),
       (2,'Jorge','Fernandez Sanchez','676493727','jforenan@hotmail.com','89218758X'),
       (3,'Carmen','Jimenez Altaya','931283745','carmen@gmail.com','62445891V');


INSERT INTO apartamentos (id_apartamento,capacidad, direccion, piso, puerta, id_propietario,foto_url,id_huesped)
VALUES (1,3, 'Edificio Esmeralda I, Calle Ronda 24' ,'2', 'A',1,'src/main/resources/fotos/logo_apartamento.png',null),
       (2,2, 'Edificio Esmeralda I, Calle Ronda 24' ,'4', 'D',1,'src/main/resources/fotos/logo_apartamento.png',1),
       (3,1, 'Edificio Esmeralda II, Calle Cicuta 2' ,'8', 'B',1,'src/main/resources/fotos/logo_apartamento.png',null),
       (4,6, 'Calle Cicuta 6' ,'', '',2,'src/main/resources/fotos/logo_apartamento.png',null) ,
       (5,3, 'Rio Hermosillo 24' ,'2', 'A',3,'src/main/resources/fotos/logo_apartamento.png',null),
       (6,2, 'Calle Pobedilla 4' ,'1', 'Izquierda',3,'src/main/resources/fotos/logo_apartamento.png',3),
       (7,6, 'Calle Amaranto 6' ,'', '',3,'src/main/resources/fotos/logo_apartamento.png',null) ,
       (8,4, 'Edificio Rubi I, Calle Francisco I' ,'2', 'A',1,'src/main/resources/fotos/logo_apartamento.png',null),
       (9,2, 'Edificio Rubi I, Calle Francisco I' ,'5', 'C',1,'src/main/resources/fotos/logo_apartamento.png',2),
       (10,1, 'Calle Amaranto 6' ,'', '',3,'src/main/resources/fotos/logo_apartamento.png',null) ;