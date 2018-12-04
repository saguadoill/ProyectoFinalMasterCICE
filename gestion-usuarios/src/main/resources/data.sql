INSERT INTO usuarios (id_usuario,username,foto_url, passwd, perfil, nombre, apellidos, cambio_passwd) VALUES
  (1,'admin','src/main/resources/fotos/logo_user_off.png', '$2a$10$JR4QF6zFg0aP0/TUZqZeOeFlDnmF/FVI.qmA6ticlADU6CExvp3Hu', 'ROLE_ADMIN' ,'Administrador', 'General',true),
  (2,'user','src/main/resources/fotos/logo_user_off.png', '$2a$10$k56IA7jfjNGT.O3QqGuRSuHWhT2pzMOQeYik5g6rHogMWphtWojFS', 'ROLE_USER' ,'Usuario', 'Normal',true ),
  (3,'clean','src/main/resources/fotos/logo_user_off.png', '$2a$10$/b0zkTAJ7wZAGwr7AoRZC.E52nfawIGKwz543Bie4Z/sFRROh3g2m', 'ROLE_CLEAN' ,'Administrador', 'Limpieza',true),
  (4,'cambiarpasswd','src/main/resources/fotos/logo_user_off.png', '$2a$10$GYjc72LFw/PGHI2Wl5niruzl9i/CHpULzbyqK/e3HZiobIe5AzEr2', 'ROLE_USER' ,'Usuario', 'CambioContraseña',false);






