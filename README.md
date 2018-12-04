

**GestApaMS 0.1 (Gestion Apartamentos Microservicios)**

_Memoria borrador(muchos de los datos son notas para mi.)_

Índice

1. Información general.

2. Entrono Cloud (si se puede)

3. Bases de Datos y Modelos

4. Funcionamiento previsto (lo que quiero que haga de forma generalizada)

5. Funcionamiento (Hasta lo que he podido hacer)

6. Spring Security

7. Microservicios

8. Biografía y búsquedas

9. Herramientas utilizadas



















**Información general de la Aplicación**

        Se trata de una aplicación con la que poder gestionar apartamentos vacacionales. No se trata de una gestión de reservas (aunque se podría incluir si fuera necesario). Pensado para empresas que gestione una cantidad elevada de apartamentos o pisos.  Valido también para hoteles y hostales(con pocas modificaciones).

        A parte del apartamento, es posible gestionar otros servicios como Parkings, Servicio Limpieza y escalonar la aplicación para cualquier otro servicio necesario.

        La aplicación está orientada a funcionar con Microservicios. Debido a limitaciones con el hardware, no se ha podido fraccionar la aplicación en el proyecto, pero en la memoria se indican los distintos microservicios que habría.

**Entrono Cloud (si se puede)**

- --Spring Configure Server
- --Spring Eureka Server
- --Spring Zuul Server
- --Hystryx y Ribbon

**Bases de Datos y Modelos**

        Las bases de datos se han realizado en H2(in memory) para que se pueda probar mejor

1. a)UsuariosDB: Base de datos de los usuarios que tendrán acceso y su nivel de acceso; Administrador, usuario, limpieza, etc.

1. b)ApartamentosDB: Base de datos de los apartamentos/pisos que dispone el cliente para su ocupación.

1. c)ReservasDB: Base de datos de las reservas que tiene el cliente.

- --Cada microservicios con su base de datos si la necesita
- --No relacional (Feign)
- --H2 para mejor comprobación del proyecto por parte del profesor
- --Generadores de Datos
- --Modelos utilizados (Usuario, Apartamento, PlazaParking, etc. )

**Funcionamiento previsto (lo que quiero que haga de forma generalizada)**

- --esquema funcionalidad (si da tiempo)
- --esquema de usuario (si da tiempo)

**Funcionamiento final (Hasta lo que he podido hacer)**

- --Cliente web: página web donde accede el cliente. La parte Front de Usuarios y Apartamentos está hecha, con ayuda de Thymeleaf para volcar datos y enviar los dtos

**Spring Security**

        Explicar el Microservicio para autentificarse en la web. Uso de Feign para consultar usuario y password. Definir recursos y terminar de definir el acceso a las paginas según el perfil.

**Microservicios**

1. Usuarios(&quot;gestión-usuarios&quot;)

Microservicio encargado de la gestión de los usuarios y todos los datos relacionados. Comunica directamente con la base de datos, en este caso H2 en memoria para pruebas.

1. Apartamentos(&quot;gestión-apartamentos&quot;)

Microservicio encargado de la gestión de los apartamentos/pisos/habitaciones que pueda tener el cliente. Base de datos H2 en memoria para pruebas. También se incluye la gestión de los propietarios. Esta función podría separarse en otro Microservicio.

1. Reservas (&quot;gestión-reservas&quot;)

Microservicio encargado de gestionar las reservas. Base de datos propia en H2 en memoria. También se encargaría de procesar las reservas entrantes de otros sitios, he utilizado por ejemplo de Booking.com, utilizo un response de prueba y gracias a un conversor de XML a objeto puedo tratar y transformar esas reservas a las de la propia aplicación.



**Biografía y búsquedas**

 Lo que he tenido que buscar y de donde lo he buscado para hacerlo.

- --Thymeleaf
- --SpringSecurity
- --JAXB API (xml to Object)

**Herramientas utilizadas**

- --Intellij

