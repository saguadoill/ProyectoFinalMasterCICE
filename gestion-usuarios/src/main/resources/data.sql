INSERT  INTO perfiles (id_perfil,nombre_perfil) VALUES ( 1, 'ROLE_ADMIN' ),
                                                       ( 2, 'ROLE_USER' ),
                                                       ( 3, 'ROLE_CLEAN');

INSERT INTO usuarios (id_usuario,username,foto_url, passwd, perfil, nombre, apellidos, cambio_passwd) VALUES
  (1,'admin','src/main/resources/fotos/logo_user_off.png', '$2a$10$JR4QF6zFg0aP0/TUZqZeOeFlDnmF/FVI.qmA6ticlADU6CExvp3Hu', 1 ,'Administrador', 'General',true),
  (2,'user','src/main/resources/fotos/logo_user_off.png', '$2a$10$k56IA7jfjNGT.O3QqGuRSuHWhT2pzMOQeYik5g6rHogMWphtWojFS', 2 ,'Usuario', 'Normal',true ),
  (3,'clean','src/main/resources/fotos/logo_user_off.png', '$2a$10$/b0zkTAJ7wZAGwr7AoRZC.E52nfawIGKwz543Bie4Z/sFRROh3g2m', 3 ,'Administrador', 'Limpieza',true),
  (4,'cambiarpasswd','src/main/resources/fotos/logo_user_off.png', '$2a$10$GYjc72LFw/PGHI2Wl5niruzl9i/CHpULzbyqK/e3HZiobIe5AzEr2', 2 ,'Usuario', 'CambioContraseña',false);